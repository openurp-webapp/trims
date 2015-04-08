[@b.head/]
<h4>学期平均上课课时:${avg}</h4>
[#assign base=request.contextPath/]
[#if datas?size gt 0]
<div id="lessonLineChart" style="height:250px;">
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
            'echarts/chart/line' // 使用柱状图就加载bar模块，按需加载
        ],
        function (ec) {
            // 基于准备好的dom，初始化echarts图表
            var myChart = ec.init(document.getElementById('lessonLineChart')); 
            
            var option = {
                title : {
                    text: '学期课时',
                },
                tooltip : {
                    trigger: 'axis'
                },
                calculable : true,
                xAxis : [
                    {
                        name:'学年学期',
                        type : 'category',
                        boundaryGap : false,
                        data : [[#list datas as data][#if data_index gt 0],[/#if]'${data[0]}'[/#list]]
                    }
                ],
                yAxis : [
                    {
                        name:'学期课时',
                        type : 'value',
                        axisLabel : {
                            formatter: '{value}'
                        }
                    }
                ],
                series : [
                    {
                        name:'学期课时',
                        type:'line',
                        data:[[#list datas as data][#if data_index gt 0],[/#if]${data[1]}[/#list]],
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
        }
    );
</script>
[#else]
<div style="padding:100px; font-size:20px; text-align:center">暂无数据</div>
[/#if]
[@b.div id="lessonDiv" href="!lessonIndex?id=${staff.id}"/]
[@b.foot/]