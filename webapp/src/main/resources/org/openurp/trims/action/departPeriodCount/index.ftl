[@b.head/]
<div style="max-width:1000px; margin:10px auto; border:1px solid #ccc; border-radius:5px; padding:10px;">
  [@b.form action="!period" target="departPeriodDiv"]
    学年：<select name="year"><option value="">请选择学年</option>[#list datas as d]<option>${d}</option>[/#list]</select>
    学期：<select name="term"><option value="">请选择学期</option><option>1</option><option>2</option></select>
    [@b.submit value="查询"/]
  [/@]
  [@b.div id="departPeriodDiv" href="!period"/]
</div>
[@b.foot/]