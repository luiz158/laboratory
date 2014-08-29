package org.cruxframework.crux.core.client.ioc;

import org.cruxframework.crux.core.client.screen.views.View;
import com.google.gwt.core.client.GWT;

public class IocContainer_listbookmark_largeDisplayMouse extends org.cruxframework.crux.core.client.ioc.IocContainer {
  public IocContainer_listbookmark_largeDisplayMouse(View view){
    super(view);
  }
  public  org.cruxframework.bookmark.client.controller.ListBookmarkController.GridView getorg_cruxframework_bookmark_client_controller_ListBookmarkController_GridView(org.cruxframework.crux.core.client.ioc.IoCResource.Scope scope, String subscope){
    org.cruxframework.bookmark.client.controller.ListBookmarkController.GridView result = _getScope(scope).getValue(new org.cruxframework.crux.core.client.ioc.IocProvider<org.cruxframework.bookmark.client.controller.ListBookmarkController.GridView>(){
      public org.cruxframework.bookmark.client.controller.ListBookmarkController.GridView get(){
        return GWT.create(org.cruxframework.bookmark.client.controller.ListBookmarkController.GridView.class);
      }
    }, "org.cruxframework.bookmark.client.controller.ListBookmarkController.GridView", subscope, 
    new IocScope.CreateCallback<org.cruxframework.bookmark.client.controller.ListBookmarkController.GridView>(){
      public void onCreate(org.cruxframework.bookmark.client.controller.ListBookmarkController.GridView newObject){
      }
    }
    );
    if (scope != org.cruxframework.crux.core.client.ioc.IoCResource.Scope.SINGLETON && result.getBoundCruxViewId() == null){
      result.bindCruxView(this.getBoundCruxViewId());
    }
    return result;
  }
  public  org.cruxframework.bookmark.client.service.BookmarkRestClient getorg_cruxframework_bookmark_client_service_BookmarkRestClient(org.cruxframework.crux.core.client.ioc.IoCResource.Scope scope, String subscope){
    org.cruxframework.bookmark.client.service.BookmarkRestClient result = _getScope(scope).getValue(new org.cruxframework.crux.core.client.ioc.IocProvider<org.cruxframework.bookmark.client.service.BookmarkRestClient>(){
      public org.cruxframework.bookmark.client.service.BookmarkRestClient get(){
        return GWT.create(org.cruxframework.bookmark.client.service.BookmarkRestClient.class);
      }
    }, "org.cruxframework.bookmark.client.service.BookmarkRestClient", subscope, 
    new IocScope.CreateCallback<org.cruxframework.bookmark.client.service.BookmarkRestClient>(){
      public void onCreate(org.cruxframework.bookmark.client.service.BookmarkRestClient newObject){
      }
    }
    );
    if (scope != org.cruxframework.crux.core.client.ioc.IoCResource.Scope.SINGLETON && result.getBoundCruxViewId() == null){
      result.bindCruxView(this.getBoundCruxViewId());
    }
    return result;
  }
}
