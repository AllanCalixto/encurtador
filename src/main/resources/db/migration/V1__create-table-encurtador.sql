CREATE TABLE encurtador(
    id BIGINT NOT NULL AUTO_INCREMENT,
    alias VARCHAR(50),
    url VARCHAR(100) NOT NULL,
    url_gerada VARCHAR(100),

    PRIMARY KEY (id)
);