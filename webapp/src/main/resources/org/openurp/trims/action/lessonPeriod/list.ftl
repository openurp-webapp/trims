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
                title: {text:'[#if year??]${year}学年[/#if][#if term??]  第${term}学期[/#if]'},
                //renderAsImage:true,
                tooltip: {
                    show: true
                },
                xAxis : [
                    {
                        type : 'category',
                        axisLabel:{interval:0, rotate:-25},
                        data : [[#list datas as d][#if d_index gt 0],[/#if]'${dempartmentMap[d[0]?string]}'[/#list]]
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
                        barMinHeight: 20,
                        "data":[[#list datas as d][#if d_index gt 0],[/#if]${d[1]}[/#list]],
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
            myChart.on('click', function (param){
              bg.Go('${b.url('!department')}?did='+departmentIds[param.dataIndex],'LessonDepartmentChartDiv')
            });
        });
</script>
[@b.div id="LessonDepartmentChartDiv"/]
[@b.foot/]