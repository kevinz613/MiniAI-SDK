package cn.fuzhizhuang.miniai.sdk.executor.parameter.chat;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 * 使用量
 *
 * @author zhuang.zhi
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Usage implements Serializable {
    @Serial
    private static final long serialVersionUID = -1369385225152354877L;

    /**
     * 提问token
     */
    @JsonProperty("prompt_tokens")
    private Integer promptTokens;

    /**
     * 回答token
     */
    @JsonProperty("completion_tokens")
    private Integer completionTokens;

    /**
     * 总量token
     */
    @JsonProperty("total_tokens")
    private Integer totalTokens;
}
