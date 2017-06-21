/**
 * Created by L.H.S on 2017/6/22.
 */

function getBigBar(id, data, lbl) {

    var chart = echarts.init(document.getElementById(id));

    var option = {
        toolbox: {
            // y: 'bottom',
            feature: {
                magicType: {
                    type: ['stack', 'tiled']
                },
                dataView: {},
                saveAsImage: {
                    pixelRatio: 2
                }
            }
        },
        tooltip: {},
        xAxis: {
            data: data[0],
            silent: false,
            splitLine: {
                show: false
            }
        },
        yAxis: {
        },
        series: [{
            name: lbl,
            type: 'bar',
            data: data[1],
            animationDelay: function (idx) {
                return idx * 10;
            }
        }],
        animationEasing: 'elasticOut',
        animationDelayUpdate: function (idx) {
            return idx * 5;
        }
    };

    chart.setOption(option);
}