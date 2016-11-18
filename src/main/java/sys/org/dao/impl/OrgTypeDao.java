package sys.org.dao.impl;

import org.springframework.stereotype.Repository;
import sys.basic.dao.BaseDao;
import sys.dto.OrgTypeDto;
import sys.dto.OrgTypeRuleDto;
import sys.org.dao.IOrgTypeDao;
import sys.org.model.OrgType;
import sys.org.model.OrgTypeRule;

import java.util.ArrayList;
import java.util.List;

@Repository("orgTypeDao")
public class OrgTypeDao extends BaseDao<OrgType> implements IOrgTypeDao {


    @Override
    public List<OrgType> list() {
        String hql = "select ot from OrgType ot";
        return super.list(hql);
    }

    @Override
    public void addOrgTypeRule(int pid, int cid, int num) {
        OrgTypeRule otr = new OrgTypeRule();
        otr.setPid(pid);
        otr.setCid(cid);
        otr.setNum(num);
        super.addEntity(otr);
    }

    @Override
    public void deleteOrgTypeRule(int pid, int cid) {
        String hql = "delete OrgTypeRule otr where otr.cid=? and otr.pid=?";
        super.updateByHql(hql, cid, pid);
    }

    @Override
    public void updateOrgTypeRule(int pid, int cid, int num) {
        String hql = "update OrgTypeRule otr set otr.num=? where otr.cid=? and otr.pid=?";
        super.updateByHql(hql, num, cid, pid);
    }

    @Override
    public List<OrgType> listByRule(int pid) {
        String hql = "select otr from OrgTypeRule otr where otr.parent.id=?";
        return super.listObj(hql, pid);
    }

    @Override
    public int loadOrgRuleNum(int pid, int cid) {
        String hql = "select ort.num from OrgTypeRule ort where ort.cid=? and ort.pid=?";
        return (Integer) super.queryObject(hql, cid, pid);
    }

    @Override
    public OrgType loadBySn(String sn) {
        return (OrgType) super.loadBySn(sn, OrgType.class.getName());
    }

    @Override
    public List<OrgTypeDto> listChildType(int pid) {
        String hql = "select new OrgTypeDto(ot2.id,ot2.name,otr.num) from " +
                "OrgType ot,OrgTypeRule otr,OrgType ot2 " +
                "where ot.id=? and ot.id=otr.pid and ot2.id=otr.cid";
        return super.listObj(hql, pid);
    }

    @Override
    public List<OrgTypeRuleDto> listOrgTypeRuleByOrg(int id) {
        List<OrgTypeDto> otds = this.listChildType(id);
        List<OrgTypeRuleDto> ortds = new ArrayList<OrgTypeRuleDto>();
        List<Integer> aids = new ArrayList<Integer>();
        OrgTypeRuleDto otrd;
        //把存在的添加进行
        for (OrgTypeDto otd : otds) {
            otrd = new OrgTypeRuleDto();
            otrd.setCid(otd.getCid());
            otrd.setCname(otd.getCname());
            otrd.setExists(true);
            otrd.setNum(otd.getNum());
            ortds.add(otrd);
            aids.add(otd.getCid());
        }
        List<OrgType> ots = this.list();
        for (OrgType ot : ots) {
            if (ot.getId() == id) continue;
            if (aids.contains(ot.getId())) continue;
            otrd = new OrgTypeRuleDto();
            otrd.setCid(ot.getId());
            otrd.setCname(ot.getName());
            otrd.setExists(false);
            otrd.setNum(0);
            ortds.add(otrd);
        }
        return ortds;
    }

}
