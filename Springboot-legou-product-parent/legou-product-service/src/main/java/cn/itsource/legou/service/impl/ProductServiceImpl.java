package cn.itsource.legou.service.impl;

import cn.itsource.legou.domain.Product;
import cn.itsource.legou.mapper.ProductMapper;
import cn.itsource.legou.service.IProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品 服务实现类
 * </p>
 *
 * @author solargen
 * @since 2019-07-31
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements IProductService {

}
