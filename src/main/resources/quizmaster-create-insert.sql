-- -----------------------------------------------------
-- Schema Quizmaster
-- -----------------------------------------------------

SET @OLD_UNIQUE_CHECKS = @@UNIQUE_CHECKS, UNIQUE_CHECKS = 0;
SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0;
SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE =
        'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema Quizmaster
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `Quizmaster`;

-- -----------------------------------------------------
-- Schema Quizmaster
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `Quizmaster` DEFAULT CHARACTER SET utf8;
USE `Quizmaster`;

-- -----------------------------------------------------
-- Database user for Schema Quizmaster
-- -----------------------------------------------------
CREATE USER IF NOT EXISTS 'userQuizmaster'@'localhost'
    IDENTIFIED WITH caching_sha2_password
        BY 'pwQuizmaster' PASSWORD EXPIRE NEVER;
GRANT SELECT ON Quizmaster.* TO 'userQuizmaster'@'localhost';
GRANT INSERT ON Quizmaster.* TO 'userQuizmaster'@'localhost';
GRANT DELETE ON Quizmaster.* TO 'userQuizmaster'@'localhost';
GRANT UPDATE ON Quizmaster.* TO 'userQuizmaster'@'localhost';

-- -----------------------------------------------------
-- Table `Quizmaster`.`Course`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Quizmaster`.`Course`;

CREATE TABLE IF NOT EXISTS `Quizmaster`.`Course`
(
    `idCourse`   INT         NOT NULL AUTO_INCREMENT,
    `courseName` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`idCourse`)

)
    ENGINE = InnoDB
    AUTO_INCREMENT = 13
    DEFAULT CHARACTER SET = utf8;

ALTER TABLE `quizmaster`.`course`
    ADD COLUMN `idCoordinator` INT NOT NULL AFTER `idCourse`,
    ADD INDEX `idCoordinator_idx` (`idCoordinator` ASC) VISIBLE;
;
ALTER TABLE `quizmaster`.`course`
    ADD CONSTRAINT `idCoordinator`
        FOREIGN KEY (`idCoordinator`)
            REFERENCES `quizmaster`.`user` (`idUser`)
            ON DELETE NO ACTION
            ON UPDATE CASCADE;

-- -----------------------------------------------------
-- Data for table `Quizmaster`.`Course`
-- -----------------------------------------------------
START TRANSACTION;
USE `Quizmaster`;

INSERT INTO Course (idCourse, courseName, idCoordinator)
VALUES (1, 'SkillUp', 3);
INSERT INTO Course (idCourse, courseName, idCoordinator)
VALUES (2, 'No-Bull Bootcamp', 3);
INSERT INTO Course (idCourse, courseName, idCoordinator)
VALUES (3, 'Mentee to Mentor', 3);
INSERT INTO Course (idCourse, courseName, idCoordinator)
VALUES (4, 'Active Achievement', 3);
INSERT INTO Course (idCourse, courseName, idCoordinator)
VALUES (5, 'Practice to Perfect', 3);
INSERT INTO Course (idCourse, courseName, idCoordinator)
VALUES (6, 'Strive Training', 3);
INSERT INTO Course (idCourse, courseName, idCoordinator)
VALUES (7, 'Commission Kings', 3);
INSERT INTO Course (idCourse, courseName, idCoordinator)
VALUES (8, 'Productivity Today', 3);
INSERT INTO Course (idCourse, courseName, idCoordinator)
VALUES (9, 'Unbound Opportunities', 3);
INSERT INTO Course (idCourse, courseName, idCoordinator)
VALUES (10, 'Passion Chasers', 3);
INSERT INTO Course (idCourse, courseName, idCoordinator)
VALUES (11, 'Limitless Horizons', 3);
INSERT INTO Course (idCourse, courseName, idCoordinator)
VALUES (12, 'Excalibur Training', 3);

COMMIT;

-- -----------------------------------------------------
-- Table `Quizmaster`.`Course_User`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Quizmaster`.`Course_User`;

