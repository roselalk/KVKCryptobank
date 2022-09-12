CREATE TABLE Trader (
                         idTrader VARCHAR(45) NOT NULL,
                         Password VARCHAR(128) NOT NULL,
                         FirstName VARCHAR(128),
                         Prefix VARCHAR(128),
                         Name VARCHAR(128) NOT NULL,
                         BSN VARCHAR(128) NOT NULL,
                         Birthdate VARCHAR(128) NOT NULL,
                         Adress VARCHAR(128) NOT NULL,
                         Number VARCHAR(128) NOT NULL,
                         PostalCode VARCHAR(128) NOT NULL,
                         City VARCHAR(128) NOT NULL,
                         Email VARCHAR(128) NOT NULL,
                         Active boolean NOT NULL,
                         Salt VARCHAR(128) NOT NULL,
                         PRIMARY KEY (idTrader)
);

