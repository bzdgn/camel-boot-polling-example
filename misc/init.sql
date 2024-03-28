CREATE DATABASE maindb;

CREATE TABLE DOCUMENTS (
	id MEDIUMINT NOT NULL AUTO_INCREMENT,
    IDENTIFIER		VARCHAR(255),
    TITLE			VARCHAR(2047),
    PREFERRED_URL	varchar(511),
    PDF_URL			VARCHAR(511),
    METADATA		LONGTEXT,
	PRIMARY KEY		(id)
);

SELECT * FROM DOCUMENTS;