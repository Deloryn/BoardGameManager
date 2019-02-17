CREATE TABLE Games
(
  id          INT          NOT NULL,
  name        VARCHAR(100) NOT NULL,
  publisher   VARCHAR(100) NOT NULL,
  minPlayers  SMALLINT     NOT NULL,
  maxPlayers  SMALLINT     NOT NULL,
  avgTime     TIMESTAMP(0) NOT NULL,
  description VARCHAR(300)
);

ALTER TABLE Games
  ADD CONSTRAINT PK_Games PRIMARY KEY (id);



CREATE TABLE GameCopies
(
  id     INT NOT NULL,
  gameId INT NOT NULL
);

ALTER TABLE GameCopies
  ADD CONSTRAINT PK_GameCopies PRIMARY KEY (id);



CREATE TABLE Persons
(
  id          INT          NOT NULL,
  name        VARCHAR(100) NOT NULL,
  surname     VARCHAR(100) NOT NULL,
  email       VARCHAR(100) NOT NULL,
  phoneNumber VARCHAR(20)  NOT NULL,
  role        CHAR(1)
);

ALTER TABLE Persons
  ADD CONSTRAINT PK_Persons PRIMARY KEY (id);


CREATE TABLE TournamentParticipants
(
  clientId    INT NOT NULL,
  tournamentId INT NOT NULL
);

ALTER TABLE TournamentParticipants
  ADD CONSTRAINT PK_TournamentParticipants PRIMARY KEY (clientId,
                                                        tournamentId);



CREATE TABLE Rentals
(
  copyId INT NOT NULL,
  type   CHAR(1)
);

ALTER TABLE Rentals
  ADD CONSTRAINT PK_Rentals PRIMARY KEY (copyId);




CREATE TABLE Reservations
(
  tableId INT NOT NULL,
  tutorId INT,
  type    CHAR(1)
);

ALTER TABLE Reservations
  ADD CONSTRAINT PK_Reservations PRIMARY KEY (tableId);



CREATE TABLE Tables
(
  id           INT      NOT NULL,
  numberOfSits SMALLINT NOT NULL
);

ALTER TABLE Tables
  ADD CONSTRAINT PK_Tables PRIMARY KEY (id);



CREATE TABLE Tournaments
(
  id         INT          NOT NULL,
  time       TIMESTAMP(0) NOT NULL,
  duration   TIMESTAMP(0) NOT NULL,
  maxPlayers SMALLINT     NOT NULL,
  gameId     INT          NOT NULL
);

ALTER TABLE Tournaments
  ADD CONSTRAINT PK_Tournaments PRIMARY KEY (id);



ALTER TABLE GameCopies
  ADD CONSTRAINT FK_GameCopies_Games FOREIGN KEY (gameId)
    REFERENCES Games (id);

ALTER TABLE TournamentParticipants
  ADD CONSTRAINT FK_TournamentParticipants_Clients FOREIGN KEY (id)
    REFERENCES Clients (id);

ALTER TABLE TournamentParticipants
  ADD CONSTRAINT FK_TournamentParticipants_Tournaments FOREIGN KEY (tournamentId)
    REFERENCES Tournaments (id);

ALTER TABLE Rentals
  ADD CONSTRAINT FK_Rentals_GameCopies FOREIGN KEY (copyId)
    REFERENCES GameCopies (id);

ALTER TABLE Reservations
  ADD CONSTRAINT FK_Reservations_Tables FOREIGN KEY (tableId)
    REFERENCES Tables (id);

ALTER TABLE Reservations
  ADD CONSTRAINT FK_Reservations_Tutors FOREIGN KEY (tutorId)
    REFERENCES Tutors (id);

ALTER TABLE Tournaments
  ADD CONSTRAINT FK_Tournaments_Games FOREIGN KEY (gameId)
    REFERENCES Games (id);



CREATE OR REPLACE FUNCTION getRegularClientsEmails() RETURNS SETOF Persons.email%TYPE AS
$$

SELECT email
FROM Persons
WHERE (SELECT COUNT(*)
       FROM TournamentParticipants
       WHERE TournamentParticipants.id = Persons.id) >= 2

$$ LANGUAGE sql;


CREATE OR REPLACE FUNCTION getMostPopularBoardGame() RETURNS Games.name%TYPE AS
$$

SELECT name
FROM Games
WHERE id = (SELECT gameId
            FROM Tournaments
            WHERE id =
                  (SELECT tournamentId
                   FROM TournamentParticipants
                   GROUP BY tournamentId
                   ORDER BY AVG(id) DESC
                   LIMIT 1))
$$ LANGUAGE sql;


CREATE SEQUENCE Games_SEQ;

CREATE SEQUENCE GameCopies_SEQ;

CREATE SEQUENCE Persons_SEQ;

CREATE SEQUENCE Tables_SEQ;

CREATE SEQUENCE Tournaments_SEQ;