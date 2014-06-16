[#ftl]
[#macro days id="daysdiv"]
  <div class="btn-group" data-toggle="buttons">
    [#assign btns=["今天","昨天","最近7天","最近30天","本学期","自定义时间段"]/]
    [#assign btnvals=["0","-1","-7","-30","-180",""]/]
    [#list btns as btn]
      <label class="btn btn-default daylabel" id="${id}day${btn_index}">
        <input type="radio" name="${id}day" value="${btnvals[btn_index]}"/> ${btn}
      </label>
    [/#list]
  </div>
  <div id="${id}customer">
    [@b.startend label="开始时间,结束时间" name="assessSession.voteBeginOn,assessSession.voteEndOn" required="false,false" format="datetime"/]
  </div>
  <script>
    $(function() {
      $(".daylabel").last().click(function (){
        $("#${id}customer").slideDown();
      }).siblings().click(function (){
        $("#${id}customer").slideUp();
      });
      $("#${id}customer").hide();
    });
  </script>
[/#macro]