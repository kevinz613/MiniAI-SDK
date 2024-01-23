package cn.fuzhizhuang.miniai.sdk.executor.parameter.picture;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * 图片理解，图片和对图片的描述
 *
 * @author zhuang.zhi
 */
@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PictureRequest implements Serializable {
    @Serial
    private static final long serialVersionUID = 8846778792112148460L;

    /**
     * 模型
     */
    private String model;

    /**
     * 消息
     */
    private List<Text> messages;

    /**
     * 温度
     */
    @Builder.Default
    private double temperature = 0.5;

    /**
     * 是否为流式输出
     */
    @Builder.Default
    private boolean stream = false;

    /**
     * 停止输出标识
     */
    private List<String> stop;

    /**
     * 输出字符串限制0~4096
     */
    @Builder.Default
    @JsonProperty("max_tokens")
    private Integer maxTokens = 2048;
}
