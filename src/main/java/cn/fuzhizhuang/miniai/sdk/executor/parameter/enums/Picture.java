package cn.fuzhizhuang.miniai.sdk.executor.parameter.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class Picture {

    @Getter
    @AllArgsConstructor
    public enum ContentType {

        IMAGE("image"),
        TEXT("text"),
        ;

        private final String value;
    }
}
