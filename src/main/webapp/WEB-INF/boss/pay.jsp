<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0, user-scalable=no"/>
    <title>Welcome to Hoome!!!</title>
    <!-- CSS  -->
    <link href="/css/materialize.css" type="text/css" rel="stylesheet"
          media="screen,projection"/>
    <link href="/css/style.css" type="text/css" rel="stylesheet" media="screen,projection"/>
    <link href="/css/common.css" type="text/css" rel="stylesheet">
</head>
<body>

<header>
    <nav class="top-nav blue-grey darken-2">
        <div class="container">
            <div class="nav-wrapper"><a class="page-title">结算和统计</a></div>
        </div>
    </nav>
    <%@ include file="../common/boss-nav.jsp" %>

</header>

<main>
    <div class="container" style="min-height: 500px;">
        <table class="highlight">
            <thead>
            <tr>
                <th>订单号</th>
                <th>客栈id</th>
                <th>房间类型</th>
                <th>总价</th>
                <th>入住时间</th>
                <th>退房时间</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody id="record_container">
            <tr class="none" id="record_pattern">
                <td class="record_id">1</td>
                <td class="hotel_id">1</td>
                <td class="room_style">单人房</td>
                <td class="amount">400</td>
                <td class="in_time">-</td>
                <td class="out_time">-</td>
                <td>
                    <a class="btn blue" onclick="pay(this.parentNode.parentNode)">结算</a>
                </td>
            </tr>
            </tbody>
        </table>


        <div style="position: relative; margin-top: 60px;">
            <h4 style="display: inline;">统计信息</h4>
        </div>

        <table class="highlight">
            <thead>
            <tr>
                <th>会员预定数</th>
                <th>会员退订数</th>
                <th>会员消费总额</th>
                <th>hostel world总营收</th>
            </tr>
            </thead>
            <tbody>
            <tr class="" id="statistics">
                <td id="vip_book_num">1</td>
                <td id="vip_cancel_num">1</td>
                <td id="vip_money_sum">1</td>
                <td id="all_sum">1</td>
            </tr>
            </tbody>
        </table>
        <div class="divider"></div>
        <div id="chart" style="height: 300px; margin-top: 80px;"></div>
    </div>
    <%--<%@ include file="../common/footer.jsp" %>--%>
</main>

<!--  Scripts-->
<script src="/js/jquery.min.js"></script>
<script src="/js/echarts.min.js"></script>
<script src="/js/materialize.js"></script>
<script src="/js/init.js"></script>
<script>
    var vip_book_num = 0, vip_cancel_num = 0, vip_money_sum = 0.0, all_sum = 0.0;
    var hotel = new Array();
    var data = new Array();
    var index = 0;


    $.ajax({
        method: "post",
        url: "/boss/getHotelOrder",
        async: false,
        dataType: "json",
        success: function (result) {
            var recordHtml = $("#record_pattern").html();
            //all order record
            for (var i = 0; i < result.length; i++) {
                if (result[i].bookerId != 8) {
                    if (result[i].status == -1) {
                        vip_cancel_num++;

                    } else if (result[i].status == 2) {
                        index = findNum(hotel, result[i].hotelName);
                        if (index != -1) {
                            data[index] = data[index]+1;
                        } else {
                            hotel.push(result[i].hotelName);
                            data.push(1);
                        }

                        vip_book_num++;
                        vip_money_sum += result[i].amount;
                        if (result[i].isPaid == 1)
                            all_sum += result[i].amount * 0.2;

                        var tr = document.createElement("tr");
                        tr.innerHTML = recordHtml;
                        tr.getElementsByClassName("record_id")[0].innerHTML = result[i].id;
                        tr.getElementsByClassName("hotel_id")[0].innerHTML = result[i].hotelId;
                        tr.getElementsByClassName("room_style")[0].innerHTML = result[i].roomStyle == 0 ? "单人房" : "双人房";
                        tr.getElementsByClassName("in_time")[0].innerHTML = result[i].inTime == null ? "-" : new Date(result[i].inTime).toLocaleString();
                        tr.getElementsByClassName("out_time")[0].innerHTML = result[i].outTime == null ? "-" : new Date(result[i].outTime).toLocaleString();
                        tr.getElementsByClassName("amount")[0].innerHTML = result[i].amount;
                        switch (result[i].isPaid) {
                            case 0:
                                break;
                            case 1:
                                tr.getElementsByClassName("btn")[0].className = "btn disabled";
                                tr.getElementsByClassName("btn")[0].innerHTML = "已付";
                                tr.getElementsByClassName("btn")[0].onclick = null;
                                break;
                        }
                        $("#record_container").append(tr);
                    } else {
                        index = findNum(hotel, result[i].hotelName);
                        if (index != -1) {
                            data[index] = data[index]+1;
                        } else {
                            hotel.push(result[i].hotelName);
                            data.push(1);
                        }

                        vip_book_num++;
                        vip_money_sum += result[i].amount;
                    }
                }
            }

            $("#vip_book_num").html(vip_book_num);
            $("#vip_cancel_num").html(vip_cancel_num);
            $("#vip_money_sum").html(vip_money_sum);
            $("#all_sum").html(all_sum);
        },
        error: function () {
            Materialize.toast('请求出错!', 1200);
        }
    });

    function pay(obj) {
        var record_id = obj.getElementsByClassName("record_id")[0].innerHTML;

        $.ajax({
            method: "post",
            url: "/boss/pay",
            async: false,
            data: {
                "record_id": record_id,
            },
            success: function (result) {
                if (result == "success") {
                    Materialize.toast('结算成功!', 1200, (function () {
                        window.location.reload();
                    })());
                } else {
                    Materialize.toast('结算失败!', 1200);
                }
            },
            error: function () {
                Materialize.toast('请求出错!', 1200);
            }
        });
    }


    var chart = echarts.init(document.getElementById('chart'));

    option = {
        color: ['#3398DB'],
        tooltip: {
            trigger: 'axis',
            axisPointer: {            // 坐标轴指示器，坐标轴触发有效
                type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
            }
        },
        legend: {
            data:['各酒店预定数'],
        },
        grid: {
            left: '0%',
            right: '0%',
            bottom: '3%',
            containLabel: true
        },
        xAxis: [
            {
                type: 'category',
                data: hotel,
                axisTick: {
                    alignWithLabel: true
                }
            }
        ],
        yAxis: [
            {
                type: 'value'
            }
        ],
        series: [
            {
                name: '各酒店预定数',
                type: 'bar',
                barWidth: '60%',
                data: data
            }
        ]
    };

    chart.setOption(option);


    function findNum(array, e) {
        for (i = 0; i < array.length; i++) {
            if (array[i] == e){
                return i;
            }
        }
        return -1;
    }


</script>
</body>
</html>
