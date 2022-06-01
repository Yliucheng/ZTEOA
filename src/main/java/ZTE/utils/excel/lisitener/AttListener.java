package ZTE.utils.excel.lisitener;

import ZTE.entity.att.AttUpload;
import ZTE.service.att.AttService;
import ZTE.service.att.AttServiceImpl;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

public class AttListener extends AnalysisEventListener<AttUpload> {
    AttService attService = new AttServiceImpl();
    @Override
    public void invoke(AttUpload attUpload, AnalysisContext analysisContext) {
        AttUpload upload = new AttUpload(attUpload.getStudentNo(),
                attUpload.getStudentName(),
                attUpload.getCkTime(),
                attUpload.getCkstatu()
        );
        attService.addArr(upload);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
