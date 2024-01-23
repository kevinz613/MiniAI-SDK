package cn.fuzhizhuang.miniai.sdk.executor.parameter.chat;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 对话信息
 *
 * @author zhuang.zhi
 */
@Data
public class ChatChoice implements Serializable {
    @Serial
    private static final long serialVersionUID = 5887384690920106278L;

    private long index;

    /**
     * stream=true，请求参数里返回的属性是delta
     */
    @JsonProperty("delta")
    private Message delta;

    /**
     * stream=false，请求参数里返回的属性是message
     */
    @JsonProperty("message")
    private Message message;

    @JsonProperty("finish_reason")
    private String finishReason;
}
