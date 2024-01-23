package cn.fuzhizhuang.miniai.sdk.executor.parameter;


import cn.fuzhizhuang.miniai.sdk.executor.parameter.image.ImageRequest;

/**
 * 参数处理器
 *
 * @author zhuang.zhi
 */
public interface ImageParameterHandler<T> {

    /**
     * 获取图像参数对象
     *
     * @param imageRequest 图像请求
     * @return t
     */
    T convertImageParameter(ImageRequest imageRequest);
}
