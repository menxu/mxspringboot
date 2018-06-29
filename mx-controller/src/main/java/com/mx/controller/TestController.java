package com.mx.controller;

import com.mx.dto.base.Response;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by menxu on 18/6/29.
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @RequestMapping(value = "/v1", name = "管理员列表")
    public Response v1() {
        return Response.ok("TEST v1");
    }
}
