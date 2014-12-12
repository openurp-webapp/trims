[@b.head/]
[#assign base=request.contextPath/]
<div id="departTeacherChart" style="height:400px;">
</div>
<script src="${base}/static/js/echarts/echarts-all.js"></script>
<script type="text/javascript">
        $(function () {
            // 基于准备好的dom，初始化echarts图表
            var myChart = echarts.init(document.getElementById('departTeacherChart')); 
            
            var option = {
                title: {text:'部门人数统计'},
                //renderAsImage:true,
                tooltip: {
                    show: true
                },
                xAxis : [
                    {
                        name : "部门",
                        type : 'category',
                        axisLabel:{interval:0, rotate:-25},
                        data : [[#list datas as d][#if d_index gt 0],[/#if]'${dempartmentMap[d[0]?string]}'[/#list]]
                    }
                ],
                yAxis : [
                    {
                        name : "教师人数",
                        type : 'value'
                    }
                ],
                series : [
                    {
                        "name":"教师人数",
                        "type":"bar",
                        barMinHeight: 20,
                        itemStyle: {
                            normal: {
                                color: function(params) {
                                    // build a color map as your need.
                                    var colorList = [
                                      '#C1232B','#B5C334','#FCCE10','#E87C25','#27727B',
                                       '#FE8463','#9BCA63','#FAD860','#F3A43B','#60C0DD',
                                       '#D7504B','#C6E579','#F4E001','#F0805A','#26C0C0'
                                    ];
                                    return colorList[params.dataIndex%colorList.length]
                                },
                                label: {
                                    show: true,
                                    position: 'top',
                                    formatter: '{c}'
                                }
                            }
                        },
                        "data":[[#list datas as d][#if d_index gt 0],[/#if]${d[1]}[/#list]],
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