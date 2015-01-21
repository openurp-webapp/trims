[@b.head/]

[#if datas?size gt 0]
<div id="echartDiv" style="height:600px;">

</div>
<script src="${base}/static/js/echarts/echarts-all.js"></script>
<script type="text/javascript">
        $(function () {
            // 基于准备好的dom，初始化echarts图表
            var myChart = echarts.init(document.getElementById('echartDiv')); 
            
            option = {
              title : {
                  text: '生源地统计',
                  x:'center'
              },
              tooltip : {
                  trigger: 'item'
              },
              dataRange: {
                  min: 0,
                  max: ${max},
                  x: 'left',
                  y: 'bottom',
                  text:['高','低'],           // 文本，默认为数值文本
                  calculable : true
              },
              roamController: {
                  show: true,
                  x: 'right',
                  mapTypeControl: {
                      'china': true
                  }
              },
              series : [
                  {
                      name: '学生数',
                      type: 'map',
                      mapType: 'china',
                      roam: false,
                      itemStyle:{
                          normal:{label:{show:true}},
                          emphasis:{label:{show:true}}
                      },
                      data:[
                          [#list datas as data]
                          {name: '${data[0]?replace('省','')?replace('市','')?replace('自治区','')?replace('维吾尔','')?replace('壮族','')?replace('回族','')?replace('特别行政区','')?replace('维吾尔','')}',value: ${data[1]}}[#if data_has_next],[/#if]
                          [/#list]
                      ]
                  }
              ]
          };
          // 为echarts对象加载数据 
          myChart.setOption(option); 
          myChart.on('click', function (param){
            bg.Go('${b.url('!depart')}?sfzx=${(sfzx?string(1,0))!}&area='+param.name,'studentAreaDepartDiv')
          });
        });
</script>
[#else]
<div style="padding:100px; font-size:20px; text-align:center">暂无数据</div>
[/#if]
[@b.div id="studentAreaDepartDiv"/]
[@b.foot/]