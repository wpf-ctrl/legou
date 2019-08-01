package cn.itsource.common.client;

import cn.itsource.util.AjaxResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "COMMON-SERVICE",fallbackFactory = RedisFallbackFactory.class)
public interface RedisClient {
    @PostMapping("/redis")
     AjaxResult set(@RequestParam("key") String key, @RequestParam("value")String value);

    @GetMapping("/redis")
     AjaxResult get(final String key);

}
