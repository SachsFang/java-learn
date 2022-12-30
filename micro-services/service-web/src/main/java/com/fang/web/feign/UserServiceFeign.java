package com.fang.web.feign;

import com.fang.micro.api.user.UserService;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author shaobin
 * @date 2022/12/29 17:31
 */
@FeignClient(name = "user-micro-service", path = "/learn")
public interface UserServiceFeign extends UserService {
}
