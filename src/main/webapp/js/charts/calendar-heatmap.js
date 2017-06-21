/**
 * Created by L.H.S on 2017/6/13.
 */

function getVirtulData(year) {
    year = year || '2017';
    var date = +echarts.number.parseDate(year + '-01-01');
    var end = +echarts.number.parseDate((+year + 1) + '-01-01');
    var dayTime = 3600 * 24 * 1000;
    var data = [];
    for (var time = date; time < end; time += dayTime * 100) {
        data.push([
            echarts.format.formatTime('yyyy-MM-dd', time),
            Math.floor(Math.random() * 10000) + ""
        ]);
    }

    console.log(data)
    return data;
}

function getHeatmap(id, data) {

    var chart = echarts.init(document.getElementById(id));

    var max = 0;
    for (var i = 0; i < data.length; i++) {
        if (data[i][1] > max) {
            max = Math.floor(data[i][1]);
        }
    }

    var option = {
        title: {
            top: 30,
            left: 'center',
            text: '2016年客栈每天的销售额'
        },
        tooltip: {},
        visualMap: {
            min: 0,
            max:  max,
            type: 'piecewise',
            orient: 'horizontal',
            left: 'center',
            top: 65,
            textStyle: {
                color: '#000'
            }
        },
        calendar: {
            top: 120,
            left: 30,
            right: 30,
            cellSize: ['auto', 13],
            range: '2016',
            itemStyle: {
                normal: {
                    borderWidth: 0.5
                }
            },
            yearLabel: {
                show: false
            }
        },
        series: {
            type: 'heatmap',
            coordinateSystem: 'calendar',
            data: data
        }
    };

    chart.setOption(option);

}
