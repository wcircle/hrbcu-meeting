package com.zxy.hrbcu.meeting.service;

import com.zxy.hrbcu.meeting.dao.*;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by wenxu on 2017/6/9.
 */
@Service
@Scope("prototype")
public class ExportService {

    @Resource
    private ReceiveService receiveService;

    @Resource
    private TUserReceiveDao receiveDao;

    @Resource
    private TUserEatingDao eatingDao;

    @Resource
    private TUserSleepingDao sleepingDao;

    @Resource
    private UserService userService;

    @Resource
    private TMeetingCollegeDao collegeDao;

    @Resource
    private TUUserDao userDao;

    /**
     * 回执管理导出
     * @throws Exception
     */
    public String exportExcelForReceive(HttpServletRequest request,List<Map<String, String>> receiveList) throws Exception{
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("回执导出");
        Row row = sheet.createRow(0);
        row.createCell(0).setCellValue("姓名");
        row.createCell(1).setCellValue("手机号");
        row.createCell(2).setCellValue("身份证号");
        row.createCell(3).setCellValue("代表院校");
        row.createCell(4).setCellValue("职称");
        row.createCell(5).setCellValue("住宿需求");
        row.createCell(6).setCellValue("是否需要接送");
        row.createCell(7).setCellValue("出发方式");
        row.createCell(8).setCellValue("航班号/车次");
        row.createCell(9).setCellValue("到达机场/到达车站");
        row.createCell(10).setCellValue("到达时间");
        row.createCell(11).setCellValue("回程方式");
        row.createCell(12).setCellValue("航班号/车次");
        row.createCell(13).setCellValue("回程机场/回程车站");
        row.createCell(14).setCellValue("回程时间");
        for(int i = 0 ; i < receiveList.size(); i++){
            row = sheet.createRow(1+i);
            row.createCell(0).setCellValue(receiveList.get(i).get("userName"));
            row.createCell(1).setCellValue(receiveList.get(i).get("phone"));
            row.createCell(2).setCellValue(receiveList.get(i).get("idNo"));
            row.createCell(3).setCellValue(receiveList.get(i).get("representCollege"));
            row.createCell(4).setCellValue(receiveList.get(i).get("work"));

            if(receiveList.get(i).get("bedRequire").equals("1")) {
                row.createCell(5).setCellValue("标准间（2人合住）");
            }else if(receiveList.get(i).get("bedRequire").equals("2")) {
                row.createCell(5).setCellValue("单人间（1人独住）");
            }else {
                row.createCell(5).setCellValue("不需要安排住宿");
            }

            if(receiveList.get(i).get("isSend").equals("1")) {
                row.createCell(6).setCellValue("是");
                if(receiveList.get(i).get("arriveType") != null) {
                    if (receiveList.get(i).get("arriveType").equals("1")) {
                        row.createCell(7).setCellValue("飞机");
                    } else if (receiveList.get(i).get("arriveType").equals("2")) {
                        row.createCell(7).setCellValue("火车");
                    }
                }

                row.createCell(8).setCellValue(receiveList.get(i).get("arriveFlightNo"));

                if(receiveList.get(i).get("arriveAirport").equals("1")) {
                    row.createCell(9).setCellValue("哈尔滨太平国际机场");
                }else if(receiveList.get(i).get("arriveAirport").equals("2")) {
                    row.createCell(9).setCellValue("哈尔滨站");
                }else if(receiveList.get(i).get("arriveAirport").equals("3")) {
                    row.createCell(9).setCellValue("哈尔滨西站");
                }else if(receiveList.get(i).get("arriveAirport").equals("4")) {
                    row.createCell(9).setCellValue("哈尔滨东站");
                }else if(receiveList.get(i).get("arriveAirport").equals("5")) {
                    row.createCell(9).setCellValue("哈尔滨北站");
                }

                row.createCell(10).setCellValue(receiveList.get(i).get("arriveTime"));

                if(receiveList.get(i).get("arriveType") != null) {
                    if (receiveList.get(i).get("returnType").equals("1")) {
                        row.createCell(11).setCellValue("飞机");
                    } else if (receiveList.get(i).get("returnType").equals("2")) {
                        row.createCell(11).setCellValue("火车");
                    }
                }
                row.createCell(12).setCellValue(receiveList.get(i).get("returnFlightNo"));

                if(receiveList.get(i).get("returnAirport").equals("1")) {
                    row.createCell(13).setCellValue("哈尔滨太平国际机场");
                }else if(receiveList.get(i).get("returnAirport").equals("2")) {
                    row.createCell(13).setCellValue("哈尔滨站");
                }else if(receiveList.get(i).get("returnAirport").equals("3")) {
                    row.createCell(13).setCellValue("哈尔滨西站");
                }else if(receiveList.get(i).get("returnAirport").equals("4")) {
                    row.createCell(13).setCellValue("哈尔滨东站");
                }else if(receiveList.get(i).get("returnAirport").equals("5")) {
                    row.createCell(13).setCellValue("哈尔滨北站");
                }

                row.createCell(14).setCellValue(receiveList.get(i).get("returnTime"));
            }else {
                row.createCell(6).setCellValue("否");
            }

        }
        SimpleDateFormat sdFormat = new SimpleDateFormat("yyyyMMddhhmmss");
        String fileName = sdFormat.format(new Date());
        //创建路径 判断文件夹是否存在
        String url = request.getSession().getServletContext().getRealPath("/");
        if (!url.endsWith(File.separator)) {
            url = url + File.separator;
        }

        // 系统时间
        String catalog = new SimpleDateFormat("yyyyMMdd").format(new Date());

        String dirName = "export/receive";
        String uploadPath = url + dirName+"/" + catalog;
        String downUploadPath = dirName+"/" + catalog;
        String FilePath = uploadPath +"/" +fileName +".xlsx";
        File file = new File(uploadPath);
        if(!file.exists()){
            file.mkdirs();
        }
        FileOutputStream fileOut = new FileOutputStream(FilePath);
        workbook.write(fileOut);
        String fileUrl = "/"+downUploadPath + "/" + fileName +".xlsx";
        fileOut.close();
        return fileUrl;
    }

