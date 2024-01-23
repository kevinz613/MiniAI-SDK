package cn.fuzhizhuang.miniai.sdk.interceptor;

import cn.fuzhizhuang.miniai.sdk.client.Configuration;
import cn.fuzhizhuang.miniai.sdk.executor.model.chatglm.utils.BearerTokenUtils;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

/**
 * HTTP 拦截器
 * 智谱AI需要先进行用户鉴权才能够调用api接口
 *
 * @author zhuang.zhi
 */
public class HttpInterceptor implements Interceptor {


    private final Configuration configuration;

    public HttpInterceptor(Configuration configuration) {
        this.configuration = configuration;
    }

    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {
        // 1. 获取原始 Request
        Request original = chain.request();

        // 2. 构建请求
        Request request = original.newBuilder()
                .url(original.url())
                .header("Authorization", "Bearer " + BearerTokenUtils.getToken(configuration.getChatGLMConfig().getApiKey(), configuration.getChatGLMConfig().getApiSecret()))
                .header("User-Agent", Configuration.DEFAULT_USER_AGENT)
                .header("Accept","*/*")
                .header("Content-Type", "application/json")
                .header("Connection", "keep-alive")
                .method(original.method(), original.body())
                .build();

        // 3. 返回执行结果
        return chain.proceed(request);
    }
}
