DROP DATABASE IF EXISTS ensf614_movie_theatre;
CREATE DATABASE ensf614_movie_theatre;
USE ensf614_movie_theatre;

DROP TABLE IF EXISTS MOVIE_STATUS;
CREATE TABLE MOVIE_STATUS
(
    MovieStatus VARCHAR(50) NOT NULL,
    PRIMARY KEY(MovieStatus)
);

INSERT INTO MOVIE_STATUS
VALUES
    ('AVAILABLE'),
    ('CANCELLED');

DROP TABLE IF EXISTS MOVIE;
CREATE TABLE MOVIE
(
    MovieID		    INT AUTO_INCREMENT  NOT NULL,
    Title		    VARCHAR(20)		    NOT NULL,
    OpeningDate		DATE				NOT NULL,
    Description 	VARCHAR(200) 	    NOT NULL,
    Runtime 	    INT 	            NOT NULL,
    MovieStatus     VARCHAR(50)         NOT NULL DEFAULT 'AVAILABLE',
    PRIMARY KEY (MovieID),
    FOREIGN KEY(MovieStatus) REFERENCES MOVIE_STATUS(MovieStatus),
    UNIQUE (Title, MovieStatus)
);

SELECT * FROM MOVIE WHERE OpeningDate > '2021-01-01' AND MovieStatus = 'AVAILABLE';

SELECT * FROM MOVIE;

INSERT INTO MOVIE(Title, OpeningDate, Description, Runtime)
VALUES('Titanic', '2022-01-01', 'Titanic, the movie', 194),
      ('LOTR', '2021-12-12', 'LOTR, the movie', 145);


DROP TABLE IF EXISTS THEATRE;
CREATE TABLE THEATRE
(
    TheatreID	    INT	AUTO_INCREMENT  NOT NULL,
    TheatreName		VARCHAR(20)		    NOT NULL,
    Capacity		VARCHAR(20)	 	    NOT NULL,
    PRIMARY KEY(TheatreID)
);

INSERT INTO THEATRE(TheatreName, Capacity)
VALUES  ('Red Theatre', 70),
        ('Blue Theatre', 70),
        ('Black Theatre', 70);

SELECT * FROM THEATRE;

DROP TABLE IF EXISTS SHOWTIME;
CREATE TABLE SHOWTIME
(
    ShowtimeID		INT AUTO_INCREMENT	NOT NULL,
    MovieID		    INT				    NOT NULL,
    TheatreID		INT		            NOT NULL,
    StartTime		TIME			    NOT NULL,
    EndTime		    TIME			    NOT NULL,
    ShowDate        DATE                NOT NULL,
    PRIMARY KEY(ShowtimeID),
    FOREIGN KEY(MovieID) REFERENCES MOVIE(MovieID),
    FOREIGN KEY(TheatreID) REFERENCES THEATRE(TheatreID),
    UNIQUE (MovieID, TheatreID, StartTime, ShowDate)
);

INSERT INTO SHOWTIME(MovieID, TheatreID, StartTime, EndTime, ShowDate)
VALUES (1,1,'08:00:00', '11:14:00', '2022-01-01'),
       (1,1,'12:00:00', '15:14:00', '2022-01-01'),
       (1,1,'16:00:00', '19:14:00', '2022-01-01'),
       (1,1,'08:00:00', '11:14:00', '2022-01-02'),
       (1,1,'12:00:00', '15:14:00', '2022-01-02'),
       (1,1,'16:00:00', '19:14:00', '2022-01-02'),
       (2,2,'08:00:00', '11:14:00', '2022-01-01'),
       (2,2,'12:00:00', '15:14:00', '2022-01-01'),
       (2,2,'16:00:00', '19:14:00', '2022-01-01'),
       (2,2,'08:00:00', '11:14:00', '2022-01-02'),
       (2,2,'12:00:00', '15:14:00', '2022-01-02'),
       (2,2,'16:00:00', '19:14:00', '2022-01-02');

