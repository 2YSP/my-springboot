package cn.sp.utils;

import cn.afterturn.easypoi.excel.entity.params.ExcelExportEntity;
import cn.afterturn.easypoi.excel.entity.params.ExcelForEachParams;
import cn.afterturn.easypoi.excel.export.styler.IExcelExportStyler;
import org.apache.poi.ss.usermodel.*;

/**
 * @Author:ship
 * @Description:
 * @Date: Created in 2018/11/14
 */
public class ExcelExportStylerImpl implements IExcelExportStyler {

    /**
     * 每列标题样式
     */
    private CellStyle titleStyle;
    /**
     * 数据行样式
     */
    private CellStyle styles;

    public ExcelExportStylerImpl(Workbook workbook){
        this.titleStyle = initTitleStyle(workbook);
        this.styles = initStyles(workbook);
    }

    private CellStyle initStyles(Workbook workbook) {
        return getBaseCellStyle(workbook);
    }

    private CellStyle initTitleStyle(Workbook workbook) {
        return getBaseCellStyle(workbook);
    }

    @Override
    public CellStyle getTitleStyle(short i) {
        return this.titleStyle;
    }

    @Override
    public CellStyle getStyles(Cell cell, int i, ExcelExportEntity excelExportEntity, Object o, Object o1) {
        return this.styles;
    }

    @Override
    public CellStyle getHeaderStyle(short i) {
        return null;
    }

    @Override
    public CellStyle getStyles(boolean b, ExcelExportEntity excelExportEntity) {
        return this.styles;
    }

    @Override
    public CellStyle getTemplateStyles(boolean b, ExcelForEachParams excelForEachParams) {
        return null;
    }

    /**
     * 基础样式
     *
     * @return
     */
    private CellStyle getBaseCellStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setFontName("宋体");
        font.setFontHeightInPoints((short) 11);
        style.setFont(font);
        //水平居中
        style.setAlignment(HorizontalAlignment.CENTER);
        //上下居中
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        //设置自动换行
        style.setWrapText(true);
        return style;
    }

}
