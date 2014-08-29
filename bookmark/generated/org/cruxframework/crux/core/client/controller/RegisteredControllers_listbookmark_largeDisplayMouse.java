package org.cruxframework.crux.core.client.controller;

import com.google.gwt.core.client.GWT;
import org.cruxframework.crux.core.client.screen.Screen;
import com.google.gwt.core.client.RunAsyncCallback;
import org.cruxframework.crux.core.client.Crux;
import org.cruxframework.crux.core.client.collection.FastMap;
import org.cruxframework.crux.core.client.utils.StringUtils;

public class RegisteredControllers_listbookmark_largeDisplayMouse implements org.cruxframework.crux.core.client.controller.RegisteredControllers {
  public RegisteredControllers_listbookmark_largeDisplayMouse(org.cruxframework.crux.core.client.screen.views.View view, org.cruxframework.crux.core.client.ioc.IocContainer_listbookmark_largeDisplayMouse iocContainer){
    this.view = view;
    this.iocContainer = iocContainer;
  }
  public <T> T getController(String controller){
    T ret = (T)controllers.get(controller);
    if (ret == null){
      if (org.cruxframework.crux.core.client.utils.StringUtils.unsafeEquals(controller, "listBookmarkController")){
        org.cruxframework.bookmark.client.controller.ListBookmarkController_ControllerProxy __cont  = new org.cruxframework.bookmark.client.controller.ListBookmarkController_ControllerProxy(this.view);
        __cont.gridView = iocContainer.getorg_cruxframework_bookmark_client_controller_ListBookmarkController_GridView(org.cruxframework.crux.core.client.ioc.IoCResource.Scope.LOCAL, null);
        __cont.bookmarkRest = iocContainer.getorg_cruxframework_bookmark_client_service_BookmarkRestClient(org.cruxframework.crux.core.client.ioc.IoCResource.Scope.LOCAL, null);
        controllers.put("listBookmarkController", __cont);
      }
      if (ret == null){
        ret = (T)controllers.get(controller);
      }
    }
    return ret;
  }
  private FastMap<Object> controllers = new FastMap<Object>();
  private org.cruxframework.crux.core.client.screen.views.View view;
  private org.cruxframework.crux.core.client.ioc.IocContainer_listbookmark_largeDisplayMouse iocContainer;
}
