package cn.fuzhizhuang.miniai.sdk.executor.model.chatglm.valobj;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * ChatGLM 应答参数
 *
 * @author zhuang.zhi
 */
@Data
public class ChatGLMCompletionResponse {

    /**
     * 任务ID
     */
    private String id;

    @JsonProperty("request_id")
    private String requestId;

    /**
     * 请求创建时间，是以秒为单位的 Unix 时间戳。
     */
    private Long created;

    /**
     * 同步调用-模型名称
     */
    private String model;

    /**
     * 当前对话的模型输出内容
     */
    private List<Choice> choices;

    /**
     * 结束时返回本次模型调用的tokens数量统计。
     */
    private Usage usage;

}
