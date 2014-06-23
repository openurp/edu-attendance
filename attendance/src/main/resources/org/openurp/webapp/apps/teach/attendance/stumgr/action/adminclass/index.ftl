[#ftl]
[#import "../../../comm.ftl" as c/]
[@c.body title="班级列表" search=true;type, item]
  [#if type == "nav"]
    <ol class="breadcrumb">
      <li>[@b.a href="college?${f.dayParams}"]首页[/@]</li>
      <li class="active">${college.name}</li>
    </ol>
  [#elseif type == "search"]
    [#--
      <div class="form-group">
        <label class="sr-only" for="f.majorName">专业名称</label>
        <input class="form-control" id="f.majorName" name="f.majorName" value="${f.majorName!}" placeholder="请输入专业名称">
      </div>
    --]
    [@c.search.adminclassName/]
    [@c.search.teacherName title="辅导员名称"/]
  [#elseif type == "th"]
    <th>班级代码</th>
    <th>班级名称</th>
    <th>辅导员姓名</th>
  [#elseif type == "td"]
    <td>[@b.a href="student?f.adminclassId=${item.id}${f.params}"]${item.code}[/@]<td>${item.name}</td><td>${item.name2}</td>
  [/#if]
[/@]