Ext.namespace('Ext.spring.dataConfiguration.umanege.accountManage');//自定一个命名空间，防止同名方法发生冲突
Ext.onReady(function () {
    Ext.tip.QuickTipManager.init();

    Ext.define('App.DeptManagementWindow', {
        extend : 'Ext.window.Window',
        constructor : function(config) {
            config = config || {};
            Ext.apply(config, {
                title : '部门信息',
                width : 400,
                height : 250,
                bodyPadding : '10 5',
                modal : true,
                layout : 'fit',
                items : [ {
                    xtype : 'form',
                    fieldDefaults : {
                        labelAlign : 'left',
                        labelWidth : 90,
                        anchor : '100%'
                    },
                    items : [
                        {
                            xtype : 'combobox',
                            fieldLabel : '请选择单位',
                            name : 'company_name',
                            store : Ext.create('Ext.data.JsonStore', {
                                proxy : {
                                    type : 'ajax',
                                    url : appBaseUri + '/dnw/dept/loadCompany',
                                    reader : {
                                        type : 'json',
                                        root : 'list',
                                        idProperty : 'ItemValue'
                                    }
                                },
                                fields : [ 'ItemText', 'ItemValue' ]
                            }),
                            valueField : 'ItemValue',
                            displayField : 'ItemText',
                            typeAhead : true,
                            queryMode : 'remote',
                            editable : false,
                            allowBlank : false,
                            listeners : {
                                select : function(combo, record, index) {
                                    var value = combo.getValue();
                                    this.up('form').getForm().findField('company_id').setValue(value);
                                }
                            }
                        },
                        {
                            name : "cmd",
                            xtype : "hidden",
                            value : 'new'
                        }, {
                            xtype : 'hiddenfield',
                            name : 'dept_id'
                        }, {
                            xtype : 'hiddenfield',
                            name : 'company_id'
                        }, {
                            xtype : 'textfield',
                            name : 'dept_name',
                            fieldLabel : '部门',
                            emptyText : '请输入部门',
                            allowBlank : false,
                            maxLength : 30
                        }],
                    buttons : [ '->', {
                        text : '保存',
                        iconCls : 'icon-save',
                        width : 80,
                        handler : function(btn, eventObj) {
                            var window = btn.up('window');
                            var form = window.down('form').getForm();
                            if (form.isValid()) {
                                window.getEl().mask('数据保存中，请稍候...');
                                var vals = form.getValues();
                                var company_id="";
                                company_id=vals['company_id'];
                                Ext.Ajax.request({
                                    url : appBaseUri + '/dnw/dept/saveUpdateDept',
                                    params : {
                                        cmd : vals['cmd'],
                                        dept_id : vals['dept_id'],
                                        dept_name : vals['dept_name'],
                                        company_id : company_id
                                    },
                                    method : 'POST',
                                    success : function(response) {
                                        window.getEl().unmask();
                                        if (response.responseText != '') {
                                            var res = Ext.JSON.decode(response.responseText);
                                            if (res.success) {
                                                window.close();
                                                globalObject.msgTip('操作成功！');
                                                Ext.getCmp('dept_grid').getStore().reload();
                                            } else {
                                                globalObject.msgTip(res.message);
                                            }
                                        }
                                    },
                                    failure : function(response) {
                                        window.getEl().unmask();
                                        globalObject.errTip('操作失败！');
                                    }
                                });
                            }
                        }
                    }, {
                        text : '取消',
                        iconCls : 'icon-cancel',
                        width : 80,
                        handler : function() {
                            this.up('window').close();
                        }
                    }]
                } ]
            });
            App.DeptManagementWindow.superclass.constructor.call(this, config);
        }
    });
    Ext.define('Forestry.app.umanage.accountManage', {
        extend : 'Ext.grid.Panel',
        region : 'center',
        initComponent : function() {
            var me = this;
            Ext.define('ModelList', {
                extend : 'Ext.data.Model',
                idProperty : 'region_id',
                fields : [ {
                    name : 'region_id'
                }, 'region_name']
            });
            var store = Ext.create('Ext.data.Store', {
                model : 'ModelList',
                remoteSort : true,
                pageSize : globalPageSize,
                proxy : {
                    type : 'ajax',
                    url : appBaseUri + '/dnw/account/getRegionList',
                    extraParams : me.extraParams || null,
                    reader : {
                        type : 'json',
                        root : 'data',
                        totalProperty : 'totalRecord',
                        successProperty : "success"
                    }
                },
                sorters : [ {
                    property : 'region_id',
                    direction : 'DESC'
                }]
            });
            var rownum = new Ext.grid.RowNumberer({
                header : '序号',
                flex: 0.05,
                align:'center'
            });
            var columns = [rownum, {
                text : "ID",
                xtype : "hidden",
                dataIndex : 'region_id',
                flex : 0.05
            },{
                text : "地区名称",
                align:'center',
                dataIndex : 'region_name',
                flex : 0.6
            }, {text : '操作项',dataIndex : 'oper',sortable : false,flex: 0.20,
                renderer : function(value, cellmeta, record, rowIndex, columnIndex, store){
                    return "<span class='tip_info' onclick='Ext.roof.dataConfiguration.umanage.DeptManage.dept_edit()'>修改</span>"
                        + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + "<span class='tip_danger' " +
                        "onclick='Ext.roof.dataConfiguration.umanage.DeptManage.dept_del()'>删除</span>";
                }
            }];
            var ttoolbar = Ext.create('Ext.toolbar.Toolbar', {
                items: [{
                    xtype : 'textfield',
                    id : 'dept_name',
                    name : 'dept_name',
                    hideLabel : true,
                    fieldLabel : '部门名称',
                    maxLength : 100,
                    emptyText : '请输入部门'
                }, {
                    xtype : 'button',
                    text : '搜索',
                    iconCls : 'icon-search',
                    width : '15%',
                    maxWidth : 60,
                    handler : function(btn, eventObj) {
                        var searchParams = {
                            dept_name : Ext.getCmp('dept_name').getValue()
                        };
                        Ext.apply(store.proxy.extraParams, searchParams);
                        store.reload();
                    }
                }, '->',
                    {
                        xtype: 'button',
                        text: '新增部门',
                        iconCls: 'icon-add',
                        width : '25%',
                        maxWidth : 100,
                        handler: function (btn, eventObj) {
                            var win = new App.DeptManagementWindow({
                                hidden : true
                            });
                            var form = win.down('form').getForm();
                            form.findField('cmd').setValue('new');
                            win.show();
                            if (companyId!='00') {
                                form.findField('company_id').setValue(companyId);
                                form.findField('company_name').setValue(companyName);
                                form.findField('company_name').disable(true);
                            }
                        }
                    }

                ]
            });

            Ext.apply(this, {
                id : 'dept_grid',
                store : store,
                columns : columns,
                tbar : ttoolbar,
                //selModel : Ext.create('Ext.selection.CheckboxModel'),
                bbar : Ext.create('Ext.PagingToolbar', {
                    store : store,
                    displayInfo : true
                })
            });
            store.loadPage(1);
            this.callParent(arguments);
        }
    });

    //修改的按钮函数
    Ext.roof.dataConfiguration.umanage.DeptManage.dept_edit = function () {
        var record = Ext.getCmp('dept_grid').getSelectionModel().getLastSelected();
        //以window的名字.DeptManagementWindow
        var win = new App.DeptManagementWindow({
            hidden : true
        });
        var form = win.down('form').getForm();
        form.loadRecord(record);
        form.findField("cmd").setValue("edit");
        if (companyId!='00') {
            form.findField('company_name').disable(true);
        }
        win.show();
    }

    //删除的按钮函数
    Ext.roof.dataConfiguration.umanage.DeptManage.dept_del = function () {
        var record = Ext.getCmp('dept_grid').getSelectionModel().getLastSelected();
        globalObject.confirmTip('请确认，是否执行删除操作？', function(btn) {
            if (btn == 'yes') {
                var id = record.get('dept_id');
                Ext.Ajax.request({
                    url : appBaseUri + '/dnw/dept/deleteDept',
                    params : {
                        ids : id
                    },
                    success : function(response) {
                        if (response.responseText != '') {
                            var res = Ext.JSON.decode(response.responseText);
                            if (res.success) {
                                globalObject.msgTip('操作成功！');
                                //Ext.roof.dataConfiguration.umanage.DeptManage.linemanage_tree_refresh();
                                Ext.getCmp('dept_grid').getStore().reload();
                            } else {
                                globalObject.errTip('操作失败！' + res.msg);
                            }
                        }
                    }
                });
            }
        });
    }
});