package ZTE.utils.excel.student;

import lombok.Data;

/**
 * 创建学生表格上传模版
 */
@Data
public class StudentData {

    private String studentName;

    private Integer classId;

    private String fromSchool;

    private String education;

    private String logincode;

    private String password;

}
