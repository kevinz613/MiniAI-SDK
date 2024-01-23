package cn.fuzhizhuang.miniai.sdk.executor.parameter.image;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 图像信息
 *
 * @author zhuang.zhi
 */
@Data
public class Image implements Serializable {
    @Serial
    private static final long serialVersionUID = -5330761294757275209L;

    private String url;

    @JsonProperty("b64_json")
    private String b64Json;

    @JsonProperty("revised_prompt")
    private String revisedPrompt;
}
