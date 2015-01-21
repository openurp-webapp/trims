[@b.head/]
[#include "../trims.ftl"/]
<div style="margin:0; border:1px solid #ccc; border-radius:5px; padding:10px;">
  [@b.form action="!search" target="studentAreaMapDiv"]
    [@btns name="sfzx" datas=[[1, '在校生'],['', '全部']] value = '1'/]
  [/@]
  [@b.div id="studentAreaMapDiv" href="!search"/]
</div>
[@b.foot/]