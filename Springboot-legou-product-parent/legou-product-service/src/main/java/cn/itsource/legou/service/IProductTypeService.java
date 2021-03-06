package cn.itsource.legou.service;

import cn.itsource.legou.domain.ProductType;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 商品目录 服务类
 * </p>
 *
 * @author solargen
 * @since 2019-07-31
 */
public interface IProductTypeService extends IService<ProductType> {

    List<ProductType> loadTypeTree();

    void getHomePage();
}
