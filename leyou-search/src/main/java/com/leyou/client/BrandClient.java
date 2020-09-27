package com.leyou.client;


import leyou.item.api.BrandApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * Feature:品牌FeignClient
 */
@FeignClient(value = "item-service")
public interface BrandClient extends BrandApi {
}
