CREATE TABLE Games
(
  id          INT          NOT NULL,
  name        VARCHAR(100) NOT NULL,
  publisher   VARCHAR(100) NOT NULL,
  minPlayers  SMALLINT     NOT NULL,
  maxPlayers  SMALLINT     NOT NULL,
  avgTime     INTEGER      NOT NULL,
  description VARCHAR(300)
);

ALTER TABLE Games
  ADD CONSTRAINT PK_Games PRIMARY KEY (id);



CREATE TABLE GameCopies
(
  id           INT NOT NULL,
  gameId       INT NOT NULL
);

ALTER TABLE GameCopies
  ADD CONSTRAINT PK_GameCopies PRIMARY KEY (id);



CREATE TABLE Persons
(
  id          INT          NOT NULL,
  name        VARCHAR(100) NOT NULL,
  surname     VARCHAR(100) NOT NULL,
  email       VARCHAR(100) NOT NULL UNIQUE,
  phoneNumber VARCHAR(20)  NOT NULL,
  role        CHAR(1)
);

ALTER TABLE Persons
  ADD CONSTRAINT PK_Persons PRIMARY KEY (id);


CREATE TABLE TournamentParticipants
(
  clientId     INT NOT NULL,
  tournamentId INT NOT NULL
);

ALTER TABLE TournamentParticipants
  ADD CONSTRAINT PK_TournamentParticipants PRIMARY KEY (clientId,
                                                        tournamentId);



CREATE TABLE Rentals
(
  id     INT NOT NULL,
  copyId INT NOT NULL,
  type   CHAR(1)
);

ALTER TABLE Rentals
  ADD CONSTRAINT PK_Rentals PRIMARY KEY (id);




CREATE TABLE Reservations
(
  id      INT NOT NULL,
  tableId INT NOT NULL,
  tutorId INT,
  type    CHAR(1)
);

ALTER TABLE Reservations
  ADD CONSTRAINT PK_Reservations PRIMARY KEY (id);



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
  startTime  TIMESTAMP(0) NOT NULL,
  duration   INTEGER      NOT NULL,
  maxPlayers SMALLINT     NOT NULL,
  gameId     INT          NOT NULL
);

ALTER TABLE Tournaments
  ADD CONSTRAINT PK_Tournaments PRIMARY KEY (id);



ALTER TABLE GameCopies
  ADD CONSTRAINT FK_GameCopies_Games FOREIGN KEY (gameId)
    REFERENCES Games (id);

ALTER TABLE TournamentParticipants
  ADD CONSTRAINT FK_TournamentParticipants_Clients FOREIGN KEY (clientId)
    REFERENCES Persons (id);

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
    REFERENCES Persons (id);

ALTER TABLE Tournaments
  ADD CONSTRAINT FK_Tournaments_Games FOREIGN KEY (gameId)
    REFERENCES Games (id);




CREATE SEQUENCE Games_SEQ;

CREATE SEQUENCE GameCopies_SEQ;

CREATE SEQUENCE Persons_SEQ;

CREATE SEQUENCE Tables_SEQ;

CREATE SEQUENCE Tournaments_SEQ;
