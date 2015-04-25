/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `address`
--

DROP TABLE IF EXISTS `address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `address` (
  `address_id` int(11) NOT NULL AUTO_INCREMENT,
  `addressName` varchar(255) DEFAULT NULL,
  `city` varchar(255) NOT NULL,
  `email_address` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `post_code` varchar(255) NOT NULL,
  `state` varchar(255) NOT NULL,
  `street_address_1` varchar(255) NOT NULL,
  `street_address_2` varchar(255) DEFAULT NULL,
  `suburb` varchar(255) NOT NULL,
  `telephone_number` varchar(255) DEFAULT NULL,
  `customer_id` int(11) NOT NULL,
  PRIMARY KEY (`address_id`),
  KEY `FK_nydg76tqo86ue3m5wsoojho6b` (`customer_id`),
  CONSTRAINT `FK_nydg76tqo86ue3m5wsoojho6b` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `basket`
--

DROP TABLE IF EXISTS `basket`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `basket` (
  `basket_id` int(11) NOT NULL,
  `date_created` datetime NOT NULL,
  `session_id` varchar(255) NOT NULL,
  `customer_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`basket_id`),
  UNIQUE KEY `UK_6qcuo8kr17qrlflaa8brnv2f9` (`customer_id`),
  CONSTRAINT `FK_6qcuo8kr17qrlflaa8brnv2f9` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `basket_item`
--

