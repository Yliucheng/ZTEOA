package ZTE.dao.jiaowuqing.classes;

import ZTE.entity.jiaowuqing.Classes;

import java.util.List;

public interface ClassDao {
    /**
     * 查询所有zteclassinfo表中班级列表集合
     * @return 返回所有班级列表的集合
     */
    List<Classes> getClassList();
    /**
     * 根据专业id查询zteclassinfo表中对应条件的班级列表集合
     * @param majorId Integer 专业id
     * @return 返回查询班级列表的集合
     */
    List<Classes> getClassListByMajorId(Integer majorId);


}
