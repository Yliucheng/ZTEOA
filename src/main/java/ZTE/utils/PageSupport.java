package ZTE.utils;

public class PageSupport {

    private Integer pageSize;
    private Integer currentPageNo=1;//当前页号
    public static final Integer pageSizeVal=5;//页面尺寸(每页显示几条数据)
    private Integer totalCount;//总记录数
    private Integer totalPageCount;//总共有页面数

    public Integer getCurrentPageNo() {
        return currentPageNo;
    }

    public void setCurrentPageNo(Integer currentPageNo) {
        this.currentPageNo = currentPageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
        //得到总记录数之后，总页数等于总记录数/页面尺寸,如果除不尽就+1页
        this.totalPageCount=
                this.totalCount%this.pageSize==0?this.totalCount/this.pageSize:this.totalCount/this.pageSize+1;
    }

    public Integer getTotalPageCount() {
        return totalPageCount;
    }

    public void setTotalPageCount(Integer totalPageCount) {
        this.totalPageCount = totalPageCount;
    }
}
