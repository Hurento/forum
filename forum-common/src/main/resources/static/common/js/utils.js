var GlobalUtil = function() {


	/**
	 * js加法误差解决
	 */
	var addValue = function(value1, value2) {
		if (value1 == "")
			value1 = "0";
		if (value2 == "")
			value2 = "0";
		var temp1 = 0;
		var temp2 = 0;
		if (value1.indexOf(".") != -1)
			temp1 = value1.length - value1.indexOf(".") - 1;
		if (value2.indexOf(".") != -1)
			temp2 = value2.length - value2.indexOf(".") - 1;

		var temp = 0;

		if (temp1 > temp2)
			temp = (parseFloat(value1) + parseFloat(value2)).toFixed(temp1);
		else
			temp = (parseFloat(value1) + parseFloat(value2)).toFixed(temp2);

		return temp;
	};
	/**
	 * js减法误差解决
	 */
	var subValue = function(value1, value2) {
		if (value1 == "")
			value1 = "0";
		if (value2 == "")
			value2 = "0";
		var temp1 = 0;
		var temp2 = 0;
		if (value1.indexOf(".") != -1)
			temp1 = value1.length - value1.indexOf(".") - 1;
		if (value2.indexOf(".") != -1)
			temp2 = value2.length - value2.indexOf(".") - 1;
		var temp = 0;

		if (temp1 > temp2)
			temp = (parseFloat(value1) - parseFloat(value2)).toFixed(temp1);
		else
			temp = (parseFloat(value1) - parseFloat(value2)).toFixed(temp2);

		return temp;

	};

	/**
	 * 删除当前dialog
	 */
	var removeSignleDialog = function(obj) {
		var _dialog = $(obj).closest(".modal"),
			_modal = _dialog.next("div");
		_dialog.remove();
		_modal.remove();
	};
	
	/**
	 * 验证空
	 */
	var dataEmpty = function(v) {
    	return (v == undefined || v == null || v == "" || v.length <= 0);
    }
	
	/**
	 * 空值处理（-）
	 */
	var dataNullDeal = function(v,rechar) {
		if(rechar == undefined) rechar = "";
		if (dataEmpty(v)) {
			return v = rechar;
		} else {
			return v;
		}
	};

	/**
	 * 回车事件
	 * 为保证回车时刷新页面，请确保该页面的form下面的input超过一个
	 */
	var keydownEvent = function(eventMtehod) {
		$(document).keypress(function(e) {
		    // 回车键事件
		    if(e.which == 13) {
		    	eventMtehod();
		    }
	    });
    }
	
	/**
	 * 格式化table下面的tbody内容宽度
	 * 获取thead下面的th标签宽度
	 * 文本长度过长，以省略号显示，并定义其宽度
	 * tableId 
	 * value 文本值
	 * index th的下标
	 */
	var formatTableWidthandText = function(value, colWidth) {
		var _divHtml = "<div class='overflow-sign-text' style='width:"+ colWidth +";' title='" + GlobalUtil.dataNullDeal(value) + "'>" + GlobalUtil.dataNullDeal(value) + "</div>";
		return _divHtml;
	}
	
	/**
	 * 设置获得列宽或定义一个列宽
	 */
	var formatTableReturnWidth = function(tableId, trIndex, tdIndex, colWidth) {
		var _grid = document.getElementById(tableId).rows[trIndex].cells[tdIndex], _width;
		if(colWidth)
			_width = colWidth;
		_width = (_width < 100) ? (96 - 12) + 'px' : (_width - 12) + 'px';
		return _width;
	}


    /**
     * 取消千分位显示
     */
    var cancelDecimalNumberDataForma = function(number) {
        number = $.trim(number);
        return number.replace(/,/g, '');
    };

	/**
	 * 默认千分位分割方法
	 */
	var defaultNumberDataForma = function(obj, decimals) {
		if((decimals == undefined || decimals == "") && decimals != 0)
			decimals = 2;
		var tempValue = $(obj).val();
		if (tempValue != null && tempValue != "") {
			tempValue = tempValue.replace(/,/g, '');
			var value = number_format(tempValue, decimals, '.', ',');
			$(obj).val(value);
		}
	};
	
	/**
	 * 千分位通用函数 Usage: number_format(123456.789, 2, '.', ','); result: 123,456.79
	 */
	var number_format = function(number, decimals, dec_point, thousands_sep) {
		number = (number + '').replace(/[^0-9+-Ee.]/g, '');
		var n = !isFinite(+number) ? 0 : +number, prec = !isFinite(+decimals) ? 0
				: Math.abs(decimals), sep = (typeof thousands_sep === 'undefined') ? ','
				: thousands_sep, dec = (typeof dec_point === 'undefined') ? '.'
				: dec_point, s = '', toFixedFix = function(n, prec) {
			var k = Math.pow(10, prec);
			return '' + Math.round(n * k) / k;
		};
		// Fix for IE parseFloat(0.55).toFixed(0) = 0;
		s = (prec ? toFixedFix(n, prec) : '' + Math.round(n)).split('.');
		if (s[0].length > 3) {
			s[0] = ('' + s[0]).replace(/(\d)(?=(\d{3})+$)/g, '$1' + sep);
			// s[0] = s[0].replace(/B(?=(?:d{3})+(?!d))/g, sep);
		}
		if ((s[1] || '').length < prec) {
			s[1] = s[1] || '';
			s[1] += new Array(prec - s[1].length + 1).join('0');
		}
		return s.join(dec);
	}
	
	/**
	 * 创建tab标签页
	 * url 页面跳转路径
	 * name iframe的name值
	 * title tab标签页的title
	 */
	var createTab = function(url, name, title) {
        var c=parent.ConTab["c"]; //本页面为iframe嵌入，因此需要调用parent的ConTab对象，才能添加新的Tab
        c(url, name, title);
	};
	
	/**
	 * 删除打开的标签页
	 * prename 父级
	 * name iframe的name名称或排序碼（初始菜單為排序碼）
	 * id 查询按钮id
	 * isQuery 是否查询列表，值为true和false
	 */
	var delTab = function(prename, name, id, isQuery) {
		var cm = "iframe" + prename, nm = "iframe" + name, g = parent.ConTab["c"],index=""; 
		var $tabs = $(".page-tabs-content", window.parent.parent.document);
		var $iframs = $(".J_mainContent .J_iframe", window.parent.parent.document);
		if(isQuery) {
			var $iframeName = $("iframe[name='"+cm+"']",window.parent.document);
			var $dom = $iframeName.contents().find("#"+id);
			$dom.click();
		}
		$tabs.find(".J_menuTab").each(function(i){
			if(i == 0) return true;
			if ($(this).hasClass("active")) {
				$iframs.each(function() {
					var m = $(this).attr("name");
					if(m.indexOf("iframe") <= -1) {
			        	m = "iframe" + m;
					}
		            if (m === cm) {
                        $(this).show().siblings(".J_iframe").hide();
		                return false;
		            }
		        });
				$(this).remove();
				$iframs.each(function() {
					var m = $(this).attr("name");
					if(m.indexOf("iframe") <= -1) {
			        	m = "iframe" + m;
					}
		            if (m === nm) {
                        $(this).remove();
		                return false;
		            }
		        });
			}
			index = $(this).data("index");
			if(prename == index) {
				$(this).addClass("active");
			}
		});
	};
	
	/**
	 * 删除tab标签页
	 * url 页面跳转路径
	 * name iframe的name值
	 * title tab标签页的title
	 */
	var delAndCreateTab = function(url, name, title) {
		//获取父页面的iframe对象
		var iframeName = $("iframe[name='iframe"+name+"']",window.parent.document);
		//判断iframeName是否存在
		if(iframeName.length > 0 && iframeName != null && iframeName != undefined) {
			var $aDom = $(".page-tabs-content a", window.parent.parent.document);
			$aDom.each(function(i,obj){
				if($.trim($(obj).text()) == $.trim(title)) {
					//最后删掉tab标签页
					$(obj).find(".fa.fa-times-circle").click();
				}
			});
			//先创建
			//createTab(url, name, title);
		} else {
			//createTab(url, name, title);
		}
	};
	
	/**
     * 删除行
     * obj 当前对象
     * parent 父级
     * tabId table的id
     * flag 是否允许第一行显示序列
     */
    var removeCurrentRow = function(obj, parent,tabId, flag) {
    	$(obj).closest(parent).remove();
    	resetTableSortSequences($("#" + tabId), flag);//重新排列序号以及对象下标
    	var length = $("#" + tabId + " tbody").find("tr:not(.no-records-found)").length;
    	if(length <= 0) {
    		var cellsCount = $("#" + tabId + " thead tr:first() th").length;
    		var row = '<tr class="no-records-found"><td colspan="'+cellsCount+'">没有找到匹配的记录</td></tr>';
    		$("#" + tabId + " tbody").append(row);
    	}
    }
    
    /**
     * 复制  行-copy row
     */
    var createRow = function(tabId, defaultTabId) {
    	var tbody = $("#" + tabId + " tbody");//获取对象
    		tbody.find("tr.no-records-found").remove();//删除
    	var index = tbody.find("tr:not(.no-records-found)").length;//获取tr长度
    	var row = $("." + defaultTabId + " tbody").html();//获取默认html
    	row = row.replace(/rename/g, "name");//替换name
    	row = row.replace(/TABLE_INDEX/g, index);//替换下标
    	tbody.append(row);
    }
	
	/**
     * 重新为table排列序号，更新列表name下标
     */
    var resetTableSortSequences = function(table, flag) {
    	flag = (flag == undefined) ? true : flag;
    	var rows = $(table).find("tbody tr");//获取所有行
    	if(rows) {
        	//遍历所有行
	    	rows.each(function(i, row) {
	    		$(row).attr("data-index", i);
	    		var cells = $(row).find("td");//获取所有列
	    		if(cells) {
		    		//遍历td
	    			cells.each(function(j, cell) {
	    				if(j == (cells.length - 1)) {//过滤最后一列
	    					return true;
	    				}
	    				if(j == 0 && flag) {//第一列
	    					$(cell).find("span").first().text(i + 1);//为第一列赋值，序号
	    				}
	    				var inputs = $(cell).find("input");
	    				var selects = $(cell).find("select");
	    				eachRestAttrDom(inputs, i);
	    				eachRestAttrDom(selects, i);
	    				
	    			});
	    		}
	    	});
    	}
    }
    
    //遍历重置dom的name值
    var eachRestAttrDom = function(arrs, index) {
    	if(arrs) {
    		arrs.each(function(k, dom) {
    			var domName = $(dom).attr("name");//获取name的值
    			var start = domName.indexOf("[");//获取下标
    			var replaceChar = domName.substr(start, 3);//需要被替换的字符
    			domName = domName.replace(replaceChar, "["+ index +"]");//替换
    			$(dom).attr("name", domName);//替换后重新赋值
    		});
    	}
    }
    
    var createDialog = function(url,titleName){
	    $.ajax({
	        type : 'GET',
	        url : url,
	        async : false,
	        success : function(data) {
	            bootbox.dialog({
	                size : "large",
	                message : data,
	                title : titleName
	            });
	        }
	    });
    }

    /**
     * 提示
     */
    var globalConfirm = function(message, callBack, cancelBack) {
        message = GlobalUtil.valiadedIsEmpty(message) ? i18n.t('GENERAL.CONFIRM.GEN') : message;
        bootbox.confirm({
            size: 'small',
            message: message,
            buttons: {
                confirm: {
                    label: i18n.t('GENERAL.OK')
                },
                cancel: {
                    label: i18n.t('GENERAL.CANCEL')
                }
            },
            callback: function (v) {
                if (v) {
                    callBack();
                } else {
                    try {
                        cancelBack();
                    } catch (e) {
                    }
                }
            }
        });
    }

    /**
     * 通用ajax请求
     * obj 参数对象
     * type: 根据类型指定提示方式
     */
    var globalAjaxCallback = function(obj) {
        $.ajax({
            type : (obj.type) ? obj.type : 'POST',
            url : obj.url,
            data : obj.data,
            cache: false,
            async : (obj.async == undefined || obj.async == null) ? true : obj.async,
            error : function(x,s,v){
                var data = {
                    'resultCode' : 1,
                    'message' : v
                };
                obj.callback(data);
            },
            success : function(data) {
                var result = data;
                var dataHtml = "";
                if (result.resultCode == 0) {
                    dataHtml = i18n.t('GENERAL.SUCCESS');
                } else {
                    dataHtml = result.message;
                }
                var type = obj.alertType;
                if(type == "1") {// alert 提示并回调
                    bootbox.alert({
                        size : 'small',
                        message : dataHtml,
                        callback : function(v) {
                            obj.callback(data);
                        }
                    });
                } else if(type == "2") {//
                    bootbox.confirm({
                        size : 'small',
                        message : i18n.t('GENERAL.CONFIRM.GEN'),
                        buttons : {
                            confirm : {
                                label : i18n.t('GENERAL.OK')
                            },
                            cancel : {
                                label : i18n.t('GENERAL.CANCEL')
                            }
                        },
                        callback : function(v) {
                            if(v) {
                                obj.callback(data);
                            }
                        }
                    });
                } else if(type == "3") {
                    obj.callback(data);
                }

            }
        });
    }

    return {
		addValue : function(value1, value2) {
			return addValue(value1, value2);
		},
		subValue : function(value1, value2) {
			return subValue(value1, value2);
		},
		dealDataTableAjaxData : function(d) {
			return dealDataTableAjaxData(d);
		},
		removeSignleDialog : function(obj) {
			return removeSignleDialog(obj);
		},
		dataNullDeal : function(v,rechar) {
			return dataNullDeal(v,rechar);
		},
		dataEmpty : function(v) {
			return dataEmpty(v);
		},
		keydownEvent : function(eventMtehod) {
			keydownEvent(eventMtehod);
		},
		formatTableWidthandText : function(value, colWidth) {
			return formatTableWidthandText(value, colWidth);
		},
		formatTableReturnWidth : function(tableId, trIndex, tdIndex, colWidth) {
			return formatTableReturnWidth(tableId, trIndex, tdIndex, colWidth);
		},
        cancelDecimalNumberDataForma : function(obj) {
            cancelDecimalNumberDataForma(obj);
		},
		defaultNumberDataForma : function(obj, decimals) {
			defaultNumberDataForma(obj, decimals);
		},
		number_format : function(number, decimals, dec_point, thousands_sep) {
			return number_format(number, decimals, dec_point, thousands_sep);
		},
		createTab : function(url, name, title) {
			createTab(url, name, title);
		},
		delTab : function(prename, name, id, isQuery) {
			delTab(prename, name, id, isQuery);
		},
		delAndCreateTab : function(url, name, title) {
			delAndCreateTab(url, name, title);
		},
		removeCurrentRow : function(obj, parent, tabId, flag) {
			removeCurrentRow(obj, parent, tabId, flag);
		},
		createRow : function(tabId, defaultTabId) {
			createRow(tabId, defaultTabId);
		},
		resetTableSortSequences : function(table, flag) {
			resetTableSortSequences(table, flag);
		},
		createDialog : function(url,title){
			createDialog(url,title);
		},
        globalAjaxCallback : function(obj) {
            globalAjaxCallback(obj);
        },
        globalConfirm : function(message, callBack, cancelBack) {
            globalConfirm(message, callBack, cancelBack);
        }
	};
}();
