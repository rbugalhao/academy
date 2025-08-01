
INSERT INTO T_TEAM (id, product, name, default_location)
VALUES (1, 'Plant', 'The Gardeners', 'Lisbon');

INSERT INTO T_TEAM (id, product, name, default_location)
VALUES (2, 'Car', 'Stars', 'Porto');

INSERT INTO T_TEAM (id, product, name, default_location)
VALUES (3, 'Moto', 'Speedsters', 'Braga');

INSERT INTO T_TEAM (id, product, name, default_location)
VALUES (4, 'Dashboards', 'The Analytics', 'Lisbon');

INSERT INTO T_TEAM (id, product, name, default_location)
VALUES (5, 'Car', 'Wheels', 'Porto');

INSERT INTO T_TEAM (id, product, name, default_location)
VALUES (6, 'Car', 'Sonic Team', 'Porto');




INSERT INTO T_RACK (id, serial_number, team_id, default_location, status)
VALUES (1, '1000-12021-01', 1, 'PORTO', 'AVAILABLE');

INSERT INTO T_RACK (id, serial_number, team_id, default_location, status)
VALUES (2, '1000-12021-02', 2, 'PORTO', 'AVAILABLE');

INSERT INTO T_RACK (id, serial_number, team_id, default_location, status)
VALUES (3, '2222-10000-01', 3, 'LISBON', 'AVAILABLE');

INSERT INTO T_RACK (id, serial_number, team_id, default_location, status)
VALUES (4, '1000-12021-03', 4, 'BRAGA', 'AVAILABLE');

INSERT INTO T_RACK (id, serial_number, team_id, default_location, status)
VALUES (5, '3100-11031-01', 5, 'PORTO', 'AVAILABLE');




INSERT INTO TEAM_MEMBER(id, team_id, ctw_id, name)
VALUES (1, 2, 'CTW0001', 'João Pires');

INSERT INTO TEAM_MEMBER(id, team_id, ctw_id, name)
VALUES (2, 2, 'CTW0002', 'Amália Rodrigues');

INSERT INTO TEAM_MEMBER(id, team_id, ctw_id, name)
VALUES (3, 3, 'CTW0003', 'Alberto Meireles');

INSERT INTO TEAM_MEMBER(id, team_id, ctw_id, name)
VALUES (4, 4, 'CTW0004', 'Ana Luísa');

INSERT INTO TEAM_MEMBER(id, team_id, ctw_id, name)
VALUES (5, 4, 'CTW0005', 'António Costa');

INSERT INTO TEAM_MEMBER(id, team_id, ctw_id, name)
VALUES (6, 5, 'CTW0006', 'Catarina Silva');




INSERT INTO T_RACK_ASSET(asset_tag, rack_id)
VALUES ('tag1',1);

INSERT INTO T_RACK_ASSET(asset_tag, rack_id)
VALUES ('tag2',2);

INSERT INTO T_RACK_ASSET(asset_tag, rack_id)
VALUES ('tag3',3);

INSERT INTO T_RACK_ASSET(asset_tag, rack_id)
VALUES ('tag4',4);

INSERT INTO T_RACK_ASSET(asset_tag, rack_id)
VALUES ('tag5',5);




INSERT INTO T_BOOKING(rack_id, requester_id, book_from, book_to)
VALUES (1,1,'2024-01-01', '2024-01-31');

INSERT INTO T_BOOKING(rack_id, requester_id, book_from, book_to)
VALUES (5,2,'2024-07-01', '2024-07-31');

INSERT INTO T_BOOKING(rack_id, requester_id, book_from, book_to)
VALUES (2,4,'2024-03-01', '2024-05-15');

INSERT INTO T_BOOKING(rack_id, requester_id, book_from, book_to)
VALUES (4,3,'2024-01-01', '2024-03-31');

INSERT INTO T_BOOKING(rack_id, requester_id, book_from, book_to)
VALUES (4,6,'2024-04-01', '2024-04-30');




-- DO $$
-- DECLARE
-- max_id bigint;
-- BEGIN
-- SELECT MAX(id) INTO max_id FROM T_TEAM;
--
-- IF max_id IS NULL THEN
--         max_id := 0;
-- END IF;
--
-- EXECUTE format('ALTER SEQUENCE seq_team_id RESTART WITH %s', max_id + 1);
-- END $$;


DO $$
DECLARE
max_tean_id bigint;
max_rack_id bigint;
max_team_member_id bigint;
max_rack_asset_id bigint;
max_booking_id bigint;
BEGIN
SELECT MAX(id) INTO max_tean_id FROM T_TEAM;
SELECT MAX(id) INTO max_rack_id FROM T_RACK;
SELECT MAX(id) INTO max_team_member_id FROM TEAM_MEMBER;
SELECT MAX(id) INTO max_rack_asset_id FROM T_RACK_ASSET;
SELECT MAX(id) INTO max_booking_id FROM T_BOOKING;

-- IF max_id IS NULL THEN
--         max_id := 0;
-- END IF;

EXECUTE format('ALTER SEQUENCE seq_team_id RESTART WITH %s', max_tean_id + 1);
EXECUTE format('ALTER SEQUENCE seq_rack_id RESTART WITH %s', max_rack_id + 1);
EXECUTE format('ALTER SEQUENCE seq_team_member_id RESTART WITH %s', max_team_member_id + 1);
EXECUTE format('ALTER SEQUENCE seq_rack_asset_id RESTART WITH %s', max_rack_asset_id + 1);
EXECUTE format('ALTER SEQUENCE seq_booking_id RESTART WITH %s', max_booking_id + 1);
END $$;

