package cn.featherfly.common.structure.page;

/**
 * <p>
 * 简单分页模型实现
 * </p>
 *
 * @author zhongj
 */
public class SimplePagination implements Pagination {

    /**
     */
    public SimplePagination() {
    }

    /**
     * @param limit limit
     */
    public SimplePagination(Limit limit) {
        this(limit.getOffset(), limit.getLimit());
    }

    /**
     * @param offset offset
     * @param limit  limit
     */
    public SimplePagination(int offset, int limit) {
        pageSize = limit;
        pageNumber = (offset + limit) / limit;
    }

    private Integer total;

    private Integer pageSize;

    private Integer pageNumber;

    /**
     * <p>
     * 设置每页数量
     * </p>
     *
     * @param pageSize 每页数量
     */
    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * <p>
     * 设置页数
     * </p>
     *
     * @param pageNumber 页数
     */
    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    /**
     * <p>
     * 设置总数
     * </p>
     *
     * @param total 总数
     */
    public void setTotal(Integer total) {
        this.total = total;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer getTotalPage() {
        return (getTotal() + pageSize - 1) / pageSize;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer getTotal() {
        return total;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer getPageSize() {
        return pageSize;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer getPageNumber() {
        return pageNumber;
    }
}
