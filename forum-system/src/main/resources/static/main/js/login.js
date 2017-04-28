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
            }
        }),
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
                    //login();
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
            password = paramEncryptionPassword(v),
            loginName = $('input[name$="loginName"]').val(),
            data = {
                loginName: loginName,
                loginPassword: password
            };
            $('input[name$="loginPassword"]').val(password);

        options = {
                url: "/rest/login/signIn",
                alertType: 'noAlter',
                dataType: 'JSON',
                async: false,
                data : JSON.stringify(data),
                callback : function (data) {

                }
            };

        GlobalUtil.globalAjaxCallback(options);
    },
    paramEncryptionPassword = function (v) {
        return GlobalCommon.paramEncryption(v);
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
                $(".forget-form").hide();
        }
    }
} ();