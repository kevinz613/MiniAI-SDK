package cn.fuzhizhuang.miniai.sdk.executor.parameter.picture;

import cn.fuzhizhuang.miniai.sdk.executor.parameter.enums.Picture;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Text implements Serializable {
    @Serial
    private static final long serialVersionUID = 1760482218194337300L;

    /**
     * 角色
     */
    private String role;

    /**
     * contentType is image,picture for base64
     * contentType is text,content for prompt
     */
    private String content;

    /**
     * 内容类型
     * @see Picture
     */
    private String contentType;
}
