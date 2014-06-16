[#ftl]
[#import "../../../comm.ftl" as c/]
[@b.head/]
<form>
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
  <div class="panel-heading">学院列表</div>
  <div class="panel-body">
    <table class="table table-bordered">
      <thead>
        <tr>
          <th>上课院系</th>
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
      <td>[@b.a href="teacher"]国际金融学院[/@][#list 1..8 as v]<td>${v}</td>[/#list]
      </tr>
      <tr>
      <td>[@b.a href="teacher"]会计学院[/@]</td>[#list 1..8 as v]<td>${v}</td>[/#list]
      </tr>
    </table>
  </div>
</div>
[@b.foot/]