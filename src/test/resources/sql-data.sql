INSERT INTO `customers` (`first_name`, `surname`) VALUES ('jordan', 'harrison');
INSERT INTO `item` (`item_name`,`item_price`) VALUES ('test_item', 10.00);
INSERT INTO `orders` (`fk_customer_id`) VALUES (1);
INSERT INTO `order_items` (`fk_order_id`, `fk_item_id`) VALUES (1,1);