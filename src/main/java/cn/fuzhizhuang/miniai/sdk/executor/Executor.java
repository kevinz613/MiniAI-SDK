package cn.fuzhizhuang.miniai.sdk.executor;

import cn.fuzhizhuang.miniai.sdk.executor.parameter.chat.CompletionRequest;
import cn.fuzhizhuang.miniai.sdk.executor.parameter.chat.CompletionResponse;
import cn.fuzhizhuang.miniai.sdk.executor.parameter.image.ImageRequest;
import cn.fuzhizhuang.miniai.sdk.executor.parameter.image.ImageResponse;
import cn.fuzhizhuang.miniai.sdk.executor.parameter.picture.PictureRequest;
import okhttp3.sse.EventSource;
import okhttp3.sse.EventSourceListener;

/**
 * 统一模型执行器入口
 *
 * @author zhuang.zhi
 */
public interface Executor {

    /**
     * 对话模式，流式反馈
     *
     * @param completionRequest   对话请求
     * @param eventSourceListener 实现监听，通过监听onEvent方法接收数据
     * @return 应答结果
     * @throws Exception 异常
     */
    EventSource streamCompletions(CompletionRequest completionRequest, EventSourceListener eventSourceListener) throws Exception;


    /**
     * 对话模式，流式反馈 & 用户自定义apiHost、apiKey
     *
     * @param apiHostByUser       apiHost
     * @param apiKeyByUser        apiKey
     * @param completionRequest   对话请求
     * @param eventSourceListener 实现监听，通过监听onEvent方法接收数据
     * @return 应答结果
     * @throws Exception 异常
     */
    EventSource streamCompletions(String apiHostByUser, String apiKeyByUser, CompletionRequest completionRequest, EventSourceListener eventSourceListener) throws Exception;


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


    /**
     * 图片理解
     *
     * @param pictureRequest      图片请求
     * @param eventSourceListener 实现监听
     * @return 应答结果
     * @throws Exception 异常
     */
    EventSource streamPictureUnderstand(PictureRequest pictureRequest, EventSourceListener eventSourceListener) throws Exception;

    /**
     * 图片理解
     *
     * @param apiHostByUser       apiHost
     * @param apiKeyByUser        apiKey
     * @param pictureRequest      图片请求
     * @param eventSourceListener 实现监听
     * @return 应答结果
     * @throws Exception 异常
     */
    EventSource streamPictureUnderstand(String apiHostByUser, String apiKeyByUser, PictureRequest pictureRequest, EventSourceListener eventSourceListener) throws Exception;

}
