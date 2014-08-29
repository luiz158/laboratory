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

public class menu_largeDisplayMouse extends org.cruxframework.crux.core.client.screen.views.View {
  protected menu_largeDisplayMouse(String id){
    super(id);
    setTitle(null);
    this.iocContainer = new org.cruxframework.crux.core.client.ioc.IocContainer_menu_largeDisplayMouse(this);
    this.registeredControllers = new org.cruxframework.crux.core.client.controller.RegisteredControllers_menu_largeDisplayMouse(this, iocContainer);
    this.registeredDataSources = new org.cruxframework.crux.core.client.datasource.RegisteredDataSources_menu_largeDisplayMouse(this, iocContainer);
  }
  public org.cruxframework.crux.core.client.controller.RegisteredControllers getRegisteredControllers(){
    return this.registeredControllers;
  }
  public org.cruxframework.crux.core.client.datasource.DataSource<?> createDataSource(String dataSource){
    return this.registeredDataSources.getDataSource(dataSource);
  }
  protected void createWidgets(){
    final org.cruxframework.crux.smartfaces.client.panel.HeaderPanel widget41 = GWT.create(org.cruxframework.crux.smartfaces.client.panel.HeaderPanel.class);
    __view.addWidget("hpHeader", widget41);
    widget41.setStyleName("header");
    final com.google.gwt.user.client.ui.FlowPanel widget42 = GWT.create(com.google.gwt.user.client.ui.FlowPanel.class);
    __view.addWidget("menuWrapper", widget42);
    final org.cruxframework.crux.widgets.client.anchor.Anchor widget43 = GWT.create(org.cruxframework.crux.widgets.client.anchor.Anchor.class);
    __view.addWidget("HeaderLogo", widget43);
    widget43.setStyleName("header-logo");
    widget42.add(widget43);
    final org.cruxframework.crux.widgets.client.anchor.Anchor widget44 = GWT.create(org.cruxframework.crux.widgets.client.anchor.Anchor.class);
    __view.addWidget("HeaderPtbr", widget44);
    widget44.setHref("index.html?locale=pt_BR&gwt.codesvr=10.1.2.177:9997");
    widget44.setStyleName("header-ptbr");
    widget42.add(widget44);
    final org.cruxframework.crux.widgets.client.anchor.Anchor widget45 = GWT.create(org.cruxframework.crux.widgets.client.anchor.Anchor.class);
    __view.addWidget("HeaderEn", widget45);
    widget45.setHref("index.html?locale=pt-BR&gwt.codesvr=10.1.2.177:9997");
    widget45.setStyleName("header-en");
    widget42.add(widget45);
    final org.cruxframework.crux.smartfaces.client.menu.Menu widget46 = new org.cruxframework.crux.smartfaces.client.menu.Menu(org.cruxframework.crux.smartfaces.client.menu.Type.LargeType.HORIZONTAL_DROPDOWN,org.cruxframework.crux.smartfaces.client.menu.Type.SmallType.VERTICAL_ACCORDION);
    __view.addWidget("menu", widget46);
    org.cruxframework.crux.smartfaces.client.menu.MenuItem item3 = widget46.addItem(mesg6.menuBookmark());
    org.cruxframework.crux.smartfaces.client.menu.MenuItem item4 = widget46.addItem(item3,mesg6.menuNew());
    item4.addSelectHandler(new org.cruxframework.crux.smartfaces.client.event.SelectHandler(){
      public void onSelect(org.cruxframework.crux.smartfaces.client.event.SelectEvent event){
        ((org.cruxframework.bookmark.client.controller.MenuController_ControllerProxy)__view.getController("menuController")).add_Exposed_();
      }
    });
    item4.setId("new");
    org.cruxframework.crux.smartfaces.client.menu.MenuItem item5 = widget46.addItem(item3,mesg6.menuList());
    item5.addSelectHandler(new org.cruxframework.crux.smartfaces.client.event.SelectHandler(){
      public void onSelect(org.cruxframework.crux.smartfaces.client.event.SelectEvent event){
        ((org.cruxframework.bookmark.client.controller.MenuController_ControllerProxy)__view.getController("menuController")).list_Exposed_();
      }
    });
    item5.setId("list");
    item5.addSelectHandler(new org.cruxframework.crux.smartfaces.client.event.SelectHandler(){
      public void onSelect(org.cruxframework.crux.smartfaces.client.event.SelectEvent event){
        ((org.cruxframework.bookmark.client.controller.MenuController_ControllerProxy)__view.getController("menuController")).list_Exposed_();
      }
    });
    item3.setId("menuBookmark");
    final com.google.gwt.user.client.ui.Anchor widget47 = GWT.create(com.google.gwt.user.client.ui.Anchor.class);
    __view.addWidget("anQuit", widget47);
    widget47.setHref("www.frameworkdemoiselle.gov.br");
    widget47.setText(mesg6.menuQuit());
    org.cruxframework.crux.smartfaces.client.menu.MenuItem item6 = widget46.addItem(widget47);
    item6.setId("menuBookmark");
    widget42.add(widget46);
    widget41.add(widget42);
    if (com.google.gwt.logging.client.LogConfiguration.loggingIsEnabled()){
      logger5.info(Crux.getMessages().viewContainerViewCreated(getId()));
    }
  }
  protected void render(com.google.gwt.user.client.ui.Panel rootPanel7, final org.cruxframework.crux.core.client.screen.views.View.RenderCallback renderCallback) throws InterfaceConfigException{
    if (this.viewPanel2 == null){
      this.viewPanel2 = new com.google.gwt.user.client.ui.HTMLPanel(" <div id=\"_crux_"+__view.getPrefix()+"hpHeader\"></div> ");
      rootPanel7.add(this.viewPanel2);
      this.viewPanel2.addAndReplaceElement(widgets.get("hpHeader"), ViewFactoryUtils.getEnclosingPanelId("hpHeader", __view));
      renderCallback.onRendered();
    }
    else {
      rootPanel7.add(this.viewPanel2);
      renderCallback.onRendered();
    }
    if(!StringUtils.isEmpty(this.width)){
      updateViewWidth(this.width);
    }
    if(!StringUtils.isEmpty(this.height)){
      updateViewHeight(this.height);
    }
    if (com.google.gwt.logging.client.LogConfiguration.loggingIsEnabled()){
      logger5.info(Crux.getMessages().viewContainerViewRendered(getId()));
    }
  }
  protected void updateViewHeight(String height){
    if (this.viewPanel2 != null){
      this.viewPanel2.setHeight(height);
    }
  }
  protected void updateViewWidth(String width){
    if (this.viewPanel2 != null){
      this.viewPanel2.setWidth(width);
    }
  }
  protected native org.cruxframework.crux.core.client.collection.Map<String> initializeLazyDependencies()/*-{
  return {};
}-*/;
public org.cruxframework.crux.core.client.ioc.IocContainer getIocContainer(){
  return iocContainer;
}
public org.cruxframework.crux.core.client.ioc.IocContainer_menu_largeDisplayMouse getTypedIocContainer(){
  return iocContainer;
}
private org.cruxframework.crux.core.client.controller.RegisteredControllers registeredControllers;
private org.cruxframework.crux.core.client.datasource.RegisteredDataSources registeredDataSources;
protected org.cruxframework.crux.core.client.ioc.IocContainer_menu_largeDisplayMouse iocContainer;
private org.cruxframework.bookmark.client.shared.messages.CommonMessages mesg6 = GWT.create(org.cruxframework.bookmark.client.shared.messages.CommonMessages.class);
private final View __view = this;
private static java.util.logging.Logger logger5 = java.util.logging.Logger.getLogger(menu_largeDisplayMouse.class.getName());
private com.google.gwt.user.client.ui.HTMLPanel viewPanel2 = null;
}
