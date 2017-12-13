create table USER_COMMENT (
  id bigint generated by default as identity,
  is_active boolean,
  last_update date,
  updated_by varchar(255),
  valid_from date,
  valid_to date,
  comment_text clob,
  comment_topic varchar(255),
  user_id_ref bigint,
  primary key (id)
);
