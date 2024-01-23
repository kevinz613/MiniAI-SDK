package cn.fuzhizhuang.miniai.sdk.executor.model.chatglm.valobj;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

/**
 * ChatGLM 图片生成请求参数
 *
 * @author zhuang.zhi
 */
@Slf4j
@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
public class ChatGLMImageRequest {

    /**
     * 模型名称
     */
    @Builder.Default
    private String model = Model.GLM_COGVIEW_3.getCode();

    /**
     * 所需图像的文本描述
     */
    @NonNull
    private String prompt;
}
