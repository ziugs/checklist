
DROP TABLE IF EXISTS `checklist_element`;
DROP TABLE IF EXISTS `checklist`;

CREATE TABLE IF NOT EXISTS `checklist` (
  `id_log_day` int(11)  NULL AUTO_INCREMENT,
  `log_day` date NOT NULL DEFAULT curdate(),
  `crew_member` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id_log_day`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


CREATE TABLE IF NOT EXISTS `checklist_element` (
	`id_checklist_element` INT(11) NOT NULL AUTO_INCREMENT,
	`checklist_id` INT(11) NULL DEFAULT NULL,
	`helo_a` TINYINT(4) NULL,
	`helo_b` TINYINT(4) NULL,
	`helo_c` TINYINT(4) NULL,
	`element_name` VARCHAR(500) NULL DEFAULT NULL COLLATE 'utf8mb4_unicode_ci',
	`quantity` INT(11) NOT NULL,
	`element_group` VARCHAR(500) NULL DEFAULT NULL COLLATE 'utf8mb4_unicode_ci',
	`pressure` VARCHAR(50) NULL DEFAULT NULL COLLATE 'utf8mb4_unicode_ci',
	`day_check` TINYINT(4) NOT NULL,
	`night_check` TINYINT(4) NOT NULL,
	`comment` VARCHAR(50) NULL DEFAULT NULL COLLATE 'utf8mb4_unicode_ci',
	PRIMARY KEY (`id_checklist_element`),
	INDEX `FK_checklist_element_checklist` (`checklist_id`),
	CONSTRAINT `FK_checklist_element_checklist` FOREIGN KEY (`checklist_id`) REFERENCES `checklist` (`id_log_day`)
)
COLLATE='utf8mb4_unicode_ci'
ENGINE=InnoDB
ROW_FORMAT=DYNAMIC
AUTO_INCREMENT=5
;