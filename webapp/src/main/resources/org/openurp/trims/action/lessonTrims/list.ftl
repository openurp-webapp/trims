[@b.head/]
[#assign base=request.contextPath/]
<div id="lessonTrimsChart" style="height:400px;">

</div>
<div style="text-align:center; font-size:14px; padding:5px;">
[#if year??]学年：${year}[/#if][#if term??]&nbsp;&nbsp;&nbsp;&nbsp;学期：${term}[/#if]
</div>
<script src="${base}/static/js/echarts/echarts.js"></script>
<script type="text/javascript">
    // 路径配置
    require.config({
        paths: {
            echarts: '${base}/static/js/echarts'
        }
    });
        
    // 使用
    require(
        [
            'echarts',
            'echarts/chart/bar' // 使用柱状图就加载bar模块，按需加载
        ],
        function (ec) {
            // 基于准备好的dom，初始化echarts图表
            var myChart = ec.init(document.getElementById('lessonTrimsChart')); 
            
            var option = {
                //title: '[#if year??]学年：${year}[/#if][#if term??]  学期：${term}[/#if]',
                tooltip: {
                    show: true
                },
                xAxis : [
                    {
                        type : 'category',
                        data : [[#list datas as d][#if d_index gt 0],[/#if]'${d[0]?substring(0,2)}'[/#list]]
                    }
                ],
                yAxis : [
                    {
                        type : 'value'
                    }
                ],
                series : [
                    {
                        "name":"课程数量",
                        "type":"bar",
                        "data":[[#list datas as d][#if d_index gt 0],[/#if]${d[1]}[/#list]]
                    }
                ]
            };
    
            // 为echarts对象加载数据 
            myChart.setOption(option); 
        }
    );
</script>
[@b.foot/]