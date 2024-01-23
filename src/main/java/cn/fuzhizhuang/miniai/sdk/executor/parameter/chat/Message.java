package cn.fuzhizhuang.miniai.sdk.executor.parameter.chat;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 * 描述信息
 *
 * @author zhuang.zhi
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Message implements Serializable {
    @Serial
    private static final long serialVersionUID = -7764368720025797785L;

    /**
     * 角色
     */
    private String role;

    /**
     * 消息内容
     */
    private String content;

}
