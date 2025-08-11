package s.spring.boot.common.result;

import lombok.Data;

import java.util.List;

/**
 * Created on 3/22/21.
 *
 * @author chensihong
 */
@Data
public class PageResult<T> {
    /**
     * 实体
     */
    private List<T> content;
    /**
     * 当前页
     */
    private Integer number;
    /**
     * 当前页元素
     */
    private Integer numberOfElements;
    /**
     * 查询页元素条数
     */
    private Integer size;
    /**
     * 总数据条数
     */
    private Integer totalElements;
    /**
     * 总页数
     */
    private Integer totalPages;
}
