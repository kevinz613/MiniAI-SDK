package cn.fuzhizhuang.miniai.sdk.executor.result;

import okhttp3.sse.EventSourceListener;

/**
 * 出参处理器
 *
 * @author zhuang.zhi
 */
public interface ResultHandler {

    EventSourceListener eventSourceListener(EventSourceListener eventSourceListener);
}
