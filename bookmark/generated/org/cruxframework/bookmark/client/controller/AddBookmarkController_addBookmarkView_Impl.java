package org.cruxframework.bookmark.client.controller;

import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.core.client.GWT;
import org.cruxframework.crux.core.client.Crux;
import com.google.gwt.user.client.Window;

public class AddBookmarkController_addBookmarkView_Impl implements org.cruxframework.bookmark.client.controller.AddBookmarkController.addBookmarkView {
  public AddBookmarkController_addBookmarkView_Impl(){
    this.__view = "addbookmark";
  }
  public com.google.gwt.user.client.ui.TextBox description(){
    org.cruxframework.crux.core.client.screen.views.View __view = org.cruxframework.crux.core.client.screen.views.View.getView(this.__view);
    assert(__view != null):"View ["+this.__view+"] was not loaded. Ensure that desired view is loaded by the application (through useView declaration).";
    return (com.google.gwt.user.client.ui.TextBox)__view.getWidget("description");
  }
  public com.google.gwt.user.client.ui.TextBox link(){
    org.cruxframework.crux.core.client.screen.views.View __view = org.cruxframework.crux.core.client.screen.views.View.getView(this.__view);
    assert(__view != null):"View ["+this.__view+"] was not loaded. Ensure that desired view is loaded by the application (through useView declaration).";
    return (com.google.gwt.user.client.ui.TextBox)__view.getWidget("link");
  }
  public String getBoundCruxViewId(){
    return this.__view;
  }
  
  public org.cruxframework.crux.core.client.screen.views.View getBoundCruxView(){
    return (this.__view!=null?org.cruxframework.crux.core.client.screen.views.View.getView(this.__view):null);
  }
  
  public void bindCruxView(String view){
    this.__view = view;
  }
  
  public IsWidget _getFromView(String widgetName){
    org.cruxframework.crux.core.client.screen.views.View __view = org.cruxframework.crux.core.client.screen.views.View.getView(this.__view);
    assert (__view != null):"No view loaded with desired identifier.";
    IsWidget ret = __view.getWidget(widgetName);
    if (ret == null){
      String widgetNameFirstUpper;
      if (widgetName.length() > 1){
        widgetNameFirstUpper = Character.toUpperCase(widgetName.charAt(0)) + widgetName.substring(1);
      }
      else{
        widgetNameFirstUpper = ""+Character.toUpperCase(widgetName.charAt(0));
      }
      ret = __view.getWidget(widgetNameFirstUpper);
    }
    return ret;
  }
  private String __view;
}
