package com.example.batch7.ch8.utils;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class Response {

    public Map sukses(Object obj){
        Map map = new HashMap();
        map.put("data", obj);
        map.put("status", 200);
        map.put("message", "Success");
        return map;
    }

    public Map Sukses(Object obj){
        Map map = new HashMap();
        map.put("data", obj);
        map.put("status", 200);
        map.put("message", "Success");
        return map;
    }



    public Map templateSukses(Object obj){
        Map map = new HashMap();
        map.put("data", obj);
        map.put("status", 200);
        map.put("message", "Success");
        return map;
    }

    public Map sucsess(Object obj){
        Map map = new HashMap();
        map.put("data", obj);
        map.put("status", 200);
        map.put("message", "Success");
        return map;
    }
    public Map error(Object obj, Object code){
        Map map = new HashMap();
        map.put("status", code);
        map.put("message", obj);
        return map;
    }

    public Map Error(Object obj){
        Map map = new HashMap();
        map.put("status", 404);
        map.put("message", obj);
        return map;
    }

    public Map templateEror(Object obj){
        Map map = new HashMap();
        map.put("status", 404);
        map.put("message", obj);
        return map;
    }



    public boolean chekNull(Object obj){
        if(obj == null){
            return true;
        }
        return  false;
    }

    public Map notFound(Object obj) {
        Map map = new HashMap();
        map.put("message", obj);
        map.put("status", "404");
        return map;
    }
}