    /**
     * 就餐管理导出
     * @throws Exception
     */
    public String exportExcelForEat(HttpServletRequest request,List<Map<String, String>> receiveList) throws Exception{
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("就餐导出");
        Row row = sheet.createRow(0);
        row.createCell(0).setCellValue("姓名");
        row.createCell(1).setCellValue("手机号");
        row.createCell(2).setCellValue("代表院校");
        row.createCell(3).setCellValue("就餐序号");
        row.createCell(4).setCellValue("地点");
        row.createCell(5).setCellValue("房间号");
        row.createCell(6).setCellValue("桌号");
        for(int i = 0 ; i < receiveList.size(); i++){
            row = sheet.createRow(1+i);
            row.createCell(0).setCellValue(receiveList.get(i).get("userName"));
            row.createCell(1).setCellValue(receiveList.get(i).get("phone"));
            row.createCell(2).setCellValue(receiveList.get(i).get("representCollege"));
            row.createCell(3).setCellValue(String.valueOf(receiveList.get(i).get("eatOrder")));
            row.createCell(4).setCellValue(receiveList.get(i).get("address"));
            row.createCell(5).setCellValue(receiveList.get(i).get("roomNo"));
            row.createCell(6).setCellValue(receiveList.get(i).get("tableNo"));
        }
        SimpleDateFormat sdFormat = new SimpleDateFormat("yyyyMMddhhmmss");
        String fileName = sdFormat.format(new Date());
        //创建路径 判断文件夹是否存在
        String url = request.getSession().getServletContext().getRealPath("/");
        if (!url.endsWith(File.separator)) {
            url = url + File.separator;
        }

        // 系统时间
        String catalog = new SimpleDateFormat("yyyyMMdd").format(new Date());

        String dirName = "export/eat";
        String uploadPath = url + dirName+"/" + catalog;
        String downUploadPath = dirName+"/" + catalog;
        String FilePath = uploadPath +"/" +fileName +".xlsx";
        File file = new File(uploadPath);
        if(!file.exists()){
            file.mkdirs();
        }
        FileOutputStream fileOut = new FileOutputStream(FilePath);
        workbook.write(fileOut);
        String fileUrl = "/"+downUploadPath + "/" + fileName +".xlsx";
        fileOut.close();
        return fileUrl;
    }

    /**
     * 住宿管理导出
     * @throws Exception
     */
    public String exportExcelForSleep(HttpServletRequest request,List<Map<String, String>> receiveList) throws Exception{
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("住宿导出");
        Row row = sheet.createRow(0);
        row.createCell(0).setCellValue("姓名");
        row.createCell(1).setCellValue("手机号");
        row.createCell(2).setCellValue("代表院校");
        row.createCell(3).setCellValue("住宿序号");
        row.createCell(4).setCellValue("住宿日期");
        row.createCell(5).setCellValue("地点");
        row.createCell(6).setCellValue("房间号");

        for(int i = 0 ; i < receiveList.size(); i++){
            row = sheet.createRow(1+i);
            row.createCell(0).setCellValue(receiveList.get(i).get("userName"));
            row.createCell(1).setCellValue(receiveList.get(i).get("phone"));
            row.createCell(2).setCellValue(receiveList.get(i).get("representCollege"));
            row.createCell(3).setCellValue(String.valueOf(receiveList.get(i).get("sleepOrder")));
            row.createCell(4).setCellValue(receiveList.get(i).get("sleepDate"));
            row.createCell(5).setCellValue(receiveList.get(i).get("address"));
            row.createCell(6).setCellValue(receiveList.get(i).get("roomNo"));
        }
        SimpleDateFormat sdFormat = new SimpleDateFormat("yyyyMMddhhmmss");
        String fileName = sdFormat.format(new Date());
        //创建路径 判断文件夹是否存在
        String url = request.getSession().getServletContext().getRealPath("/");
        if (!url.endsWith(File.separator)) {
            url = url + File.separator;
        }

        // 系统时间
        String catalog = new SimpleDateFormat("yyyyMMdd").format(new Date());

        String dirName = "export/sleep";
        String uploadPath = url + dirName+"/" + catalog;
        String downUploadPath = dirName+"/" + catalog;
        String FilePath = uploadPath +"/" +fileName +".xlsx";
        File file = new File(uploadPath);
        if(!file.exists()){
            file.mkdirs();
        }
        FileOutputStream fileOut = new FileOutputStream(FilePath);
        workbook.write(fileOut);
        String fileUrl = "/"+downUploadPath + "/" + fileName +".xlsx";
        fileOut.close();
        return fileUrl;
    }

