ALTER TABLE `Quizmaster`.`User`
    ADD COLUMN `role` VARCHAR(20) NOT NULL AFTER `password`;
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
WHERE (`idUser` = '5');
