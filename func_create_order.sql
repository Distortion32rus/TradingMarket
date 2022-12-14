-- FUNCTION: tm.func_create_order(bigint, bigint)

-- DROP FUNCTION IF EXISTS tm.func_create_order(bigint, bigint);

CREATE OR REPLACE FUNCTION tm.func_create_order(
    tmid bigint,
    storeid bigint)
    RETURNS integer
    LANGUAGE 'plpgsql'
    COST 100
    VOLATILE PARALLEL UNSAFE
AS $BODY$
DECLARE orderID bigint;
    DECLARE row_counter int;
    DECLARE mviews RECORD;
BEGIN

    CREATE TEMP TABLE IF NOT EXISTS tm_disrtibutions ON COMMIT DROP AS
    SELECT
        trading_market_id
         ,store_id
         ,counterparty_id
         ,assortment_plan_id
         ,counterparts_nomenclature_id
         ,ROW_NUMBER() OVER(PARTITION BY store_id, assortment_plan_id ORDER BY converted_price)  as row_num
         ,counterparties_price
         ,round_demandqnt
         ,shelf_life
    FROM tm.trading_market_distribution
    where nomen_comparition_control AND multiplicity_control AND best_price_control AND min_suppl_balance_control AND max_stock_control AND in_price_control AND shelf_life_control
      AND store_id = storeid AND trading_market_id = tmid;

    CREATE TEMP TABLE IF NOT EXISTS tm_orders ON COMMIT DROP AS
    SELECT * FROM tm_disrtibutions where row_num = 1;

    CREATE TEMP TABLE IF NOT EXISTS tm_orders_header ON COMMIT DROP AS
    SELECT DISTINCT trading_market_id
                  ,store_id
                  ,counterparty_id FROM tm_disrtibutions where row_num = 1;

    FOR mviews IN
        SELECT tm_orders_header.*, nextval('tm.order_generator') as order_ID from tm_orders_header
        LOOP

            INSERT INTO tm.orders VALUES ( mviews.order_ID, current_date, mviews.counterparty_id, mviews.store_id, mviews.trading_market_id);

            INSERT INTO tm.order_position
            SELECT counterparties_price, round_demandqnt, shelf_life, mviews.order_ID, counterparts_nomenclature_id, assortment_plan_id FROM tm_orders;

        END LOOP ;

    SELECT COUNT(*) into row_counter FROM  tm.orders where trading_market_id = tmid;
    RETURN row_counter;

END
$BODY$;

ALTER FUNCTION tm.func_create_order(bigint, bigint)
    OWNER TO postgres;
