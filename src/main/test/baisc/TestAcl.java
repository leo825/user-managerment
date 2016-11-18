package baisc;

import org.junit.Test;
import sys.auth.model.Acl;


public class TestAcl {
	
	@Test
	public void testSetPermission() {
		int num = 7;
		Acl acl = new Acl();
		acl.setAclState(num);
		acl.setPermission(1, false);
		System.out.println(acl.getAclState());
		
		System.out.println(acl.checkPermission(1));
	}
}
