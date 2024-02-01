-- -------------------------------------------------
-- Reconstruction de la base de donnée
-- -------------------------------------------------

DROP DATABASE IF EXISTS Shop;
CREATE DATABASE Shop;
USE Shop;

-- -------------------------------------------------
-- Construction de la table des articles en vente
-- -------------------------------------------------

CREATE TABLE T_Articles (
	IdArticle		int(4)		PRIMARY KEY AUTO_INCREMENT,
	Description		varchar(30)	NOT NULL,
	Brand 			varchar(30) NOT NULL,
	UnitaryPrice	float(8)	NOT NULL
) ENGINE = InnoDB;

INSERT INTO T_Articles ( Description, Brand, UnitaryPrice ) VALUES ( 'Souris', 'Logitoch', 65);
INSERT INTO T_Articles ( Description, Brand, UnitaryPrice ) VALUES ( 'Clavier', 'Microhard', 49.5);
INSERT INTO T_Articles ( Description, Brand, UnitaryPrice ) VALUES ( 'Systeme d''exploitation', 'Fenêtres Vistouille', 150);
INSERT INTO T_Articles ( Description, Brand, UnitaryPrice ) VALUES ( 'Tapis souris', 'Chapeau Bleu', 5);
INSERT INTO T_Articles ( Description, Brand, UnitaryPrice ) VALUES ( 'Cle USB 8 To', 'Syno', 8);
INSERT INTO T_Articles ( Description, Brand, UnitaryPrice ) VALUES ( 'Laptop', 'PH', 1199);
INSERT INTO T_Articles ( Description, Brand, UnitaryPrice ) VALUES ( 'CD x 500', 'CETME', 250);
INSERT INTO T_Articles ( Description, Brand, UnitaryPrice ) VALUES ( 'DVD-R x 100', 'CETM', 99);
INSERT INTO T_Articles ( Description, Brand, UnitaryPrice ) VALUES ( 'DVD+R x 100', 'CETM', 105);
INSERT INTO T_Articles ( Description, Brand, UnitaryPrice ) VALUES ( 'Batterie Laptop', 'PH', 80);
INSERT INTO T_Articles ( Description, Brand, UnitaryPrice ) VALUES ( 'Casque Audio', 'Syno', 105);
INSERT INTO T_Articles ( Description, Brand, UnitaryPrice ) VALUES ( 'WebCam', 'Logitoch', 755);


SELECT * FROM T_Articles;

-- 1.2 ---------------------------
-- Effectuer les requêtes permettant d’afficher toutes les tables en base
SHOW TABLES;

-- 1.3 ---------------------------
-- Trouver un moyen pour décrire une table
DESCRIBE T_Articles;

-- 1.4 ---------------------------
-- Ajouter à la table des articles des occurrences de votre choix
INSERT INTO T_Articles ( Description, Brand, UnitaryPrice ) VALUES ( 'Laptop', 'Lenovo', 1500);
INSERT INTO T_Articles ( Description, Brand, UnitaryPrice ) VALUES ( 'Ecran', 'ASUS', 155);
INSERT INTO T_Articles ( Description, Brand, UnitaryPrice ) VALUES ( 'Clavier', 'Logitoch', 100);
SELECT * FROM T_Articles;

-- 1.5 ---------------------------
-- Modifier un article avant de vérifier si c’est pris en compte
UPDATE T_Articles set Brand='Logitech', UnitaryPrice='120' where IdArticle = 15;
SELECT * FROM T_Articles;

-- 1.6 ---------------------------
-- Supprimer un article puis vérifier
DELETE FROM T_Articles where IdArticle = 15;
SELECT * FROM T_Articles;

-- 1.7 ---------------------------
-- Sélectionner tous les articles dont le prix est supérieur à 100
SELECT * FROM T_Articles where UnitaryPrice > 100;

-- 1.8 ---------------------------
-- Sélectionner les articles dont le prix est compris entre 50 et 150
SELECT * FROM T_Articles where UnitaryPrice > 50 and UnitaryPrice < 100;

