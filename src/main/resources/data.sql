INSERT INTO Trade(trade_id,version,counter_party_id,book_id,maturity_date,created_date,expired)
VALUES ('T1',1,'CP1','B1','2020-05-20',CURRENT_DATE,'N'),
       ('T2',2,'CP2','B1','2021-05-20',CURRENT_DATE,'N'),
       ('T2',1,'CP1','B1','2021-05-20','2015-03-14','N'),
       ('T3',3,'CP3','B2','2014-05-20',CURRENT_DATE,'Y');