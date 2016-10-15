CREATE USER 'java'@'localhost' IDENTIFIED BY 'password';
GRANT ALL ON javabase.* TO 'java'@'localhost' IDENTIFIED BY 'password';

CREATE DATABASE newscrawler;

USE newscrawler

CREATE TABLE categories (
        id INT(9) NOT NULL AUTO_INCREMENT,
        description VARCHAR(16),        
        dateCreate VARCHAR(18),
        lastUpDate VARCHAR(18),
        recurrenceInterval INT(9), 
        PRIMARY KEY (id)
);

CREATE TABLE followLists (
        id INT(9) NOT NULL AUTO_INCREMENT,
        description VARCHAR(16),        
        dateCreate VARCHAR(18),
        lastUpDate VARCHAR(18),
        
        categoryId INT(9),
        sourceId INT(9),
        
        PRIMARY KEY (id)
);

CREATE TABLE sources (
        id INT(9) NOT NULL AUTO_INCREMENT,
        description VARCHAR(16),        
        dateCreate VARCHAR(18),
        lastUpDate VARCHAR(18),
        
        url VARCHAR(256),
        
        PRIMARY KEY (id)
);

CREATE TABLE whiteList (
        id INT(9) NOT NULL AUTO_INCREMENT,
        description VARCHAR(16),        
        dateCreate VARCHAR(18),
        lastUpDate VARCHAR(18),

        word VARCHAR(32),

        PRIMARY KEY (id)
);

CREATE TABLE blackList (
        id INT(9) NOT NULL AUTO_INCREMENT,
        description VARCHAR(16),        
        dateCreate VARCHAR(18),
        lastUpDate VARCHAR(18),

        word VARCHAR(32),

        PRIMARY KEY (id)
);

CREATE TABLE news (
        id INT(9) NOT NULL AUTO_INCREMENT,
        sourceId INT(9),
        url VARCHAR(512),
	text VARCHAR(512),
        value INT(9),        	        
        dateCreate VARCHAR(18),
        lastUpDate VARCHAR(18),		
        PRIMARY KEY (id)
);


INSERT INTO sources (url) VALUES ("https://sputniknews.com");
INSERT INTO sources (url) VALUES ("https://rt.com");
INSERT INTO sources (url) VALUES ("https://www.theguardian.com/international");
INSERT INTO sources (url) VALUES ("http://www.dailymail.co.uk/home/index.html");
INSERT INTO sources (url) VALUES ("http://www.nytimes.com/");
INSERT INTO sources (url) VALUES ("http://www.latimes.com/");
INSERT INTO sources (url) VALUES ("https://www.washingtonpost.com/");
INSERT INTO sources (url) VALUES ("http://brasil.elpais.com/");
INSERT INTO sources (url) VALUES ("http://edition.cnn.com/");
INSERT INTO sources (url) VALUES ("http://www.bbc.com");
INSERT INTO sources (url) VALUES ("http://g1.globo.com/");
INSERT INTO sources (url) VALUES ("http://www.ig.com.br/");
INSERT INTO sources (url) VALUES ("http://www.r7.com/");
INSERT INTO sources (url) VALUES ("https://br.noticias.yahoo.com/");
INSERT INTO sources (url) VALUES ("http://www.estadao.com.br/");
INSERT INTO sources (url) VALUES ("http://www.folha.uol.com.br/");
INSERT INTO sources (url) VALUES ("http://www.cartacapital.com.br/");
INSERT INTO sources (url) VALUES ("http://imasters.com.br/");
INSERT INTO sources (url) VALUES ("http://www.clubedohardware.com.br/");
INSERT INTO sources (url) VALUES ("http://olhardigital.uol.com.br/");
INSERT INTO sources (url) VALUES ("https://tecnoblog.net/");
INSERT INTO sources (url) VALUES ("http://www.cartacapital.com.br/");
INSERT INTO sources (url) VALUES ("http://www.france24.com/en/");
INSERT INTO sources (url) VALUES ("http://www.independent.co.uk/topic/France");
INSERT INTO sources (url) VALUES ("http://www.telegraph.co.uk");
INSERT INTO sources (url) VALUES ("http://www.reuters.com");
INSERT INTO sources (url) VALUES ("http://www.aljazeera.com");
INSERT INTO sources (url) VALUES ("http://www.foxnews.com");
INSERT INTO sources (url) VALUES ("http://www.technewsworld.com/");
INSERT INTO sources (url) VALUES ("https://www.cnet.com/news/");
INSERT INTO sources (url) VALUES ("http://www.theverge.com/tech");
INSERT INTO sources (url) VALUES ("http://www.spiegel.de/international/");
INSERT INTO sources (url) VALUES ("http://www.dw.com");
INSERT INTO sources (url) VALUES ("http://portal.imprensanacional.gov.br/");

INSERT INTO categories (description,recurrenceInterval) VALUES ("política",10);
INSERT INTO categories (description,recurrenceInterval) VALUES ("tecnologia",10);
INSERT INTO categories (description,recurrenceInterval) VALUES ("conflitos",10);

INSERT INTO whiteList (word) VALUES ("parfor");
INSERT INTO whiteList (word) VALUES ("war");
INSERT INTO whiteList (word) VALUES ("guerra");
INSERT INTO whiteList (word) VALUES ("russia");
INSERT INTO whiteList (word) VALUES ("violenc");
INSERT INTO whiteList (word) VALUES ("violência");
INSERT INTO whiteList (word) VALUES ("breaking");
INSERT INTO whiteList (word) VALUES ("peace");
INSERT INTO whiteList (word) VALUES ("riot");
INSERT INTO whiteList (word) VALUES ("technology");
INSERT INTO whiteList (word) VALUES ("tecnologia");
INSERT INTO whiteList (word) VALUES ("protesto");
INSERT INTO whiteList (word) VALUES ("morte");
INSERT INTO whiteList (word) VALUES ("acidente");
INSERT INTO whiteList (word) VALUES ("murder");
INSERT INTO whiteList (word) VALUES ("dead");
INSERT INTO whiteList (word) VALUES ("crime");
INSERT INTO whiteList (word) VALUES ("strategic");
INSERT INTO whiteList (word) VALUES ("estratégia");
INSERT INTO whiteList (word) VALUES ("estrategia");
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

