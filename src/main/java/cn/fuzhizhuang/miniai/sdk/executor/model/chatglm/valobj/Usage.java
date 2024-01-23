package cn.fuzhizhuang.miniai.sdk.executor.model.chatglm.valobj;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Usage {
    /**
     * 模型输入的tokens数量
     */
    @JsonProperty("completion_tokens")
    private Integer completionTokens;
    /**
     * 用户输入的tokens数量
     */
    @JsonProperty("prompt_tokens")
    private Integer promptTokens;
    /**
     * 总tokens数量
     */
    @JsonProperty("total_tokens")
    private Integer totalTokens;
}
