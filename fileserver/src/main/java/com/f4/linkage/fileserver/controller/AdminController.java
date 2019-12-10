package com.f4.linkage.fileserver.controller;

import com.f4.linkage.fileserver.dao.PostDao;
import com.f4.linkage.fileserver.model.HotPostCandidate;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class AdminController {
    public static List<Integer> hpList = new ArrayList<Integer>();

    static{
        for(int i = 0; i < 20; i++){
            hpList.add(-1);
        }
    }

    @Resource
    PostDao postDao;
    
    @GetMapping("/admin/post/trend")
    List<HotPostCandidate> getHotPostCandidateList(HttpServletRequest request){
        int interval, amount;
        try {
            interval = Integer.parseInt(request.getParameter("interval"));
            amount = Integer.parseInt(request.getParameter("amount"));
        }catch (NumberFormatException e){
            interval = 3600;
            amount = 20;
        }
        List<HotPostCandidate> hpcList = postDao.getVisit(interval, amount);
        for (HotPostCandidate hpc: hpcList
             ) {
            if(hpList.contains(hpc.getId())){
                hpc.setTrend(true);
            }else {
                hpc.setTrend(false);
            }
        }
        return hpcList;
    }


    @PostMapping("/admin/post/trend")
    void setHotPostCandidateList(@RequestBody Map param){
        try {
            List<Integer> trend = (List<Integer>) param.get("trend");
            for(int i = 0; i < trend.size() && i < hpList.size(); i++){
                hpList.set(i, trend.get(i));
            }
            if(trend.size() < hpList.size()){
                for(int i = trend.size(); i < hpList.size(); i++){
                    hpList.set(i, -1);
                }
            }
        }catch (ClassCastException e){
            e.printStackTrace();
        }
    }
}
