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

public class bookmark_WEB_INF_classes_org_cruxframework_bookmark_public_index_html_largeDisplayMouse extends org.cruxframework.crux.core.client.screen.views.View {
  protected bookmark_WEB_INF_classes_org_cruxframework_bookmark_public_index_html_largeDisplayMouse(String id){
    super(id);
    setTitle(null);
    this.iocContainer = new org.cruxframework.crux.core.client.ioc.IocContainer_bookmark_WEB_INF_classes_org_cruxframework_bookmark_public_index_html_largeDisplayMouse(this);
    this.registeredControllers = new org.cruxframework.crux.core.client.controller.RegisteredControllers_bookmark_WEB_INF_classes_org_cruxframework_bookmark_public_index_html_largeDisplayMouse(this, iocContainer);
    this.registeredDataSources = new org.cruxframework.crux.core.client.datasource.RegisteredDataSources_bookmark_WEB_INF_classes_org_cruxframework_bookmark_public_index_html_largeDisplayMouse(this, iocContainer);
    org.cruxframework.crux.core.client.screen.views.bookmarkResources_largeDisplayMouse.init();
    org.cruxframework.crux.core.client.screen.views.xStandardResources_largeDisplayMouse.init();
  }
  public org.cruxframework.crux.core.client.controller.RegisteredControllers getRegisteredControllers(){
    return this.registeredControllers;
  }
  public org.cruxframework.crux.core.client.datasource.DataSource<?> createDataSource(String dataSource){
    return this.registeredDataSources.getDataSource(dataSource);
  }
  protected void createWidgets(){
    __view.addViewActivateHandler(new org.cruxframework.crux.core.client.screen.views.ViewActivateHandler(){
      public void onActivate(org.cruxframework.crux.core.client.screen.views.ViewActivateEvent event){
        ((org.cruxframework.bookmark.client.controller.IndexController_ControllerProxy)__view.getController("indexController")).onActivate_Exposed_();
      }
    });
    setHeight("100%");
    final org.cruxframework.crux.widgets.client.simplecontainer.SimpleViewContainer widget71 = GWT.create(org.cruxframework.crux.widgets.client.simplecontainer.SimpleViewContainer.class);
    __view.addWidget("header", widget71);
    widget71.setStyleName("header");
    final org.cruxframework.crux.widgets.client.simplecontainer.SimpleViewContainer widget72 = GWT.create(org.cruxframework.crux.widgets.client.simplecontainer.SimpleViewContainer.class);
    __view.addWidget("container", widget72);
    widget72.setStyleName("container");
    if (com.google.gwt.logging.client.LogConfiguration.loggingIsEnabled()){
      logger12.info(Crux.getMessages().viewContainerViewCreated(getId()));
    }
  }
  protected void render(com.google.gwt.user.client.ui.Panel rootPanel14, final org.cruxframework.crux.core.client.screen.views.View.RenderCallback renderCallback) throws InterfaceConfigException{
    if (this.viewPanel4 == null){
      this.viewPanel4 = new com.google.gwt.user.client.ui.HTMLPanel("   <div id=\"_crux_"+__view.getPrefix()+"header\"></div> <div id=\"_crux_"+__view.getPrefix()+"container\"></div> ");
      rootPanel14.add(this.viewPanel4);
      this.viewPanel4.addAndReplaceElement(widgets.get("container"), ViewFactoryUtils.getEnclosingPanelId("container", __view));
      this.viewPanel4.addAndReplaceElement(widgets.get("header"), ViewFactoryUtils.getEnclosingPanelId("header", __view));
      renderCallback.onRendered();
    }
    else {
      rootPanel14.add(this.viewPanel4);
      renderCallback.onRendered();
    }
    if(!StringUtils.isEmpty(this.width)){
      updateViewWidth(this.width);
    }
    if(!StringUtils.isEmpty(this.height)){
      updateViewHeight(this.height);
    }
    if (com.google.gwt.logging.client.LogConfiguration.loggingIsEnabled()){
      logger12.info(Crux.getMessages().viewContainerViewRendered(getId()));
    }
  }
  protected void updateViewHeight(String height){
    if (this.viewPanel4 != null){
      this.viewPanel4.setHeight(height);
    }
  }
  protected void updateViewWidth(String width){
    if (this.viewPanel4 != null){
      this.viewPanel4.setWidth(width);
    }
  }
  protected native org.cruxframework.crux.core.client.collection.Map<String> initializeLazyDependencies()/*-{
  return {};
}-*/;
public org.cruxframework.crux.core.client.ioc.IocContainer getIocContainer(){
  return iocContainer;
}
public org.cruxframework.crux.core.client.ioc.IocContainer_bookmark_WEB_INF_classes_org_cruxframework_bookmark_public_index_html_largeDisplayMouse getTypedIocContainer(){
  return iocContainer;
}
private org.cruxframework.crux.core.client.controller.RegisteredControllers registeredControllers;
private org.cruxframework.crux.core.client.datasource.RegisteredDataSources registeredDataSources;
protected org.cruxframework.crux.core.client.ioc.IocContainer_bookmark_WEB_INF_classes_org_cruxframework_bookmark_public_index_html_largeDisplayMouse iocContainer;
private final View __view = this;
private static java.util.logging.Logger logger12 = java.util.logging.Logger.getLogger(bookmark_WEB_INF_classes_org_cruxframework_bookmark_public_index_html_largeDisplayMouse.class.getName());
private com.google.gwt.user.client.ui.HTMLPanel viewPanel4 = null;
}
