package cn.fuzhizhuang.miniai.sdk.client.impl;

import cn.fuzhizhuang.miniai.sdk.client.AiStreamClient;
import cn.fuzhizhuang.miniai.sdk.client.Configuration;
import cn.fuzhizhuang.miniai.sdk.executor.Executor;
import cn.fuzhizhuang.miniai.sdk.executor.parameter.chat.CompletionRequest;
import cn.fuzhizhuang.miniai.sdk.executor.parameter.picture.PictureRequest;
import okhttp3.OkHttpClient;
import okhttp3.sse.EventSource;
import okhttp3.sse.EventSourceListener;

import java.util.Map;
import java.util.Objects;

/**
 * 默认实现，策略模式实现AI模型调用
 *
 * @author zhuang.zhi
 */
public class DefaultAiStreamClient implements AiStreamClient {

    private final Configuration configuration;

    /**
     * 保存执行组，例如ChatGLM、文心一言等
     */
    private final Map<String, Executor> executorGroup;

    public DefaultAiStreamClient(Configuration configuration, Map<String, Executor> executorGroup) {
        this.configuration = configuration;
        this.executorGroup = executorGroup;
    }

    @Override
    public EventSource streamCompletions(CompletionRequest completionRequest, EventSourceListener eventSourceListener) throws Exception {
        return streamCompletions(null, null, completionRequest, eventSourceListener);
    }

    @Override
    public EventSource streamCompletions(String apiHostByUser, String apiKeyByUser, CompletionRequest completionRequest, EventSourceListener eventSourceListener) throws Exception {
        //选择执行器，model->ChatGLM/ChatGPT/other
        Executor executor = executorGroup.get(completionRequest.getModel());
        if (Objects.isNull(executor)) {
            throw new RuntimeException(completionRequest.getModel().concat(" 模型执行器尚未实现!"));
        }
        //返回执行结果
        return executor.streamCompletions(apiHostByUser, apiKeyByUser, completionRequest, eventSourceListener);
    }

    @Override
    public OkHttpClient getClient() {
        return configuration.getOkHttpClient();
    }

    @Override
    public EventSource streamPictureUnderstand(PictureRequest pictureRequest, EventSourceListener eventSourceListener) throws Exception {
        return streamPictureUnderstand(null, null, pictureRequest, eventSourceListener);
    }

    @Override
    public EventSource streamPictureUnderstand(String apiHostByUser, String apiKeyByUser, PictureRequest pictureRequest, EventSourceListener eventSourceListener) throws Exception {
        //选择执行器
        Executor executor = executorGroup.get(pictureRequest.getModel());
        if (Objects.isNull(executor))
            throw new RuntimeException(pictureRequest.getModel().concat(" 模型执行器尚未实现!"));
        //返回执行结果
        return executor.streamPictureUnderstand(apiHostByUser, apiKeyByUser, pictureRequest, eventSourceListener);
    }
}