package ZTE.controller.lidelin.listener;

import ZTE.entity.lidelin.WorkInfo;
import ZTE.service.lidelin.student.WorkRecordService;
import ZTE.service.lidelin.student.impl.WorkRecordServiceImpl;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

public class DemoDataListener extends AnalysisEventListener<WorkInfo> {

    WorkRecordService workRecordService = new WorkRecordServiceImpl();

    @Override
    public void invoke(WorkInfo data, AnalysisContext analysisContext) {
        System.out.println(data);
        WorkInfo workInfo = new WorkInfo(data.getId(), data.getStudentName(), data.getCktime(), data.getCkstatu());
        workRecordService.importWorkInfo(workInfo);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }

}