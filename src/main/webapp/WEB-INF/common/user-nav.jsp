<%@ page language="java" pageEncoding="UTF-8" %>
<ul id="slide-out" class="side-nav fixed">
    <li>
        <div class="userView">
            <div class="background">
                <img src="/image/background.jpg">
            </div>
            <a href="#!user"><img class="circle" src="/image/mario.jpg"></a>
            <a href="#!name" style="height: 60px; font-size: 30px;"><span class="white-text name">${sessionScope.get("user").getName()}</span></a>
        </div>
    </li>
    <li><a class="subheader teal-text">欢迎挑选您心仪的房间</a></li>
    <li>
        <div class="divider"></div>
    </li>
    <li class="margin-top-10"><a href="/user/hotel" class="waves-effect"><i class="material-icons">business</i>所有房源</a></li>
    <li>
        <div class="divider"></div>
    </li>
    <li class="margin-top-10"><a class="waves-effect" href="/user/order"><i class="material-icons">shopping_cart</i>我的订单</a></li>
    <li><a class="waves-effect" href="/user/account"><i class="material-icons">person_pin</i>账户和统计</a></li>
    <%--<li><a class="waves-effect" href="/user/statistics"><i class="material-icons">assessment</i>统计明细</a></li>--%>
    <li>
        <div class="divider"></div>
    </li>
    <li class="margin-top-10"><a class="waves-effect" href="javascript:logout(${sessionScope.get("user").getId()})"><i class="material-icons">open_in_browser</i>退出登录</a></li>
</ul>
<a href="#" data-activates="slide-out" class="hide-on-large-only button-collapse"><i class="material-icons">menu</i></a>