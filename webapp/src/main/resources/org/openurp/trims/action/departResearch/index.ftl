[@b.head/]
[#include "../trims.ftl"/]
<div style="margin:0; border:1px solid #ccc; border-radius:5px; padding:10px;">
  [@b.form action="!research" target="departResearchDiv"  class="form-inline" role="form"]
    [@yearAndDepartCondition years=years/]
    [@b.submit value="查询" class="btn btn-default"/]
  [/@]
  [@b.div id="departResearchDiv" href="!research"/]
</div>
[@b.foot/]