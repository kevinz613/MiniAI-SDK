package cn.fuzhizhuang.miniai.sdk.executor.model.chatglm;


import cn.fuzhizhuang.miniai.sdk.client.Configuration;
import cn.fuzhizhuang.miniai.sdk.executor.Executor;
import cn.fuzhizhuang.miniai.sdk.executor.model.chatglm.config.ChatGLMConfig;
import cn.fuzhizhuang.miniai.sdk.executor.model.chatglm.utils.BearerTokenUtils;
import cn.fuzhizhuang.miniai.sdk.executor.model.chatglm.valobj.ChatGLMCompletionRequest;
import cn.fuzhizhuang.miniai.sdk.executor.model.chatglm.valobj.ChatGLMCompletionResponse;
import cn.fuzhizhuang.miniai.sdk.executor.model.chatglm.valobj.ChatGLMImageRequest;
import cn.fuzhizhuang.miniai.sdk.executor.model.chatglm.valobj.EventType;
import cn.fuzhizhuang.miniai.sdk.executor.parameter.CompletionParameterHandler;
import cn.fuzhizhuang.miniai.sdk.executor.parameter.ImageParameterHandler;
import cn.fuzhizhuang.miniai.sdk.executor.parameter.chat.CompletionRequest;
import cn.fuzhizhuang.miniai.sdk.executor.parameter.chat.CompletionResponse;
import cn.fuzhizhuang.miniai.sdk.executor.parameter.image.ImageRequest;
import cn.fuzhizhuang.miniai.sdk.executor.parameter.image.ImageResponse;
import cn.fuzhizhuang.miniai.sdk.executor.parameter.picture.PictureRequest;
import cn.fuzhizhuang.miniai.sdk.executor.result.ResultHandler;
import cn.fuzhizhuang.miniai.sdk.utils.BuildApiUtils;
import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.sse.EventSource;
import okhttp3.sse.EventSourceListener;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

/**
 * ChatGLM模型执行器
 * <p>文档：https://open.miniai.sdk.cn/dev/api#chatglm_turbo</p>
 * <p>ApiKey：https://open.miniai.sdk.cn/usercenter/apikeys</p>
 *
 * @author zhuang.zhi
 */
@Slf4j
public class ChatGLMModelExecutor implements Executor, CompletionParameterHandler<ChatGLMCompletionRequest>, ImageParameterHandler<ChatGLMImageRequest>, ResultHandler {

    /**
     * 配置信息
     */
    private final ChatGLMConfig chatGLMConfig;

    private final Configuration configuration;

    /**
     * 工厂事件
     */
    private final EventSource.Factory factory;


    public ChatGLMModelExecutor(Configuration configuration) {
        this.chatGLMConfig = configuration.getChatGLMConfig();
        this.factory = configuration.createRequestFactory();
        this.configuration = configuration;
    }

    @Override
    public EventSource streamCompletions(CompletionRequest completionRequest, EventSourceListener eventSourceListener) throws Exception {
        //转换参数信息
        ChatGLMCompletionRequest chatGLMCompletionRequest = convertCompletionParameter(completionRequest);
        //构造请求
        Request request = new Request.Builder()
                .header("Authorization", "Bearer " + BearerTokenUtils.getToken(chatGLMConfig.getApiKey(), chatGLMConfig.getApiSecret()))
                .header("Content-Type", "application/json")
                .url(chatGLMConfig.getApiHost().concat(chatGLMConfig.getV4_completions()))
                //okhttp会默认增加"charset=utf-8"字符集，也就是Content-Type变成了"application/json;charset=utf-8"
                .post(RequestBody.create(JSON.toJSONBytes(chatGLMCompletionRequest), MediaType.parse(Configuration.APPLICATION_JSON)))
                .build();
        //返回事件结果
        return factory.newEventSource(request, eventSourceListener);
    }

