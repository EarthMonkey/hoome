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
    <nav class="top-nav blue">
        <div class="container">
            <div class="nav-wrapper"><a class="page-title">登记和统计</a></div>
        </div>
    </nav>
    <%@ include file="../common/landlord-nav.jsp" %>

</header>

<main>
    <div class="container" style="min-height: 500px;">
        <table class="highlight">
            <thead>
            <tr>
                <th>订单号</th>
                <th>房间类型</th>
                <%--<th>数目</th>--%>
                <th>预定时间</th>
                <th>总价</th>
                <th>入住时间</th>
                <th>退房时间</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody id="record_container">
            <tr class="none" id="record_pattern">
                <td class="record_id">1</td>
                <td class="hotel_id none">1</td>
                <td class="room_style">单人房</td>
                <%--<td class="days">2</td>--%>
                <td class="book_time">2016-10-11 10:00:00</td>
                <td class="amount">400</td>
                <td class="in_time">-</td>
                <td class="out_time">-</td>
                <td>
                    <a class="btn blue" href="#checkin">入住</a>
                    <a class="btn blue" onclick="checkout(this.parentNode.parentNode)">退房</a>
                </td>
            </tr>
            </tbody>
        </table>


        <div style="position: relative; margin-top: 60px;">
            <h4 style="display: inline;">非会员入住</h4>
            <a class="btn teal" href="#cashcheckin" style="position: absolute;right: 20px;">办理入住</a>
        </div>

        <table class="highlight">
            <thead>
            <tr>
                <th>订单号</th>
                <th>房间类型</th>
                <%--<th>数目</th>--%>
                <th>总价</th>
                <th>入住时间</th>
                <th>退房时间</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody id="cash_record_container">
            <tr class="none" id="cash_record_pattern">
                <td class="record_id">1</td>
                <td class="hotel_id none">1</td>
                <td class="room_style">单人房</td>
                <%--<td class="days">2</td>--%>
                <td class="amount">400</td>
                <td class="in_time">-</td>
                <td class="out_time">-</td>
                <td>
                    <a class="btn blue" onclick="checkout(this.parentNode.parentNode)">退房</a>
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
                <th>会员卡收入</th>
                <th>现金收入</th>
                <th>退订数目</th>
                <th>单人房入住数</th>
                <th>双人房入住数</th>
            </tr>
            </thead>
            <tbody>
            <tr class="" id="statistics">
                <td id="card_in">1</td>
                <td id="cash_in">1</td>
                <td id="cancel_num">1</td>
                <td id="small_sum">1</td>
                <td id="big_num">400</td>
            </tr>
            </tbody>
        </table>

    </div>
    <%--<%@ include file="../common/footer.jsp" %>--%>
</main>


<div id="checkin" class="modal" style="width: 500px;">
    <div class="modal-content" id="modal_list">
        <h4 class="center-align teal-text">入 住</h4>
        <div class="row">
            <div class="input-field col s11">
                <input placeholder="订单号" type="text" id="order_id">
                <label>订单号</label>
            </div>
        </div>
        <div class="row people">
            <div class="input-field col s4">
                <input placeholder="真实姓名" type="text" class="name">
                <label>姓名</label>
            </div>
            <div class="input-field col s7">
                <input placeholder="身份证号码" type="text" class="identity">
                <label>身份证号码</label>
            </div>
            <div class="input-field col s1 none delete">
                <a onclick="deletePeople(this)" class="btn-floating" style="padding-left: 5px;">删除</a>
            </div>
        </div>
    </div>
    <div class="modal-footer">
        <a onclick="checkIn(this.parentNode.parentNode)"
           class=" modal-action waves-effect waves-teal btn-flat teal-text">确认</a>
        <a onclick="addPeople(this.parentNode.parentNode)"
           class=" modal-action waves-effect waves-orange btn-flat orange-text">增加人员</a>
        <a href="#!" class=" modal-action modal-close waves-effect waves-grey btn-flat">取消</a>
    </div>
</div>

