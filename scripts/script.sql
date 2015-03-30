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
  `department` varchar(30) DEFAULT '',
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

CREATE TABLE `appointments` (
  `id` varchar(50) NOT NULL DEFAULT '0',
  `patientid` varchar(30) DEFAULT '',
  `doctorid` varchar(30) DEFAULT '',
  `appointmentdate` date DEFAULT '2000-01-01',
  `status` varchar(50) DEFAULT '0',
  `department` varchar(30) DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `recovery` (
  `uid` varchar(50) NOT NULL DEFAULT '',
  `username` varchar(50) DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL,
  `code` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;