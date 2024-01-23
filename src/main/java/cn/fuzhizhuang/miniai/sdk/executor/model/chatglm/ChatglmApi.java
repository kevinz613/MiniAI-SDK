package cn.fuzhizhuang.miniai.sdk.executor.model.chatglm;

import cn.fuzhizhuang.miniai.sdk.executor.model.chatglm.valobj.ChatGLMCompletionRequest;
import cn.fuzhizhuang.miniai.sdk.executor.model.chatglm.valobj.ChatGLMCompletionResponse;
import cn.fuzhizhuang.miniai.sdk.executor.model.chatglm.valobj.ChatGLMImageRequest;
import cn.fuzhizhuang.miniai.sdk.executor.model.chatglm.valobj.ChatGLMImageResponse;
import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * ChatGLM API 接口
 *
 * @author zhuang.zhi
 */
public interface ChatglmApi {

    String completions = "api/paas/v4/chat/completions";

    String images = "api/paas/v4/images/generations";

    /**
     * 对话模式，同步响应 - 对流式回答数据同步处理
     *
     * @param completionRequest 对话请求
     * @return 应答结果
     * @throws Exception 异常
     */
    @POST(completions)
    Single<ChatGLMCompletionResponse> syncCompletions(@Body ChatGLMCompletionRequest completionRequest) throws Exception;

    /**
     * 生成图像
     *
     * @param imageRequest 图像请求
     * @return 生成图像响应
     * @throws Exception 异常
     */
    @POST(images)
    Single<ChatGLMImageResponse> generateImages(@Body ChatGLMImageRequest imageRequest) throws Exception;
}