CREATE TABLE IF NOT EXISTS `Quizmaster`.`Course_User`
(
    `idUser`   INT NOT NULL,
    `idCourse` INT NOT NULL,
    INDEX `fk_Course_User` (`idUser` ASC) VISIBLE,
    INDEX `fk_User_Course` (`idCourse` ASC) VISIBLE,
    CONSTRAINT `fk_Course_User`
        FOREIGN KEY (`idUser`)
            REFERENCES `quizmaster`.`user` (`idUser`),
    CONSTRAINT `fk_User_Course`
        FOREIGN KEY (`idCourse`)
            REFERENCES `quizmaster`.`course` (`idCourse`)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8;

ALTER TABLE `Quizmaster`.`Course_User`
    ADD PRIMARY KEY (`idUser`, `idCourse`);
;

-- -----------------------------------------------------
-- Data for table `Quizmaster`.`Course_User`
-- -----------------------------------------------------
INSERT INTO `Quizmaster`.`Course_User` (`idUser`, `idCourse`)
VALUES ('7', '1');
INSERT INTO `Quizmaster`.`Course_User` (`idUser`, `idCourse`)
VALUES ('7', '3');
INSERT INTO `Quizmaster`.`Course_User` (`idUser`, `idCourse`)
VALUES ('7', '5');
INSERT INTO `Quizmaster`.`Course_User` (`idUser`, `idCourse`)
VALUES ('7', '7');
INSERT INTO `Quizmaster`.`Course_User` (`idUser`, `idCourse`)
VALUES ('7', '9');
INSERT INTO `Quizmaster`.`Course_User` (`idUser`, `idCourse`)
VALUES ('7', '11');
INSERT INTO `Quizmaster`.`Course_User` (`idUser`, `idCourse`)
VALUES ('8', '2');
INSERT INTO `Quizmaster`.`Course_User` (`idUser`, `idCourse`)
VALUES ('8', '4');
INSERT INTO `Quizmaster`.`Course_User` (`idUser`, `idCourse`)
VALUES ('8', '6');
INSERT INTO `Quizmaster`.`Course_User` (`idUser`, `idCourse`)
VALUES ('8', '8');
INSERT INTO `Quizmaster`.`Course_User` (`idUser`, `idCourse`)
VALUES ('8', '10');
INSERT INTO `Quizmaster`.`Course_User` (`idUser`, `idCourse`)
VALUES ('8', '12');
INSERT INTO `Quizmaster`.`Course_User` (`idUser`, `idCourse`)
VALUES ('9', '2');
INSERT INTO `Quizmaster`.`Course_User` (`idUser`, `idCourse`)
VALUES ('9', '3');
INSERT INTO `Quizmaster`.`Course_User` (`idUser`, `idCourse`)
VALUES ('9', '7');
INSERT INTO `Quizmaster`.`Course_User` (`idUser`, `idCourse`)
VALUES ('9', '8');
INSERT INTO `Quizmaster`.`Course_User` (`idUser`, `idCourse`)
VALUES ('9', '10');
INSERT INTO `Quizmaster`.`Course_User` (`idUser`, `idCourse`)
VALUES ('9', '11');

-- -----------------------------------------------------
-- Table `Quizmaster`.`Question`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Quizmaster`.`Question`;

CREATE TABLE IF NOT EXISTS `Quizmaster`.`Question`
(
    idQuestion   INT          NOT NULL AUTO_INCREMENT,
    description  VARCHAR(200) NOT NULL,
    answerRight  VARCHAR(45)  NOT NULL,
    answerWrong1 VARCHAR(45)  NOT NULL,
    answerWrong2 VARCHAR(45)  NOT NULL,
    answerWrong3 VARCHAR(45)  NOT NULL,
    PRIMARY KEY (`idQuestion`)
)
    ENGINE = InnoDB
    AUTO_INCREMENT = 8
    DEFAULT CHARACTER SET = utf8;

ALTER TABLE `Quizmaster`.`Question`
    ADD COLUMN `idQuiz` INT NULL AFTER `idQuestion`,
    ADD INDEX `idQuiz_idx` (`idQuiz` ASC) VISIBLE;
;
ALTER TABLE `Quizmaster`.`Question`
    ADD CONSTRAINT `idQuiz`
        FOREIGN KEY (`idQuiz`)
            REFERENCES `Quizmaster`.`Quiz` (`idQuiz`)
            ON DELETE NO ACTION
            ON UPDATE CASCADE;

-- -----------------------------------------------------
-- Data for table `Quizmaster`.`Question`
-- -----------------------------------------------------
START TRANSACTION;
USE `Quizmaster`;

INSERT INTO Question (idQuestion, description, answerRight, answerWrong1, answerWrong2, answerWrong3)
VALUES (1, 'Wat is 1+1?', '2', '15', '166', '345');
INSERT INTO Question (idQuestion, description, answerRight, answerWrong1, answerWrong2, answerWrong3)
VALUES (2, 'Wat is 2+2?', '4', 'werw', 'ewrw', 'ww');
INSERT INTO Question (idQuestion, description, answerRight, answerWrong1, answerWrong2, answerWrong3)
VALUES (3, 'Wat is 3+3?', '6', '345', '533', '353');
INSERT INTO Question (idQuestion, description, answerRight, answerWrong1, answerWrong2, answerWrong3)
VALUES (4, 'Wat is 10x5?', '50', 'Appel', '655', '23');
INSERT INTO Question (idQuestion, description, answerRight, answerWrong1, answerWrong2, answerWrong3)
VALUES (5, 'Wat is 60/2?', '30 ', '545', 'Sinterklaas', 'Boeien');
INSERT INTO Question (idQuestion, description, answerRight, answerWrong1, answerWrong2, answerWrong3)
VALUES (6, 'Wat is de hoofdstad van Nederland?', 'Amsterdam', 'Enschede', 'Rotterdam', 'Urk');
INSERT INTO Question (idQuestion, description, answerRight, answerWrong1, answerWrong2, answerWrong3)
VALUES (7, 'Hoe is het?', 'Oke', 'Goed', 'Slecht', 'Matig');

COMMIT;

-- -----------------------------------------------------
-- Table `Quizmaster`.`Quiz`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Quizmaster`.`Quiz`;

CREATE TABLE IF NOT EXISTS `Quizmaster`.`Quiz`
(
    `idQuiz`   INT         NOT NULL AUTO_INCREMENT,
    `quizName` VARCHAR(25) NOT NULL,
    PRIMARY KEY (`idQuiz`)
)
    ENGINE = InnoDB
    AUTO_INCREMENT = 1
    DEFAULT CHARACTER SET = utf8;

ALTER TABLE `Quizmaster`.`Quiz`
    ADD COLUMN `idCourse` INT AFTER `idQuiz`,
    ADD INDEX `idCourse_idx` (`idCourse` ASC) VISIBLE;
;
ALTER TABLE `Quizmaster`.`Quiz`
    ADD CONSTRAINT `idCourse`
        FOREIGN KEY (`idCourse`)
            REFERENCES `Quizmaster`.`Course` (`idCourse`)
            ON DELETE NO ACTION
            ON UPDATE CASCADE;
-- -----------------------------------------------------
-- Data for table `Quizmaster`.`Quiz`
-- -----------------------------------------------------
INSERT INTO `quizmaster`.`quiz` (`idQuiz`, `idCourse`, `quizName`) VALUES (1, 1, 'Wie van de Drie');
INSERT INTO `quizmaster`.`quiz` (`idQuiz`, `idCourse`, `quizName`) VALUES (2, 2, 'Weet Ik Veel');
INSERT INTO `quizmaster`.`quiz` (`idQuiz`, `idCourse`, `quizName`) VALUES (3, 3, 'Blokken');
INSERT INTO `quizmaster`.`quiz` (`idQuiz`, `idCourse`, `quizName`) VALUES (4, 4, 'Per Seconde Wijzer');
INSERT INTO `quizmaster`.`quiz` (`idQuiz`, `idCourse`, `quizName`) VALUES (5, 5, 'Met het Mes op Tafel');
INSERT INTO `quizmaster`.`quiz` (`idQuiz`, `idCourse`, `quizName`) VALUES (6, 6, 'Noem nog eens een kwis');
INSERT INTO `quizmaster`.`quiz` (`idQuiz`, `idCourse`, `quizName`) VALUES (7, 7, 'Wie ben ik?');
INSERT INTO `quizmaster`.`quiz` (`idQuiz`, `idCourse`, `quizName`) VALUES (8, 8, 'De slimste mens');

-- -----------------------------------------------------
-- Table `Quizmaster`.`StudentGroup` -------------- TODO
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Quizmaster`.`StudentGroup`;

CREATE TABLE IF NOT EXISTS `Quizmaster`.`StudentGroup`
(
    `idStudentGroup`   INT         NOT NULL AUTO_INCREMENT,
    `StudentGroupName` VARCHAR(20) NOT NULL,
    `idCourse`         INT         NULL,
    PRIMARY KEY (`idStudentGroup`),
    INDEX `fk_Group_Course_idx` (`idCourse` ASC) VISIBLE,
    CONSTRAINT `fk_Group_Course`
        FOREIGN KEY (`idCourse`)
            REFERENCES `Quizmaster`.`Course` (`idCourse`)
)
    ENGINE = InnoDB
    AUTO_INCREMENT = 2
    DEFAULT CHARACTER SET = utf8;

-- -----------------------------------------------------
-- Data for table `Quizmaster`.`StudentGroup` ----- TODO
-- -----------------------------------------------------
START TRANSACTION;
USE `Quizmaster`;

INSERT INTO `Quizmaster`.`StudentGroup` (`idStudentGroup`, `StudentGroupName`, `idCourse`)
VALUES (1, 'Group1', 1);

COMMIT;

-- -----------------------------------------------------
-- Table `Quizmaster`.`User`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Quizmaster`.`User`;

CREATE TABLE IF NOT EXISTS `Quizmaster`.`User`
(
    `idUser`    INT         NOT NULL AUTO_INCREMENT,
    `userName`  VARCHAR(20) NOT NULL,
    `password`  VARCHAR(20) NOT NULL,
    `role`      VARCHAR(20) NOT NULL,
    `firstName` VARCHAR(15) NOT NULL,
    `lastName`  VARCHAR(25) NOT NULL,
    PRIMARY KEY (`idUser`),
    UNIQUE INDEX `userName_UNIQUE` (`userName` ASC) VISIBLE
)
    ENGINE = InnoDB
    AUTO_INCREMENT = 7
    DEFAULT CHARACTER SET = utf8;
-- -----------------------------------------------------
-- Data for table `Quizmaster`.`User`
-- -----------------------------------------------------
START TRANSACTION;
USE `Quizmaster`;

INSERT INTO `Quizmaster`.`User` (`idUser`, `userName`, `password`, `role`, `firstName`, `lastName`)
VALUES ('1', 'User1', 'pwUser1', 'STUDENT', 'Piet', 'Kweetniet');
INSERT INTO `Quizmaster`.`User` (`idUser`, `userName`, `password`, `role`, `firstName`, `lastName`)
VALUES ('2', 'User2', 'pwUser2', 'DOCENT', 'Huub', 'Huub-Barbatruuk');
INSERT INTO `Quizmaster`.`User` (`idUser`, `userName`, `password`, `role`, `firstName`, `lastName`)
VALUES ('3', 'User3', 'pwUser3', 'COORDINATOR', 'Co', 'Ordinator');
INSERT INTO `Quizmaster`.`User` (`idUser`, `userName`, `password`, `role`, `firstName`, `lastName`)
VALUES ('4', 'User4', 'pwUser4', 'ADMINISTRATOR', 'Ad', 'Ministrator');
INSERT INTO `Quizmaster`.`User` (`idUser`, `userName`, `password`, `role`, `firstName`, `lastName`)
VALUES ('5', 'User5', 'pwUser5', 'TECHNISCH_BEHEERDER', 'Rinus', 'Radeloos');
INSERT INTO `Quizmaster`.`User` (`idUser`, `userName`, `password`, `role`, `firstName`, `lastName`)
VALUES ('6', 'Daan', 'pwDaan', 'TECHNISCH_BEHEERDER', 'Daan', 'Banaan');
INSERT INTO `Quizmaster`.`User` (`idUser`, `userName`, `password`, `role`, `firstName`, `lastName`)
VALUES ('7', 'Student1', 'pw1', 'STUDENT', 'Student1', 'Een');
INSERT INTO `Quizmaster`.`User` (`idUser`, `userName`, `password`, `role`, `firstName`, `lastName`)
VALUES ('8', 'Student2', 'pw2', 'STUDENT', 'Student2', 'Twee');
INSERT INTO `Quizmaster`.`User` (`idUser`, `userName`, `password`, `role`, `firstName`, `lastName`)
VALUES ('9', 'Student3', 'pw3', 'STUDENT', 'Student3', 'Drie');
INSERT INTO `Quizmaster`.`User` (`idUser`, `userName`, `password`, `role`, `firstName`, `lastName`)
VALUES ('10', 'Student4', 'pw4', 'STUDENT', 'Student4', 'Vier');


COMMIT;

SET SQL_MODE = @OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS;