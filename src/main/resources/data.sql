INSERT INTO SECTION (ID, NAME) VALUES ( 101, 'A');
INSERT INTO SECTION (ID, NAME) VALUES ( 102, 'B');

INSERT INTO SEAT (ID, SEAT_NUMBER, ALLOCATED, SECTION_ID) VALUES ( 01, 01, FALSE, 101 );
INSERT INTO SEAT (ID, SEAT_NUMBER, ALLOCATED, SECTION_ID) VALUES ( 02, 02, FALSE, 101  );
INSERT INTO SEAT (ID, SEAT_NUMBER, ALLOCATED, SECTION_ID) VALUES ( 03, 03, FALSE, 101  );
INSERT INTO SEAT (ID, SEAT_NUMBER, ALLOCATED, SECTION_ID) VALUES ( 04, 04, FALSE, 101  );

INSERT INTO SEAT (ID, SEAT_NUMBER, ALLOCATED, SECTION_ID) VALUES ( 05, 01, FALSE, 102 );
INSERT INTO SEAT (ID, SEAT_NUMBER, ALLOCATED, SECTION_ID) VALUES ( 06, 02, FALSE, 102 );
INSERT INTO SEAT (ID, SEAT_NUMBER, ALLOCATED, SECTION_ID) VALUES ( 07, 03, FALSE, 102  );
INSERT INTO SEAT (ID, SEAT_NUMBER, ALLOCATED, SECTION_ID) VALUES ( 08, 04, FALSE, 102  );

-- INSERT INTO SECTION_SEATS (SECTION_ID, SEATS_ID) VALUES ( 101, 01 );
-- INSERT INTO SECTION_SEATS (SECTION_ID, SEATS_ID) VALUES ( 101, 02 );
-- INSERT INTO SECTION_SEATS (SECTION_ID, SEATS_ID) VALUES ( 101, 03 );
-- INSERT INTO SECTION_SEATS (SECTION_ID, SEATS_ID) VALUES ( 101, 04 );
-- INSERT INTO SECTION_SEATS (SECTION_ID, SEATS_ID) VALUES ( 102, 05 );
-- INSERT INTO SECTION_SEATS (SECTION_ID, SEATS_ID) VALUES ( 102, 06 );
-- INSERT INTO SECTION_SEATS (SECTION_ID, SEATS_ID) VALUES ( 102, 07 );
-- INSERT INTO SECTION_SEATS (SECTION_ID, SEATS_ID) VALUES ( 102, 08 );
