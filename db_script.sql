CREATE USER 'java'@'localhost' IDENTIFIED BY 'password';
GRANT ALL ON javabase.* TO 'java'@'localhost' IDENTIFIED BY 'password';

CREATE DATABASE newscrawler;

USE newscrawler

CREATE TABLE categories (
        id INT(9) NOT NULL AUTO_INCREMENT,

        description VARCHAR(16),        
        recurrenceInterval INT(9), 

        dateCreate VARCHAR(18),
        lastUpDate VARCHAR(18),

        PRIMARY KEY (id)
);

CREATE TABLE followLists (
        id INT(9) NOT NULL AUTO_INCREMENT,

        categoryId INT(9),
        sourceId INT(9),
        description VARCHAR(16),        
                
        dateCreate VARCHAR(18),
        lastUpDate VARCHAR(18),

        PRIMARY KEY (id)
);

CREATE TABLE sources (
        id INT(9) NOT NULL AUTO_INCREMENT,

        description VARCHAR(16),
        url VARCHAR(256) UNIQUE,

        dateCreate VARCHAR(18),
        lastUpDate VARCHAR(18),

        PRIMARY KEY (id)
);

CREATE TABLE whiteList (
        id INT(9) NOT NULL AUTO_INCREMENT,

        description VARCHAR(16),        
        word VARCHAR(32) UNIQUE,

        dateCreate VARCHAR(18),
        lastUpDate VARCHAR(18),

        PRIMARY KEY (id)
);

CREATE TABLE blackList (
        id INT(9) NOT NULL AUTO_INCREMENT,

        description VARCHAR(16),        
        word VARCHAR(32),

        dateCreate VARCHAR(18),
        lastUpDate VARCHAR(18),

        PRIMARY KEY (id)
);

CREATE TABLE news (
        id INT(9) NOT NULL AUTO_INCREMENT,

        sourceId INT(9),
        url VARCHAR(512),
	text VARCHAR(512),
        value INT(9),
        
        hash VARCHAR(32) UNIQUE,        	        

        dateCreate VARCHAR(20),
        lastUpDate VARCHAR(18),		

        PRIMARY KEY (id)
);

CREATE TABLE stats (
        id INT(9) NOT NULL AUTO_INCREMENT,

        runTime VARCHAR(18),
        crawlingTime VARCHAR(18),        
        totalSources INT(9),
        totalKeyWords INT(9),
        totalNews INT(9),

        dateCreate VARCHAR(18),
        lastUpDate VARCHAR(18),		

        PRIMARY KEY (id)
);


CREATE TABLE moda (
        id INT(9) NOT NULL AUTO_INCREMENT,

        word VARCHAR(32),
        counter INT(9),

        PRIMARY KEY (id)
);


SELECT table_schema "newscrawler", Round(Sum(data_length + index_length) / 1024 / 1024, 1) "DB Size in MB" FROM   information_schema.tables GROUP  BY table_schema; 

INSERT INTO sources (url) VALUES ("http://www.france24.com/en/");                
INSERT INTO sources (url) VALUES ("http://www.independent.co.uk/topic/France");  
INSERT INTO sources (url) VALUES ("http://www.telegraph.co.uk");                 
INSERT INTO sources (url) VALUES ("http://www.reuters.com");                     
INSERT INTO sources (url) VALUES ("http://www.aljazeera.com");                   
INSERT INTO sources (url) VALUES ("http://veja.abril.com.br");                   
INSERT INTO sources (url) VALUES ("https://twitter.com/sciencenews");            
INSERT INTO sources (url) VALUES ("www.nature.com");
INSERT INTO sources (url) VALUES ("http://www.lemonde.fr/");
INSERT INTO sources (url) VALUES ("http://www.pravda.ru/");


INSERT INTO categories (description,recurrenceInterval) VALUES ("política",10);
INSERT INTO categories (description,recurrenceInterval) VALUES ("tecnologia",10);
INSERT INTO categories (description,recurrenceInterval) VALUES ("conflitos",10);

INSERT INTO whiteList (word) VALUES ("war");         
INSERT INTO whiteList (word) VALUES ("guerra");      
INSERT INTO whiteList (word) VALUES ("russia");      
INSERT INTO whiteList (word) VALUES ("violenc");     
INSERT INTO whiteList (word) VALUES ("breaking");    
INSERT INTO whiteList (word) VALUES ("peace");       
INSERT INTO whiteList (word) VALUES ("riot");        
INSERT INTO whiteList (word) VALUES ("technology");  
INSERT INTO whiteList (word) VALUES ("tecnologia");  
INSERT INTO whiteList (word) VALUES ("protest");    
INSERT INTO whiteList (word) VALUES ("morte");       
INSERT INTO whiteList (word) VALUES ("acidente");    
INSERT INTO whiteList (word) VALUES ("murder");      
INSERT INTO whiteList (word) VALUES ("dead");        
INSERT INTO whiteList (word) VALUES ("crime");       
INSERT INTO whiteList (word) VALUES ("strategic");   
INSERT INTO whiteList (word) VALUES ("perigo");      
INSERT INTO whiteList (word) VALUES ("danger");      
INSERT INTO whiteList (word) VALUES ("ameaça");      
INSERT INTO whiteList (word) VALUES ("warning");     
INSERT INTO whiteList (word) VALUES ("global");      
INSERT INTO whiteList (word) VALUES ("crash");       
INSERT INTO whiteList (word) VALUES ("clash");       
INSERT INTO whiteList (word) VALUES ("ISIS");        
INSERT INTO whiteList (word) VALUES ("daesh");       

INSERT INTO blackList (word) VALUES ("promoção");
INSERT INTO blackList (word) VALUES ("ganhe");
INSERT INTO blackList (word) VALUES ("compre");
INSERT INTO blackList (word) VALUES ("concorra");
