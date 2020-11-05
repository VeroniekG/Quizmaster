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
    `idCoordinator` INT NULL,
    PRIMARY KEY (`idCourse`),
    INDEX `idCoordinator_idx` (`idCoordinator` ASC) VISIBLE,
    CONSTRAINT `fk_idCoordinator`
        FOREIGN KEY (`idCoordinator`)
            REFERENCES `quizmaster`.`user` (`idUser`)
            ON DELETE SET NULL
            ON UPDATE CASCADE
)
    ENGINE = InnoDB
    AUTO_INCREMENT = 13
    DEFAULT CHARACTER SET = utf8;

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
    PRIMARY KEY (`idUser`, `idCourse`),
    INDEX `fk_Course_User` (`idUser` ASC) VISIBLE,
    INDEX `fk_User_Course` (`idCourse` ASC) VISIBLE,
    CONSTRAINT `fk_Course_User`
        FOREIGN KEY (`idUser`)
            REFERENCES `quizmaster`.`user` (`idUser`)
            ON DELETE CASCADE
            ON UPDATE CASCADE,
    CONSTRAINT `fk_User_Course`
        FOREIGN KEY (`idCourse`)
            REFERENCES `quizmaster`.`course` (`idCourse`)
            ON DELETE CASCADE
            ON UPDATE CASCADE
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8;

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
    idQuiz       INT          NOT NULL,
    PRIMARY KEY (idQuestion),
    INDEX verzinzelf1_idx (idQuiz ASC) VISIBLE,
    CONSTRAINT verzinzelf1
        FOREIGN KEY (idQuiz)
            REFERENCES Quizmaster.Quiz (idQuiz)
            ON DELETE CASCADE
            ON UPDATE CASCADE
)
    ENGINE = InnoDB
    AUTO_INCREMENT = 8
    DEFAULT CHARACTER SET = utf8;

-- -----------------------------------------------------
-- Data for table `Quizmaster`.`Question`
-- -----------------------------------------------------
START TRANSACTION;
USE `Quizmaster`;

INSERT INTO Question (idQuestion, description, answerRight, answerWrong1, answerWrong2, answerWrong3, idQuiz)
VALUES (1, 'Wat is 1+1?', '2', '15', '166', '345', 1);
INSERT INTO Question (idQuestion, description, answerRight, answerWrong1, answerWrong2, answerWrong3, idQuiz)
VALUES (2, 'Wat is 2+2?', '4', '6', '12', '64', 2);
INSERT INTO Question (idQuestion, description, answerRight, answerWrong1, answerWrong2, answerWrong3 , idQuiz)
VALUES (3, 'Wat is 3+3?', '6', '345', '533', '353', 3);
INSERT INTO Question (idQuestion, description, answerRight, answerWrong1, answerWrong2, answerWrong3, idQuiz)
VALUES (4, 'Wat is 10x5?', '50', '242', '43', '44', 4);
INSERT INTO Question (idQuestion, description, answerRight, answerWrong1, answerWrong2, answerWrong3, idQuiz)
VALUES (5, 'Wat is 60/2?', '30 ', '545', '646', '15', 5);
INSERT INTO Question (idQuestion, description, answerRight, answerWrong1, answerWrong2, answerWrong3, idQuiz)
VALUES (6, 'Wat is 8x8?', '64', '234', '242', '667', 6);
INSERT INTO Question (idQuestion, description, answerRight, answerWrong1, answerWrong2, answerWrong3, idQuiz)
VALUES (7, 'Wat is 19-5?', '14', '5345', '75', '7', 7);
INSERT INTO Question (idQuestion, description, answerRight, answerWrong1, answerWrong2, answerWrong3, idQuiz)
VALUES (8, 'Wat is 19+5?', '24', '42', '2', '5', 8);
INSERT INTO Question (idQuestion, description, answerRight, answerWrong1, answerWrong2, answerWrong3, idQuiz)
VALUES (9, 'Wat is 19-7?', '12', '6', '575', '27', 9);
INSERT INTO Question (idQuestion, description, answerRight, answerWrong1, answerWrong2, answerWrong3, idQuiz)
VALUES (10, 'Wat is 10x4??', '40', '63', '759', '77', 10);
INSERT INTO Question (idQuestion, description, answerRight, answerWrong1, answerWrong2, answerWrong3, idQuiz)
VALUES (11, 'Wat is 5+2?', '7', '54545', '755', '327', 11);

