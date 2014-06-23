[#ftl]
[#macro teacherName title="教师姓名"]
<div class="form-group">
  <label class="sr-only" for="f.teacherName">${title}</label>
  <input class="form-control" id="f.teacherName" name="f.teacherName" value="${(f.teacherName)!}" placeholder="请输入${title}">
</div>
[/#macro]

[#macro studentName]
<div class="form-group">
  <label class="sr-only" for="f.studentName">学生姓名</label>
  <input class="form-control" id="f.studentName" name="f.studentName" value="${(f.studentName)!}" placeholder="请输入学生姓名">
</div>
[/#macro]

[#macro adminclassName]
<div class="form-group">
  <label class="sr-only" for="f.adminclassName">班级名称</label>
  <input class="form-control" id="f.adminclassName" name="f.adminclassName" value="${f.adminclassName!}" placeholder="请输入班级名称">
</div>
[/#macro]