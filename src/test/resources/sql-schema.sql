
DROP TABLE IF EXISTS `order_items`;
DROP TABLE IF EXISTS `orders`;
DROP TABLE IF EXISTS `customers`;

CREATE TABLE IF NOT EXISTS `imsTest`.`customers` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `first_name` VARCHAR(40) DEFAULT NULL,
    `surname` VARCHAR(40) DEFAULT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `imsTest`.`item` (
	`id` INT NOT NULL AUTO_INCREMENT,
    `item_name` VARCHAR(40) DEFAULT NULL,
    `item_price` DOUBLE DEFAULT 0,
    PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `imsTest`.`orders` (
	`id` INT NOT NULL AUTO_INCREMENT,
    `fk_customer_id` INT NOT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`fk_customer_id`) 
		REFERENCES `item` (`id`) 
			ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS `imsTest`.`order_items` (
	`id` INTEGER NOT NULL AUTO_INCREMENT,
	`fk_order_id` INT NOT NULL,
    `fk_item_id` INT NOT NULL,
	PRIMARY KEY (`id`),
    FOREIGN KEY (`fk_order_id`)
		REFERENCES `orders` (`id`)
			ON DELETE CASCADE,
	FOREIGN KEY (`fk_item_id`)
		REFERENCES `item` (`id`) 
			ON DELETE CASCADE
);