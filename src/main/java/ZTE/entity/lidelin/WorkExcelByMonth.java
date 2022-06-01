package ZTE.entity.lidelin;

import ZTE.controller.lidelin.report.ExcelWorkServlet2;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName WorkExcelByMonth
 * @Description 按月份导出信息用
 * @Version 1.0
 */
@ContentRowHeight(30) // 表体行高
@HeadRowHeight(40) // 表头行高
@ColumnWidth(20) // 列宽
public class WorkExcelByMonth {

    private int id; // 编号
    private String studentId; // 学号
    private String studentName; // 学生姓名
    private String ckstatu1; // 考试日期作业完成情况
    private String ckstatu2;
    private String ckstatu3;
    private String ckstatu4;
    private String ckstatu5;
    private String ckstatu6;
    private String ckstatu7;
    private String ckstatu8;
    private String ckstatu9;
    private String ckstatu10;
    private String ckstatu11;
    private String ckstatu12;
    private String ckstatu13;
    private String ckstatu14;
    private String ckstatu15;
    private String ckstatu16;
    private String ckstatu17;
    private String ckstatu18;
    private String ckstatu19;
    private String ckstatu20;
    private String ckstatu21;
    private String ckstatu22;
    private String ckstatu23;
    private String ckstatu24;
    private String ckstatu25;
    private String ckstatu26;
    private String ckstatu27;
    private String ckstatu28;
    private String ckstatu29;
    private String ckstatu30;
    private String ckstatu31;

    public WorkExcelByMonth() {
    }

    /**
     * @description 大月的构造
     */
    public WorkExcelByMonth(int id, String studentId, String studentName, String ckstatu1, String ckstatu2, String ckstatu3, String ckstatu4, String ckstatu5, String ckstatu6, String ckstatu7, String ckstatu8, String ckstatu9, String ckstatu10, String ckstatu11, String ckstatu12, String ckstatu13, String ckstatu14, String ckstatu15, String ckstatu16, String ckstatu17, String ckstatu18, String ckstatu19, String ckstatu20, String ckstatu21, String ckstatu22, String ckstatu23, String ckstatu24, String ckstatu25, String ckstatu26, String ckstatu27, String ckstatu28, String ckstatu29, String ckstatu30, String ckstatu31) {
        this.id = id;
        this.studentId = studentId;
        this.studentName = studentName;
        this.ckstatu1 = ckstatu1;
        this.ckstatu2 = ckstatu2;
        this.ckstatu3 = ckstatu3;
        this.ckstatu4 = ckstatu4;
        this.ckstatu5 = ckstatu5;
        this.ckstatu6 = ckstatu6;
        this.ckstatu7 = ckstatu7;
        this.ckstatu8 = ckstatu8;
        this.ckstatu9 = ckstatu9;
        this.ckstatu10 = ckstatu10;
        this.ckstatu11 = ckstatu11;
        this.ckstatu12 = ckstatu12;
        this.ckstatu13 = ckstatu13;
        this.ckstatu14 = ckstatu14;
        this.ckstatu15 = ckstatu15;
        this.ckstatu16 = ckstatu16;
        this.ckstatu17 = ckstatu17;
        this.ckstatu18 = ckstatu18;
        this.ckstatu19 = ckstatu19;
        this.ckstatu20 = ckstatu20;
        this.ckstatu21 = ckstatu21;
        this.ckstatu22 = ckstatu22;
        this.ckstatu23 = ckstatu23;
        this.ckstatu24 = ckstatu24;
        this.ckstatu25 = ckstatu25;
        this.ckstatu26 = ckstatu26;
        this.ckstatu27 = ckstatu27;
        this.ckstatu28 = ckstatu28;
        this.ckstatu29 = ckstatu29;
        this.ckstatu30 = ckstatu30;
        this.ckstatu31 = ckstatu31;
    }

