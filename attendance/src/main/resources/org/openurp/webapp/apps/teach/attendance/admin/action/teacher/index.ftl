[#ftl]
[#import "../../../comm.ftl" as c/]
[@b.head/]
<form>
  <ol class="breadcrumb">
    <li>[@b.a href="college"]首页[/@]</li>
    <li class="active">国际金融学院</li>
  </ol>
  [@c.days/]
</form>
<div class="panel panel-default">
  <div class="panel-heading">统计指标</div>
  <div class="panel-body">
    <table class="table table-bordered" style="text-align:center">
      <tr>
        <td rowspan="2">应签到人次：0</td>
        <td>签到人次：0</td>
        <td>出勤人次：0</td>
        <td>迟到人次：0</td>
        <td>请假人次：0</td>
        <td>缺勤人次：0</td>
      </tr>
      <tr>
        <td>签到率：0</td>
        <td>出勤率：0</td>
        <td>迟到率：0</td>
        <td>请假率：0</td>
        <td>缺勤率：0</td>
      </tr>
    </table>
  </div>
</div>
<div class="panel panel-default">
  <div class="panel-heading">教师列表</div>
  <div class="panel-body">
    <table class="table table-bordered">
      <thead>
        <tr>
          <th>教职工号</th>
          <th>教师姓名</th>
          <th>应签到人次</th>
          <th>签到人次</th>
          <th>出勤总人次</th>
          <th>迟到总人次</th>
          <th>缺勤总人次</th>
          <th>请假总人次</th>
          <th>签到率</th>
          <th>缺勤率</th>
        </tr>
      </thead>
      <tr>
      <td>[@b.a href="lesson"]20121211[/@]<td>老师A</td>[#list 1..8 as v]<td>${v}</td>[/#list]
      </tr>
      <tr>
      <td>[@b.a href="lesson"]20121212[/@]<td>老师B</td>[#list 1..8 as v]<td>${v}</td>[/#list]
      </tr>
    </table>
  </div>
</div>
[@b.foot/]