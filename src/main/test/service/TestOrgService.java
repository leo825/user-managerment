package service;

import java.util.List;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.orm.hibernate4.SessionHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import sys.dto.OrgTypeDto;
import sys.dto.TreeDto;
import sys.org.service.IOrgService;
import sys.org.service.IOrgTypeService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:applicationContext.xml"})
public class TestOrgService {
	@Inject
	private IOrgService orgService;
	@Inject
	private IOrgTypeService orgTypeService;
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
	public void testDeleteOrgTypeRule() {
		orgTypeService.deleteOrgTypeRule(1, 3);
	}
	
	@Test
	public void testListChildByType() {
		List<OrgTypeDto> otds = orgTypeService.listChildType(3);
		System.out.println(otds);
	}
	
	@Test
	public void testListParentByType() {
		List<TreeDto> tds = orgService.listParentTreeByOrgType("BJ");
		for(TreeDto td:tds) {
			System.out.println(td.getName());
		}
	}
	
	@Test
	public void testAddRule() {
//		orgService.addRule(3, 4);
//		orgService.addRule(3,11);
		orgService.addRule(3,18);
	}
	
	@Test
	public void testDelRule() {
		orgService.deleteRule(3, 18);
	}
	
	@Test
	public void testLoadChilds() {
//		List<Integer> orgIds = orgService.listAllChildIdsByOrg(4);
		List<Integer> orgIds = orgService.listAllChildIdsByOrg(3);
		System.out.println(orgIds);
	}
	
	@Test
	public void testLoadTrees() {
		List<TreeDto> tds = orgService.listAllChildTreeByOrg(0);
		System.out.println(tds);
	}
}
