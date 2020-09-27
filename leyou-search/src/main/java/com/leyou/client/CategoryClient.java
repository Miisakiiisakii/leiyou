package com.leyou.client;


import leyou.item.api.CategoryApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * Feature:商品分类FeignClient
 */
@FeignClient(value = "item-service")
public interface CategoryClient extends CategoryApi {
}
