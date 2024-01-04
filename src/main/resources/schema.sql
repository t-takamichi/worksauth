DROP TABLE IF EXISTS SNSUSER;

CREATE TABLE SNSUSER (
    ID INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    NAME VARCHAR(255) NOT NULL,
    EMAIL VARCHAR(500) UNIQUE NOT NULL,
    PASSWORD VARCHAR(255) NOT NULL,
    CREATED_DATETIME DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    UPDATED_DATETIME DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
);