package sys.org.dao.impl;

import org.springframework.stereotype.Repository;
import sys.basic.dao.BaseDao;
import sys.basic.model.Pager;
import sys.dto.TreeDto;
import sys.kit.BasicSysKit;

import sys.org.dao.IOrgDao;
import sys.org.model.Org;
import sys.org.model.OrgRule;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("orgDao")
public class OrgDao extends BaseDao<Org> implements IOrgDao {


    @Override
    public Pager<Org> findByParent(Integer pid, Integer typeId) {
        String hql = "select org from Org org where";
        if (pid == null) {
            hql += " org.parent is null";
        } else {
            hql += " org.parent.id=" + pid;
        }
        if (typeId != null && typeId > 0) {
            hql += " and org.typeId=" + typeId;
        }
        return super.find(hql);
    }

    @Override
    public List<TreeDto> tree() {
        String sql = "select id,name,pid from t_org";
        List<TreeDto> tds = super.listBySql(sql, TreeDto.class, false);
        return tds;
    }

    @Override
    public int loadNumByType(Integer pid, int type) {
        String hql = "select count(*) from Org where typeId=?";
        if (pid == null) {
            hql += " and parent is null";
        } else {
            hql += " and parent.id=" + pid;
        }
        return ((Long) (super.queryObject(hql, type))).intValue();
    }

    @Override
    public void addRule(int orgId, int cid) {
        OrgRule oru = this.loadManagerOrg(orgId);
        String managerOrg = null;
        if (BasicSysKit.isEmpty(oru)) {
            managerOrg = "";
            managerOrg += "(" + cid + ")";
            oru = new OrgRule();
            oru.setOrgId(orgId);
            oru.setManagerOrg(managerOrg);
            super.addEntity(oru);
            return;
        }
        managerOrg = oru.getManagerOrg();
        if (BasicSysKit.isEmpty(managerOrg)) managerOrg = "";
        if (managerOrg.indexOf("(" + cid + ")") > 0)
            return;
        managerOrg += "(" + cid + ")";
        oru.setManagerOrg(managerOrg);
        super.updateEntity(oru);
    }

    @Override
    public void deleteRule(int orgId, int cid) {
        OrgRule oru = this.loadManagerOrg(orgId);
        String managerOrg = oru.getManagerOrg();
        //(22)-->((22))(22)(33)
        managerOrg = managerOrg.replace("(" + cid + ")", "");
        oru.setManagerOrg(managerOrg);
        super.updateEntity(oru);
    }

    @Override
    public List<Integer> listAllChildIdsByOrg(int id) {
        //首先判断这个节点类型，如果是直线型，就直接返回listChildByOrg
        Org org = super.load(id);
        List<Integer> orgIds = new ArrayList<Integer>();
        switch (org.getManagerType()) {
            case OrgRule.NO_TYPE:
                break;
            case OrgRule.DEFAULT_TYPE:
                orgIds = listChildIdsByOrg(id);
                break;
            case OrgRule.ALL_TYPE:
                orgIds = listAllOrgId();
                break;
            case OrgRule.DEF_TYPE:
                List<Integer> managerIds = listManagerRuleIds(id);
                List<Org> orgs = listChildOrgByIds(managerIds);
                orgIds = orgs2OrgIds(orgs);
                break;
            default:
                break;
        }
        return orgIds;
    }

    private List<Integer> listAllOrgId() {
        String hql = "select org.id from Org org order by org.id";
        return super.listObj(hql);
    }

    /**
     * 通过几个ids获取相应的组织机构的节点
     *
     * @param ids
     * @return
     */
    private List<Org> listChildOrgByIds(List<Integer> ids) {
        List<Org> orgs = listAllOrg();
        Map<Integer, List<Org>> corgs = org2map(orgs);
        List<Org> rorgs = new ArrayList<Org>();
        for (Integer id : ids) {
            rorgs.add(this.load(id));
            getOrgByMap(corgs, id, rorgs);
        }
        return rorgs;
    }

    @Override
    public List<Integer> listChildIdsByOrg(int id) {
        /*
         * 获取某个组织机构下面的所有组织的子节点，通常使用的是递归，使用递归会反复的查询数据库，效率不高
		 * 可以考虑使用map来替代反复查询数据库的递归操作。
		 */
        //1、取出所有的数据
        List<Org> orgs = listAllOrg();
        //2、格式化为一个map
        Map<Integer, List<Org>> corgs = org2map(orgs);
        //3、通过map来获取所有的子节点数据
        List<Org> rorgs = new ArrayList<Org>();
        rorgs.add(this.load(id));
        getOrgByMap(corgs, id, rorgs);
        List<Integer> orgIds = orgs2OrgIds(rorgs);
        return orgIds;
    }

    private List<Integer> orgs2OrgIds(List<Org> orgs) {
        List<Integer> orgIds = new ArrayList<Integer>();
        for (Org to : orgs) {
            orgIds.add(to.getId());
        }
        return orgIds;
    }

    private List<Org> listAllOrg() {
        String hql = "select org from Org org left join fetch org.parent order by org.id";
        List<Org> orgs = super.list(hql);
        return orgs;
    }

    private void getOrgByMap(Map<Integer, List<Org>> orgs, int id, List<Org> corgs) {
        if (!orgs.containsKey(id)) return;
        List<Org> torgs = orgs.get(id);
        for (Org o : torgs) {
            corgs.add(o);
            //org的子节点中如果还是orgs的key，就说明该子节点中依然是某个父节点，此时就通过递归获取数据
            if (orgs.containsKey(o.getId())) {
                getOrgByMap(orgs, o.getId(), corgs);
            }
        }
    }

