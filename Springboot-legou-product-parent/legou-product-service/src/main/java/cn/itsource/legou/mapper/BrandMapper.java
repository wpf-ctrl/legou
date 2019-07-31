package cn.itsource.legou.mapper;

import cn.itsource.legou.domain.Brand;
import cn.itsource.legou.query.BrandQuery;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 品牌信息 Mapper 接口
 * </p>
 *
 * @author solargen
 * @since 2019-07-31
 */
public interface BrandMapper extends BaseMapper<Brand> {

    IPage<Brand> queryPage(Page page, @Param("query")BrandQuery query);

    void deletemore(List id);
}
