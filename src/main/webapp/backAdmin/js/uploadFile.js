$(document).ready(function() {
    $("#fileUpLoader").uploadFile({
        url: "/backAdmin/uploadFile", //后台处理方法
        fileName: "myfile",   //文件的名称，此处是变量名称，不是文件的原名称只是后台接收的参数
        dragDrop: true,  //可以取消
        abortStr: "取消",
        sequential: true,  //按顺序上传
        sequentialCount: 1,  //按顺序上传
        autoSubmit: "false",  //取消自动上传
        acceptFiles: "application/msword", //限制上传文件格式
        extErrorStr: "上传文件格式不对",
        maxFileCount: 10,       //上传文件数量
        maxFileSize: 1024 * 1024 * 50, //大小限制1M
        sizeErrorStr: "上传文件不能大于50M",
        dragDropStr: "<span><b>附件拖放于此</b></span>",
        showFileCounter: false,
        uploadStr: "点击上传", returnType: "json",  //返回数据格式为json
        onSubmit: function (files) {
        },
        onSuccess: function (files, data, xhr, pd)  //上传成功事件，data为后台返回数据
        {
            if (data.status == 1) {
                layer.msg("上传成功", {icon: 0, time: 1500});
                var str = '<input type="hidden" id="uploadFileId-' + data.data.id + '" name="uploadFileIds" value="' + data.data.id + '"/>'
                    + '<input type="hidden" id="uploadFileName-' + data.data.id + '" name="uploadFileNames" value="' + data.data.name + '"/>'
                    + '<input type="hidden" id="uploadFileUrl-' + data.data.id + '" name="uploadFileUrls" value="' + data.data.url + '"/>';

                $("#uploadDiv").append(str);
            } else {
                layer.msg("上传失败", {icon: 0, time: 1500});
            }
        },
        showDelete: true,//删除按钮
        statusBarWidth: 600,
        dragdropWidth: 600,
        //删除按钮执行的方法
        deleteCallback: function (data, pd) {
            $("#uploadFileId-" + data.data.id).remove();
            $("#uploadFileName-" + data.data.id).remove();
            $("#uploadFileUrl-" + data.data.id).remove();
        }
    });
});