[#ftl]
[#import "search.ftl" as search/]
[#macro days search id="daysdiv"]
  <div style="margin-bottom:5px;">
    <div class="btn-group" data-toggle="buttons">
      [#assign btns=["今天","昨天","最近7天","最近30天","本学期","自定义时间段"]/]
      [#assign btnvals=[0,-1,-7,-30,1,2]/]
      [#list btns as btn]
        <label class="btn btn-default daylabel [#if f.days == btnvals[btn_index]]active[/#if]" id="${id}day${btn_index}">
          <input type="radio" name="f.days" value="${btnvals[btn_index]}" [#if f.days == btnvals[btn_index]]checked="true"[/#if]/> ${btn}
        </label>
      [/#list]
    </div>
    <div style="display:inline-block;">[#nested/]</div>
    <div id="${id}customer" style="padding-top:5px; display:none">
      [@b.startend label="开始时间,结束时间" name="f.startDate,f.endDate" start=f.startDate end=f.endDate required="false,false" format="date"/]
      [#if !search]
      <input type="submit" value="查询"/>
      [/#if]
    </div>
  </div>
  <script>
    $(function() {
      $(".daylabel").last().click(function (){
        $(this).find("input").get(0).checked = true
        $("#${id}customer").slideDown();
      }).siblings().click(function (){
        $(this).find("input").get(0).checked = true
        $(this).closest("form").submit();
      });
      [#if f.days == 2]
      $("#${id}customer").show();
      [/#if]
    });
  </script>
[/#macro]

[#macro count]
<div class="panel panel-default">
  <div class="panel-heading">统计指标</div>
  <table class="table table-bordered" style="text-align:center">
    <tr>
      <td rowspan="2">应签到人次：${item.total?string(",###")}</td>
      <td>签到人次：${item.signNum?string(",###")}</td>
      <td>出勤人次：${item.attendNum?string(",###")}</td>
      <td>迟到人次：${item.lateNum?string(",###")}</td>
      <td>请假人次：${item.offNum?string(",###")}</td>
      <td>缺勤人次：${item.absenceNum?string(",###")}</td>
    </tr>
    <tr>
      <td>签到率：${item.signRate}%</td>
      <td>出勤率：${item.attendRate}%</td>
      <td>迟到率：${item.lateRate}%</td>
      <td>请假率：${item.offRate}%</td>
      <td>缺勤率：${item.absenceRate}%</td>
    </tr>
  </table>
</div>
[/#macro]

[#macro table title ths]
<div class="panel panel-default">
  <div class="panel-heading">${title}</div>
  <style>
    .itemTable{}
    .itemTable .sort{cursor:pointer; color:#000080;}
    .itemTable .sort:hover{background-color:#eee;}
  </style>
  <table class="table table-bordered table-striped table-hover itemTable">
    <thead>
      <tr>
        [#nested "th"/]
        [#if ths]
        <th>应签到人次</th>
        <th>签到人次</th>
        <th>出勤总人次</th>
        <th>迟到总人次</th>
        <th>缺勤总人次</th>
        <th>请假总人次</th>
        <th class="sort" sortBy="signRate">签到率 [#if "signRate" == f.sortBy! ]<span class="glyphicon glyphicon-arrow-down"></span>[/#if]</th>
        <th class="sort" sortBy="absenceRate">缺勤率[#if "absenceRate" == f.sortBy! ]<span class="glyphicon glyphicon-arrow-down"></span>[/#if]</th>
        [/#if]
      </tr>
    </thead>
    <tbody>
      [#assign total = 0/]
      [#list items! as item]
      <tr>
        [#nested "td" item/]
        [#if ths]
        [#assign total = total + item.total/]
        <td>${item.total?string(",###")}</td>
        <td>${item.signNum?string(",###")}</td>
        <td>${item.attendNum?string(",###")}</td>
        <td>${item.lateNum?string(",###")}</td>
        <td>${item.absenceNum?string(",###")}</td>
        <td>${item.offNum?string(",###")}</td>
        <td align="right">${item.signRate}%</td>
        <td align="right">${item.absenceRate}%</td>
        [/#if]
      </tr>
      [/#list]
    </tbody>
  </table>
  <input type="hidden" id="sortByIpt" name="f.sortBy" value="${f.sortBy!}"/>
  <script>
    $(".itemTable .sort").click(function (){
      $("#sortByIpt").val($(this).attr("sortBy"));
      $(this).closest("form").submit();
    });
  </script>
</div>
[#--
<div class="alert alert-info">总应签到次数：${total}</div>
--]
[/#macro]

[#macro body title search=false days=true ths=true]
[@b.head/]
<form action="${formAction}" class="form-inline" role="form" method="post">
  <div style="margin-bottom:20px;">
    [#nested "nav"/]
    [#if days]
      [@c.days search]
        [#if search]
          [#nested "search"/]
          <button type="submit" class="btn btn-primary">查询</button>
        [/#if]
      [/@]
    [/#if]
  </div>
  [@c.count/]
  [@c.table title=title ths=ths; type, data]
    [#if type = "th"]
      [#nested "th"/]
    [#else]
      [#nested "td" data/]
    [/#if]
  [/@]
</form>
[@b.foot/]
[/#macro]
