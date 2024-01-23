package cn.fuzhizhuang.miniai.sdk.executor.parameter.chat;

import cn.fuzhizhuang.miniai.sdk.executor.model.chatglm.valobj.Tool;
import cn.fuzhizhuang.miniai.sdk.executor.parameter.enums.Model;
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
import java.util.Map;

/**
 * 对话请求参数
 *
 * @author zhuang.zhi
 */
@Data
@Slf4j
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompletionRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = -1785929959791476879L;

    /**
     * 默认模型
     */
    @Builder.Default
    private String model = Model.GLM_3_Turbo.getCode();

    /**
     * 问题描述
     */
    private List<Message> messages;

    /**
     * 控制温度【随机性】，较高的值（如0.8）将使输出更加随机，而较低的值（如0.2）将使输出更加集中和确定
     */
    @Builder.Default
    private double temperature = 0.2;

    /**
     * 多样性控制，使用温度采样的替代方法称为核心采样，其中模型考虑具有top_p概率质量的令牌结果，因此，0.1意味若只考虑包含前10%概率质量的代币
     */
    @JsonProperty("top_p")
    @Builder.Default
    private double topP = 1d;

    /**
     * 为每个提示生成的完成次数
     */
    @Builder.Default
    private Integer n = 1;

    /**
     * 是否流式输出
     */
    @Builder.Default
    private boolean stream = false;

    /**
     * 停止输出标识
     */
    private List<String> stop;

    /**
     * 输出字符串限制 0~4096
     */
    @JsonProperty("max_tokens")
    @Builder.Default
    private Integer maxTokens = 2048;

    /**
     * 频率惩罚，降低模型重复同一行的可能性
     */
    @JsonProperty("frequency_penalty")
    @Builder.Default
    private double frequencyPenalty = 0;

    /**
     * 存在惩罚,增强模型谈论新话题的可能性
     */
    @JsonProperty("presence_penalty")
    @Builder.Default
    private double presencePenalty = 0;

    /**
     * 生成多个调用结果，只显示最佳的。这样会更多消耗api token
     */
    @JsonProperty("logit_bias")
    private Map logitBias;

    /**
     * 可供模型调用的工具列表,tools字段会计算 tokens ，同样受到tokens长度的限制
     */
    private List<Tool> tools;
    /**
     * 用于控制模型是如何选择要调用的函数，仅当工具类型为function时补充。默认为auto，当前仅支持auto。
     */
    @JsonProperty("tool_choice")
    private String toolChoice = "auto";
    /**
     * 调用标识，避免重复调用
     */
    private String user;


}