    /**
     * @description 小月的构造
     */
    public WorkExcelByMonth(int id, String studentId, String studentName, String ckstatu1, String ckstatu2, String ckstatu3, String ckstatu4, String ckstatu5, String ckstatu6, String ckstatu7, String ckstatu8, String ckstatu9, String ckstatu10, String ckstatu11, String ckstatu12, String ckstatu13, String ckstatu14, String ckstatu15, String ckstatu16, String ckstatu17, String ckstatu18, String ckstatu19, String ckstatu20, String ckstatu21, String ckstatu22, String ckstatu23, String ckstatu24, String ckstatu25, String ckstatu26, String ckstatu27, String ckstatu28, String ckstatu29, String ckstatu30) {
        this.id = id;
        this.studentId = studentId;
        this.studentName = studentName;
        this.ckstatu1 = ckstatu1;
        this.ckstatu2 = ckstatu2;
        this.ckstatu3 = ckstatu3;
        this.ckstatu4 = ckstatu4;
        this.ckstatu5 = ckstatu5;
        this.ckstatu6 = ckstatu6;
        this.ckstatu7 = ckstatu7;
        this.ckstatu8 = ckstatu8;
        this.ckstatu9 = ckstatu9;
        this.ckstatu10 = ckstatu10;
        this.ckstatu11 = ckstatu11;
        this.ckstatu12 = ckstatu12;
        this.ckstatu13 = ckstatu13;
        this.ckstatu14 = ckstatu14;
        this.ckstatu15 = ckstatu15;
        this.ckstatu16 = ckstatu16;
        this.ckstatu17 = ckstatu17;
        this.ckstatu18 = ckstatu18;
        this.ckstatu19 = ckstatu19;
        this.ckstatu20 = ckstatu20;
        this.ckstatu21 = ckstatu21;
        this.ckstatu22 = ckstatu22;
        this.ckstatu23 = ckstatu23;
        this.ckstatu24 = ckstatu24;
        this.ckstatu25 = ckstatu25;
        this.ckstatu26 = ckstatu26;
        this.ckstatu27 = ckstatu27;
        this.ckstatu28 = ckstatu28;
        this.ckstatu29 = ckstatu29;
        this.ckstatu30 = ckstatu30;
    }

    /**
     * @description 闰年2月的构造
     */
    public WorkExcelByMonth(int id, String studentId, String studentName, String ckstatu1, String ckstatu2, String ckstatu3, String ckstatu4, String ckstatu5, String ckstatu6, String ckstatu7, String ckstatu8, String ckstatu9, String ckstatu10, String ckstatu11, String ckstatu12, String ckstatu13, String ckstatu14, String ckstatu15, String ckstatu16, String ckstatu17, String ckstatu18, String ckstatu19, String ckstatu20, String ckstatu21, String ckstatu22, String ckstatu23, String ckstatu24, String ckstatu25, String ckstatu26, String ckstatu27, String ckstatu28, String ckstatu29) {
        this.id = id;
        this.studentId = studentId;
        this.studentName = studentName;
        this.ckstatu1 = ckstatu1;
        this.ckstatu2 = ckstatu2;
        this.ckstatu3 = ckstatu3;
        this.ckstatu4 = ckstatu4;
        this.ckstatu5 = ckstatu5;
        this.ckstatu6 = ckstatu6;
        this.ckstatu7 = ckstatu7;
        this.ckstatu8 = ckstatu8;
        this.ckstatu9 = ckstatu9;
        this.ckstatu10 = ckstatu10;
        this.ckstatu11 = ckstatu11;
        this.ckstatu12 = ckstatu12;
        this.ckstatu13 = ckstatu13;
        this.ckstatu14 = ckstatu14;
        this.ckstatu15 = ckstatu15;
        this.ckstatu16 = ckstatu16;
        this.ckstatu17 = ckstatu17;
        this.ckstatu18 = ckstatu18;
        this.ckstatu19 = ckstatu19;
        this.ckstatu20 = ckstatu20;
        this.ckstatu21 = ckstatu21;
        this.ckstatu22 = ckstatu22;
        this.ckstatu23 = ckstatu23;
        this.ckstatu24 = ckstatu24;
        this.ckstatu25 = ckstatu25;
        this.ckstatu26 = ckstatu26;
        this.ckstatu27 = ckstatu27;
        this.ckstatu28 = ckstatu28;
        this.ckstatu29 = ckstatu29;
    }