COMMIT;

-- -----------------------------------------------------
-- Table `Quizmaster`.`Quiz` ---------------------------
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Quizmaster`.`Quiz`;

CREATE TABLE IF NOT EXISTS `Quizmaster`.`Quiz`
(
    `idQuiz`   INT         NOT NULL AUTO_INCREMENT,
    `quizName` VARCHAR(25) NOT NULL,
    `idCourse` INT         NOT NULL,
    PRIMARY KEY (`idQuiz`),
    INDEX `fk_Quizzes_Course1_idx` (`idCourse` ASC) VISIBLE,
    CONSTRAINT `fk_Quizzes_Course1`
        FOREIGN KEY (`idCourse`)
            REFERENCES `Quizmaster`.`Course` (`idCourse`)
            ON DELETE CASCADE
            ON UPDATE CASCADE
)
    ENGINE = InnoDB
    AUTO_INCREMENT = 1
    DEFAULT CHARACTER SET = utf8;

-- -----------------------------------------------------
-- Data for table `Quizmaster`.`Quiz` -------------
-- -----------------------------------------------------
INSERT INTO `quizmaster`.`quiz` (`idQuiz`, `idCourse`, `quizName`) VALUES (1, 1, 'Wie van de Drie');
INSERT INTO `quizmaster`.`quiz` (`idQuiz`, `idCourse`, `quizName`) VALUES (2, 2, 'Weet Ik Veel');
INSERT INTO `quizmaster`.`quiz` (`idQuiz`, `idCourse`, `quizName`) VALUES (3, 2, 'Blokken');
INSERT INTO `quizmaster`.`quiz` (`idQuiz`, `idCourse`, `quizName`) VALUES (4, 4, 'Per Seconde Wijzer');
INSERT INTO `quizmaster`.`quiz` (`idQuiz`, `idCourse`, `quizName`) VALUES (5, 1, 'Met het Mes op Tafel');
INSERT INTO `quizmaster`.`quiz` (`idQuiz`, `idCourse`, `quizName`) VALUES (6, 6, 'Noem nog eens een kwis');
INSERT INTO `quizmaster`.`quiz` (`idQuiz`, `idCourse`, `quizName`) VALUES (7, 4, 'Wie ben ik?');
INSERT INTO `quizmaster`.`quiz` (`idQuiz`, `idCourse`, `quizName`) VALUES (8, 8, 'De slimste mens');
INSERT INTO `quizmaster`.`quiz` (`idQuiz`, `idCourse`, `quizName`) VALUES (9, 8, 'Bankgiro Miljonairs');
INSERT INTO `quizmaster`.`quiz` (`idQuiz`, `idCourse`, `quizName`) VALUES (10, 5, 'Waku Waku');
INSERT INTO `quizmaster`.`quiz` (`idQuiz`, `idCourse`, `quizName`) VALUES (11, 5, '2 voor 12');
INSERT INTO `quizmaster`.`quiz` (`idQuiz`, `idCourse`, `quizName`) VALUES (12, 3, 'Postcodeloterij');
INSERT INTO `quizmaster`.`quiz` (`idQuiz`, `idCourse`, `quizName`) VALUES (13, 3, 'Alles of niets');
INSERT INTO `quizmaster`.`quiz` (`idQuiz`, `idCourse`, `quizName`) VALUES (14, 7, 'Lingo');
INSERT INTO `quizmaster`.`quiz` (`idQuiz`, `idCourse`, `quizName`) VALUES (15, 7, 'Top Pop');

-- -----------------------------------------------------
-- Table `Quizmaster`.`Quizresult` ---------------------
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Quizmaster`.`QuizResult`;

