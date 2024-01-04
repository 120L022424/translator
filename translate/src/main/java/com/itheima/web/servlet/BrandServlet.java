package com.itheima.web.servlet;

import java.io.*;
import com.alibaba.fastjson.JSON;
import com.itheima.pojo.TransApi;
import com.itheima.pojo.Chinese;
import com.itheima.service.BrandService;
import com.itheima.service.impl.BrandServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/brand/*")
public class BrandServlet extends BaseServlet{

//
//    public void selectAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        //1. 调用service查询
//        List<Brand> brands = brandService.selectAll();
//
//        //2. 转为JSON
//        String jsonString = JSON.toJSONString(brands);
//        //3. 写数据
//        response.setContentType("text/json;charset=utf-8");
//        response.getWriter().write(jsonString);
//    }
//
//
//    public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//        //1. 接收品牌数据
//        BufferedReader br = request.getReader();
//        String params = br.readLine();//json字符串
//
//        //转为Brand对象
//        Brand brand = JSON.parseObject(params, Brand.class);
//
//        //2. 调用service添加
//        brandService.add(brand);
//
//        //3. 响应成功的标识
//        response.getWriter().write("success");
//    }
//    public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//        //1. 接收品牌数据
//        BufferedReader br = request.getReader();
//        String params = br.readLine();//json字符串
//        System.out.println(params);
//        //转为Brand对象
//        Brand brand = JSON.parseObject(params, Brand.class);
//        //2. 调用service添加
//        brandService.update(brand);
//
//        //3. 响应成功的标识
//        response.getWriter().write("success");
//    }
//    /**
//     * 批量删除
//     * @param request
//     * @param response
//     * @throws ServletException
//     * @throws IOException
//     */
//    public void deleteByIds(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//        //1. 接收数据  [1,2,3]
//        BufferedReader br = request.getReader();
//        String params = br.readLine();//json字符串
//
//        //转为 int[]
//        int[] ids = JSON.parseObject(params, int[].class);
//
//        //2. 调用service添加
//        brandService.deleteByIds(ids);
//
//        //3. 响应成功的标识
//        response.getWriter().write("success");
//    }
//    public void deleteById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//        //1. 接收数据  [1,2,3]
//        BufferedReader br = request.getReader();
//        String params = br.readLine();//json字符串
//
//        //转为 int[]
//        int id = JSON.parseObject(params, int.class);
//
//        //2. 调用service添加
//        brandService.deleteById(id);
//
//        //3. 响应成功的标识
//        response.getWriter().write("success");
//    }
//
//    /**
//     * 分页查询
//     * @param request
//     * @param response
//     * @throws ServletException
//     * @throws IOException
//     */
//
//    public void selectByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        //1. 接收 当前页码 和 每页展示条数    url?currentPage=1&pageSize=5
//        String _currentPage = request.getParameter("currentPage");
//        String _pageSize = request.getParameter("pageSize");
//
//        int currentPage = Integer.parseInt(_currentPage);
//        int pageSize = Integer.parseInt(_pageSize);
//
//        //2. 调用service查询
//        PageBean<Brand> pageBean = brandService.selectByPage(currentPage, pageSize);
//
//        //2. 转为JSON
//        String jsonString = JSON.toJSONString(pageBean);
//        //3. 写数据
//        response.setContentType("text/json;charset=utf-8");
//        response.getWriter().write(jsonString);
//    }
//
//
//
//
//    /**
//     * 分页条件查询
//     * @param request
//     * @param response
//     * @throws ServletException
//     * @throws IOException
//     */
//
//    public void selectByPageAndCondition(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        //1. 接收 当前页码 和 每页展示条数    url?currentPage=1&pageSize=5
//        String _currentPage = request.getParameter("currentPage");
//        String _pageSize = request.getParameter("pageSize");
//
//        int currentPage = Integer.parseInt(_currentPage);
//        int pageSize = Integer.parseInt(_pageSize);
//
//        // 获取查询条件对象
//        BufferedReader br = request.getReader();
//        String params = br.readLine();//json字符串
//
//        //转为 Brand
//        Brand brand = JSON.parseObject(params, Brand.class);
//
//
//        //2. 调用service查询
//        PageBean<Brand> pageBean = brandService.selectByPageAndCondition(currentPage,pageSize,brand);
//
//        //2. 转为JSON
//        String jsonString = JSON.toJSONString(pageBean);
//        //3. 写数据
//        response.setContentType("text/json;charset=utf-8");
//        response.getWriter().write(jsonString);
//    }
    public void trans(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1. 调用service查询
        String for_tran = new String(request.getParameter("chinese").getBytes("iso-8859-1"), "utf-8");
        System.out.println(for_tran);
        String APP_ID = "20221115001452967";
        String SECURITY_KEY = "YpRz9dkDHYRoYPY7fc0j";
        TransApi api = new TransApi(APP_ID, SECURITY_KEY);
        String query = for_tran;
        String domain = "medicine";
        String str=api.getTransResult(query, "auto", "en", domain);
        String str1 = str.substring(0, str.indexOf("["));
        String str2 = str.substring(str1.length()+1, str.length());
        int index = str2.indexOf(":");
        //根据第一个点的位置 获得第二个点的位置
        index = str2.indexOf(":", index + 1);
        //根据第二个点的位置，截取 字符串。得到结果 result
        String result = str2.substring(index + 2);
        String res=result.substring(0, result.indexOf("}")-1);

        //2. 转为JSON
        String jsonString = JSON.toJSONString(res);
        System.out.println(res);
        //3. 写数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }
}