    /**
     * 住宿管理导出
     * @throws Exception
     */
    public String exportExcelForUser(HttpServletRequest request,List<Map<String, String>> userList) throws Exception{
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("通讯录导出");
        Row row = sheet.createRow(0);
        row.createCell(0).setCellValue("姓名");
        row.createCell(1).setCellValue("手机号");
        row.createCell(2).setCellValue("性别");
        row.createCell(3).setCellValue("身份证号");
        row.createCell(4).setCellValue("代表院校");
        row.createCell(5).setCellValue("职称");

        for(int i = 0 ; i < userList.size(); i++){
            row = sheet.createRow(1+i);
            row.createCell(0).setCellValue(userList.get(i).get("userName"));
            row.createCell(1).setCellValue(userList.get(i).get("phone"));
            row.createCell(2).setCellValue(String.valueOf(userList.get(i).get("gender")).equals("1")?"男":"女");
            row.createCell(3).setCellValue(userList.get(i).get("idNo"));
            row.createCell(4).setCellValue(userList.get(i).get("representCollege"));
            row.createCell(5).setCellValue(userList.get(i).get("work"));
        }
        SimpleDateFormat sdFormat = new SimpleDateFormat("yyyyMMddhhmmss");
        String fileName = sdFormat.format(new Date());
        //创建路径 判断文件夹是否存在
        String url = request.getSession().getServletContext().getRealPath("/");
        if (!url.endsWith(File.separator)) {
            url = url + File.separator;
        }

        // 系统时间
        String catalog = new SimpleDateFormat("yyyyMMdd").format(new Date());

        String dirName = "export/user";
        String uploadPath = url + dirName+"/" + catalog;
        String downUploadPath = dirName+"/" + catalog;
        String FilePath = uploadPath +"/" +fileName +".xlsx";
        File file = new File(uploadPath);
        if(!file.exists()){
            file.mkdirs();
        }
        FileOutputStream fileOut = new FileOutputStream(FilePath);
        workbook.write(fileOut);
        String fileUrl = "/"+downUploadPath + "/" + fileName +".xlsx";
        fileOut.close();
        return fileUrl;
    }

    /**
     * 住宿管理导出
     * @throws Exception
     */
    public String exportExcelForCollegeStatistics(HttpServletRequest request,List<Map<String, String>> statisticsList) throws Exception{
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("参会单位数据统计");
        Row row = sheet.createRow(0);
        row.createCell(0).setCellValue("代表单位");
        row.createCell(1).setCellValue("报名回执数量");

        for(int i = 0 ; i < statisticsList.size(); i++){
            row = sheet.createRow(1+i);
            row.createCell(0).setCellValue(statisticsList.get(i).get("representCollege"));
            row.createCell(1).setCellValue(String.valueOf(statisticsList.get(i).get("receiveCount")));
        }
        SimpleDateFormat sdFormat = new SimpleDateFormat("yyyyMMddhhmmss");
        String fileName = sdFormat.format(new Date());
        //创建路径 判断文件夹是否存在
        String url = request.getSession().getServletContext().getRealPath("/");
        if (!url.endsWith(File.separator)) {
            url = url + File.separator;
        }

        // 系统时间
        String catalog = new SimpleDateFormat("yyyyMMdd").format(new Date());

        String dirName = "export/collegeStatistics";
        String uploadPath = url + dirName+"/" + catalog;
        String downUploadPath = dirName+"/" + catalog;
        String FilePath = uploadPath +"/" +fileName +".xlsx";
        File file = new File(uploadPath);
        if(!file.exists()){
            file.mkdirs();
        }
        FileOutputStream fileOut = new FileOutputStream(FilePath);
        workbook.write(fileOut);
        String fileUrl = "/"+downUploadPath + "/" + fileName +".xlsx";
        fileOut.close();
        return fileUrl;
    }

}
