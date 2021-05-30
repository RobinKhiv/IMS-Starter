drop schema ims;

CREATE SCHEMA IF NOT EXISTS `ims`;

USE `ims` ;

CREATE TABLE IF NOT EXISTS `ims`.`customers` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `first_name` VARCHAR(40) DEFAULT NULL,
    `surname` VARCHAR(40) DEFAULT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `ims`.`item` (
	`id` INT NOT NULL AUTO_INCREMENT,
    `item_name` VARCHAR(40) DEFAULT NULL,
    `item_price` DECIMAL(6,2) DEFAULT 0,
    PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `ims`.`orders` (
	`id` INT NOT NULL AUTO_INCREMENT,
    `fk_customer_id` INT NOT NULL,
    PRIMARY KEY (`id`),
    KEY (`fk_customer_id`),
    FOREIGN KEY (`fk_customer_id`) 
		REFERENCES `item` (`id`) 
			ON DELETE CASCADE
);

