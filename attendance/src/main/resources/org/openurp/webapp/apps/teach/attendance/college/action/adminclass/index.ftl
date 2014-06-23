[#ftl]
[#import "../../../comm.ftl" as c/]
[@c.body title="班级列表" search=true;type, item]
  [#if type == "nav"]
    <ol class="breadcrumb">
      <li>首页</li>
    </ol>
  [#elseif type == "search"]
    [@c.search.adminclassName/]
    [@c.search.teacherName title="辅导员名称"/]
  [#elseif type == "th"]
    <th>班级代码</th>
    <th>班级名称</th>
    <th>辅导员姓名</th>
  [#elseif type == "td"]
    <td>[@b.a href="adminclass-student?f.adminclassId=${item.id}${f.params}"]${item.code}[/@]<td>${item.name}</td><td>${item.name2}</td>
  [/#if]
[/@]