var GlobalCommon = function() {
    /**
     * 获取服务器根目录的全路径
     * 
     * @param url
     * @returns
     */
    var getRootUrl = function(url) {
        var globalRoot = $("#global_rooturl").val();
        if (globalRoot == undefined || globalRoot == null || globalRoot == '') {
            // 从iframe父页面index.jsp获取
            globalRoot = $("#global_rooturl", parent.document).val();
        }
        url = globalRoot + url;
        return url;
    };

    var getLangType = function() {
        var langType = $("#global_langType").val();
        if (langType == null || langType == '') {
            langType = $('#global_langType', parent.document).val();
            if (langType == null || langType == '') {
                langType = 'zh_CN';
            }
        }
        return langType;
    };

    var i18nInit = function() {
        var langType = getLangType();
        i18n.init({
            lng : langType,
            fallbackLng : langType,
            resGetPath : '/i18n/script-__lng__.json',
            tName : 't',
            getAsync : false,// 设置同步
            i18nName : 'i18n'
        });
    };
    var datetimepickerInit = function() {
        var langType = getLangType();
        // 日历控件国际化全局语言初始化
        $.extend(true, $.fn.datetimepicker.defaults, {
            language : langType
        });
    };

    var bootstrapTableInit = function() {
        // Bootstrap Tabel全局参数初始化
        $.extend(true, $.fn.bootstrapTable.defaults, {
            method : "post",
            contentType : "application/x-www-form-urlencoded",
            sidePagination : "server",
            queryParamsType : "limit",
            showExport : true,
            exportDataType : "all",
            exportTypes : ['json','csv','txt','excel'],
            pagination : true,
            silent : true,
            showColumns : true,
            showRefresh : true,
            showToggle : false,
            pageNumber : 1,
            pageSize : 10,
            pageList : [5, 10, 15, 20, 25, 30, 50, 100, 200, 300, 500, 700, 1000],
            striped : true,// 间隔底色
            responseHandler : function(res) {
                if (typeof (res.rows) == "undefined") {
                    res.total = res.recordsFiltered; // 总数据条数
                    res.rows = res.data; // 数据
                }
                return res;
            }
        });
    };

    var ajaxSessionInit = function() {
        /**
         * Ajax Session 超时处理
         */
        $.ajaxSetup({
            contentType : "application/x-www-form-urlencoded;charset=utf-8",
            complete : function(XMLHttpRequest, textStatus) {
                var sessionstatus = XMLHttpRequest.getResponseHeader("sessionstatus"); // 通过XMLHttpRequest取得响应头，sessionstatus，
                if (sessionstatus == "timeout") {
                    bootbox.alert({
                        size : 'small',
                        message : i18n.t('GENERAL.SEESION_TIMEOUT'),
                        callback : function(result) {
                            // 如果超时就处理 ，指定要跳转的页面
                            alert("ajax session timeout");
                            window.location.href = "index.html";
                        }
                    });

                }
            }
        });
    };

    /**
     * 需要base64.js,Base64三重加密传输
     */
    var paramEncryption = function(paramValue) {
        if (paramValue != undefined && paramValue != null && paramValue != '') {
            var base64 = new Base64();
            // 三次加密
            paramValue = base64.encode(paramValue);
            paramValue = base64.encode(paramValue);
            paramValue = base64.encode(paramValue);
        }
        return paramValue;
    };
    /**
     * 需要base64.js,Base64三重解密
     */
    var paramDecryption = function(paramValue) {
        if (paramValue != undefined && paramValue != null && paramValue != '') {
            var base64 = new Base64();
            // 三次解密
            paramValue = base64.decode(paramValue);
            paramValue = base64.decode(paramValue);
            paramValue = base64.decode(paramValue);
        }
        return paramValue;
    };

    var jqueryValidateInit = function() {
        /**
         * jquery-validate 默认的全局设置
         */
        $.extend(true, $.fn.validate.defaults, {
            errorClass : 'dd-validate-error', // 使用自定义样式
            validClass : 'dd-validate-error',// 使用自定义样式
            errorElement : "span",
            focusCleanup : false,
            focusInvalid : true
        });
    };

    //
    // 添加统一时间戳方法
    var urlToken = function(url) {
        if (!url) {
            return "";
        }
        try {
            var v = new Date().getTime();
            if (url.indexOf("?") > -1) {
                url += "&_v=" + v;
            } else {
                url += "?_v=" + v;
            }
        } catch (e) {

        }
        return url;
    }

    // 自定义ajax方法，添加统一时间戳
    var ajaxTimeToken = function() {
        var myAjax = $.ajax;
        $.ajax = function(options) {
            options = options || {};
            var url = options.url || "";
            url = urlToken(url);
            options.url = url;
            myAjax(options);
        }
    }

    var bootboxInit = function() {
        bootbox.setDefaults({
            onEscape : true
        });
    }

    var toastrInit = function() {
        toastr.options = {
            "closeButton": false,
            "debug": false,
            "positionClass": "toast-top-right",
            "onclick": null,
            "showDuration": "1000",
            "hideDuration": "1000",
            "timeOut": "3000",
            "extendedTimeOut": "1000",
            "showEasing": "swing",
            "hideEasing": "linear",
            "showMethod": "fadeIn",
            "hideMethod": "fadeOut"
        }
    }
    


    return {// GlobalCommon
        // main function to initiate the module
        init : function() {
            i18nInit();
            ajaxSessionInit();
            jqueryValidateInit();
            // 自定义ajax方法，添加统一时间戳
            ajaxTimeToken();
            // bootbox设置默认属性 esc按键关闭等
            bootboxInit();
            //toastrInit();
        },
        getRootUrl : function(url) {
            return getRootUrl(url);
        },
        i18nInit : function() {
            i18nInit();
        },
        datetimepickerInit : function() {
            datetimepickerInit();
        },
        dataTableInit : function() {
            // dataTableInit();
        },
        bootstrapTableInit : function() {
            bootstrapTableInit();
        },
        ajaxSessionInit : function() {
            ajaxSessionInit();
        },
        jqueryValidateInit : function() {
            jqueryValidateInit();
        },
        paramEncryption : function(d) {
            return paramEncryption(d);
        },
        paramDecryption : function(d) {
            return paramDecryption(d);
        }
    };
}();
// 执行
GlobalCommon.init();
