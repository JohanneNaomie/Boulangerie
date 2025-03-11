create view v_all_production as
select  productions.id_production as id_production ,
        productions.id_product as id_product ,
        products.name_product as name_product,
        ingredients.id_ingredient as id_ingredient ,
        productions.quantity as quantity ,
        productions.date_time as date_time 
from bakery_productions as productions
join bakery_products as products 
    on products.id_product = productions.id_product
join bakery_composition_products as c_products 
    on c_products.id_product = productions.id_product
join bakery_ingredients as ingredients 
    on ingredients.id_ingredient = c_products.id_ingredient;

create or replace view  v_all_sales as 
select s.id_sale , c.name, c.id_client  , p.name_product ,p.id_product , bc.name_category ,
    bc.id_category , bp.name_parfum , bp.id_parfum , s.quantity, s.quantity*bpprice.price as total_price ,
    s.date_sales , id_seller 
from bakery_sales as s
join bakery_product_parfums as bpp
    on s.id_pp = bpp.id_pp 
join bakery_parfums as bp 
    on bpp.id_parfum = bp.id_parfum
join bakery_products as p
    on p.id_product = bpp.id_product
join bakery_product_categories as bpc
    on bpc.id_product = p.id_product
join bakery_categories as bc
    on bc.id_category = bpc.id_category
join bakery_product_prices as bpprice
    on bpprice.id_product = p.id_product
join bakery_clients as c 
    on s.id_client=c.id_client;

CREATE or replace view v_calcule_commission as
select 
    id_seller , 
    name ,
    case 
        when total_price < 200000 
        THEN 0
        else total_price 
    end as total_price,
    date_sales
from v_all_sales    ;

create or replace view v_all_commissions as 
select 
    s.id_seller , 
    sel.name ,
    sex.sexe ,
    sum(total_price*5/100) as commission ,
    date_sales
from v_calcule_commission as s
join bakery_sellers as sel 
    on s.id_seller = sel.id_seller
join bakery_sexe as sex 
    on sex.id_sexe = sel.id_sexe
group by s.id_seller,date_sales,sel.name,sex.sexe;

create or replace view v_quantity_product_sale as
select 
    id_product ,  
    case when s.quantity is NULL 
        THEN 0 
        else s.quantity
    end as quantity
    ,date_sales
from bakery_sales as s
RIGHT join bakery_product_parfums as pp
on pp.id_pp = s.id_pp ;

SELECT id_product , sum(quantity) as quantity FROM v_quantity_product_sale 
GROUP BY id_product ORDER BY quantity DESC ;