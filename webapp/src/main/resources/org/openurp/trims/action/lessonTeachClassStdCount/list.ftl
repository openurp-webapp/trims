[@b.head/]
[#if datas?size gt 0]
<div id="lessonTrimsChart" style="height:300px;">

</div>
<script src="${base}/static/js/echarts/echarts-all.js"></script>
<script type="text/javascript">
        $(function () {
            // 基于准备好的dom，初始化echarts图表
            var myChart = echarts.init(document.getElementById('lessonTrimsChart')); 
            
            var option = {
                title: {text:'[#if year??]${year}学年[/#if][#if term??]  第${term}学期[/#if][#if department??]  ${department.name}[/#if] 平均值：${avg} 标准差：${standardDeviation}'},
                //renderAsImage:true,
                tooltip: {
                    show: true
                },
                xAxis : [
                    {
                        type : 'category',
                        data : [[#list datas as d][#if d_index gt 0],[/#if]'${d[0]?string}'[/#list]]
                    }
                ],
                yAxis : [
                    {
                        type : 'value'
                    }
                ],
                series : [
                    {
                        "name":"班级数量",
                        "type":"bar",
                        "data":[[#list datas as d][#if d_index gt 0],[/#if]${d[1]}[/#list]],
                        markPoint : {
                            data : [
                                {type : 'max', name: '最大值'},
                                {type : 'min', name: '最小值'}
                            ]
                        },
                        markLine : {
                            data : [
                                {type : 'average', name: '平均值'}
                            ]
                        }
                    }
                ]
            };
            var departmentIds = [[#list datas as d][#if d_index gt 0],[/#if]${d[0]}[/#list]]
            // 为echarts对象加载数据 
            myChart.setOption(option); 
        });
</script>
[#else]
<div style="padding:50px; text-align:center; font-size:16px;">暂无数据</div>
[/#if]
[@b.foot/]