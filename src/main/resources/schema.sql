DROP TABLE IF EXISTS `checklist_element`;
DROP TABLE IF EXISTS `checklist`;
CREATE TABLE IF NOT EXISTS `checklist` (
  `id_log_day` int(11)  NULL AUTO_INCREMENT,
  `log_day` date NOT NULL DEFAULT curdate(),
  `crew_member` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id_log_day`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
CREATE TABLE IF NOT EXISTS `checklist_element` (
  `id_checklist_element` int(11) NOT NULL AUTO_INCREMENT,
  `checklist_id` int(11) NOT NULL,
  `element_name` varchar(50) COLLATE utf8mb4_unicode_ci NULL,
  `quantity` int(11) NOT NULL,
  `element_group` varchar(50) COLLATE utf8mb4_unicode_ci NULL,
  `pressure` varchar(50) COLLATE utf8mb4_unicode_ci NULL,
  `day_check` tinyint(4) NOT NULL,
  `night_check` tinyint(4) NOT NULL,
  `comment` varchar(50) COLLATE utf8mb4_unicode_ci NULL,
  PRIMARY KEY (`id_checklist_element`),
  KEY `FK_checklist_element_checklist` (`checklist_id`),
  CONSTRAINT `FK_checklist_element_checklist` FOREIGN KEY (`checklist_id`) REFERENCES `checklist` (`id_log_day`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC;