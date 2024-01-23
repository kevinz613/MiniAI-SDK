package cn.fuzhizhuang.miniai.sdk.executor.parameter.image;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.io.Serial;
import java.io.Serializable;

@Slf4j
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ImageEditRequest extends Image implements Serializable {

    @Serial
    private static final long serialVersionUID = -4269062772386531383L;


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
