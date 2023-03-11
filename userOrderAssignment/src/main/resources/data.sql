insert into tbl_user(name,email,password,phone_number)
values('antony_ajay_','ajay@gmail.com','xyz','9901924843');
insert into tbl_user(name,email,password,phone_number)
values('abc','abc@gmail.com','xyz','9901924843');
insert into tbl_user(name,email,password,phone_number)
values('xyz','xyz@gmail.com','xyz','9901924843');

insert into tbl_product(name,price,category,description,brand)
values('iPhone',100000,'phone','Apple Iphone','Apple');
insert into tbl_product(name,price,category,description,brand)
values('1+ 7t',100000,'phone','One plus','One plus');
insert into tbl_product(name,price,category,description,brand)
values('Tshirt',1000,'Shirt','Polo tshirt','Arrow');
insert into tbl_product(name,price,category,description,brand)
values('Drop-shoulders',1000,'Shirt','drop shoulder','Skrullers');

insert into tbl_address(phone_number,zipcode,state,user_id)
values('8885577','560077','Karnataka',1);
insert into tbl_address(phone_number,zipcode,state,user_id)
values('845547','660056','Tamil Nadu',2);
insert into tbl_address(phone_number,zipcode,state,user_id)
values('478547','440066','Telangana',3);

insert into tbl_order(user_id,address_id,product_id,product_quantity)
values('1',1,2,3);
insert into tbl_order(user_id,address_id,product_id,product_quantity)
values('1',1,1,5);
insert into tbl_order(user_id,address_id,product_id,product_quantity)
values('2',2,2,6);


