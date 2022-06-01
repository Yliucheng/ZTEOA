package ZTE.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 考试工具类
 */
public class ExamUtil {
    public static Map<Integer,String> majorMap = new HashMap<>();
    static {
        majorMap.put(1,"晨考");
        majorMap.put(2,"周考");
    }

    /**
     * 根据传入的map集合和值获取对应的键
     * @param map
     * @param value
     * @return
     * @param <K>
     * @param <V>
     */
    public static <K,V> K getKey(Map<K,V> map,V value){
        for (Map.Entry<K, V> kvEntry : map.entrySet()) {
            if(kvEntry.getValue().equals(value)){
                return kvEntry.getKey();
            }
        }
        return null;
    }

    public static List<String> getResultList() {
        List<String> resultList = new ArrayList<>();
        resultList.add("差");
        resultList.add("中等");
        resultList.add("好");
        resultList.add("非常好");
        return resultList;
    }
public static List<String> getAttStatusList(){
    List<String> attStatusList = new ArrayList<>();
    attStatusList.add("正常");
    attStatusList.add("迟到");
    attStatusList.add("早退");
    attStatusList.add("旷课");
    return attStatusList;
}
    public static String transMajorIdToName(int studyType) {
        String major = null;
        switch (studyType) {
            case 1:
                major = "java";
                break;
            case 2:
                major = "web";
                break;
            case 3:
                major = "UI";
                break;
            default:
                break;
        }
        return major;
    }

    public static String tansExamTypeIdToName(Integer examType) {
        String examName = null;
        switch (examType) {
            case 1:
                examName = "晨考";
                break;
            case 2:
                examName = "周考";
                break;
            default:
                break;
        }
        return examName;
    }
    public static Integer getScore(String result){
        Map<String,Integer> scoreMap = new HashMap<>();
        scoreMap.put("非常好",90);
        scoreMap.put("好",80);
        scoreMap.put("中等",70);
        scoreMap.put("差",59);
        Integer score = scoreMap.get(result);
        return score;
    }
}
