CREATE TABLE taglijst (
  id int NOT NULL GENERATED ALWAYS AS IDENTITY,
  tagnaam varchar(45) DEFAULT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY id_UNIQUE (id)
)