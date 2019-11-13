package com.f4.linkage.fileserver.controller;

import com.f4.linkage.fileserver.model.Moment;
import com.f4.linkage.fileserver.util.DataUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class MomentController {
    @Resource
    DataUtil dataUtil;

    @GetMapping("/moment/search")
    public List<Moment> test(){

        return dataUtil.getMoments("admin");
    }
}
