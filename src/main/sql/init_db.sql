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

alter table only suppliers
  add constraint fk_supplier_id foreign key (id) references products(id);
  
alter table only categories
  add constraint fk_category_id foreign key (id) references products(id);