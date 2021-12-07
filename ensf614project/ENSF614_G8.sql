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
    Title		    VARCHAR(100)	    NOT NULL,
    OpeningDate		DATE				NOT NULL,
    Description 	VARCHAR(1000) 	    NOT NULL,
    Runtime 	    INT 	            NOT NULL,
    MovieStatus     VARCHAR(50)         NOT NULL DEFAULT 'AVAILABLE',
    PRIMARY KEY (MovieID),
    FOREIGN KEY(MovieStatus) REFERENCES MOVIE_STATUS(MovieStatus),
    UNIQUE (Title, MovieStatus)
);


DROP TABLE IF EXISTS THEATRE;
CREATE TABLE THEATRE
(
    TheatreID	    INT	AUTO_INCREMENT  NOT NULL,
    TheatreName		VARCHAR(100)		    NOT NULL,
    Capacity		INT	 	    NOT NULL,
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

# SELECT COUNT(*) FROM SEAT_CHART;

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
    CreditCardNo     VARCHAR(20)        NOT NULL,
    Amount            INT                NOT NULL,
    PRIMARY KEY(PaymentID),
    FOREIGN KEY(TicketID) REFERENCES TICKET(TicketID)
);

SELECT SeatID FROM SEAT_INSTANCE WHERE SeatID IN (20,208)

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
    Fname 		    VARCHAR(20)			    NOT NULL,
    Lname 		    VARCHAR(20)			    NOT NULL,
    CreditCardNo    VARCHAR(20)                     ,
    MembershipStart DATE                    NOT NULL,
    Email           VARCHAR(50)                     ,
    Password        VARCHAR(20)             NOT NULL,
    PRIMARY KEY(UserID),
    UNIQUE (Email));

DROP USER IF EXISTS 'mock_user'@'localhost';
FLUSH PRIVILEGES;
CREATE USER 'mock_user'@'localhost' IDENTIFIED BY 'password';
GRANT ALL PRIVILEGES ON ensf614_movie_theatre. * TO 'mock_user'@'localhost';

