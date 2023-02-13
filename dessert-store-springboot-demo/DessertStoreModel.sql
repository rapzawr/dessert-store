/*
 Navicat Premium Data Transfer

 Source Server         : sakila
 Source Server Type    : MySQL
 Source Server Version : 80022
 Source Host           : localhost:3306
 Source Schema         : dessertstore

 Target Server Type    : MySQL
 Target Server Version : 80022
 File Encoding         : 65001

 Date: 18/11/2022 16:14:16
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for customer
-- ----------------------------
DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer`  (
  `customer_id` int UNSIGNED NOT NULL AUTO_INCREMENT,
  `wallet_id` int UNSIGNED NOT NULL,
  `customer_name` varchar(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '',
  `address` varchar(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '',
  `contact_no` int NOT NULL,
  PRIMARY KEY (`customer_id`, `wallet_id`) USING BTREE,
  INDEX `wallet_id`(`wallet_id`) USING BTREE,
  INDEX `customer_id`(`customer_id`) USING BTREE,
  CONSTRAINT `wallet_id` FOREIGN KEY (`wallet_id`) REFERENCES `wallet` (`wallet_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of customer
-- ----------------------------
INSERT INTO `customer` VALUES (1, 1, 'Rapzawr', 'Pasay ', 12345678);
INSERT INTO `customer` VALUES (2, 2, 'Zawrap', 'Makati', 123456789);

-- ----------------------------
-- Table structure for dessert
-- ----------------------------
DROP TABLE IF EXISTS `dessert`;
CREATE TABLE `dessert`  (
  `dessert_id` int UNSIGNED NOT NULL AUTO_INCREMENT,
  `dessert_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `price` decimal(10, 2) NULL DEFAULT NULL,
  `is_available` int NOT NULL DEFAULT 0,
  PRIMARY KEY (`dessert_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dessert
-- ----------------------------
INSERT INTO `dessert` VALUES (1, 'Covet', 450.00, 1);
INSERT INTO `dessert` VALUES (2, 'Indulge', 450.00, 1);
INSERT INTO `dessert` VALUES (3, 'Climax', 450.00, 1);
INSERT INTO `dessert` VALUES (4, 'Coffee', 450.00, 1);

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders`  (
  `order_id` int UNSIGNED NOT NULL AUTO_INCREMENT,
  `customer_id` int UNSIGNED NOT NULL,
  `dessert_id` int UNSIGNED NOT NULL,
  `quantity` int NOT NULL,
  `total_price` int NOT NULL,
  `is_delivered` int NULL DEFAULT 0,
  `order_date` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`order_id`, `customer_id`) USING BTREE,
  INDEX `customer_id`(`customer_id`) USING BTREE,
  INDEX `dessert_id`(`dessert_id`) USING BTREE,
  CONSTRAINT `customer_id` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `dessert_id` FOREIGN KEY (`dessert_id`) REFERENCES `dessert` (`dessert_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES (1, 1, 1, 3, 1350, 1, NULL);
INSERT INTO `orders` VALUES (2, 1, 1, 6, 2700, 1, NULL);
INSERT INTO `orders` VALUES (3, 1, 1, 8, 3600, 1, NULL);
INSERT INTO `orders` VALUES (4, 1, 1, 8, 3600, 1, NULL);
INSERT INTO `orders` VALUES (5, 1, 1, 9, 4050, 1, NULL);
INSERT INTO `orders` VALUES (6, 1, 1, 9, 4050, 1, NULL);
INSERT INTO `orders` VALUES (7, 1, 1, 10, 4500, 1, NULL);
INSERT INTO `orders` VALUES (8, 1, 1, 10, 4500, 1, NULL);
INSERT INTO `orders` VALUES (9, 1, 1, 11, 4950, 1, NULL);
INSERT INTO `orders` VALUES (10, 1, 1, 11, 4950, 1, NULL);
INSERT INTO `orders` VALUES (11, 1, 1, 11, 4950, 1, NULL);
INSERT INTO `orders` VALUES (12, 1, 1, 11, 4950, 1, NULL);
INSERT INTO `orders` VALUES (13, 1, 1, 11, 4950, 1, NULL);
INSERT INTO `orders` VALUES (15, 1, 1, 11, 4950, 1, NULL);
INSERT INTO `orders` VALUES (16, 1, 1, 11, 4950, 1, NULL);
INSERT INTO `orders` VALUES (17, 2, 1, 10, 4500, 1, NULL);
INSERT INTO `orders` VALUES (18, 2, 1, 10, 4500, 1, NULL);

-- ----------------------------
-- Table structure for wallet
-- ----------------------------
DROP TABLE IF EXISTS `wallet`;
CREATE TABLE `wallet`  (
  `wallet_id` int UNSIGNED NOT NULL AUTO_INCREMENT,
  `balance` int NOT NULL,
  PRIMARY KEY (`wallet_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of wallet
-- ----------------------------
INSERT INTO `wallet` VALUES (1, 10000);
INSERT INTO `wallet` VALUES (2, 45500);

SET FOREIGN_KEY_CHECKS = 1;
