insert into "MEMBER" (MEMBER_ID, LOGIN_ID, PASSWORD) values (1, 'admin', '$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO/Vxd5DOi');
-- insert into "MEMBER" (MEMBER_ID, LOGIN_ID, PASSWORD) values (2, 'user', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC');

insert into authority (authority_name) values ('ROLE_USER');
insert into authority (authority_name) values ('ROLE_ADMIN');

-- insert into MEMBER_AUTHORITY (MEMBER_ID, AUTHORITY_NAME) values (1, 'ROLE_USER');
insert into MEMBER_AUTHORITY (MEMBER_ID, AUTHORITY_NAME) values (1, 'ROLE_ADMIN');
-- insert into MEMBER_AUTHORITY (MEMBER_ID, AUTHORITY_NAME) values (2, 'ROLE_USER');