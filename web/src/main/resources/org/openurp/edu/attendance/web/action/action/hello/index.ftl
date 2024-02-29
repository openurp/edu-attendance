[#ftl]
[@b.head/]
<table  class="indexpanel">
  <tr>
    <td class="index_view">
    [@b.form name="assessSessionSearchForm"  action="!search" target="assessSessionlist" title="ui.searchForm" theme="search"]
      [@b.textfields names="assessSession.name;批次名称"/]
      [@b.select name="kyxmzx.szdw.id" label="所在单位" value="" empty="..." items=items option="id,name"/]
      [@b.select name="menu.enabled" items=profiles label="common.status" items={'true':'${b.text("action.activate")}','false':'${b.text("action.freeze")}'} empty="..."/]
      [@b.datepicker label="巡检日期" name="startDate" id="startDate" format="date" maxDate="#F{$dp.$D(\\'endDate\\')}"/]
    [/@]
    </td>
    <td class="index_content">[@b.div id="assessSessionlist" href="!search" /]</td>
  </tr>
</table>
[@b.foot/]
