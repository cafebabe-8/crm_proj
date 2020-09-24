package org.crm.web.controller;


import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.crm.entity.Activity;
import org.crm.entity.Page;
import org.crm.entity.TblUser;
import org.crm.service.ActivityService;
import org.crm.utils.DateTimeUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import sun.misc.BASE64Encoder;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Controller
@RequestMapping("/activity")
public class ActivityController {

    @Resource
    private ActivityService activityService;

    @RequestMapping("/queryAll")
    @ResponseBody
    public List<Activity> queryAll(){
        return activityService.queryAll();
    }

    @RequestMapping("/fuzzyQuery")
    @ResponseBody
    public List<Activity> fuzzyQuery(String keyword){
        return activityService.fuzzyQuery(keyword);
    }



    @RequestMapping("/queryAllForPage")
    @ResponseBody
    public Page queryAllForPage(Page page, Activity activity){
        activityService.queryAllForPage(page, activity);
        return page;
    }

    @RequestMapping("/insert")
    @ResponseBody
    public Map<String, Object> insert(HttpServletRequest request, Activity activity){
        // 先获取当前user的id 作为创建者的id
        TblUser user = (TblUser) request.getSession().getAttribute("user");
        String creatorId = user.getId();
        activityService.insert(activity, creatorId);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("success", true);
        return resultMap;
    }

    @RequestMapping("/queryById")
    @ResponseBody
    public Activity queryById(String id){
        return activityService.queryById(id);
    }

    @RequestMapping("/deleteByIds")
    @ResponseBody
    public Map<String, Object> deleteByIds(String[] ids){
        activityService.deleteByIds(ids);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("success", true);
        return resultMap;
    }

    @RequestMapping("/deleteForPagination")
    @ResponseBody
    public Map<String, Object> deleteForPagination( Activity activity,
                                                    String[] in,
                                                    String[] notin,
                                                    boolean lastState,
                                                    boolean selectall){
        activityService.deleteForPagination(activity, in, notin, lastState, selectall);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("success", true);
        return resultMap;
    }

    @RequestMapping("/exportChosen")
    public void exportChosen(Activity activity,
                             String[] in,
                             String[] notin,
                             boolean lastState,
                             boolean selectall,
                             HttpServletRequest request,
                             HttpServletResponse response) throws IOException {
        // 创建一个excel文件对象
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 创建一页表
        HSSFSheet sheet = workbook.createSheet();
        // 创建第一行（表头）的字段
        HSSFRow sheetHead = sheet.createRow(0);
        sheetHead.createCell(0).setCellValue("所有者");
        sheetHead.createCell(1).setCellValue("活动名称");
        sheetHead.createCell(2).setCellValue("开始日期");
        sheetHead.createCell(3).setCellValue("结束日期");
        sheetHead.createCell(4).setCellValue("活动预算");
        sheetHead.createCell(5).setCellValue("描述");

        // 根据请求参数查询所有的市场活动记录
        List<Activity> activityList = activityService.queryAllForExport(activity, in, notin, lastState, selectall);
        // 将list中的所有记录插入表中
        for (int i = 0; i < activityList.size() ; i++) {
            Activity a = activityList.get(i);
            HSSFRow sheetRow = sheet.createRow(i + 1);
            sheetRow.createCell(0).setCellValue(a.getUser().getName());
            sheetRow.createCell(1).setCellValue(a.getName());
            sheetRow.createCell(2).setCellValue(a.getStartdate());
            sheetRow.createCell(3).setCellValue(a.getEnddate());
            sheetRow.createCell(4).setCellValue(a.getCost());
            sheetRow.createCell(5).setCellValue(a.getDescription());
        }

        // 设置文件名
        String filename = DateTimeUtil.getSysTimeForUpload() + ".xls";
        // 根据浏览器标识对文件名进行编码
        String encodedFilename = encodingFileName(filename, request);

        // 为浏览器提供下载框
        response.setHeader("Content-Disposition", "attachment;filename=" + encodedFilename);
        ServletOutputStream out = response.getOutputStream();
        workbook.write(out);

    }

