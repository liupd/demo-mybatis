package com.hong.mybatis.util;

import java.util.List;

/**
 * Created by Cai on 2014/4/23 13:15.
 */
public class PaginationUtil {
    public static class PageBean {
        private int currentPage;// 当前页数
        private int pageSize;// 每页显示的条数
        private int total;// 总条数
        private int pageTotal;// 总页数
        private int beginPageIndex;// 开始索引对应
        private int endPageIndex;// 结束索引
        private List<?> resultList;// 分页结果

        public PageBean(int currentPage, int pageSize, int total, int stepSize, List<?> resultList) {
            this.currentPage = currentPage;
            this.pageSize = pageSize;
            this.total = total;
            this.pageTotal = (total + pageSize - 1) / pageSize;
            if (pageTotal <= stepSize) {
                this.beginPageIndex = 1;
                this.endPageIndex = this.pageTotal;
            } else {
                int beforeIndex = stepSize % 2 == 0 ? (stepSize / 2 - 1) : (stepSize / 2);
                int afterIndex = stepSize / 2;

                this.beginPageIndex = currentPage - beforeIndex;
                this.endPageIndex = currentPage + afterIndex;
                if (this.beginPageIndex < 1) {
                    this.beginPageIndex = 1;
                    this.endPageIndex = stepSize;
                }
                if (this.endPageIndex > this.pageTotal) {
                    this.endPageIndex = this.pageTotal;
                    this.beginPageIndex = this.pageTotal - stepSize + 1;
                }
            }
            this.resultList = resultList;
        }

        public int getCurrentPage() {
            return currentPage;
        }

        public void setCurrentPage(int currentPage) {
            this.currentPage = currentPage;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getPageTotal() {
            return pageTotal;
        }

        public void setPageTotal(int pageTotal) {
            this.pageTotal = pageTotal;
        }

        public int getBeginPageIndex() {
            return beginPageIndex;
        }

        public void setBeginPageIndex(int beginPageIndex) {
            this.beginPageIndex = beginPageIndex;
        }

        public int getEndPageIndex() {
            return endPageIndex;
        }

        public void setEndPageIndex(int endPageIndex) {
            this.endPageIndex = endPageIndex;
        }

        public List<?> getResultList() {
            return resultList;
        }

        public void setResultList(List<?> resultList) {
            this.resultList = resultList;
        }
    }

    public static PageBean pagingList(Integer currentPage, Integer pageSize, Integer total, Integer stepSize, List<?> list) {
        return new PageBean(currentPage, pageSize, total, stepSize, list);
    }
}
