package cn.fuzhizhuang.miniai.sdk.executor.parameter.chat;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * 对话请求结果信息
 *
 * @author zhuang.zhi
 */
@Data
public class CompletionResponse implements Serializable {
    @Serial
    private static final long serialVersionUID = -156083033144808504L;

    /**
     * 编号
     */
    private String id;

    /**
     * 模型
     */
    private String model;

    /**
     * 请求 ID
     */
    @JsonProperty("request_id")
    private String requestId;

    /**
     * 对话
     */
    private List<ChatChoice> choices;

    /**
     * 创建
     */
    private long created;

    /**
     * 耗材用量
     */
    private Usage usage;

}
