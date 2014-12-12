[#macro echartsLine id title names=[] values=[] onclick='']
[@echarts id=id title=title names=names values=values onclick=onclick type='line'/]
[/#macro]
[#macro echarts id title names=[] values=[] onclick='' type='bar']

<div id="${id}" style="height:300px;">

</div>
<script src="${base}/static/js/echarts/echarts-all.js"></script>
<script type="text/javascript">
        $(function () {
            // 基于准备好的dom，初始化echarts图表
            var myChart = echarts.init(document.getElementById('${id}')); 
            
            var option = {
                title: {text:'${title}'},
                //renderAsImage:true,
                tooltip: {
                    show: true
                },
                xAxis : [
                    {
                        type : 'category',
                        axisLabel:{interval:0, rotate:-25},
                        data : [[#list names as d][#if d_index gt 0],[/#if]'${d}'[/#list]]
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
                        "type":"${type}",
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
                        "data":[[#list values as d][#if d_index gt 0],[/#if]${d}[/#list]],
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
            [#if onclick != '']
            myChart.on('click', ${onclick});
            [/#if]
        });
</script>
[/#macro]