<div id="cashcheckin" class="modal" style="width: 500px;">
    <div class="modal-content" id="cash_modal_list">
        <h4 class="center-align teal-text">入 住</h4>
        <div class="row">
            <div class="input-field col s5">
                <select id="room_style">
                    <option value="" disabled selected>选择房型</option>
                    <option value="0">单人房</option>
                    <option value="1">双人房</option>
                </select>
                <label>目的</label>
            </div>
            <div class="input-field col s6">
                <input placeholder="输入房间价格" type="text" id="price">
                <label>房间价格</label>
            </div>
        </div>
        <div class="row people">
            <div class="input-field col s4">
                <input placeholder="真实姓名" type="text" class="name">
                <label>姓名</label>
            </div>
            <div class="input-field col s7">
                <input placeholder="身份证号码" type="text" class="identity">
                <label>身份证号码</label>
            </div>
            <div class="input-field col s1 none delete">
                <a onclick="deletePeople(this)" class="btn-floating" style="padding-left: 5px;">删除</a>
            </div>
        </div>
    </div>
    <div class="modal-footer">
        <a onclick="cashCheckIn(this.parentNode.parentNode)"
           class=" modal-action waves-effect waves-teal btn-flat teal-text">确认</a>
        <a onclick="addPeople(this.parentNode.parentNode)"
           class=" modal-action waves-effect waves-orange btn-flat orange-text">增加人员</a>
        <a href="#!" class=" modal-action modal-close waves-effect waves-grey btn-flat">取消</a>
    </div>
</div>


