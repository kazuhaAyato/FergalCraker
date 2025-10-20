package cn.frkovo.fergalCraker;

import com.alibaba.fastjson2.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Booter {
    public static HashMap<Integer,Fucker> map = new HashMap<>();
    public Booter(){
        while(true){
            for(int i = 1000;i<=9999;i++){
                new Fucker(String.valueOf(i)).run();
            }
        }
    }
}
