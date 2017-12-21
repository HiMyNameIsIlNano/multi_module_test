CREATE TABLE USER_TO_FRIEND (
	USER_ID bigint NOT NULL,
	FRIEND_ID bigint NOT NULL, primary key (USER_ID, FRIEND_ID)
);

alter table USER_TO_FRIEND add constraint FK_USER_REF foreign key (USER_ID) references USER_PROFILE;
alter table USER_TO_FRIEND add constraint FK_FRIEND_REF foreign key (USER_ID) references USER_PROFILE;