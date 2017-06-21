/**
 * Created by L.H.S on 2017/6/13.
 */

function getCountryMap(id, data, lengend_lbl) {

    var selectMode = "multiple";

    if (arguments.length == 2) {
        lengend_lbl = ['第一季度', '第二季度', '第三季度', '第四季度'];
    } else {
        selectMode = "single";
    }

    var lbl1 = lengend_lbl[0];
    var lbl2 = lengend_lbl[1];
    var lbl3 = lengend_lbl[2];
    var lbl4 = lengend_lbl[3];

    var chart = echarts.init(document.getElementById(id));

    var max = [0, 0, 0, 0];
    for (var i = 0; i < data.length; i++) {
        for (var j = 0; j < data[i].length; j++) {
            if (data[i][j].value > max[i]) {
                max[i] = data[i][j].value;
            }
        }

    }

    var option = {
        title: {
            subtext: '2016年',
            x: 'center'
        },
        tooltip: {
            trigger: 'item'
        },
        legend: {
            orient: 'vertical',
            x: 'left',
            data: lengend_lbl,
            selected: {},
            selectedMode: selectMode
        },
        dataRange: {
            min: 0,
            max: max[0],
            x: 'left',
            y: 'bottom',
            text: ['高', '低'],           // 文本，默认为数值文本
            calculable: true
        },
        toolbox: {
            show: true,
            orient: 'vertical',
            x: 'right',
            y: 'center',
            feature: {
                mark: {show: true},
                dataView: {show: true, readOnly: false},
                restore: {show: true},
                saveAsImage: {show: true}
            }
        },
        roamController: {
            show: true,
            x: 'right',
            mapTypeControl: {
                'china': true
            }
        },
        series: [
            {
                name: lbl1,
                type: 'map',
                mapType: 'china',
                roam: false,
                itemStyle: {
                    normal: {label: {show: true}},
                    emphasis: {label: {show: true}}
                },
                data: data[0]
            },
            {
                name: lbl2,
                type: 'map',
                mapType: 'china',
                itemStyle: {
                    normal: {label: {show: true}},
                    emphasis: {label: {show: true}}
                },
                data: data[1]
            },
            {
                name: lbl3,
                type: 'map',
                mapType: 'china',
                itemStyle: {
                    normal: {label: {show: true}},
                    emphasis: {label: {show: true}}
                },
                data: data[2]
            },
            {
                name: lbl4,
                type: 'map',
                mapType: 'china',
                itemStyle: {
                    normal: {label: {show: true}},
                    emphasis: {label: {show: true}}
                },
                data: data[3]
            }
        ]
    };

    option.legend.selected[lbl2] = false;
    option.legend.selected[lbl3] = false;
    option.legend.selected[lbl4] = false;

    chart.setOption(option);

    chart.on('legendselectchanged', function (params) {

        if (selectMode != "single") return;

        var lbl = params.name;
        for (var i = 0; i < 4; i++) {
            if (lengend_lbl[i] == lbl) {
                console.log(i);
                option.dataRange.max = max[i];
                option.legend.selected[lengend_lbl[i]] = true;
                option.legend.selected[lengend_lbl[(i + 1) % 4]] = false;
                option.legend.selected[lengend_lbl[(i + 2) % 4]] = false;
                option.legend.selected[lengend_lbl[(i + 3) % 4]] = false;
                chart.setOption(option);
                break;
            }
        }

    });
}