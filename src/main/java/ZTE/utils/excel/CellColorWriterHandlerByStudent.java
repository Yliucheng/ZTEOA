package ZTE.utils.excel;

import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.metadata.Head;
import com.alibaba.excel.write.handler.CellWriteHandler;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.metadata.holder.WriteTableHolder;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.List;

/**
 * 设置单元格格式
 */
public class CellColorWriterHandlerByStudent implements CellWriteHandler {
    @Override
    public void beforeCellCreate(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, Row row, Head head, Integer integer, Integer integer1, Boolean aBoolean) {

    }

    @Override
    public void afterCellCreate(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, Cell cell, Head head, Integer integer, Boolean aBoolean) {
        Sheet sheet = writeSheetHolder.getSheet();
        sheet.getRow(cell.getRowIndex()).setHeightInPoints(10000);
    }

    @Override
    public void afterCellDispose(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, List<CellData> list, Cell cell, Head head, Integer integer, Boolean aBoolean) {
        //获取行坐标
        int rowIndex = cell.getRowIndex();

        //获取列下标，(从0开始)
        int colIndex =cell.getColumnIndex();
        // 设置列宽
        Sheet sheet = writeSheetHolder.getSheet();
        if (colIndex==4){
            sheet.setColumnWidth(colIndex,15000);
        }
    }
}
