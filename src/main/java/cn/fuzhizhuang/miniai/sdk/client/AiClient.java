package cn.fuzhizhuang.miniai.sdk.client;

import cn.fuzhizhuang.miniai.sdk.executor.parameter.chat.CompletionRequest;
import cn.fuzhizhuang.miniai.sdk.executor.parameter.chat.CompletionResponse;
import cn.fuzhizhuang.miniai.sdk.executor.parameter.image.ImageRequest;
import cn.fuzhizhuang.miniai.sdk.executor.parameter.image.ImageResponse;

/**
 * AI模型客户端
 *
 * @author zhuang.zhi
 */
public interface AiClient {


    /**
     * 对话模式，同步响应 - 对流式回答数据同步处理
     *
     * @param completionRequest 对话请求
     * @return 应答结果
     * @throws Exception 异常
     */
    CompletionResponse syncCompletions(CompletionRequest completionRequest) throws Exception;

    /**
     * 生成图像
     *
     * @param imageRequest 图像请求
     * @return 图像响应
     * @throws Exception 异常
     */
    ImageResponse generateImages(ImageRequest imageRequest) throws Exception;


}
