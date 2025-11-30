[#ftl]
[@b.head/]
[@b.grid items=stdLeaves var="stdLeave"]
  [@b.gridbar]
    bar.addItem("${b.text('action.add')}",action.add());
    bar.addItem("${b.text('action.edit')}",action.edit());
    bar.addItem("${b.text('action.delete')}",action.remove());
    bar.addItem("导入",action.method('importForm'));
    bar.addItem("生成请假课程",action.multi('generateLeaveLessons'));
  [/@]
  [@b.row]
    [@b.boxcol/]
    [@b.col property="std.code" title="学号"  width="10%"/]
    [@b.col property="std.name" title="姓名"  width="10%"]${(stdLeave.std.name)?if_exists}[/@]
    [@b.col property="std.state.department.name" title="院系" width="10%"]${(stdLeave.std.state.department.shortName)!stdLeave.std.state.department.name}[/@]
    [@b.col property="std.state.major.name" title="专业"]
      ${(stdLeave.std.state.major.name)!}${(stdLeave.std.state.direction.name)!}
    [/@]
    [@b.col property="std.majorTutorNames"  width="10%" title="导师" sortable="false"/]
    [@b.col property="leaveType" title="类型" width="6%"]${(stdLeave.leaveType)!}[/@]
    [@b.col property="beginAt" title="时间" width="20%"]
      <div title="${stdLeave.reason?html}">
        [@b.a href="!info?id="+stdLeave.id]
          ${stdLeave.beginAt?string("yyyy-MM-dd HH:mm")}~${stdLeave.endAt?string("yyyy-MM-dd HH:mm")}
        [/@]
      </div>
    [/@]
    [@b.col property="lessons" title="请假课程" width="6%"/]
    [@b.col property="days" title="请假天数" width="6%"/]
  [/@]
[/@]
[@b.foot/]
