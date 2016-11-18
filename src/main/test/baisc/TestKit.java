package baisc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;
import sys.auth.model.MenuResources;
import sys.kit.BasicSysKit;


public class TestKit {

	@Test
	public void testBraceStr2List() {
		String str = "(22)(12)(2)";
		System.out.println(BasicSysKit.braceStr2List(str));
	}
	
	@Test
	public void testReplace() {
		String str = "(22)";
		System.out.println(str.replace("(22)", "(33)"));
	}
	
	@Test
	public void testMenuOrder() {
		List<MenuResources> mrs = new ArrayList<MenuResources>();
		MenuResources mr1 = new MenuResources();
		mr1.setName("aaa"); mr1.setOrderNum(12); mrs.add(mr1);
		mr1 = new MenuResources();
		mr1.setName("bbb"); mr1.setOrderNum(13); mrs.add(mr1);
		mr1 = new MenuResources();
		mr1.setName("ccc"); mr1.setOrderNum(5); mrs.add(mr1);
		mr1 = new MenuResources();
		mr1.setName("dddd"); mr1.setOrderNum(122); mrs.add(mr1);
		mr1 = new MenuResources();
		mr1.setName("eeee"); mr1.setOrderNum(6); mrs.add(mr1);
		
		Collections.sort(mrs);
		
		for(MenuResources m:mrs) {
			System.out.println(m.getName());
		}
	}
}
