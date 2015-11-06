[@b.head/]
<div style="text-align:center;">
  <div align="center"><b class="LargerText">全校教师信息一览表</b>(点击查看详细信息)</div>
  [@b.form name="staffSearchForm" action="!search" target="stafflist" title="ui.searchForm"]
    教师姓名<input name="staff.person.name" maxlength="15" size="8" value="" type="text"/>
    所属部门
    [@b.select items=departments selected="" name="staff.department.id" style="width:150px;"]
      <option value="">...</option>
    [/@]
    在职状态
    <select name="staff.state.id"><option value="">全部</option><option value="1" selected>在职</option><option value="2">离职</option></select>
    [@b.submit value="查询"/]
  [/@]
</div>
[@b.div id="stafflist" href="!search?staff.state.id=1" /]
[@b.foot/]