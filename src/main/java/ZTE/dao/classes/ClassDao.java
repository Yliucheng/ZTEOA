package ZTE.dao.classes;

import ZTE.entity.Classes;
import ZTE.entity.Major;

import java.util.ArrayList;
import java.util.List;

public interface ClassDao {

    ArrayList<Classes> getClassList(Integer majorIds, Integer currentPageNo, Integer pageSize);

    int getClassListCount(Integer majorIds);
    List<Classes> getClassList(Integer majorType);

    ArrayList<Classes> getStudyTypeAll();

    boolean addClass(String classname, int studytype);

    List<Major> getMajorList();
}
