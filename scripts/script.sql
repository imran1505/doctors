CREATE TABLE `doctors` (
  `username` varchar(30) NOT NULL DEFAULT '',
  `password` varchar(30) DEFAULT '',
  `fname` varchar(30) DEFAULT '',
  `lname` varchar(30) DEFAULT '',
  `address` varchar(100) DEFAULT '',
  `city` varchar(20) DEFAULT '',
  `pincode` varchar(10) DEFAULT NULL,
  `state` varchar(20) DEFAULT NULL,
  `registrationdocname` varchar(30) DEFAULT '',
  `status` varchar(20) DEFAULT '',
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `patients` (
  `username` varchar(50) NOT NULL DEFAULT '',
  `password` varchar(50) DEFAULT NULL,
  `fname` varchar(50) DEFAULT NULL,
  `lname` varchar(50) DEFAULT NULL,
  `verificationcode` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;