var myDatagrid = {

    datagridId: "",  // datagrid table id
    queryFormId: "", // search form id
    queryAction: "", // search from action
    infoFormId: "",  // info form id
    infoAddAction: "",  // info data add action
    infoUpdateAction: "", //info update action
    infoDlgDivId: "",  // info data detail/edit dlg div id
    deleteAction: "",  //data delete action  from ajax
    deleteMsg: "",    // show the message before do delete
    moveDlgDivId: "",  // the div id of dialog for move show
    moveFormId: "",    //the form id for move
    moveTreeId: "",    // the combotree id for move
    dataUrl: "",       //datagrid data load url from ajax
    queryParams: {},   //search params name for post, must to be {}
    queryParamsVCN: {}, //search params value from htmlcontrol name, must to be {}
    columns: [],   //datagrid columns ,must to be []

    addOrUpdateFlag: 0,  //0-add ,1-update

    beforeAddFn: function () {
        return true;
    },
    beforeDoAddFn: function () {
        return true;
    },
    afterDoAddFn: function () {
    },

    beforeDeleteFn: function () {
        return true;
    },
    afterDeleteFn: function () {
    },

    beforeDetailFn: function () {
        return true;
    },
    afterDetailFn: function () {
    },

    beforeUpdateFn: function () {
        return true;
    },
    beforeDoUpdateFn: function () {
        return true;
    },
    afterUpdateFn: function () {
    },

    beforeMoveFn: function () {
        return true;
    },
    beforeDoMoveFn: function () {
        return true;
    },
    afterMoveFn: function () {
    },


    //datagrid default set 
    loadMsg: '���ݼ�����,���Ժ�...',
    fitColumns: false,
    nowrap: true,
    rownumbers: true,
    pagination: true,
    pageSize: 18,
    pageList: 18,
    width: "auto",
    height: "auto",
    singleSelect: false,
    idField: 'id',
    fit: false,
    dBodyHeight: 126,//��Ҫ��ȥ�ĸ߶�
    moveSelectedId: "moveSelectedId",
    moveParentId: "moveParentId",

    init: function () {
        this.initBodyHeight();
        this.initDatagrid();
        this.initInfoDlg();
        this.initPagination();
        this.datagridReload();
        try {
            this.initMoveDlg();
        } catch (e) {
        }  //��Ϊת�ƹ��ܲ���һ����Ҫ����ʹ��try
    },
    initBodyHeight: function () {
        var bh = parent.document.body.clientHeight;
        $("body").css("height", (bh - this.dBodyHeight) + "px");
        this.height = document.body.clientHeight;
    },
    initDatagrid: function () {

        $('#' + this.datagridId).datagrid({
            //url:myDatagrid.parseAction(myDatagrid.dataUrl), //������������URL����Ϊ�˽��IE���������
            loadMsg: this.loadMsg,
            width: this.width,
            height: this.height,
            fitColumns: this.fitColumns,
            nowrap: this.nowrap,
            rownumbers: this.rownumbers,
            pagination: this.pagination,
            pageSize: this.pageSize,
            pageList: [this.pageList],
            queryParams: this.queryParams,
            singleSelect: this.singleSelect,
            idField: this.idField,
            fit: this.fit,
            columns: [this.columns]

        });
    },
    datagridSelections: function () {
        return $('#' + this.datagridId).datagrid('getSelections');
    },
    datagridSelected: function () {
        return $('#' + this.datagridId).datagrid('getSelected');
    },
    clearSelections: function () {
        $('#' + this.datagridId).datagrid("clearSelections");
    },
    unselectRow: function (index) {
        $('#' + this.datagridId).datagrid("unselectRow", index);
    },
    selectRecord: function (idv) {
        return $('#' + this.datagridId).datagrid("selectRecord", idv);
    },
    selectRow: function (index) {
        return $('#' + this.datagridId).datagrid("selectRow", index);
    },
    selectAll: function () {
        return $('#' + this.datagridId).datagrid("selectAll");
    },
    datagridReload: function () {
        $("#" + this.queryFormId).attr("action", this.parseAction(this.queryAction));
        $('#' + this.datagridId).datagrid('options').url = this.parseAction(this.dataUrl);
        var queryParams = $('#' + this.datagridId).datagrid('options').queryParams;
        for (var key in queryParams) {
            for (var _key  in  this.queryParamsVCN) {
                if (key == _key) {
                    queryParams[key] = encodeURIComponent($("#" + this.queryParamsVCN[_key]).val());
                }
            }
        }
        $('#' + this.datagridId).datagrid('reload');
    },
    initPagination: function () {
        var pager = $('#' + this.datagridId).datagrid('getPager')
        pager.pagination({
            onSelectPage: function (page, rows) {
                myDatagridObj.datagridReload();
            }
        });
    },
    resize: function (_width, _height) {
        $('#' + this.datagridId).datagrid("resize", {width: _width});
    },
    doSearch: function () {
        this.datagridReload();
    },
    moveTreeReload: function () {
        $('#' + this.moveTreeId).combotree("reload");
    },
    getMoveTreeVal: function () {
        return $('#' + this.moveTreeId).combotree('getValue');
    },
    getMoveSelectedIds: function () {
        return $('#' + this.moveSelectedId).val();
    },
    getMoveParentId: function () {
        return $('#' + this.moveParentId).val();
    },
    initInfoDlg: function () {
        $('#' + this.infoFormId).css("display", "block");
        $('#' + this.infoDlgDivId).dialog({
            resizable: false,
            collapsible: true,
            modal: true,
            buttons: [{
                text: 'ȷ��',
                iconCls: 'icon-ok',
                handler: function () {
                    if (myDatagridObj.addOrUpdateFlag == 0)
                        myDatagridObj.doAdd();
                    else if (myDatagridObj.addOrUpdateFlag == 1)
                        myDatagridObj.doUpdate();
                }
            }, {
                text: '�ر�',
                iconCls: 'icon-cancel',
                handler: function () {
                    myDatagridObj.closeInfoDlg();
                }
            }]

        });

    },
    initMoveDlg: function () {
        $('#' + this.moveFormId).css("display", "block");
        $('#' + this.moveDlgDivId).dialog({
            resizable: false,
            collapsible: true,
            modal: true,
            buttons: [{
                text: 'ȷ��',
                iconCls: 'icon-ok',
                handler: function () {
                    myDatagridObj.doMove();
                }
            }, {
                text: '�ر�',
                iconCls: 'icon-cancel',
                handler: function () {
                    myDatagridObj.closeMoveDlg();
                }
            }]

        });

        $('#' + this.moveFormId).append("<input type='hidden' name='" + this.moveSelectedId + "'  id='" + this.moveSelectedId + "' />");
        $('#' + this.moveFormId).append("<input type='hidden' name='" + this.moveParentId + "'  id='" + this.moveParentId + "' />");
    },
    showInfoDlg: function () {
        $('#' + this.infoDlgDivId).dialog("open");
    },
    closeInfoDlg: function () {
        $('#' + this.infoDlgDivId).dialog("close");
    },
    showMoveDlg: function () {
        $('#' + this.moveDlgDivId).dialog("open");
    },
    closeMoveDlg: function () {
        $('#' + this.moveDlgDivId).dialog("close");
    },
    toAdd: function () {
        this.addOrUpdateFlag = 0;
        if (!this.beforeAddFn()) return;
        this.showInfoDlg();
    },
    toDetail: function () {
        if (!this.beforeDetailFn()) return;
        this.showInfoDlg();
    },
    toUpdate: function () {
        this.addOrUpdateFlag = 1;
        var tmp = this.datagridSelected();
        if (tmp == null) {
            $.messager.alert('ϵͳ��ʾ', '<br>��ѡ����Ҫ�޸ĵļ�¼��', 'info');
            return;
        }
        if (!this.beforeUpdateFn()) return;
        this.showInfoDlg();
    },
    toMove: function () {
        var tmp = this.datagridSelections();
        if (tmp.length <= 0) {
            $.messager.alert('ϵͳ��ʾ', '<br>��ѡ����Ҫת�Ƶļ�¼��', 'info');
            return;
        }

        if (!this.beforeMoveFn()) return;
        this.showMoveDlg();
    },
    doAdd: function () {
        $("#" + this.infoFormId).attr("action", this.parseAction(this.infoAddAction));
        $('#' + this.infoFormId).form('submit', {
            onSubmit: function () {
                if (!myDatagridObj.beforeDoAddFn()) return;
                return $(this).form('validate');
            },
            success: function (data) {
                if (data.Trim() == "success") {
                    //$.messager.alert('ϵͳ��ʾ','<br>���ӳɹ���','info');
                    myDatagridObj.closeInfoDlg();
                    myDatagridObj.datagridReload();
                    myDatagridObj.afterDoAddFn();
                } else {
                    $.messager.alert('ϵͳ��ʾ', '<br>����ʧ�ܡ�', 'info');
                }
            }
        });

    },
    doUpdate: function () {
        $("#" + this.infoFormId).attr("action", this.parseAction(this.infoUpdateAction));
        $('#' + this.infoFormId).form('submit', {
            onSubmit: function () {
                if (!myDatagridObj.beforeDoUpdateFn()) return;
                return $(this).form('validate');
            },
            success: function (data) {
                if (data.Trim() == "success") {
                    //$.messager.alert('ϵͳ��ʾ','<br>�޸ĳɹ���','info');
                    myDatagridObj.closeInfoDlg();
                    myDatagridObj.clearSelections();
                    myDatagridObj.datagridReload();
                    myDatagridObj.afterUpdateFn();
                } else {
                    $.messager.alert('ϵͳ��ʾ', '<br>�޸�ʧ�ܡ�', 'info');
                }
            }
        });

    },
    doDelete: function () {
        if (!this.beforeDeleteFn()) return;

        var tmp = this.datagridSelections();
        if (tmp.length <= 0) {
            $.messager.alert('ϵͳ��ʾ', '<br>��ѡ����Ҫɾ���ļ�¼��', 'info');
            return;
        }
        $.messager.confirm('ϵͳ��ʾ', '<br>�����Ҫɾ����Щ������?<br>' + this.deleteMsg, function (r) {
            if (r) {
                var ids = "";
                for (var i = 0; i < tmp.length; i++) {
                    if (ids == "")
                        ids = (tmp[i]).id;
                    else
                        ids += "," + (tmp[i]).id;
                }
                //alert(tmp+"--"+tmp.length+"--"+ids); 
                $.ajax({
                    type: "POST",
                    data: {"ids": ids},
                    url: myDatagridObj.parseAction(myDatagridObj.deleteAction),
                    success: function (data) {
                        if (data.Trim() == "success") {
                            //$.messager.alert('ϵͳ��ʾ','<br>ɾ���ɹ���','info');
                            myDatagridObj.clearSelections();
                            myDatagridObj.datagridReload();
                            myDatagridObj.afterDeleteFn();
                        } else {
                            $.messager.alert('ϵͳ��ʾ', '<br>ɾ��ʧ�ܡ�', 'info');
                        }
                    }
                });

            }
        });
    },
    doMove: function () {
        var s = "sdf";
        $('#' + this.moveFormId).form('submit', {
            onSubmit: function () {

                var val = myDatagridObj.getMoveTreeVal();
                if (val == "") {
                    $.messager.alert('ϵͳ��ʾ', '<br>��ѡ����Ҫת��ĸ��ࡣ', 'info');
                    return false;
                }

                var tmp = myDatagridObj.datagridSelections();
                var ids = "";
                for (var i = 0; i < tmp.length; i++) {
                    if (((tmp[i]).id) != val) {
                        if (ids == "")
                            ids = (tmp[i]).id;
                        else
                            ids += "," + (tmp[i]).id;
                    }
                }
                $("#" + myDatagridObj.moveSelectedId).val(ids);
                $("#" + myDatagridObj.moveParentId).val(val);
                if (!myDatagridObj.beforeDoMoveFn()) return;
                return $(this).form('validate');
            },
            success: function (data) {
                if (data.Trim() == "success") {
                    //$.messager.alert('ϵͳ��ʾ','<br>ת�Ƴɹ���','info');
                    myDatagridObj.clearSelections();
                    myDatagridObj.closeMoveDlg();
                    myDatagridObj.datagridReload();
                    myDatagridObj.moveTreeReload();
                    myDatagridObj.afterMoveFn();

                } else {
                    $.messager.alert('ϵͳ��ʾ', '<br>ת��ʧ�ܡ�', 'info');
                }
            }
        });
    },
    parseAction: function (action) {
        if (action.indexOf('?') >= 0)
            return action += "&xxtime=" + (new Date().getTime());
        else
            return action += "?xxtime=" + (new Date().getTime());
    }
};