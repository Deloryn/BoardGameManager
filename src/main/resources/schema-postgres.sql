CREATE TABLE GameCopies (
    id          INT NOT NULL,
    gameId      INT NOT NULL
);

ALTER TABLE GameCopies ADD CONSTRAINT PK_GameCopies PRIMARY KEY ( id );

CREATE TABLE Games (
    id            INT NOT NULL,
    name          VARCHAR(100) NOT NULL,
    publisher     VARCHAR(100) NOT NULL,
    minPlayers    SMALLINT NOT NULL,
    maxPlayers    SMALLINT NOT NULL,
    avgTime       TIMESTAMP(0) NOT NULL,
    description   VARCHAR(300)
);

ALTER TABLE Games ADD CONSTRAINT PK_Games PRIMARY KEY ( id );

CREATE TABLE Clients (
    id   INT NOT NULL
);

ALTER TABLE Clients ADD CONSTRAINT PK_Clients PRIMARY KEY ( id );

CREATE TABLE Persons (
    id             INT NOT NULL,
    name           VARCHAR(100) NOT NULL,
    surname        VARCHAR(100) NOT NULL,
    email          VARCHAR(100) NOT NULL,
    phoneNumber    VARCHAR(20) NOT NULL,
    role           CHAR(1)
);

ALTER TABLE Persons ADD CONSTRAINT PK_Persons PRIMARY KEY ( id );

CREATE TABLE PrivateRentals (
    copyId          INT NOT NULL,
    rentalTime      TIMESTAMP(0) NOT NULL,
    duration        TIMESTAMP(0) NOT NULL,
    status          VARCHAR(30) NOT NULL,
    clientId        INT NOT NULL
);

ALTER TABLE PrivateRentals ADD CONSTRAINT PK_PrivateRentals PRIMARY KEY ( copyId );

ALTER TABLE PrivateRentals ADD CONSTRAINT UQ_PrivateRentals UNIQUE ( clientId,
                                                                     rentalTime );

CREATE TABLE PrivateReservations (
    tableId           INT NOT NULL,
    reservationTime   TIMESTAMP(0) NOT NULL,
    duration          TIMESTAMP(0) NOT NULL,
    clientId          INT NOT NULL
);

ALTER TABLE PrivateReservations ADD CONSTRAINT PK_PrivateReservations PRIMARY KEY ( tableId );

ALTER TABLE PrivateReservations ADD CONSTRAINT UQ_PrivateReservations UNIQUE ( clientId,
                                                                               reservationTime );

CREATE TABLE Rentals (
    copyId   INT NOT NULL,
    type     CHAR(1)
);

ALTER TABLE Rentals ADD CONSTRAINT PK_Rentals PRIMARY KEY ( copyId );

CREATE TABLE Reservations (
    tableId    INT NOT NULL,
    tutorId    INT,
    type       CHAR(1)
);

ALTER TABLE Reservations ADD CONSTRAINT PK_Reservations PRIMARY KEY ( tableId );

CREATE TABLE Tables (
    id             INT NOT NULL,
    numberOfSits   SMALLINT NOT NULL
);

ALTER TABLE Tables ADD CONSTRAINT PK_Tables PRIMARY KEY ( id );

CREATE TABLE TournamentParticipants (
    tournamentId   INT NOT NULL,
    clientId       INT NOT NULL
);

ALTER TABLE TournamentParticipants ADD CONSTRAINT PK_TournamentParticipants PRIMARY KEY ( tournamentId,
                                                                                          clientId );

CREATE TABLE TournamentRentals (
    copyId         INT NOT NULL,
    tournamentId   INT NOT NULL
);

ALTER TABLE TournamentRentals ADD CONSTRAINT PK_TournamentRentals PRIMARY KEY ( copyId );

ALTER TABLE TournamentRentals ADD CONSTRAINT UQ_TournamentRentals UNIQUE ( tournamentId );

CREATE TABLE TournamentReservations (
    tableId        INT NOT NULL,
    tournamentId   INT NOT NULL
);

ALTER TABLE TournamentReservations ADD CONSTRAINT PK_TournamentReservations PRIMARY KEY ( tableId );

ALTER TABLE TournamentReservations ADD CONSTRAINT UQ_TournamentReservations UNIQUE ( tournamentId );