<!--  Scripts-->
<script src="/js/jquery.min.js"></script>
<script src="/js/materialize.js"></script>
<script src="/js/init.js"></script>
<script>
    var id = ${sessionScope.get("user").getId()};
    $.ajax({
        method: "post",
        url: "/landlord/getHotelOrder",
        async: false,
        dataType: "json",
        data: {
            "id": id,
        },
        success: function (result) {
            var recordHtml = $("#record_pattern").html();
            var cashRecordHtml = $("#cash_record_pattern").html();
            var card_in = 0.0, cash_in = 0.0, cancel_num = 0, small_sum = 0, big_num = 0;

            //all order record
            for (var i = 0; i < result.length; i++) {
                if (result[i].bookerId != 8) {
                    var tr = document.createElement("tr");
                    tr.innerHTML = recordHtml;
                    tr.getElementsByClassName("record_id")[0].innerHTML = result[i].id;
                    tr.getElementsByClassName("hotel_id")[0].innerHTML = result[i].hotelId;
                    tr.getElementsByClassName("book_time")[0].innerHTML = new Date(result[i].bookTime).toLocaleString();
                    tr.getElementsByClassName("room_style")[0].innerHTML = result[i].roomStyle == 0 ? "单人房" : "双人房";
                    tr.getElementsByClassName("in_time")[0].innerHTML = result[i].inTime == null ? "-" : new Date(result[i].inTime).toLocaleString();
                    tr.getElementsByClassName("out_time")[0].innerHTML = result[i].outTime == null ? "-" : new Date(result[i].outTime).toLocaleString();
                    tr.getElementsByClassName("amount")[0].innerHTML = result[i].amount;
                    switch (result[i].status) {
                        case 1:
                            card_in += result[i].amount;
                            if (result[i].roomStyle == 0) {
                                small_sum++;
                            } else {
                                big_num++;
                            }
                            tr.getElementsByClassName("btn")[0].className = "btn disabled";
                            tr.getElementsByClassName("btn")[0].onclick = null;
                            break;
                        case 2:
                            card_in += result[i].amount;
                            if (result[i].roomStyle == 0) {
                                small_sum++;
                            } else {
                                big_num++;
                            }
                            tr.getElementsByClassName("btn")[0].className = "btn disabled";
                            tr.getElementsByClassName("btn")[0].onclick = null;
                            tr.getElementsByClassName("btn")[1].className = "btn disabled";
                            tr.getElementsByClassName("btn")[1].onclick = null;
                            break;
                        case -1:
                            cancel_num++;
                            tr.getElementsByClassName("btn")[0].className = "btn disabled";
                            tr.getElementsByClassName("btn")[0].onclick = null;
                            tr.getElementsByClassName("btn")[1].className = "btn disabled";
                            tr.getElementsByClassName("btn")[1].onclick = null;
                            break;
                        case 0:
                            card_in += result[i].amount;
                            break;
                    }

                    $("#record_container").append(tr);
                } else {
                    var tr = document.createElement("tr");
                    tr.innerHTML = cashRecordHtml;
                    tr.getElementsByClassName("record_id")[0].innerHTML = result[i].id;
                    tr.getElementsByClassName("hotel_id")[0].innerHTML = result[i].hotelId;
                    tr.getElementsByClassName("room_style")[0].innerHTML = result[i].roomStyle == 0 ? "单人房" : "双人房";
                    tr.getElementsByClassName("in_time")[0].innerHTML = result[i].inTime == null ? "-" : new Date(result[i].inTime).toLocaleString();
                    tr.getElementsByClassName("out_time")[0].innerHTML = result[i].outTime == null ? "-" : new Date(result[i].outTime).toLocaleString();
                    tr.getElementsByClassName("amount")[0].innerHTML = result[i].amount;
                    cash_in += result[i].amount;
                    if (result[i].status == 2) {
                        if (result[i].roomStyle == 0) {
                            small_sum++;
                        } else {
                            big_num++;
                        }
                        tr.getElementsByClassName("btn")[0].className = "btn disabled";
                        tr.getElementsByClassName("btn")[0].onclick = null;
                    }

                    $("#cash_record_container").append(tr);
                }
            }

            $("#small_num").html(small_sum);
            $("#big_num").html(big_num);
            $("#cancel_num").html(cancel_num);
            $("#cash_in").html(cash_in);
            $("#card_in").html(card_in);

        },
        error: function () {
            Materialize.toast('请求出错!', 1200);
        }
    });


    function cashCheckIn(obj) {
        var array = new Array();
        var room_style = $("#room_style").val();
        var price = $("#price").val();
        var names = obj.getElementsByClassName("name");
        var identities = obj.getElementsByClassName("identity");
        for (var i = 0; i < names.length; i++) {
            var people = {
                "name": names[i].value,
                "identity_id": identities[i].value
            };
            array.push(people);
        }

        $.ajax({
            method: "post",
            url: "/landlord/cashCheckin",
            async: false,
            data: {
                "peoples": array,
                "room_style": room_style,
                "price": price,
                "owner_id": id
            },
            success: function (result) {
                if (result == "success") {
                    Materialize.toast('入住成功!', 1200, (function () {
                        window.location.reload();
                    })());
                } else {
                    Materialize.toast('入住失败!', 1200);
                }
            },
            error: function () {
                Materialize.toast('请求出错!', 1200);
            }
        });
    }

    function checkIn(obj) {
        var order_id = $("#order_id").val();
        var array = new Array();
        var names = obj.getElementsByClassName("name");
        var identities = obj.getElementsByClassName("identity");
        for (var i = 0; i < names.length; i++) {
            var people = {
                "name": names[i].value,
                "identity_id": identities[i].value
            };
            array.push(people);
        }

        $.ajax({
            method: "post",
            url: "/landlord/checkin",
            async: false,
            data: {
                "record_id": order_id,
                "peoples": array
            },
            success: function (result) {
                if (result == "success") {
                    Materialize.toast('入住成功!', 1200, (function () {
                        window.location.reload();
                    })());
                } else {
                    Materialize.toast('入住失败!', 1200);
                }
            },
            error: function () {
                Materialize.toast('请求出错!', 1200);
            }
        });
    }

    function checkout(obj) {
        var record_id = obj.getElementsByClassName("record_id")[0].innerHTML;

        $.ajax({
            method: "post",
            url: "/landlord/checkout",
            async: false,
            data: {
                "record_id": record_id,
            },
            success: function (result) {
                if (result == "success") {
                    Materialize.toast('退房成功!', 1200, (function () {
                        window.location.reload();
                    })());
                } else {
                    Materialize.toast('退房失败!', 1200);
                }
            },
            error: function () {
                Materialize.toast('请求出错!', 1200);
            }
        });

    }

    function addPeople(obj, id) {
        var html = obj.getElementsByClassName("people")[0].cloneNode(true);
        html.id = "";
        html.getElementsByClassName("delete")[0].className = "input-field col s1 delete";
        var a = obj.getElementsByClassName("modal-content")[0];
        a.appendChild(html);
    }

    function deletePeople(obj) {
        obj.parentNode.parentNode.parentNode.removeChild(obj.parentNode.parentNode);
    }

</script>
</body>
</html>
