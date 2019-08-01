package cn.itsource.common.client;

import cn.itsource.util.AjaxResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@FeignClient(value = "COMMON-SERVICE",fallbackFactory = StaticPageFallbackFactory.class)
public interface StaticPageClient {
    @PostMapping("/getstaticPage")
    public AjaxResult getstaticPage(@RequestBody Map<String,Object> map);
}
