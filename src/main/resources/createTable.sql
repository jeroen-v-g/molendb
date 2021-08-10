DROP TABLE Molens;

CREATE TABLE Molens (
    molenID int AUTO_INCREMENT  PRIMARY KEY,
    Naam varchar(255),
    Bouwjaar int,
    Type varchar(255),
    Kenmerken varchar(1024),
    Functie varchar(255),
    Adres varchar(1024),
    Molenaar varchar(1024),
    Eigenaar varchar(255),
    Plaats varchar(255),
    Website varchar(255),
    Foto blob(3M),
    FotoContentType varchar(255),
    FotoWidth int,
    FotoHeight int
);

