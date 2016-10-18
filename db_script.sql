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

        dateCreate VARCHAR(18),
        lastUpDate VARCHAR(18),		

        PRIMARY KEY (id)
);

CREATE TABLE duplicates (
        id INT(9) NOT NULL AUTO_INCREMENT,

        newsId INT(9),
        hash VARCHAR(32),

        dateCreate VARCHAR(18),
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

SELECT table_schema "newscrawler", Round(Sum(data_length + index_length) / 1024 / 1024, 1) "DB Size in MB" FROM   information_schema.tables GROUP  BY table_schema; 

INSERT INTO sources (url) VALUES ("https://sputniknews.com");                    
INSERT INTO sources (url) VALUES ("https://www.rt.com");                         
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
INSERT INTO sources (url) VALUES ("http://elpais.com");                          
INSERT INTO sources (url) VALUES ("https://news.google.com");                                 
INSERT INTO sources (url) VALUES ("https://www.yahoo.com/news/");                
INSERT INTO sources (url) VALUES ("http://www.news.com.au");                     
INSERT INTO sources (url) VALUES ("http://www.usatoday.com/news/");              
INSERT INTO sources (url) VALUES ("http://indiatoday.intoday.in");               
INSERT INTO sources (url) VALUES ("http://www.bloomberg.com");                   
INSERT INTO sources (url) VALUES ("http://www.forbes.com");                      
INSERT INTO sources (url) VALUES ("http://www.ft.com");                          
INSERT INTO sources (url) VALUES ("https://www.ft.com");                         
INSERT INTO sources (url) VALUES ("www.un.org/news/");                           
INSERT INTO sources (url) VALUES ("bandnewstv.band.uol.com.br");                 
INSERT INTO sources (url) VALUES ("http://www.ufam.edu.br/");                    
INSERT INTO sources (url) VALUES ("www.pragmatismopolitico.com.br");             
INSERT INTO sources (url) VALUES ("www.otempo.com.br");                          
INSERT INTO sources (url) VALUES ("www.redebrasilatual.com.br");                 
INSERT INTO sources (url) VALUES ("www.viomundo.com.br");                        
INSERT INTO sources (url) VALUES ("www.cienciahoje.org.br");                     
INSERT INTO sources (url) VALUES ("revistagalileu.globo.com");                   
INSERT INTO sources (url) VALUES ("http://veja.abril.com.br");                   
INSERT INTO sources (url) VALUES ("www.sbcs.org/revista/revista-online/");       
INSERT INTO sources (url) VALUES ("www.ufcg.edu.br/revistasaudeeciencia/");      
INSERT INTO sources (url) VALUES ("www.scielo.org");
INSERT INTO sources (url) VALUES ("http://www.revistas.usp.br/wp/");             
INSERT INTO sources (url) VALUES ("https://www.sciencenews.org");                
INSERT INTO sources (url) VALUES ("https://www.sciencedaily.com");               
INSERT INTO sources (url) VALUES ("https://www.sciencemag.org/news");            
INSERT INTO sources (url) VALUES ("www.sciencemag.org/news");                    
INSERT INTO sources (url) VALUES ("www.huffingtonpost.com");                     
INSERT INTO sources (url) VALUES ("www.livescience.com/news");                   
INSERT INTO sources (url) VALUES ("phys.org");      
INSERT INTO sources (url) VALUES ("https://science.nasa.gov/science-news");      
INSERT INTO sources (url) VALUES ("https://twitter.com/sciencenews");            
INSERT INTO sources (url) VALUES ("www.nature.com");
INSERT INTO sources (url) VALUES ("http://www.lemonde.fr/");
INSERT INTO sources (url) VALUES ("http://www.pravda.ru/");


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
INSERT INTO whiteList (word) VALUES ("ameaça");      
INSERT INTO whiteList (word) VALUES ("global");      
INSERT INTO whiteList (word) VALUES ("crash");       
INSERT INTO whiteList (word) VALUES ("clash");       
INSERT INTO whiteList (word) VALUES ("ISIS");        
INSERT INTO whiteList (word) VALUES ("daesh");       
INSERT INTO whiteList (word) VALUES ("Dilma");       
INSERT INTO whiteList (word) VALUES ("Lula");        
INSERT INTO whiteList (word) VALUES ("Delação");     
INSERT INTO whiteList (word) VALUES ("Prisão");      
INSERT INTO whiteList (word) VALUES ("Operação");    
INSERT INTO whiteList (word) VALUES ("Bilhão");      
INSERT INTO whiteList (word) VALUES ("Terror");      
INSERT INTO whiteList (word) VALUES ("Left-wing");   
INSERT INTO whiteList (word) VALUES ("Right-wing");  
INSERT INTO whiteList (word) VALUES ("eleições");    
INSERT INTO whiteList (word) VALUES ("elections");   
INSERT INTO whiteList (word) VALUES ("attack");      
INSERT INTO whiteList (word) VALUES ("ataque");      
INSERT INTO whiteList (word) VALUES ("bomb");        
INSERT INTO whiteList (word) VALUES ("atentado");    
INSERT INTO whiteList (word) VALUES ("imposto");     
INSERT INTO whiteList (word) VALUES ("brics");       
INSERT INTO whiteList (word) VALUES ("hostage");     
INSERT INTO whiteList (word) VALUES ("refem");       
INSERT INTO whiteList (word) VALUES ("petrobras");   
INSERT INTO whiteList (word) VALUES ("reféns");      
INSERT INTO whiteList (word) VALUES ("snowden");     
INSERT INTO whiteList (word) VALUES ("wikileaks");   
INSERT INTO whiteList (word) VALUES ("threats");     
INSERT INTO whiteList (word) VALUES ("obama");       
INSERT INTO whiteList (word) VALUES ("clinton");     
INSERT INTO whiteList (word) VALUES ("president");   
INSERT INTO whiteList (word) VALUES ("senator");     
INSERT INTO whiteList (word) VALUES ("senador");     
INSERT INTO whiteList (word) VALUES ("deputado");    
INSERT INTO whiteList (word) VALUES ("engenharia");  
INSERT INTO whiteList (word) VALUES (" engineering"); 
INSERT INTO whiteList (word) VALUES ("protesters");  
INSERT INTO whiteList (word) VALUES ("gaza");        
INSERT INTO whiteList (word) VALUES ("palestine");   
INSERT INTO whiteList (word) VALUES ("israel");      
INSERT INTO whiteList (word) VALUES ("syria");       
INSERT INTO whiteList (word) VALUES ("siria");       
INSERT INTO whiteList (word) VALUES ("ceasefire");   
INSERT INTO whiteList (word) VALUES ("pentagon");    
INSERT INTO whiteList (word) VALUES ("assange");     
INSERT INTO whiteList (word) VALUES ("exonera");     
INSERT INTO whiteList (word) VALUES ("manaus");      
INSERT INTO whiteList (word) VALUES ("earthquake");  
INSERT INTO whiteList (word) VALUES ("terremoto");   
INSERT INTO whiteList (word) VALUES ("catastrofe");  
INSERT INTO whiteList (word) VALUES ("cessar-fogo"); 
INSERT INTO whiteList (word) VALUES ("bolsonaro");   
INSERT INTO whiteList (word) VALUES ("estudo");      
INSERT INTO whiteList (word) VALUES ("study");       
INSERT INTO whiteList (word) VALUES ("tiroteio");    
INSERT INTO whiteList (word) VALUES ("shootout");    
INSERT INTO whiteList (word) VALUES ("rebel");       
INSERT INTO whiteList (word) VALUES ("FHC");         
INSERT INTO whiteList (word) VALUES (" AI");         
INSERT INTO whiteList (word) VALUES ("discover");    
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

