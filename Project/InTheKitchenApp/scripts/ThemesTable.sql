CREATE TABLE themes (
  id INT NOT NULL GENERATED ALWAYS AS IDENTITY,
  theme_type VARCHAR(45) DEFAULT NULL,
  PRIMARY KEY (id)
)