SELECT Title, TheatreName, ShowtimeID,StartTime, EndTime, ShowDate
FROM SHOWTIME S
         JOIN MOVIE M on S.MovieID = M.MovieID
         JOIN THEATRE T on S.TheatreID = T.TheatreID
WHERE M.Title = 'Titanic'
  AND ShowDate = '2022-01-02'
  AND MovieStatus = 'AVAILABLE';

DROP TABLE IF EXISTS SEAT_ROW;
CREATE TABLE SEAT_ROW
(
    SeatRow INT NOT NULL,
    PRIMARY KEY(SeatRow)
);

INSERT INTO SEAT_ROW(SeatRow)
VALUES (1),
       (2),
       (3),
       (4),
       (5),
       (6),
       (7);

DROP TABLE IF EXISTS SEAT_COL;
CREATE TABLE SEAT_COL
(
    SeatCol CHAR NOT NULL,
    PRIMARY KEY(SeatCol)
);

INSERT INTO SEAT_COL(SeatCol)
VALUES ('A'),
       ('B'),
       ('C'),
       ('D'),
       ('E'),
       ('F'),
       ('G'),
       ('H'),
       ('I'),
       ('J');


DROP TABLE IF EXISTS SEAT_CHART;
CREATE TABLE SEAT_CHART
(
    SeatID			INT	AUTO_INCREMENT	NOT NULL,
    SeatRow         INT                 NOT NULL,
    SeatCol         CHAR                NOT NULL,
    TheatreID		INT				    NOT NULL,
    PRIMARY KEY(SeatID),
    FOREIGN KEY(TheatreID) REFERENCES THEATRE(TheatreID),
    FOREIGN KEY(SeatRow) REFERENCES SEAT_ROW(SeatRow),
    FOREIGN KEY(SeatCol) REFERENCES SEAT_COL(SeatCol),
    UNIQUE (SeatRow, SeatCol, TheatreID)
);

#  Red Theatre
INSERT INTO SEAT_CHART(SeatRow, SeatCol, TheatreID)
SELECT SeatRow, SeatCol, 1 FROM SEAT_COL
                                    CROSS JOIN SEAT_ROW;

#  Blue Theatre
INSERT INTO SEAT_CHART(SeatRow, SeatCol, TheatreID)
SELECT SeatRow, SeatCol, 2 FROM SEAT_COL
                                    CROSS JOIN SEAT_ROW;

#  Black Theatre
INSERT INTO SEAT_CHART(SeatRow, SeatCol, TheatreID)
SELECT SeatRow, SeatCol, 3 FROM SEAT_COL
                                    CROSS JOIN SEAT_ROW;

SELECT COUNT(*) FROM SEAT_CHART;

DROP TABLE IF EXISTS SEAT_INSTANCE;
CREATE TABLE SEAT_INSTANCE
(
    SeatInstanceID 	INT AUTO_INCREMENT	NOT NULL,
    SeatID          INT                 NOT NULL,
    ShowtimeID      INT                 NOT NULL,
    Presale         BOOLEAN             DEFAULT false,
    Occupied        BOOLEAN             DEFAULT false,
    PRIMARY KEY(SeatInstanceID),
    FOREIGN KEY(SeatID) REFERENCES SEAT_CHART(SeatID),
    FOREIGN KEY(ShowtimeID) REFERENCES SHOWTIME(ShowtimeID),
    UNIQUE (SeatID, ShowtimeID)
);

#  Quick way to insert for theatre 1
INSERT INTO SEAT_INSTANCE(SeatID, ShowtimeID)
SELECT SeatID, ShowtimeID FROM (
                                   SELECT ShowtimeID FROM SHOWTIME
                                                              JOIN THEATRE T on SHOWTIME.TheatreID = T.TheatreID
                                   WHERE T.TheatreID = 1) ST
                                   CROSS JOIN (
    SELECT SeatID FROM SEAT_CHART
    WHERE TheatreID = 1
) CJ;

