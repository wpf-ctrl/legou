package cn.itsource.common.client;

import cn.itsource.common.client.RedisClient;
import cn.itsource.util.AjaxResult;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class RedisFallbackFactory implements FallbackFactory<RedisClient> {
    @Override
    public RedisClient create(Throwable throwable) {
        return new RedisClient() {
            @Override
            public AjaxResult set(String key, String value) {
                 return AjaxResult.me().setSuccess(false).setMessage("熔断无法访问");
            }

            @Override
            public AjaxResult get(String key) {
                return AjaxResult.me().setSuccess(false).setMessage("熔断无法访问");
            }
        };
    }
}
