/**
 * @author zhixin wen <wenzhixin2010@gmail.com>
 * extensions: https://github.com/kayalshri/tableExport.jquery.plugin
 */

(function ($) {
    'use strict';
    var sprintf = $.fn.bootstrapTable.utils.sprintf;

    var TYPE_NAME = {
        json: 'JSON',
        xml: 'XML',
        png: 'PNG',
        csv: 'CSV',
        txt: 'TXT',
        sql: 'SQL',
        doc: 'MS-Word',
        excel: 'MS-Excel',
        powerpoint: 'MS-Powerpoint',
        pdf: 'PDF'
    };

    $.extend($.fn.bootstrapTable.defaults, {
        showExport: false,
        exportDataType: 'basic', // basic, all, selected
        // 'json', 'xml', 'png', 'csv', 'txt', 'sql', 'doc', 'excel', 'powerpoint', 'pdf'
        exportTypes: ['json', 'xml', 'csv', 'txt', 'sql', 'excel'],
        exportOptions: {}
    });

    $.extend($.fn.bootstrapTable.defaults.icons, {
        export: 'glyphicon-export icon-share'
    });

    $.extend($.fn.bootstrapTable.locales, {
        formatExport: function () {
            return 'Export data';
        }
    });
    $.extend($.fn.bootstrapTable.defaults, $.fn.bootstrapTable.locales);

    var BootstrapTable = $.fn.bootstrapTable.Constructor,
        _initToolbar = BootstrapTable.prototype.initToolbar;

    BootstrapTable.prototype.initToolbar = function () {
        this.showToolbar = this.options.showExport;

        _initToolbar.apply(this, Array.prototype.slice.apply(arguments));

        if (this.options.showExport) {
            var that = this;
            var fileName = this.options.fileName;
            var def = fileName?{"fileName":fileName}:{};
            var doExport = function (options) {
                that.$el.tableExport($.extend(def,options, {
                    type: "excel",
                    escape: false
                }));
            };
            var _toolBar = this.$toolbar.find('>.bs-bars').find("#toolbar");
            var addButtons = _toolBar.find(".cex");
            if(!addButtons.length){
            	_toolBar.append('<button id="exselected" type="button" class="layui-btn cex"><li class="fa fa-file-excel-o"></li>&nbsp;导出选择项</button>&nbsp;<button id="exPage" type="button" class="layui-btn cex"><li class="fa fa-file-excel-o"></li>&nbsp;导出本页</button>');
                this.options.showType =='all' && _toolBar.append('&nbsp;<button id="exAll" type="button" class="layui-btn cex"><li class="fa fa-file-excel-o"></li>&nbsp;导出全部</button>');
            }
            _toolBar.find("#exselected").eq(0).click(function(){
            	var selectedData = that.getAllSelections();
            	if(!selectedData.length){
            		layer.alert("请选择至少一条数据");
            		return false;
            	}
            	var data = that.getData();
	            that.load({"rowCount":selectedData.length,"records":selectedData});
	            doExport();
	            that.load({"rowCount":data.length,"records":data});
            }); 
            
            _toolBar.find("#exPage").eq(0).click(function(){
            	doExport({exportDataType:"basic"});
            });
            
            this.options.showType =='all' && _toolBar.find("#exAll").eq(0).click(function(){
            	that.$el.one('post-body.bs.table', function () {
            		doExport();
            		that.togglePagination();
                });
            	that.togglePagination();
            }); 
        }
    };
})(jQuery);