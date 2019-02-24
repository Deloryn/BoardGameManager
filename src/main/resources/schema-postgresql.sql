
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
  duration  INT          NOT NULL
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
    REFERENCES Games (id) ON DELETE CASCADE;

ALTER TABLE TournamentParticipants
  ADD CONSTRAINT FK_TournamentParticipants_Clients FOREIGN KEY (clientId)
    REFERENCES Persons (id) ON DELETE CASCADE;

ALTER TABLE TournamentParticipants
  ADD CONSTRAINT FK_TournamentParticipants_Tournaments FOREIGN KEY (tournamentId)
    REFERENCES Tournaments (id) ON DELETE CASCADE;

ALTER TABLE PrivateRentals
  ADD CONSTRAINT FK_PrivateRentals_GameCopies FOREIGN KEY (copyId)
    REFERENCES GameCopies (id) ON DELETE CASCADE;

ALTER TABLE PrivateRentals
  ADD CONSTRAINT FK_PrivateRentals_Clients FOREIGN KEY (clientId)
    REFERENCES Persons (id) ON DELETE CASCADE;

ALTER TABLE TournamentRentals
  ADD CONSTRAINT FK_PrivateRentals_GameCopies FOREIGN KEY (copyId)
    REFERENCES GameCopies (id) ON DELETE CASCADE;

ALTER TABLE TournamentRentals
  ADD CONSTRAINT FK_PrivateRentals_Tournaments FOREIGN KEY (tournamentId)
    REFERENCES Tournaments (id) ON DELETE CASCADE;

ALTER TABLE PrivateReservations
  ADD CONSTRAINT FK_PrivateReservations_Tables FOREIGN KEY (tableId)
    REFERENCES Tables (id) ON DELETE CASCADE;

ALTER TABLE PrivateReservations
  ADD CONSTRAINT FK_PrivateReservations_Tutors FOREIGN KEY (tutorId)
    REFERENCES Persons (id) ON DELETE SET NULL;

ALTER TABLE PrivateReservations
  ADD CONSTRAINT FK_PrivateReservations_Clients FOREIGN KEY (clientId)
    REFERENCES Persons (id) ON DELETE CASCADE;

ALTER TABLE TournamentReservations
  ADD CONSTRAINT FK_TournamentReservations_Tables FOREIGN KEY (tableId)
    REFERENCES Tables (id) ON DELETE CASCADE;

ALTER TABLE TournamentReservations
  ADD CONSTRAINT FK_TournamentReservations_Tutors FOREIGN KEY (tutorId)
    REFERENCES Persons (id) ON DELETE SET NULL;

ALTER TABLE TournamentReservations
  ADD CONSTRAINT FK_TournamentReservations_Tournaments FOREIGN KEY (tournamentId)
    REFERENCES Tournaments (id) ON DELETE CASCADE;

ALTER TABLE Tournaments
  ADD CONSTRAINT FK_Tournaments_Games FOREIGN KEY (gameId)
    REFERENCES Games (id) ON DELETE CASCADE;




CREATE SEQUENCE Games_SEQ START 101;

CREATE SEQUENCE GameCopies_SEQ START 101;

CREATE SEQUENCE Persons_SEQ START 101;

CREATE SEQUENCE Tables_SEQ START 101;

CREATE SEQUENCE Tournaments_SEQ START 101;

CREATE SEQUENCE PrivateReservations_SEQ START 101;

CREATE SEQUENCE TournamentReservations_SEQ START 101;

CREATE SEQUENCE PrivateRentals_SEQ START 101;

CREATE SEQUENCE TournamentRentals_SEQ START 101;


CREATE INDEX INDEX_Tournaments ON Tournaments(id);
CREATE INDEX INDEX_TournamentReservations ON TournamentReservations(id);
CREATE INDEX INDEX_TournamentRentals ON TournamentRentals(id);
CREATE INDEX INDEX_PrivateReservations ON PrivateReservations(id);
CREATE INDEX INDEX_PrivateRentals ON PrivateRentals(id);
CREATE INDEX INDEX_Persons ON Persons(id);
CREATE INDEX INDEX_Games ON Games(id);
CREATE INDEX INDEX_GameCopies ON GameCopies(id);
CREATE INDEX INDEX_Tables ON Tables(id);

CREATE INDEX INDEX_Tournaments_Games ON Tournaments(gameId);
CREATE INDEX INDEX_TournamentReservations_Tournaments ON TournamentReservations(tournamentId);
CREATE INDEX INDEX_TournamentReservations_Tutors ON TournamentReservations(tutorId);
CREATE INDEX INDEX_TournamentReservations_Tables ON TournamentReservations(tableId);
CREATE INDEX INDEX_PrivateReservations_Clients ON PrivateReservations(clientId);
CREATE INDEX INDEX_PrivateReservations_Tutors ON PrivateReservations(tutorId);
CREATE INDEX INDEX_PrivateReservations_Tables ON PrivateReservations(tableId);
CREATE INDEX INDEX_TournamentRentals_GameCopies ON TournamentRentals(copyId);
CREATE INDEX INDEX_TournamentRentals_Tournaments ON TournamentRentals(tournamentId);
CREATE INDEX INDEX_PrivateRentals_GameCopies ON PrivateRentals(copyId);
CREATE INDEX INDEX_PrivateRentals_Clients ON PrivateRentals(clientId);
CREATE INDEX INDEX_TournamentParticipants_Clients ON TournamentParticipants(clientId);
CREATE INDEX INDEX_TournamentParticipants_Tournaments ON TournamentParticipants(tournamentId);
CREATE INDEX INDEX_GameCopies_Games ON GameCopies(gameId);
CREATE INDEX INDEX_Persons_Email ON Persons(email);
CREATE INDEX INDEX_Tournaments_StartTime ON Tournaments(startTime);
CREATE INDEX INDEX_Tournaments_Duration ON Tournaments(duration);
CREATE INDEX INDEX_PrivateRentals_StartTime ON PrivateRentals(startTime);
CREATE INDEX INDEX_PrivateRentals_Duration ON PrivateRentals(duration);
CREATE INDEX INDEX_PrivateReservations_StartTime ON PrivateReservations(startTime);
CREATE INDEX INDEX_PrivateReservations_Duration ON PrivateReservations(duration);


CREATE OR REPLACE FUNCTION copyPersons() RETURNS VOID AS
'
   BEGIN
      DROP TABLE IF EXISTS PersonsCopy;
      CREATE TABLE PersonsCopy AS SELECT * FROM Persons;
   END
'
  LANGUAGE plpgsql;


CREATE OR REPLACE FUNCTION calculateFinishTime(startTime TIMESTAMP(0), duration INT) RETURNS TIMESTAMP(0) AS
  '
begin
  return startTime + (duration * interval ''1 minute '');
end;
' LANGUAGE  plpgsql;


CREATE OR REPLACE FUNCTION getRegularClientsEmails() RETURNS SETOF persons.email%TYPE AS $$

SELECT email FROM persons

$$ LANGUAGE sql;