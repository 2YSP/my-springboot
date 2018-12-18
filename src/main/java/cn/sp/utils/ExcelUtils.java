package cn.sp.utils;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import org.apache.poi.ss.usermodel.Workbook;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @Author:ship
 * @Description:
 * @Date: Created in 2018/11/14
 */
public class ExcelUtils {

    public static <T> void export(String fileName, Class<?> clazz, List<T> list, HttpServletResponse response){
        try {
            ExportParams exportParams = new ExportParams();
            exportParams.setStyle(ExcelExportStylerImpl.class);
            Workbook workbook = ExcelExportUtil.exportExcel(
                    exportParams,clazz, list);
            //设置内容的类型
            response.setContentType("application/csv");
            //告诉浏览器是附件以及附件的名字，设置请求头
            response.setHeader("Content-Disposition",
                    "acttachment;filename=" + new String(fileName.getBytes(), "ISO-8859-1"));
            ServletOutputStream outputStream = response.getOutputStream();
            workbook.write(outputStream);
            workbook.close();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
