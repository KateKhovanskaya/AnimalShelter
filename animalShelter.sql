CREATE TABLE animal_categories (id INT AUTO_INCREMENT PRIMARY KEY,
								category_name VARCHAR(45));
                                
INSERT INTO animal_categories (category_name)
VALUES
	('HomeAnimal'),
    ('PackAnimal');
    
CREATE TABLE animal_types (id INT AUTO_INCREMENT PRIMARY KEY,
								type_name VARCHAR(45));
                                
INSERT INTO animal_types (type_name)
VALUES
	('dog'),
    ('cat'),
    ('hamster'),
    ('horse'),
    ('camel'),
    ('donkey');

CREATE TABLE animals (id INT AUTO_INCREMENT PRIMARY KEY, 
					category_id INT,
                    animal_type_id INT,
					animal_name VARCHAR(45),
                    birthday DATE,
                    FOREIGN KEY (category_id) REFERENCES animal_categories(id),
                    FOREIGN KEY (animal_type_id) REFERENCES animal_types(id));
                    
INSERT INTO animals (category_id, animal_type_id, animal_name, birthday)
	VALUES
		(1, 1, 'Bobik', '2023-05-15'),
		(1, 1, 'Rex', '2022-10-12'),
        (1, 2, 'Vasya', '2023-07-02'),
		(1, 2, 'Barsik', '2023-09-11'),
		(1, 3, 'Homa', '2023-08-21'),
		(1, 3, 'Puhlya', '2022-11-11'),
		(2, 4, 'Winter', '2020-04-18'),
		(2, 4, 'Belka', '2021-06-28'),
		(2, 5, 'Gosha', '2022-12-06'),
		(2, 5, 'Robi', '2022-05-13'),
		(2, 6, 'Ia', '2023-02-12'),
		(2, 6, 'Kishi', '2019-08-08');
        
CREATE OR REPLACE VIEW dogs AS
	SELECT animals.id, animals.animal_name, animals.birthday
    FROM animals
    WHERE animal_type_id = 1;
    
CREATE OR REPLACE VIEW cats AS
	SELECT animals.id, animals.animal_name, animals.birthday
    FROM animals
    WHERE animal_type_id = 2;
    
CREATE OR REPLACE VIEW hamsters AS
	SELECT animals.id, animals.animal_name, animals.birthday
    FROM animals
    WHERE animal_type_id = 3;
    
CREATE OR REPLACE VIEW horses AS
	SELECT animals.id, animals.animal_name, animals.birthday
    FROM animals
    WHERE animal_type_id = 4;
    
CREATE OR REPLACE VIEW camels AS
	SELECT animals.id, animals.animal_name, animals.birthday
    FROM animals
    WHERE animal_type_id = 5;
    
CREATE OR REPLACE VIEW donkeis AS
	SELECT animals.id, animals.animal_name, animals.birthday
    FROM animals
    WHERE animal_type_id = 6;
    
CREATE TABLE commands (id INT AUTO_INCREMENT PRIMARY KEY, 
					command_name VARCHAR(45));
                    
INSERT INTO commands (command_name)
VALUES
	('go'), ('stop'), ('come_up'), ('lie'), ('jump'), ('stand_up');

CREATE TABLE learned_commsnds (
	animal_id INT NOT NULL,
    command_id INT,
	FOREIGN KEY (animal_id) REFERENCES animals(id),
	FOREIGN KEY (command_id) REFERENCES commands(id));
    
INSERT INTO learned_commsnds (animal_id, command_id)
	VALUES
		(1, 3),
        (1, 4),
        (2, 3),
        (2, 4),
        (2, 5),
        (7, 1),
        (7, 2),
        (8, 1),
        (8, 2);
        
SELECT animals.animal_name, commands.command_name
	FROM learned_commsnds
    JOIN animals ON learned_commsnds.animal_id = animals.id
    JOIN commands ON learned_commsnds.command_id = commands.id;
    
-- 10. Удалив из таблицы верблюдов, т.к. верблюдов решили перевезти в другой
-- питомник на зимовку. Объединить таблицы лошади, и ослы в одну таблицу.

DELETE FROM animals
	WHERE animal_type_id = 5;
    
CREATE OR REPLACE VIEW pack_aimals AS
	SELECT animals.id, animals.animal_name, animals.birthday
    FROM animals
    WHERE animal_type_id = 6 OR animal_type_id = 4;
    
-- 11.Создать новую таблицу “молодые животные” в которую попадут все
-- животные старше 1 года, но младше 3 лет и в отдельном столбце с точностью
-- до месяца подсчитать возраст животных в новой таблице

CREATE OR REPLACE VIEW young_aimals AS
	SELECT animals.id, animals.animal_name, DATE_FORMAT(FROM_DAYS(DATEDIFF(CURRENT_DATE, animals.birthday)),'%y yr %c mth') AS age
    FROM animals
    WHERE animals.birthday BETWEEN '2020-10-03' AND '2022-10-03';