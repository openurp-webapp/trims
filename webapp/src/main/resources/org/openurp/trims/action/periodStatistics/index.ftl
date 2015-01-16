[@b.head/]
[#include "../trims.ftl"/]
<div style="margin:0; border:1px solid #ccc; border-radius:5px; padding:10px;">
  [@b.form action="!period" target="periodChartDiv"]
  
    [@termAndDepartCondition years=years/]
    [#--
    学年：<select name="year"><option value="">请选择学年</option>[#list datas as d]<option>${d}</option>[/#list]</select>
    学期：<select name="term"><option value="">请选择学期</option><option>1</option><option>2</option></select>
    --]
    部门：<select name="departmentId" class="form-control"><option value="">请选择部门</option>[#list departs as depart]<option value="${depart.id}">${depart.name}</option>[/#list]</select>
    [@b.submit value="查询" class="btn btn-default"/]
  [/@]
  [@b.div id="periodChartDiv" href="!period"/]
</div>
[@b.foot/]