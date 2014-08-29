package org.cruxframework.crux.core.client.screen.views;

import com.google.gwt.core.client.GWT;
import org.cruxframework.crux.core.client.Crux;
import org.cruxframework.crux.core.client.collection.FastMap;
import org.cruxframework.crux.core.client.screen.views.ViewFactory;
import org.cruxframework.crux.core.client.utils.StringUtils;
import com.google.gwt.user.client.ui.Widget;
import org.cruxframework.crux.core.rebind.screen.widget.WidgetCreatorContext;
import org.cruxframework.crux.core.client.screen.InterfaceConfigException;

public class ViewFactory_Impl_largeDisplayMouse implements org.cruxframework.crux.core.client.screen.views.ViewFactory {
  public void createView(String name, CreateCallback callback) throws InterfaceConfigException{ 
    createView(name, name, callback);
  }
  
  public void createView(String name, String id, CreateCallback callback) throws InterfaceConfigException{ 
    if (callback == null){
      callback = CreateCallback.EMPTY_CALLBACK;
    }
    if (StringUtils.unsafeEquals(name, "bookmark/index.html")){
      callback.onViewCreated(new org.cruxframework.crux.core.client.screen.views.bookmark_index_html_largeDisplayMouse(id));
      org.cruxframework.crux.core.client.screen.DisplayHandler.configureViewport("user-scalable=no, width=device-width, height=device-height");
    }
    else if (StringUtils.unsafeEquals(name, "listbookmark")){
      callback.onViewCreated(new org.cruxframework.crux.core.client.screen.views.listbookmark_largeDisplayMouse(id));
    }
    else if (StringUtils.unsafeEquals(name, "addbookmark")){
      callback.onViewCreated(new org.cruxframework.crux.core.client.screen.views.addbookmark_largeDisplayMouse(id));
    }
    else if (StringUtils.unsafeEquals(name, "menu")){
      callback.onViewCreated(new org.cruxframework.crux.core.client.screen.views.menu_largeDisplayMouse(id));
    }
    else if (StringUtils.unsafeEquals(name, "homebookmark")){
      callback.onViewCreated(new org.cruxframework.crux.core.client.screen.views.homebookmark_largeDisplayMouse(id));
    }
    else 
    throw new InterfaceConfigException("View ["+name+"] was not found. Check if you import it using useView attribute.");
  }
  public org.cruxframework.crux.core.client.screen.DeviceAdaptive.Device getCurrentDevice(){ 
    return org.cruxframework.crux.core.client.screen.DeviceAdaptive.Device.largeDisplayMouse;
  }
}
