insert into STICKER (name, description, image, status, price, level, created_at, updated_at) values ('팽귄', '귀여운 팽귄 스티커', 'https://cdn.pixabay.com/photo/2016/03/31/19/58/avatar-1295429_960_720.png', 'ACTIVE', 10, 1, now(), now());
insert into STICKER (name, description, image, status, price, level, created_at, updated_at) values ('고양이', '귀여운 고양이 스티커', 'https://cdn.pixabay.com/photo/2016/03/31/19/58/avatar-1295429_960_720.png', 'ACTIVE', 10, 1, now(), now());
insert into WALLET (coin, exp, created_at, updated_at, status) values (1000, 0, now(), now(), 'ACTIVE');
insert into MEMBER (username, email, role, wallet_id) values ('admin', 'admin@gmail.com', 'ADMIN', 1);
