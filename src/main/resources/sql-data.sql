INSERT INTO `ims`.`customers` (`first_name`, `surname`) VALUES ('jordan', 'harrison');
INSERT INTO `ims`.`item` (`item_name`, `item_price`) VALUES ('computer', 100.25);
INSERT INTO `ims`.`orders` (`fk_customer_id`) VALUES (1); 
INSERT INTO `ims`.`order_items` (`fk_order_id`, `fk_item_id`) VALUES (1, 1);