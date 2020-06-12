package com.etoak.controller;


import com.etoak.bean.PageVo;
import com.etoak.bean.User;
import com.etoak.service.UserService2;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService2 userService2;

    @RequestMapping("/toList")
    public String toList(){
        return "list";
    }
    @RequestMapping("/list")
    @ResponseBody
    public PageVo<User> list(Integer pageNumber, Integer pageSize){
        return userService2.list(pageNumber,pageSize);
    }

    @RequestMapping("/export2Excel")
    @ResponseBody
    public void export2Excel(HttpServletResponse response) throws IOException {
        Workbook wb = new XSSFWorkbook();
        Sheet sheet = wb.createSheet("异常人员列表");
        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("序号");
        header.createCell(1).setCellValue("姓名");
        header.createCell(2).setCellValue("身份证号");
        header.createCell(3).setCellValue("体温");
        header.createCell(4).setCellValue("手机号");
        header.createCell(5).setCellValue("身体状况");
        header.createCell(6).setCellValue("是否外地流入");
        header.createCell(7).setCellValue("流入地址");
        header.createCell(8).setCellValue("在济地址");
        header.createCell(9).setCellValue("反济时间");
        List<User> books = userService2.queryYCallUser();
        int k=1;
        for(User b:books){
            Row row = sheet.createRow(k++);
            row.createCell(0).setCellValue(k-1);
            row.createCell(1).setCellValue(b.getName());
            row.createCell(2).setCellValue(b.getIdentity());
            row.createCell(3).setCellValue(b.getTemperature());
            row.createCell(4).setCellValue(b.getPhone());
            row.createCell(5).setCellValue(b.getState());
            row.createCell(6).setCellValue(b.getInflow()==1?"是":"否");
            row.createCell(7).setCellValue(b.getFromm());
            row.createCell(8).setCellValue(b.getAddress());
            row.createCell(9).setCellValue(b.getReturnDate());
        }
        response.setHeader("Content-Disposition","attachment;filename="+ URLEncoder.encode("异常用户列表","utf-8")+".xlsx");
        wb.write(response.getOutputStream());
        wb.close();
    }

}
