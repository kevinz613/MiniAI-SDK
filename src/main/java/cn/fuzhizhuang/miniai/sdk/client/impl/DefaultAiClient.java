package cn.fuzhizhuang.miniai.sdk.client.impl;

import cn.fuzhizhuang.miniai.sdk.client.AiClient;
import cn.fuzhizhuang.miniai.sdk.client.Configuration;
import cn.fuzhizhuang.miniai.sdk.executor.Executor;
import cn.fuzhizhuang.miniai.sdk.executor.parameter.chat.CompletionRequest;
import cn.fuzhizhuang.miniai.sdk.executor.parameter.chat.CompletionResponse;
import cn.fuzhizhuang.miniai.sdk.executor.parameter.image.ImageRequest;
import cn.fuzhizhuang.miniai.sdk.executor.parameter.image.ImageResponse;

import java.util.Map;
import java.util.Objects;

/**
 * 默认实现，策略模式实现AI模型调用
 *
 * @author zhuang.zhi
 */
public class DefaultAiClient implements AiClient {

    private final Configuration configuration;

    /**
     * 保存执行组，例如ChatGLM、文心一言等
     */
    private final Map<String, Executor> executorGroup;

    public DefaultAiClient(Configuration configuration, Map<String, Executor> executorGroup) {
        this.configuration = configuration;
        this.executorGroup = executorGroup;
    }

    @Override
    public CompletionResponse syncCompletions(CompletionRequest completionRequest) throws Exception {
        //选择执行器，model->ChatGLM/ChatGPT/other
        Executor executor = executorGroup.get(completionRequest.getModel());
        if (Objects.isNull(executor)) {
            throw new RuntimeException(completionRequest.getModel().concat(" 模型执行器尚未实现!"));
        }
        //返回执行结果
        return executor.syncCompletions(completionRequest);
    }


    @Override
    public ImageResponse generateImages(ImageRequest imageRequest) throws Exception {
        Executor executor = executorGroup.get(imageRequest.getModel());
        if (Objects.isNull(executor)) {
            throw new RuntimeException(imageRequest.getModel().concat(" 模型执行器尚未实现!"));
        }
        //返回执行结果
        return executor.generateImages(imageRequest);
    }

}