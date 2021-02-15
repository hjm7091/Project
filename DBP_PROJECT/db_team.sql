show databases;
create database teamProject;
use teamProject;

CREATE TABLE students (
	student_id VARCHAR(20) PRIMARY KEY,
    password VARCHAR(22)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE course (
	classification VARCHAR(10),
	course_id VARCHAR(10) PRIMARY KEY,
    course_name VARCHAR(25),
    course_name_eng VARCHAR(50),
    credit VARCHAR(9),
    professor_name VARCHAR(15),
    classroom VARCHAR(20),
    time VARCHAR(20)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO course VALUES('기초', '1100004', '글쓰기와 말하기(인문사회)', 'Writing and Speaking:Humanity and Society', '3-3-0-0', '심재욱', '60주년기념관 315', '2,3,4');

INSERT INTO course VALUES('기초', '1100005', '글쓰기와 말하기(자연공학)', 'Writing and Speaking:Nature and Engineering', '3-3-0-0', '김옥영', '60주년기념관 315', '6,7,8');
INSERT INTO course VALUES('기초', '1100002', '의사소통여엉1', 'Communication English1', '2-2-0-0', '애덤 화이트', '60주년기념관 314', '2,3');
INSERT INTO course VALUES('기초', '1100003', '의사소통여엉2', 'Communication English2', '2-2-0-0', '김효실', '60주년기념관 208', '2,3');
INSERT INTO course VALUES('기초', '1100006', '컴퓨팅사고력(일반)', 'Computing', '3-3-0-0', '예비', '예비', '예비');

INSERT INTO course VALUES('균형(사회와문화)', '1200059', '매스컴과사회', 'asdf', '3-3-0-0', '예비', '예비', '예비');
INSERT INTO course VALUES('균형(예술과건강)', '1200110', '정신건강', 'asdf', '3-3-0-0', '예비', '예비', '예비');
INSERT INTO course VALUES('균형(사회와문화)', '1200053', '생활법률', 'asdf', '3-3-0-0', '예비', '예비', '예비');
INSERT INTO course VALUES('균형(역사와철학)', '1200033', '서양의 역사와문화', 'asdf', '3-3-0-0', '예비', '예비', '예비');
INSERT INTO course VALUES('균형(과학과기술)', '1200081', '컴퓨터개론및실습', 'asdf', '3-3-0-0', '예비', '예비', '예비');
INSERT INTO course VALUES('균형(과학과기술)', '1200099', '컴퓨터프로그래밍기초', 'asdf', '3-3-0-0', '예비', '예비', '예비');
INSERT INTO course VALUES('균형(과학과기술)', '1200080', '웹과인터넷활용및실습', 'asdf', '3-3-0-0', '예비', '예비', '예비');
INSERT INTO course VALUES('균형(예술과건강)', '1200103', '디자인과생활', 'asdf', '3-3-0-0', '예비', '예비', '예비');

INSERT INTO course VALUES('특화', '1300015', '직업선택', 'asdf', '2-2-0-0', '예비', '예비', '예비');


INSERT INTO course VALUES('대학별', '1400145', 'C프로그래밍1', 'C Programming1', '3-3-0-0', '예비', '예비', '예비');
INSERT INTO course VALUES('대학별', '1400146', 'C프로그래밍2', 'C Programming2', '3-3-0-0', '예비', '예비', '예비');
INSERT INTO course VALUES('대학별', '1400147', '자바프로그래밍1', 'Java Programming1', '3-3-0-0', '예비', '예비', '예비');
INSERT INTO course VALUES('대학별', '1400148', '자바프로그래밍2', 'Java Programming1', '3-3-0-0', '예비', '예비', '예비');
INSERT INTO course VALUES('대학별', '1400149', '컴퓨터개론', 'asdf', '3-3-0-0', '예비', '예비', '예비');
INSERT INTO course VALUES('대학별', '1400015', '확률및통계', 'asdf', '3-3-0-0', '예비', '예비', '예비');
INSERT INTO course VALUES('대학별', '1400095', 'IT미적분학', 'asdf', '3-3-0-0', '예비', '예비', '예비');

INSERT INTO course VALUES('전선', '4471991', '진로탐색과꿈-설계', 'asdf', '3-3-0-0', '예비', '예비', '예비');

INSERT INTO course VALUES('전선', '4471001', '논리회로', 'asdf', '3-3-0-0', '예비', '예비', '예비');
INSERT INTO course VALUES('전선', '4471008', '이산수학', 'asdf', '3-3-0-0', '예비', '예비', '예비');
INSERT INTO course VALUES('전선', '4473005', '선형대수학', 'asdf', '3-3-0-0', '예비', '예비', '예비');
INSERT INTO course VALUES('전선', '4473008', '자료구조', 'asdf', '3-3-0-0', '예비', '예비', '예비');
INSERT INTO course VALUES('전선', '4473046', '웹프로그래밍및실습', 'asdf', '3-3-0-0', '예비', '예비', '예비');
INSERT INTO course VALUES('전선', '4473047', '객체지향프로그래밍', 'asdf', '3-3-0-0', '예비', '예비', '예비');

INSERT INTO course VALUES('전선', '4473009', '수치해석', 'asdf', '3-3-0-0', '예비', '예비', '예비');
INSERT INTO course VALUES('전선', '4473012', '디지털회로설계', 'asdf', '3-3-0-0', '예비', '예비', '예비');
INSERT INTO course VALUES('전선', '4473015', '자료처리', 'asdf', '3-3-0-0', '예비', '예비', '예비');
INSERT INTO course VALUES('전선', '4473069', '리눅스프로그래밍', 'asdf', '3-3-0-0', '예비', '예비', '예비');
INSERT INTO course VALUES('전선', '4471009', '모바일프로그래밍', 'asdf', '3-3-0-0', '예비', '예비', '예비');

INSERT INTO course VALUES('전필', '4473002', '운영체제', 'asdf', '3-3-0-0', '예비', '예비', '예비');
INSERT INTO course VALUES('전필', '4473014', '컴퓨터구조', 'asdf', '3-3-0-0', '예비', '예비', '예비');
INSERT INTO course VALUES('전선', '4473011', '컴퓨터알고리즘', 'asdf', '3-3-0-0', '예비', '예비', '예비');
INSERT INTO course VALUES('전선', '4473018', '데이터통신', 'asdf', '3-3-0-0', '예비', '예비', '예비');
INSERT INTO course VALUES('전선', '4473022', '시스템프로그래밍', 'asdf', '3-3-0-0', '예비', '예비', '예비');
INSERT INTO course VALUES('전선', '4473027', '형식언어와오토마타', 'asdf', '3-3-0-0', '예비', '예비', '예비');
INSERT INTO course VALUES('전선', '4473032', '컴퓨터그래픽스', 'asdf', '3-3-0-0', '예비', '예비', '예비');

INSERT INTO course VALUES('전선', '4471992', '직업선택과꿈-설계', 'asdf', '3-3-0-0', '예비', '예비', '예비');
INSERT INTO course VALUES('전필', '4473001', '프로그래밍언어론', 'asdf', '3-3-0-0', '예비', '예비', '예비');
INSERT INTO course VALUES('전필', '4473021', '데이터베이스', 'asdf', '3-3-0-0', '예비', '예비', '예비');
INSERT INTO course VALUES('전선', '4473017', '인공지능', 'asdf', '3-3-0-0', '예비', '예비', '예비');
INSERT INTO course VALUES('전선', '4473024', '컴퓨터시스템설계', 'asdf', '3-3-0-0', '예비', '예비', '예비');
INSERT INTO course VALUES('전선', '4473025', '컴퓨터네트워크', 'asdf', '3-3-0-0', '예비', '예비', '예비');
INSERT INTO course VALUES('전선', '4473036', '소프트웨어공학', 'asdf', '3-3-0-0', '예비', '예비', '예비');
INSERT INTO course VALUES('전선', '4473051', '정보시스템보안', 'asdf', '3-3-0-0', '예비', '예비', '예비');

INSERT INTO course VALUES('전선', '4473029', '컴파일러구성론', 'asdf', '3-3-0-0', '예비', '예비', '예비');
INSERT INTO course VALUES('전선', '4473031', '마이크로컴퓨터', 'asdf', '3-3-0-0', '예비', '예비', '예비');
INSERT INTO course VALUES('전선', '4473033', '데이터베이스프로그래밍', 'asdf', '3-3-0-0', '예비', '예비', '예비');
INSERT INTO course VALUES('전선', '4473044', '네트워크프로그래밍', 'asdf', '3-3-0-0', '예비', '예비', '예비');
INSERT INTO course VALUES('전선', '4473067', '캡스톤디자인1', 'asdf', '3-3-0-0', '예비', '예비', '예비');
INSERT INTO course VALUES('전선', '4473071', '정보검색', 'asdf', '3-3-0-0', '예비', '예비', '예비');

INSERT INTO course VALUES('전선', '4473052', '네트워크보안', 'asdf', '3-3-0-0', '예비', '예비', '예비');
INSERT INTO course VALUES('전선', '4473041', '멀티미디어', 'asdf', '3-3-0-0', '예비', '예비', '예비');
INSERT INTO course VALUES('전선', '4473066', '자연언어처리', 'asdf', '3-3-0-0', '예비', '예비', '예비');
INSERT INTO course VALUES('전선', '4473068', '캡스톤디자인2', 'asdf', '3-3-0-0', '예비', '예비', '예비');

INSERT INTO course VALUES('전선', '2220051', '영어SW기술문서작성법', 'asdf', '3-3-0-0', '예비', '예비', '예비');
INSERT INTO course VALUES('전선', '2220052', 'SW국내단기현장실습(4주)', 'asdf', '3-3-0-0', '예비', '예비', '예비');
INSERT INTO course VALUES('전선', '2220053', 'SW국내단기현장실습(8주)', 'asdf', '6-0-0-0', '예비', '예비', '예비');
INSERT INTO course VALUES('전선', '2220054', 'SW국내장기현장실습(15주)', 'asdf', '15-0-0-0', '예비', '예비', '예비');
INSERT INTO course VALUES('전선', '2220055', 'SW국내장기심화현장실습(15주)', 'asdf', '15-0-0-0', '예비', '예비', '예비');
INSERT INTO course VALUES('전선', '2220056', 'SW국외단기현장실습(4주이내)', 'asdf', '3-0-0-0', '예비', '예비', '예비');
INSERT INTO course VALUES('전선', '2220057', 'SW국외단기현장실습(8주이내)', 'asdf', '6-0-0-0', '예비', '예비', '예비');
INSERT INTO course VALUES('전선', '2230030', 'SW창업입문', 'asdf', '3-3-0-0', '예비', '예비', '예비');
INSERT INTO course VALUES('전선', '4705006', '실전코딩', 'asdf', '3-3-0-0', '예비', '예비', '예비');
INSERT INTO course VALUES('전선', '4705008', 'SW개발도구및환경', 'asdf', '3-3-0-0', '예비', '예비', '예비');


CREATE TABLE cs_curriculm (
	classification VARCHAR(10),
	cours_id VARCHAR(10) primary key
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert into cs_curriculm values('기초', '1100005');
insert into cs_curriculm values('기초', '1100002');
insert into cs_curriculm values('기초', '1100003');
insert into cs_curriculm values('기초', '1100006');



insert into cs_curriculm values('대학별(필수)', '1400145');
insert into cs_curriculm values('대학별(필수)', '1400146');
insert into cs_curriculm values('대학별(필수)', '1400147');
insert into cs_curriculm values('대학별(필수)', '1400148');
insert into cs_curriculm values('대학별(선택)', '1400149');
insert into cs_curriculm values('대학별(선택)', '1400015');
insert into cs_curriculm values('대학별(선택)', '1400095');

insert into cs_curriculm values('전선', '4471991');

insert into cs_curriculm values('전선', '4471001');
insert into cs_curriculm values('전선', '4471008');
insert into cs_curriculm values('전선', '4473005');
insert into cs_curriculm values('전선', '4473008');
insert into cs_curriculm values('전선', '4473046');
insert into cs_curriculm values('전선', '4473047');

insert into cs_curriculm values('전선', '4473009');
insert into cs_curriculm values('전선', '4473012');
insert into cs_curriculm values('전선', '4473015');
insert into cs_curriculm values('전선', '4473069');
insert into cs_curriculm values('전선', '4471009');

insert into cs_curriculm values('전필', '4473002');
insert into cs_curriculm values('전필', '4473014');
insert into cs_curriculm values('전선', '4473011');
insert into cs_curriculm values('전선', '4473018');
insert into cs_curriculm values('전선', '4473022');
insert into cs_curriculm values('전선', '4473027');
insert into cs_curriculm values('전선', '4473032');

insert into cs_curriculm values('전선', '4471992');
insert into cs_curriculm values('전필', '4473001');
insert into cs_curriculm values('전필', '4473021');
insert into cs_curriculm values('전선', '4473017');
insert into cs_curriculm values('전선', '4473024');
insert into cs_curriculm values('전선', '4473025');
insert into cs_curriculm values('전선', '4473036');
insert into cs_curriculm values('전선', '4473051');

insert into cs_curriculm values('전선', '4473029');
insert into cs_curriculm values('전선', '4473031');
insert into cs_curriculm values('전선', '4473033');
insert into cs_curriculm values('전선', '4473044');
insert into cs_curriculm values('전선', '4473067');
insert into cs_curriculm values('전선', '4473071');

insert into cs_curriculm values('전선', '4473052');
insert into cs_curriculm values('전선', '4473041');
insert into cs_curriculm values('전선', '4473066');
insert into cs_curriculm values('전선', '4473068');

insert into cs_curriculm values('전선', '2220051');
insert into cs_curriculm values('전선', '2220052');
insert into cs_curriculm values('전선', '2220053');
insert into cs_curriculm values('전선', '2220054');
insert into cs_curriculm values('전선', '2220055');
insert into cs_curriculm values('전선', '2220056');
insert into cs_curriculm values('전선', '2220057');
insert into cs_curriculm values('전선', '2230030');
insert into cs_curriculm values('전선', '4705006');
insert into cs_curriculm values('전선', '4705008');
