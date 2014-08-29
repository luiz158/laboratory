package org.cruxframework.crux.core.client.controller;

import com.google.gwt.core.client.GWT;
import org.cruxframework.crux.core.client.screen.Screen;
import com.google.gwt.core.client.RunAsyncCallback;
import org.cruxframework.crux.core.client.Crux;
import org.cruxframework.crux.core.client.collection.FastMap;
import org.cruxframework.crux.core.client.utils.StringUtils;

public class RegisteredControllers_addbookmark_largeDisplayMouse implements org.cruxframework.crux.core.client.controller.RegisteredControllers {
  public RegisteredControllers_addbookmark_largeDisplayMouse(org.cruxframework.crux.core.client.screen.views.View view, org.cruxframework.crux.core.client.ioc.IocContainer_addbookmark_largeDisplayMouse iocContainer){
    this.view = view;
    this.iocContainer = iocContainer;
  }
  public <T> T getController(String controller){
    T ret = (T)controllers.get(controller);
    if (ret == null){
      if (org.cruxframework.crux.core.client.utils.StringUtils.unsafeEquals(controller, "addBookmarkController")){
        org.cruxframework.bookmark.client.controller.AddBookmarkController_ControllerProxy __cont  = new org.cruxframework.bookmark.client.controller.AddBookmarkController_ControllerProxy(this.view);
        __cont.bookmarkRest = iocContainer.getorg_cruxframework_bookmark_client_service_BookmarkRestClient(org.cruxframework.crux.core.client.ioc.IoCResource.Scope.LOCAL, null);
        __cont.message = iocContainer.getorg_cruxframework_bookmark_client_shared_messages_CommonMessages(org.cruxframework.crux.core.client.ioc.IoCResource.Scope.LOCAL, null);
        controllers.put("addBookmarkController", __cont);
      }
      if (ret == null){
        ret = (T)controllers.get(controller);
      }
    }
    return ret;
  }
  private FastMap<Object> controllers = new FastMap<Object>();
  private org.cruxframework.crux.core.client.screen.views.View view;
  private org.cruxframework.crux.core.client.ioc.IocContainer_addbookmark_largeDisplayMouse iocContainer;
}
