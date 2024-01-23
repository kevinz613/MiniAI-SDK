package cn.fuzhizhuang.miniai.sdk.client.impl;

import cn.fuzhizhuang.miniai.sdk.client.AiClient;
import cn.fuzhizhuang.miniai.sdk.client.Configuration;
import cn.fuzhizhuang.miniai.sdk.executor.Executor;
import cn.fuzhizhuang.miniai.sdk.executor.model.chatglm.valobj.Role;
import cn.fuzhizhuang.miniai.sdk.executor.parameter.chat.ChatChoice;
import cn.fuzhizhuang.miniai.sdk.executor.parameter.chat.CompletionRequest;
import cn.fuzhizhuang.miniai.sdk.executor.parameter.chat.CompletionResponse;
import cn.fuzhizhuang.miniai.sdk.executor.parameter.chat.Message;
import cn.fuzhizhuang.miniai.sdk.executor.parameter.image.ImageRequest;
import cn.fuzhizhuang.miniai.sdk.executor.parameter.image.ImageResponse;
import cn.fuzhizhuang.miniai.sdk.executor.parameter.picture.PictureRequest;
import com.alibaba.fastjson2.JSON;
import io.reactivex.Single;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.sse.EventSource;
import okhttp3.sse.EventSourceListener;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

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
        return generateImages(null, null, imageRequest);
    }

    @Override
    public ImageResponse generateImages(String apiHostByUser, String apiKeyByUser, ImageRequest imageRequest) throws Exception {
        return executorGroup.get(imageRequest.getModel()).generateImages(apiHostByUser, apiKeyByUser, imageRequest);
    }

}