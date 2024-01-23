package cn.fuzhizhuang.miniai.sdk.executor.model.chatglm.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

/**
 * 智谱Ai 配置信息
 *
 * @author zhuang.zhi
 */
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
public class ChatGLMConfig {

    // 智普Ai ChatGlM 请求地址
    @Getter
    @Setter
    private String apiHost = "https://open.bigmodel.cn/";

    /**
     * 对话
     */
    @Getter
    private String v4_completions = "api/paas/v4/chat/completions";

    /**
     * 图像生成
     */
    @Getter
    private String v4_images = "api/paas/v4/images/generations";

    // 智普Ai https://open.bigmodel.cn/usercenter/apikeys - apiSecretKey = {apiKey}.{apiSecret}
    private String apiSecretKey;

    public void setApiSecretKey(String apiSecretKey) {
        this.apiSecretKey = apiSecretKey;
        if (StringUtils.isBlank(apiSecretKey)) {
            log.warn("not apiSecretKey set");
        } else {
            String[] arrStr = apiSecretKey.split("\\.");
            if (arrStr.length != 2) {
                throw new RuntimeException("invalid apiSecretKey");
            }
            this.apiKey = arrStr[0];
            this.apiSecret = arrStr[1];
        }
    }

    @Getter
    private String apiKey;
    @Getter
    private String apiSecret;

}
