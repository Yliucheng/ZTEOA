$(function () {
    // 单击普通按钮
    $('#importButton').click(function (event) {
        event.preventDefault();
        // 帮我们去单击文件输入框
        $('#excelFile').click();
        // 文件输入框发生改变调用
        $('#excelFile').change(function () {
            // 提交表单到后台
            $('#importExcel').submit();
        });
    });
});