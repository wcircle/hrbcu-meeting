package com.zxy.hrbcu.meeting.interceptor;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * 日期转换格式拦截器
 * Created by wenxu on 2017/7/31.
 */
public class DateJsonSerializer extends JsonSerializer<Date> {
    @Override
    public void serialize(Date value, JsonGenerator jgen, SerializerProvider provider) throws IOException,
            JsonProcessingException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = formatter.format(value);
        jgen.writeString(formattedDate);
    }
}
