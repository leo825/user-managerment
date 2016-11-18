package service;

import java.util.List;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import sys.auth.service.IControllerResService;
import sys.auth.service.IMenuResService;
import sys.auth.model.MenuResources;
import org.springframework.orm.hibernate4.SessionHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import sys.dto.LeftMenuDto;
import sys.org.service.IInitService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:applicationContext.xml"})
public class TestInitService {
    @Inject
    private IInitService initService;
    @Inject
    private IControllerResService controllerResService;
    @Inject
    private IMenuResService menuResService;
    @Inject
    private SessionFactory sessionFactory;

    @Before
    public void setUp() {
        //此时最好不要使用Spring的Transactional来管理，因为dbunit是通过jdbc来处理connection，再使用spring在一些编辑操作中会造成事务shisu
        Session s = sessionFactory.openSession();
        TransactionSynchronizationManager.bindResource(sessionFactory, new SessionHolder(s));
        //SystemContext.setRealPath("D:\\teach_source\\class2\\j2EE\\dingan\\cms-da\\src\\main\\webapp");
    }

    @After
    public void tearDown() {
        SessionHolder holder = (SessionHolder) TransactionSynchronizationManager.getResource(sessionFactory);
        Session s = holder.getSession();
        s.flush();
        TransactionSynchronizationManager.unbindResource(sessionFactory);
    }

    @Test
    public void testInitRes() {
        controllerResService.initControllerRes(new String[]{"org.konghao.sys.web.controller"});
    }

    @Test
    public void testInitMenuRes() {
        menuResService.initMenuResources(new String[]{"org.konghao.sys.web.controller"});
    }

    @Test
    public void testGetLeftNavMenu() {
        List<LeftMenuDto> mds = menuResService.listLeftNav();
        for (LeftMenuDto md : mds) {
            System.out.println(md.getParent().getName() + "," + md.getChilds().size());
            for (MenuResources mr : md.getChilds()) {
                System.out.println("---->" + mr.getName());
            }
        }
    }


    @Test
    public void testInitByXml() {
        initService.initEntityByXml("orgs.xml");
    }
}