    public String encodingFileName(String filename, HttpServletRequest request) throws UnsupportedEncodingException {
        // 获取UA
        String userAgent = request.getHeader("User-Agent");
        // 区分不同微软系浏览器发出的请求：
        // 含有MSIE: 小于IE11的版本
        // 含有rv:11.0) like Gecko: IE11
        // 含有Edge 或者 Edg: Edge浏览器
        boolean isIE_LT11 = userAgent.contains("MSIE");
        boolean isIE11 = userAgent.contains("rv:11.0) like Gecko");
        boolean isEdge = userAgent.contains("Edg");
        // 微软系浏览器 直接进行UTF-8编码
        if (isIE_LT11 || isIE11 || isEdge) {
            filename = URLEncoder.encode(filename, "UTF-8");
            // 如果文件名中有空格 这个方法会将空格编码成+ 而浏览器则会编码成%20 因此需替换
            filename = filename.replace("+", "%20");
        } else { // chrome firefox等采用 base64编码
            BASE64Encoder base64Encoder = new BASE64Encoder();
            filename = base64Encoder.encode(filename.getBytes(StandardCharsets.UTF_8));
            // 告诉浏览器要以base64解码
            filename = "=?utf-8?B?" + filename + "?=";

        }
        return filename;
    }

    @RequestMapping("/import")
    @ResponseBody
    public Map<String, Object> importSheet(MultipartFile worksheet, HttpSession session) throws IOException {
        // 读取excel文件
        HSSFWorkbook workbook = new HSSFWorkbook(worksheet.getInputStream());
        // 获取第一页工作表
        HSSFSheet sheet = workbook.getSheetAt(0);
        // 创建list容器放入数据
        List<Activity> activityList = new ArrayList<>();
        // 获得表中最后行的索引 与数据条数相等
        int totalRowsOfData = sheet.getLastRowNum();
        // 获得creator的信息
        TblUser userInSession = (TblUser) session.getAttribute("user");
        // 将数据放入list
        for (int i = 1; i <= totalRowsOfData; i++) {
            // 获取记录行的数据
            HSSFRow rowData = sheet.getRow(i);
            Activity activity = new Activity();
            TblUser user = new TblUser();
            user.setName(rowData.getCell(0).getStringCellValue());
            activity.setId(UUID.randomUUID().toString().replace("-", ""));
//            activity.setOwner();
            activity.setName(rowData.getCell(1).getStringCellValue());
            activity.setStartdate(rowData.getCell(2).getStringCellValue());
            activity.setEnddate(rowData.getCell(3).getStringCellValue());
            activity.setCost(rowData.getCell(4).getStringCellValue());
            activity.setDescription(rowData.getCell(5).getStringCellValue());
            activity.setCreateby(userInSession.getId());
            activity.setCreatetime(DateTimeUtil.getSysTime());
            activity.setUser(user);

            activityList.add(activity);
        }
        // 将数据插入数据库
        activityService.insertImported(activityList);
        Map<String, Object> result = new HashMap<>();
        result.put("success", true);
        return result;
    }

    @RequestMapping("/downloadTemplate")
    public void downloadTemplate(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 获取classpath
        ServletContext context = request.getServletContext();
        String filename = "template.xls";
        // 获取文件路径
        String filePath = context.getRealPath("/static/worksheet_template/"+filename);
        // 创建输入输出流对象
        FileInputStream in = new FileInputStream(filePath);
        ServletOutputStream out = response.getOutputStream();
        // 对文件名进行编码
        filename = encodingFileName(filename, request);
        // 为浏览器提供下载框
        response.setHeader("Content-Disposition", "attachment;filename="+filename);
        // 输入输出
        try {
            byte[] bytes = new byte[1024];
            int len = 0;
            while ((len = in.read(bytes)) != -1) {
                out.write(bytes, 0, len);
            }
        } finally {
            out.close();
            in.close();
        }
    }


    @RequestMapping("/detail.html")
    public ModelAndView jumpToDetail(String id) {
        ModelAndView mav = new ModelAndView();
        Activity activity = activityService.queryById(id);
        mav.addObject("activity", activity);
        mav.setViewName("/workbench/activity/detail");
        return mav;
    }



}