CREATE TABLE Tournaments (
    id                 INT NOT NULL,
    time               TIMESTAMP(0) NOT NULL,
    duration           TIMESTAMP(0) NOT NULL,
    maxPlayers         SMALLINT NOT NULL,
    gameId             INT NOT NULL
);

ALTER TABLE Tournaments ADD CONSTRAINT PK_Tournaments PRIMARY KEY ( id );

CREATE TABLE Tutors (
    id   INT NOT NULL
);

ALTER TABLE Tutors ADD CONSTRAINT PK_Tutors PRIMARY KEY ( id );

ALTER TABLE GameCopies
    ADD CONSTRAINT FK_GameCopies_Games FOREIGN KEY ( gameId )
        REFERENCES Games ( id );

ALTER TABLE Clients
    ADD CONSTRAINT FK_Clients_Persons FOREIGN KEY ( id )
        REFERENCES Persons ( id );

ALTER TABLE PrivateRentals
    ADD CONSTRAINT FK_PrivateRentals_Clients FOREIGN KEY ( clientId )
        REFERENCES Clients ( id );

ALTER TABLE PrivateRentals
    ADD CONSTRAINT FK_PrivateRentals_Rentals FOREIGN KEY ( copyId )
        REFERENCES Rentals ( copyId );

ALTER TABLE PrivateReservations
    ADD CONSTRAINT FK_PrivateReservations_Clients FOREIGN KEY ( clientId )
        REFERENCES Clients ( id );

ALTER TABLE PrivateReservations
    ADD CONSTRAINT FK_PrivateReservations_Reservations FOREIGN KEY ( tableId )
        REFERENCES Reservations ( tableId );

ALTER TABLE TournamentParticipants
    ADD CONSTRAINT FK_TournamentParticipants_Clients FOREIGN KEY ( clientId )
        REFERENCES Clients ( id );

ALTER TABLE TournamentParticipants
    ADD CONSTRAINT FK_TournamentParticipants_Tournaments FOREIGN KEY ( tournamentId )
        REFERENCES Tournaments ( id );

ALTER TABLE Rentals
    ADD CONSTRAINT FK_Rentals_GameCopies FOREIGN KEY ( copyId )
        REFERENCES GameCopies ( id );

ALTER TABLE Reservations
    ADD CONSTRAINT FK_Reservations_Tables FOREIGN KEY ( tableId )
        REFERENCES Tables ( id );

ALTER TABLE Reservations
    ADD CONSTRAINT FK_Reservations_Tutors FOREIGN KEY ( tutorId )
        REFERENCES Tutors ( id );

ALTER TABLE Tournaments
    ADD CONSTRAINT FK_Tournaments_Games FOREIGN KEY ( gameId )
        REFERENCES Games ( id );

ALTER TABLE TournamentRentals
    ADD CONSTRAINT FK_TournamentRentals_Rentals FOREIGN KEY ( copyId )
        REFERENCES Rentals ( copyId );

ALTER TABLE TournamentRentals
    ADD CONSTRAINT FK_TournamentRentals_Tournaments FOREIGN KEY ( tournamentId )
        REFERENCES Tournaments ( id );

ALTER TABLE TournamentReservations
    ADD CONSTRAINT FK_TournamentReservations_Reservations FOREIGN KEY ( tableId )
        REFERENCES Reservations ( tableId );

ALTER TABLE TournamentReservations
    ADD CONSTRAINT FK_TournamentReservations_Tournaments FOREIGN KEY ( tournamentId )
        REFERENCES Tournaments ( id );

ALTER TABLE Tutors
    ADD CONSTRAINT FK_Tutors_Persons FOREIGN KEY ( id )
        REFERENCES Persons ( id );


CREATE OR REPLACE FUNCTION getRegularClientsEmails() RETURNS SETOF Persons.email%TYPE AS $$

SELECT email FROM Persons
	WHERE (SELECT COUNT(*) FROM TournamentParticipants
		WHERE TournamentParticipants.clientId = Persons.id) >= 2

$$ LANGUAGE sql;


CREATE OR REPLACE FUNCTION getMostPopularBoardGame() RETURNS Games.name%TYPE AS $$

SELECT name FROM Games WHERE id = (SELECT gameId FROM Tournaments
						WHERE id =
							(SELECT tournamentId FROM TournamentParticipants
								GROUP BY tournamentId
									ORDER BY AVG(clientId) DESC
										LIMIT 1))
$$ LANGUAGE sql;
