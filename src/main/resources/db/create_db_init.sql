CREATE TABLE T_TEAM(
    id BIGSERIAL,
    name varchar(20),
    product varchar(20),
    created_at TIMESTAMP DEFAULT now(),
    modified_at TIMESTAMP DEFAULT now(),
    default_location varchar(20) DEFAULT 'LISBON',
    PRIMARY KEY (id)
);
CREATE SEQUENCE IF NOT EXISTS SEQ_TEAM_ID;

CREATE TABLE T_RACK(
    id BIGSERIAL,
    serial_number text NOT NULL UNIQUE,
    team_id BIGINT NOT NULL,
    default_location varchar(20),
    status varchar(20) CHECK ( status = 'AVAILABLE' OR status = 'BOOKED' OR status = 'UNAVAILABLE' ),
    created_at TIMESTAMP DEFAULT now(),
    modified_at TIMESTAMP DEFAULT now(),
    PRIMARY KEY (id),
    FOREIGN KEY (team_id) REFERENCES T_TEAM(id)
);
CREATE SEQUENCE IF NOT EXISTS SEQ_RACK_ID;

CREATE TABLE T_RACK_ASSET(
     id BIGSERIAL,
     asset_tag varchar(20) NOT NULL,
     rack_id BIGINT NOT NULL,
     PRIMARY KEY (id),
     FOREIGN KEY (rack_id) REFERENCES T_RACK(id)
);
CREATE SEQUENCE IF NOT EXISTS SEQ_RACK_ASSET_ID;

CREATE TABLE TEAM_MEMBER(
    id BIGSERIAL,
    team_id BIGINT NOT NULL,
    ctw_id varchar(10) NOT NULL,
    name varchar(20) NOT NULL,
    created_at TIMESTAMP DEFAULT now(),
    modified_at TIMESTAMP DEFAULT now(),
    PRIMARY KEY (id),
    FOREIGN KEY (team_id) REFERENCES T_TEAM(id)
);
CREATE SEQUENCE IF NOT EXISTS SEQ_TEAM_MEMBER_ID;

CREATE TABLE T_BOOKING(
    id BIGSERIAL,
    rack_id BIGINT NOT NULL,
    requester_id BIGINT NOT NULL,
    book_from TIMESTAMP DEFAULT now(),
    book_to TIMESTAMP DEFAULT now(),
    created_at TIMESTAMP DEFAULT now(),
    modified_at TIMESTAMP DEFAULT now(),
    PRIMARY KEY (id),
    FOREIGN KEY (rack_id) REFERENCES T_RACK(id),
    FOREIGN KEY (requester_id) REFERENCES TEAM_MEMBER(id)
);
CREATE SEQUENCE IF NOT EXISTS SEQ_BOOKING_ID;



