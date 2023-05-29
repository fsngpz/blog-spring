-- authentication --
CREATE TABLE IF NOT EXISTS authentication(
    id BIGSERIAL PRIMARY KEY,
    username text NOT NULL,
    password text NOT NULL,
    email text NOT NULL,
    roles text
);
