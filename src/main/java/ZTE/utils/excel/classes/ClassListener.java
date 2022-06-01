package ZTE.utils.excel.classes;


import ZTE.service.classes.ClassService;
import ZTE.service.classes.ClassServiceImpl;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

/**
 * AnalysisEventListener 读监听器 每读一行内容，都会调用一次改对象的invke 在invoke可以操作使用读取道德数据
 * ClassImportData 用于确认每行的实体类型
 */
public class ClassListener extends AnalysisEventListener<ClassImportData> {
    ClassService classService = new ClassServiceImpl();
    @Override
    public void invoke(ClassImportData classImportData, AnalysisContext analysisContext) {
        classService.addClass(classImportData.getClassName(),classImportData.getStudyType());
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
