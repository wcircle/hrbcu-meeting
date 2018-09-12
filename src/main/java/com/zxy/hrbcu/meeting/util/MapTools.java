package com.zxy.hrbcu.meeting.util;

import com.zxy.hrbcu.meeting.vo.PageVo;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.*;

/**
 * Created by wenxu on 2017-5-24 09:54:57
 */
public class MapTools {

    public static List<Map<String, Object>> parseJSON2List(String jsonStr) {
        JSONArray jsonArr = JSONArray.fromObject(jsonStr);
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Iterator<JSONObject> it = jsonArr.iterator();
        while (it.hasNext()) {
            JSONObject json2 = it.next();
            list.add(parseJSON2Map(json2.toString()));
        }
        return list;
    }

    public static PageVo parse2Page(String jsonStr) {
        if (StringUtils.isBlank(jsonStr)) {
            return new PageVo();
        }
        PageVo pageVo = new PageVo();
        Map<String, Object> data = parseJSON2Map(jsonStr);
        pageVo.setTotalCount(MapUtils.getInteger(data,"totalCount"));
        pageVo.setTotalPageNum(MapUtils.getInteger(data,"totalPageNum"));
        pageVo.setPerPageSize(MapUtils.getInteger(data,"perPageSize"));
        pageVo.setCurrentPageNum(MapUtils.getInteger(data,"currentPageNum"));
        return pageVo;
    }

    public static Map<String, Object> parseJSON2Map(String jsonStr) {
        Map<String, Object> map = new HashMap<String, Object>();
        //最外层解析
        JSONObject json = JSONObject.fromObject(jsonStr);
        for (Object k : json.keySet()) {
            Object v = json.get(k);
            //如果内层还是数组的话，继续解析
            if (v instanceof JSONArray) {
                List list = new ArrayList();
                Iterator<JSONObject> it = ((JSONArray)v).iterator();
                while (it.hasNext()) {
                    Object json2 = it.next();
                    if(json2 instanceof JSONArray){
                        list.add(parseJSON2Map(json2.toString()));
                    }else{
                        list.add(json2);
                    }

                }
                map.put(k.toString(), list);
            } else {
                map.put(k.toString(), v);
            }
        }
        return map;
    }

    public static JSONObject parse(String jsonStr){
        return JSONObject.fromObject(jsonStr);
    }


    public static List<Map<String, Object>> getListByUrl(String url) {
        try {
            //通过HTTP获取JSON数据
            InputStream in = new URL(url).openStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            return parseJSON2List(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Map ConvertObjToMap(Object obj){
        Map<String,Object> reMap = new HashMap<String,Object>();
        if (obj == null){
            return null;
        }
        Field[] fields = obj.getClass().getDeclaredFields();
        try {
            for(int i=0;i<fields.length;i++){
                try {
                    Field f = obj.getClass().getDeclaredField(fields[i].getName());
                    f.setAccessible(true);
                    Object o = f.get(obj);
                    reMap.put(fields[i].getName(), o);
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        } catch (SecurityException e) {
            e.printStackTrace();
        }
        return reMap;
    }
}
