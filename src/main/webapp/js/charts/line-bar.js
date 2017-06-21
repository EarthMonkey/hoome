/**
 * Created by L.H.S on 2017/6/15.
 */

function getLineBar(id, data) {

    var chart = echarts.init(document.getElementById(id));

    var option = {
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'cross',
                crossStyle: {
                    color: '#999'
                }
            }
        },
        toolbox: {
            feature: {
                dataView: {show: true, readOnly: false},
                magicType: {show: true, type: ['line', 'bar']},
                restore: {show: true},
                saveAsImage: {show: true}
            }
        },
        legend: {
            data: ['当月销售额', '总销售额']
        },
        xAxis: [
            {
                type: 'category',
                data: ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月'],
                axisPointer: {
                    type: 'shadow'
                }
            }
        ],
        yAxis: [
            {
                type: 'value',
                name: '月销售额',
                axisLabel: {
                    formatter: '{value}'
                }
            },
            {
                type: 'value',
                name: '总销售额',
                axisLabel: {
                    formatter: '{value}'
                }
            }
        ],
        series: [
            {
                name: '当月销售额',
                type: 'bar',
                data: data[0]
            },
            {
                name: '总销售额',
                type: 'line',
                yAxisIndex: 1,
                data: data[1]
            }
        ]
    };

    chart.setOption(option);
}