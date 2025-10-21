-- MySQL Database Setup Script
-- Run this script in MySQL to create the cms database

-- Create the cms database if it doesn't exist
CREATE DATABASE IF NOT EXISTS cms 
CHARACTER SET utf8mb4 
COLLATE utf8mb4_unicode_ci;

-- Use the cms database
USE cms;

-- Grant permissions (adjust username as needed)
-- GRANT ALL PRIVILEGES ON cms.* TO 'root'@'localhost';
-- FLUSH PRIVILEGES;

-- Show current database
SELECT DATABASE() as current_database;

-- Show tables (should be empty initially, Spring Boot will create them)
SHOW TABLES;