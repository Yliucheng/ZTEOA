package ZTE.listener;

public class OnlineCounter {
    //在线人数
    private static Integer online=0;

    public static Integer getOnline() {
        return online;
    }

    public static void setOnline(Integer online) {
        OnlineCounter.online = online;
    }

    public static void raise() {
        online++;
    }

    public static void reduce() {
        if (online<1){
            online=1;
        }else {
            online--;
        }
    }
}
