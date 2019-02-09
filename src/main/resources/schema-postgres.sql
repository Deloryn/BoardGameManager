-- noinspection SqlNoDataSourceInspectionForFile

CREATE TABLE boardgame_copies (
    copy_id             INT NOT NULL,
    boardgame_game_id   INT NOT NULL
);

ALTER TABLE boardgame_copies ADD CONSTRAINT boardgame_copies_pk PRIMARY KEY ( copy_id );

CREATE TABLE boardgames (
    game_id       INT NOT NULL,
    name          VARCHAR(100) NOT NULL,
    publisher     VARCHAR(100) NOT NULL,
    min_players   SMALLINT NOT NULL,
    max_players   SMALLINT NOT NULL,
    avg_time      TIMESTAMP(0) NOT NULL,
    description   VARCHAR(300)
);

ALTER TABLE boardgames ADD CONSTRAINT boardgame_pk PRIMARY KEY ( game_id );

CREATE TABLE clients (
    person_id   INT NOT NULL
);

ALTER TABLE clients ADD CONSTRAINT client_pk PRIMARY KEY ( person_id );

CREATE TABLE persons (
    person_id      INT NOT NULL,
    name           VARCHAR(100) NOT NULL,
    surname        VARCHAR(100) NOT NULL,
    email          VARCHAR(100) NOT NULL,
    phone_number   VARCHAR(20) NOT NULL,
    role           CHAR(1)
);

ALTER TABLE persons ADD CONSTRAINT person_pk PRIMARY KEY ( person_id );

CREATE TABLE private_rentals (
    copy_id1           INT NOT NULL,
    rental_time        TIMESTAMP(0) NOT NULL,
    duration           TIMESTAMP(0) NOT NULL,
    status             VARCHAR(30) NOT NULL,
    client_person_id   INT NOT NULL
);

ALTER TABLE private_rentals ADD CONSTRAINT private_rentals_pk PRIMARY KEY ( copy_id1 );

ALTER TABLE private_rentals ADD CONSTRAINT private_rentals_pkv1 UNIQUE ( client_person_id,
                                                                         rental_time );

CREATE TABLE private_reservations (
    table_id1          INT NOT NULL,
    reservation_time   TIMESTAMP(0) NOT NULL,
    duration           TIMESTAMP(0) NOT NULL,
    client_person_id   INT NOT NULL
);

ALTER TABLE private_reservations ADD CONSTRAINT private_reservation_pk PRIMARY KEY ( table_id1 );

ALTER TABLE private_reservations ADD CONSTRAINT private_reservation_pkv1 UNIQUE ( client_person_id,
                                                                                  reservation_time );

CREATE TABLE rentals (
    boardgame_copies_copy_id   INT NOT NULL,
    type                       CHAR(1)
);

ALTER TABLE rentals ADD CONSTRAINT rentals_pk PRIMARY KEY ( boardgame_copies_copy_id );

CREATE TABLE reservations (
    table_table_id    INT NOT NULL,
    tutor_person_id   INT,
    type              CHAR(1)
);

ALTER TABLE reservations ADD CONSTRAINT reservation_pk PRIMARY KEY ( table_table_id );

CREATE TABLE tables (
    table_id         INT NOT NULL,
    number_of_sits   SMALLINT NOT NULL
);

ALTER TABLE tables ADD CONSTRAINT table_pk PRIMARY KEY ( table_id );

CREATE TABLE tournament_participants (
    tournament_tournament_id   INT NOT NULL,
    client_person_id           INT NOT NULL
);

ALTER TABLE tournament_participants ADD CONSTRAINT relation_6_pk PRIMARY KEY ( tournament_tournament_id,
                                                                               client_person_id );

CREATE TABLE tournament_rentals (
    copy_id1                   INT NOT NULL,
    tournament_tournament_id   INT NOT NULL
);

ALTER TABLE tournament_rentals ADD CONSTRAINT tournament_rentals_pk PRIMARY KEY ( copy_id1 );

ALTER TABLE tournament_rentals ADD CONSTRAINT tournament_rentals_pkv1 UNIQUE ( tournament_tournament_id );

CREATE TABLE tournament_reservations (
    table_id1                   INT NOT NULL,
    tournaments_tournament_id   INT NOT NULL
);

ALTER TABLE tournament_reservations ADD CONSTRAINT tournament_reservation_pk PRIMARY KEY ( table_id1 );

ALTER TABLE tournament_reservations ADD CONSTRAINT tournament_reservation_pkv1 UNIQUE ( tournaments_tournament_id );

CREATE TABLE tournaments (
    tournament_id       INT NOT NULL,
    tournament_time     TIMESTAMP(0) NOT NULL,
    duration            TIMESTAMP(0) NOT NULL,
    max_players         SMALLINT NOT NULL,
    boardgame_game_id   INT NOT NULL
);

ALTER TABLE tournaments ADD CONSTRAINT tournament_pk PRIMARY KEY ( tournament_id );

CREATE TABLE tutors (
    person_id   INT NOT NULL
);

