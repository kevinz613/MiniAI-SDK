package cn.fuzhizhuang.miniai.sdk.executor.parameter.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Role {
    SYSTEM("system", "系统"),
    USER("user", "用户"),
    ASSISTANT("assistant", "AI助手"),
    ;
    private final String code;
    private final String info;
}
