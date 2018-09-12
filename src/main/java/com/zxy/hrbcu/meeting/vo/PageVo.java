package com.zxy.hrbcu.meeting.vo;

import java.io.Serializable;

/**
 * @author wenxu
 * @Description 分页vo
 * @create 2017-5-24 09:55:45
 */
public class PageVo implements Serializable {
    private Integer currentPageNum = 1;// 当前第几页(默认第一页),---主要用于传递到前台显示
    private Integer perPageSize = 10;// 每页显示的记录条数(默认10条)
    private Integer totalPageNum=0;// 总页数
    private Integer totalCount=0;// 总记录数

    public PageVo() {
    }

    public PageVo(Integer currentPageNum, Integer perPageSize) {

        this.currentPageNum = currentPageNum;
        this.perPageSize = perPageSize;
    }

    public Integer getCurrentPageNum() {
        return currentPageNum;
    }

    public void setCurrentPageNum(Integer currentPageNum) {
        this.currentPageNum = currentPageNum;
    }

    public Integer getPerPageSize() {
        return perPageSize;
    }

    public void setPerPageSize(Integer perPageSize) {
        this.perPageSize = perPageSize;
    }

    public Integer getTotalPageNum() {
        return totalPageNum;
    }

    public void setTotalPageNum(Integer totalPageNum) {
        this.totalPageNum = totalPageNum;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }
}
