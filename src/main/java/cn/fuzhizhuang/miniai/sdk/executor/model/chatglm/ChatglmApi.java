package cn.fuzhizhuang.miniai.sdk.executor.model.chatglm;

import cn.fuzhizhuang.miniai.sdk.executor.model.chatglm.valobj.ChatGLMCompletionRequest;
import cn.fuzhizhuang.miniai.sdk.executor.model.chatglm.valobj.ChatGLMCompletionResponse;
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

    /**
     * 对话模式，同步响应 - 对流式回答数据同步处理
     *
     * @param completionRequest 对话请求
     * @return 应答结果
     * @throws Exception 异常
     */
    @POST(completions)
    Single<ChatGLMCompletionResponse> syncCompletions(@Body ChatGLMCompletionRequest completionRequest) throws Exception;


}
