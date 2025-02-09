CREATE TABLE role (
    id TINYINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE user (
    id CHAR(36),
    full_name VARCHAR(255) NOT NULL,
    dob DATE NOT NULL,
    gender ENUM('MALE', 'FEMALE') NOT NULL,
    phone_number VARCHAR(255) NOT NULL,
    address VARCHAR(255) NOT NULL,
    identification_number VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255),
    role_id TINYINT NOT NULL,
    is_active BOOLEAN,

    PRIMARY KEY (id),
    FOREIGN KEY (role_id) REFERENCES role(id)
);

CREATE TABLE author (
    id CHAR(36),
    name VARCHAR(255) NOT NULL,

    PRIMARY KEY (id)
);

CREATE TABLE category (
    code VARCHAR(3),
    name VARCHAR(255) NOT NULL,

    PRIMARY KEY (code)
);

CREATE TABLE document (
    id CHAR(36),
    document_code VARCHAR(255) NOT NULL UNIQUE,
    title VARCHAR(255) NOT NULL,
    topic VARCHAR(255),
    description TEXT,
    note TEXT,
    category VARCHAR(255),
    year_published INT,
    publisher VARCHAR(255),
    quantity INT,

    PRIMARY KEY (id),
    FOREIGN KEY (category) REFERENCES category(code)
);

CREATE TABLE document_copy (
    document_copy_code VARCHAR(255),
    document_id CHAR(36) NOT NULL,
    location VARCHAR(255) NOT NULL,
    status ENUM('AVAILABLE', 'NOT_AVAILABLE', 'BORROWED') NOT NULL,

    PRIMARY KEY (document_copy_code),
    FOREIGN KEY (document_id) REFERENCES document(id)
);

CREATE TABLE librarian (
    user_id CHAR(36),
    department VARCHAR(255),
    position VARCHAR(255),
    workplace VARCHAR(255),

    PRIMARY KEY (user_id),
    FOREIGN KEY (user_id) REFERENCES user(id)
);

CREATE TABLE reader (
    user_id CHAR(36),
    student_id VARCHAR(255) UNIQUE,

    PRIMARY KEY (user_id),
    FOREIGN KEY (user_id) REFERENCES user(id)
);

CREATE TABLE reader_card (
    id CHAR(36),
    user_id CHAR(36) NOT NULL,
    pin VARCHAR(255),
    issue_date DATE,
    expiry_period INT,
    status ENUM('REQUESTED', 'ACTIVE', 'REQUEST_EXTEND', 'EXPIRED', 'INACTIVE') NOT NULL,
    created_at DATETIME,
    updated_at DATETIME,

    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES reader(user_id)
);

CREATE TABLE reader_request (
    id CHAR(36),
    user_id CHAR(36) NOT NULL,
    status ENUM('REQUESTED', 'ACCEPTED', 'REJECTED', 'BORROWED', 'RETURNED', 'OVERDUE', 'CANCELLED') NOT NULL,
    date_borrowed DATE,
    date_due DATE,
    date_returned DATE,
    penalty_fee DOUBLE,
    notes TEXT,
    created_at DATETIME,
    updated_at DATETIME,

    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES reader(user_id)
);

CREATE TABLE document_author (
    document_id CHAR(36),
    author_id CHAR(36),

    PRIMARY KEY (document_id, author_id),
    FOREIGN KEY (document_id) REFERENCES document(id),
    FOREIGN KEY (author_id) REFERENCES author(id)
);

CREATE TABLE reader_bookshelf (
    user_id CHAR(36),
    document_copy_code VARCHAR(255),

    PRIMARY KEY (user_id, document_copy_code),
    FOREIGN KEY (user_id) REFERENCES reader(user_id),
    FOREIGN KEY (document_copy_code) REFERENCES document_copy(document_copy_code)
);

CREATE TABLE reader_request_detail (
    reader_request_id CHAR(36),
    document_copy_code VARCHAR(255),
    
    PRIMARY KEY (reader_request_id, document_copy_code),
    FOREIGN KEY (reader_request_id) REFERENCES reader_request(id),
    FOREIGN KEY (document_copy_code) REFERENCES document_copy(document_copy_code)
);