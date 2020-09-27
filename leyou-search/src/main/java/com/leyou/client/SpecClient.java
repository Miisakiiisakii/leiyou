package com.leyou.client;


import leyou.item.api.SpecApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * Feature:特有属性FeignClient
 */
@FeignClient(value = "item-service")
public interface SpecClient extends SpecApi {
}
