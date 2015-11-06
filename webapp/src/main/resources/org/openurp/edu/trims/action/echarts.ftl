[#macro echarts id title='' title2='' names=[] values=[] onclick='' type='bar' xname='' yname='' interval=0 color=true showSeriesLable=true xrotate=-30
 barMinHeight=20 maxAndMin=true series='' height=300 legend='' trigger='item' datas=[]]

[#if (names?size gt 0) || (datas?size gt 0)]
<div id="${id}" style="height:${height}px;">

</div>
<script src="${base}/static/js/echarts/echarts-all.js"></script>
<script type="text/javascript">
        $(function () {
            // 基于准备好的dom，初始化echarts图表
            var myChart = echarts.init(document.getElementById('${id}')); 
            
            var option = {
                title: {
                  text:'${title}'
                  [#if title2 != '']
                  , subtext : '${title2}'
                  [/#if], padding: 0},
                //renderAsImage:true,
                [#if legend != '']
                legend: {
                    data:${legend}
                },
                [/#if]
                
                [#if type != "pie"]
                xAxis : [
                    {
                        [#if xname != '']name : '${xname}',[/#if]
                        type : 'category',
                        start:0,
                        axisLabel:{interval:'${interval}', rotate:${xrotate}},
                        [#if datas?size gt 0]
                        data : [[#list datas as d][#if d_index gt 0],[/#if]'${d[0]}'[/#list]]
                        [#else]
                        data : [[#list names as d][#if d_index gt 0],[/#if]'${d}'[/#list]]
                        [/#if]
                    }
                ],
                yAxis : [
                    {
                        scale:true,
                        [#if yname != '']name : '${yname}',[/#if]
                        type : 'value'
                    }
                ],
                tooltip : {
                  trigger: '${trigger}',
                  axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                    type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                  }
                },
                [#else]//pie
                tooltip : {
                    trigger: 'item',
                    formatter: "{a} <br/>{b} : {c} ({d}%)"
                },
                calculable : true,
                [/#if]
                [#if series == '']
                series : [
                    {
                        type:"${type}",
                        barMinHeight: ${barMinHeight},
                        smooth:true,
                        [#if type == "pie"]
                          data:[
                          [#list datas as d]
                            {value: ${d[1]},  name:'${d[0]}'}[#if d_has_next],[/#if]
                          [/#list]
                          ],
                        [#else]
                          itemStyle: {
                              normal: {
                                  [#if color]
                                  color: function(params) {
                                      // build a color map as your need.
                                      var colorList = [
                                        '#C1232B','#B5C334','#FCCE10','#E87C25','#27727B',
                                         '#FE8463','#9BCA63','#FAD860','#F3A43B','#60C0DD',
                                         '#D7504B','#C6E579','#F4E001','#F0805A','#26C0C0'
                                      ];
                                      return colorList[params.dataIndex%colorList.length]
                                  },
                                  [/#if]
                                  label: {
                                      show: ${showSeriesLable?string},
                                      position: 'top',
                                      formatter: '{c}'
                                  }
                              }
                          },
                          [#if datas?size gt 0]
                            "data":[[#list datas as d][#if d_index gt 0],[/#if]${d[1]}[/#list]],
                          [#else]
                            "data":[[#list values as d][#if d_index gt 0],[/#if]${d}[/#list]],
                          [/#if]
                        [/#if]
                        [#if maxAndMin]
                        markPoint : {
                            data : [
                                {type : 'max', name: '最大值'},
                                {type : 'min', name: '最小值'}
                            ]
                        },
                        [/#if]
                        markLine : {
                            data : [
                                {type : 'average', name: '平均值'}
                            ]
                        }
                    }
                ]
                [#else]
                series : ${series}
                [/#if]
            };
            // 为echarts对象加载数据 
            myChart.setOption(option); 
            [#if onclick != '']
            myChart.on('click', function (param){
              if(param.name == '最大值' || param.name == '最小值') return;
              ${onclick}(param)
            });
            [/#if]
        });
</script>
[#else]
<div style="padding:100px; font-size:20px; text-align:center">暂无数据</div>
[/#if]
[/#macro]

[#macro beginAndEnd][#if beginYear?? && beginYear!=""]${beginYear}年[/#if][#if endYear?? && endYear!="" && ((beginYear!) != endYear)][#if beginYear?? && beginYear!=""]到[/#if]${endYear}年[/#if][/#macro]