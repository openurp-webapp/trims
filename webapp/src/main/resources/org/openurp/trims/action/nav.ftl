[#ftl]
[#macro nav name]
<style>
.nav{padding:0px;}
.nav li{float:left; padding: 5 10px;}
.nav li{border-bottom:1px solid #aaa;}
.nav li a{text-decoration:none;}
.nav li.active{border:1px solid #aaa;border-bottom:0px;}
</style>
<ul class="nav">
  <li role="presentation" class="a"><a href="#">Home</a></li>
  <li role="presentation" class="b"><a href="#">Profile</a></li>
  <li role="presentation"><a href="#">Messages</a></li>
</ul>
<script>$(".${name}").addClass('active');</script>
[/#macro]