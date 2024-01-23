package cn.fuzhizhuang.miniai.sdk.executor.parameter.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 图像参数枚举
 *
 * @author zhuang.zhi
 */
public class Image {

    /**
     * 图片大小
     *
     * @author zhuang.zhi
     */
    @Getter
    @AllArgsConstructor
    public enum Size {
        SIZE_256("256x256"),
        SIZE_512("512x512"),
        SIZE_1024("1024x1024"),
        ;
        private final String code;
    }

    /**
     * 响应格式
     *
     * @author zhuang.zhi
     */
    @Getter
    @AllArgsConstructor
    public enum ResponseFormat {

        URL("url"),
        B64_JSON("b64_json"),
        ;

        private final String code;
    }

}
