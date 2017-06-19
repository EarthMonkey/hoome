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
            <div class="nav-wrapper"><a class="page-title">审批申请</a></div>
        </div>
    </nav>
    <%@ include file="../common/boss-nav.jsp" %>

</header>

<main>
    <div class="container margin-top-20">
        <div class="row">
            <h5>开店申请</h5>
            <ul id="new_apply_container" class="collapsible" data-collapsible="accordion">
                <li id="new_apply" class="none">
                    <div class="collapsible-header new_name">wow</div>
                    <div class="collapsible-body">
                        <div class="margin-top-10 margin-left-10">
                            <i class="new_id none"></i>
                            <i class="material-icons">room</i><span class="margin-left-5 new_address">南京大学</span>
                            <i class="material-icons margin-left-20">contact_phone</i><span
                                class="margin-left-5 new_phone">15615161717</span>
                            <span class="margin-left-20">单人房数目:</span><span class="new_small_num">100</span>
                            <span class="margin-left-20">双人房数目:</span><span class="new_big_num">200</span>
                            <a onclick="newApprove(this,0)" class="margin-bottom-10 btn waves-effect waves-light teal"
                               style="float: right; margin-right: 20px;">通过申请</a>
                            <p class="new_introduction">这是房间的简介哦哦哦哦哦 哦哦哦</p>
                        </div>
                    </div>
                </li>
            </ul>
        </div>
        <div class="row">
            <h5>修改信息申请</h5>
            <ul id="change_apply_container" class="collapsible" data-collapsible="accordion">
                <li id="change_apply" class="none">
                    <div class="collapsible-header change_name">First</div>
                    <div class="collapsible-body"><div class="margin-top-10 margin-left-10">
                        <i class="change_id none"></i>
                        <i class="material-icons">room</i><span class="margin-left-5 change_address">南京大学</span>
                        <i class="material-icons margin-left-20">contact_phone</i><span
                            class="margin-left-5 change_phone">15615161717</span>
                        <span class="margin-left-20">单人房数目:</span><span class="change_small_num">100</span>
                        <span class="margin-left-20">双人房数目:</span><span class="change_big_num">200</span>
                        <a onclick="newApprove(this,1)" class="margin-bottom-10 btn waves-effect waves-light teal"
                           style="float: right; margin-right: 20px;">通过申请</a>
                        <p class="change_introduction">这是房间的简介哦哦哦哦哦 哦哦哦</p>
                    </div>
                </li>
            </ul>
        </div>
    </div>

    <%--<%@ include file="../common/footer.jsp" %>--%>
</main>

<!--  Scripts-->
<script src="/js/jquery.min.js"></script>
<script src="/js/materialize.js"></script>
<script src="/js/init.js"></script>
<script>
    $.ajax({
        method: "post",
        url: "/boss/allApply",
        async: false,
        dataType: "json",
        success: function (result) {
            var newApplyHtml = $("#new_apply").html();
            var changeApplyHtml = $("#change_apply").html();
            if (result == null) {
            } else {
                var newList = result.newApply;
                var changeList = result.changeApply;
                for (var i = 0; i < newList.length; i++) {
                    var li = document.createElement("li");
                    li.innerHTML = newApplyHtml;
                    li.getElementsByClassName("new_id")[0].innerHTML = newList[i].id;
                    li.getElementsByClassName("new_name")[0].innerHTML = newList[i].name;
                    li.getElementsByClassName("new_address")[0].innerHTML = newList[i].address;
                    li.getElementsByClassName("new_phone")[0].innerHTML = newList[i].phone;
                    li.getElementsByClassName("new_introduction")[0].innerHTML = newList[i].introduction;
                    $("#new_apply_container").append(li);
                }
                //changeList

                for (var i = 0; i < changeList.length;i++) {
                    var li = document.createElement("li");
                    li.innerHTML = changeApplyHtml;
                    li.getElementsByClassName("change_id")[0].innerHTML = changeList[i].id;
                    li.getElementsByClassName("change_name")[0].innerHTML = changeList[i].name;
                    li.getElementsByClassName("change_address")[0].innerHTML = changeList[i].address;
                    li.getElementsByClassName("change_phone")[0].innerHTML = changeList[i].phone;
                    li.getElementsByClassName("change_introduction")[0].innerHTML = changeList[i].introduction;
                    $("#change_apply_container").append(li);
                }
            }
        },
        error: function () {
            //Materialize.toast('请求出错!', 1200);
        }
    });


    function newApprove(obj, flag) {
        var applyId;

        if (flag == 0) {
            applyId = obj.parentNode.getElementsByClassName("new_id")[0].innerHTML;
            $.ajax({
                method: "post",
                url: "/boss/approveNew",
                async: false,
                data: {
                    "apply_id": applyId
                },
                success: function (result) {
                    if (result == "success") {
                        Materialize.toast('处理成功!', 1200);
                        setInterval((function () {
                            window.location.reload()
                        }()), 1800);
                    } else {
                        Materialize.toast('处理失败!', 1200);
                    }
                },
                error: function () {
                    Materialize.toast('请求出错!', 1200);
                }
            });
        } else {
            applyId = obj.parentNode.getElementsByClassName("change_id")[0].innerHTML;
            $.ajax({
                method: "post",
                url: "/boss/approveChange",
                async: false,
                data: {
                    "apply_id": applyId
                },
                success: function (result) {
                    if (result == "success") {
                        Materialize.toast('处理成功!', 1200);
                        setInterval((function () {
                            window.location.reload()
                        }()), 1800);
                    } else {
                        Materialize.toast('处理失败!', 1200);
                    }
                },
                error: function () {
                    Materialize.toast('请求出错!', 1200);
                }
            });
        }
    }


</script>
</body>
</html>
