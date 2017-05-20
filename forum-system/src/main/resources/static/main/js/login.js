/**
 *
 * @Author SY
 * @Description: 登录
 * @Date 2017/4/21 16:48
 */
var LoginAccount = function() {
    var r = function() {
        $(".form-login").validate({
            errorElement: "span",
            errorClass: "help-block",
            focusInvalid: false,
            rules: {
                loginName: {
                    required: true
                },
                loginPassword: {
                    required: true
                },
                remember: {
                    required: false
                }
            },
            messages: {
                loginName: {
                    required: "loginName is required."
                },
                loginPassword: {
                    required: "Password is required."
                }
            },
            invalidHandler: function(r, e) {
                $(".alert-danger", $(".form-login")).show()
            },
            highlight: function(r) {
                $(r).closest(".form-group").addClass("has-error")
            },
            success: function(r) {
                r.closest(".form-group").removeClass("has-error"),
                    r.remove(),
                    $(".form-login .display-hide").hide();
            },
            errorPlacement: function(r, e) {
                r.insertAfter(e.closest(".input-icon"))
            },
            submitHandler: function(r) {
                //login();
            }
        }),
        $('input[name="loginName"]').focus(),
        $("#login-btn").keypress(function () {
            if($('.form-login').validate().form()) {
                login();
            }
        }),
        $("#login-btn").click(function () {
            if($('.form-login').validate().form()) {
                login();
            }
        }),
        $('.form-login input').keypress(function(e) {
            if (e.which == 13) {
                if ($('.form-login').validate().form()) {
                    login();
                }
                return false;
            }
        }),
        $('.forget-form input').keypress(function(e) {
            if (e.which == 13) {
                if ($('.forget-form').validate().form()) {
                    //$('.forget-form').submit();
                }
                return false;
            }
        }),
        $("#forget-password").click(function() {
            $(".form-login").hide(),
                $(".forget-form").show()
        }),
        $("#back-btn").click(function() {
            $(".form-login").show(),
                $(".forget-form").hide()
        })
    },
    login = function(){
        var v = $('input[name$="loginPassword"]').val(),
            options,
            password = CryptoJS.MD5(v).toString(),
            loginName = $('input[name$="loginName"]').val(),
            language = $('input[name="language"]').attr("data-id"),
            data = {
                loginName: loginName,
                langType: language,
                loginPassword: GlobalCommon.paramEncryption(password + "-" + GlobalCommon.paramEncryption("1"))
            };
            $('input[name$="loginPassword"]').val(password);

        options = {
                url: "/system/signIn",
                dataType: 'JSON',
                async: false,
                data : JSON.stringify(data),
                callback : function (data) {
                    if(data.resultCode == "0") {
                        location.href = "/rest/home/homePage/defaultKey/defaultContent";
                    } else {
                        $('[name="loginPassword"]').val("");//清空密码
                        $('[name="loginPassword"]').focus();
                        swal("系统提示", data.message, "error");
                        //案例
                        // layer.open({
                        //     id: 'parent-window',
                        //     type: 2,
                        //     title: "Window",
                        //     maxmin: true,
                        //     area: ['800px', '500px'],
                        //     shade: 0.8,
                        //     closeBtn: 0,
                        //     shadeClose: true,
                        //     content: ['/system/400'],
                        //     btn:['yes','close'],
                        //     yes: function(index, layero){
                        //         //按钮【按钮一】的回调
                        //     },
                        //     close: function(index, layero){
                        //         //按钮【按钮一】的回调
                        //     }
                        // });
                    }
                }
            };

        GlobalUtil.globalAjaxCallback(options);
    },
    changeLanguage = function(lang, language) {
        var data = {
            lang: lang
        },
        options = {
            url: "/localeLanguage/langType",
            dataType: 'JSON',
            type: 'GET',
            async: false,
            data : data = {
                lang: lang
            },
            callback : function (data) {
                if(data.resultCode == "0") {
                    location.href = data.message;
                }
            }
        };
        GlobalUtil.globalAjaxCallback(options);
    };

    return {
        init: function() {
            r(),
            $(".login-bg").backstretch([
                "/global/plugins/pages/img/login/bg1.jpg",
                "/global/plugins/pages/img/login/bg2.jpg",
                "/global/plugins/pages/img/login/bg3.jpg"
            ], {
                fade: 1000,
                duration: 8000
            }),
            $(".forget-form").hide(),
            $(".language-bind").find("li").click(function() {//绑定语言选择事件
                var lang = $.trim($(this).find("a").attr("data-id"));
                var language = $.trim($(this).find("a").text());
                changeLanguage(lang, language);
            });
        }
    }
} ();