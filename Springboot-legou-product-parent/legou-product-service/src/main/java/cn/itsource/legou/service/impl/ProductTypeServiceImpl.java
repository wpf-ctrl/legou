package cn.itsource.legou.service.impl;

import cn.itsource.common.client.RedisClient;
import cn.itsource.common.client.StaticPageClient;
import cn.itsource.legou.domain.ProductType;
import cn.itsource.legou.mapper.ProductTypeMapper;
import cn.itsource.legou.service.IProductTypeService;
import cn.itsource.util.AjaxResult;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 商品目录 服务实现类
 * </p>
 *
 * @author solargen
 * @since 2019-07-31
 */
@Service
public class ProductTypeServiceImpl extends ServiceImpl<ProductTypeMapper, ProductType> implements IProductTypeService {
    //由于依赖的openfeign,会创建接口的动态代理对象交给spring管理
    @Autowired
    private RedisClient redisClient;
    //生成静态页面
    @Autowired
    private StaticPageClient staticPageClient;

    @Override
    public List<ProductType> loadTypeTree() {
        //从redis中获取数据
        AjaxResult result = redisClient.get("productType");
        String productTypestr = (String) result.getRestObj();
        List<ProductType> list = JSON.parseArray(productTypestr, ProductType.class);
        //判断是有值
        if(list==null||list.size()<=0){
            //没有查询数据库 ，并将数据缓存到redis
            list=loop();
            redisClient.set("productType", JSON.toJSONString(list));
        }
//        return recursive(0L);
        return list;
    }

    @Override
    public void getHomePage() {

        /*
        * staticRoot=D:\java\idel project1\xiangmu\Springboot-parent\Springboot-legou-product-parent\legou-product-service\src\main\resources
        *
        * */
        //第一步先生成product.type.vm.html
        Map<String, Object> map = new HashMap<>();
        List<ProductType> productTypes = loadTypeTree();
        String templatePath="D:\\java\\idel project1\\xiangmu\\Springboot-parent\\Springboot-legou-product-parent\\legou-product-service\\src\\main\\resources\\template\\product.type.vm";
        String targetPath="D:\\java\\idel project1\\xiangmu\\Springboot-parent\\Springboot-legou-product-parent\\legou-product-service\\src\\main\\resources";
        map.put("model", productTypes);
        map.put("templatePath", templatePath);
        map.put("targetPath", targetPath);


        staticPageClient.getstaticPage(map);
    }

    private List<ProductType> recursive(Long id){
        //查询一级菜单
        List<ProductType> parents = baseMapper.selectList(new QueryWrapper<ProductType>().eq("pid", id));
        for (ProductType parent : parents) {
            List<ProductType> children=recursive(parent.getId());
            if(!children.isEmpty()){
                parent.setChildren(children);
            }
        }
        return parents;
    }

    private List<ProductType> loop(){
        //获取所有类型
        List<ProductType> productTypes = baseMapper.selectList(null);
        //装父类 及其下面的子子孙孙
        List<ProductType> result = new ArrayList<>();
        //定义一个map   来存  id 对应的 对象    不用 10*10 获得 子子孙孙  用这种方式 10+10就ok
        Map<Long, ProductType> map = new HashMap<>();
        //装id 对应的镀锡
        for (ProductType productType : productTypes) {
            map.put(productType.getId(), productType);
        }
        //循环取到子子孙孙
        for (ProductType productType : productTypes) {
            if (productType.getPid() == 0 ){
                result.add(productType);
            }else {
                //根据pid 获得父亲
                ProductType parent = map.get(productType.getPid());
                parent.getChildren().add(productType);
            }
        }
        return result;
    }

}
