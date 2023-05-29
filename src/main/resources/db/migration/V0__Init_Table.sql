-- authentication --
CREATE TABLE IF NOT EXISTS authentication(
    authentication_id BIGSERIAL                             NOT NULL
        CONSTRAINT authentication_pk
            PRIMARY KEY,
    username          text                                  NOT NULL,
    password          text                                  NOT NULL,
    email             text                                  NOT NULL,
    roles             text
);

-- archives --
CREATE TABLE IF NOT EXISTS archives(
    archive_id BIGSERIAL                                    NOT NULL
        CONSTRAINT archives_pk
            PRIMARY KEY ,
    title      text                                         NOT NULL,
    body       text                                         NOT NULL,
    author     text                                         NOT NULL,
    created_at timestamp with time zone                     NOT NULL
);
