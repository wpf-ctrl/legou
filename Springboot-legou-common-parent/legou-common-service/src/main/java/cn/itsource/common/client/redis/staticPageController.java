package cn.itsource.common.client.redis;

import cn.itsource.util.AjaxResult;
import cn.itsource.util.VelocityUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


/*
页面静态化
model=数据  templatePath=模板路径 targetPath=目标路径

*/
@RestController
public class staticPageController {
    @PostMapping("/getstaticPage")
    public AjaxResult getstaticPage(@RequestBody Map<String,Object> map){
        Object model=map.get("model");
        String templatePath= (String) map.get("templatePath");
        String targetPath= (String) map.get("targetPath");

        try {
            VelocityUtils.staticByTemplate(model,templatePath,targetPath);
            return AjaxResult.me().setSuccess(true).setMessage("生成成功");
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("生成失败"+e.getMessage());
        }
    }
}
