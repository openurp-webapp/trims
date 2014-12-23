[@b.head/]
<div style="max-width:1000px; margin:10px auto; border:1px solid #ccc; border-radius:5px; padding:10px;">
  [@b.form action="!research" target="departResearchDiv"]
    起始年份：<select name="beginYear"id="beginYear"><option value="">请选择起始年份</option>[#list years as d]<option>${d}</option>[/#list]</select>
    截止年份：<select name="endYear" id="endYear"><option value="">请选择截止年份</option>[#list years as d]<option>${d}</option>[/#list]</select>
    [@b.submit value="查询"/]
  [/@]
  [@b.div id="departResearchDiv" href="!research"/]
  <script>
    $('#beginYear').change(function (){
      var beginYear = this.value
      var endYear = $('#endYear')
      endYear.val('')
      endYear.find('option').show().filter(function (){
        return this.value < beginYear
      }).hide()
    });
  </script>
</div>
[@b.foot/]