/*!40100 DEFAULT CHARACTER SET latin1 */;

CREATE TABLE if not exists `transformer` (
  `id` int(3) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `team` varchar(100) NOT NULL,
  `strength` int(2) unsigned NOT NULL,
  `intelligence` int(2) unsigned NOT NULL,
  `speed` int(2) unsigned NOT NULL,
  `endurance` int(2) unsigned NOT NULL,
  `rank` int(2) unsigned NOT NULL,
  `courage` int(2) unsigned NOT NULL,
  `firepower` int(2) unsigned NOT NULL,
  `skill` int(2) unsigned NOT NULL,
  PRIMARY KEY (`id`)
) ;

