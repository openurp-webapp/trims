[@b.head/]
[#assign base=request.contextPath/]
<div id="lessonTrimsDepartmentChart" style="height:300px;">

</div>
<script src="${base}/static/js/echarts/echarts-all.js"></script>
<script type="text/javascript">
        $(function () {
            // 基于准备好的dom，初始化echarts图表
            var myChart = echarts.init(document.getElementById('lessonTrimsDepartmentChart')); 
            
            var option = {
                title: {text:'${department.name}历年开课情况'},
                //renderAsImage:true,
                tooltip: {
                    show: true
                },
                xAxis : [
                    {
                        type : 'category',
                        axisLabel:{interval:0, rotate:-25},
                        data : [[#list datas as d][#if d_index gt 0],[/#if]'${d[0]?substring(0,4)}-${d[0]?substring(4,5)}'[/#list]]
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
                        type:'line',
                        smooth:true,
                        barMinHeight: 20,
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
            // 为echarts对象加载数据 
            myChart.setOption(option); 
        });
</script>
[@b.foot/]