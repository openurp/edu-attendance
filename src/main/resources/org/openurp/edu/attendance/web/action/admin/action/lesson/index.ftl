[#ftl]
[#import "../../../comm.ftl" as c/]
[@c.body title="课程列表";type, item]
  [#if type == "nav"]
    <ol class="breadcrumb">
      <li>[@b.a href="college"]首页[/@]</li>
      <li>[@b.a href="teacher?f.kkxyId=${college.id}${f.dayParams}"]${college.name}[/@]</li>
      <li class="active">${teacher.name}</li>
    </ol>
  [#elseif type == "th"]
    <th>课程序号</th>
    <th>课程名称</th>
    <th>教学班名称</th>
  [#elseif type == "td"]
    <td>[@b.a href="student?f.jxrwId=${item.id}${f.params}"]${item.code}[/@]</td>
    <td width="200">${item.name}</td>
    <td width="200">${item.name2!}</td>
  [/#if]
[/@]
