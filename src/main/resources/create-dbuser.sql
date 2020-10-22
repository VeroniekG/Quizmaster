-- -----------------------------------------------------
-- User for Schema Quizmaster
-- -----------------------------------------------------
CREATE USER IF NOT EXISTS 'userQuizmaster'@'localhost'
IDENTIFIED WITH caching_sha2_password BY 'pwQuizmaster'
PASSWORD EXPIRE NEVER;
GRANT SELECT ON Quizmaster.* TO 'userQuizmaster'@'localhost';
GRANT INSERT ON Quizmaster.* TO 'userQuizmaster'@'localhost';
GRANT DELETE ON Quizmaster.* TO 'userQuizmaster'@'localhost';
GRANT UPDATE ON Quizmaster.* TO 'userQuizmaster'@'localhost';
