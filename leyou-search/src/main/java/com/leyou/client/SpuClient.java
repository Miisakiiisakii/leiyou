package com.leyou.client;


import leyou.item.api.SpuApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * Feature:spu FeignClient
 */
@FeignClient(value = "item-service")
public interface SpuClient extends SpuApi {
}
