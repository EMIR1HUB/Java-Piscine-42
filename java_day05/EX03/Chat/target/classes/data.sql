INSERT INTO chat.users (login, password)
VALUES
    ('jnidorin', 'rqk9Qffe'),
    ('abernita', 'WcJ5HbXp'),
    ('hamchur', 'HxSQGYQ1'),
    ('tlunchla', 'DA90fVaQ'),
    ('jleslee', '178iPnIu');

INSERT INTO chat.chatrooms (name, owner)
VALUES
    ('general', (SELECT id FROM chat.users WHERE login = 'jnidorin')),
    ('random', (SELECT id FROM chat.users WHERE login = 'jnidorin')),
    ('memes', (SELECT id FROM chat.users WHERE login = 'jnidorin')),
    ('musicBot', (SELECT id FROM chat.users WHERE login = 'jnidorin')),
    ('art', (SELECT id FROM chat.users WHERE login = 'jnidorin'));

INSERT INTO chat.messages (author, chatroom, text, datetime)
VALUES
    ((SELECT id FROM chat.users WHERE login = 'jnidorin'), 1, 'This is general channel. Feel free to talk!', (SELECT NOW()::timestamp)),
    ((SELECT id FROM chat.users WHERE login = 'jnidorin'), 2, 'This is random channel. Feel free to talk!', (SELECT NOW()::timestamp)),
    ((SELECT id FROM chat.users WHERE login = 'jnidorin'), 3, 'This is memes channel. Feel free to talk!', (SELECT NOW()::timestamp)),
    ((SELECT id FROM chat.users WHERE login = 'jnidorin'), 4, 'This is musicBot channel. Feel free to talk!', (SELECT NOW()::timestamp)),
    ((SELECT id FROM chat.users WHERE login = 'jnidorin'), 5, 'This is art channel. Feel free to talk!', (SELECT NOW()::timestamp));