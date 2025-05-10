CREATE TABLE public.users (
                              id BIGINT PRIMARY KEY,
                              name VARCHAR(255),
                              email VARCHAR(255) UNIQUE,
                              phone_number VARCHAR(20),
                              password VARCHAR(255),
                              enabled BOOLEAN,
                              created_at TIMESTAMP
);

CREATE TABLE public.groups (
                               id BIGINT PRIMARY KEY,
                               name VARCHAR(255),
                               user_id BIGINT,
                               created_at TIMESTAMP,
                               FOREIGN KEY (user_id) REFERENCES public.users(id)
);

CREATE TABLE public.expenses (
                                 id SERIAL PRIMARY KEY,
                                 description VARCHAR(255),
                                 total DOUBLE PRECISION,
                                 total_by_user DOUBLE PRECISION,
                                 group_id BIGINT,
                                 date TIMESTAMP,
                                 FOREIGN KEY (group_id) REFERENCES public.groups(id)
);

CREATE TABLE public.participants (
                                     id SERIAL PRIMARY KEY,
                                     user_id BIGINT,
                                     group_id BIGINT,
                                     date TIMESTAMP,
                                     FOREIGN KEY (user_id) REFERENCES public.users(id),
                                     FOREIGN KEY (group_id) REFERENCES public.groups(id)
);
