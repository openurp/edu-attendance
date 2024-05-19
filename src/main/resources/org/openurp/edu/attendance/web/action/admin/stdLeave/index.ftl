[#ftl]
[@b.head/]
[@b.toolbar title="学生请假信息"]
[/@]

<div class="search-container">
  <div class="search-panel">
    [@b.form name="stdLeaveForm" action="!search" title="ui.searchForm" target="contentDiv" theme="search"]
       [@base.semester name="stdLeave.semester.id" value=semester label="学年学期"/]
       [@b.textfields names="stdLeave.std.code;学号,stdLeave.std.name;姓名,stdLeave.std.state.grade.code;年级" maxlength="25"/]
       [@b.select name="stdLeave.std.state.department.id" label="院系" items=departs/]
       [@b.select name="stdLeave.leaveType" label="请假类型" items=leaveTypes/]
       [@b.date name="leaveOn" label="请假日期"/]
       [@b.textfield name="stdLeave.reason" label="请假原因"/]
    [/@]
  </div>
  <div class="search-list">
    [@b.div id="contentDiv" href="!search" /]
  </div>
</div>
[@b.foot/]
