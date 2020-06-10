INSERT INTO `checklist` (`id_log_day`, `log_day`, `crew_member`) VALUES
   (1, '2020-05-29', 'mzi'),
   (2, '2020-05-30', 'dpe'),
   (3, '2020-05-31', 'kpe');


INSERT INTO `checklist_element` (`id_checklist_element`, `checklist_id`, `helo_a`,`helo_b`,`helo_c`, `element_name`, `quantity`, `element_group`, `day_check`, `night_check`, `comment`) VALUES
   (1, 1, 0, 0, 0, 'Kannatanu prillid', 2, 'SAR aedik', 1, 1, NULL),
   (2, 2, 0, 0, 0, 'Kannatanu prillid', 2, 'SAR aedik', 1, 1, NULL),
   (3, 1, 0, 0, 0, 'Maandus', 1, 'SAR aedik', 1, 0, 'some comment'),
   (4, 1, 1, 0, 0, 'Õhuballoon', 80, 'SAR aedik', 1, 1, NULL);