INSERT INTO SEAT_INSTANCE(SeatID, ShowtimeID)
SELECT SeatID, ShowtimeID FROM (
                                   SELECT ShowtimeID FROM SHOWTIME
                                                              JOIN THEATRE T on SHOWTIME.TheatreID = T.TheatreID
                                   WHERE T.TheatreID = 2
                                     AND ShowtimeID = 1
                               ) ST
                                   CROSS JOIN (
    SELECT SeatID FROM SEAT_CHART
    WHERE TheatreID = 2
) CJ;

SELECT * FROM SHOWTIME;


#  should be 840
SELECT COUNT(*) FROM SEAT_INSTANCE;


DROP TABLE IF EXISTS TICKET_STATUS;
CREATE TABLE TICKET_STATUS
(
    TicketStatus VARCHAR(50) NOT NULL,
    PRIMARY KEY(TicketStatus)
);

INSERT INTO TICKET_STATUS
VALUES
    ('SOLD'),
    ('AVAILABLE'),
    ('REFUNDED');



DROP TABLE IF EXISTS TICKET;
CREATE TABLE TICKET
(
    TicketID 	     INT AUTO_INCREMENT	NOT NULL,
    SeatInstanceID   INT                NOT NULL,
    Price            INT                NOT NULL,
    TicketStatus     VARCHAR(50)        DEFAULT 'AVAILABLE',
    Email            VARCHAR(50)                ,
    TimePurchase     TIMESTAMP          DEFAULT NOW(),
    PRIMARY KEY(TicketID),
    FOREIGN KEY(SeatInstanceID) REFERENCES SEAT_INSTANCE(SeatInstanceID),
    UNIQUE(SeatInstanceID, TicketStatus)
);

DROP TABLE IF EXISTS PAYMENT;
CREATE TABLE PAYMENT
(
    PaymentID 	     INT AUTO_INCREMENT	NOT NULL,
    TicketID         INT                NOT NULL,
    CreditCardNo     VARCHAR(16)        NOT NULL,
    Amount            INT                NOT NULL,
    PRIMARY KEY(PaymentID),
    FOREIGN KEY(TicketID) REFERENCES TICKET(TicketID)
);

SELECT * FROM SEAT_INSTANCE;


DROP TABLE IF EXISTS COUPONS;
CREATE TABLE COUPONS
(
    CouponCode		VARCHAR(15)			    NOT NULL,
    CouponValue	    INT		                NOT NULL,
    TicketID        INT                     NOT NULL,
    ExpiryDate      DATE                    NOT NULL,
    PRIMARY KEY(CouponCode),
    FOREIGN KEY(TicketID) REFERENCES TICKET(TicketID));

DROP TABLE IF EXISTS REGISTERED_USERS;
CREATE TABLE REGISTERED_USERS
(
    UserID			INT AUTO_INCREMENT		NOT NULL,
    Username		VARCHAR(20)			    NOT NULL,
    Fname 		    VARCHAR(20)			    NOT NULL,
    Lname 		    VARCHAR(20)			    NOT NULL,
    CreditCardNo    VARCHAR(16)                     ,
    MembershipStart DATE                    NOT NULL,
    Email           VARCHAR(50)                     ,
    Password        VARCHAR(20)             NOT NULL,
    PRIMARY KEY(UserID),
    UNIQUE (Username),
    UNIQUE (Email));

INSERT INTO REGISTERED_USERS(Username, Fname, Lname, CreditCardNo, MembershipStart, Email, Password)
VALUES ('aleakos', 'Alex', 'Leakos', '000000000000000', '2021-11-11', 'al@test.com', 'testpassword');


