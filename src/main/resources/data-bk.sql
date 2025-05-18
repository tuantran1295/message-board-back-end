INSERT INTO users (id, username, email, password)
VALUES (1, 'testuser', 'test1@email.com', '$2a$10$FqqEVlNXj8k9DLkLhE21ceDG.SdxvpjLKASDwQWiRRDaa8enjeKDm');
INSERT INTO users (id, username, email, password)
VALUES (2, 'alice', 'alice@example.com', '$2a$12$WPx1U595MA28cajxSO5M4uEgUf4LGE7Eu98Fl3sFWo0IhtkkFCAfu');
INSERT INTO users (id, username, email, password)
VALUES (2, 'alice', 'alice@example.com', '$2a$12$WPx1U595MA28cajxSO5M4uEgUf4LGE7Eu98Fl3sFWo0IhtkkFCAfu');
INSERT INTO users (id, username, email, password)
VALUES (3, 'bob', 'bob@example.com', '$2a$12$WPx1U595MA28cajxSO5M4uEgUf4LGE7Eu98Fl3sFWo0IhtkkFCAfu');
INSERT INTO users (id, username, email, password)
VALUES (4, 'charlie', 'charlie@example.com', '$2a$12$WPx1U595MA28cajxSO5M4uEgUf4LGE7Eu98Fl3sFWo0IhtkkFCAfu');

INSERT INTO message (id, content, created_at, parent_id, user_id)
VALUES (1, 'Hello, this is Alice''s first message!', CURRENT_TIMESTAMP, NULL, 1);
INSERT INTO message (id, content, created_at, parent_id, user_id)
VALUES (2, 'Bob here, checking in!', CURRENT_TIMESTAMP, NULL, 2);
INSERT INTO message (id, content, created_at, parent_id, user_id)
VALUES (3, 'Alice replies to Bob.', CURRENT_TIMESTAMP, 2, 1);
INSERT INTO message (id, content, created_at, parent_id, user_id)
VALUES (4, 'Charlie says hi to everyone.', CURRENT_TIMESTAMP, NULL, 3);
INSERT INTO message (id, content, created_at, parent_id, user_id)
VALUES (5, 'Alice replies to herself.', CURRENT_TIMESTAMP, 1, 1);