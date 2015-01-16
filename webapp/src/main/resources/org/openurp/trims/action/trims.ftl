[#ftl]
[#macro yearAndDepartCondition years term=false]
  起止时间：<select name="beginYear"id="beginYear" class="form-control">
  <option value="">...</option>
  [#list years as d]<option>${d}</option>[/#list]</select>
  ～<select name="endYear" id="endYear" class="form-control"><option value="">...</option>
  [#list years as d]<option>${d}</option>[/#list]</select>
  <a id="jsnBtn" class="btn btn-default">近三年</a>
  <div class="btn-group departBtn pull-right">
    <button type="button" class="btn btn-default departBtn" value="1">教学部门</button>
    <button type="button" class="btn btn-default departBtn" value="0">职能部门</button>
    <button type="button" class="btn btn-default departBtn active" value="">全部</button>
  </div>
  <input type="hidden" id="teachingIpt" name="teaching" value=""/>
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
      bg.form.submit($('#beginYear').closest('form').attr('id'),null,null,null);
      return false;
    }
    $('#beginYear').closest('form').addClass('form-inline');
  </script>
[/#macro]

[#macro termAndDepartCondition years]
  [@yearAndDepartCondition years=years term=true/]
[/#macro]
