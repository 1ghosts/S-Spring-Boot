package s.spring.boot.common.result;

/**
 * Created on 3/20/21.
 *
 * @author chensihong
 */
public class BasePage {
    private Integer page;
    private Integer size;

    public Integer getPage() {
        if (this.page == null || this.page < 1) {
            return this.page = 1;
        }
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        if (this.size == null || this.size < 1) {
            return this.page = 10;
        }
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
