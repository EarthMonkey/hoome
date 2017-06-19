(function ($) {
    $(function () {
        $('.button-collapse').sideNav();
        $('.parallax').parallax();
    }); // end of document ready

})(jQuery); // end of jQuery name space、

$(document).ready(function () {
    $('.modal').modal();
    $('.collapsible').collapsible({
        accordion: false // A setting that changes the collapsible behavior to expandable instead of the default accordion style
    });
    $(function () {
        $('.button-collapse').sideNav();
        $('.parallax').parallax();
    });
    $('ul.tabs').tabs();
    $('select').material_select();
});

function logout(id) {
    $.ajax({
        method: "post",
        url: "/user/logout",
        async: false,
        data: {
            "id": id
        },
        success: function (result) {
            if (result == "success") {
                Materialize.toast('退出成功!', 1200);
                window.location.href = "/";
            } else {
                Materialize.toast('退出失败!', 1200);
            }
        },
        error: function () {
            Materialize.toast('请求出错!', 1200);
        }
    });
}