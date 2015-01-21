[@b.head/]
[#include "../trims.ftl"/]
<div style="margin:0; border:1px solid #ccc; border-radius:5px; padding:10px;">
  [@b.form action="!depart" target="departResearchDiv"  class="form-inline" role="form" style="font-size:14px;"]
       <b>毕业季</b>：<select name="seasonId"  id="seasonId" class="form-control">
       [#list seasons as season]<option value="${season[0]}">${season[1]}</option>[/#list]
       </select>
    [@b.submit value="查询" class="btn btn-default"/]
  [/@]
  [@b.div id="departResearchDiv" href="!depart?seasonId=${seasons?first[0]}"/]
  <script>
    $('#seasonId').closest('form').addClass('form-inline');
  </script>
</div>
[@b.foot/]