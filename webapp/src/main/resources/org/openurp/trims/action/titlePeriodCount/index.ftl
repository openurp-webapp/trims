[@b.head/]

[#include "../trims.ftl"/]

<div style="margin:0; border:1px solid #ccc; border-radius:5px; padding:10px;">
  [@b.form action="!search" target="titlePeriodDiv" style="font-size:14px;"]
    [@termAndDepartCondition teaching=-1/]
  [/@]
  [@b.div id="titlePeriodDiv" href="!search"/]
</div>
[@b.foot/]