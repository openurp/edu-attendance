[#ftl]
[#import "../../../comm.ftl" as c/]
[@c.body title="签到列表" ths=false days=false;type, item]
  [#if type == "nav"]
    <ol class="breadcrumb">
      <li>[@b.a href="lesson"]首页[/@]</li>
      <li class="active">${lesson.name}</li>
    </ol>
  [#elseif type == "th"]
    <th>上课日期</th>
    <th>签到时间</th>
    <th>教室名称</th>
    <th>签到状态</th>
  [#elseif type == "td"]
    <td align="center">${item[0]?string('yyyy-MM-dd')}</td>
    <td align="center">${item[1]!}</td>
    <td align="center">${item[2]!}</td>
    <td align="center">${item[3]}</td>
  [/#if]
[/@]
