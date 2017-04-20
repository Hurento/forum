package com.sy.forum.core.entity;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.sy.forum.utils.Utils;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author SY
 * @Description: DataTable插件所用的分页封装
 * @Date 2017/3/13 18:17
 */
public class DataTablePage<T> implements Serializable {
    public final static String DRAW = "draw";
    public final static String IDISPLAY_START = "start";
    public final static String IDISPLAY_LENGTH = "length";
    public final static String ORDER = "order";
    public final static String ORDER_COLUMN = "column";
    public final static String ORDER_DIR = "dir";
    public final static String IRECORDSTOTAL = "recordsTotal";
    public final static String IRECORDSFILTERED = "recordsFiltered";
    private static final long serialVersionUID = 1L;
    private String draw;                                        // 获取请求次数
    private int start = 1;                    // 数据起始位置
    private int length = 10;                    // 数据长度
    private long recordsTotal = 0;                    // 总记录数
    private long recordsFiltered = 0;                    // 过滤后记录数
    private int pageNum = 1;                    // 第几页
    private List<T> data = new ArrayList<T>();
    private String orderStr;

    public DataTablePage() {

    }

    /**
     * 默认controller中获取基本的三个参数
     *
     * @param request
     */
    public DataTablePage(HttpServletRequest request) {
        this.draw = request.getParameter(DRAW);
        String startString = request.getParameter(IDISPLAY_START);
        String lengthString = request.getParameter(IDISPLAY_LENGTH);
        if (!Utils.isEmpty(startString)) {
            // 前端的参数是从0开始的
            this.start = Integer.valueOf(startString);
        }
        if (lengthString != null && !("").equals(lengthString.trim())) {
            this.length = Integer.valueOf(lengthString);
        }
        this.pageNum = start / length + 1;
        // 排序参数转换
        String orderString = request.getParameter(ORDER);
        if (Utils.isEmpty(orderString)) {
            return;
        }

        JSONArray orderJsonArray = JSON.parseArray(orderString);
        JSONObject obj = null;
        StringBuffer orderBuffer = new StringBuffer("");
        for (int i = 0; i < orderJsonArray.size(); i++) {
            obj = orderJsonArray.getJSONObject(i);
            orderBuffer.append(obj.getString(ORDER_COLUMN));
            orderBuffer.append(" ");
            orderBuffer.append(obj.getString(ORDER_DIR));
            if (i != (orderJsonArray.size() - 1)) {
                orderBuffer.append(",");
            }
        }
        this.orderStr = orderBuffer.toString();
    }

    public DataTablePage<T> transfer(DataTablePage<T> result, PageInfo<T> pageList) {
        result.setRecordsTotal(pageList.getPageSize());
        result.setRecordsFiltered(pageList.getTotal());
        result.setData(pageList.getList());
        return result;
    }

    public String getDraw() {
        return draw;
    }

    public void setDraw(String draw) {
        this.draw = draw;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public long getRecordsTotal() {
        return recordsTotal;
    }

    public void setRecordsTotal(long recordsTotal) {
        this.recordsTotal = recordsTotal;
    }

    public long getRecordsFiltered() {
        return recordsFiltered;
    }

    public void setRecordsFiltered(long recordsFiltered) {
        this.recordsFiltered = recordsFiltered;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public String getOrderStr() {
        return orderStr;
    }

    public void setOrderStr(String orderStr) {
        this.orderStr = orderStr;
    }

}
