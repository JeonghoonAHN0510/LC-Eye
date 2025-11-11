-- --------------------------------------- company ----------------------------------------
INSERT INTO company (cno, cname, caddress, ctel, cowner, createdate, updatedate)
VALUES
-- ①
(10001, 'LC-Eye', '인천시 부평구 경원대로 1366', '032-521-8889', '유재석', now(), now()),
-- ② 가상회사 A
(10002, 'GreenMetrics', '서울특별시 마포구 독막로 324', '02-000-0000', '김태경', now(), now()),

-- ③ 가상회사 B
(10003, 'EcoBalance', '부산광역시 해운대구 센텀서로 45', '051-000-0000', '박지현', now(), now());

-- --------------------------------------- member ----------------------------------------
INSERT INTO member (mno, mname, cno, mid, mpwd, mrole, memail, mphone, createdate, updatedate)
VALUES
-- ① LC-Eye (회사번호 10001)
(20001, '유재석', 10001, 'admin', '1234', 'ADMIN', 'admin@lceye.kr', '032-521-8889', now(), now()),

-- ②~④ GreenMetrics (회사번호 10002)
(20002, '김태경', 10002, 'kmanager', 'gm2025', 'MANAGER', 'kim@greenmetrics.kr', '02-723-1145', now(), now()),
(20003, '박성민', 10002, 'smin', 'pw123', 'WORKER', 'park@greenmetrics.kr', '02-723-1166', now(), now()),
(20004, '이하나', 10002, 'hana', 'pw234', 'WORKER', 'hana@greenmetrics.kr', '02-723-1177', now(), now()),

-- ⑤~⑦ EcoBalance (회사번호 10003)
(20005, '박지현', 10003, 'managerpjh', 'eco2025', 'MANAGER', 'park@ecobalance.kr', '051-981-2254', now(), now()),
(20006, '정수현', 10003, 'worker1', 'pw345', 'WORKER', 'jeong@ecobalance.kr', '051-981-2255', now(), now()),
(20007, '최유진', 10003, 'worker2', 'pw456', 'WORKER', 'choi@ecobalance.kr', '051-981-2256', now(), now());

-- --------------------------------------- unitgroup ----------------------------------------
INSERT INTO unitgroup (ugname, uguuid, createdate, updatedate) VALUES ('Units of person*length', '11d161f0-37e3-4d49-bf7a-ff4f31a9e5c7', now(), now());
INSERT INTO unitgroup (ugname, uguuid, createdate, updatedate) VALUES ('Units of length*time', '326eb58b-e5b3-4cea-b45a-2398c25109f8', now(), now());
INSERT INTO unitgroup (ugname, uguuid, createdate, updatedate) VALUES ('Units of person*time', '4a4f6060-85a7-3ad8-961b-00844a2dfa96', now(), now());
INSERT INTO unitgroup (ugname, uguuid, createdate, updatedate) VALUES ('Units of mass*time', '59f191d6-5dd3-4553-af88-1a32accfe308', now(), now());
INSERT INTO unitgroup (ugname, uguuid, createdate, updatedate) VALUES ('Units of items', '5beb6eed-33a9-47b8-9ede-1dfe8f679159', now(), now());
INSERT INTO unitgroup (ugname, uguuid, createdate, updatedate) VALUES ('Units of mass*length', '838aaa21-0117-11db-92e3-0800200c9a66', now(), now());
INSERT INTO unitgroup (ugname, uguuid, createdate, updatedate) VALUES ('Units of length', '838aaa22-0117-11db-92e3-0800200c9a66', now(), now());
INSERT INTO unitgroup (ugname, uguuid, createdate, updatedate) VALUES ('Units of energy', '93a60a57-a3c8-11da-a746-0800200c9a66', now(), now());
INSERT INTO unitgroup (ugname, uguuid, createdate, updatedate) VALUES ('Units of volume', '93a60a57-a3c8-12da-a746-0800200c9a66', now(), now());
INSERT INTO unitgroup (ugname, uguuid, createdate, updatedate) VALUES ('Units of radioactivity', '93a60a57-a3c8-16da-a746-0800200c9a66', now(), now());
INSERT INTO unitgroup (ugname, uguuid, createdate, updatedate) VALUES ('Units of area', '93a60a57-a3c8-18da-a746-0800200c9a66', now(), now());
INSERT INTO unitgroup (ugname, uguuid, createdate, updatedate) VALUES ('Units of area*time', '93a60a57-a3c8-20da-a746-0800200c9a66', now(), now());
INSERT INTO unitgroup (ugname, uguuid, createdate, updatedate) VALUES ('Units of volume*time', '93a60a57-a3c8-23da-a746-0800200c9a66', now(), now());
INSERT INTO unitgroup (ugname, uguuid, createdate, updatedate) VALUES ('Units of mass', '93a60a57-a4c8-11da-a746-0800200c9a66', now(), now());
INSERT INTO unitgroup (ugname, uguuid, createdate, updatedate) VALUES ('Units of time', 'af638906-3ec7-4314-8de7-f76039f2dd01', now(), now());