#  Example to be able to get seat instance
SELECT TheatreName, SC.SeatRow, SC.SeatCol FROM SHOWTIME ST
                                                    JOIN THEATRE T on ST.TheatreID = T.TheatreID
                                                    JOIN SEAT_INSTANCE SI on ST.ShowtimeID = SI.ShowtimeID
                                                    JOIN SEAT_CHART SC on SI.SeatID = SC.SeatID
                                                    JOIN MOVIE M on ST.MovieID = M.MovieID
WHERE Title = 'Titanic'
  AND TheatreName = 'Red Theatre'
  AND ST.ShowDate = '2022-01-01'
  AND ST.StartTime = '08:00'
  AND MovieStatus = 'AVAILABLE'
ORDER BY SeatRow, SeatCol;

#  Example to be able to get seat instance
SELECT SeatInstanceID, SC.SeatCol, SC.SeatRow, Presale, Occupied FROM SHOWTIME ST
                                                                          JOIN THEATRE T on ST.TheatreID = T.TheatreID
                                                                          JOIN SEAT_INSTANCE SI on ST.ShowtimeID = SI.ShowtimeID
                                                                          JOIN SEAT_CHART SC on SI.SeatID = SC.SeatID
                                                                          JOIN MOVIE M on ST.MovieID = M.MovieID
WHERE M.MovieID = 1
  AND T.TheatreID = 1
  AND ST.ShowtimeID = 3
  AND MovieStatus = 'AVAILABLE'
ORDER BY SeatCol, SeatRow;

SELECT * FROM SHOWTIME
                  JOIN MOVIE M
                       ON SHOWTIME.MovieID = M.MovieID
WHERE Title = 'Titanic'
  AND MovieStatus = 'AVAILABLE';


SELECT Title, ShowDate, StartTime FROM SHOWTIME ST
                                           JOIN MOVIE M on ST.MovieID = M.MovieID
WHERE ShowDate = '2022-01-01'
  AND StartTime > '10:00:00'
  AND MovieStatus = 'AVAILABLE';

DROP USER IF EXISTS 'mock_user'@'localhost';
FLUSH PRIVILEGES;

CREATE USER 'mock_user'@'localhost' IDENTIFIED BY 'password';

GRANT ALL PRIVILEGES ON ensf614_movie_theatre. * TO 'mock_user'@'localhost';


#  START  -------------------------- Adding Tickets - SEQUENCE ------------------------------
UPDATE SEAT_INSTANCE SET Occupied = TRUE
WHERE ShowtimeID = 1 AND SeatInstanceID = 1;

# must match seat instance id above
INSERT INTO TICKET(SeatInstanceID, Price, TicketStatus, Email)
VALUES (1, 2000, 'SOLD', 'test@email.com');

# must match seat instance id
INSERT INTO PAYMENT (TicketID, CreditCardNo, Amount)
VALUES (
           (SELECT TicketID
            FROM TICKET
            WHERE SeatInstanceID = 1
           ), '111111111111', 2000
       );

UPDATE SEAT_INSTANCE SET Occupied = TRUE
WHERE ShowtimeID = 1 AND SeatInstanceID = 2;

# must match seat instance id above
INSERT INTO TICKET(SeatInstanceID, Price, TicketStatus, Email)  VALUES (2, 2000, 'SOLD', 'test@email.com');

# must match seat instance id
INSERT INTO PAYMENT (TicketID, CreditCardNo, Amount)
VALUES (
           (SELECT TicketID
            FROM TICKET
            WHERE SeatInstanceID = 2
           ), '222222222222', 2000
       );

SELECT * FROM TICKET;

SELECT * FROM PAYMENT;

#  END --------------------------Adding Tickets - SEQUENCE ------------------------------



#  START  -------------------------- INSERT SHOWTIMES - SEQUENCE ------------------------------

INSERT INTO MOVIE(Title, OpeningDate, Description, Runtime)
VALUES('TEST Movie', '2021-01-01', 'Titanic, the movie', 100);