ALTER TABLE tutors ADD CONSTRAINT tutor_pk PRIMARY KEY ( person_id );

ALTER TABLE boardgame_copies
    ADD CONSTRAINT boardgame_copies_boardgame_fk FOREIGN KEY ( boardgame_game_id )
        REFERENCES boardgames ( game_id );

ALTER TABLE clients
    ADD CONSTRAINT client_person_fk FOREIGN KEY ( person_id )
        REFERENCES persons ( person_id );

ALTER TABLE private_rentals
    ADD CONSTRAINT private_rentals_client_fk FOREIGN KEY ( client_person_id )
        REFERENCES clients ( person_id );

ALTER TABLE private_rentals
    ADD CONSTRAINT private_rentals_rentals_fk FOREIGN KEY ( copy_id1 )
        REFERENCES rentals ( boardgame_copies_copy_id );

ALTER TABLE private_reservations
    ADD CONSTRAINT private_reservation_client_fk FOREIGN KEY ( client_person_id )
        REFERENCES clients ( person_id );

ALTER TABLE private_reservations
    ADD CONSTRAINT private_reservation_reserv_fk FOREIGN KEY ( table_id1 )
        REFERENCES reservations ( table_table_id );

ALTER TABLE tournament_participants
    ADD CONSTRAINT relation_6_client_fk FOREIGN KEY ( client_person_id )
        REFERENCES clients ( person_id );

ALTER TABLE tournament_participants
    ADD CONSTRAINT relation_6_tournament_fk FOREIGN KEY ( tournament_tournament_id )
        REFERENCES tournaments ( tournament_id );

ALTER TABLE rentals
    ADD CONSTRAINT rentals_boardgame_copies_fk FOREIGN KEY ( boardgame_copies_copy_id )
        REFERENCES boardgame_copies ( copy_id );

ALTER TABLE reservations
    ADD CONSTRAINT reservation_table_fk FOREIGN KEY ( table_table_id )
        REFERENCES tables ( table_id );

ALTER TABLE reservations
    ADD CONSTRAINT reservation_tutor_fk FOREIGN KEY ( tutor_person_id )
        REFERENCES tutors ( person_id );

ALTER TABLE tournaments
    ADD CONSTRAINT tournament_boardgame_fk FOREIGN KEY ( boardgame_game_id )
        REFERENCES boardgames ( game_id );

ALTER TABLE tournament_rentals
    ADD CONSTRAINT tournament_rentals_rentals_fk FOREIGN KEY ( copy_id1 )
        REFERENCES rentals ( boardgame_copies_copy_id );

ALTER TABLE tournament_rentals
    ADD CONSTRAINT tournament_rentals_trnmnt_fk FOREIGN KEY ( tournament_tournament_id )
        REFERENCES tournaments ( tournament_id );

ALTER TABLE tournament_reservations
    ADD CONSTRAINT tournament_reserv_reserv_fk FOREIGN KEY ( table_id1 )
        REFERENCES reservations ( table_table_id );

ALTER TABLE tournament_reservations
    ADD CONSTRAINT tournament_reserv_trnmnt_fk FOREIGN KEY ( tournaments_tournament_id )
        REFERENCES tournaments ( tournament_id );

ALTER TABLE tutors
    ADD CONSTRAINT tutor_person_fk FOREIGN KEY ( person_id )
        REFERENCES persons ( person_id );



CREATE OR REPLACE FUNCTION get_good_clients_emails() RETURNS SETOF persons.email%TYPE AS $$

SELECT email FROM persons 
	WHERE (SELECT COUNT(*) FROM tournament_participants 
		WHERE tournament_participants.client_person_id = persons.person_id) >= 2

$$ LANGUAGE sql;



CREATE OR REPLACE FUNCTION get_good_board_game() RETURNS boardgames.name%TYPE AS $$

SELECT name FROM boardgames WHERE game_id = (SELECT boardgame_game_id FROM tournaments 
						WHERE tournament_id =
							(SELECT tournament_tournament_id FROM tournament_participants
								GROUP BY tournament_tournament_id
									ORDER BY AVG(client_person_id) DESC
										LIMIT 1))
$$ LANGUAGE sql;


CREATE OR REPLACE FUNCTION arc_fk_tournament_reservations_procedure() RETURNS trigger AS $$
DECLARE
    d char;
BEGIN
    SELECT
        a.type
    INTO d
    FROM
        reservations a
    WHERE
        a.table_table_id = new.table_id1;

    IF
        ( d IS NULL OR d <> 'T' )
    THEN
RAISE EXCEPTION 'FK Tournament_reserv_Reserv_FK in Table Tournament_reservations violates Arc constraint on Table Reservations - discriminator column Type doesn''t have value ''T''';
    END IF;

EXCEPTION
    WHEN no_data_found THEN
        NULL;
    WHEN OTHERS THEN
        RAISE;
END;
$$ LANGUAGE plpgsql;



CREATE OR REPLACE FUNCTION arc_fkarc_private_reservations_procedure() RETURNS trigger AS $$
DECLARE
    d char;
