package cn.fuzhizhuang.miniai.sdk.executor.model.chatglm.valobj;


import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * 图片响应
 *
 * @author zhuang.zhi
 */
@Data
public class ChatGLMImageResponse implements Serializable {
    @Serial
    private static final long serialVersionUID = -2232303797108028871L;

    /**
     * 包含生成的图片 URL。目前数组中只包含一张图片。
     */
    private List<ImageItem> data;
    /**
     * 请求创建时间，是以秒为单位的Unix时间戳。
     */
    private String created;


    @Data
    public static class ImageItem{
        /**
         * 图片链接
         */
        private String url;
    }
}
