CREATE 
    ALGORITHM = UNDEFINED 
    DEFINER = `root`@`localhost` 
    SQL SECURITY DEFINER
VIEW `sapir`.`view_avg` AS
    SELECT 
        `sapir`.`highschool`.`Grade_avg` AS `Grade_avg`,
        `sapir`.`highschool`.`Id` AS `Id`
    FROM
        `sapir`.`highschool`