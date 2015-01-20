[#ftl]
[#macro yearAndDepartCondition years=[] term=false teaching=""]
  [#if years?size gt 0]
  <b>起止时间</b>：<select name="beginYear"id="beginYear" class="form-control">
  <option value="">...</option>
  [#list years as d][#if term]<option value="${d[0]}">${d[1]}</option>[#else]<option>${d}</option>[/#if][/#list]</select>
  ～<select name="endYear" id="endYear" class="form-control"><option value="">...</option>
  [#list years as d][#if term]<option value="${d[0]}">${d[1]}</option>[#else]<option>${d}</option>[/#if][/#list]</select>
  <a id="jsnBtn" class="btn btn-default">近三年</a>
  [/#if]
  <div class="btn-group departBtn pull-right" style="z-index:999;">
    <button type="button" class="btn btn-default departBtn [#if teaching?string == "1"]active[/#if]" value="1">教学部门</button>
    <button type="button" class="btn btn-default departBtn [#if teaching?string == "0"]active[/#if]" value="0">职能部门</button>
    <button type="button" class="btn btn-default departBtn [#if teaching?string == ""]active[/#if]" value="">全部</button>
  </div>
  <input type="hidden" id="teachingIpt" name="teaching" value="${teaching}"/>
  <script>
    $('#beginYear').change(function (){
      var beginYear = this.value
      var endYear = $('#endYear')
      if(beginYear > endYear.val()) endYear.val('')
      endYear.find('option').show().filter(function (){
        return this.value < beginYear && this.value != ''
      }).hide()
    });
    $('#jsnBtn').click(function (){
      var opts = $('#beginYear option')
      opts.eq(opts.length - [#if term]6[#else]3[/#if]).attr("selected", "1");
      opts = $('#endYear option')
      opts.eq(opts.length - 1).attr("selected", "1");
      return submitSearchForm();
      return false;
    });
    $('.departBtn').click(function (){
      $('#teachingIpt').val(this.value);
      $('.departBtn').removeClass('active');
      $(this).addClass('active');
      return submitSearchForm();
    });
    function submitSearchForm(){
      bg.form.submit($('#teachingIpt').closest('form').attr('id'),null,null,null);
      return false;
    }
    $('#beginYear').closest('form').addClass('form-inline');
  </script>
[/#macro]

[#macro termAndDepartCondition years=[] teaching=""]
  [@yearAndDepartCondition years=years term=true teaching=teaching/]
[/#macro]

[#macro beginYearAndEndYear][#if beginYear??][@showYearOrYearAndTerm beginYear/][/#if][#if beginYear?? && endYear??] 到 [/#if][#if endYear??][@showYearOrYearAndTerm endYear/][/#if][/#macro]
[#macro showYearOrYearAndTerm year][#if year?length gt 4]${year[0..3]}-${year[4]}[#else]${year}[/#if][/#macro]
