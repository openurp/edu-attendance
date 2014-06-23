[#ftl]
[#import "../../../comm.ftl" as c/]
[@c.body title="课程列表";type, item]
  [#if type == "nav"]
    <ol class="breadcrumb">
      <li>[@b.a href="college"]首页[/@]</li>
      <li>[@b.a href="adminclass?f.departmentId=${college.id}${f.dayParams}"]${college.name}[/@]</li>
      <li>[@b.a href="student?f.adminclassId=${adminclass.id}&f.departmentId=${college.id}${f.dayParams}"]${adminclass.name}[/@]</li>
      <li class="active">${student.name}</li>
    </ol>
  [#elseif type == "th"]
    <th>课程序号</th>
    <th>课程名称</th>
    <th>上课教师</th>
  [#elseif type == "td"]
    <td>${item.code}<td>${item.name}</td><td>${item.name3}</td>
  [/#if]
[/@]