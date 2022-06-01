function getRealPath() {
    var currwwwpath=window.document.location.href;
    var pathName=window.document.location.pathname;
    var position=currwwwpath.indexOf(pathName);
    var localhostPath=currwwwpath.substring(0,position);
    var projectName=pathName.substring(0,pathName.substring(1).indexOf("/")+1)
    var realPath = localhostPath+projectName;
    return  realPath;
}
$(function () {
    $("#shangchuan").on("click",function (event) {
        event.preventDefault();
        $("#excelFile").click();
    });

    $("#excelFile").change(function () {
        $("#toSubmit").click();
    })
    var table = $("#datatable tbody");
    var realpath = getRealPath();
    // $.ajax({
    //     async :true,
    //     type:"get",
    //     url:realpath+"/classServlet.do?param=show",
    //     date:"",
    //     dataType:"text",
    //     success:function (data) {
    //         if (data!=null){
    //             $(data).each(function (i,item) {
    //                 table.append("<tr><td>"+1+"</td><td>"+item.className+"</td><td>"+item.studyType+"</td></tr>")
    //             })
    //         }
    //     }

    // })
    $.getJSON(realpath+"/classServlet.do?param=show","",function (data) {
        if (data!=null){
            alert(data)
            var datum = data[0];
            alert(datum)
            var ff;
            $(datum).each(function (i,item) {
                ff+="<tr><td>"+item.classId+"</td><td>"+item.className+"</td><td>"+item.majorName+"</td></tr>"

            })
            table.append(ff)
        }
    })
})