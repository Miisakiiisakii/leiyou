package com.leyou.search.search;

import com.leyou.search.pojo.Goods;
import com.leyou.upload.pojo.Spu;
import org.springframework.stereotype.Service;

@Service
public class SearchService {

    public Goods buildGoods(Spu spu){
        Goods goods = new Goods();

        goods.setId(spu.getId());
        goods.setCid1(spu.getCid1());
        goods.setCid2(spu.getCid2());
        goods.setCid3(spu.getCid3());
        goods.setBrandId(spu.getBrandId());
        goods.setCreateTime(spu.getCreateTime());
        goods.setSubTitle(spu.getSubTitle());
        //拼接all字段，需要分类名称以及品牌名称
        goods.setAll(spu.getTitle() + " " + null + " " + null);
        //获取spu下的所有价格
        goods.setPrice(null);
        //获取spu下的所有sku，并转化成json字符串
        goods.setSkus(null);

        goods.setSpecs(null);
        return goods;
    }
}
