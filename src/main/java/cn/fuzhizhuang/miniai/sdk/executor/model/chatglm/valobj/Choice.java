package cn.fuzhizhuang.miniai.sdk.executor.model.chatglm.valobj;

import cn.fuzhizhuang.miniai.sdk.executor.parameter.chat.Message;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Choice {
    /**
     * 流式响应-结果下标
     */
    private Integer index;

    /**
     * 模型推理终止的原因。
     * stop代表推理自然结束或触发停止词。
     * tool_calls 代表模型命中函数。
     * length 代表到达 tokens 长度上限。
     */
    @JsonProperty("finish_reason")
    private String finishReason;

    /**
     * 同步响应-模型返回的文本信息
     */
    private Message message;

    /**
     * 流式响应-模型增量返回的文本信息
     */
    private Message delta;
}
