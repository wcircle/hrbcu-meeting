package com.zxy.hrbcu.meeting.util;

import java.io.Serializable;

public class Page implements Serializable {
    private int currentPageNum = 1;
    private int totalPageNum;
    private int totalCount;
    private int perPageSize = 10;

    public Page() {
    }

    public Page(int currentPageNum, int perPageSize) {
        this.currentPageNum = currentPageNum;
        this.perPageSize = perPageSize;
    }

    public Page(int currentPageNum, int totalCount, int perPageSize) {
        this.totalCount = totalCount;
        this.perPageSize = perPageSize;
        this.totalPageNum = totalCount % perPageSize == 0?totalCount / perPageSize:totalCount / perPageSize + 1;
        this.currentPageNum = currentPageNum < 1?1:(currentPageNum > this.totalPageNum?this.totalPageNum:currentPageNum);
    }

    public Page(int totalCount) {
        this.totalCount = totalCount;
        this.totalPageNum = totalCount % this.perPageSize == 0?totalCount / this.perPageSize:totalCount / this.perPageSize + 1;
        this.currentPageNum = this.currentPageNum < 1?1:(this.currentPageNum > this.totalPageNum?this.totalPageNum:this.currentPageNum);
    }

    public int getCurrentPageNum() {
        return this.currentPageNum;
    }

    public void setCurrentPageNum(int currentPageNum) {
        this.currentPageNum = currentPageNum < 1?1:(currentPageNum > this.totalPageNum?this.totalPageNum:currentPageNum);
    }

    public int getTotalPageNum() {
        return this.totalPageNum;
    }

    public void setTotalPageNum(int totalPageNum) {
        this.totalPageNum = this.totalCount % this.perPageSize == 0?this.totalCount / this.perPageSize:this.totalCount / this.perPageSize + 1;
    }

    public int getTotalCount() {
        return this.totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
        this.totalPageNum = totalCount % this.perPageSize == 0?totalCount / this.perPageSize:totalCount / this.perPageSize + 1;
    }

    public int getPerPageSize() {
        return this.perPageSize;
    }

    public void setPerPageSize(int perPageSize) {
        this.perPageSize = perPageSize;
    }

    public String toString() {
        return "Page{currentPageNum=" + this.currentPageNum + ", totalPageNum=" + this.totalPageNum + ", totalCount=" + this.totalCount + ", perPageSize=" + this.perPageSize + '}';
    }
}
