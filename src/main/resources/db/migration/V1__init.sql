# ************************************************************
# Sequel Pro SQL dump
# Version 4541
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: 127.0.0.1 (MySQL 5.7.29)
# Database: secret_hitler
# Generation Time: 2020-02-28 21:37:21 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table admins
# ------------------------------------------------------------

DROP TABLE IF EXISTS `admins`;

CREATE TABLE `admins` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  `email` varchar(40) DEFAULT NULL,
  `name` varchar(40) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `role` int(11) DEFAULT NULL,
  `username` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKmi8vkhus4xbdbqcac2jm4spvd` (`username`),
  UNIQUE KEY `UK47bvqemyk6vlm0w7crc3opdd4` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table games
# ------------------------------------------------------------

DROP TABLE IF EXISTS `games`;

CREATE TABLE `games` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  `game_hash` varchar(40) DEFAULT NULL,
  `name` varchar(40) DEFAULT NULL,
  `admin_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK7x99x3yacbpxnxux4huwt1cnt` (`admin_id`),
  CONSTRAINT `FK7x99x3yacbpxnxux4huwt1cnt` FOREIGN KEY (`admin_id`) REFERENCES `admins` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table players
# ------------------------------------------------------------

DROP TABLE IF EXISTS `players`;

CREATE TABLE `players` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  `name` varchar(40) DEFAULT NULL,
  `game_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKchry7scrns4qyuyxe9dpod9k3` (`game_id`),
  CONSTRAINT `FKchry7scrns4qyuyxe9dpod9k3` FOREIGN KEY (`game_id`) REFERENCES `games` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table policies
# ------------------------------------------------------------

DROP TABLE IF EXISTS `policies`;

CREATE TABLE `policies` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `is_discarded` bit(1) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `game_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKjwsh9vvwyonsxdk9coqctf95e` (`game_id`),
  CONSTRAINT `FKjwsh9vvwyonsxdk9coqctf95e` FOREIGN KEY (`game_id`) REFERENCES `games` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table roles
# ------------------------------------------------------------

DROP TABLE IF EXISTS `roles`;

CREATE TABLE `roles` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  `type` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table stages
# ------------------------------------------------------------

DROP TABLE IF EXISTS `stages`;

CREATE TABLE `stages` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  `stage_name` varchar(40) DEFAULT NULL,
  `stage_type` varchar(40) DEFAULT NULL,
  `player_id` bigint(20) NOT NULL,
  `policy_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKfixksna6m742xxvhgekaf1a8d` (`player_id`),
  KEY `FKnvsuad4re1ua9yshm3iwyf6jc` (`policy_id`),
  CONSTRAINT `FKfixksna6m742xxvhgekaf1a8d` FOREIGN KEY (`player_id`) REFERENCES `players` (`id`),
  CONSTRAINT `FKnvsuad4re1ua9yshm3iwyf6jc` FOREIGN KEY (`policy_id`) REFERENCES `policies` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;




/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
