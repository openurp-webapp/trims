[#ftl]
  <style>
  .nav{padding:0px; margin:3px 0;}
  .nav li{text-decoration:none;}
  .nav li{float:left;}
  /*.nav li.active{border:1px solid #aaa;border-bottom:0px;}*/
  .nav li.active{background-color: #eee;}
  .nav1 li a{font-size:14px}
  .nav li{padding: 3px 5px; /*border-bottom:1px solid #aaa;*/}
  .nav2 > li > a{font-size:14px; padding: 2px 3px!important;}
  .nav3 > li > a{font-size:14px; padding: 1px 2px!important;}
  </style>
[#macro nav id level=1]
<ul class="nav nav${level} nav${id}">
[#nested/]
</ul>
<script>
  $(".nav${id} li").click(function (){
    $(".nav${level} li").removeClass("active")
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
