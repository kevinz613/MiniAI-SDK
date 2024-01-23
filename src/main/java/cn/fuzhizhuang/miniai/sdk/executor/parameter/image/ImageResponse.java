package cn.fuzhizhuang.miniai.sdk.executor.parameter.image;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * 图像响应
 *
 * @author zhuang.zhi
 */
@Data
public class ImageResponse implements Serializable {
    @Serial
    private static final long serialVersionUID = 5840362533193743342L;

    /**
     * 条目数据
     */
    private List<Image> data;

    /**
     * 创建时间
     */
    private long created;
}
