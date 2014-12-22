[@b.head/]
  [#include "../echarts.ftl"/]
  [#assign series]
  [
  [#list values as v][#if v_index gt 0],[/#if]
    {
        name:'[#if v_index == 0]论文[#else]专著[/#if]',
        type:'bar',
        barMinHeight: 10,
        data:[[#list 0..(v?size-1) as i][#if i > 0],[/#if]${v[i]}[/#list]],
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
  [/#list]
  ]
  [/#assign]
  [#assign legend]['论文', '专著'][/#assign]
  [@echarts id="student_year_chart" title="按院系职称统计"
    xname='院系' yname='科研成果数量'
    names=names series=series legend=legend height=500/]
[@b.div id="teacherTitleDepartDiv"/]
[@b.foot/]