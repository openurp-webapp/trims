[#ftl]
  <style>
  .inav{padding:0px; margin:3px 0;}
  .inav li{text-decoration:none; list-style:none;}
  .inav li{float:left;}
  /*.inav li.active{border:1px solid #aaa;border-bottom:0px;}*/
  .inav li.active{background-color: #eee;}
  .inav1 li a{font-size:14px}
  .inav li{padding: 3px 5px; /*border-bottom:1px solid #aaa;*/}
  .inav2 > li > a{font-size:14px; padding: 2px 3px!important;}
  .inav3 > li > a{font-size:14px; padding: 1px 2px!important;}
  </style>
[#macro nav id level=1]
<ul class="inav inav${level} inav${id}">
[#nested/]
</ul>
<div style="clear:both;"></div>
<script>
  $(".inav${id} li").click(function (){
    $(".inav${level} li").removeClass("active")
    $(this).addClass("active");
  }).first().addClass("active");
</script>
[/#macro]

[#macro nav2 id]
  [@nav id 2]
    [#nested/]
  [/@]
[/#macro]

[#macro nav3 id]
  [@nav id 3]
    [#nested/]
  [/@]
[/#macro]
