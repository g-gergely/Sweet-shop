create table products
(
id int,
name varchar,
description varchar,
default_price float,
currency varchar,
supplier_id int,
category_id int
);

create table suppliers
(
id int,
name varchar,
description varchar
);

create table categories
(
id int,
name varchar,
description varchar,
department varchar
);

create sequence products_id_seq;

alter table products alter column id set default nextval('public.products_id_seq');

alter sequence products_id_seq owned by products.id;

create sequence suppliers_id_seq;

alter table suppliers alter column id set default nextval('public.suppliers_id_seq');

alter sequence suppliers_id_seq owned by suppliers.id;

create sequence categories_id_seq;

alter table categories alter column id set default nextval('public.categories_id_seq');

alter sequence categories_id_seq owned by categories.id;

create unique index categories_id_uindex
  on categories (id);

create unique index products_id_uindex
  on products (id);

create unique index suppliers_id_uindex
  on suppliers (id);

alter table only suppliers
  add constraint fk_supplier_id foreign key (id) references products(id);

alter table only categories
  add constraint fk_category_id foreign key (id) references products(id);