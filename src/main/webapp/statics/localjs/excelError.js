$(function () {
    // 10秒后自动提交表单返回录入页面
    var reback = setTimeout(function () {
        $('#reback-Excel').submit();
    }, 7000);

    // 单击返回主页面按钮
    $('#goMainPage').click(function () {
        // 取消计时事件
        clearTimeout(reback);
        // 跳转主页面
        window.location.href = "jsp/main.jsp";
    });

    // 显示跳转剩余秒数
    setTimeout(function () {
        $('#second').html('6 秒');
    }, 1000);
    setTimeout(function () {
        $('#second').html('5 秒');
    }, 2000);
    setTimeout(function () {
        $('#second').html('4 秒');
    }, 3000);
    setTimeout(function () {
        $('#second').html('3 秒');
    }, 4000);
    setTimeout(function () {
        $('#second').html('2 秒');
    }, 5000);
    setTimeout(function () {
        $('#second').html('1 秒');
    }, 6000);
    setTimeout(function () {
        $('#second').html('0 秒');
    }, 7000);
});





