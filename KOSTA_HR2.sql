drop table customer;

create table customer(
    cust_id number,
    cust_name varchar(20),
    gender char(1),
    gender2 char(10),
    birthday date,
    year01 interval year(3) to month,
    day01 interval day(3) to second
);

insert into customer values (1, 'ȫ�浿', 'm', '����', sysdate, interval '36' month(3), interval '100' day(3));
insert into customer values (2, 'ȫ�浿2', 'f', '���� ', sysdate, interval '36' month(3), interval '100' day(3));
insert into customer values (3, 'ȫ�浿3', 'm', '���� ', sysdate, interval '36' month(3), interval '100' day(3));

select * from customer
where trim(gender2) = '����'
;

create table emp2(
    emp_id number(3),
    emp_name varchar(20),
    salary number(9,2)
);

insert into emp2 values (1, '��浿12345678901', 1000000.12);
insert into emp2 values (2, '�̱浿11111111111', 1234567.89);

select * from emp2;

create table emp3
as
select * from employees;

select * from emp3;

create table emp4
as
select * from employees
where department_id = 80;

select * from emp4;

create table emp5
as
select * from employees
where 1=0;

select * from emp5;

select * from user_tables;

select * from all_tables;

create table emp_basic
as
select employee_id, first_name, salary
from employees
where 1=0;

drop table emp_addinfo;
create table emp_addinfo
as
select employee_id, first_name, hire_date
from employees
where 1=0;

select * from emp_basic;
select * from emp_addinfo;

insert all
into emp_basic values(employee_id, first_name, salary)
into emp_addinfo values(employee_id, first_name, hire_date)
select employee_id, first_name, salary, hire_date
from employees;

select * from user_tables;

update emp_basic
set salary = salary * 1.1
where salary >= 10000;

select * from employees;

desc departments;

desc employees;

desc user_constraints;

select *
from user_constraints
where table_name = 'EMPLOYEES';

select *
from user_cons_columns
where table_name = 'EMPLOYEES';

select column_name, constraint_type, search_condition
from user_constraints join user_cons_columns using(constraint_name)
where user_constraints.table_name = 'EMPLOYEES';

create table ��ǰ����(
��ǰ��ȣ number constraint constraint_goods_pk primary key,
��ǰ�̸� varchar2(100),
��ǰ���� number(9)
);

create table �ֹ�(
����ȣ number ,
�ֹ����� date ,
���� number ,
��ǰ��ȣ number constraint order_goods_fk references ��ǰ����(��ǰ��ȣ) ,
���� number ,
constraint order_pk primary key(����ȣ, �ֹ�����, ����)
);

insert into ��ǰ���� values (1,'notebook', 100);
insert into ��ǰ���� values (2,'smartphone', 200);
select * from ��ǰ����;

insert into �ֹ� values (4, '2001/03/17', 1, 2, 10);
insert into �ֹ� values (4, '2001/03/17', 2, 2, 10);
insert into �ֹ� values (4, '2001/03/17', 3, 2, 10);
select * from �ֹ�;

desc employees;

update employees set
    FIRST_NAME = ?,
    LAST_NAME  = ?,
    EMAIL  = ?,
    PHONE_NUMBER  = ?,
    HIRE_DATE  = ?,  
    JOB_ID  = ?,
    SALARY  = ?,
    COMMISSION_PCT  = ?,  
    MANAGER_ID  = ?,   
    DEPARTMENT_ID  = ?
where employee_id = ?;

create table emp_backup
as
select * from employees;

SELECT * from emp_backup;

create or replace view emp_backup_view2
as
select employee_id, first_name, salary
from emp_backup
where department_id = 60;

select * from emp_backup_view2;

select *
from user_views;

update emp_backup_view2
set salary = 10000
where salary > 5000;

create or replace view emp_backup_view_groupby
as
select department_id, avg(salary) sal_avg
from emp_backup
group by department_id;

select department_name, sal_avg
from emp_backup_view_groupby join departments using (department_id);

create or replace view emp_backup_30
as
select *
from emp_backup
where department_id = 30 with check option;

select * from emp_backup_30;

update emp_backup_30
set salary = 10000;

create or replace view emp_backup_30
as
select *
from emp_backup
where department_id = 30 with read only;

