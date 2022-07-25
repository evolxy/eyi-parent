/*
 Navicat Premium Data Transfer

 Source Server         : docker-mysql80-3306
 Source Server Type    : MySQL
 Source Server Version : 80028
 Source Host           : localhost:3306
 Source Schema         : eyi

 Target Server Type    : MySQL
 Target Server Version : 80028
 File Encoding         : 65001

 Date: 04/07/2022 14:51:43
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

use eyi;
-- ----------------------------
-- Table structure for eyi_article
-- ----------------------------
DROP TABLE IF EXISTS `eyi_article`;
CREATE TABLE `eyi_article`  (
  `id` bigint NOT NULL,
  `create_id` bigint NULL DEFAULT NULL,
  `create_time` datetime(6) NULL DEFAULT NULL,
  `del_flag` int NULL DEFAULT NULL,
  `delete_id` bigint NULL DEFAULT NULL,
  `delete_time` datetime(6) NULL DEFAULT NULL,
  `update_id` bigint NULL DEFAULT NULL,
  `update_time` datetime(6) NULL DEFAULT NULL,
  `article_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `sub_title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `view_count` int NULL DEFAULT NULL,
  `comment_count` int NULL DEFAULT NULL,
  `like_count` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of eyi_article
-- ----------------------------
INSERT INTO `eyi_article` VALUES (551201931553411072, 1, '2022-06-20 14:52:02.018435', 1, NULL, NULL, NULL, '2022-06-20 14:52:02.018435', '62b019129846895e1bebdde7', '321', '3123', NULL, NULL, NULL);
INSERT INTO `eyi_article` VALUES (551208995436630016, 1, '2022-06-20 15:20:06.482125', 0, NULL, NULL, NULL, '2022-06-20 15:20:06.482125', '62b01fa69846895e1bebdde8', '123', '测试', NULL, NULL, NULL);
INSERT INTO `eyi_article` VALUES (551222425031806976, 1, '2022-06-20 17:03:45.182237', 0, NULL, NULL, NULL, '2022-06-20 17:03:45.182237', '62b02c28dc7013422bc2544d', '23132222', '11222', NULL, NULL, NULL);

-- ----------------------------
-- Table structure for eyi_article_catalog
-- ----------------------------
DROP TABLE IF EXISTS `eyi_article_catalog`;
CREATE TABLE `eyi_article_catalog`  (
  `article_id` bigint NOT NULL,
  `catalog_id` bigint NOT NULL,
  `create_id` bigint NULL DEFAULT NULL,
  `create_time` datetime(6) NULL DEFAULT NULL,
  `del_flag` int NULL DEFAULT NULL,
  `delete_id` bigint NULL DEFAULT NULL,
  `delete_time` datetime(6) NULL DEFAULT NULL,
  `update_id` bigint NULL DEFAULT NULL,
  `update_time` datetime(6) NULL DEFAULT NULL,
  `id` bigint NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK56wahjud1g4x1ky9roe9t2kc6`(`catalog_id`) USING BTREE,
  INDEX `FKbk70wirqoawc9q7davba38g97`(`article_id`) USING BTREE,
  CONSTRAINT `FK56wahjud1g4x1ky9roe9t2kc6` FOREIGN KEY (`catalog_id`) REFERENCES `eyi_catalog` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKbk70wirqoawc9q7davba38g97` FOREIGN KEY (`article_id`) REFERENCES `eyi_article` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of eyi_article_catalog
-- ----------------------------
INSERT INTO `eyi_article_catalog` VALUES (551201931553411072, 551201931729571840);
INSERT INTO `eyi_article_catalog` VALUES (551208995436630016, 551208995440824320);
INSERT INTO `eyi_article_catalog` VALUES (551222425031806976, 551222425111498752);
INSERT INTO `eyi_article_catalog` VALUES (551222425031806976, 551222425115693056);
INSERT INTO `eyi_article_catalog` VALUES (551222425031806976, 551235078663966720);

-- ----------------------------
-- Table structure for eyi_catalog
-- ----------------------------
DROP TABLE IF EXISTS `eyi_catalog`;
CREATE TABLE `eyi_catalog`  (
  `id` bigint NOT NULL,
  `create_id` bigint NULL DEFAULT NULL,
  `create_time` datetime(6) NULL DEFAULT NULL,
  `del_flag` int NULL DEFAULT NULL,
  `delete_id` bigint NULL DEFAULT NULL,
  `delete_time` datetime(6) NULL DEFAULT NULL,
  `update_id` bigint NULL DEFAULT NULL,
  `update_time` datetime(6) NULL DEFAULT NULL,
  `catalog_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `catalog_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of eyi_catalog
-- ----------------------------
INSERT INTO `eyi_catalog` VALUES (551201931729571840, NULL, '2022-06-20 14:52:02.018435', 0, NULL, NULL, NULL, '2022-06-20 14:52:02.018435', NULL, '121');
INSERT INTO `eyi_catalog` VALUES (551208995440824320, NULL, '2022-06-20 15:20:06.482125', 0, NULL, NULL, NULL, '2022-06-20 15:20:06.482125', NULL, '发放');
INSERT INTO `eyi_catalog` VALUES (551222425111498752, NULL, '2022-06-20 16:13:28.000000', 0, NULL, NULL, NULL, '2022-06-20 16:13:28.000000', NULL, '123');
INSERT INTO `eyi_catalog` VALUES (551222425115693056, NULL, '2022-06-20 16:13:28.000000', 0, NULL, NULL, NULL, '2022-06-20 16:13:28.000000', NULL, '222');
INSERT INTO `eyi_catalog` VALUES (551229573593829376, NULL, '2022-06-20 16:41:52.623343', 0, NULL, NULL, NULL, '2022-06-20 16:41:52.623343', NULL, '333');
INSERT INTO `eyi_catalog` VALUES (551229813495435264, NULL, '2022-06-20 16:42:49.882487', 0, NULL, NULL, NULL, '2022-06-20 16:42:49.882487', NULL, '555');
INSERT INTO `eyi_catalog` VALUES (551235078663966720, NULL, '2022-06-20 17:03:45.114059', 0, NULL, NULL, NULL, '2022-06-20 17:03:45.114059', NULL, '213123');

-- ----------------------------
-- Table structure for eyi_file_info
-- ----------------------------
DROP TABLE IF EXISTS `eyi_file_info`;
CREATE TABLE `eyi_file_info`  (
  `id` bigint NOT NULL,
  `create_id` bigint NULL DEFAULT NULL,
  `create_time` datetime(6) NULL DEFAULT NULL,
  `del_flag` int NULL DEFAULT NULL,
  `delete_id` bigint NULL DEFAULT NULL,
  `delete_time` datetime(6) NULL DEFAULT NULL,
  `update_id` bigint NULL DEFAULT NULL,
  `update_time` datetime(6) NULL DEFAULT NULL,
  `extension` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `origin_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `store_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of eyi_file_info
-- ----------------------------
INSERT INTO `eyi_file_info` VALUES (551188753155100672, NULL, '2022-06-20 13:59:40.338915', 1, NULL, NULL, NULL, '2022-06-29 15:44:21.253573', 'png', 'test.png', 'http://localhost:19099/eyi/test.png');
INSERT INTO `eyi_file_info` VALUES (551917069050843136, NULL, '2022-06-22 14:13:44.343994', 1, NULL, NULL, NULL, '2022-06-28 15:25:10.889855', 'png', '202206221413224444.png', 'http://localhost:19099/eyi/202206221413224444.png');
INSERT INTO `eyi_file_info` VALUES (553727949992366080, 1, '2022-06-27 14:09:32.056471', 1, NULL, NULL, 1, '2022-06-28 15:27:18.089847', 'png', '202206271409153131.png', 'http://localhost:19099/eyi/202206271409153131.png');
INSERT INTO `eyi_file_info` VALUES (553737562397609984, 1, '2022-06-27 14:47:43.832837', 1, NULL, NULL, 1, '2022-06-28 15:28:08.863162', 'png', '202206271447984242.png', 'http://localhost:19099/eyi/202206271447984242.png');
INSERT INTO `eyi_file_info` VALUES (1541610715250372610, NULL, '2022-06-28 10:33:30.930609', 0, NULL, NULL, NULL, '2022-06-28 10:33:30.930609', 'png', '202206281033163030.png', 'http://localhost:19099/eyi/202206281033163030.png');
INSERT INTO `eyi_file_info` VALUES (1542050983155757058, NULL, '2022-06-29 15:42:58.978662', 0, NULL, NULL, NULL, '2022-06-29 15:42:58.978662', 'jpg', 'wallhaven-v9x6gm.jpg', 'http://localhost:19099/eyi/wallhaven-v9x6gm.jpg');

-- ----------------------------
-- Table structure for eyi_permission
-- ----------------------------
DROP TABLE IF EXISTS `eyi_permission`;
CREATE TABLE `eyi_permission`  (
  `id` bigint NOT NULL,
  `create_id` bigint NULL DEFAULT NULL,
  `create_time` datetime(6) NULL DEFAULT NULL,
  `del_flag` int NULL DEFAULT NULL,
  `delete_id` bigint NULL DEFAULT NULL,
  `delete_time` datetime(6) NULL DEFAULT NULL,
  `update_id` bigint NULL DEFAULT NULL,
  `update_time` datetime(6) NULL DEFAULT NULL,
  `permission_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `permission_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of eyi_permission
-- ----------------------------

-- ----------------------------
-- Table structure for eyi_role
-- ----------------------------
DROP TABLE IF EXISTS `eyi_role`;
CREATE TABLE `eyi_role`  (
  `id` bigint NOT NULL,
  `create_id` bigint NULL DEFAULT NULL,
  `create_time` datetime(6) NULL DEFAULT NULL,
  `del_flag` int NULL DEFAULT NULL,
  `delete_id` bigint NULL DEFAULT NULL,
  `delete_time` datetime(6) NULL DEFAULT NULL,
  `update_id` bigint NULL DEFAULT NULL,
  `update_time` datetime(6) NULL DEFAULT NULL,
  `role_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `role_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of eyi_role
-- ----------------------------

-- ----------------------------
-- Table structure for eyi_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `eyi_role_permission`;
CREATE TABLE `eyi_role_permission`  (
  `role_id` bigint NOT NULL,
  `permission_id` bigint NOT NULL,
  `id` bigint NOT NULL,
  `create_id` bigint NULL DEFAULT NULL,
  `create_time` datetime(6) NULL DEFAULT NULL,
  `del_flag` int NULL DEFAULT NULL,
  `delete_id` bigint NULL DEFAULT NULL,
  `delete_time` datetime(6) NULL DEFAULT NULL,
  `update_id` bigint NULL DEFAULT NULL,
  `update_time` datetime(6) NULL DEFAULT NULL,
  UNIQUE INDEX `UK_h070oy2i78uksrjidma5do64g`(`permission_id`) USING BTREE,
  INDEX `FK2ai1vkeic9wvs86pp36b3p5et`(`role_id`) USING BTREE,
  CONSTRAINT `FK2ai1vkeic9wvs86pp36b3p5et` FOREIGN KEY (`role_id`) REFERENCES `eyi_role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKjrk2mj8lv6ayyu5sy88w27pik` FOREIGN KEY (`permission_id`) REFERENCES `eyi_permission` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of eyi_role_permission
-- ----------------------------

-- ----------------------------
-- Table structure for eyi_user
-- ----------------------------
DROP TABLE IF EXISTS `eyi_user`;
CREATE TABLE `eyi_user`  (
  `id` bigint NOT NULL,
  `create_id` bigint NULL DEFAULT NULL,
  `create_time` datetime(6) NULL DEFAULT NULL,
  `del_flag` int NULL DEFAULT NULL,
  `delete_id` bigint NULL DEFAULT NULL,
  `delete_time` datetime(6) NULL DEFAULT NULL,
  `update_id` bigint NULL DEFAULT NULL,
  `update_time` datetime(6) NULL DEFAULT NULL,
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `expire` bit(1) NULL DEFAULT NULL,
  `gender` int NULL DEFAULT NULL,
  `introduce` varchar(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `locked` bit(1) NULL DEFAULT NULL,
  `password` varchar(80) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `username` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `birthday` date NULL DEFAULT NULL,
  `nickname` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `password_strength` int NULL DEFAULT NULL,
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of eyi_user
-- ----------------------------
INSERT INTO `eyi_user` VALUES (1, 1, NULL, 0, NULL, NULL, 1, '2022-06-28 10:33:36.702778', '2471077872@qq.com', b'0', 1, '无31231', b'0', '$2a$10$bFG1Xc.vmLo4kde.9dGHN.XSHwVMjws5.ueAKT0EyzBDvDIYEJlp6', 'admin', '2001-11-11', '管理员', 2, 'http://localhost:19099/eyi/202206281033163030.png');

-- ----------------------------
-- Table structure for eyi_user_additional_info
-- ----------------------------
DROP TABLE IF EXISTS `eyi_user_additional_info`;
CREATE TABLE `eyi_user_additional_info`  (
  `id` bigint NOT NULL,
  `password_strength` int NULL DEFAULT NULL,
  `phone` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `question` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `answer` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `email_addr` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `del_flag` int NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  `delete_time` datetime NULL DEFAULT NULL,
  `create_id` bigint NULL DEFAULT NULL,
  `update_id` bigint NULL DEFAULT NULL,
  `delete_id` bigint NULL DEFAULT NULL,
  `status` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `eyi_user_additional_info_id_uindex`(`id`) USING BTREE,
  CONSTRAINT `eyi_user_additional_info_user_id_fk` FOREIGN KEY (`id`) REFERENCES `eyi_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of eyi_user_additional_info
-- ----------------------------
INSERT INTO `eyi_user_additional_info` VALUES (1, 2, '123-1111-2222', '测试？', '1231', '1254226073@qq.com', 0, '2022-06-28 10:48:36', '2022-06-29 13:48:03', NULL, NULL, NULL, NULL, 1);

-- ----------------------------
-- Table structure for eyi_user_role
-- ----------------------------
DROP TABLE IF EXISTS `eyi_user_role`;
CREATE TABLE `eyi_user_role`  (
  `user_id` bigint NOT NULL,
  `role_id` bigint NOT NULL,
  `id` bigint NOT NULL,
  `create_id` bigint NULL DEFAULT NULL,
  `create_time` datetime(6) NULL DEFAULT NULL,
  `del_flag` int NULL DEFAULT NULL,
  `delete_id` bigint NULL DEFAULT NULL,
  `delete_time` datetime(6) NULL DEFAULT NULL,
  `update_id` bigint NULL DEFAULT NULL,
  `update_time` datetime(6) NULL DEFAULT NULL,
  UNIQUE INDEX `UK_5f35yf7as6cg8tob3tbe21ndx`(`role_id`) USING BTREE,
  INDEX `FKnna3u9u8jss0cx7jnbiy4thxg`(`user_id`) USING BTREE,
  CONSTRAINT `FK11s8x0e467ryr68wmntafswq4` FOREIGN KEY (`role_id`) REFERENCES `eyi_role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKnna3u9u8jss0cx7jnbiy4thxg` FOREIGN KEY (`user_id`) REFERENCES `eyi_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of eyi_user_role
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
