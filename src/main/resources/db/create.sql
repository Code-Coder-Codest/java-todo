SET MODE PostgreSQL;

CREATE TABLE IF NOT EXISTS tasks (
  id int PRIMARY KEY auto_increment,
  description VARCHAR,
  name VARCHAR,
  completes BOOLEAN,
  categoryid INTEGER
);

CREATE TABLE IF NOT EXISTS categories (
  id int PRIMARY KEY auto_increment,
  name VARCHAR
);