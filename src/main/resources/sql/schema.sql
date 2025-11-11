alter table company auto_increment = 10001;
alter table member auto_increment = 20001;
alter table unitgroup auto_increment = 30001;
alter table units auto_increment = 40001;

-- 추후 테이블 생성 시, 활성화하며 ALTER 추가
-- alter table flow auto_increment = 100001;
-- alter table process_info auto_increment = 200001;
-- alter table process_exchange auto_increment = 100000001;
-- alter table project auto_increment = 300001;
-- alter table project_exchange auto_increment = 200000001;
-- alter table project_result auto_increment = 300000001;