-- FUNCTION: tm.func_trading_market_calc(bigint)

-- DROP FUNCTION IF EXISTS tm.func_trading_market_calc(bigint);

CREATE OR REPLACE FUNCTION tm.func_trading_market_calc(
    tmid bigint)
    RETURNS integer
    LANGUAGE 'plpgsql'
    COST 100
    VOLATILE PARALLEL UNSAFE
AS $BODY$
DECLARE row_counter int;
BEGIN

    DELETE FROM  tm.trading_market_distribution WHERE trading_market_id = tmid;

    CREATE TEMP TABLE IF NOT EXISTS tm_stores ON COMMIT DROP AS
    SELECT  trading_market_stores.store_id, trading_market_stores.trading_market_id
    FROM
        tm.trading_market_stores
    WHERE trading_market_stores.trading_market_id = tmId;

    CREATE TEMP TABLE IF NOT EXISTS dm_store_nomen ON COMMIT DROP AS
    SELECT
        demandqnt
         ,sales_speed
         ,stockqnt
         ,demand.store_id
         ,demand.assortment_plan_id
         ,store.organization_id
         ,store.price_zone_id
         ,store.threshold_values_id
         ,nomenclature.id as nomenclature_id
         ,tm_stores.trading_market_id
    FROM tm.demand
             INNER JOIN tm_stores
                        ON tm_stores.store_id = tm.demand.store_id
             INNER JOIN tm.store
                        ON store.id = demand.store_id
             LEFT JOIN tm.nomenclature
                       ON nomenclature.assortment_plan_id = demand.assortment_plan_id;




    CREATE TEMP TABLE IF NOT EXISTS cp_nomen ON COMMIT DROP AS
    SELECT
        dm_store_nomen.demandqnt
         ,dm_store_nomen.sales_speed
         ,dm_store_nomen.stockqnt
         ,dm_store_nomen.store_id
         ,dm_store_nomen.assortment_plan_id
         ,dm_store_nomen.organization_id
         ,dm_store_nomen.price_zone_id
         ,dm_store_nomen.threshold_values_id
         ,dm_store_nomen.nomenclature_id
         ,agreement.counterparty_id
         ,COALESCE(agreement.deferment_of_payment, 0) as deferment_of_payment
         ,COALESCE(agreement.deferment_rate, 0) as deferment_rate
         ,COALESCE(agreement.min_first_order_amoung) as min_first_order_amoung
         ,COALESCE(agreement.min_secondary_order_amoung, 0) as min_secondary_order_amoung
         ,counterparts_nomenclature.id as counterparts_nomenclature_id
         ,trading_market_id
    FROM dm_store_nomen
             INNER JOIN tm.agreement
                        ON agreement.organization_id = dm_store_nomen.organization_id
             INNER JOIN tm.counterparts_nomenclature
                        ON counterparts_nomenclature.nomenclature_id = dm_store_nomen.nomenclature_id;



    CREATE TEMP TABLE IF NOT EXISTS tp ON COMMIT DROP AS
    SELECT
        demandqnt
         ,sales_speed
         ,stockqnt
         ,store_id
         ,assortment_plan_id
         ,organization_id
         ,cp_nomen.price_zone_id
         ,cp_nomen.threshold_values_id
         ,nomenclature_id
         ,cp_nomen.counterparty_id
         ,deferment_of_payment
         ,deferment_rate
         ,min_first_order_amoung
         ,min_secondary_order_amoung
         ,cp_nomen.counterparts_nomenclature_id
         ,trading_market_id
         ,COALESCE(price_list.counterpartys_price, 0) as counterpartys_price
         ,COALESCE(price_list.counterpartys_stock, 0) as counterpartys_stock
         ,COALESCE(price_list.multiplicity_of, 1) as multiplicity_of
         ,COALESCE(price_list.shelf_life, current_date) as shelf_life
         ,COALESCE(t1.deviation, 999999) as BESTPRICEDEVIATION
         ,COALESCE(t2.deviation, 999999) as EXPIRATIONDATEDEVIATION
         ,COALESCE(t3.deviation, 999999) as MULTIPLICITYDEVIATION
         ,COALESCE(t4.deviation, 999999) as MINSUPPLBALANCE
         ,COALESCE(t5.deviation, 999999) as MAXSTOCK
         ,MIN(price_list.counterpartys_price) OVER (PARTITION BY assortment_plan_id) AS bestPrice
         ,COALESCE(CEIL(CAST(demandqnt AS DOUBLE PRECISION)/price_list.multiplicity_of ) * price_list.multiplicity_of, 0) as roundDemand
    FROM cp_nomen
             LEFT JOIN tm.price_list
                       ON price_list.counterparts_nomenclature_id = cp_nomen.counterparts_nomenclature_id
                           AND cp_nomen.counterparty_id = price_list.counterparty_id
             LEFT JOIN tm.threshold_values_table t1
                       ON t1.threshold_values_id = cp_nomen.threshold_values_id
                           and SUBSTRING(t1.threshold_category, 1, 18) = 'BESTPRICEDEVIATION'
                           and t1.min_price <= price_list.counterpartys_price
                           and t1.max_price >= price_list.counterpartys_price
             LEFT JOIN tm.threshold_values_table t2
                       ON t2.threshold_values_id = cp_nomen.threshold_values_id
                           and SUBSTRING(t2.threshold_category, 1, 23) = 'EXPIRATIONDATEDEVIATION'
                           and t2.min_price <= price_list.counterpartys_price
                           and t2.max_price >= price_list.counterpartys_price
             LEFT JOIN tm.threshold_values_table t3
                       ON t3.threshold_values_id = cp_nomen.threshold_values_id
                           and SUBSTRING(t3.threshold_category, 1, 21) = 'MULTIPLICITYDEVIATION'
                           and t3.min_price <= price_list.counterpartys_price
                           and t3.max_price >= price_list.counterpartys_price
             LEFT JOIN tm.threshold_values_table t4
                       ON t4.threshold_values_id = cp_nomen.threshold_values_id
                           and SUBSTRING(t4.threshold_category, 1, 15) = 'MINSUPPLBALANCE'
                           and t4.min_price <= price_list.counterpartys_price
                           and t4.max_price >= price_list.counterpartys_price
             LEFT JOIN tm.threshold_values_table t5
                       ON t5.threshold_values_id  = cp_nomen.threshold_values_id
                           and SUBSTRING(t5.threshold_category, 1, 8) = 'MAXSTOCK'
                           and t5.min_price <= price_list.counterpartys_price
                           and t5.max_price >= price_list.counterpartys_price;


    INSERT INTO tm.trading_market_distribution
    SELECT DISTINCT
        bestPrice as bestPrice
                  ,CASE WHEN (roundDemand * counterpartys_price / demandqnt / bestPrice * 100) <= BESTPRICEDEVIATION
                            THEN TRUE
                        ELSE FALSE
        END AS best_price_control
                  ,CAST(COALESCE(roundDemand * counterpartys_price / demandqnt + counterpartys_price * deferment_rate/100 * deferment_of_payment/365, 0) as NUMERIC(10,2))  as converted_price
                  ,counterpartys_price as counterparties_price
                  ,counterpartys_stock as counterparties_stock
                  ,demandqnt
                  ,CASE WHEN counterpartys_price IS NULL
                            THEN FALSE
                        ELSE TRUE
        END AS in_price_control
                  ,CASE WHEN (demandqnt + stockqnt)/sales_speed > MAXSTOCK
                            THEN FALSE
                        ELSE TRUE
        END as max_stock_control
                  ,CASE WHEN counterpartys_stock < MINSUPPLBALANCE
                            THEN FALSE
                        ELSE TRUE
        END as min_suppl_balance_control
                  ,CASE WHEN
                                    CASE WHEN (roundDemand + stockqnt)/sales_speed + EXPIRATIONDATEDEVIATION <= shelf_life - CURRENT_DATE
                                             THEN TRUE
                                         ELSE FALSE
                                        END = FALSE
                                OR
                                    CASE WHEN (roundDemand + stockqnt)/sales_speed > MULTIPLICITYDEVIATION
                                             THEN FALSE
                                         ELSE TRUE
                                        END  = FALSE
                            THEN FALSE
                        ELSE TRUE
        END
        AS multiplicity_control
                  ,multiplicity_of
                  ,CASE WHEN counterparts_nomenclature_id IS NULL
                            THEN FALSE
                        ELSE TRUE
        END AS nomen_comparition_control
                  ,stockqnt as on_stockqnt
                  ,roundDemand as round_demandqnt
                  ,sales_speed
                  ,shelf_life
                  ,CASE WHEN (demandqnt + stockqnt)/sales_speed + EXPIRATIONDATEDEVIATION <= shelf_life - CURRENT_DATE
                            THEN TRUE
                        ELSE FALSE
        END AS shelf_life_control
                  ,counterparts_nomenclature_id
                  ,store_id
                  ,counterparty_id
                  ,assortment_plan_id
                  ,trading_market_id
    FROM tp;

    SELECT COUNT(*) into row_counter FROM  tm.trading_market_distribution where trading_market_id = tmid;
    RETURN row_counter;

END
$BODY$;

ALTER FUNCTION tm.func_trading_market_calc(bigint)
    OWNER TO postgres;
