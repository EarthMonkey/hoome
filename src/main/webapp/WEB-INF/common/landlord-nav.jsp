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
    <li class="margin-top-10"><a href="/landlord/hotel-info" class="waves-effect"><i class="material-icons">info_outline</i>客栈信息</a></li>
    <li><a href="/landlord/special" class="waves-effect"><i class="material-icons">stars</i>发布优惠</a></li>
    <li><a href="/landlord/check-in-out" class="waves-effect"><i class="material-icons">mode_edit</i>登记和统计</a></li>

    <li>
        <div class="divider"></div>
    </li>
    <li class="margin-top-10"><a class="waves-effect" href="javascript:logout(${sessionScope.get("user").getId()})"><i class="material-icons">open_in_browser</i>退出登录</a></li>
</ul>
<a href="#" data-activates="slide-out" class="hide-on-large-only button-collapse"><i class="material-icons">menu</i></a>