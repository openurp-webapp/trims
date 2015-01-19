[@b.head/]
[#include "../trims.ftl"/]
<div style="margin:0; border:1px solid #ccc; border-radius:5px; padding:10px;">
  [@b.form action="!period" target="departPeriodDiv" style="font-size:14px;"]
    [@termAndDepartCondition years=years teaching=1/]
    <b>教师类型</b>：<select name="teacherTypeId" class="form-control"><option value="">请选择教师类型</option>[#list teacherTypes as v]<option value="${v.id}">${v.name}</option>[/#list]</select>
    [@b.submit value="查询" class="btn btn-default"/]
  [/@]
  [@b.div id="departPeriodDiv" href="!period?teaching=1"/]
</div>
[@b.foot/]