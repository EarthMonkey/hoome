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
    <style type="text/css">

        .sprite {
            background-image: url(/image/sprite.png);
            background-repeat: no-repeat;
            display: block;
            -webkit-filter: blur(10px);
            -moz-filter: blur(10px);
            -ms-filter: blur(10px);
            filter: blur(10px);
        }

        .sprite-background1 {
            width: 1440px;
            height: 743px;
            background-position: -5px -5px;
        }

        .sprite-background2 {
            width: 1440px;
            height: 801px;
            background-position: -5px -758px;
        }

        .sprite-background3 {
            width: 1440px;
            height: 835px;
            background-position: -1455px -5px;
        }
    </style>
</head>
<body>
<nav class="white" role="navigation">
    <div class="nav-wrapper container">

        <a id="logo-container" href="#" class="brand-logo"><img class="responsive-img"
                                                                src="image/logo.svg" alt=""></a>
        <ul class="right hide-on-med-and-down">
            <li><a href="#login">登录</a></li>
            <li><a href="#logup">注册</a></li>
        </ul>

        <ul id="nav-mobile" class="side-nav">
            <li><a href="#login">登录</a></li>
            <li><a href="#logup">注册</a></li>
        </ul>
        <a href="#" data-activates="nav-mobile" class="button-collapse"><i class="material-icons">menu</i></a>
    </div>
</nav>

<div id="index-banner" class="parallax-container">
    <div class="section no-pad-bot">
        <div class="container">
            <br><br>
            <h1 class="header center teal-text text-lighten-3">Hostel World</h1>
            <div class="row center">
                <h5 class="header col s12 light">专注你的住房体验</h5>
            </div>
            <div class="row center">
                <a href="#login" id="download-button"
                   class="btn-large waves-effect waves-light teal lighten-1">立即体验</a>
            </div>
            <br><br>

        </div>
    </div>
    <div class="parallax"><img class="sprite sprite-background1" src="" alt="Unsplashed background img 1">
    </div>
</div>


<div class="container">
    <div class="section">

        <!--   Icon Section   -->
        <div class="row">
            <div class="col s12 m4">
                <div class="icon-block">
                    <h2 class="center brown-text"><i class="material-icons">flash_on</i></h2>
                    <h5 class="center">Improves motivation</h5>

                    <p class="center-align light">Records everything of your daily sports</p>
                </div>
            </div>

            <div class="col s12 m4">
                <div class="icon-block">
                    <h2 class="center brown-text"><i class="material-icons">group</i></h2>
                    <h5 class="center">Finds friends</h5>

                    <p class="center-align light">Many people with same goals can be friends</p>
                </div>
            </div>

            <div class="col s12 m4">
                <div class="icon-block">
                    <h2 class="center brown-text"><i class="material-icons">settings</i></h2>
                    <h5 class="center">Easy to manage</h5>

                    <p class="center-align light">Simple UX design helps you manage it easily</p>
                </div>
            </div>
        </div>

    </div>
</div>


<div class="parallax-container valign-wrapper">
    <div class="section no-pad-bot">
        <div class="container">
            <div class="row center">
                <h5 class="header col s12 light">A platform you can compete with others</h5>
            </div>
        </div>
    </div>
    <div class="parallax"><img class="sprite sprite-background2" alt="Unsplashed background img 2">
    </div>
</div>

<div class="container">
    <div class="section">

        <div class="row">
            <div class="col s12 center">
                <h3><i class="mdi-content-send brown-text"></i></h3>
                <h4>About Mario</h4>
                <p class="center-align light">A student in Junior year in NJU</p>
            </div>
        </div>

    </div>
</div>


<div class="parallax-container valign-wrapper">
    <div class="section no-pad-bot">
        <div class="container">
            <div class="row center">
                <h5 class="header col s12 light">A stage where you can find friends with same interests</h5>
            </div>
        </div>
    </div>
    <div class="parallax"><img class="sprite sprite-background3" alt="Unsplashed background img 3">
    </div>
</div>

