CREATE TABLE user (
    id BIGINT NOT NULL, 
    name VARCHAR(255) CHARACTER SET UTF8MB4 NOT NULL,
    dob DATE NOT NULL,
    gender ENUM('MALE', 'FEMALE') NOT NULL,
    phone_number VARCHAR(15) NOT NULL,
    address VARCHAR(255) CHARACTER SET UTF8MB4 NOT NULL,
    identification_number VARCHAR(15) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(60) NOT NULL,
    role ENUM('READER', 'LIBRARIAN') NOT NULL,
    status ENUM('ACTIVE', 'INACTIVE') NOT NULL,

    PRIMARY KEY (id)
);

CREATE TABLE reader (
    user_id BIGINT NOT NULL UNIQUE,
    student_id VARCHAR(10) UNIQUE,

    PRIMARY KEY (user_id),
    FOREIGN KEY (user_id) REFERENCES user(id)
        ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE librarian (
    user_id BIGINT NOT NULL,
    department VARCHAR(255) CHARACTER SET UTF8MB4,
    position VARCHAR(255) CHARACTER SET UTF8MB4,
    workplace VARCHAR(255) CHARACTER SET UTF8MB4,

    PRIMARY KEY (user_id),
    FOREIGN KEY (user_id) REFERENCES user(id)
        ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE reader_card (
    id BIGINT NOT NULL,
    reader_id BIGINT NOT NULL,
    pin VARCHAR(60),
    issue_date DATE,
    expiry_date DATE,
    status ENUM('REQUESTED', 'ACTIVE', 'EXPIRED', 'REQUEST_EXTEND') NOT NULL,

    PRIMARY KEY (id),
    FOREIGN KEY (reader_id) REFERENCES reader(id)
        ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE document (
    id BIGINT NOT NULL,
    title VARCHAR(255) CHARACTER SET UTF8MB4 NOT NULL,
    topic VARCHAR(255) CHARACTER SET UTF8MB4,
    description TEXT CHARACTER SET UTF8MB4,
    note TEXT CHARACTER SET UTF8MB4,
    category VARCHAR(10) CHARACTER SET UTF8MB4,
    year_published INT,
    publisher VARCHAR(255) CHARACTER SET UTF8MB4,
    quantity INT NOT NULL,

    PRIMARY KEY (id)
);

CREATE TABLE author (
    id BIGINT NOT NULL,
    name VARCHAR(255) CHARACTER SET UTF8MB4 NOT NULL,
    
    PRIMARY KEY (id)
);

CREATE TABLE document_author (
    document_id BIGINT NOT NULL,
    author_id BIGINT NOT NULL,

    PRIMARY KEY (document_id, author_id),
    FOREIGN KEY (document_id) REFERENCES document(id)
        ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (author_id) REFERENCES author(id)
        ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE document_copy (
    document_id BIGINT NOT NULL,
    code VARCHAR(255) NOT NULL UNIQUE,
    location VARCHAR(255) CHARACTER SET UTF8MB4 NOT NULL,
    status ENUM('AVAILABLE', 'NOT_AVAILABLE', 'BORROWED') NOT NULL,

    PRIMARY KEY (document_id, code),
    FOREIGN KEY (document_id) REFERENCES document(id)
        ON DELETE CASCADE ON UPDATE CASCADE 
);

CREATE TABLE reader_bookshelf (
    reader_id BIGINT NOT NULL,
    document_copy_code VARCHAR(255) NOT NULL,

    PRIMARY KEY (reader_id, document_copy_code),
    FOREIGN KEY (reader_id) REFERENCES reader(id)
        ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (document_copy_code) REFERENCES document_copy(code)
        ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE reader_request (
    id BIGINT NOT NULL,
    reader_id BIGINT NOT NULL,
    status ENUM('REQUESTED', 'ACCEPTED', 'REJECTED', 'BORROWED', 'RETURNED', 'OVERDUE', 'CANCELLED') NOT NULL,
    date_borrowed DATE,
    date_due DATE,
    date_returned DATE,
    penalty_fee DECIMAL(10, 2) DEFAULT 0,
    notes TEXT CHARACTER SET UTF8MB4,

    PRIMARY KEY (id),
    FOREIGN KEY (reader_id) REFERENCES reader(id) 
        ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE reader_request_detail (
    reader_request_id BIGINT NOT NULL,
    document_copy_code VARCHAR(255) NOT NULL,

    PRIMARY KEY (reader_request_id, document_copy_code),
    FOREIGN KEY (reader_request_id) REFERENCES reader_request(id)
        ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (document_copy_code) REFERENCES document_copy(code)
        ON DELETE CASCADE ON UPDATE CASCADE
);

