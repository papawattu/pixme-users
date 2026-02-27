CREATE TABLE IF NOT EXISTS users (
  id varchar(36) NOT NULL,
  name varchar(100) NOT NULL,
  email varchar(100) NOT NULL,
  role varchar(20) NOT NULL DEFAULT 'user',
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id),
  CONSTRAINT users_email_unique UNIQUE (email)
);

-- Add role column to existing tables that predate this migration
ALTER TABLE users ADD COLUMN IF NOT EXISTS role VARCHAR(20) NOT NULL DEFAULT 'user';
