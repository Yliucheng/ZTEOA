package ZTE.utils.excel.exam;

import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.metadata.Head;
import com.alibaba.excel.util.StyleUtil;
import com.alibaba.excel.write.handler.CellWriteHandler;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.metadata.holder.WriteTableHolder;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import org.apache.poi.ss.usermodel.*;

import java.util.List;

/**
 * 设置成绩表单元格格式，如果是差单元格变红
 */
public class ExamCellColorWriterHandler implements CellWriteHandler {
    @Override
    public void beforeCellCreate(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, Row row, Head head, Integer integer, Integer integer1, Boolean aBoolean) {

    }

    @Override
    public void afterCellCreate(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, Cell cell, Head head, Integer integer, Boolean aBoolean) {

    }

    @Override
    public void afterCellDispose(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, List<CellData> list, Cell cell, Head head, Integer integer, Boolean aBoolean) {
        //获取行坐标
        int rowIndex = cell.getRowIndex();

        //获取列下标，(从0开始)
        int colIndex =cell.getColumnIndex();
        // 设置列宽
        Sheet sheet = writeSheetHolder.getSheet();
        if (colIndex>2){
            sheet.setColumnWidth(colIndex,5500);
        }else {
            sheet.setColumnWidth(colIndex,3000);
        }

        if (0!=rowIndex){
            //从第三列开始找
            if (colIndex>2){
                String value = cell.getRow().getCell(colIndex).getStringCellValue();
                if (value.contains("差")){
                    Workbook workbook = cell.getSheet().getWorkbook();
                    WriteCellStyle contentCellStyle = new WriteCellStyle();
                    // 设置填充前背景颜色，直接设置填充背景颜色没有用
                    contentCellStyle.setFillForegroundColor(IndexedColors.RED.getIndex());
                    CellStyle cellStyle = StyleUtil.buildHeadCellStyle(workbook,contentCellStyle);
                    cell.getRow().getCell(colIndex).setCellStyle(cellStyle);
                }
            }
        }
    }
}