CREATE TABLE IF NOT EXISTS `Quizmaster`.`QuizResult`
(
    `idQuizResult`      INT          NOT NULL AUTO_INCREMENT,
    `User_idUser`       INT          NOT NULL,
    `Quizzes_idQuizzes` INT          NOT NULL,
    `feedback`          VARCHAR(100) NOT NULL,
    `dateTime`          DATETIME     NOT NULL,
    `timesCompleted`    VARCHAR(45)  NOT NULL,
    PRIMARY KEY (`idQuizResult`),
    INDEX `fk_QuizResult_User1_idx` (`User_idUser` ASC) VISIBLE,
    INDEX `fk_QuizResult_Quizzes1_idx` (`Quizzes_idQuizzes` ASC) VISIBLE,
    CONSTRAINT `fk_QuizResult_Quizzes1`
        FOREIGN KEY (`Quizzes_idQuizzes`)
            REFERENCES `Quizmaster`.`Quiz` (`idQuiz`)
            ON DELETE CASCADE
            ON UPDATE CASCADE,
    CONSTRAINT `fk_QuizResult_User1`
        FOREIGN KEY (`User_idUser`)
            REFERENCES `Quizmaster`.`User` (`idUser`)
            ON DELETE CASCADE
            ON UPDATE CASCADE
)
    ENGINE = InnoDB
    AUTO_INCREMENT = 1
    DEFAULT CHARACTER SET = utf8;

-- -----------------------------------------------------
-- Data for table `Quizmaster`.`Quizresult` ------- TODO
-- -----------------------------------------------------


-- -----------------------------------------------------
-- Table `Quizmaster`.`StudentGroup` -------------- TODO
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Quizmaster`.`StudentGroup`;

CREATE TABLE IF NOT EXISTS `Quizmaster`.`StudentGroup`
(
    `idStudentGroup`   INT         NOT NULL AUTO_INCREMENT,
    `StudentGroupName` VARCHAR(20) NOT NULL,
    `idCourse`         INT         NOT NULL,
    PRIMARY KEY (`idStudentGroup`),
    INDEX `fk_Group_Course_idx` (`idCourse` ASC) VISIBLE,
    CONSTRAINT `fk_Group_Course`
        FOREIGN KEY (`idCourse`)
            REFERENCES `Quizmaster`.`Course` (`idCourse`)
            ON DELETE CASCADE
            ON UPDATE CASCADE
)
    ENGINE = InnoDB
    AUTO_INCREMENT = 2
    DEFAULT CHARACTER SET = utf8;

-- -----------------------------------------------------
-- Data for table `Quizmaster`.`StudentGroup` ----------
-- -----------------------------------------------------
START TRANSACTION;
USE `Quizmaster`;

INSERT INTO `Quizmaster`.`StudentGroup` (`idStudentGroup`, `StudentGroupName`, `idCourse`)
VALUES (1, 'Groep 1', 1);
INSERT INTO `Quizmaster`.`StudentGroup` (`idStudentGroup`, `StudentGroupName`, `idCourse`)
VALUES (2, 'Groep 2', 1);
INSERT INTO `Quizmaster`.`StudentGroup` (`idStudentGroup`, `StudentGroupName`, `idCourse`)
VALUES (3, 'Groep 3', 1);
INSERT INTO `Quizmaster`.`StudentGroup` (`idStudentGroup`, `StudentGroupName`, `idCourse`)
VALUES (4, 'Groep 4', 1);

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
VALUES ('3', 'User3', 'pwUser3', 'COORDINATOR', 'Co', 'Ordinator0');
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