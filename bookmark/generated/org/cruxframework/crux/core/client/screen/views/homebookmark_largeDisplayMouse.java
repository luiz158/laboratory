package org.cruxframework.crux.core.client.screen.views;

import com.google.gwt.core.client.GWT;
import org.cruxframework.crux.core.client.screen.Screen;
import org.cruxframework.crux.core.client.screen.views.View;
import org.cruxframework.crux.core.client.utils.StringUtils;
import com.google.gwt.user.client.Window;
import org.cruxframework.crux.core.client.screen.views.ViewFactoryUtils;
import org.cruxframework.crux.core.client.screen.views.ViewFactory.CreateCallback;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Node;
import org.cruxframework.crux.core.client.screen.views.ViewLoadEvent;
import com.google.gwt.user.client.ui.Panel;
import org.cruxframework.crux.core.client.screen.InterfaceConfigException;
import com.google.gwt.user.client.ui.Widget;
import org.cruxframework.crux.core.client.Crux;

public class homebookmark_largeDisplayMouse extends org.cruxframework.crux.core.client.screen.views.View {
  protected homebookmark_largeDisplayMouse(String id){
    super(id);
    setTitle(null);
    this.iocContainer = new org.cruxframework.crux.core.client.ioc.IocContainer_homebookmark_largeDisplayMouse(this);
    this.registeredControllers = new org.cruxframework.crux.core.client.controller.RegisteredControllers_homebookmark_largeDisplayMouse(this, iocContainer);
    this.registeredDataSources = new org.cruxframework.crux.core.client.datasource.RegisteredDataSources_homebookmark_largeDisplayMouse(this, iocContainer);
  }
  public org.cruxframework.crux.core.client.controller.RegisteredControllers getRegisteredControllers(){
    return this.registeredControllers;
  }
  public org.cruxframework.crux.core.client.datasource.DataSource<?> createDataSource(String dataSource){
    return this.registeredDataSources.getDataSource(dataSource);
  }
  protected void createWidgets(){
    final org.cruxframework.crux.widgets.client.styledpanel.StyledPanel widget48 = GWT.create(org.cruxframework.crux.widgets.client.styledpanel.StyledPanel.class);
    __view.addWidget("sanel", widget48);
    widget48.setWidth("100%");
    if (com.google.gwt.logging.client.LogConfiguration.loggingIsEnabled()){
      logger6.info(Crux.getMessages().viewContainerViewCreated(getId()));
    }
  }
  protected void render(com.google.gwt.user.client.ui.Panel rootPanel8, final org.cruxframework.crux.core.client.screen.views.View.RenderCallback renderCallback) throws InterfaceConfigException{
    if (this.viewPanel3 == null){
      this.viewPanel3 = new com.google.gwt.user.client.ui.HTMLPanel(" <div id=\"_crux_"+__view.getPrefix()+"sanel\"></div> ");
      rootPanel8.add(this.viewPanel3);
      this.viewPanel3.addAndReplaceElement(widgets.get("sanel"), ViewFactoryUtils.getEnclosingPanelId("sanel", __view));
      renderCallback.onRendered();
    }
    else {
      rootPanel8.add(this.viewPanel3);
      renderCallback.onRendered();
    }
    if(!StringUtils.isEmpty(this.width)){
      updateViewWidth(this.width);
    }
    if(!StringUtils.isEmpty(this.height)){
      updateViewHeight(this.height);
    }
    if (com.google.gwt.logging.client.LogConfiguration.loggingIsEnabled()){
      logger6.info(Crux.getMessages().viewContainerViewRendered(getId()));
    }
  }
  protected void updateViewHeight(String height){
    if (this.viewPanel3 != null){
      this.viewPanel3.setHeight(height);
    }
  }
  protected void updateViewWidth(String width){
    if (this.viewPanel3 != null){
      this.viewPanel3.setWidth(width);
    }
  }
  protected native org.cruxframework.crux.core.client.collection.Map<String> initializeLazyDependencies()/*-{
  return {};
}-*/;
public org.cruxframework.crux.core.client.ioc.IocContainer getIocContainer(){
  return iocContainer;
}
public org.cruxframework.crux.core.client.ioc.IocContainer_homebookmark_largeDisplayMouse getTypedIocContainer(){
  return iocContainer;
}
private org.cruxframework.crux.core.client.controller.RegisteredControllers registeredControllers;
private org.cruxframework.crux.core.client.datasource.RegisteredDataSources registeredDataSources;
protected org.cruxframework.crux.core.client.ioc.IocContainer_homebookmark_largeDisplayMouse iocContainer;
private final View __view = this;
private static java.util.logging.Logger logger6 = java.util.logging.Logger.getLogger(homebookmark_largeDisplayMouse.class.getName());
private com.google.gwt.user.client.ui.HTMLPanel viewPanel3 = null;
}
