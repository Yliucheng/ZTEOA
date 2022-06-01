package ZTE.utils.excel.classes;

import com.alibaba.excel.write.handler.SheetWriteHandler;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.metadata.holder.WriteWorkbookHolder;
import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.DataValidationConstraint;
import org.apache.poi.ss.usermodel.DataValidationHelper;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.usermodel.XSSFDataValidation;

import java.util.HashMap;
import java.util.Map;

public class ClassSheetSelectWriteHandler implements SheetWriteHandler {
    @Override
    public void beforeSheetCreate(WriteWorkbookHolder writeWorkbookHolder, WriteSheetHolder writeSheetHolder) {

    }

    @Override
    public void afterSheetCreate(WriteWorkbookHolder writeWorkbookHolder, WriteSheetHolder writeSheetHolder) {

        String[] studentResult = {"1","2","3"};
        // 键是下拉框所在列的索引，值是下拉框的内容
        Map<Integer,String[]> mapDropDown = new HashMap<>();
        mapDropDown.put(1,studentResult);
        Sheet sheet = writeSheetHolder.getSheet();
        // 开始设置下拉框 DataValidationHelper数据验证助手
        DataValidationHelper helper = sheet.getDataValidationHelper();
        for (Map.Entry<Integer, String[]> entry : mapDropDown.entrySet()) {
            // 起始行、终止行、起始列、终止列
            CellRangeAddressList addressList = new CellRangeAddressList(1,1000,entry.getKey(),entry.getKey());
            // 设置下拉框数据  DataValidationConstraint 数据验证约束  createExplicitListConstraint创建下拉列表
            DataValidationConstraint constraint = helper.createExplicitListConstraint(entry.getValue());
            DataValidation dataValidation = helper.createValidation(constraint, addressList);
            // 处理excel兼容性问题
            if(dataValidation instanceof XSSFDataValidation){
                dataValidation.setSuppressDropDownArrow(true);
                dataValidation.setShowErrorBox(true);
            }else {
                dataValidation.setSuppressDropDownArrow(false);
            }
            sheet.addValidationData(dataValidation);
        }
    }
}
