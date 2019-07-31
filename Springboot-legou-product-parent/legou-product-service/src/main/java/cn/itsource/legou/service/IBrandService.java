package cn.itsource.legou.service;

import cn.itsource.legou.domain.Brand;
import cn.itsource.legou.query.BrandQuery;
import cn.itsource.util.PageList;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 品牌信息 服务类
 * </p>
 *
 * @author solargen
 * @since 2019-07-31
 */
public interface IBrandService extends IService<Brand> {
    /*分页查询*/
    PageList<Brand> queryPage(BrandQuery query);

    void delete(List id);
}
