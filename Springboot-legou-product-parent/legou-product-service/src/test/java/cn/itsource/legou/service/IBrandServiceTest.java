package cn.itsource.legou.service;

import cn.itsource.legou.domain.Brand;
import cn.itsource.legou.query.BrandQuery;
import cn.itsource.legou.service.IBrandService;
import cn.itsource.util.PageList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class IBrandServiceTest {
    @Autowired
    private IBrandService brandService;

    @Test
    public void test() {
        BrandQuery query=new BrandQuery();
        PageList<Brand> brandPageList = brandService.queryPage(query);
        System.out.println(brandPageList.getTotle());
        brandPageList.getRows().forEach(e-> System.out.println(e));
    }
}