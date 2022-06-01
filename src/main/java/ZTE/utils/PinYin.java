package ZTE.utils;


import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

public class PinYin {
    /**
     * 中文转拼音工具类
     * @param name 中文
     * @return 中文拼音
     * @throws BadHanyuPinyinOutputFormatCombination 转换异常
     */
    public static String getNameEn(String name) throws BadHanyuPinyinOutputFormatCombination {
        char[] charArray = name.toCharArray();
        StringBuilder pinyinlast = new StringBuilder();
        StringBuilder pinyin = new StringBuilder();
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        //设置大小写格式
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        boolean flag = true;
        for (int i = 0; i < charArray.length; i++) {
            String str = Character.toString(charArray[i]);
            //匹配中文,非中文转换会转换成null
            if (str.matches("[\\u4E00-\\u9FA5]+")) {
                String[] hanyuPinyinStringArray = PinyinHelper.toHanyuPinyinStringArray(charArray[i],defaultFormat);
                if(flag){
                    char[] chars = hanyuPinyinStringArray[0].toCharArray();
                    pinyinlast.append(chars);
                    pinyin=pinyin.append(pinyinlast);
                    flag = false;
                }else{
                    String pin = hanyuPinyinStringArray[0];
                    pinyin.append(pin);
                }
            } else {
                pinyin.append(charArray[i]);
            }
        }
        return pinyin.toString();
    }
}
