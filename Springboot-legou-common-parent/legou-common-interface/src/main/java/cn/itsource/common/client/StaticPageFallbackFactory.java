package cn.itsource.common.client;

import cn.itsource.common.client.StaticPageClient;
import cn.itsource.util.AjaxResult;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class StaticPageFallbackFactory implements FallbackFactory<StaticPageClient> {

    @Override
    public StaticPageClient create(Throwable throwable) {
       return new StaticPageClient() {
           @Override
           public AjaxResult getstaticPage(Map<String, Object> map) {
               return AjaxResult.me().setSuccess(false).setMessage("熔断无法访问");
           }
       };

    }
}
