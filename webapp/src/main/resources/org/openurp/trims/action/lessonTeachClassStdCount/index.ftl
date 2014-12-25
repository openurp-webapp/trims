[@b.head/]
<div style="margin:0; border:1px solid #ccc; border-radius:5px; padding:10px;">
  <h4>筛选条件</h4>
  [@b.form action="!search" target="courseChartDiv"]
    学年：<select name="year"><option value="">请选择学年</option>[#list years as d]<option>${d}</option>[/#list]</select>
    学期：<select name="term"><option value="">请选择学期</option><option>1</option><option>2</option></select>
    部门：<select name="departmentId"><option value="">请选择部门</option>[#list departments as d]<option value="${d.id}">${d.name}</option>[/#list]</select>
    [@b.submit value="查询"/]
  [/@]
  [@b.div id="courseChartDiv" href="!search"/]
</div>
[@b.foot/]