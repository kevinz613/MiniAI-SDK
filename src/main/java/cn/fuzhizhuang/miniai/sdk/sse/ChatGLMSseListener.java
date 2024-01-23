package cn.fuzhizhuang.miniai.sdk.sse;

import cn.fuzhizhuang.miniai.sdk.executor.model.chatglm.valobj.ChatGLMCompletionResponse;
import com.alibaba.fastjson2.JSON;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.sse.EventSource;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;

import java.util.Objects;

/**
 * 智谱AI-ChatGLM事件监听
 *
 * @author zhuang.zhi
 */
@Slf4j
public class ChatGLMSseListener extends AbstractEventSourceListener {

    private final ResponseBodyEmitter sseEmitter;

    public ChatGLMSseListener(ResponseBodyEmitter sseEmitter) {
        this.sseEmitter = sseEmitter;
    }

    @SneakyThrows
    @Override
    public void onEvent(@NotNull EventSource eventSource, @Nullable String id, @Nullable String type, @NotNull String data) {
        if ("[DONE]".equals(data)) {
            sseEmitter.complete();
            return;
        }
        ChatGLMCompletionResponse response = JSON.parseObject(data, ChatGLMCompletionResponse.class);
        log.info("测试结果:{}", JSON.toJSONString(response));
        //推送返回信息
        sseEmitter.send(response.getChoices().get(0).getDelta());
        if (response != null && response.getUsage() != null) {
            log.info("usage: {}", response.getUsage());
        }
    }

    @Override
    public void onClosed(@NotNull EventSource eventSource) {
        log.info("对话完成");
        sseEmitter.complete();
    }

    @SneakyThrows
    @Override
    public void onFailure(EventSource eventSource, @Nullable Throwable t, @Nullable Response response) {
        if (Objects.isNull(response)) {
            log.error("智谱AI-ChatGLM SSE连接异常:{}", t);
            eventSource.cancel();
            return;
        }
        ResponseBody body = response.body();
        if (Objects.nonNull(body)) {
            log.error("智谱AI-ChatGLM SSE连接异常 body: {},异常: {}", body.string(), t);
        } else {
            log.error("智谱AI-ChatGLM SSE连接异常 response: {},异常: {}", response, t);
        }
        eventSource.cancel();
    }

    /**
     * 返回SseEmitter
     *
     * @return 响应
     */
    public ResponseBodyEmitter responseBodyEmitter(){
        return this.sseEmitter;
    }

}
