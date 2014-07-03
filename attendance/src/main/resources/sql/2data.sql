-- roles 
insert into security.roles(id,name,indexno,updated_at,enabled)
  values(1,'系统管理员','1',current_date,true);
insert into security.roles(id,name,indexno,updated_at,enabled)
  values(2,'教务处','2',current_date,true);
insert into security.roles(id,name,indexno,updated_at,enabled)
  values(3,'学生处','3',current_date,true);
insert into security.roles(id,name,indexno,updated_at,enabled)
  values(4,'学院管理员','4',current_date,true);
insert into security.roles(id,name,indexno,updated_at,enabled)
  values(5,'辅导员','5',current_date,true);
insert into security.roles(id,name,indexno,updated_at,enabled)
  values(6,'学生','6',current_date,true);
insert into security.roles(id,name,indexno,updated_at,enabled)
  values(7,'老师','7',current_date,true);
  
--members
insert into security.members(id, user_id, role_id, member, granter, manager)
  values(1, 2014004, 1, 't', 'f', 'f');
insert into security.members(id, user_id, role_id, member, granter, manager)
  values(2, 2014005, 1, 't', 'f', 'f');
insert into security.members(id, user_id, role_id, member, granter, manager)
  values(3, 2014006, 1, 't', 'f', 'f');
insert into security.members(id, user_id, role_id, member, granter, manager)
  values(4, 2001032, 4, 't', 'f', 'f');
insert into security.members(id, user_id, role_id, member, granter, manager)
  values(5, 2003010, 5, 't', 'f', 'f');
insert into security.members(id, user_id, role_id, member, granter, manager)
  values(6, 2012134101, 6, 't', 'f', 'f');
insert into security.members(id, user_id, role_id, member, granter, manager)
  values(7, 2001032, 7, 't', 'f', 'f');

--func_resources
insert into security.func_resources(id,name,enabled,scope,title) 
  values(1,'/admin/college.action',true,2,'教学处按院系汇总');
insert into security.func_resources(id,name,enabled,scope,title) 
  values(２,'/admin/teacher.action',true,2,'教学处按老师汇总');
insert into security.func_resources(id,name,enabled,scope,title) 
  values(3,'/admin/lesson.action',true,2,'教学处按课程汇总');
insert into security.func_resources(id,name,enabled,scope,title) 
  values(4,'/admin/student.action',true,2,'教学处按学生汇总');
insert into security.func_resources(id,name,enabled,scope,title) 
  values(5,'/stumgr/college.action',true,2,'学生处按学院汇总');
insert into security.func_resources(id,name,enabled,scope,title) 
  values(6,'/stumgr/adminclass.action',true,2,'学生处按班级汇总');
insert into security.func_resources(id,name,enabled,scope,title) 
  values(7,'/stumgr/student.action',true,2,'学生处按学生汇总');
insert into security.func_resources(id,name,enabled,scope,title) 
  values(8,'/stumgr/lesson.action',true,2,'学生处按课程汇总');
insert into security.func_resources(id,name,enabled,scope,title) 
  values(9,'/college/adminclass.action',true,2,'学院按班级汇总');
insert into security.func_resources(id,name,enabled,scope,title) 
  values(10,'/college/adminclass-student.action',true,2,'学院按班级学生汇总');
insert into security.func_resources(id,name,enabled,scope,title) 
  values(11,'/college/student-lesson.action',true,2,'学院按学生成绩汇总');
insert into security.func_resources(id,name,enabled,scope,title) 
  values(12,'/college/teacher.action',true,2,'学院按老师汇总');
insert into security.func_resources(id,name,enabled,scope,title) 
  values(13,'/college/teacher-lesson.action',true,2,'学院按老师课程汇总');
insert into security.func_resources(id,name,enabled,scope,title) 
  values(14,'/college/lesson-student.action',true,2,'学院按课程学生汇总');
insert into security.func_resources(id,name,enabled,scope,title) 
  values(15,'/counselor/adminclass.action',true,2,'辅导员按班级汇总');
insert into security.func_resources(id,name,enabled,scope,title) 
  values(16,'/counselor/lesson.action',true,2,'辅导员按课程汇总');
insert into security.func_resources(id,name,enabled,scope,title) 
  values(17,'/counselor/student.action',true,2,'辅导员按学生汇总');
insert into security.func_resources(id,name,enabled,scope,title) 
  values(18,'/student/lesson.action',true,2,'学生按课程汇总');
insert into security.func_resources(id,name,enabled,scope,title) 
  values(19,'/student/attend.action',true,2,'学生查询考勤明细');
insert into security.func_resources(id,name,enabled,scope,title) 
  values(21,'/teacher/lesson.action',true,2,'老师按课程汇总');
insert into security.func_resources(id,name,enabled,scope,title) 
  values(22,'/teacher/student.action',true,2,'老师按学生汇总');
  
--func_permissions
insert into security.func_permissions(id, role_id, resource_id)
  values(2, 2, 1);
insert into security.func_permissions(id, role_id, resource_id)
  values(3, 2, 2);
insert into security.func_permissions(id, role_id, resource_id)
  values(4, 2, 3);
insert into security.func_permissions(id, role_id, resource_id)
  values(5, 2, 4);
insert into security.func_permissions(id, role_id, resource_id)
  values(6, 3, 5);
insert into security.func_permissions(id, role_id, resource_id)
  values(7, 3, 6);
insert into security.func_permissions(id, role_id, resource_id)
  values(8, 3, 7);
insert into security.func_permissions(id, role_id, resource_id)
  values(9, 3, 8);
insert into security.func_permissions(id, role_id, resource_id)
  values(10, 4, 9);
insert into security.func_permissions(id, role_id, resource_id)
  values(11, 4, 10);
insert into security.func_permissions(id, role_id, resource_id)
  values(12, 4, 11);
insert into security.func_permissions(id, role_id, resource_id)
  values(13, 4, 12);
insert into security.func_permissions(id, role_id, resource_id)
  values(14, 4, 13);
insert into security.func_permissions(id, role_id, resource_id)
  values(15, 4, 14);
insert into security.func_permissions(id, role_id, resource_id)
  values(16, 5, 15);
insert into security.func_permissions(id, role_id, resource_id)
  values(17, 5, 16);
insert into security.func_permissions(id, role_id, resource_id)
  values(18, 5, 17);
insert into security.func_permissions(id, role_id, resource_id)
  values(19, 6, 18);
insert into security.func_permissions(id, role_id, resource_id)
  values(21, 7, 21);
insert into security.func_permissions(id, role_id, resource_id)
  values(22, 7, 22);
  