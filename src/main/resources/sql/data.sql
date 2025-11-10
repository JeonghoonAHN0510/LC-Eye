-- --------------------------------------- company ----------------------------------------
INSERT INTO company (cname, caddress, ctel, cowner, createdate) VALUES ("LC-Eye", "인천시 부평구 경원대로 1366", "032-521-8889", "유재석", now());

-- --------------------------------------- member ----------------------------------------
INSERT INTO member (mname, cno, mid, mpwd, mrole, memail, mphone, createdate) VALUES ("유재석", "10001", "admin", "1234", "admin", "admin@lceye.kr", "032-521-8889", now());