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
    <li><a class="subheader blue-grey-text text-darken-3">这是您的 Hostel World</a></li>
    <li>
        <div class="divider"></div>
    </li>
    <li class="margin-top-10"><a href="/boss/apply" class="waves-effect"><i class="material-icons">done_all</i>审批申请</a></li>
    <li><a href="/boss/pay" class="waves-effect"><i class="material-icons">payment</i>结算和统计</a></li>

    <hr>
    <%-- 数据分析 --%>
    <li><a href="/boss/analysis-finance" class="waves-effect"><i class="material-icons">assessment</i>财务管理</a></li>
    <li><a href="/boss/analysis-order" class="waves-effect"><i class="material-icons">assignment</i>订单管理</a></li>
    <li><a href="/boss/analysis-member" class="waves-effect"><i class="material-icons">perm_identity</i>会员管理</a></li>
    <li><a href="/boss/analysis-checkin" class="waves-effect"><i class="material-icons">business</i>入住管理</a></li>
    <li><a href="/boss/analysis-market" class="waves-effect"><i class="material-icons">shopping_cart</i>销售管理</a></li>

    <li>
        <div class="divider"></div>
    </li>
    <li class="margin-top-10"><a class="waves-effect" href="javascript:logout(${sessionScope.get("user").getId()})"><i class="material-icons">open_in_browser</i>退出登录</a></li>
</ul>
<a href="#" data-activates="slide-out" class="hide-on-large-only button-collapse"><i class="material-icons">menu</i></a>