package music.util;

/**
 * @author D
 */
public class CheckEmpty {
    public static boolean checkEmpty(String str){
        if(str.equals("")){
            return true;
        }
        return false;
    }
    public static boolean checkEmpty(String ... strs){
        if(strs == null || strs.length <=0) {
            return true;
        }
        for(String str : strs) {
            if(str == null || "".contentEquals(str)){
                return true;
            }
        }
        return false;
    }
}
