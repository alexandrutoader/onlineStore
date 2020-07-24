-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               8.0.20-0ubuntu0.19.10.1 - (Ubuntu)
-- Server OS:                    Linux
-- HeidiSQL Version:             10.2.0.5599
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for online_store
CREATE DATABASE IF NOT EXISTS `online_store` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `online_store`;

-- Dumping structure for table online_store.address
CREATE TABLE IF NOT EXISTS `address` (
  `address_id` int unsigned NOT NULL AUTO_INCREMENT,
  `customer_id` int NOT NULL,
  `first_name` varchar(50) NOT NULL DEFAULT '',
  `last_name` varchar(50) NOT NULL DEFAULT '',
  `telephone` varchar(50) NOT NULL DEFAULT '',
  `address_name` varchar(50) NOT NULL DEFAULT '',
  `city` varchar(50) NOT NULL DEFAULT '',
  `postal_code` varchar(50) DEFAULT '',
  `status` tinyint NOT NULL,
  `date_added` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `date_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`address_id`),
  KEY `customer_id` (`customer_id`),
  KEY `status` (`status`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table online_store.address: ~6 rows (approximately)
DELETE FROM `address`;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
INSERT INTO `address` (`address_id`, `customer_id`, `first_name`, `last_name`, `telephone`, `address_name`, `city`, `postal_code`, `status`, `date_added`, `date_modified`) VALUES
	(1, 1, 'Ionut', 'Popescu', '0720331431', 'Str Test', 'Bacau', '1234', 1, '2020-07-12 16:07:19', '2020-07-23 22:22:03'),
	(3, 2, 'alex', 'ionut', '000000000', 'Str Test', 'Bucuresti', '1234', 1, '2020-07-17 00:00:00', '2020-07-23 22:37:45'),
	(4, 3, 'Mircea', 'Ilie', '0723821873', 'Str Pancota', 'Bucuresti', '1234', 1, '2020-07-17 00:00:00', '2020-07-23 22:37:46'),
	(5, 1, 'Andreea', 'Stoica', '0742652621', 'Str Ceahlau', 'Brasov', '6312', 1, '2020-07-17 00:00:00', '2020-07-21 11:11:57'),
	(6, 1, 'Ciprian', 'Dumitru', '0723123123', 'Str test', 'Iasi', '5555', 1, '2020-07-23 22:29:03', '2020-07-24 17:07:56'),
	(7, 1, 'Ciprian', 'Dumitru', '0723123123', 'Str test', 'Iasi', '5555', 1, '2020-07-23 22:34:10', '2020-07-24 17:07:57');
/*!40000 ALTER TABLE `address` ENABLE KEYS */;

-- Dumping structure for table online_store.cart
CREATE TABLE IF NOT EXISTS `cart` (
  `id` int NOT NULL,
  `customer_id` int DEFAULT NULL,
  `token` varchar(32) NOT NULL,
  `status` tinyint NOT NULL DEFAULT '0',
  `date_added` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `date_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `token` (`token`),
  KEY `customer_id` (`customer_id`),
  KEY `status` (`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table online_store.cart: ~0 rows (approximately)
DELETE FROM `cart`;
/*!40000 ALTER TABLE `cart` DISABLE KEYS */;
/*!40000 ALTER TABLE `cart` ENABLE KEYS */;

-- Dumping structure for table online_store.cart_products
CREATE TABLE IF NOT EXISTS `cart_products` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `cart_id` int NOT NULL,
  `product_id` int NOT NULL,
  `quantity` int NOT NULL,
  `status` tinyint NOT NULL DEFAULT '0',
  `date_added` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `date_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `product_id` (`product_id`),
  KEY `status` (`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table online_store.cart_products: ~0 rows (approximately)
DELETE FROM `cart_products`;
/*!40000 ALTER TABLE `cart_products` DISABLE KEYS */;
/*!40000 ALTER TABLE `cart_products` ENABLE KEYS */;

-- Dumping structure for table online_store.customer
CREATE TABLE IF NOT EXISTS `customer` (
  `customer_id` int unsigned NOT NULL AUTO_INCREMENT,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `telephone` varchar(50) NOT NULL,
  `password` varchar(100) NOT NULL,
  `address_id` int DEFAULT NULL,
  `agent_id` int DEFAULT NULL,
  `status` tinyint NOT NULL,
  `date_added` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `date_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`customer_id`),
  UNIQUE KEY `email` (`email`),
  KEY `first_name` (`first_name`),
  KEY `last_name` (`last_name`),
  KEY `email_key` (`email`),
  KEY `telephone` (`telephone`),
  KEY `address_id` (`address_id`),
  KEY `agent_id` (`agent_id`),
  KEY `status` (`status`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table online_store.customer: ~4 rows (approximately)
DELETE FROM `customer`;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` (`customer_id`, `first_name`, `last_name`, `email`, `telephone`, `password`, `address_id`, `agent_id`, `status`, `date_added`, `date_modified`) VALUES
	(1, 'Ciprian', 'Vasile', 'toader.alexandru02@gmail.com', '0720331431', '$2a$10$T60XrI7V9R59TBt3QqhDQexGz7X5i9yTO6/H1d37P/5dT.Y3ih92a', 1, 1, 1, '2020-07-12 00:00:00', '2020-07-12 00:00:00'),
	(2, 'Gigel', 'Bogdan', 't_alex02@yahoo.com', '0720331431', '$2a$10$ExcKxrVk7uQ2ON2KvcIwmOnNjXmhNlmrBc4Makt3gGDEEH8npmvxm', 1, 104, 0, '2020-07-17 00:00:00', '2020-07-17 00:00:00'),
	(3, 'Florin', 'Dumitru', 'test@yahoo.com', '0720331431', '$2a$10$Nwi3f6SKSNTjSSp80UvGLunSSEueGrJyr7aMoAxsA037Z8lPufPxG', 1, 104, 1, '2020-07-17 00:00:00', '2020-07-17 00:00:00'),
	(4, 'Mircea', 'Adrian', 'test@test.ro', '123456789', '$2a$10$9Bm0dSxCSqWqNfrwkXugeuA0Wmb9P9YvGJ9Yo3S7u/ukvClYDiFJS', 2, 0, 0, '2020-07-20 00:00:00', '2020-07-20 00:00:00');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;

-- Dumping structure for table online_store.invoice
CREATE TABLE IF NOT EXISTS `invoice` (
  `invoice_id` int unsigned NOT NULL AUTO_INCREMENT,
  `order_id` int NOT NULL,
  `invoice_number` int NOT NULL DEFAULT '0',
  `type` tinyint NOT NULL,
  `status` tinyint NOT NULL,
  `date_added` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `date_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`invoice_id`),
  KEY `order_id` (`order_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table online_store.invoice: ~2 rows (approximately)
DELETE FROM `invoice`;
/*!40000 ALTER TABLE `invoice` DISABLE KEYS */;
INSERT INTO `invoice` (`invoice_id`, `order_id`, `invoice_number`, `type`, `status`, `date_added`, `date_modified`) VALUES
	(1, 1, 1, 1, 0, '2020-07-20 17:36:33', '2020-07-24 17:08:35'),
	(2, 5, 2, 1, 1, '2020-07-20 17:36:33', '2020-07-24 17:08:36'),
	(3, 1, 1, 1, 1, '2020-07-23 22:44:36', '2020-07-24 17:08:37');
/*!40000 ALTER TABLE `invoice` ENABLE KEYS */;

-- Dumping structure for table online_store.order
CREATE TABLE IF NOT EXISTS `order` (
  `order_id` int unsigned NOT NULL AUTO_INCREMENT,
  `customer_id` int NOT NULL DEFAULT '0',
  `invoice_id` int DEFAULT NULL,
  `address_id` int NOT NULL,
  `payment_id` int NOT NULL,
  `total` decimal(10,0) NOT NULL DEFAULT '0',
  `currency` varchar(50) NOT NULL DEFAULT 'RON',
  `order_status_id` tinyint(1) NOT NULL,
  `status` tinyint(1) NOT NULL DEFAULT '1',
  `date_added` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `date_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`order_id`),
  KEY `customer_id` (`customer_id`),
  KEY `address_id` (`address_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table online_store.order: ~2 rows (approximately)
DELETE FROM `order`;
/*!40000 ALTER TABLE `order` DISABLE KEYS */;
INSERT INTO `order` (`order_id`, `customer_id`, `invoice_id`, `address_id`, `payment_id`, `total`, `currency`, `order_status_id`, `status`, `date_added`, `date_modified`) VALUES
	(1, 1, 1, 1, 1, 1000, 'RON', 1, 0, '2020-07-14 00:00:00', '2020-07-23 22:45:36'),
	(2, 2, 3, 3, 2, 2000, 'RON', 1, 1, '2020-07-14 00:00:00', '2020-07-24 17:08:54');
/*!40000 ALTER TABLE `order` ENABLE KEYS */;

-- Dumping structure for table online_store.order_status
CREATE TABLE IF NOT EXISTS `order_status` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `sort_order` tinyint NOT NULL,
  `date_added` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `date_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table online_store.order_status: ~6 rows (approximately)
DELETE FROM `order_status`;
/*!40000 ALTER TABLE `order_status` DISABLE KEYS */;
INSERT INTO `order_status` (`id`, `name`, `sort_order`, `date_added`, `date_modified`) VALUES
	(1, 'Comanda plasata', 1, '2020-07-12 13:25:08', '2020-07-12 13:25:08'),
	(2, 'Comanda preluata', 2, '2020-07-12 13:25:32', '2020-07-12 13:25:32'),
	(3, 'Comanda predata curierului', 3, '2020-07-12 13:26:11', '2020-07-12 13:26:11'),
	(4, 'Comanda livrata', 4, '2020-07-12 13:26:26', '2020-07-12 13:26:26'),
	(5, 'Comanda anulata', 5, '2020-07-12 13:26:44', '2020-07-12 13:26:44'),
	(6, 'Comanda finalizata', 6, '2020-07-23 22:51:27', '2020-07-23 22:51:57');
/*!40000 ALTER TABLE `order_status` ENABLE KEYS */;

-- Dumping structure for table online_store.payment
CREATE TABLE IF NOT EXISTS `payment` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `status` tinyint NOT NULL,
  `date_added` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table online_store.payment: ~2 rows (approximately)
DELETE FROM `payment`;
/*!40000 ALTER TABLE `payment` DISABLE KEYS */;
INSERT INTO `payment` (`id`, `name`, `status`, `date_added`) VALUES
	(1, 'Ramburs', 1, '2020-07-12 14:15:43'),
	(2, 'Card online', 1, '2020-07-12 14:15:43');
/*!40000 ALTER TABLE `payment` ENABLE KEYS */;

-- Dumping structure for table online_store.product
CREATE TABLE IF NOT EXISTS `product` (
  `product_id` int unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `price` decimal(10,0) NOT NULL DEFAULT '0',
  `quantity` int NOT NULL DEFAULT '0',
  `reserved` int NOT NULL,
  `image` varchar(255) DEFAULT NULL,
  `currency` varchar(3) DEFAULT 'RON',
  `status` tinyint(1) NOT NULL DEFAULT '0',
  `added_by` smallint NOT NULL,
  `date_added` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `date_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`product_id`),
  KEY `name` (`name`),
  KEY `added_by` (`added_by`),
  KEY `status` (`status`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table online_store.product: ~5 rows (approximately)
DELETE FROM `product`;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` (`product_id`, `name`, `price`, `quantity`, `reserved`, `image`, `currency`, `status`, `added_by`, `date_added`, `date_modified`) VALUES
	(1, 'Televizor SAMSUNG HB500', 2600, 500, 0, '/test', 'RON', 1, 1, '2020-07-12 13:29:28', '2020-07-21 16:39:42'),
	(2, 'Televizor LG 780LV', 3200, 100, 0, NULL, 'RON', 1, 1, '2020-07-12 13:30:26', '2020-07-12 13:30:26'),
	(3, 'Sitem audio Philips 400 DJI', 1599, 50, 0, NULL, 'RON', 1, 1, '2020-07-12 13:31:16', '2020-07-12 13:31:16'),
	(4, 'Combina frigorifica SAMSUNG', 2200, 10, 0, '/combina', 'RON', 1, 1, '2020-07-12 00:00:00', '2020-07-12 00:00:00'),
	(5, 'test', 800, 100, 0, '/arctic', 'RON', 1, 1, '2020-07-21 00:00:00', '2020-07-21 17:01:37');
/*!40000 ALTER TABLE `product` ENABLE KEYS */;

-- Dumping structure for table online_store.user
CREATE TABLE IF NOT EXISTS `user` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `password` varchar(100) NOT NULL,
  `user_role_id` int NOT NULL,
  `status` tinyint(1) NOT NULL DEFAULT '0',
  `date_added` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `date_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `email` (`email`),
  KEY `username` (`username`),
  KEY `status` (`status`),
  KEY `user_role_id` (`user_role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table online_store.user: ~0 rows (approximately)
DELETE FROM `user`;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`id`, `username`, `first_name`, `last_name`, `email`, `password`, `user_role_id`, `status`, `date_added`, `date_modified`) VALUES
	(1, 'alex.toader', 'Alexandru', 'Toader', 'toader.alexandru02@gmail.com', '$2a$10$2VUGQJUzURdA08RG447eZOOa0EUDFb4EOZXxnZ5FwBfrfKrypwSzC', 1, 1, '2020-07-24 00:43:07', '2020-07-24 00:43:07'),
	(2, 'mihai.andrei', 'Mihai', 'Andrei', 'toader.alexandru02@gmail.com', '$2a$10$2VUGQJUzURdA08RG447eZOOa0EUDFb4EOZXxnZ5FwBfrfKrypwSzS', 2, 1, '2020-07-24 17:09:42', '2020-07-24 17:09:42');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

-- Dumping structure for table online_store.user_role
CREATE TABLE IF NOT EXISTS `user_role` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `role` varchar(50) NOT NULL,
  `status` tinyint NOT NULL,
  `date_added` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table online_store.user_role: ~2 rows (approximately)
DELETE FROM `user_role`;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` (`id`, `role`, `status`, `date_added`) VALUES
	(1, 'ADMIN', 1, '2020-07-12 13:22:16'),
	(2, 'USER', 1, '2020-07-12 13:22:42');
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
