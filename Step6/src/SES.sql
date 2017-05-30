-- 테이블 삭제
drop table professor;
drop table trainee;
drop table staff;
drop table human;

-- Human의 공통된 정보를 저장하는 테이블 
create table human (
	name varchar2(20) not null,			-- 이름 문자열 null존재불가
	age number(3) not null,				-- 나이 숫자 null존재불가 
	jumin varchar2(20) primary key,		-- 주민번호 문자열 프라이머리키
	type varchar2(20) not null			-- Human 타입 문자열 null존재불가
);

-- 교수 정보를 저장하는 테이블
create table professor(
	jumin varchar2(20) not null,		-- 주민번호 문자열 null존배불가
	major varchar2(30) not null,		-- 전공 문자열 null존배불가
	constraint professor_jumin_fk 
	foreign key (jumin) 
	references human(jumin)				-- 외래키로 jumin에서 human의 jumin을 참조한다  
);

-- 연수생 정보를 저장하는 테이블
create table trainee(
	jumin varchar2(20) not null,		-- 주민번호 문자열 null존배불가
	stdNo varchar2(20) not null,		-- 학번 문자열 null존배불가
	constraint trainee_jumin_fk 
	foreign key (jumin) 
	references human(jumin)				-- 외래키로 jumin에서 human의 jumin을 참조한다
);

-- 운영진 정보를 저장하는 테이블
create table staff(
	jumin varchar2(20) not null,		-- 주민번호 문자열 null존배불가
	field varchar2(20) not null,		-- 부서 문자열 null존배불가
	constraint staff_jumin_fk 
	foreign key (jumin) 
	references human(jumin)				-- 외래키로 jumin에서 human의 jumin을 참조한다
);	

-- 커밋
commit;