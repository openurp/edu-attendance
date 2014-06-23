[#ftl]
[#import "../../../comm.ftl" as c/]
[@c.body title="教师列表" search=true;type, item]
  [#if type == "nav"]
    <ol class="breadcrumb">
      <li>[@b.a href="college?${f.dayParams}"]首页[/@]</li>
      <li class="active">${college.name}</li>
    </ol>
  [#elseif type == "search"]
    [@c.search.teacherName/]
  [#elseif type == "th"]
    <th>教职工号</th>
    <th>教师姓名</th>
  [#elseif type == "td"]
    <td>[@b.a href="lesson?f.teacherId=${item.id}${f.params}"]${item.code}[/@]<td>${item.name}</td>
  [/#if]
[/@]