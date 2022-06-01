$(function () {

    $("#shangchuan").on("click",function (event) {
        event.preventDefault();
        $("#excelFile").click();
    });

    $("#excelFile").change(function () {
        $("#toSubmit").click();
    })
})
