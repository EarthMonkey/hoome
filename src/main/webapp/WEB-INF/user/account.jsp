<%@ page import="entity.User" %>
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
    <nav class="top-nav teal">
        <div class="container">
            <div class="nav-wrapper"><a class="page-title">账户和统计</a></div>
        </div>
    </nav>
    <%@ include file="../common/user-nav.jsp" %>

</header>

<main>
    <div class="container" style="min-height: 500px;">

        <%
            User user = (User) session.getAttribute("user");
            if (user.getIsVip() == (byte) 0) {
        %>
        <div class="row">
            <div class="col s12 m12">
                <div class="card white">
                    <div class="card-content blue-grey-text text-darken-1">
                        <span class="card-title blue-grey-text text-darken-2" style="font-size: 30px;">申请会员卡</span>
                    </div>
                    <div class="divider"></div>
                    <div class="card-content">
                        <p>注册为普通会员能在此平台订购房间，且需一次性充值1000元以上进行激活，因平台不支持第三方支付，故不办理会员卡不能进行房间预定，银卡和金卡会员更能享受最低9折订房优惠，欢迎办理。</p>
                    </div>
                    <div class="card-action">
                        <a onclick="newCard(${sessionScope.get("user").getId()})"
                           class="btn teal waves-effect waves-light">申请开办</a>
                    </div>
                </div>
            </div>
        </div>
        <%
        } else if (user.getIsVip() == (byte) 1) {
        %>
        <div class="row">
            <div class="col s12 m12">
                <div class="card white">
                    <div class="card-content blue-grey-text text-darken-1">
                        <span class="card-title blue-grey-text text-darken-2" style="font-size: 30px;">会员卡激活</span>
                    </div>
                    <div class="divider"></div>
                    <div class="card-content row">
                        <div class="input-field col s6">
                            <input id="bank_card" type="text" placeholder="输入与注册手机号绑定的银行卡号可免密支付">
                            <label for="bank_card">银行卡账号</label>
                        </div>
                        <div class="input-field col s6">
                            <input id="balance" type="text" placeholder="充1000元以上进行激活">
                            <label for="balance">开卡充值金额</label>
                        </div>
                    </div>
                    <div class="card-action">
                        <a onclick="activateCard(${sessionScope.get("user").getId()})"
                           class="btn teal waves-effect waves-light">立即激活</a>
                    </div>
                </div>
            </div>
        </div>
        <%
        } else if (user.getIsVip() == (byte) 2) {
        %>

        <div class="row">
            <div class="col s12 m12">
                <div class="card white">
                    <div class="card-content blue-grey-text text-darken-1">
                        <span class="card-title blue-grey-text text-darken-2" style="font-size: 30px;">会员卡卡号：<span
                                id="card_id">0000000</span></span>
                        <span class="badge margin-top-10">有效期至:  <span id="expire_time">2016年</span></span>
                    </div>
                    <div class="divider"></div>
                    <div class="card-content blue-grey-text text-darken-1">
                        <span>积分：<span id="point_balance">1000</span></span>
                        <span class="margin-left-20">余额：<span id="money_balance">2000</span></span>
                        <span class="margin-left-20">级别：<span id="vip_type">普通会员</span></span>
                    </div>
                    <div class="divider"></div>
                    <div class="card-content row">
                        <div class="input-field col s6">
                            <input id="topUp" type="text" placeholder="输入充值金额(元)">
                            <label for="topUp">充值金额</label>
                        </div>
                        <div class="margin-top-10" style="position: absolute;right: 20px; bottom: 40px;">
                            <a onclick="suspend(${sessionScope.get("user").getId()})"
                               class="btn blue-grey waves-effect waves-light">停用此卡</a>
                            <a onclick="topUp(${sessionScope.get("user").getId()})"
                               class="btn teal waves-effect waves-light">立即充值</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col s12 m12">
                <div class="card white">
                    <ul class="collection with-header">
                        <li class="collection-header blue-grey-text text-darken-1"><h4 style="font-size: 30px;">
                            余额变动记录</h4><span style="position: absolute;right: 20px;top: 40px;">总计：<span id="all_in_out">3000</span></span>
                        </li>
                        <div id="money_balance_container">
                            <li id="money_record_pattern" class="collection-item blue-grey-text text-darken-1 none">
                                <span class="money-balance-record">10000</span>
                                <span class="badge teal white-text in-out">+1000</span>
                                <span class="badge in-out-type" style="right: 80px;">充值/消费/退订</span>
                            </li>
                        </div>
                    </ul>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col s12 m12">
                <div class="card white">
                    <ul class="collection with-header">
                        <li class="collection-header blue-grey-text text-darken-1"><h4 style="font-size: 30px;">
                            积分变动记录</h4><span style="position: absolute;right: 20px;top: 40px;">总计：<span
                                id="all_point_in_out">3000</span></span></li>
                        <div id="point_balance_container">
                            <li id="point_record_pattern" class="collection-item blue-grey-text text-darken-1 none">
                                <span class="point-balance-record">10000</span>
                                <span class="badge teal white-text point-in-out">+1000</span>
                                <span class="badge point-in-out-type" style="right: 80px;">消费/退订</span>
                            </li>
                        </div>
                    </ul>
                </div>
            </div>
        </div>


        <%} else {%>
        <div class="row margin-top-20 new">
            <div class="col s12 m12">
                <div class="card-panel blue-grey">
          <span class="white-text">您的会员卡已被停用，点击按钮立即激活
          </span>
                </div>
            </div>
            <a class="margin-left-10 waves-effect waves-light btn teal"
               onclick="reactiveCard(${sessionScope.get("user").getId()})">立即重启</a>
        </div>
        <%}%>
    </div>

    <%--<%@ include file="../common/footer.jsp" %>--%>
