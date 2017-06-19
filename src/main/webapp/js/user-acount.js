function newCard(id) {
    $.ajax({
        method: "post",
        url: "/user/newVip",
        async: false,
        data: {
            "id": id,
        },
        success: function (result) {
            if (result == "success") {
                Materialize.toast('办理成功!', 1200);
                setInterval((function () {
                    window.location.reload();
                }()), 1800)
            } else {
                Materialize.toast('办理失败!', 1200);
            }
        },
        error: function () {
            Materialize.toast('请求出错!', 1200);
        }
    });
}

function activateCard(id) {
    var bank_card = $("#bank_card").val();
    var balance = $("#balance").val();

    if (bank_card == "" || balance == "") {
        Materialize.toast('请填写完整信息', 1200);
    } else if (balance < 1000) {
        Materialize.toast('需一次性充值1000元以上噢~', 1200);
    } else {
        $.ajax({
            method: "post",
            url: "/user/activateVip",
            async: false,
            data: {
                "bank_card": bank_card,
                "balance": balance,
                "id": id
            },
            success: function (result) {
                if (result == "success") {
                    Materialize.toast('激活成功!', 1200);
                    setInterval((function () {
                        window.location.reload();
                    }()), 1800)
                } else {
                    Materialize.toast('激活失败!', 1200);
                }
            },
            error: function () {
                Materialize.toast('请求出错!', 1200);
            }
        });
    }
}

//充值
function topUp(id){
    var money = $("#topUp").val();

    if(money!=""){
        $.ajax({
            method: "post",
            url: "/user/topUp",
            async: false,
            data: {
                "money": money,
                "id": id
            },
            success: function (result) {
                if (result == "success") {
                    Materialize.toast('充值成功!', 1200);
                    setInterval((function () {
                        window.location.reload();
                    }()), 1800)
                } else {
                    Materialize.toast('充值失败!', 1200);
                }
            },
            error: function () {
                Materialize.toast('请求出错!', 1200);
            }
        });
    }else{
        Materialize.toast('请输入充值金额!', 1200);
    }
}
