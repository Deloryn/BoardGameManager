-- noinspection SqlDialectInspectionForFile

-- noinspection SqlNoDataSourceInspectionForFile

-- Here we can place initial date to insert into database
INSERT INTO Persons(id, name, surname, email, phoneNumber, role) VALUES (1, 'Jan', 'Kowalski', 'jan.kowalski@poczta.pl', '123456789', 'c');
INSERT INTO Persons(id, name, surname, email, phoneNumber, role) VALUES (2, 'Andrzej', 'Duda', 'duda@poczta.pl', '551456789', 'c');
INSERT INTO Persons(id, name, surname, email, phoneNumber, role) VALUES (3, 'Marian', 'Kowalski', 'idzzpradem@poczta.pl', '123456789', 'c');
INSERT INTO Persons(id, name, surname, email, phoneNumber, role) VALUES (4, 'Grzegorz', 'Braun', 'kww@poczta.pl', '123123789', 't');
INSERT INTO Persons(id, name, surname, email, phoneNumber, role) VALUES (5, 'Janusz', 'Korwin-Mikke', 'jkm@poczta.pl', '001456789', 't');
INSERT INTO Persons(id, name, surname, email, phoneNumber, role) VALUES (6, 'Mariusz', 'Pudzianowski', 'kulturysta@poczta.pl', '123090789', 't');
INSERT INTO Persons(id, name, surname, email, phoneNumber, role) VALUES (7, 'Henryk', 'Sienkiewicz', 'copywriter@poczta.pl', '127776789', 'c');
INSERT INTO Persons(id, name, surname, email, phoneNumber, role) VALUES (8, 'Robert', 'Martin', 'bob@poczta.pl', '127771789', 'c');
INSERT INTO Persons(id, name, surname, email, phoneNumber, role) VALUES (9, 'Sir', 'Dijkstra', 'kruskalwasright@poczta.pl', '127776781', 'c');
INSERT INTO Persons(id, name, surname, email, phoneNumber, role) VALUES (10, 'Anakin', 'Skywalker', 'sonofvader@poczta.pl', '127776729', 'c');
INSERT INTO Persons(id, name, surname, email, phoneNumber, role) VALUES (11, 'Wielki', 'Brat', 'patrzy@poczta.pl', '127776739', 'c');
INSERT INTO Persons(id, name, surname, email, phoneNumber, role) VALUES (12, 'Orochimaru', 'Sama', 'edo.tensei@poczta.pl', '127776749', 'c');
INSERT INTO Persons(id, name, surname, email, phoneNumber, role) VALUES (13, 'Ulrich', 'Von Jungingen', 'prusy@poczta.pl', '127776759', 'c');

INSERT INTO Games(id, name, publisher, minPlayers, maxPlayers, avgTime, description) VALUES (1, 'Business Tour', 'PP', 2, 4, 60, 'A really nice game');
INSERT INTO Games(id, name, publisher, minPlayers, maxPlayers, avgTime, description) VALUES (2, 'Monopoly', 'PP', 2, 6, 120, 'A really nice game');
INSERT INTO Games(id, name, publisher, minPlayers, maxPlayers, avgTime, description) VALUES (3, 'Munchkin', 'PP', 1, 4, 60, 'A really nice game');
INSERT INTO Games(id, name, publisher, minPlayers, maxPlayers, avgTime, description) VALUES (4, 'Pokemon', 'PP', 1, 3, 60, 'A really nice game');
INSERT INTO Games(id, name, publisher, minPlayers, maxPlayers, avgTime, description) VALUES (5, 'Yu-Gi-Oh!', 'PP', 2, 2, 60, 'A really nice game');

INSERT INTO GameCopies(id, gameId) VALUES (1, 1);
INSERT INTO GameCopies(id, gameId) VALUES (2, 1);
INSERT INTO GameCopies(id, gameId) VALUES (3, 1);
INSERT INTO GameCopies(id, gameId) VALUES (4, 2);
INSERT INTO GameCopies(id, gameId) VALUES (5, 2);
INSERT INTO GameCopies(id, gameId) VALUES (6, 3);
INSERT INTO GameCopies(id, gameId) VALUES (7, 3);
INSERT INTO GameCopies(id, gameId) VALUES (8, 3);
INSERT INTO GameCopies(id, gameId) VALUES (9, 4);
INSERT INTO GameCopies(id, gameId) VALUES (10, 4);

