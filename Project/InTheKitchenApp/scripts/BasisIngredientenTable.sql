DROP TABLE basis_ingredienten;
CREATE TABLE basis_ingredienten (
  id int NOT NULL GENERATED ALWAYS AS IDENTITY,
  naam varchar(45) NOT NULL,
  foto_naam varchar(45) DEFAULT NULL,
  beschrijving varchar(500) DEFAULT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY id_UNIQUE (id),
  UNIQUE KEY naam_UNIQUE (naam)
) 