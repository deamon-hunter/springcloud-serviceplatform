var prefix = "/loan/query";
$(function () {
    load();
});

function load() {

    initSearch();


    $('#exampleTable')
        .bootstrapTable(
            {
                method: 'post', // 服务器数据的请求方式 get or post
                url: prefix + "/list", // 服务器数据的加载地址
                striped: true, // 设置为true会有隔行变色效果
                dataType: "json", // 服务器返回的数据类型
                pagination: true, // 设置为true会在底部显示分页条
                // queryParamsType : "limit",
                // //设置为limit则会发送符合RESTFull格式的参数
                singleSelect: false, // 设置为true将禁止多选
                iconSize: 'outline',
                toolbar: '#exampleToolbar',
                contentType : "application/json",
                // //发送到服务器的数据编码类型
                pageSize: 10, // 如果设置了分页，每页数据条数
                pageNumber: 1, // 如果设置了分布，首页页码
                // search : true, // 是否显示搜索框
                // showColumns: true, // 是否显示内容下拉框（选择显示的列）
                sidePagination: "server", // 设置在哪里进行分页，可选值为"client" 或者
                // "server"
                queryParams : function(params) {
                    $.extend(params, $.serializeObject($('#searchForm')));
                    return params;
                },

                // //请求服务器数据时，你可以通过重写参数的方式添加一些额外的参数，例如 toolbar 中的参数 如果
                // queryParamsType = 'limit' ,返回参数必须包含
                // limit, offset, search, sort, order 否则, 需要包含:
                // pageSize, pageNumber, searchText, sortName,
                // sortOrder.
                // 返回false将会终止请求
                columns: [
                    {
                        title: "借款编号",
                        field: "borrowNumber"
                    },
                    {
                        title: "借款商户",
                        field: "storeName"
                    },
                    {
                        title: "借款人",
                        field: "userName"
                    },
                    {
                        title: "联系人电话",
                        field: "userPhone"
                    },
                    {
                        title: "借款金额",
                        field: "borrowAmount"
                    },
                    {
                        title: "借款期数",
                        field: "borrowPeriod"
                    },
                    {
                        title: "申请时间",
                        field: "createTime"
                    },
                    {
                        title: "当前状态",
                        field: "approveState"
                    },
                    {
                        title: '操作',
                        field: 'borrowId',
                        align: 'center',
                        formatter: function (value, row, index) {
                            var e = '<a class="btn btn-primary btn-sm ' + s_edit_h + '" href="#" mce_href="#" title="查看借款单" onclick="edit(\''
                                + row.ivd
                                + '\')"><i class="fa fa-book"></i></a> ';

                            var v = '<a class="btn btn-success btn-sm ' + s_edit_h + '" href="#" mce_href="#" title="查看还款单" onclick="viewRefund(\''
                                + row.id
                                + '\')"><i class="fa fa-book"></i></a> ';

                            return e + v;
                        }
                    }]
            });
}

function reLoad() {
   $('#exampleTable').bootstrapTable('refresh');
}

function add() {
    // iframe层
    layer.open({
        type: 2,
        title: '添加角色',
        maxmin: true,
        shadeClose: false, // 点击遮罩关闭层
        area: ['1000px', '620px'],
        content: prefix + '/add' // iframe的url
    });
}

function remove(id) {
    layer.confirm('确定要删除选中的记录？', {
        btn: ['确定', '取消']
    }, function () {
        $.ajax({
            url: prefix + "/remove",
            type: "post",
            data: {
                'id': id
            },
            success: function (r) {
                if (r.code === 0) {
                    layer.msg("删除成功");
                    reLoad();
                } else {
                    layer.msg(r.msg);
                }
            }
        });
    })

}

function edit(id) {
    layer.open({
        type: 2,
        title: '借款单详情',
        maxmin: true,
        shadeClose: true, // 点击遮罩关闭层
        area: ['1000px', '620px'],
        content: prefix + '/detail/' + id // iframe的url
    });
}

function viewRefund(id) {
    layer.open({
        type: 2,
        title: '还款单详情',
        maxmin: true,
        shadeClose: true, // 点击遮罩关闭层
        area: ['1000px', '620px'],
        content: prefix + '/refundDetail/' + id // iframe的url
    });
}

function batchRemove() {

    var rows = $('#exampleTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
    if (rows.length == 0) {
        layer.msg("请选择要删除的数据");
        return;
    }
    layer.confirm("确认要删除选中的'" + rows.length + "'条数据吗?", {
        btn: ['确定', '取消']
    }, function () {
        var ids = new Array();
        $.each(rows, function (i, row) {
            ids[i] = row['borrowId'];
        });
        console.log(ids);
        $.ajax({
            type: 'POST',
            data: {
                "ids": ids
            },
            url: prefix + '/batchRemove',
            success: function (r) {
                if (r.code == 0) {
                    layer.msg(r.msg);
                    reLoad();
                } else {
                    layer.msg(r.msg);
                }
            }
        });
    }, function () {
    });
}

function initSearch() {
    $(".datetime-start").datetimepicker({
        // format : 'yyyy-mm-dd hh:ii:ss',
        minView:'hour',
        language: 'zh-CN',
        autoclose:true
//        startDate:new Date()
    }).on("click",function(){
        $(".datetime-start").datetimepicker("setEndDate",$(".datetime-end").val())
    });
    $(".datetime-end").datetimepicker({
        // format : 'yyyy-mm-dd hh:ii:ss',
        minView:'hour',
        language: 'zh-CN',
        autoclose:true
//        startDate:new Date()
    }).on("click",function(){
        $(".datetime-end").datetimepicker("setStartDate",$(".datetime-start").val())
    });

    selectLoad();
}

function selectLoad() {
    var html = "";
    $.ajax({
        url : '/common/sysDict/list/approve_state',
        success : function(data) {
            data = data.data;
            //加载数据
            for (var i = 0; i < data.length; i++) {
                html += '<option value="' + data[i].value + '">' + data[i].name + '</option>'
            }
            $(".chosen-select").append(html);
            $(".chosen-select").chosen({
                maxHeight : 200
            });

        }
    });
}