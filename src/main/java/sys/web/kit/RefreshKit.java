package sys.web.kit;

import java.util.List;

import javax.servlet.ServletContext;

import sys.auth.service.IMenuResService;
import sys.dto.LeftMenuDto;
import sys.web.context.BeanFactoryContext;

public class RefreshKit {
    public static void refreshLeftMenu(ServletContext ctx) {
        IMenuResService menuResService = (IMenuResService) BeanFactoryContext.getService("menuResService");
        List<LeftMenuDto> mds = menuResService.listLeftNav();
        ctx.setAttribute("leftMenus", mds);
    }
}
