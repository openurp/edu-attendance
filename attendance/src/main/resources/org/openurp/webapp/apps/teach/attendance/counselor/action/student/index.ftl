[#ftl]
[#import "../../../comm.ftl" as c/]
[@c.body title="学生列表" search=true;type, item]
  [#if type == "nav"]
    <ol class="breadcrumb">
      <li>[@b.a href="adminclass?${f.dayParams}"]首页[/@]</li>
      <li class="active">${adminclass.name}</li>
    </ol>
  [#elseif type == "search"]
    [@c.search.studentName/]
  [#elseif type == "th"]
    <th>学号</th>
    <th>学生姓名</th>
  [#elseif type == "td"]
    <td>[@b.a href="lesson?f.studentId=${item.id}${f.params}"]${item.code}[/@]<td>${item.name}</td>
  [/#if]
[/@]