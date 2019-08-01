package cn.itsource.common.client.redis;

import cn.itsource.common.client.RedisClient;
import cn.itsource.util.AjaxResult;
import cn.itsource.util.RedisUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedisController implements RedisClient {

    //存
    @PostMapping("/redis")
    public AjaxResult set(String key,String value){
        try {
            RedisUtils.INSTANCE.set(key, value);
            return AjaxResult.me().setSuccess(true).setMessage("保存成功");
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("保存失败"+e.getMessage());
        }
    }

    //取
    @GetMapping("/redis")
    public AjaxResult get(String key){
        try {
            String s = RedisUtils.INSTANCE.get(key);
            return AjaxResult.me().setSuccess(true).setMessage("成功").setRestObj(s);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("保存失败"+e.getMessage());
        }
    }
}
