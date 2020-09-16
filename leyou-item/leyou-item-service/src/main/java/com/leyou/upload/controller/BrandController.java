package com.leyou.upload.controller;

import com.leyou.common.pojo.PageResult;
import com.leyou.upload.pojo.Brand;
import com.leyou.upload.pojo.Category;
import com.leyou.upload.service.BrandService;
import com.leyou.upload.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("brand")
public class  BrandController {

    @Autowired
    private BrandService brandService;

    @Autowired
    private CategoryService categoryService;


    /**
     * 根据查询条件分页并排序查询品牌信息
     * @param key
     * @param page
     * @param rows
     * @param sortBy
     * @param desc
     * @return
     */
    @GetMapping("page") //?key=&page=1&rows=5&sortBy=id&desc=false
    public ResponseEntity<PageResult<Brand>> queryBrandsByPage(
            @RequestParam(value = "key", required = false)String key,
            @RequestParam(value = "page", defaultValue = "1")Integer page,
            @RequestParam(value = "rows", defaultValue = "5")Integer rows,
            @RequestParam(value = "sortBy", required = false)String sortBy,
            @RequestParam(value = "desc", required = false)Boolean desc
    ) {
        PageResult<Brand> result = this.brandService.queryBrandsByPage(key, page, rows, sortBy, desc);
        if(result == null || CollectionUtils.isEmpty(result.getItems())){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    /**
     *
     * @param brand
     * @param cids
     * @return
     */
    @PostMapping
    public ResponseEntity<Void> saveBrand(Brand brand, @RequestParam("cids") List<Long> cids){
        this.brandService.saveBrand(brand, cids);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * 通过品牌id查询商品分类
     * @param bid
     * @return
     */
    @GetMapping("bid/{bid}")
    public ResponseEntity<List<Category>> queryByBrandId(@PathVariable("bid") Long bid) {
        List<Category> list = this.categoryService.queryByBrandId(bid);
        if (list == null || list.size() < 1) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(list);
    }

    @GetMapping("cid/{cid}")
    public ResponseEntity<List<Brand>> queryBrandsByCid(@PathVariable("cid")Long cid){
        List<Brand> brands = this.brandService.queryBrandsByCid(cid);
        if (CollectionUtils.isEmpty(brands)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(brands);
    }
}