DROP TABLE IF EXISTS `basket_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `basket_item` (
  `basket_id` int(11) NOT NULL,
  `sku` varchar(255) NOT NULL,
  `basket_item_price` decimal(19,2) NOT NULL,
  `basket_item_special_price` decimal(19,2) NOT NULL,
  `quantity` int(11) NOT NULL,
  `product_id` int(11) NOT NULL,
  PRIMARY KEY (`basket_id`,`sku`),
  KEY `FK_ixi5l9vr7n8phw23d3yqlm7p2` (`product_id`),
  CONSTRAINT `FK_ggvh312sbowaxuw75dm7tt9m8` FOREIGN KEY (`basket_id`) REFERENCES `basket` (`basket_id`),
  CONSTRAINT `FK_ixi5l9vr7n8phw23d3yqlm7p2` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `basket_item_to_product_attribute`
--

DROP TABLE IF EXISTS `basket_item_to_product_attribute`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `basket_item_to_product_attribute` (
  `basket_id` int(11) NOT NULL,
  `sku` varchar(255) NOT NULL,
  `product_attribute_id` int(11) NOT NULL,
  KEY `FK_t9p2acvwo6b9cce5hmy3pa8ys` (`product_attribute_id`),
  KEY `FK_cfa07b972smu4tl6ik6jalv0s` (`basket_id`,`sku`),
  CONSTRAINT `FK_cfa07b972smu4tl6ik6jalv0s` FOREIGN KEY (`basket_id`, `sku`) REFERENCES `basket_item` (`basket_id`, `sku`),
  CONSTRAINT `FK_t9p2acvwo6b9cce5hmy3pa8ys` FOREIGN KEY (`product_attribute_id`) REFERENCES `product_attribute` (`product_attribute_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category` (
  `category_id` int(11) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `category_name` varchar(255) NOT NULL,
  `parent_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`category_id`),
  KEY `FK_81thrbnb8c08gua7tvqj7xdqk` (`parent_id`),
  CONSTRAINT `FK_81thrbnb8c08gua7tvqj7xdqk` FOREIGN KEY (`parent_id`) REFERENCES `category` (`category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `configuration`
--

DROP TABLE IF EXISTS `configuration`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `configuration` (
  `key` varchar(255) NOT NULL,
  `value` varchar(255) NOT NULL,
  PRIMARY KEY (`key`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customer` (
  `customer_id` int(11) NOT NULL AUTO_INCREMENT,
  `birth_date` datetime NOT NULL,
  `document` varchar(255) NOT NULL,
  `email_address` varchar(255) NOT NULL,
  `first_name` varchar(255) NOT NULL,
  `gender` char(1) NOT NULL,
  `last_logon` datetime DEFAULT NULL,
  `last_name` varchar(255) NOT NULL,
  `mobile_phone_number` varchar(255) DEFAULT NULL,
  `number_of_logons` int(11) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `register_date` datetime NOT NULL,
  `telephone_number` varchar(255) DEFAULT NULL,
  `basket_id` int(11) DEFAULT NULL,
  `address_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`customer_id`),
  KEY `FK_dajolo4nrao68kn6629ewb3wu` (`basket_id`),
  KEY `FK_r8whbd0mf9er6vwfr0sclsxkd` (`address_id`),
  CONSTRAINT `FK_dajolo4nrao68kn6629ewb3wu` FOREIGN KEY (`basket_id`) REFERENCES `basket` (`basket_id`),
  CONSTRAINT `FK_r8whbd0mf9er6vwfr0sclsxkd` FOREIGN KEY (`address_id`) REFERENCES `address` (`address_id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `customer_group`
--

DROP TABLE IF EXISTS `customer_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customer_group` (
  `customer_group_id` int(11) NOT NULL,
  PRIMARY KEY (`customer_group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `customer_to_customer_group`
--

DROP TABLE IF EXISTS `customer_to_customer_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customer_to_customer_group` (
  `customer_id` int(11) NOT NULL,
  `customer_group_id` int(11) NOT NULL,
  KEY `FK_sdm0673y8ai53b5t0lmd40jwl` (`customer_group_id`),
  KEY `FK_2eiygidw1pe1n9gfk60gt0qui` (`customer_id`),
  CONSTRAINT `FK_2eiygidw1pe1n9gfk60gt0qui` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`),
  CONSTRAINT `FK_sdm0673y8ai53b5t0lmd40jwl` FOREIGN KEY (`customer_group_id`) REFERENCES `customer_group` (`customer_group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `manufacturer`
--

DROP TABLE IF EXISTS `manufacturer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `manufacturer` (
  `manufacturer_id` int(11) NOT NULL,
  `manufacturer_name` varchar(255) NOT NULL,
  `manufacturer_url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`manufacturer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `order_product`
--

DROP TABLE IF EXISTS `order_product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order_product` (
  `order_id` int(11) NOT NULL,
  `sku` varchar(255) NOT NULL,
  `price` decimal(19,2) NOT NULL,
  `quantity` int(11) NOT NULL,
  `special_price` decimal(19,2) DEFAULT NULL,
  `product_id` int(11) NOT NULL,
  PRIMARY KEY (`order_id`,`sku`),
  KEY `FK_729vil5pvmbprq6b1sf4en1ft` (`product_id`),
  CONSTRAINT `FK_729vil5pvmbprq6b1sf4en1ft` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`),
  CONSTRAINT `FK_tpt9pulqsn4ahvm8tcl8uyue4` FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `order_product_to_product_attribute`
--

DROP TABLE IF EXISTS `order_product_to_product_attribute`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order_product_to_product_attribute` (
  `order_id` int(11) NOT NULL,
  `sku` varchar(255) NOT NULL,
  `product_attribute_id` int(11) NOT NULL,
  KEY `FK_cn2t8o18uamwqsybutdd7t2ty` (`product_attribute_id`),
  KEY `FK_q7avyg9p7smsw3704vnpj7gsf` (`order_id`,`sku`),
  CONSTRAINT `FK_cn2t8o18uamwqsybutdd7t2ty` FOREIGN KEY (`product_attribute_id`) REFERENCES `product_attribute` (`product_attribute_id`),
  CONSTRAINT `FK_q7avyg9p7smsw3704vnpj7gsf` FOREIGN KEY (`order_id`, `sku`) REFERENCES `order_product` (`order_id`, `sku`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `order_status`
--

DROP TABLE IF EXISTS `order_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order_status` (
  `order_status_id` int(11) NOT NULL,
  `cancelable` bit(1) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`order_status_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `order_status_history`
--

DROP TABLE IF EXISTS `order_status_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order_status_history` (
  `order_status_history_id` int(11) NOT NULL,
  `comments` varchar(255) DEFAULT NULL,
  `customer_notified` bit(1) NOT NULL,
  `date_added` datetime NOT NULL,
  `order_id` int(11) NOT NULL,
  `order_status_id` int(11) NOT NULL,
  PRIMARY KEY (`order_status_history_id`),
  KEY `FK_axuy5mcnhlcn9o0b8q3lg2uv2` (`order_id`),
  KEY `FK_5coowpum9d58s31831vqlnsw4` (`order_status_id`),
  CONSTRAINT `FK_5coowpum9d58s31831vqlnsw4` FOREIGN KEY (`order_status_id`) REFERENCES `order_status` (`order_status_id`),
  CONSTRAINT `FK_axuy5mcnhlcn9o0b8q3lg2uv2` FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `order_total`
--

DROP TABLE IF EXISTS `order_total`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order_total` (
  `order_total_id` int(11) NOT NULL,
  `order_total_module` varchar(255) NOT NULL,
  `text` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `value` decimal(19,2) NOT NULL,
  `order_id` int(11) NOT NULL,
  PRIMARY KEY (`order_total_id`),
  KEY `FK_l518rpkbv1dvhlqhcswgbs9kd` (`order_id`),
  CONSTRAINT `FK_l518rpkbv1dvhlqhcswgbs9kd` FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orders` (
  `order_id` int(11) NOT NULL AUTO_INCREMENT,
  `billing_address1` varchar(255) DEFAULT NULL,
  `billing_address2` varchar(255) DEFAULT NULL,
  `billing_city` varchar(255) DEFAULT NULL,
  `billing_name` varchar(255) DEFAULT NULL,
  `billing_post_code` varchar(255) DEFAULT NULL,
  `billing_state` varchar(255) DEFAULT NULL,
  `billing_suburb` varchar(255) DEFAULT NULL,
  `billing_telephone` varchar(255) DEFAULT NULL,
  `customer_address1` varchar(255) DEFAULT NULL,
  `customer_address2` varchar(255) DEFAULT NULL,
  `customer_city` varchar(255) DEFAULT NULL,
  `customer_name` varchar(255) DEFAULT NULL,
  `customer_post_code` varchar(255) DEFAULT NULL,
  `customer_state` varchar(255) DEFAULT NULL,
  `customer_suburb` varchar(255) DEFAULT NULL,
  `customer_telephone` varchar(255) DEFAULT NULL,
  `date_finished` datetime DEFAULT NULL,
  `date_modified` datetime NOT NULL,
  `date_purchased` datetime NOT NULL,
  `delivery_address1` varchar(255) NOT NULL,
  `delivery_address2` varchar(255) DEFAULT NULL,
  `delivery_city` varchar(255) NOT NULL,
  `delivery_name` varchar(255) NOT NULL,
  `delivery_post_code` varchar(255) NOT NULL,
  `delivery_state` varchar(255) NOT NULL,
  `delivery_suburb` varchar(255) NOT NULL,
  `delivery_telephone` varchar(255) NOT NULL,
  `payment_method` varchar(255) NOT NULL,
  `shipping_service` varchar(255) NOT NULL,
  `tracking_number` varchar(255) DEFAULT NULL,
  `customer_id` int(11) NOT NULL,
  `order_status_id` int(11) NOT NULL,
  PRIMARY KEY (`order_id`),
  KEY `FK_astys1dv61mdlp0n0wx0574r2` (`customer_id`),
  KEY `FK_sd54uh7m15taltnaj0e22cuny` (`order_status_id`),
  CONSTRAINT `FK_astys1dv61mdlp0n0wx0574r2` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`),
  CONSTRAINT `FK_sd54uh7m15taltnaj0e22cuny` FOREIGN KEY (`order_status_id`) REFERENCES `order_status` (`order_status_id`)
) ENGINE=InnoDB AUTO_INCREMENT=66 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `payment_notification`
--

DROP TABLE IF EXISTS `payment_notification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `payment_notification` (
  `payment_notification_id` int(11) NOT NULL,
  `order_id` int(11) NOT NULL,
  PRIMARY KEY (`payment_notification_id`),
  KEY `FK_dvjx4868hdyeacb582uskw33w` (`order_id`),
  CONSTRAINT `FK_dvjx4868hdyeacb582uskw33w` FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product` (
  `product_id` int(11) NOT NULL,
  `date_added` datetime NOT NULL,
  `date_available` datetime NOT NULL,
  `details` text,
  `expiry_date` datetime DEFAULT NULL,
  `free_gift` bit(1) NOT NULL,
  `height` decimal(19,2) NOT NULL,
  `product_name` varchar(255) NOT NULL,
  `price` decimal(19,2) NOT NULL,
  `technical_details` text,
  `weight` decimal(19,2) NOT NULL,
  `width` decimal(19,2) NOT NULL,
  `manufacturer_id` int(11) NOT NULL,
  PRIMARY KEY (`product_id`),
  KEY `FK_9iq5p820dkqa93ra5wjlwcksi` (`manufacturer_id`),
  CONSTRAINT `FK_9iq5p820dkqa93ra5wjlwcksi` FOREIGN KEY (`manufacturer_id`) REFERENCES `manufacturer` (`manufacturer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `product_attribute`
--

DROP TABLE IF EXISTS `product_attribute`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product_attribute` (
  `product_attribute_id` int(11) NOT NULL,
  `product_attribute_name` varchar(255) NOT NULL,
  `product_attribute_value` decimal(19,2) DEFAULT NULL,
  `product_option_id` int(11) NOT NULL,
  PRIMARY KEY (`product_attribute_id`),
  KEY `FK_thpkqgl3yg7x2jjfh9v4oef5` (`product_option_id`),
  CONSTRAINT `FK_thpkqgl3yg7x2jjfh9v4oef5` FOREIGN KEY (`product_option_id`) REFERENCES `product_option` (`product_option_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `product_image`
--

DROP TABLE IF EXISTS `product_image`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product_image` (
  `product_image_id` int(11) NOT NULL,
  `image_path` varchar(255) NOT NULL,
  `image_type` varchar(255) NOT NULL,
  `sku` varchar(255) NOT NULL,
  PRIMARY KEY (`product_image_id`),
  KEY `FK_jb6c85860ls60mn7ki10bbmxo` (`sku`),
  CONSTRAINT `FK_jb6c85860ls60mn7ki10bbmxo` FOREIGN KEY (`sku`) REFERENCES `product_quantity` (`sku`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `product_option`
--

DROP TABLE IF EXISTS `product_option`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product_option` (
  `product_option_id` int(11) NOT NULL,
  `product_option_name` varchar(255) NOT NULL,
  PRIMARY KEY (`product_option_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `product_quantity`
--

DROP TABLE IF EXISTS `product_quantity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product_quantity` (
  `sku` varchar(255) NOT NULL,
  `quantity` int(11) NOT NULL,
  `product_id` int(11) NOT NULL,
  PRIMARY KEY (`sku`),
  KEY `FK_f5h48buaolnnv96wbkjjg1icm` (`product_id`),
  CONSTRAINT `FK_f5h48buaolnnv96wbkjjg1icm` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `product_quantity_to_product_attribute`
--

DROP TABLE IF EXISTS `product_quantity_to_product_attribute`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product_quantity_to_product_attribute` (
  `sku` varchar(255) NOT NULL,
  `product_attribute_id` int(11) NOT NULL,
  KEY `FK_sf5ttwb61ly1lybbq8uera51r` (`product_attribute_id`),
  KEY `FK_8i76q3lm35425j2i3it4vm2x` (`sku`),
  CONSTRAINT `FK_8i76q3lm35425j2i3it4vm2x` FOREIGN KEY (`sku`) REFERENCES `product_quantity` (`sku`),
  CONSTRAINT `FK_sf5ttwb61ly1lybbq8uera51r` FOREIGN KEY (`product_attribute_id`) REFERENCES `product_attribute` (`product_attribute_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `product_to_category`
--

DROP TABLE IF EXISTS `product_to_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product_to_category` (
  `product_id` int(11) NOT NULL,
  `category_id` int(11) NOT NULL,
  KEY `FK_q22o6s24hfborglo1ovvi7619` (`category_id`),
  KEY `FK_fimllq2wjr3homh0e0fd23ema` (`product_id`),
  CONSTRAINT `FK_fimllq2wjr3homh0e0fd23ema` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`),
  CONSTRAINT `FK_q22o6s24hfborglo1ovvi7619` FOREIGN KEY (`category_id`) REFERENCES `category` (`category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `promotion_discount`
--

DROP TABLE IF EXISTS `promotion_discount`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `promotion_discount` (
  `promotion_id` int(11) NOT NULL,
  `cumulative` bit(1) DEFAULT NULL,
  `date_added` datetime DEFAULT NULL,
  `date_modified` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `discount` decimal(19,2) DEFAULT NULL,
  `end_date` datetime DEFAULT NULL,
  `promotion_name` varchar(255) DEFAULT NULL,
  `percentual` bit(1) DEFAULT NULL,
  `start_date` datetime DEFAULT NULL,
  `discount_value` decimal(19,2) DEFAULT NULL,
  `percent` bit(1) DEFAULT NULL,
  PRIMARY KEY (`promotion_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-04-08 19:13:16
