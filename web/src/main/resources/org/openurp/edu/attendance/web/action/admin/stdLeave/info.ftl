[#ftl]
[@b.head/]
[@b.toolbar title="请假信息"]
  bar.addBack("${b.text("action.back")}");
[/@]
<table class="infoTable">
  <tr>
    <td class="title" width="15%">学号:</td>
    <td class="content">${leave.std.code}</td>
    <td class="title" width="15%">姓名:</td>
    <td class="content">${leave.std.name}</td>
    <td class="title" width="15%">院系:</td>
    <td class="content">${leave.std.department.name}</td>
  </tr>
  <tr>
    <td class="title">培养层次:</td>
    <td class="content">${leave.std.level.name}</td>
    <td class="title">学生类别:</td>
    <td class="content">${leave.std.stdType.name}</td>
    <td class="title">专业:</td>
    <td class="content">${leave.std.major.name}${(leave.std.direction.name)!}</td>
  </tr>
  <tr>
    <td class="title">请假类别:</td>
    <td class="content">${leave.leaveType.name}</td>
    <td class="title">请假时间:</td>
    <td class="content"> ${leave.beginAt?string("yyyy-MM-dd HH:mm")}~${leave.endAt?string("yyyy-MM-dd HH:mm")}</td>
    <td class="title">请假天数:</td>
    <td class="content">${leave.days}</td>
  </tr>
  <tr>
    <td class="title">请假事由:</td>
    <td class="content" colspan="5">${leave.reason}</td>
  </tr>
</table>

[@b.grid items=lessons var="l"]
  [@b.row]
    [@b.col title="序号" width="5%"]${l_index+1}[/@]
    [@b.col title="课程"]${l.clazz.crn} ${l.clazz.course.name}[/@]
    [@b.col title="任课教师" width="10%"][#list l.clazz.teachers as t]${t.name}[#sep]&nbsp;[/#list][/@]
    [@b.col title="时间" width="20%"]${l.lessonOn?string("yyyy-MM-dd")} ${l.lessonTime}[/@]
  [/@]
[/@]
