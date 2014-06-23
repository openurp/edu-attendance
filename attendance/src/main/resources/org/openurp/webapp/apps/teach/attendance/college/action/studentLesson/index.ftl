[#ftl]
[#import "../../../comm.ftl" as c/]
[@c.body title="课程列表";type, item]
  [#if type == "nav"]
    <ol class="breadcrumb">
      <li>[@b.a href="adminclass?${f.dayParams}"]首页[/@]</li>
      <li>[@b.a href="adminclass-student?f.adminclassId=${adminclass.id}${f.dayParams}"]${adminclass.name}[/@]</li>
      <li class="active">${student.name}</li>
    </ol>
  [#elseif type == "th"]
    <th>课程序号</th>
    <th>课程名称</th>
    <th>上课老师</th>
  [#elseif type == "td"]
    <td>${item.code}<td>${item.name}</td><td>${item.name3}</td>
  [/#if]
[/@]