insert into product (id, price, name) values ('100', '10', 'Soap');
insert into product (id, price, name) values ('200', '15', 'Bread');
insert into product (id, price, name) values ('300', '5', 'Tomato');
insert into product (id, price, name) values ('400','8', 'Butter');
insert into product (id, price, name) values ('500', '30', 'Camera');
insert into product (id, price, name) values ('600','12', 'Diary');


insert into quantity_product (id, is_available, product_id) values ('101', 'true', '100');
insert into quantity_product (id, is_available, product_id) values ('102', 'true', '200');
insert into quantity_product (id, is_available, product_id) values ('103', 'true', '300');
insert into quantity_product (id, is_available, product_id) values ('104', 'true', '400');


insert into order_table (order_id, create_date, final_price) values ('1000', '2020-10-05 09:13:38', '200');
insert into order_table (order_id, create_date, final_price) values ('2000', '2020-12-05 09:13:38', '300');
