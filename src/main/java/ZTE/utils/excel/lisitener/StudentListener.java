package ZTE.utils.excel.lisitener;

import ZTE.entity.jiaowuqing.Student;
import ZTE.service.jiaowuqing.student.StudentService;
import ZTE.service.jiaowuqing.student.StudentServiceImpl;
import ZTE.utils.excel.student.StudentData;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;


/**
 * 读取内容监听器
 */
public class StudentListener extends AnalysisEventListener<StudentData> {
    StudentService studentService = new StudentServiceImpl();
    /**
     * 每读一行内容，都会调用一次invoke方法，在invoke可以操作使用读取到的数据
     * @param studentData 每次读取封装对象
     * @param analysisContext
     */
    @Override
    public void invoke(StudentData studentData, AnalysisContext analysisContext) {
        Student student = new Student(studentData.getStudentName(),
                    studentData.getClassId(),
                    studentData.getFromSchool(),
                    studentData.getEducation(),
                    studentData.getLogincode(),
                    studentData.getPassword()
                    );
        studentService.addStudent(student);
        System.out.println(studentData);
    }

    /**
     * 读取完整个文档之后的方法
     * @param analysisContext
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
