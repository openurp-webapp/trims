[#ftl]
[#macro nav name id]
<style>
.nav{padding:0px;}
.nav li{float:left; padding: 5 10px;}
.nav li{border-bottom:1px solid #aaa;}
.nav li a{text-decoration:none;}
.nav li.active{border:1px solid #aaa;border-bottom:0px;}
</style>
<ul class="nav">
  <li class="a">[@b.a href="teacher-info!info?id=${teacher.id}"]基本信息[/@]</li>
  <li class="b">[@b.a href="teacher-info!info?id=${teacher.id}"]教学情况[/@]</li>
  <li class="c">[@b.a href="teacher-info!info?id=${teacher.id}"]科研情况[/@]</li>
  <li class="d">[@b.a href="teacher-info!info?id=${teacher.id}"]师资情况[/@]</li>
  <li class="e">[@b.a href="teacher-info!info?id=${teacher.id}"]获奖情况[/@]</li>
  <li class="f">[@b.a href="teacher-info!info?id=${teacher.id}"]其他情况[/@]</li>
</ul>
<script>$(".${name}").addClass('active');</script>
[/#macro]