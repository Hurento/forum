/**
 * 页面头部
 */
var PageHead = function () {

    // 退出登录
    var logout = function() {
        location.href = "/system/logout";
    }

    return {// PageHead
        init : function () {
          $(".log-out").click(function() {
             GlobalUtil.globalConfirm("",'您是否要注销登录？', logout);
          });
        }
    };
}();
$(function () {
    PageHead.init();
});