</main>

<!--  Scripts-->
<script src="/js/jquery.min.js"></script>
<script src="/js/materialize.js"></script>
<script src="/js/init.js"></script>
<script src="/js/user-acount.js"></script>
<script>
    <%if(user.getIsVip() == (byte) 2){%>
    var id = ${sessionScope.get("user").getId()};
    $.ajax({
        method: "post",
        url: "/user/getMyVip",
        async: false,
        dataType: "json",
        data: {
            "id": id,
        },
        success: function (result) {
            var moneyHtml = $("#money_record_pattern").html();
            var pointHtml = $("#point_record_pattern").html();
            var vipRecordsList = result.vipRecords;
            var pointRecordsList = result.point;

            var vipCard = result.vipCard;
            var idString = vipCard.id.toString();
            var idLength = idString.length;
            for (var i = 0; i < (7 - idLength); i++) {
                idString = "0" + idString;
            }

            var allInOut = 0.0, allPointInOut = 0;

            //card info
            $("#card_id").html(idString);
            $("#expire_time").html(new Date(result.vipCard.createdAt).toLocaleString());
            $("#point_balance").html(vipCard.point);
            $("#money_balance").html(vipCard.balance);
            switch (vipCard.level) {
                case 1:
                    $("#vip_type").html("银卡会员");
                    break;
                case 2:
                    $("#vip_type").html("金卡会员");
                    break;
            }

            //all topUp record
            for (var i = 0; i < vipRecordsList.length; i++) {
                var li = document.createElement("li");
                li.className = "collection-item blue-grey-text text-darken-1";
                li.innerHTML = moneyHtml;
                li.getElementsByClassName("money-balance-record")[0].innerHTML = vipRecordsList[i].balance;
                li.getElementsByClassName("in-out")[0].innerHTML = vipRecordsList[i].inOut > 0 ? ("+" + vipRecordsList[i].inOut) : vipRecordsList[i].inOut;
                if (vipRecordsList[i].type == 1)
                    li.getElementsByClassName("in-out-type")[0].innerHTML = "积分兑换";
                $("#money_balance_container").append(li);

                allInOut += vipRecordsList[i].inOut;
            }

            //all point record
            for (var i = 0; i < pointRecordsList.length; i++) {
                var li = document.createElement("li");
                li.className = "collection-item blue-grey-text text-darken-1";
                li.innerHTML = pointHtml;
                li.getElementsByClassName("point-balance-record")[0].innerHTML = pointRecordsList[i].balance;
                li.getElementsByClassName("point-in-out")[0].innerHTML = pointRecordsList[i].point > 0 ? ("+" + pointRecordsList[i].point) : pointRecordsList[i].point;
                if (pointRecordsList[i].type == 1)
                    li.getElementsByClassName("point-in-out-type")[0].innerHTML = "兑换现金";
                $("#point_balance_container").append(li);

                allPointInOut += pointRecordsList[i].point;
            }

            $("#all_in_out").html(allInOut > 0 ? ("+" + allInOut) : allInOut);
            $("#all_point_in_out").html(allPointInOut > 0 ? ("+" + allPointInOut) : allPointInOut);

        },
        error: function () {
            Materialize.toast('请求出错!', 1200);
        }
    });
    <%}%>

    function suspend(id) {
        $.ajax({
            method: "post",
            url: "/user/suspendCard",
            async: false,
            data: {
                "user_id": id
            },
            success: function (result) {
                if (result == "success") {
                    Materialize.toast('停用成功!', 1200, (function () {
                        window.location.reload();
                    })());
                } else {
                    Materialize.toast('停用失败!', 1200);
                }
            },
            error: function () {
                Materialize.toast('请求出错!', 1200);
            }
        });
    }

    function reactiveCard(id) {
        $.ajax({
            method: "post",
            url: "/user/reactiveCard",
            async: false,
            data: {
                "user_id": id
            },
            success: function (result) {
                if (result == "success") {
                    Materialize.toast('重启成功!', 1200, (function () {
                        window.location.reload();
                    })());
                } else {
                    Materialize.toast('重启失败!', 1200);
                }
            },
            error: function () {
                Materialize.toast('请求出错!', 1200);
            }
        });
    }


</script>
</body>
</html>
