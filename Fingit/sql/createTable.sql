delimiter $$

CREATE TABLE `ranking` (
  `hash` varchar(500) NOT NULL,
  `puntos` int(11) NOT NULL,
  `fecha` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`hash`,`fecha`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1$$