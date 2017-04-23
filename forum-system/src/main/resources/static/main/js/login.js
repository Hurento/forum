/**
 *
 * @Author SY
 * @Description: 登录
 * @Date 2017/4/21 16:48
 */
var Login = function() {
    var r = function() {
        $(".form-login").validate({
            errorElement: "span",
            errorClass: "help-block",
            focusInvalid: false,
            rules: {
                userName: {
                    required: true
                },
                userPass: {
                    required: true
                },
                remember: {
                    required: false
                }
            },
            messages: {
                userName: {
                    required: "Username is required."
                },
                userPass: {
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
                login();
                // var v = $('input[name$="password"]').val();
                // $('input[name$="password"]').val(paramEncryptionPassword(v));
                // r.submit();
            }
        }),
        $("#login-btn").click(function () {
            if($('.form-login').validate().form()) {
                login();
            }
        }),
        $('.form-login input').keyup(function(e) {
            if (e.which == 13) {
                if ($('.form-login').validate().form()) {
                    login();
                    // var v = $('input[name$="password"]').val();
                    // $('input[name$="password"]').val(paramEncryptionPassword(v));
                    // $('.form-login').submit(); //form validation success, call ajax form submit
                }
                return false;
            }
        }),
        $('.forget-form input').keyup(function(e) {
            if (e.which == 13) {
                if ($('.forget-form').validate().form()) {
                    $('.forget-form').submit();
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
        var v = $('input[name$="userPass"]').val(),
            options,
            password = paramEncryptionPassword(v),
            username = $('input[name$="userName"]').val(),
            data = {
                userName: username,
                userPass: password
            };
            $('input[name$="password"]').val(password);
        
        options = {
                url: "/rest/login/signIn",
                alertType:'3',
                callback : function (data) {
                    alert(JSON.stringify(data));
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