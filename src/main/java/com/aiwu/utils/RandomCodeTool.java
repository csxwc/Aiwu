package com.aiwu.utils;

import org.springframework.stereotype.Component;

@Component
public class RandomCodeTool {

    public String getRandomCode() {

        //随机生成5验证码
        Integer x =(int)((Math.random()*9+1)*10000);
        String code = x.toString();
        return code;

    }

}
