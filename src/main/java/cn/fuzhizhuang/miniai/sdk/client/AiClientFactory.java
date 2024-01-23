package cn.fuzhizhuang.miniai.sdk.client;

/**
 * 创建AI模型工厂+策略模式选择具体使用哪一个模型
 *
 * @author zhuang.zhi
 */
public interface AiClientFactory {

    /**
     * 使用客户端
     *
     * @return AI模型客户端
     */
    AiClient useClient();

    /**
     * 使用流式客户端
     *
     * @return AI流式客户端
     */
    AiStreamClient useStreamClient();
}
