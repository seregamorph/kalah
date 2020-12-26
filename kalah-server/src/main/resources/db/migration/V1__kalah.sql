
CREATE TABLE game (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    -- 0 - undefined (first move)
    current_player INT NOT NULL DEFAULT 0,
    pits varchar(100) NOT NULL,
    version INT NOT NULL DEFAULT 0
);

