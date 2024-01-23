package cn.fuzhizhuang.miniai.sdk.executor.parameter.image;

import cn.fuzhizhuang.miniai.sdk.executor.parameter.enums.Model;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.io.Serial;
import java.io.Serializable;

/**
 * 图像请求
 *
 * @author zhuang.zhi
 */
@EqualsAndHashCode(callSuper = true)
@Slf4j
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ImageRequest extends Image implements Serializable {
    @Serial
    private static final long serialVersionUID = -8922894723764083151L;

    /**
     * 图片模型
     */
    @Builder.Default
    private String model = Model.GLM_COGVIEW_3.getCode();


    /**
     * 图片描述需求
     */
    @NonNull
    private String prompt;

    /**
     * 为每个提示生成的完成次数
     */
    @Builder.Default
    private Integer n = 1;

    /**
     * 图片大小
     */
    @Builder.Default
    private String size = cn.fuzhizhuang.miniai.sdk.executor.parameter.enums.Image.Size.SIZE_1024.getCode();

    /**
     * 响应格式 URL、B64_JSON
     */
    @JsonProperty("response_format")
    @Builder.Default
    private String responseFormat = cn.fuzhizhuang.miniai.sdk.executor.parameter.enums.Image.ResponseFormat.URL.getCode();

    /**
     * 调用标识，避免重复调用
     */
    private String user;
}