<!-- Login Modal -->
<div id="login" class="modal" style="width: 360px">
    <div class="modal-content">
        <h4 class="center-align">登 录</h4>
        <div class="row">
            <div class="input-field col s12">
                <input id="phone" type="text" class="validate">
                <label for="phone">手机号</label>
            </div>
            <div class="input-field col s12">
                <input id="password" type="password" class="validate">
                <label for="password">密码</label>
            </div>
        </div>
    </div>
    <div class="modal-footer">
        <a onclick="login()" href="#!" class=" modal-action waves-effect waves-teal btn-flat teal-text">确认</a>
        <a onclick="trans(0)" href="#!" class=" modal-action waves-effect waves-orange btn-flat orange-text">注册</a>
        <a href="#!" class=" modal-action modal-close waves-effect waves-grey btn-flat">取消</a>
    </div>
</div>

<!-- Logup Modal -->
<div id="logup" class="modal" style="width: 360px; overflow: visible;">
    <div class="modal-content">
        <h4 class="center-align teal-text">注 册</h4>
        <div class="row">
            <div class="input-field col s12">
                <input placeholder="请输入手机号码" id="new_phone" type="text" class="validate">
                <label for="new_phone">手机号</label>
            </div>
            <div class="input-field col s12">
                <input placeholder="请输入真实姓名" id="new_name" type="text" class="validate">
                <label for="new_name">姓名</label>
            </div>
            <div class="input-field col s12">
                <input placeholder="不超过20位字母或数字" id="new_password" type="password" class="validate">
                <label for="new_password">密码</label>
            </div>
            <div class="input-field col s12">
                <select id="new_role">
                    <option value="" disabled selected>选择你的目的</option>
                    <option value="0">订房</option>
                    <option value="1">开客栈</option>
                    <option value="2">总经理</option>
                </select>
                <label>目的</label>
            </div>
        </div>
    </div>
    <div class="modal-footer">
        <a onclick="logup()" href="#!" class=" modal-action waves-effect waves-teal btn-flat teal-text">确认</a>
        <a onclick="trans(1)" href="#!" class=" modal-action waves-effect waves-orange btn-flat orange-text">登录</a>
        <a href="#!" class=" modal-action modal-close waves-effect waves-grey btn-flat">取消</a>
    </div>
</div>


<%@ include file="footer.jsp" %>

<!--  Scripts-->
<script src="/js/jquery.min.js"></script>
<script src="/js/materialize.js"></script>
<script src="/js/init.js"></script>
<script>

    function trans(flag) {
        if (flag == 1) {
            $('#logup').modal('close');
            $('#login').modal('open');
        } else {
            $('#login').modal('close');
            $('#logup').modal('open');
        }
    }

    function login() {
        var phone = $("#phone").val();
        var password = $("#password").val();

        $.ajax({
            method: "post",
            url: "/user/login",
            async: false,
            dataType: "json",
            data: {
                "phone": phone,
                "password": password
            },
            success: function (result) {
                if (result.state == "success") {
                    Materialize.toast('登录成功!', 1200);
                    switch (result.role) {
                        case 0:
                            window.location.href = "/user/hotel";
                            break;
                        case 1:
                            window.location.href = "/landlord/hotel-info";
                            break;
                        default:
                            window.location.href = "/boss/apply";
                    }
                } else {
                    Materialize.toast('登录失败!', 1200);
                }
            },
            error: function () {
                Materialize.toast('请求出错!', 1200);
            }
        });
    }

    function logup() {
        var phone = $("#new_phone").val();
        var password = $("#new_password").val();
        var role = $("#new_role").val();
        var name = $("#new_name").val();

        $.ajax({
            method: "post",
            url: "/user/logup",
            async: false,
            data: {
                "phone": phone,
                "name": name,
                "password": password,
                "role": role
            },
            success: function (result) {
                if (result == "success") {
                    Materialize.toast('注册成功!', 1800);
                    setInterval((function () {
                        window.location.href = "/";
                    }()),1800);

                } else if (result == "exist") {
                    Materialize.toast('用户已存在!', 1200);
                } else {
                    Materialize.toast('注册失败!', 1200);
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