-- --------------------------------------- units ----------------------------------------

INSERT INTO units (uuuid, unit, uvalue, ugno, createdate, updatedate) VALUES ('5dc11308-c00e-4c91-8d8d-8a458a5ea968', 'guest night', 1.0, 30001, now(), now());
INSERT INTO units (uuuid, unit, uvalue, ugno, createdate, updatedate) VALUES ('6dabe201-aaac-4509-92f0-d00c26cb72ab', 'Item(s)', 1.0, 30002, now(), now());
INSERT INTO units (uuuid, unit, uvalue, ugno, createdate, updatedate) VALUES ('3bd6c6c3-bb61-46f3-b19a-c87ac5502bb7', 'Dozen(s)', 12.0, 30002, now(), now());
INSERT INTO units (uuuid, unit, uvalue, ugno, createdate, updatedate) VALUES ('702d94e7-fbae-4a99-a2f5-b26db21126d6', 'p*mi', 1.609344, 30003, now(), now());
INSERT INTO units (uuuid, unit, uvalue, ugno, createdate, updatedate) VALUES ('fe8da65d-f0ea-4496-b13e-1955aaa412d7', 'p*km', 1.0, 30003, now(), now());
INSERT INTO units (uuuid, unit, uvalue, ugno, createdate, updatedate) VALUES ('5209be3d-094e-4203-a074-06d7b75e9a38', 't*d', 2.7397, 30004, now(), now());
INSERT INTO units (uuuid, unit, uvalue, ugno, createdate, updatedate) VALUES ('2fdc0039-6c5e-489a-af77-b225684fa337', 'g*a', 0.001, 30004, now(), now());
INSERT INTO units (uuuid, unit, uvalue, ugno, createdate, updatedate) VALUES ('ab01fe47-d2c0-4308-adb1-e32df38d5a50', 'kg*d', 0.00273972, 30004, now(), now());
INSERT INTO units (uuuid, unit, uvalue, ugno, createdate, updatedate) VALUES ('b2ad404c-3e4f-4a7a-a604-46fb36654823', 'kg*a', 1.0, 30004, now(), now());
INSERT INTO units (uuuid, unit, uvalue, ugno, createdate, updatedate) VALUES ('23cc7a32-f08a-43df-a87c-66953eeeb3f5', 't*a', 1000.0, 30004, now(), now());
INSERT INTO units (uuuid, unit, uvalue, ugno, createdate, updatedate) VALUES ('787f2ac9-7bcd-4a91-9fab-55bfe414138f', 'TCE', 29307.6, 30005, now(), now());
INSERT INTO units (uuuid, unit, uvalue, ugno, createdate, updatedate) VALUES ('86ad2244-1f0e-4912-af53-7865283103e4', 'kWh', 3.6, 30005, now(), now());
INSERT INTO units (uuuid, unit, uvalue, ugno, createdate, updatedate) VALUES ('469d61f1-3bc4-4841-8adf-873825c1bc11', 'J', 1.0E-6, 30005, now(), now());
INSERT INTO units (uuuid, unit, uvalue, ugno, createdate, updatedate) VALUES ('55244053-94ba-404e-9172-cb279d905e00', 'btu', 0.0010551, 30005, now(), now());
INSERT INTO units (uuuid, unit, uvalue, ugno, createdate, updatedate) VALUES ('57c492e7-d94b-4fcc-98df-cdc4163b754c', 'TJ', 1000000.0, 30005, now(), now());
INSERT INTO units (uuuid, unit, uvalue, ugno, createdate, updatedate) VALUES ('f4119718-2d50-47fe-9154-cab6fd2d30eb', 'kJ', 0.001, 30005, now(), now());
INSERT INTO units (uuuid, unit, uvalue, ugno, createdate, updatedate) VALUES ('ff1a332f-967c-4084-8b01-8d3ca81b3121', 'PJ', 1.0E9, 30005, now(), now());
INSERT INTO units (uuuid, unit, uvalue, ugno, createdate, updatedate) VALUES ('010f811e-3cc2-4b14-a901-337da9b3e49c', 'kcal', 0.004184, 30005, now(), now());
INSERT INTO units (uuuid, unit, uvalue, ugno, createdate, updatedate) VALUES ('fc3604f7-aa93-4aa3-8ae9-8f822874da5f', 'Wh', 0.0036, 30005, now(), now());
INSERT INTO units (uuuid, unit, uvalue, ugno, createdate, updatedate) VALUES ('52765a6c-3896-43c2-b2f4-c679acf13efe', 'MJ', 1.0, 30005, now(), now());
INSERT INTO units (uuuid, unit, uvalue, ugno, createdate, updatedate) VALUES ('92e3bd49-8ed5-4885-9db6-fc88c7afcfcb', 'MWh', 3600.0, 30005, now(), now());
INSERT INTO units (uuuid, unit, uvalue, ugno, createdate, updatedate) VALUES ('425aff51-b7e5-4561-aa5a-562ec103a79e', 'TOE', 41868.0, 30005, now(), now());
INSERT INTO units (uuuid, unit, uvalue, ugno, createdate, updatedate) VALUES ('01e58eb9-0aba-4c76-ba0c-03f6f3be1353', 'GJ', 1000.0, 30005, now(), now());
INSERT INTO units (uuuid, unit, uvalue, ugno, createdate, updatedate) VALUES ('df6987d7-afcc-4c92-ae2c-3ce3bc6f5578', 'ul', 1.0E-9, 30006, now(), now());
INSERT INTO units (uuuid, unit, uvalue, ugno, createdate, updatedate) VALUES ('7642bdc2-cdfa-4cc5-9e12-2255f561842a', 'pk', 0.008809768, 30006, now(), now());
INSERT INTO units (uuuid, unit, uvalue, ugno, createdate, updatedate) VALUES ('1c3a9695-398d-4b1f-b07e-a8715b610f70', 'm3', 1.0, 30006, now(), now());
INSERT INTO units (uuuid, unit, uvalue, ugno, createdate, updatedate) VALUES ('07973a41-56b3-4e1b-a208-fd75a09fbd4b', 'cu ft', 0.0283168, 30006, now(), now());
INSERT INTO units (uuuid, unit, uvalue, ugno, createdate, updatedate) VALUES ('ac4057f9-ec4a-4b31-9d3b-ff96969e50ff', 'cl', 1.0E-5, 30006, now(), now());
INSERT INTO units (uuuid, unit, uvalue, ugno, createdate, updatedate) VALUES ('46930fb7-8660-42a4-983e-19cf53da740b', 'cm3', 1.0E-6, 30006, now(), now());
INSERT INTO units (uuuid, unit, uvalue, ugno, createdate, updatedate) VALUES ('431df202-da83-4baa-b576-5d17f59f0c8a', 'gal (US fl)', 0.003785411784, 30006, now(), now());
INSERT INTO units (uuuid, unit, uvalue, ugno, createdate, updatedate) VALUES ('6a7531d3-3b83-4dda-8b18-4e3528491f9e', 'in3', 1.63871E-5, 30006, now(), now());
INSERT INTO units (uuuid, unit, uvalue, ugno, createdate, updatedate) VALUES ('89121cea-0d98-4466-9b0f-e33e448db7ec', 'bl (US fl)', 0.119, 30006, now(), now());
INSERT INTO units (uuuid, unit, uvalue, ugno, createdate, updatedate) VALUES ('b80a512e-e402-4363-8ad0-7d02dcf4a459', 'l', 0.001, 30006, now(), now());
INSERT INTO units (uuuid, unit, uvalue, ugno, createdate, updatedate) VALUES ('a681e9e5-6304-45f0-a5f4-df413eee0724', 'bl (US beer)', 0.117, 30006, now(), now());
INSERT INTO units (uuuid, unit, uvalue, ugno, createdate, updatedate) VALUES ('9bc2cfb2-fb43-42e6-9783-6c7479c78cce', 'bl (Imp)', 0.158987294928, 30006, now(), now());
INSERT INTO units (uuuid, unit, uvalue, ugno, createdate, updatedate) VALUES ('806c64aa-8609-4ff6-a033-6714a3348f24', 'fl oz (Imp)', 2.841E-5, 30006, now(), now());
INSERT INTO units (uuuid, unit, uvalue, ugno, createdate, updatedate) VALUES ('c026770b-5b4f-467d-8fdb-15dfb8e52913', 'pt (Imp)', 5.6826125E-4, 30006, now(), now());
INSERT INTO units (uuuid, unit, uvalue, ugno, createdate, updatedate) VALUES ('626c92d5-dcc2-40d4-b28b-4a4f82123740', 'dl', 1.0E-4, 30006, now(), now());
INSERT INTO units (uuuid, unit, uvalue, ugno, createdate, updatedate) VALUES ('8aa31dda-e324-469c-a29d-56476584f5ca', 'gal (Imp)', 0.00454609, 30006, now(), now());
INSERT INTO units (uuuid, unit, uvalue, ugno, createdate, updatedate) VALUES ('2df86f1f-293e-4e07-9cde-34eebc6d2c5f', 'pt (US fl)', 4.73E-4, 30006, now(), now());
INSERT INTO units (uuuid, unit, uvalue, ugno, createdate, updatedate) VALUES ('4669b8ce-894d-405a-95d1-3211accaa739', 'Nm3', 1.0, 30006, now(), now());
INSERT INTO units (uuuid, unit, uvalue, ugno, createdate, updatedate) VALUES ('72ef94e2-bf0c-4e85-a74c-f3b5ed1bd73e', 'dr (Fl)', 3.6966911953125E-6, 30006, now(), now());
INSERT INTO units (uuuid, unit, uvalue, ugno, createdate, updatedate) VALUES ('c530708d-be50-4207-b536-23d0857210bf', 'gal (US dry)', 0.00440488377086, 30006, now(), now());
INSERT INTO units (uuuid, unit, uvalue, ugno, createdate, updatedate) VALUES ('2ea37335-f10b-4fcc-b4a9-0a742d651fa2', 'dm3', 0.001, 30006, now(), now());
INSERT INTO units (uuuid, unit, uvalue, ugno, createdate, updatedate) VALUES ('62eb6f51-0574-4489-ae1a-1bd806f6c2ac', 'gal (US liq)', 0.003785411784, 30006, now(), now());
INSERT INTO units (uuuid, unit, uvalue, ugno, createdate, updatedate) VALUES ('e24e5e7b-6c5c-4847-969d-7772ed5df017', 'hl', 0.1, 30006, now(), now());
INSERT INTO units (uuuid, unit, uvalue, ugno, createdate, updatedate) VALUES ('505845e0-3f71-46d4-acfa-8c4ed8d3c305', 'mm3', 1.0E-9, 30006, now(), now());
INSERT INTO units (uuuid, unit, uvalue, ugno, createdate, updatedate) VALUES ('a19bcd84-e836-4764-8367-419dac3d2434', 'pt (US dry)', 5.506E-4, 30006, now(), now());
INSERT INTO units (uuuid, unit, uvalue, ugno, createdate, updatedate) VALUES ('08d8bee4-32ca-4cc3-ad33-838377e6517a', 'qt (US dry)', 0.00110121, 30006, now(), now());
INSERT INTO units (uuuid, unit, uvalue, ugno, createdate, updatedate) VALUES ('2d895297-8e96-45f1-b256-a6d4ccd4bbc4', 'dal', 0.01, 30006, now(), now());
INSERT INTO units (uuuid, unit, uvalue, ugno, createdate, updatedate) VALUES ('81696a66-6919-4bbb-a3ab-c79b2900de7d', 'ml', 1.0E-6, 30006, now(), now());
INSERT INTO units (uuuid, unit, uvalue, ugno, createdate, updatedate) VALUES ('90888d8d-3d57-497f-a3fa-12aefd2bc774', 'bsh (Imp)', 0.03636872, 30006, now(), now());
INSERT INTO units (uuuid, unit, uvalue, ugno, createdate, updatedate) VALUES ('cff8f1bf-bda0-4fc1-b14c-537d8861dcce', 'Imp.min.', 5.91938802E-8, 30006, now(), now());
INSERT INTO units (uuuid, unit, uvalue, ugno, createdate, updatedate) VALUES ('1c4169d1-4fee-40e3-9868-953ee15e26ae', 'bl (US dry)', 0.1156, 30006, now(), now());
INSERT INTO units (uuuid, unit, uvalue, ugno, createdate, updatedate) VALUES ('2d64c7e0-1167-42ec-9988-c441c261a35c', 'US fl oz', 2.95735295625E-5, 30006, now(), now());
INSERT INTO units (uuuid, unit, uvalue, ugno, createdate, updatedate) VALUES ('5ec65531-6bb4-4990-a687-44e9ab8f3529', 'bsh (US)', 0.03, 30006, now(), now());
INSERT INTO units (uuuid, unit, uvalue, ugno, createdate, updatedate) VALUES ('9cd2a2c5-77a2-4b58-b893-b039dfe635ed', 'yd3', 0.76455485798, 30006, now(), now());
INSERT INTO units (uuuid, unit, uvalue, ugno, createdate, updatedate) VALUES ('fea33583-ae83-4398-b7fb-7f90fd097269', 'qt (US liq)', 9.46353E-4, 30006, now(), now());
INSERT INTO units (uuuid, unit, uvalue, ugno, createdate, updatedate) VALUES ('078c7a18-a743-491d-87c2-530454661ef1', 'gill', 1.18294E-4, 30006, now(), now());
INSERT INTO units (uuuid, unit, uvalue, ugno, createdate, updatedate) VALUES ('91995a9e-3cb4-4fc9-a93b-8c618ff9b948', 'bbl', 0.158987294928, 30006, now(), now());
INSERT INTO units (uuuid, unit, uvalue, ugno, createdate, updatedate) VALUES ('e9773595-284e-46dd-9671-5fc9ff406833', 'kBq', 1.0, 30007, now(), now());
INSERT INTO units (uuuid, unit, uvalue, ugno, createdate, updatedate) VALUES ('a9736781-d59e-43dc-8584-147b50595c1c', 'µBq', 1.0E-9, 30007, now(), now());
INSERT INTO units (uuuid, unit, uvalue, ugno, createdate, updatedate) VALUES ('ef6d9358-a156-4c73-b678-320ddee7d2eb', 'Ci', 3.7E7, 30007, now(), now());
INSERT INTO units (uuuid, unit, uvalue, ugno, createdate, updatedate) VALUES ('a45722ee-fc30-4bb1-aa95-5c8fb56c6bfb', 'Rutherford', 1000.0, 30007, now(), now());
INSERT INTO units (uuuid, unit, uvalue, ugno, createdate, updatedate) VALUES ('ac324d87-9961-463a-81a1-099bb0f7d89b', 'Bq', 0.001, 30007, now(), now());
INSERT INTO units (uuuid, unit, uvalue, ugno, createdate, updatedate) VALUES ('df1ceca8-2d2a-46fa-91c1-24e5dd0f248f', 'nBq', 1.0E-12, 30007, now(), now());
INSERT INTO units (uuuid, unit, uvalue, ugno, createdate, updatedate) VALUES ('e2987dad-3b7e-451e-82df-fe91756e752a', 'mBq', 1.0E-6, 30007, now(), now());
INSERT INTO units (uuuid, unit, uvalue, ugno, createdate, updatedate) VALUES ('b9a011a0-c9bf-459b-b105-2623d1b61ddf', 'km2', 1000000.0, 30008, now(), now());
INSERT INTO units (uuuid, unit, uvalue, ugno, createdate, updatedate) VALUES ('0992b8e3-489a-46ea-8d71-1bf951ece5d0', 'mm2', 1.0E-6, 30008, now(), now());
INSERT INTO units (uuuid, unit, uvalue, ugno, createdate, updatedate) VALUES ('6c4b1e4a-bf45-4385-a60c-12cc48ecbab5', 'yd2', 0.836127, 30008, now(), now());
INSERT INTO units (uuuid, unit, uvalue, ugno, createdate, updatedate) VALUES ('e114cd83-3e7f-4466-8028-761c0469f7c7', 'are', 100.0, 30008, now(), now());
INSERT INTO units (uuuid, unit, uvalue, ugno, createdate, updatedate) VALUES ('20ce5f57-69b1-438b-a8e8-23089854d058', 'mi2', 2589988.11, 30008, now(), now());
INSERT INTO units (uuuid, unit, uvalue, ugno, createdate, updatedate) VALUES ('c66d26f1-7946-4027-85f0-ac79222a59f1', 'ft2', 0.09290304, 30008, now(), now());
INSERT INTO units (uuuid, unit, uvalue, ugno, createdate, updatedate) VALUES ('3ce61faa-5716-41c1-aef6-b5920054acc9', 'm2', 1.0, 30008, now(), now());
INSERT INTO units (uuuid, unit, uvalue, ugno, createdate, updatedate) VALUES ('debee4d9-bc47-4e35-8624-4957ecb75386', 'ha', 10000.0, 30008, now(), now());
INSERT INTO units (uuuid, unit, uvalue, ugno, createdate, updatedate) VALUES ('cd277914-c68c-4be4-b7cf-cdf17a6e7f48', 'in2', 6.4516E-4, 30008, now(), now());
INSERT INTO units (uuuid, unit, uvalue, ugno, createdate, updatedate) VALUES ('4689d28c-29f6-4f0a-ad03-0276f7070edd', 'nmi2', 3429904.0, 30008, now(), now());
INSERT INTO units (uuuid, unit, uvalue, ugno, createdate, updatedate) VALUES ('8ee3bcbf-9e65-4f59-9b0b-40b504cbe345', 'ac', 4046.872, 30008, now(), now());
INSERT INTO units (uuuid, unit, uvalue, ugno, createdate, updatedate) VALUES ('dc4bb818-ab06-4f65-ade9-a69a3ca116fd', 'dm2', 0.01, 30008, now(), now());
INSERT INTO units (uuuid, unit, uvalue, ugno, createdate, updatedate) VALUES ('588613d5-6c8c-4ab6-8c69-ec5e20ef7881', 'cm2', 1.0E-4, 30008, now(), now());
INSERT INTO units (uuuid, unit, uvalue, ugno, createdate, updatedate) VALUES ('c8166ae2-b592-4eb9-b365-e384c8b79f3c', 'km2*a', 1000000.0, 30009, now(), now());
INSERT INTO units (uuuid, unit, uvalue, ugno, createdate, updatedate) VALUES ('b396b97c-29ab-409a-b6fa-2285589041bd', 'mm2a', 1.0E-6, 30009, now(), now());
INSERT INTO units (uuuid, unit, uvalue, ugno, createdate, updatedate) VALUES ('0d255e00-8aa1-434e-b6cd-43991a87d3fa', 'cm2a', 1.0E-4, 30009, now(), now());
INSERT INTO units (uuuid, unit, uvalue, ugno, createdate, updatedate) VALUES ('1c43f336-c84b-4f42-bbf7-b1b6f89e121a', 'mi2*a', 2589988.11, 30009, now(), now());
INSERT INTO units (uuuid, unit, uvalue, ugno, createdate, updatedate) VALUES ('4866ec7b-6218-4783-87b4-cbd107280a85', 'ha*a', 10000.0, 30009, now(), now());
INSERT INTO units (uuuid, unit, uvalue, ugno, createdate, updatedate) VALUES ('efbbab8b-eb92-4e39-bd5f-99951ffda6c3', 'ft2*a', 0.090304, 30009, now(), now());
INSERT INTO units (uuuid, unit, uvalue, ugno, createdate, updatedate) VALUES ('00d8370e-2bf1-4f3b-81bb-f8f147e84819', 'm2*d', 0.00273790933, 30009, now(), now());
INSERT INTO units (uuuid, unit, uvalue, ugno, createdate, updatedate) VALUES ('c7266b67-4ea2-457f-b391-9b94e26e195a', 'm2*a', 1.0, 30009, now(), now());
INSERT INTO units (uuuid, unit, uvalue, ugno, createdate, updatedate) VALUES ('8c1fafa2-2b2e-4fef-9581-5de34ae87350', 'l*a', 0.001, 30010, now(), now());
INSERT INTO units (uuuid, unit, uvalue, ugno, createdate, updatedate) VALUES ('f3a1ae74-9750-4199-acdc-2e7e0546e0a5', 'm3*d', 0.00273787, 30010, now(), now());
INSERT INTO units (uuuid, unit, uvalue, ugno, createdate, updatedate) VALUES ('ee5f2241-18af-4444-b457-b275660e5a20', 'm3*a', 1.0, 30010, now(), now());
INSERT INTO units (uuuid, unit, uvalue, ugno, createdate, updatedate) VALUES ('9942703a-5962-4823-8ea3-7af06af9a21e', 'l*d', 2.73787574E-6, 30010, now(), now());
INSERT INTO units (uuuid, unit, uvalue, ugno, createdate, updatedate) VALUES ('9e6a97bb-e0cf-4e4a-9f3a-37067740b421', 'cm3*a', 1.0E-6, 30010, now(), now());
INSERT INTO units (uuuid, unit, uvalue, ugno, createdate, updatedate) VALUES ('ae5cced9-f9f1-4719-bd37-837b36013d96', 'ct', 2.0E-4, 30011, now(), now());
INSERT INTO units (uuuid, unit, uvalue, ugno, createdate, updatedate) VALUES ('218bf752-ec01-43a9-b87c-0e733374fffd', 'cg', 1.0E-5, 30011, now(), now());
INSERT INTO units (uuuid, unit, uvalue, ugno, createdate, updatedate) VALUES ('95dd50e9-c184-4412-9afc-9764b1ffcf8f', 'kg SWU', 1.0, 30011, now(), now());
INSERT INTO units (uuuid, unit, uvalue, ugno, createdate, updatedate) VALUES ('d022bf34-1162-4579-90ab-dcca9620ce54', 'Mg', 1000.0, 30011, now(), now());
INSERT INTO units (uuuid, unit, uvalue, ugno, createdate, updatedate) VALUES ('63214902-17d4-41a0-be10-d1cad375c32e', 'ng', 1.0E-12, 30011, now(), now());
INSERT INTO units (uuuid, unit, uvalue, ugno, createdate, updatedate) VALUES ('e720c842-19f5-4a38-8573-78dd13719f5b', 'kt', 1000000.0, 30011, now(), now());
INSERT INTO units (uuuid, unit, uvalue, ugno, createdate, updatedate) VALUES ('a3350a8a-5cb0-440d-9752-8afe8c08c455', 'dwt', 0.001555174, 30011, now(), now());
INSERT INTO units (uuuid, unit, uvalue, ugno, createdate, updatedate) VALUES ('2a0b9356-a2dd-444f-991e-ce66cd174c9e', 'cwt', 45.359237, 30011, now(), now());
INSERT INTO units (uuuid, unit, uvalue, ugno, createdate, updatedate) VALUES ('0300ec69-ce1a-45f0-bcf0-7b33845dc53e', 'lb av', 0.45359237, 30011, now(), now());
INSERT INTO units (uuuid, unit, uvalue, ugno, createdate, updatedate) VALUES ('90d59045-3565-4c5a-bb27-ebc365aacbd0', 'dag', 0.01, 30011, now(), now());
INSERT INTO units (uuuid, unit, uvalue, ugno, createdate, updatedate) VALUES ('e1317ffc-7f83-4a85-bc65-4fb229a25cf8', 'g', 0.001, 30011, now(), now());
INSERT INTO units (uuuid, unit, uvalue, ugno, createdate, updatedate) VALUES ('2ac22d0d-9e02-4459-9402-923490e4e1b4', 'dg', 1.0E-4, 30011, now(), now());
INSERT INTO units (uuuid, unit, uvalue, ugno, createdate, updatedate) VALUES ('d4922696-9c95-4b5d-a876-425e98276978', 'sh tn', 907.18, 30011, now(), now());
INSERT INTO units (uuuid, unit, uvalue, ugno, createdate, updatedate) VALUES ('e5ccc940-eb40-41f9-847e-5ee691ba8c2f', 'long tn', 1016.047, 30011, now(), now());
INSERT INTO units (uuuid, unit, uvalue, ugno, createdate, updatedate) VALUES ('86dfd16d-d1d8-4b71-88af-0e1126425c77', 'hg', 0.1, 30011, now(), now());
INSERT INTO units (uuuid, unit, uvalue, ugno, createdate, updatedate) VALUES ('83192ffa-5990-490b-a23a-b45ca072db6f', 't', 1000.0, 30011, now(), now());
INSERT INTO units (uuuid, unit, uvalue, ugno, createdate, updatedate) VALUES ('9dd23b40-4394-4f9a-9572-5f2ee9643864', 'Mt', 1.0E9, 30011, now(), now());
INSERT INTO units (uuuid, unit, uvalue, ugno, createdate, updatedate) VALUES ('20aadc24-a391-41cf-b340-3e4529f44bde', 'kg', 1.0, 30011, now(), now());
INSERT INTO units (uuuid, unit, uvalue, ugno, createdate, updatedate) VALUES ('b872a063-0500-42b7-9e5d-441642d84417', 'mg', 1.0E-6, 30011, now(), now());
INSERT INTO units (uuuid, unit, uvalue, ugno, createdate, updatedate) VALUES ('68c83ad3-bc5b-414a-81f7-5b47af2c8a23', 'pg', 1.0E-15, 30011, now(), now());
INSERT INTO units (uuuid, unit, uvalue, ugno, createdate, updatedate) VALUES ('2457aee5-c7ee-4416-a65a-5be4ff2b2976', 'oz t', 0.0311034768, 30011, now(), now());
INSERT INTO units (uuuid, unit, uvalue, ugno, createdate, updatedate) VALUES ('fa44e424-f37b-419d-8475-723429d63c08', 'oz av', 0.028349523125, 30011, now(), now());
INSERT INTO units (uuuid, unit, uvalue, ugno, createdate, updatedate) VALUES ('d01259b2-24c2-46af-a7fe-36dd025ead15', 'ug', 1.0E-9, 30011, now(), now());
INSERT INTO units (uuuid, unit, uvalue, ugno, createdate, updatedate) VALUES ('56f42b51-bb05-4dcd-a069-0693f167304c', 'dr (Av)', 0.0017718451953125, 30011, now(), now());
INSERT INTO units (uuuid, unit, uvalue, ugno, createdate, updatedate) VALUES ('78af80e7-ede4-4a65-b04d-97d9ce4f39d3', 'gr', 6.479891E-5, 30011, now(), now());
INSERT INTO units (uuuid, unit, uvalue, ugno, createdate, updatedate) VALUES ('7d082bce-60f5-4588-86bb-56e0dff1c8a8', 'mi*a', 1609.344, 30012, now(), now());
INSERT INTO units (uuuid, unit, uvalue, ugno, createdate, updatedate) VALUES ('f7fe0af2-e764-4984-bb9f-2cbff6cd2f18', 'm*a', 1.0, 30012, now(), now());
INSERT INTO units (uuuid, unit, uvalue, ugno, createdate, updatedate) VALUES ('8cbb4edb-d76c-41f7-8b30-cc789217c954', 'km*a', 1000.0, 30012, now(), now());
INSERT INTO units (uuuid, unit, uvalue, ugno, createdate, updatedate) VALUES ('5458351a-f6f7-4e0b-a449-823c9b6374db', 'lb*mi', 7.2998615E-4, 30013, now(), now());
INSERT INTO units (uuuid, unit, uvalue, ugno, createdate, updatedate) VALUES ('097768e6-0763-4126-a253-e323cafdf77e', 'kt*km', 1000.0, 30013, now(), now());
INSERT INTO units (uuuid, unit, uvalue, ugno, createdate, updatedate) VALUES ('3cc51ae9-b993-4a3d-964e-0aed8d7f3966', 't*mi', 1.609344, 30013, now(), now());
INSERT INTO units (uuuid, unit, uvalue, ugno, createdate, updatedate) VALUES ('7dd57df4-c092-41aa-966e-c93a27797ea1', 't*nmi', 1.852, 30013, now(), now());
INSERT INTO units (uuuid, unit, uvalue, ugno, createdate, updatedate) VALUES ('d24270f0-c1f2-49ec-a6bf-e86a74177070', 'lb*nmi', 8.40531E-4, 30013, now(), now());
INSERT INTO units (uuuid, unit, uvalue, ugno, createdate, updatedate) VALUES ('0dea4ed8-bb6b-4049-b2b4-b2c413ef2180', 't*km', 1.0, 30013, now(), now());
INSERT INTO units (uuuid, unit, uvalue, ugno, createdate, updatedate) VALUES ('a40229e6-7275-42e3-a304-23d590044770', 'kg*km', 0.001, 30013, now(), now());
INSERT INTO units (uuuid, unit, uvalue, ugno, createdate, updatedate) VALUES ('3d314eab-ef11-4ff3-a35e-9bc5cd858694', 'm', 1.0, 30014, now(), now());
INSERT INTO units (uuuid, unit, uvalue, ugno, createdate, updatedate) VALUES ('9428cdb2-e08e-46f9-a9aa-85cc11416b5f', 'dam', 10.0, 30014, now(), now());
INSERT INTO units (uuuid, unit, uvalue, ugno, createdate, updatedate) VALUES ('9003179a-bf17-4e89-8fd7-a0c1c91ac189', 'u', 1.0E-6, 30014, now(), now());
INSERT INTO units (uuuid, unit, uvalue, ugno, createdate, updatedate) VALUES ('31ace5ab-d905-46ef-b958-caa2e65d6424', 'ftm', 1.8288, 30014, now(), now());
INSERT INTO units (uuuid, unit, uvalue, ugno, createdate, updatedate) VALUES ('2a39ac84-f3f0-4296-8907-ffc2f41aebfc', 'hh', 0.1016, 30014, now(), now());
INSERT INTO units (uuuid, unit, uvalue, ugno, createdate, updatedate) VALUES ('a16960ba-31e2-4fe9-82fe-219013f4708f', 'yd', 0.9144, 30014, now(), now());
INSERT INTO units (uuuid, unit, uvalue, ugno, createdate, updatedate) VALUES ('0a025f54-0114-4548-a564-d91aa8eb8174', 'hm', 100.0, 30014, now(), now());
INSERT INTO units (uuuid, unit, uvalue, ugno, createdate, updatedate) VALUES ('d018183e-ea8c-4a41-a303-627c9b9b173d', 'cm', 0.01, 30014, now(), now());
INSERT INTO units (uuuid, unit, uvalue, ugno, createdate, updatedate) VALUES ('4a51d52b-f94f-4168-a38c-c612f14b8a2d', 'mi', 1609.344, 30014, now(), now());
INSERT INTO units (uuuid, unit, uvalue, ugno, createdate, updatedate) VALUES ('f7905d3f-904d-4fdb-829e-54a72fb4c98e', 'ft', 0.3048, 30014, now(), now());
INSERT INTO units (uuuid, unit, uvalue, ugno, createdate, updatedate) VALUES ('1f112940-6c27-4c75-828a-46d548d71cff', 'nmi', 1852.0, 30014, now(), now());
INSERT INTO units (uuuid, unit, uvalue, ugno, createdate, updatedate) VALUES ('030e99e1-2237-483c-8447-8b3a256d8b0d', 'in', 0.0254, 30014, now(), now());
INSERT INTO units (uuuid, unit, uvalue, ugno, createdate, updatedate) VALUES ('127eddde-af81-4d0d-88f3-123c0b65d971', 'ch', 20.1168, 30014, now(), now());
INSERT INTO units (uuuid, unit, uvalue, ugno, createdate, updatedate) VALUES ('715ca68e-0ac5-4c4b-b557-fdc36623be88', 'km', 1000.0, 30014, now(), now());
INSERT INTO units (uuuid, unit, uvalue, ugno, createdate, updatedate) VALUES ('35b9b720-0518-4476-941e-282d9654161a', 'dm', 0.1, 30014, now(), now());
INSERT INTO units (uuuid, unit, uvalue, ugno, createdate, updatedate) VALUES ('1135c9c8-05e9-4831-9eb2-e1c4759f218d', 'fur', 201.168, 30014, now(), now());
INSERT INTO units (uuuid, unit, uvalue, ugno, createdate, updatedate) VALUES ('cc44768b-c0ce-4bc9-804a-c59b67d22e39', 'mm', 0.001, 30014, now(), now());
INSERT INTO units (uuuid, unit, uvalue, ugno, createdate, updatedate) VALUES ('9fa94e47-03bd-4ad1-8726-e10cfb6dbb7a', 'min', 0.016666560000000004, 30015, now(), now());
INSERT INTO units (uuuid, unit, uvalue, ugno, createdate, updatedate) VALUES ('11074cfd-08a4-449b-adad-18ce24a1b275', 'd', 24.0, 30015, now(), now());
INSERT INTO units (uuuid, unit, uvalue, ugno, createdate, updatedate) VALUES ('9a87f840-752d-4863-b911-533f92ee5073', 'a', 8760.0, 30015, now(), now());
INSERT INTO units (uuuid, unit, uvalue, ugno, createdate, updatedate) VALUES ('227a54d9-44e7-468c-b8bb-f2dd1ae68c7a', 'h', 1.0, 30015, now(), now());
INSERT INTO units (uuuid, unit, uvalue, ugno, createdate, updatedate) VALUES ('845178d8-3f2c-497f-9ca6-3c5657c2823c', 's', 2.7777768E-4, 30015, now(), now());