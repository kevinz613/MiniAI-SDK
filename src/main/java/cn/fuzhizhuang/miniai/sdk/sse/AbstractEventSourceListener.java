package cn.fuzhizhuang.miniai.sdk.sse;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Response;
import okhttp3.sse.EventSource;
import okhttp3.sse.EventSourceListener;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CountDownLatch;

/**
 * 定义抽象事件监听器
 *
 * @author zhuang.zhi
 */
@Slf4j
public class AbstractEventSourceListener extends EventSourceListener {

    @Override
    public void onClosed(@NotNull EventSource eventSource) {
        log.info("AbstractEventSourceListener onClosed");
    }

    @SneakyThrows
    @Override
    public void onEvent(@NotNull EventSource eventSource, @Nullable String id, @Nullable String type, @NotNull String data) {
        log.info("AbstractEventSourceListener onEvent");
    }

    @Override
    public void onFailure(@NotNull EventSource eventSource, @Nullable Throwable t, @Nullable Response response) {
        log.info("AbstractEventSourceListener onFailure");
        eventSource.cancel();
    }

    @Override
    public void onOpen(@NotNull EventSource eventSource, @NotNull Response response) {
        log.info("AbstractEventSourceListener onOpen");
    }
}
