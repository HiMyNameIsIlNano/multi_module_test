drop table USER_COMMENT if exists;
drop table USER_PROFILE if exists;

-- Force Flyway migration at every restart
drop table SCHEMA_VERSION if exists;