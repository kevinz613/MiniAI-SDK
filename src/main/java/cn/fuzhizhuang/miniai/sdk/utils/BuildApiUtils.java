package cn.fuzhizhuang.miniai.sdk.utils;

import cn.fuzhizhuang.miniai.sdk.client.Configuration;
import cn.fuzhizhuang.miniai.sdk.executor.model.chatglm.ChatglmApi;
import cn.fuzhizhuang.miniai.sdk.interceptor.HttpInterceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.util.concurrent.TimeUnit;


public class BuildApiUtils {
    private final Configuration configuration;

    public BuildApiUtils(Configuration configuration) {
        this.configuration = configuration;
    }

    public ChatglmApi getChatglmApi() {
        //配置日志
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(configuration.getLevel());
        //开启http客户端
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
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(configuration.getChatGLMConfig().getApiHost())
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
        return retrofit.create(ChatglmApi.class);
    }
}
