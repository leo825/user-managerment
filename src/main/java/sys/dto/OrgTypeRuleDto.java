package sys.dto;

/**
 * 组织机构类型的规则dto
 *
 * @author Konghao
 */
public class OrgTypeRuleDto {
    private int cid;
    private String cname;
    private boolean exists;
    private int num;

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public boolean isExists() {
        return exists;
    }

    public void setExists(boolean exists) {
        this.exists = exists;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
