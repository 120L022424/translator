package com.itheima.web.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class main {
    public String img2text(String imgurl) {
        String tag = null;
        Process proc;
        try {
//        	python执行环境
            String pyEnv = "python";
//        	要执行的py代码
            String program = "img2text.py";
//        	参数集合
            String[] pyargs = new String[] {pyEnv, program,imgurl};
            proc = Runtime.getRuntime().exec(pyargs);
//          输出到控制台有中文，使用utf-8还是乱码 ， 使用GBK就可以
            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream(),"GBK"));
            System.out.println(in.readLine());
            tag = in.readLine();
            in.close();
            proc.waitFor();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return tag;
    }

    public void text2img(String prompt) {
        Process proc;
        try {
//        	python执行环境
            String pyEnv = "python";
//        	要执行的py代码
            String program = "text2img.py";
//        	参数集合
            String[] pyargs = new String[] {pyEnv, program, prompt};
            proc = Runtime.getRuntime().exec(pyargs);
//          输出到控制台有中文，使用utf-8还是乱码 ， 使用GBK就可以
            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream(),"GBK"));
            System.out.println(in.readLine());
            //输出控制台信息
            in.close();
            proc.waitFor();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