# would get theatre ID and movie ID from objects selected and time objets auto generated
INSERT INTO SHOWTIME(MovieID, TheatreID, StartTime, EndTime, ShowDate)
VALUES (
           (SELECT MovieID
            FROM MOVIE
            WHERE Title = 'TEST Movie'
              AND MovieStatus = 'AVAILABLE'
           ),
           (SELECT TheatreID
            FROM THEATRE
            WHERE TheatreName = 'Black Theatre'
           ),
           '08:00:00',
           '11:14:00',
           '2021-02-02');

INSERT INTO SEAT_INSTANCE(SeatID, ShowtimeID)
SELECT SeatID, ShowtimeID FROM (
                                   SELECT ShowtimeID FROM SHOWTIME
                                                              JOIN THEATRE T on SHOWTIME.TheatreID = T.TheatreID
                                   WHERE T.TheatreID = (SELECT TheatreID
                                                        FROM THEATRE
                                                        WHERE TheatreName = 'Black Theatre')) ST
                                   CROSS JOIN (
    SELECT SeatID FROM SEAT_CHART
    WHERE TheatreID = (
        SELECT TheatreID
        FROM THEATRE
        WHERE TheatreName = 'Black Theatre')
) CJ;


SELECT * FROM MOVIE WHERE MovieStatus = 'AVAILABLE';

SELECT * FROM SHOWTIME;

SELECT * FROM SEAT_INSTANCE WHERE ShowtimeID = 13;

#  END  -------------------------- INSERT SHOWTIMES - SEQUENCE ------------------------------


#  START  -------------------------- REFUND TICKET - SEQUENCE ------------------------------

#return ticketID to insert for coupon later
SELECT TicketID FROM TICKET WHERE TicketStatus = 'SOLD'
                              AND SeatInstanceID = 1;

#  updates the ticket status -
UPDATE TICKET SET TicketStatus = 'REFUNDED'
WHERE SeatInstanceID = 1 AND TicketStatus = 'SOLD';

# updates the seat instance - make presale also false by default
UPDATE SEAT_INSTANCE
SET Occupied = false, Presale = false
WHERE SeatInstanceID = 1;

# conditionally execute - value calculated by ticket price - code auto generated, doesn't check for collisions
INSERT INTO COUPONS(CouponCode, CouponValue, TicketID, ExpiryDate)
VALUES((SELECT LEFT(MD5(RAND()), 15)), 2000, 1, NOW() + INTERVAL 1 YEAR),
      ('AAAAAAAAAAAA', 200000, 1, NOW() + INTERVAL 1 YEAR),
      ('BBBBBBBBBBBB', 20000, 1, NOW() + INTERVAL 1 YEAR),
      ('CCCCCCCCCCCC', 1500, 1, NOW() + INTERVAL 1 YEAR);



#  END  -------------------------- INSERT SHOWTIMES - SEQUENCE ------------------------------




#  START  -------------------------- CANCEL MOVIE ------------------------------

UPDATE MOVIE SET MovieStatus = 'CANCELLED' WHERE MovieID = 1;

SELECT * FROM MOVIE;

SELECT * FROM MOVIE WHERE MovieStatus = 'AVAILABLE';

SELECT M.Title, SHOWTIME.* FROM SHOWTIME
                                    JOIN MOVIE M on SHOWTIME.MovieID = M.MovieID
WHERE MovieStatus = 'AVAILABLE';

SELECT ShowDate, StartTime, Price
FROM SHOWTIME
         JOIN SEAT_INSTANCE SI on SHOWTIME.ShowtimeID = SI.ShowtimeID
         JOIN TICKET T on SI.SeatInstanceID = T.SeatInstanceID
WHERE TicketID = 1

SELECT * FROM SHOWTIME;

SELECT S.* FROM SHOWTIME S
                    JOIN MOVIE M ON S.MovieID = M.MovieID
WHERE M.MovieStatus = 'AVAILABLE';

SELECT * FROM MOVIE;

SELECT * FROM REGISTERED_USERS;

SELECT * FROM TICKET;

SELECT * FROM COUPONS;