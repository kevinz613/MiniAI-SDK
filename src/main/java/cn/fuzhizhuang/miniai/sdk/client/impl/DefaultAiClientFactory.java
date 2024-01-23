package cn.fuzhizhuang.miniai.sdk.client.impl;

import cn.fuzhizhuang.miniai.sdk.client.AiClient;
import cn.fuzhizhuang.miniai.sdk.client.AiClientFactory;
import cn.fuzhizhuang.miniai.sdk.client.AiStreamClient;
import cn.fuzhizhuang.miniai.sdk.client.Configuration;
import cn.fuzhizhuang.miniai.sdk.executor.Executor;
import cn.fuzhizhuang.miniai.sdk.executor.model.chatglm.ChatglmApi;
import cn.fuzhizhuang.miniai.sdk.interceptor.HttpInterceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * 默认 AI客户端工厂实现
 *
 * @author zhuang.zhi
 */
public class DefaultAiClientFactory implements AiClientFactory {

    private final Configuration configuration;

    public DefaultAiClientFactory(Configuration configuration) {
        this.configuration = configuration;
    }


    @Override
    public AiClient useClient() {
        //配置日志
        HttpLoggingInterceptor httpLoggingInterceptor = configureHttpLoggingInterceptor();
        //开启http客户端
        openHttpClient(httpLoggingInterceptor);
        //创建执行器 模型->executor执行器
        HashMap<String, Executor> executorGroup = configuration.newExecutorGroup();
        //创建模型服务
        return new DefaultAiClient(configuration, executorGroup);
    }

    @Override
    public AiStreamClient useStreamClient() {
        //配置日志
        HttpLoggingInterceptor httpLoggingInterceptor = configureHttpLoggingInterceptor();
        //开启http客户端
        openHttpClient(httpLoggingInterceptor);
        //创建执行器 模型->executor执行器
        HashMap<String, Executor> executorGroup = configuration.newExecutorGroup();
        //创建模型服务
        return new DefaultAiStreamClient(configuration, executorGroup);
    }

    /**
     * 开启http客户端
     *
     * @param httpLoggingInterceptor HTTP日志记录拦截器
     */
    private void openHttpClient(HttpLoggingInterceptor httpLoggingInterceptor) {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                //日志
                .addInterceptor(httpLoggingInterceptor)
                //http拦截器
                .addInterceptor(new HttpInterceptor(configuration))
                .connectTimeout(configuration.getConnectTimeout(), TimeUnit.SECONDS)
                .writeTimeout(configuration.getWriteTimeout(), TimeUnit.SECONDS)
                .readTimeout(configuration.getReadTimeout(), TimeUnit.SECONDS)
                .build();
        configuration.setOkHttpClient(okHttpClient);
    }

    /**
     * 配置日志
     *
     * @return 配置日志结果
     */
    @NotNull
    private HttpLoggingInterceptor configureHttpLoggingInterceptor() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(configuration.getLevel());
        return httpLoggingInterceptor;
    }

}