-- 1.9 ---------------------------
-- Afficher les articles dans l'ordre croissant des prix
SELECT * from T_Articles ORDER BY UnitaryPrice ASC; 

-- 1.10 ---------------------------
-- Afficher uniquement la description des articles
SELECT Description from T_Articles;

-- 1.11 ---------------------------
-- Choisissez une requête particulièrement intéressante à présenter
--TODOOOOOOOOOOOOOOOOOOOOOOOO

-- 1.12 ---------------------------
-- Ajouter la table des catégories à votre base de données et insérez-en
-- Création nouvelle table
CREATE TABLE T_Categories (
	IdCategory INT(4) PRIMARY KEY AUTO_INCREMENT,
	CatName VARCHAR(30) NOT NULL,
	Description VARCHAR(100) NOT NULL
) ENGINE = InnoDB;

-- Ajout de la colonne IdCategory
ALTER TABLE T_Articles ADD COLUMN IdCategory INT(4);
ALTER TABLE T_Articles ADD FOREIGN KEY(IdCategory) REFERENCES T_Categories(IdCategory);

-- Insertion catégories
INSERT INTO T_Categories ( IdCategory, CatName, Description ) VALUES ( 1, 'PC', 'ordinateur portable ou pas');
INSERT INTO T_Categories ( IdCategory, CatName, Description ) VALUES ( 2, 'Smartphone', 'téléphone de dernière génération avec écran tactile + applis');
INSERT INTO T_Categories ( IdCategory, CatName, Description ) VALUES ( 3, 'Materiel info', 'Tout matériel informatique en lien avec un ordinateur');

SELECT * from T_Articles;

-- Mise à jour des articles
UPDATE T_ARTICLES set IdCategory=3 where IdArticle = 1;
UPDATE T_ARTICLES set IdCategory=3 where IdArticle = 2;
UPDATE T_ARTICLES set IdCategory=3 where IdArticle = 3;
UPDATE T_ARTICLES set IdCategory=3 where IdArticle = 4;
UPDATE T_ARTICLES set IdCategory=3 where IdArticle = 5;
UPDATE T_ARTICLES set IdCategory=1 where IdArticle = 6;
UPDATE T_ARTICLES set IdCategory=3 where IdArticle = 7;
UPDATE T_ARTICLES set IdCategory=3 where IdArticle = 8;
UPDATE T_ARTICLES set IdCategory=3 where IdArticle = 9;
UPDATE T_ARTICLES set IdCategory=3 where IdArticle = 10;
UPDATE T_ARTICLES set IdCategory=3 where IdArticle = 11;
UPDATE T_ARTICLES set IdCategory=3 where IdArticle = 12;
UPDATE T_ARTICLES set IdCategory=3 where IdArticle = 13;
UPDATE T_ARTICLES set IdCategory=3 where IdArticle = 14;

-- Ajout d'un nouvel article
INSERT INTO T_Articles ( Description, Brand, UnitaryPrice, IdCategory ) VALUES ( 'IPHONE11', 'Apple', 800, 2);

SELECT * FROM T_Articles;

-- 1.13 ---------------------------
-- Trouver la requête qui permet d’obtenir le résultat suivant :
SELECT IdArticle, T_Articles.Description, Brand, UnitaryPrice, CatName from T_Articles inner join t_categories where t_articles.idcategory = t_categories.idcategory ORDER BY UnitaryPrice ASC;

-- 6 + 7 ------------------------------
-- Création de la table T_Users

CREATE TABLE T_Users (
	IdUser				int(4)		PRIMARY KEY AUTO_INCREMENT,
	Login				varchar(20)			NOT NULL,
	Password			varchar(20)			NOT NULL
) ENGINE = InnoDB;

INSERT INTO T_Users ( Login, Password ) VALUES (  'Georges', "enfants");
INSERT INTO T_Users ( Login, Password ) VALUES ( 'Louis', "enfants2");
INSERT INTO T_Users ( Login, Password ) VALUES (  'Mesrine', "argent");









