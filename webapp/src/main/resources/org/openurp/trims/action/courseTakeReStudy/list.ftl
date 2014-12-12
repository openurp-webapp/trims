[@b.head/]
[#assign base=request.contextPath/]
<div id="lessonTrimsChart" style="height:300px;">

</div>
<script src="${base}/static/js/echarts/echarts-all.js"></script>
<script type="text/javascript">
        $(function () {
            // 基于准备好的dom，初始化echarts图表
            var myChart = echarts.init(document.getElementById('lessonTrimsChart')); 
            
            var option = {
                tooltip: {
                    show: true
                },
                xAxis : [
                    {
                        type : 'category',
                        axisLabel:{interval:0, rotate:-25},
                        data : [[#list datas2 as d][#if d_index gt 0],[/#if]'${d[0]?string}'[/#list]]
                    }
                ],
                yAxis : [
                    {
                        type : 'value'
                    }
                ],
                series : [
                    {
                        "name":"重修数量",
                        "type":"line",
                        smooth:true,
                        "data":[[#list datas2 as d][#if d_index gt 0],[/#if]${d[1]}[/#list]],
                        markLine : {
                            data : [
                                {type : 'average', name: '平均值'}
                            ]
                        }
                    }
                ]
            };
            // 为echarts对象加载数据 
            myChart.setOption(option); 
        });
</script>
[@b.div id="LessonDepartmentChartDiv"/]
[@b.foot/]