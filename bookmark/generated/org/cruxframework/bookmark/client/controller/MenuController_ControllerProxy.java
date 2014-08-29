package org.cruxframework.bookmark.client.controller;

import com.google.gwt.core.client.GWT;
import org.cruxframework.crux.core.client.screen.Screen;
import org.cruxframework.crux.core.client.collection.FastMap;
import org.cruxframework.crux.core.client.event.BaseEvent;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HasText;
import org.cruxframework.crux.core.client.formatter.HasFormatter;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.core.client.RunAsyncCallback;
import org.cruxframework.crux.core.client.Crux;
import java.util.logging.Logger;
import com.google.gwt.logging.client.LogConfiguration;
import java.util.logging.Level;

public class MenuController_ControllerProxy extends org.cruxframework.bookmark.client.controller.MenuController implements org.cruxframework.crux.core.client.screen.views.ViewAware {
  
  public MenuController_ControllerProxy(org.cruxframework.crux.core.client.screen.views.View view) {
    this.__view = view;
  }
  public String getBoundCruxViewId(){
    return (this.__view==null?null:this.__view.getId());
  }
  public org.cruxframework.crux.core.client.screen.views.View getBoundCruxView(){
    return this.__view;
  }
  public void add_Exposed_()
  {
    if (LogConfiguration.loggingIsEnabled()){
      _logger_.log(Level.FINE, "Calling client method: Controller[menuController], Method[add]");
    }
    add();
    if (LogConfiguration.loggingIsEnabled()){
      _logger_.log(Level.FINE, "Client method executed: Controller[menuController], Method[add]");
    }
  }
  public void list_Exposed_()
  {
    if (LogConfiguration.loggingIsEnabled()){
      _logger_.log(Level.FINE, "Calling client method: Controller[menuController], Method[list]");
    }
    list();
    if (LogConfiguration.loggingIsEnabled()){
      _logger_.log(Level.FINE, "Client method executed: Controller[menuController], Method[list]");
    }
  }
  private org.cruxframework.crux.core.client.screen.views.View __view;
  private static Logger _logger_ = Logger.getLogger(MenuController_ControllerProxy.class.getName());
  
}
