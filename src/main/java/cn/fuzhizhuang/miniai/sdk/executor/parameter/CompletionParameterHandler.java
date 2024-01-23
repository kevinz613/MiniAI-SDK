package cn.fuzhizhuang.miniai.sdk.executor.parameter;


import cn.fuzhizhuang.miniai.sdk.executor.parameter.chat.CompletionRequest;

/**
 * 参数处理器
 *
 * @author zhuang.zhi
 */
public interface CompletionParameterHandler<T> {

    /**
     * 获取对话参数对象
     *
     * @param completionRequest 对话请求
     * @return t
     */
    T convertCompletionParameter(CompletionRequest completionRequest);
}
