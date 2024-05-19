[#ftl]
[#import "../../../comm.ftl" as c/]
[@c.body title="班级列表" search=true;type, item]
  [#if type == "nav"]
    <ol class="breadcrumb">
      <li>首页</li>
    </ol>
  [#elseif type == "search"]
    [@c.search.adminclassName/]
  [#elseif type == "th"]
    <th>班级代码</th>
    <th>班级名称</th>
  [#elseif type == "td"]
    <td>[@b.a href="student?f.adminclassId=${item.id}${f.params}"]${item.code}[/@]<td>${item.name}</td>
  [/#if]
[/@]
