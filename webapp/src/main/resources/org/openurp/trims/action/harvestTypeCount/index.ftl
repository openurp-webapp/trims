[@b.head/]
[#include "../trims.ftl"/]
<div style="margin:0; border:1px solid #ccc; border-radius:5px; padding:10px;">
  [@b.form action="!thesis" target="harvestTypeDiv" class="form-inline" role="form"]
    [@yearAndDepartCondition years=years/]
    [@b.submit value="查询" class="btn btn-default"/]
  [/@]
  [@b.div id="harvestTypeDiv" href="!thesis"/]
</div>
[@b.foot/]