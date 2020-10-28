ALTER TABLE `Quizmaster`.`User`
    ADD COLUMN `role`      VARCHAR(20) NOT NULL AFTER `password`,
    ADD COLUMN `firstName` VARCHAR(15) NOT NULL AFTER `role`,
    ADD COLUMN `lastName`  VARCHAR(25) NOT NULL AFTER `firstName`;

UPDATE `Quizmaster`.`User`
SET `password` = 'pwUser1',
    `role`     = 'STUDENT'
WHERE (`idUser` = '1');
UPDATE `Quizmaster`.`User`
SET `password` = 'pwUser2',
    `role`     = 'DOCENT'
WHERE (`idUser` = '2');
UPDATE `Quizmaster`.`User`
SET `password` = 'pwUser3',
    `role`     = 'COORDINATOR'
WHERE (`idUser` = '3');
UPDATE `Quizmaster`.`User`
SET `password` = 'pwUser4',
    `role`     = 'ADMINISTRATOR'
WHERE (`idUser` = '4');
UPDATE `Quizmaster`.`User`
SET `password` = 'pwUser5',
    `role`     = 'TECHNISCH_BEHEERDER'
WHERE (`idUser` = '5'),
UPDATE `Quizmaster`.`User`
SET `firstName` = 'User',
    `lastName`  = '1'
WHERE (`idUser` = '1');
UPDATE `Quizmaster`.`User`
SET `firstName` = 'User',
    `lastName`  = '2'
WHERE (`idUser` = '2');
UPDATE `Quizmaster`.`User`
SET `firstName` = 'User',
    `lastName`  = '3'
WHERE (`idUser` = '3');
UPDATE `Quizmaster`.`User`
SET `firstName` = 'User',
    `lastName`  = '4'
WHERE (`idUser` = '4');
UPDATE `Quizmaster`.`User`
SET `firstName` = 'User',
    `lastName`  = '5'
WHERE (`idUser` = '5');
UPDATE `Quizmaster`.`User`
SET `firstName` = 'Daan',
    `lastName`  = 'Leertouwer'
WHERE (`idUser` = '6');