    @Override
    public EventSource streamCompletions(String apiHostByUser, String apiKeyByUser, CompletionRequest completionRequest, EventSourceListener eventSourceListener) throws Exception {
        //转换参数信息
        ChatGLMCompletionRequest chatGLMCompletionRequest = convertCompletionParameter(completionRequest);
        //自定义配置
        ChatGLMConfig config = new ChatGLMConfig();
        config.setApiHost(apiHostByUser);
        if (Objects.nonNull(apiKeyByUser)) {
            config.setApiSecretKey(apiKeyByUser);
        }
        String apiHost = Objects.isNull(config.getApiHost()) ? chatGLMConfig.getApiHost() : config.getApiHost();
        String apiKey = Objects.isNull(config.getApiKey()) ? chatGLMConfig.getApiHost() : config.getApiHost();
        String apiSecret = Objects.isNull(config.getApiSecret()) ? chatGLMConfig.getApiSecret() : config.getApiSecret();
        //构建请求
        Request request = new Request.Builder()
                .header("Authorization", "Bearer " + BearerTokenUtils.getToken(apiKey, apiSecret))
                .header("Content-Type", "application/json")
                .url(apiHost.concat(chatGLMConfig.getV4_completions()))
                //okhttp会默认增加"charset=utf-8"字符集，也就是Content-Type变成了"application/json;charset=utf-8"
                .post(RequestBody.create(JSON.toJSONBytes(chatGLMCompletionRequest), MediaType.parse(Configuration.APPLICATION_JSON)))
                .build();
        //返回事件结果
        return factory.newEventSource(request, eventSourceListener(eventSourceListener));
    }

    @Override
    public CompletionResponse syncCompletions(CompletionRequest completionRequest) throws Exception {
        ChatGLMCompletionRequest glmCompletionRequest = convertCompletionParameter(completionRequest);
        ChatglmApi chatglmApi = new BuildApiUtils(configuration).getChatglmApi();
        ChatGLMCompletionResponse response = chatglmApi.syncCompletions(glmCompletionRequest).blockingGet();
        CompletionResponse completionResponse = new CompletionResponse();
        BeanUtil.copyProperties(response, completionResponse);
        return completionResponse;
    }


    @Override
    public ImageResponse generateImages(ImageRequest imageRequest) throws Exception {
        return null;
    }

    @Override
    public ImageResponse generateImages(String apiHostByUser, String apiKeyByUser, ImageRequest imageRequest) throws Exception {
        return null;
    }

    @Override
    public EventSource streamPictureUnderstand(PictureRequest pictureRequest, EventSourceListener eventSourceListener) throws Exception {
        return null;
    }

    @Override
    public EventSource streamPictureUnderstand(String apiHostByUser, String apiKeyByUser, PictureRequest pictureRequest, EventSourceListener eventSourceListener) throws Exception {
        return null;
    }

    @Override
    public EventSourceListener eventSourceListener(EventSourceListener eventSourceListener) {
        return new EventSourceListener() {
            @Override
            public void onEvent(EventSource eventSource, @Nullable String id, @Nullable String type, String data) {
                if ("[DONE]".equals(data)) {
                    return;
                }
                ChatGLMCompletionResponse response = com.alibaba.fastjson.JSON.parseObject(data, ChatGLMCompletionResponse.class);
                if (response.getChoices() != null && 1 == response.getChoices().size() && "stop".equals(response.getChoices().get(0).getFinishReason())) {
                    eventSourceListener.onEvent(eventSource, id, EventType.finish.getCode(), data);
                    return;
                }
                eventSourceListener.onEvent(eventSource, id, EventType.add.getCode(), data);
            }

            @Override
            public void onClosed(EventSource eventSource) {
                eventSourceListener.onClosed(eventSource);
            }

            @Override
            public void onFailure(EventSource eventSource, @Nullable Throwable t, @Nullable Response response) {
                eventSourceListener.onFailure(eventSource, t, response);
            }
        };
    }

    @Override
    public ChatGLMCompletionRequest convertCompletionParameter(CompletionRequest completionRequest) {
        return ChatGLMCompletionRequest.builder()
                .model(completionRequest.getModel())
                .messages(completionRequest.getMessages())
                .stream(completionRequest.isStream())
                .maxTokens(completionRequest.getMaxTokens())
                .tools(completionRequest.getTools())
                .toolChoice(completionRequest.getToolChoice())
                .build();
    }

    @Override
    public ChatGLMImageRequest convertImageParameter(ImageRequest imageRequest) {
        return ChatGLMImageRequest.builder()
                .model(imageRequest.getModel())
                .prompt(imageRequest.getPrompt())
                .build();
    }

}


