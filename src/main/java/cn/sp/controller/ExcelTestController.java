package cn.sp.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.sp.entity.StudentEntity;
import cn.sp.utils.ExcelUtils;
import cn.sp.vo.IResult;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: 2YSP
 * @Description:
 * @Date: Created in 2018/1/3
 */
@RestController
public class ExcelTestController {
    @ApiOperation(value = "生成excel文件测试接口")
    @GetMapping("/excel")
    public IResult test()throws IOException{
        List<StudentEntity> list = new ArrayList<>();
        StudentEntity student = new StudentEntity();
        student.setId("1");
        student.setName("张三");
        student.setSex(1);
        student.setBirthday(new Date());
        student.setRegistrationDate(new Date());
        list.add(student);
        StudentEntity student2 = new StudentEntity();
        student2.setId("2");
        student2.setName("李四");
        student2.setSex(2);
        student2.setBirthday(new Date());
        student2.setRegistrationDate(new Date());
        list.add(student2);

        Workbook workbook = ExcelExportUtil.exportExcel(
                new ExportParams("计算机一班学生", "学生"), StudentEntity.class, list);
        String projectPath = System.getProperty("user.dir");
        OutputStream output = new FileOutputStream(new File(projectPath+File.separator+"学生.xlsx"));
        workbook.write(output);
        workbook.close();
        output.close();
        return IResult.SUCCESS;
    }

    @ApiOperation(value = "导出cvs文件")
    @GetMapping("/export")
    public IResult export(HttpServletResponse response){
        List<StudentEntity> list = new ArrayList<>();
        StudentEntity student = new StudentEntity();
        student.setId("1");
        student.setName("张三");
        student.setSex(1);
        student.setBirthday(new Date());
        student.setRegistrationDate(new Date());
        list.add(student);
        StudentEntity student2 = new StudentEntity();
        student2.setId("2");
        student2.setName("李四");
        student2.setSex(2);
        student2.setBirthday(new Date());
        student2.setRegistrationDate(new Date());
        list.add(student2);
        ExcelUtils.export("学生.csv", StudentEntity.class, list, response);
        return IResult.SUCCESS;
    }

}