INSERT INTO Tables(id, numberOfSits) VALUES (1, 4);
INSERT INTO Tables(id, numberOfSits) VALUES (2, 4);
INSERT INTO Tables(id, numberOfSits) VALUES (3, 3);
INSERT INTO Tables(id, numberOfSits) VALUES (4, 3);
INSERT INTO Tables(id, numberOfSits) VALUES (5, 2);
INSERT INTO Tables(id, numberOfSits) VALUES (6, 2);
INSERT INTO Tables(id, numberOfSits) VALUES (7, 5);
INSERT INTO Tables(id, numberOfSits) VALUES (8, 5);
INSERT INTO Tables(id, numberOfSits) VALUES (9, 8);

INSERT INTO Tournaments(id, startTime, duration, maxPlayers, gameId) VALUES (1, '2019-02-18 16:00:00', 180, 4, 1);
INSERT INTO Tournaments(id, startTime, duration, maxPlayers, gameId) VALUES (2, '2019-02-18 14:00:00', 45, 5, 2);
INSERT INTO Tournaments(id, startTime, duration, maxPlayers, gameId) VALUES (3, '2019-02-18 15:00:00', 240, 6, 3);
INSERT INTO Tournaments(id, startTime, duration, maxPlayers, gameId) VALUES (4, '2019-02-18 16:00:00', 60, 8, 4);

INSERT INTO TournamentRentals(id, copyId, tournamentId) VALUES (1, 1, 1);
INSERT INTO TournamentRentals(id, copyId, tournamentId) VALUES (2, 4, 2);
INSERT INTO TournamentRentals(id, copyId, tournamentId) VALUES (3, 6, 3);
INSERT INTO TournamentRentals(id, copyId, tournamentId) VALUES (4, 9, 4);

INSERT INTO PrivateRentals(id, clientId, copyId, startTime, duration, status) VALUES (1, 1, 2, '2019-02-18 10:00:00', 120, 'Everything okay');
INSERT INTO PrivateRentals(id, clientId, copyId, startTime, duration, status) VALUES (2, 2, 3, '2019-02-18 10:00:00', 120, 'Everything okay');
INSERT INTO PrivateRentals(id, clientId, copyId, startTime, duration, status) VALUES (3, 3, 5, '2019-02-18 10:00:00', 120, 'Everything okay');

INSERT INTO TournamentReservations(id, tableId, tutorId, tournamentId) VALUES (1, 1, NULL, 1);
INSERT INTO TournamentReservations(id, tableId, tutorId, tournamentId) VALUES (2, 2, NULL, 2);
INSERT INTO TournamentReservations(id, tableId, tutorId, tournamentId) VALUES (3, 3, NULL, 3);
INSERT INTO TournamentReservations(id, tableId, tutorId, tournamentId) VALUES (4, 4, NULL, 4);
INSERT INTO TournamentReservations(id, tableId, tutorId, tournamentId) VALUES (5, 5, 4, 4);

INSERT INTO PrivateReservations(id, clientId, tableId, tutorId, startTime, duration) VALUES (1, 10, 6, NULL, '2019-02-18 14:00:00', 90);
INSERT INTO PrivateReservations(id, clientId, tableId, tutorId, startTime, duration) VALUES (2, 11, 7, 5, '2019-02-18 09:00:00', 60);


INSERT INTO TournamentParticipants(clientId, tournamentId) VALUES (1, 1);
INSERT INTO TournamentParticipants(clientId, tournamentId) VALUES (2, 1);
INSERT INTO TournamentParticipants(clientId, tournamentId) VALUES (3, 1);
INSERT INTO TournamentParticipants(clientId, tournamentId) VALUES (7, 2);
INSERT INTO TournamentParticipants(clientId, tournamentId) VALUES (8, 2);
INSERT INTO TournamentParticipants(clientId, tournamentId) VALUES (9, 3);
INSERT INTO TournamentParticipants(clientId, tournamentId) VALUES (10, 3);
INSERT INTO TournamentParticipants(clientId, tournamentId) VALUES (11, 3);
INSERT INTO TournamentParticipants(clientId, tournamentId) VALUES (12, 3);
INSERT INTO TournamentParticipants(clientId, tournamentId) VALUES (13, 3);

COMMIT;