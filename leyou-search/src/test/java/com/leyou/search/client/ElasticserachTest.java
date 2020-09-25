package com.leyou.search.client;

import com.leyou.common.pojo.PageResult;
import com.leyou.search.pojo.Goods;
import com.leyou.search.repository.GoodsRepository;
import com.leyou.search.search.SearchService;
import com.leyou.upload.bo.SpuBo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
@RunWith(SpringRunner.class)
public class ElasticserachTest {

    @Autowired
    private ElasticsearchTemplate template;

    @Autowired
    private GoodsRepository goodsRepository;

    @Autowired
    private SearchService searchService;

    @Autowired
    private  GoodsClient goodsClient;

    @Test
    public void test() {
        this.template.createIndex(Goods.class);
        this.template.putMapping(Goods.class);

        Integer page = 1;
        Integer rows = 100;
        //分页查询spu，获取分页结果集
        PageResult<SpuBo> result = this.goodsClient.querySpuByPage(null, null, page, rows);
        //获取当前页的数据
        List<SpuBo> items = result.getItems();
        //处理List<SpuBo> ==> List<Goods>
        items.stream().map(spuBo -> {
            try {
                return this.searchService.buildGoods(spuBo);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }).collect(Collectors.toList());
    }
}
