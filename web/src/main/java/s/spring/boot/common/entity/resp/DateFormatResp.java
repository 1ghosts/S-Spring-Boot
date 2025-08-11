package s.spring.boot.common.entity.resp;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author chensihong
 * @version 1.0
 * @date 2024/3/14 23:25
 */
@Builder
@Data
public class DateFormatResp {
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime localDateTime;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date date;
}
