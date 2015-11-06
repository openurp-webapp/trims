[@b.head/]
[#include "../trims.ftl"/]
<div style="margin:0; border:1px solid #ccc; border-radius:5px; padding:10px;">
  [@b.form action="!list" target="departTeacherDiv" style="font-size:14px;"]
    [@termAndDepartCondition teaching=1/]
  [/@]
  [@b.div id="departTeacherDiv" href="!list?teaching=1"/]
</div>
[@b.foot/]