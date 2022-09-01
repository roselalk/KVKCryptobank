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
                         City VARCHAR(128) NOT NULL,
                         Email VARCHAR(128) NOT NULL,
                         Active VARCHAR(128) NOT NULL,
                         Salt VARCHAR(128) NOT NULL,
                         PRIMARY KEY (idTrader)
);

INSERT INTO Trader
VALUES (1, 'password', 'Paul', null, 'McCartney', 298791823, '15-05-1942', 'Maple St', '15', '83291MA', 'Liverpool', 'macca@hotmail.com', true),
       (2, 'wachtwoord', 'John', null, 'Lennon', 298791822, '31-09-1941', 'Oak St', '42', '83291LI', 'Liverpool', 'johnnieboy@yahoo.com', true),
       (3, 'pw', 'Ringo', null, 'Starr', 292391822, '31-10-1944', 'Tree St', '8', '82191LI', 'Liverpool', 'starr@gmail.com', true),
       (4, 'ww', 'George', null, 'Harrisson', 298791222, '14-03-1945', 'Fig St', '9A', '83291PL', 'Liverpool', 'awesomeguitarguy@live.nl', true);