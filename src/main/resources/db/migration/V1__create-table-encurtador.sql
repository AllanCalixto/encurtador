CREATE TABLE urlencurtador(
    id BIGINT NOT NULL AUTO_INCREMENT,
    alias VARCHAR(50),
    url VARCHAR(100) NOT NULL,
    url_gerada VARCHAR(100),
    acessos BIGINT,

    PRIMARY KEY (id)
);