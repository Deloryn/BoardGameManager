-- noinspection SqlNoDataSourceInspectionForFile

-- noinspection SqlDialectInspectionForFile

CREATE TABLE Games
(
  id          INT          NOT NULL,
  name        VARCHAR(100) NOT NULL UNIQUE,
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



CREATE TABLE PrivateRentals
(
  id        INT          NOT NULL,
  clientId  INT          NOT NULL,
  copyId    INT          NOT NULL,
  startTime TIMESTAMP(0) NOT NULL,
  duration  INT          NOT NULL,
  status    VARCHAR(30)  NOT NULL
);

ALTER TABLE PrivateRentals
  ADD CONSTRAINT PK_PrivateRentals PRIMARY KEY (id);




CREATE TABLE TournamentRentals
(
  id           INT NOT NULL,
  copyId       INT NOT NULL,
  tournamentId INT NOT NULL
);

ALTER TABLE TournamentRentals
  ADD CONSTRAINT PK_Rentals PRIMARY KEY (id);



CREATE TABLE PrivateReservations
(
  id        INT          NOT NULL,
  clientId  INT          NOT NULL,
  tableId   INT          NOT NULL,
  tutorId   INT,
  startTime TIMESTAMP(0) NOT NULL,
  duration  INT          NOT NULL
);

ALTER TABLE PrivateReservations
  ADD CONSTRAINT PK_PrivateReservations PRIMARY KEY (id);



CREATE TABLE TournamentReservations
(
  id           INT      NOT NULL,
  tableId      INT      NOT NULL,
  tutorId      INT,
  tournamentId INT      NOT NULL
);

ALTER TABLE TournamentReservations
  ADD CONSTRAINT PK_TournamentReservations PRIMARY KEY (id);



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

ALTER TABLE PrivateRentals
  ADD CONSTRAINT FK_PrivateRentals_GameCopies FOREIGN KEY (copyId)
    REFERENCES GameCopies (id);

ALTER TABLE PrivateRentals
  ADD CONSTRAINT FK_PrivateRentals_Clients FOREIGN KEY (clientId)
    REFERENCES Persons (id);

ALTER TABLE TournamentRentals
  ADD CONSTRAINT FK_PrivateRentals_GameCopies FOREIGN KEY (copyId)
    REFERENCES GameCopies (id);

ALTER TABLE TournamentRentals
  ADD CONSTRAINT FK_PrivateRentals_Tournaments FOREIGN KEY (tournamentId)
    REFERENCES Tournaments (id);

ALTER TABLE PrivateReservations
  ADD CONSTRAINT FK_PrivateReservations_Tables FOREIGN KEY (tableId)
    REFERENCES Tables (id);

ALTER TABLE PrivateReservations
  ADD CONSTRAINT FK_PrivateReservations_Tutors FOREIGN KEY (tutorId)
    REFERENCES Persons (id);

ALTER TABLE PrivateReservations
  ADD CONSTRAINT FK_PrivateReservations_Clients FOREIGN KEY (clientId)
    REFERENCES Persons (id);

ALTER TABLE TournamentReservations
  ADD CONSTRAINT FK_TournamentReservations_Tables FOREIGN KEY (tableId)
    REFERENCES Tables (id);

ALTER TABLE TournamentReservations
  ADD CONSTRAINT FK_TournamentReservations_Tutors FOREIGN KEY (tutorId)
    REFERENCES Persons (id);

ALTER TABLE TournamentReservations
  ADD CONSTRAINT FK_TournamentReservations_Tournaments FOREIGN KEY (tournamentId)
    REFERENCES Tournaments (id);

ALTER TABLE Tournaments
  ADD CONSTRAINT FK_Tournaments_Games FOREIGN KEY (gameId)
    REFERENCES Games (id);




CREATE SEQUENCE Games_SEQ START 101;

CREATE SEQUENCE GameCopies_SEQ START 101;

CREATE SEQUENCE Persons_SEQ START 101;

CREATE SEQUENCE Tables_SEQ START 101;

CREATE SEQUENCE Tournaments_SEQ START 101;

CREATE SEQUENCE PrivateReservations_SEQ START 101;

CREATE SEQUENCE TournamentReservations_SEQ START 101;

CREATE SEQUENCE PrivateRentals_SEQ START 101;

CREATE SEQUENCE TournamentRentals_SEQ START 101;
