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

public class ListBookmarkController_ControllerProxy extends org.cruxframework.bookmark.client.controller.ListBookmarkController implements org.cruxframework.crux.core.client.screen.views.ViewAware {
  
  public ListBookmarkController_ControllerProxy(org.cruxframework.crux.core.client.screen.views.View view) {
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
      _logger_.log(Level.FINE, "Calling client method: Controller[listBookmarkController], Method[add]");
    }
    add();
    if (LogConfiguration.loggingIsEnabled()){
      _logger_.log(Level.FINE, "Client method executed: Controller[listBookmarkController], Method[add]");
    }
  }
  public void delete_Exposed_()
  {
    if (LogConfiguration.loggingIsEnabled()){
      _logger_.log(Level.FINE, "Calling client method: Controller[listBookmarkController], Method[delete]");
    }
    delete();
    if (LogConfiguration.loggingIsEnabled()){
      _logger_.log(Level.FINE, "Client method executed: Controller[listBookmarkController], Method[delete]");
    }
  }
  public void onActivate_Exposed_()
  {
    if (LogConfiguration.loggingIsEnabled()){
      _logger_.log(Level.FINE, "Calling client method: Controller[listBookmarkController], Method[onActivate]");
    }
    onActivate();
    if (LogConfiguration.loggingIsEnabled()){
      _logger_.log(Level.FINE, "Client method executed: Controller[listBookmarkController], Method[onActivate]");
    }
  }
  public void update_Exposed_(org.cruxframework.crux.widgets.client.event.SelectEvent selectEvent)
  {
    if (LogConfiguration.loggingIsEnabled()){
      _logger_.log(Level.FINE, "Calling client method: Controller[listBookmarkController], Method[update]");
    }
    update(selectEvent);
    if (LogConfiguration.loggingIsEnabled()){
      _logger_.log(Level.FINE, "Client method executed: Controller[listBookmarkController], Method[update]");
    }
  }
  private org.cruxframework.crux.core.client.screen.views.View __view;
  private static Logger _logger_ = Logger.getLogger(ListBookmarkController_ControllerProxy.class.getName());
  
}
