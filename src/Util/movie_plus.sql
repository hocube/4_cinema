delete from login_info
where cust_id ='rhj98';
commit;

update customer
set cust_password = '2222' where cust_id = 'kkk';

-- 무비스케줄 추가
insert into movie_schedule(movie_id, movie_name, movie_date, start_time, end_time,
poster_img,theater_id)
values('4','엘리멘탈','2023/07/11','13:00','15:00','y','미나리');
insert into movie_schedule(movie_id, movie_name, movie_date, start_time, end_time,
poster_img,theater_id)
values('1','뽀로로','2023/07/10','09:00','11:00','y','한국ICT관');

insert into movie_schedule(movie_id, movie_name, movie_date, start_time, end_time,
poster_img,theater_id)
values('1','뽀로로','2023/07/11','11:00','13:00','y','한국ICT관');

insert into movie_schedule(movie_id, movie_name, movie_date, start_time, end_time,
poster_img,theater_id)
values('2','반지의제왕','2023/07/10','09:00','11:00','y','한국ICT관');

insert into movie_schedule(movie_id, movie_name, movie_date, start_time, end_time,
poster_img,theater_id)
values('2','반지의제왕','2023/07/10','13:00','15:00','y','한국ICT관');

insert into movie_schedule(movie_id, movie_name, movie_date, start_time, end_time,
poster_img,theater_id)
values('3','해리포터와비밀의방','2023/07/15','09:00','11:00','y','한국ICT관');

commit;

-- 무비 종류 추가
insert into movie(movie_id, movie_name, view_age)
values('1','뽀로로',7);
insert into movie(movie_id, movie_name, view_age)
values('2','반지의제왕',7);
insert into movie(movie_id, movie_name, view_age)
values('3','해리포터와비밀의방',15);
insert into movie(movie_id, movie_name, view_age)
values('4','엘리멘탈',12);

-- 티켓리스트 데이터 삽입
INSERT INTO TICKET (ticket_num, movie_id, cust_id, movie_name, theater_id, movie_date, start_time, end_time, theater_seat)
VALUES (5, '1', 'rhj98', '뽀로로', 'THEATER001', '2023-07-10', '13:00', '15:30', 'A1');

INSERT INTO TICKET (ticket_num, movie_id, cust_id, movie_name, theater_id, movie_date, start_time, end_time, theater_seat)
VALUES (6, '2', 'rhj98', '반지의제왕', 'THEATER002', '2023-07-11', '14:30', '16:45', 'B3');

INSERT INTO TICKET (ticket_num, movie_id, cust_id, movie_name, theater_id, movie_date, start_time, end_time, theater_seat)
VALUES (7, '3', 'rhj98', '해리포터와비밀의방', 'THEATER003', '2023-07-12', '18:00', '20:15', 'C2');

INSERT INTO TICKET (ticket_num, movie_id, cust_id, movie_name, theater_id, movie_date, start_time, end_time, theater_seat)
VALUES (8, '4', 'rhj98', '엘리멘탈', 'THEATER004', '2023-07-13', '21:30', '23:45', 'D5');

INSERT INTO TICKET (ticket_num, movie_id, cust_id, movie_name, theater_id, movie_date, start_time, end_time, theater_seat)
VALUES (9, '4', 'rhj98', '엘리멘탈', 'THEATER005', '2023-07-14', '10:00', '12:30', 'E8');

INSERT INTO TICKET (ticket_num, movie_id, cust_id, movie_name, theater_id, movie_date, start_time, end_time, theater_seat)
VALUES (10, '1', 'rhj98', '뽀로로', 'THEATER001', '2023-07-10', '13:00', '15:30', 'A1');

INSERT INTO TICKET (ticket_num, movie_id, cust_id, movie_name, theater_id, movie_date, start_time, end_time, theater_seat)
VALUES (11, '2', 'rhj98', '반지의제왕', 'THEATER002', '2023-07-11', '14:30', '16:45', 'B3');

INSERT INTO TICKET (ticket_num, movie_id, cust_id, movie_name, theater_id, movie_date, start_time, end_time, theater_seat)
VALUES (12, '3', 'rhj98', '해리포터와비밀의방', 'THEATER003', '2023-07-12', '18:00', '20:15', 'C2');

INSERT INTO TICKET (ticket_num, movie_id, cust_id, movie_name, theater_id, movie_date, start_time, end_time, theater_seat)
VALUES (13, '4', 'rhj98', '엘리멘탈', 'THEATER004', '2023-07-13', '21:30', '23:45', 'D5');

INSERT INTO TICKET (ticket_num, movie_id, cust_id, movie_name, theater_id, movie_date, start_time, end_time, theater_seat)
VALUES (14, '4', 'rhj98', '엘리멘탈', 'THEATER005', '2023-07-14', '10:00', '12:30', 'E8');

commit;

-- 티켓장바구니 데이터베이스 만들기
create table ticket_cart
(
    movie_id varchar2(50),
    movie_name varchar2(50),
    theather_id varchar2(50),
    adult number,
    kid number,
    theater_seat varchar2(50)
);

commit;