package ZTE.entity.lidelin;

/**
 * @ClassName WorkResult
 * @Description 作业表现对象
 * @Version 1.0
 */
public class WorkResult {

    private String ckstatu; // 作业完成表现

    public WorkResult(String ckstatu) {
        this.ckstatu = ckstatu;
    }

    public String getCkstatu() {
        return ckstatu;
    }

    public void setCkstatu(String ckstatu) {
        this.ckstatu = ckstatu;
    }

}
