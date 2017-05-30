-- ���̺� ����
drop table professor;
drop table trainee;
drop table staff;
drop table human;

-- Human�� ����� ������ �����ϴ� ���̺� 
create table human (
	name varchar2(20) not null,			-- �̸� ���ڿ� null����Ұ�
	age number(3) not null,				-- ���� ���� null����Ұ� 
	jumin varchar2(20) primary key,		-- �ֹι�ȣ ���ڿ� �����̸Ӹ�Ű
	type varchar2(20) not null			-- Human Ÿ�� ���ڿ� null����Ұ�
);

-- ���� ������ �����ϴ� ���̺�
create table professor(
	jumin varchar2(20) not null,		-- �ֹι�ȣ ���ڿ� null����Ұ�
	major varchar2(30) not null,		-- ���� ���ڿ� null����Ұ�
	constraint professor_jumin_fk 
	foreign key (jumin) 
	references human(jumin)				-- �ܷ�Ű�� jumin���� human�� jumin�� �����Ѵ�  
);

-- ������ ������ �����ϴ� ���̺�
create table trainee(
	jumin varchar2(20) not null,		-- �ֹι�ȣ ���ڿ� null����Ұ�
	stdNo varchar2(20) not null,		-- �й� ���ڿ� null����Ұ�
	constraint trainee_jumin_fk 
	foreign key (jumin) 
	references human(jumin)				-- �ܷ�Ű�� jumin���� human�� jumin�� �����Ѵ�
);

-- ��� ������ �����ϴ� ���̺�
create table staff(
	jumin varchar2(20) not null,		-- �ֹι�ȣ ���ڿ� null����Ұ�
	field varchar2(20) not null,		-- �μ� ���ڿ� null����Ұ�
	constraint staff_jumin_fk 
	foreign key (jumin) 
	references human(jumin)				-- �ܷ�Ű�� jumin���� human�� jumin�� �����Ѵ�
);	

-- Ŀ��
commit;