BEGIN
    SELECT
        a.type
    INTO d
    FROM
        reservations a
    WHERE
        a.table_table_id =new.table_id1;

    IF
        ( d IS NULL OR d <> 'P' )
    THEN

RAISE EXCEPTION 'FK Private_reservation_Reserv_FK in Table Private_reservations violates Arc constraint on Table Reservations - discriminator column Type doesn''t have value ''P''';
    END IF;

EXCEPTION
    WHEN no_data_found THEN
        NULL;
    WHEN OTHERS THEN
        RAISE;
END;
$$ LANGUAGE plpgsql;



CREATE OR REPLACE FUNCTION arc_fkarc_1_tutors_procedure() RETURNS trigger AS $$
DECLARE
    d char;
BEGIN
    SELECT
        a.role
    INTO d
    FROM
        persons a
    WHERE
        a.person_id =new.person_id;

    IF
        ( d IS NULL OR d <> 'T' )
    THEN
RAISE EXCEPTION 'FK Tutor_Person_FK in Table Tutors violates Arc constraint on Table Persons - discriminator column role doesn''t have value ''T''';
    END IF;

EXCEPTION
    WHEN no_data_found THEN
        NULL;
    WHEN OTHERS THEN
        RAISE;
END;
$$ LANGUAGE plpgsql;



CREATE OR REPLACE FUNCTION arc_fkarc_1_clients_procedure() RETURNS trigger AS $$
DECLARE
    d char;
BEGIN
    SELECT
        a.role
    INTO d
    FROM
        persons a
    WHERE
        a.person_id =new.person_id;

    IF
        ( d IS NULL OR d <> 'C' )
    THEN
RAISE EXCEPTION 'FK Client_Person_FK in Table Clients violates Arc constraint on Table Persons - discriminator column role doesn''t have value ''C''';
    END IF;

EXCEPTION
    WHEN no_data_found THEN
        NULL;
    WHEN OTHERS THEN
        RAISE;
END;
$$ LANGUAGE plpgsql;



CREATE OR REPLACE FUNCTION arc_fkarc_2_tournament_rentals_procedure() RETURNS trigger AS $$
DECLARE
    d char;
BEGIN
    SELECT
        a.type
    INTO d
    FROM
        rentals a
    WHERE
        a.boardgame_copies_copy_id =new.copy_id1;

    IF
        ( d IS NULL OR d <> 'T' )
    THEN
RAISE EXCEPTION 'FK Tournament_rentals_Rentals_FK in Table Tournament_rentals violates Arc constraint on Table Rentals - discriminator column Type doesn''t have value ''T''';
    END IF;

EXCEPTION
    WHEN no_data_found THEN
        NULL;
    WHEN OTHERS THEN
        RAISE;
END;
$$ LANGUAGE plpgsql;



CREATE OR REPLACE FUNCTION arc_fkarc_2_private_rentals_procedure() RETURNS trigger AS $$
DECLARE
    d char;
BEGIN
    SELECT
        a.type
    INTO d
    FROM
        rentals a
    WHERE
        a.boardgame_copies_copy_id =new.copy_id1;

    IF
        ( d IS NULL OR d <> 'P' )
    THEN
RAISE EXCEPTION 'FK Private_rentals_Rentals_FK in Table Private_rentals violates Arc constraint on Table Rentals - discriminator column Type doesn''t have value ''P''';
    END IF;

EXCEPTION
    WHEN no_data_found THEN
        NULL;
    WHEN OTHERS THEN
        RAISE;
END;
$$ LANGUAGE plpgsql;




CREATE TRIGGER arc_fk_tournament_reservations BEFORE
    INSERT OR UPDATE OF table_id1 ON tournament_reservations
    FOR EACH ROW
	EXECUTE PROCEDURE arc_fk_tournament_reservations_procedure();

CREATE TRIGGER arc_fkarc_private_reservations BEFORE
    INSERT OR UPDATE OF table_id1 ON private_reservations
    FOR EACH ROW
	EXECUTE PROCEDURE arc_fkarc_private_reservations_procedure();

CREATE TRIGGER arc_fkarc_1_tutors BEFORE
    INSERT OR UPDATE OF person_id ON tutors
    FOR EACH ROW
    EXECUTE PROCEDURE arc_fkarc_1_tutors_procedure();


CREATE TRIGGER arc_fkarc_1_clients BEFORE
    INSERT OR UPDATE OF person_id ON clients
    FOR EACH ROW
    EXECUTE PROCEDURE arc_fkarc_1_clients_procedure();


CREATE TRIGGER arc_fkarc_2_tournament_rentals BEFORE
    INSERT OR UPDATE OF copy_id1 ON tournament_rentals
    FOR EACH ROW
    EXECUTE PROCEDURE arc_fkarc_2_tournament_rentals_procedure();


CREATE TRIGGER arc_fkarc_2_private_rentals BEFORE
    INSERT OR UPDATE OF copy_id1 ON private_rentals
    FOR EACH ROW
    EXECUTE PROCEDURE arc_fkarc_2_private_rentals_procedure();

