set GLOBAL time_zone = '+07:00';
set time_zone = '+07:00';

CREATE TABLE user (
      NIK VARCHAR(50) PRIMARY KEY,
      name VARCHAR(255) NOT NULL,
      email VARCHAR(255) UNIQUE NOT NULL,
      created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
      updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE book (
      isbn VARCHAR(50) PRIMARY KEY,
      title VARCHAR(255) NOT NULL,
      stock BIGINT NOT NULL CHECK (stock >= 0),
      created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
      updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE book_loans (
        id BIGINT AUTO_INCREMENT PRIMARY KEY,
        user VARCHAR(50) NOT NULL,
        book VARCHAR(50) NOT NULL,
        borrowed_time DATETIME NOT NULL,
        actual_return_time DATETIME DEFAULT NULL,
        created_at timestamp NULL DEFAULT CURRENT_TIMESTAMP,
        updated_at timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
        expected_return_time DATETIME DEFAULT NULL,

        CONSTRAINT `fk_book_loans_book` FOREIGN KEY (`book`) REFERENCES `book` (`isbn`) ON DELETE CASCADE,
        CONSTRAINT `fk_book_loans_user` FOREIGN KEY (`user`) REFERENCES `user` (`NIK`) ON DELETE CASCADE
);