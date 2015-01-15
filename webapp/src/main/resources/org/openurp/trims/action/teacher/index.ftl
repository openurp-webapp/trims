[@b.head/]
<div style="text-align:center;">
  <div align="center"><b class="LargerText">全校教师信息一览表</b>(点击查看详细信息)</div>
  [@b.form name="teacherSearchForm" action="!search" target="teacherlist" title="ui.searchForm"]
    教师姓名<input name="teacher.person.name" maxlength="15" size="8" value="" type="text"/>
    所属部门
    [@b.select items=departments selected="" name="teacher.person.department.id" style="width:150px;"]
      <option value="">...</option>
    [/@]
    [@b.submit value="查询"/]
  [/@]
</div>
[@b.div id="teacherlist" href="!search" /]
[@b.foot/]