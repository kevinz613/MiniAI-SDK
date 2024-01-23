package cn.fuzhizhuang.miniai.sdk.executor.parameter.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Model {

    /**
     * ChatGLM
     */
    GLM_3_Turbo("glm-3-turbo", "根据输入的自然语言指令完成多种语言类任务"),
    GLM_4("glm-4", "根据输入的自然语言指令完成多种语言类任务"),
    GLM_4V("glm-4v", "根据输入的自然语言指令和图像信息完成任务"),
    GLM_COGVIEW_3("cogview-3", "根据用户的文字描述生成图像"),
    ;

    private final String code;

    private final String info;
}
