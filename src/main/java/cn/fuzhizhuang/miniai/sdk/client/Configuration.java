package cn.fuzhizhuang.miniai.sdk.client;

import cn.fuzhizhuang.miniai.sdk.executor.Executor;
import cn.fuzhizhuang.miniai.sdk.executor.model.chatglm.ChatGLMModelExecutor;
import cn.fuzhizhuang.miniai.sdk.executor.model.chatglm.config.ChatGLMConfig;
import cn.fuzhizhuang.miniai.sdk.executor.parameter.enums.Model;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.sse.EventSource;
import okhttp3.sse.EventSources;

import java.util.HashMap;

/**
 * 配置文件
 *
 * @author zhuang.zhi
 */
@Slf4j
@Data
public class Configuration {

    /**
     * 智谱AI-ChatGLM config
     */
    private ChatGLMConfig chatGLMConfig;

    /**
     * OkHttpClient
     */
    private OkHttpClient okHttpClient;

    private HashMap<String, Executor> executorGroup;

    public EventSource.Factory createRequestFactory() {
        return EventSources.createFactory(okHttpClient);
    }

    // OkHttp 配置信息
    private HttpLoggingInterceptor.Level level = HttpLoggingInterceptor.Level.BODY;
    private long connectTimeout = 4500;
    private long writeTimeout = 4500;
    private long readTimeout = 4500;

    // http keywords
    public static final String DEFAULT_USER_AGENT = "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)";
    public static final String APPLICATION_JSON = "application/json";

    public HashMap<String, Executor> newExecutorGroup() {
        this.executorGroup = new HashMap<>();
        // ChatGLM 类型执行器填充
        Executor chatGLMModelExecutor = new ChatGLMModelExecutor(this);
        executorGroup.put(Model.GLM_4.getCode(), chatGLMModelExecutor);
        executorGroup.put(Model.GLM_4V.getCode(), chatGLMModelExecutor);
        executorGroup.put(Model.GLM_COGVIEW_3.getCode(), chatGLMModelExecutor);
        executorGroup.put(Model.GLM_3_Turbo.getCode(), chatGLMModelExecutor);

        return this.executorGroup;
    }

}
