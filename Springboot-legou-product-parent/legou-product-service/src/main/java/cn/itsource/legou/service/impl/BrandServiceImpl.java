package cn.itsource.legou.service.impl;

import cn.itsource.legou.domain.Brand;
import cn.itsource.legou.mapper.BrandMapper;
import cn.itsource.legou.query.BrandQuery;
import cn.itsource.legou.service.IBrandService;
import cn.itsource.util.PageList;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.function.Supplier;

/**
 * <p>
 * 品牌信息 服务实现类
 * </p>
 *
 * @author solargen
 * @since 2019-07-31
 */
@Service
public class BrandServiceImpl extends ServiceImpl<BrandMapper, Brand> implements IBrandService {

    @Override
    //如果当前没有事务加入到事务中
    @Transactional(readOnly = true,propagation = Propagation.SUPPORTS)
    public PageList<Brand> queryPage(BrandQuery query) {
        //查询总数
        //查询当前页的数据
        //封装到Pagelist返回
        Page<Brand> page = new Page<>(query.getPageNum(),query.getPageSize());
        IPage<Brand> ip = baseMapper.queryPage(page, query);
        return new PageList<>(ip.getTotal(),ip.getRecords());
    }

    @Override
    @Transactional(readOnly = true,propagation = Propagation.SUPPORTS)
    public void delete(List id) {
        baseMapper.deletemore(id);
    }
}
