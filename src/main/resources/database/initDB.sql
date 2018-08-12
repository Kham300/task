DROP TABLE IF EXISTS tasks;
DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE global_seq START 1000;

CREATE TABLE tasks
(
  id          INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  GUID        VARCHAR NOT NULL,
  status      VARCHAR NOT NULL,
  registered  TIMESTAMP DEFAULT now() NOT NULL
);