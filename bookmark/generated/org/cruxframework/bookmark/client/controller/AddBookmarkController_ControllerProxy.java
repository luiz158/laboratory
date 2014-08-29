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

public class AddBookmarkController_ControllerProxy extends org.cruxframework.bookmark.client.controller.AddBookmarkController implements org.cruxframework.crux.core.client.screen.views.ViewAware {
  
  public AddBookmarkController_ControllerProxy(org.cruxframework.crux.core.client.screen.views.View view) {
    this.__view = view;
  }
  public String getBoundCruxViewId(){
    return (this.__view==null?null:this.__view.getId());
  }
  public org.cruxframework.crux.core.client.screen.views.View getBoundCruxView(){
    return this.__view;
  }
  public void onActivate_Exposed_(org.cruxframework.crux.core.client.screen.views.ViewActivateEvent event)
  {
    if (LogConfiguration.loggingIsEnabled()){
      _logger_.log(Level.FINE, "Calling client method: Controller[addBookmarkController], Method[onActivate]");
    }
    onActivate(event);
    if (LogConfiguration.loggingIsEnabled()){
      _logger_.log(Level.FINE, "Client method executed: Controller[addBookmarkController], Method[onActivate]");
    }
  }
  public void save_Exposed_()
  {
    if (LogConfiguration.loggingIsEnabled()){
      _logger_.log(Level.FINE, "Calling client method: Controller[addBookmarkController], Method[save]");
    }
    save();
    if (LogConfiguration.loggingIsEnabled()){
      _logger_.log(Level.FINE, "Client method executed: Controller[addBookmarkController], Method[save]");
    }
  }
  private org.cruxframework.crux.core.client.screen.views.View __view;
  private static Logger _logger_ = Logger.getLogger(AddBookmarkController_ControllerProxy.class.getName());
  
}
