[@b.head/]
  [#include "../echarts.ftl"/]
  [#assign series]
  [
  [#list values as v][#if v_index gt 0],[/#if]
    {
        name:'${titles[v_index]}',
        type:'bar',
        stack: '${titles[v_index]}',
        barMinHeight: 10,
        data:[[#list 0..(v?size - 1) as i][#if i > 0],[/#if][#if v[i] != 0]${v[i]}[#else]'-'[/#if][/#list]],
        itemStyle: {
            normal: {
                label: {
                    show: true,
                    position: 'top',
                    formatter: '{c}'
                }
            }
        }
    }
  [/#list]
  ]
  [/#assign]
  [#assign legend][[#list titles as t][#if t_index gt 0],[/#if]'${t}'[/#list]][/#assign]
  <script>
    function selectItem(data){
      var schoolYear = data.seriesName;
      var departIds = [[#list departIds as id][#if id_index gt 0],[/#if]${id}[/#list]]
      bg.Go('${b.url('!top10')}?beginYear=${beginYear}&endYear=${endYear}&departId='+departIds[data.dataIndex] + '&schoolYear=' + schoolYear,'teachingQualtyTop10Div')
    }
  </script>
  <h3>
  ${beginYear} ~ ${endYear} 教学部门平均评教统计
  </h3>
  [@echarts id="student_year_chart"  title2="点击图表查看某职称按院系统计"
    xname='院系' yname='教师人数' maxAndMin=false onclick="selectItem"
    names=names series=series legend=legend height=500 trigger='axis'/]
[@b.div id="teachingQualtyTop10Div"/]
[@b.foot/]