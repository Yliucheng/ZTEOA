package ZTE.utils.excel.lisitener;

import ZTE.entity.exam.ExamUpload;
import ZTE.service.exam.ExamService;
import ZTE.service.exam.ExamServiceImpl;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

public class ExamListener extends AnalysisEventListener<ExamUpload> {
    ExamService examService = new ExamServiceImpl();
    @Override
    public void invoke(ExamUpload examUpload, AnalysisContext analysisContext) {
        ExamUpload upload = new ExamUpload(examUpload.getStudentId(),
                    examUpload.getStudentName(),
                    examUpload.getExamDate(),
                    examUpload.getExamType(),
                    examUpload.getStudentResult()
            );
            examService.addExamResult(upload);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