    private Map<Integer, List<Org>> org2map(List<Org> orgs) {
        Map<Integer, List<Org>> maps = new HashMap<Integer, List<Org>>();
        List<Org> os = null;
        for (Org o : orgs) {
            if (o.getParent() == null) {
                os = new ArrayList<Org>();
                maps.put(o.getId(), os);
            } else {
                if (maps.containsKey(o.getParent().getId())) {
                    maps.get(o.getParent().getId()).add(o);
                } else {
                    os = new ArrayList<Org>();
                    if (!os.contains(o))
                        os.add(o);
                    maps.put(o.getParent().getId(), os);
                }
            }
        }
        return maps;
    }

    private OrgRule loadManagerOrg(int orgId) {
        //1,2,4,5,12,55
        String hql = "select oru from OrgRule oru where oru.orgId=?";
        //(12)(33)(22)
        return (OrgRule) super.queryObject(hql, orgId);
    }

    public List<Integer> listManagerRuleIds(int orgId) {
        String managerOrg = loadManagerOrg(orgId).getManagerOrg();
        List<Integer> ids = BasicSysKit.braceStr2List(managerOrg);
        return ids;
    }

    @Override
    public int getMaxOrder(Integer pid) {
        return super.getMaxOrder(pid, "Org");
    }

    @Override
    public List<Org> listAllChildByOrg(int id) {
        //首先判断这个节点类型，如果是直线型，就直接返回listChildByOrg
        Org org = super.load(id);
        List<Org> orgs = new ArrayList<Org>();
        switch (org.getManagerType()) {
            case OrgRule.NO_TYPE:
                break;
            case OrgRule.DEFAULT_TYPE:
                orgs = listChildByOrg(id);
                break;
            case OrgRule.ALL_TYPE:
                orgs = listAllOrg();
                break;
            case OrgRule.DEF_TYPE:
                List<Integer> managerIds = listManagerRuleIds(id);
                orgs = listChildOrgByIds(managerIds);
                break;
            default:
                break;
        }
        return orgs;
    }

    @Override
    public List<Org> listChildByOrg(int id) {
		/*
		 * 获取某个组织机构下面的所有组织的子节点，通常使用的是递归，使用递归会反复的查询数据库，效率不高
		 * 可以考虑使用map来替代反复查询数据库的递归操作。
		 */
        //1、取出所有的数据
        List<Org> orgs = listAllOrg();
        //2、格式化为一个map
        Map<Integer, List<Org>> corgs = org2map(orgs);
        //3、通过map来获取所有的子节点数据
        List<Org> rorgs = new ArrayList<Org>();
        rorgs.add(this.load(id));
        getOrgByMap(corgs, id, rorgs);
        return rorgs;
    }

    @Override
    public List<TreeDto> listChildTreeByOrg(int id) {
        List<Org> orgs = listChildByOrg(id);
        return orgs2Trees(orgs);
    }

    private List<TreeDto> orgs2Trees(List<Org> orgs) {
        List<TreeDto> tds = new ArrayList<TreeDto>();
        TreeDto td = null;
        for (Org org : orgs) {
            if (!BasicSysKit.isEmpty(org.getParent())) {
                td = new TreeDto(org.getId(), org.getName(), org.getParent().getId());
            } else {
                td = new TreeDto(org.getId(), org.getName(), -1);
            }
            tds.add(td);
        }
        return tds;
    }

    @Override
    public List<TreeDto> listAllChildTreeByOrg(int id) {
        //首先判断这个节点类型，如果是直线型，就直接返回listChildByOrg
        Org org = super.load(id);
        List<TreeDto> tds = new ArrayList<TreeDto>();
        switch (org.getManagerType()) {
            case OrgRule.NO_TYPE:
                break;
            case OrgRule.DEFAULT_TYPE:
                tds = listChildTreeByOrg(id);
                break;
            case OrgRule.ALL_TYPE:
                tds = tree();
                break;
            case OrgRule.DEF_TYPE:
                List<Integer> managerIds = listManagerRuleIds(id);
                tds = listChildTreeByOrgs(managerIds);
                break;
            default:
                break;
        }
        return tds;
    }

    @Override
    public List<TreeDto> listChildTreeByOrgs(List<Integer> ids) {
        List<Org> orgs = this.listChildOrgByIds(ids);
        return orgs2Trees(orgs);
    }

    @Override
    public int getOrgNumsByType(int typeId) {
        String hql = "select count(*) from Org org where org.typeId=?";
        Long c = (Long) super.queryObject(hql, typeId);
        return c.intValue();
    }

    @Override
    public void addRule(int orgId, Integer[] cids) {
        StringBuffer sb = new StringBuffer("");
        for (Integer cid : cids) {
            sb.append("(").append(cid).append(")");
        }
        OrgRule oru = this.loadManagerOrg(orgId);
        if (oru == null) {
            oru = new OrgRule();
            oru.setOrgId(orgId);
            oru.setManagerOrg(sb.toString());
            super.addEntity(oru);
        } else {
            oru.setManagerOrg(sb.toString());
            super.updateEntity(oru);
        }
    }

    @Override
    public List<Org> listPersonOrg(int pid) {
        String hql = "select new Org(org.id,org.name) from Org org,PersonOrgPos pop " +
                "where org.id=pop.orgId and pop.personId=?";
        return super.list(hql, pid);
    }

    @Override
    public List<TreeDto> listParentTreeByOrgType(String sn) {
        String hql = "select org from Org org,OrgType ot,OrgTypeRule otr where ot.sn=? and ot.id=otr.cid and otr.pid=org.typeId";
        List<Org> orgs = super.list(hql, sn);
        return orgs2Trees(orgs);
    }
}
