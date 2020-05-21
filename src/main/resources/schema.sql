CREATE TABLE Users
(
  id       int          NOT NULL auto_increment,
  name     VARCHAR(128) NOT NULL,
  email    VARCHAR(128) NOT NULL,
  address  VARCHAR(128) NOT NULL,
  password VARCHAR(128) NOT NULL,
  phone    INTEGER,
  PRIMARY KEY (id)
);