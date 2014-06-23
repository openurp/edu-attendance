[#ftl]
[#import "../../../comm.ftl" as c/]
[@c.body title="学院列表";type, item]
  [#if type == "nav"]
    <ol class="breadcrumb">
      <li>首页</li>
    </ol>
  [#elseif type == "th"]
    <th>上课院系</th>
  [#elseif type == "td"]
    <td>[@b.a href="adminclass?f.departmentId=${item.id!}${f.params}"]${item.name!}[/@]</td>
  [/#if]
[/@]