    /**
     * @description 平年2月的构造
     */
    public WorkExcelByMonth(int id, String studentId, String studentName, String ckstatu1, String ckstatu2, String ckstatu3, String ckstatu4, String ckstatu5, String ckstatu6, String ckstatu7, String ckstatu8, String ckstatu9, String ckstatu10, String ckstatu11, String ckstatu12, String ckstatu13, String ckstatu14, String ckstatu15, String ckstatu16, String ckstatu17, String ckstatu18, String ckstatu19, String ckstatu20, String ckstatu21, String ckstatu22, String ckstatu23, String ckstatu24, String ckstatu25, String ckstatu26, String ckstatu27, String ckstatu28) {
        this.id = id;
        this.studentId = studentId;
        this.studentName = studentName;
        this.ckstatu1 = ckstatu1;
        this.ckstatu2 = ckstatu2;
        this.ckstatu3 = ckstatu3;
        this.ckstatu4 = ckstatu4;
        this.ckstatu5 = ckstatu5;
        this.ckstatu6 = ckstatu6;
        this.ckstatu7 = ckstatu7;
        this.ckstatu8 = ckstatu8;
        this.ckstatu9 = ckstatu9;
        this.ckstatu10 = ckstatu10;
        this.ckstatu11 = ckstatu11;
        this.ckstatu12 = ckstatu12;
        this.ckstatu13 = ckstatu13;
        this.ckstatu14 = ckstatu14;
        this.ckstatu15 = ckstatu15;
        this.ckstatu16 = ckstatu16;
        this.ckstatu17 = ckstatu17;
        this.ckstatu18 = ckstatu18;
        this.ckstatu19 = ckstatu19;
        this.ckstatu20 = ckstatu20;
        this.ckstatu21 = ckstatu21;
        this.ckstatu22 = ckstatu22;
        this.ckstatu23 = ckstatu23;
        this.ckstatu24 = ckstatu24;
        this.ckstatu25 = ckstatu25;
        this.ckstatu26 = ckstatu26;
        this.ckstatu27 = ckstatu27;
        this.ckstatu28 = ckstatu28;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getCkstatu1() {
        return ckstatu1;
    }

    public void setCkstatu1(String ckstatu1) {
        this.ckstatu1 = ckstatu1;
    }

    public String getCkstatu2() {
        return ckstatu2;
    }

    public void setCkstatu2(String ckstatu2) {
        this.ckstatu2 = ckstatu2;
    }

    public String getCkstatu3() {
        return ckstatu3;
    }

    public void setCkstatu3(String ckstatu3) {
        this.ckstatu3 = ckstatu3;
    }

    public String getCkstatu4() {
        return ckstatu4;
    }

    public void setCkstatu4(String ckstatu4) {
        this.ckstatu4 = ckstatu4;
    }

    public String getCkstatu5() {
        return ckstatu5;
    }

    public void setCkstatu5(String ckstatu5) {
        this.ckstatu5 = ckstatu5;
    }

    public String getCkstatu6() {
        return ckstatu6;
    }

    public void setCkstatu6(String ckstatu6) {
        this.ckstatu6 = ckstatu6;
    }

    public String getCkstatu7() {
        return ckstatu7;
    }

    public void setCkstatu7(String ckstatu7) {
        this.ckstatu7 = ckstatu7;
    }

    public String getCkstatu8() {
        return ckstatu8;
    }

    public void setCkstatu8(String ckstatu8) {
        this.ckstatu8 = ckstatu8;
    }

    public String getCkstatu9() {
        return ckstatu9;
    }

    public void setCkstatu9(String ckstatu9) {
        this.ckstatu9 = ckstatu9;
    }

    public String getCkstatu10() {
        return ckstatu10;
    }

    public void setCkstatu10(String ckstatu10) {
        this.ckstatu10 = ckstatu10;
    }

    public String getCkstatu11() {
        return ckstatu11;
    }

    public void setCkstatu11(String ckstatu11) {
        this.ckstatu11 = ckstatu11;
    }

    public String getCkstatu12() {
        return ckstatu12;
    }

    public void setCkstatu12(String ckstatu12) {
        this.ckstatu12 = ckstatu12;
    }

    public String getCkstatu13() {
        return ckstatu13;
    }

    public void setCkstatu13(String ckstatu13) {
        this.ckstatu13 = ckstatu13;
    }

    public String getCkstatu14() {
        return ckstatu14;
    }

    public void setCkstatu14(String ckstatu14) {
        this.ckstatu14 = ckstatu14;
    }

    public String getCkstatu15() {
        return ckstatu15;
    }

    public void setCkstatu15(String ckstatu15) {
        this.ckstatu15 = ckstatu15;
    }

    public String getCkstatu16() {
        return ckstatu16;
    }

    public void setCkstatu16(String ckstatu16) {
        this.ckstatu16 = ckstatu16;
    }

    public String getCkstatu17() {
        return ckstatu17;
    }

    public void setCkstatu17(String ckstatu17) {
        this.ckstatu17 = ckstatu17;
    }

    public String getCkstatu18() {
        return ckstatu18;
    }

    public void setCkstatu18(String ckstatu18) {
        this.ckstatu18 = ckstatu18;
    }

    public String getCkstatu19() {
        return ckstatu19;
    }

    public void setCkstatu19(String ckstatu19) {
        this.ckstatu19 = ckstatu19;
    }

    public String getCkstatu20() {
        return ckstatu20;
    }

    public void setCkstatu20(String ckstatu20) {
        this.ckstatu20 = ckstatu20;
    }

    public String getCkstatu21() {
        return ckstatu21;
    }

    public void setCkstatu21(String ckstatu21) {
        this.ckstatu21 = ckstatu21;
    }

    public String getCkstatu22() {
        return ckstatu22;
    }

    public void setCkstatu22(String ckstatu22) {
        this.ckstatu22 = ckstatu22;
    }

    public String getCkstatu23() {
        return ckstatu23;
    }

    public void setCkstatu23(String ckstatu23) {
        this.ckstatu23 = ckstatu23;
    }

    public String getCkstatu24() {
        return ckstatu24;
    }

    public void setCkstatu24(String ckstatu24) {
        this.ckstatu24 = ckstatu24;
    }

    public String getCkstatu25() {
        return ckstatu25;
    }

    public void setCkstatu25(String ckstatu25) {
        this.ckstatu25 = ckstatu25;
    }

    public String getCkstatu26() {
        return ckstatu26;
    }

    public void setCkstatu26(String ckstatu26) {
        this.ckstatu26 = ckstatu26;
    }

    public String getCkstatu27() {
        return ckstatu27;
    }

    public void setCkstatu27(String ckstatu27) {
        this.ckstatu27 = ckstatu27;
    }

    public String getCkstatu28() {
        return ckstatu28;
    }

    public void setCkstatu28(String ckstatu28) {
        this.ckstatu28 = ckstatu28;
    }

    public String getCkstatu29() {
        return ckstatu29;
    }

    public void setCkstatu29(String ckstatu29) {
        this.ckstatu29 = ckstatu29;
    }

    public String getCkstatu30() {
        return ckstatu30;
    }

    public void setCkstatu30(String ckstatu30) {
        this.ckstatu30 = ckstatu30;
    }

    public String getCkstatu31() {
        return ckstatu31;
    }

    public void setCkstatu31(String ckstatu31) {
        this.ckstatu31 = ckstatu31;
    }

    /**
     * 表头信息列表
     */
    public static List<List<String>> head() {
        String month = ExcelWorkServlet2.mon.substring(5, 7);
        int date = 31;
        int year = Integer.parseInt(ExcelWorkServlet2.mon.substring(0, 4));
        if ("04".equals(month) || "06".equals(month) || "09".equals(month) || "11".equals(month)) {
            date = 30;
        } else if ("02".equals(month)) {
            if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
                // 闰年2月29天
                date = 29;
            } else {
                // 平年2月28天
                date = 28;
            }
        }
        List<String> head = new ArrayList<>();
        head.add(ExcelWorkServlet2.mon + "作业完成情况学生信息表");
        head.add("编号");
        head.add(ExcelWorkServlet2.mon + "作业完成情况学生信息表");
        head.add("学号");
        head.add(ExcelWorkServlet2.mon + "作业完成情况学生信息表");
        head.add("姓名");
        for (int i = 0; i < date; i++) {
            head.add(ExcelWorkServlet2.mon + "作业完成情况学生信息表");
            head.add(ExcelWorkServlet2.mon + "-" + (i + 1));
        }
        List<List<String>> list = groupList(head);
        return list;
    }

    /**
     * 一个集合分多个集合
     */
    public static List<List<String>> groupList(List<String> list) {
        List<List<String>> listGroup = new ArrayList<>();
        int listSize = list.size();
        // 子集合的长度
        int toIndex = 2;
        for (int i = 0; i < list.size(); i += 2) {
            if (i + 2 > listSize) {
                toIndex = listSize - i;
            }
            List<String> newList = list.subList(i, i + toIndex);
            listGroup.add(newList);
        }
        return listGroup;
    }

}
