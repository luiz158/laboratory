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

public class addbookmark_largeDisplayMouse extends org.cruxframework.crux.core.client.screen.views.BindableView<org.cruxframework.bookmark.client.dto.BookmarkDTO> {
  protected addbookmark_largeDisplayMouse(String id){
    super(id);
    setTitle(null);
    this.iocContainer = new org.cruxframework.crux.core.client.ioc.IocContainer_addbookmark_largeDisplayMouse(this);
    this.registeredControllers = new org.cruxframework.crux.core.client.controller.RegisteredControllers_addbookmark_largeDisplayMouse(this, iocContainer);
    this.registeredDataSources = new org.cruxframework.crux.core.client.datasource.RegisteredDataSources_addbookmark_largeDisplayMouse(this, iocContainer);
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
        ((org.cruxframework.bookmark.client.controller.AddBookmarkController_ControllerProxy)__view.getController("addBookmarkController")).onActivate_Exposed_(event);
      }
    });
    final org.cruxframework.crux.widgets.client.styledpanel.StyledPanel widget34 = GWT.create(org.cruxframework.crux.widgets.client.styledpanel.StyledPanel.class);
    __view.addWidget("sanel", widget34);
    widget34.setWidth("100%");
    final org.cruxframework.crux.widgets.client.styledpanel.StyledPanel widget35 = GWT.create(org.cruxframework.crux.widgets.client.styledpanel.StyledPanel.class);
    __view.addWidget("sPanel", widget35);
    widget35.setStyleName("spanel");
    final org.cruxframework.crux.widgets.client.formdisplay.FormDisplay widget36 = GWT.create(org.cruxframework.crux.widgets.client.formdisplay.FormDisplay.class);
    __view.addWidget("form", widget36);
    widget36.setStyleName("form");
    final com.google.gwt.user.client.ui.TextBox widget37 = GWT.create(com.google.gwt.user.client.ui.TextBox.class);
    __view.addWidget("descricaoTextBox", widget37, new org.cruxframework.crux.core.client.screen.views.BindableView.PropertyBinder<org.cruxframework.bookmark.client.dto.BookmarkDTO>(){
      public void copyTo(org.cruxframework.bookmark.client.dto.BookmarkDTO dataObject, Widget w){
        if (w != null){
          ((com.google.gwt.user.client.ui.HasValue<java.lang.String>)w).setValue(dataObject.getDescription());
        }
      }
      public void copyFrom(Widget w, org.cruxframework.bookmark.client.dto.BookmarkDTO dataObject){
        if (dataObject != null){
          dataObject.setDescription((w==null?null:((com.google.gwt.user.client.ui.HasValue<java.lang.String>)w).getValue()));
        }
      }
    });
    widget37.setWidth("250px");
    widget36.addEntry(mesg5.addDescription(), widget37, com.google.gwt.user.client.ui.HasHorizontalAlignment.ALIGN_LEFT);
    final com.google.gwt.user.client.ui.TextBox widget38 = GWT.create(com.google.gwt.user.client.ui.TextBox.class);
    __view.addWidget("linkTextBox", widget38, new org.cruxframework.crux.core.client.screen.views.BindableView.PropertyBinder<org.cruxframework.bookmark.client.dto.BookmarkDTO>(){
      public void copyTo(org.cruxframework.bookmark.client.dto.BookmarkDTO dataObject, Widget w){
        if (w != null){
          ((com.google.gwt.user.client.ui.HasValue<java.lang.String>)w).setValue(dataObject.getLink());
        }
      }
      public void copyFrom(Widget w, org.cruxframework.bookmark.client.dto.BookmarkDTO dataObject){
        if (dataObject != null){
          dataObject.setLink((w==null?null:((com.google.gwt.user.client.ui.HasValue<java.lang.String>)w).getValue()));
        }
      }
    });
    widget38.setWidth("250px");
    widget36.addEntry(mesg5.addLink(), widget38, com.google.gwt.user.client.ui.HasHorizontalAlignment.ALIGN_LEFT);
    final com.google.gwt.user.client.ui.HorizontalPanel widget39 = GWT.create(com.google.gwt.user.client.ui.HorizontalPanel.class);
    __view.addWidget("gwtHorizontalpanel", widget39);
    final org.cruxframework.crux.widgets.client.button.Button widget40 = GWT.create(org.cruxframework.crux.widgets.client.button.Button.class);
    __view.addWidget("saveButton", widget40);
    widget40.setWidth("120px");
    com.google.gwt.dom.client.Element elem0 = widget40.getElement();
    org.cruxframework.crux.core.client.utils.StyleUtils.addStyleProperty(elem0, "display", "block");
    widget40.setText(mesg5.addSave());
    widget40.addSelectHandler(new org.cruxframework.crux.widgets.client.event.SelectHandler(){
      public void onSelect(org.cruxframework.crux.widgets.client.event.SelectEvent event){
        ((org.cruxframework.bookmark.client.controller.AddBookmarkController_ControllerProxy)__view.getController("addBookmarkController")).save_Exposed_();
      }
    });
    widget39.add(widget40);
    widget39.setCellHorizontalAlignment(widget40, com.google.gwt.user.client.ui.HasHorizontalAlignment.ALIGN_CENTER);
    widget39.setCellVerticalAlignment(widget40, com.google.gwt.user.client.ui.HasVerticalAlignment.ALIGN_MIDDLE);
    widget36.addEntry(null, widget39, com.google.gwt.user.client.ui.HasHorizontalAlignment.ALIGN_LEFT);
    widget35.add(widget36);
    widget34.add(widget35);
    if (com.google.gwt.logging.client.LogConfiguration.loggingIsEnabled()){
      logger4.info(Crux.getMessages().viewContainerViewCreated(getId()));
    }
  }
  protected void render(com.google.gwt.user.client.ui.Panel rootPanel6, final org.cruxframework.crux.core.client.screen.views.View.RenderCallback renderCallback) throws InterfaceConfigException{
    if (this.viewPanel1 == null){
      this.viewPanel1 = new com.google.gwt.user.client.ui.HTMLPanel(" <div id=\"_crux_"+__view.getPrefix()+"sanel\"></div> ");
      rootPanel6.add(this.viewPanel1);
      this.viewPanel1.addAndReplaceElement(widgets.get("sanel"), ViewFactoryUtils.getEnclosingPanelId("sanel", __view));
      renderCallback.onRendered();
    }
    else {
      rootPanel6.add(this.viewPanel1);
      renderCallback.onRendered();
    }
    if(!StringUtils.isEmpty(this.width)){
      updateViewWidth(this.width);
    }
    if(!StringUtils.isEmpty(this.height)){
      updateViewHeight(this.height);
    }
    if (com.google.gwt.logging.client.LogConfiguration.loggingIsEnabled()){
      logger4.info(Crux.getMessages().viewContainerViewRendered(getId()));
    }
  }
  protected void updateViewHeight(String height){
    if (this.viewPanel1 != null){
      this.viewPanel1.setHeight(height);
    }
  }
  protected void updateViewWidth(String width){
    if (this.viewPanel1 != null){
      this.viewPanel1.setWidth(width);
    }
  }
  protected native org.cruxframework.crux.core.client.collection.Map<String> initializeLazyDependencies()/*-{
  return {};
}-*/;
public org.cruxframework.crux.core.client.ioc.IocContainer getIocContainer(){
  return iocContainer;
}
public org.cruxframework.crux.core.client.ioc.IocContainer_addbookmark_largeDisplayMouse getTypedIocContainer(){
  return iocContainer;
}
protected org.cruxframework.bookmark.client.dto.BookmarkDTO createDataObject(){
  return GWT.create(org.cruxframework.bookmark.client.dto.BookmarkDTO.class);
}
private org.cruxframework.crux.core.client.controller.RegisteredControllers registeredControllers;
private org.cruxframework.crux.core.client.datasource.RegisteredDataSources registeredDataSources;
protected org.cruxframework.crux.core.client.ioc.IocContainer_addbookmark_largeDisplayMouse iocContainer;
private org.cruxframework.bookmark.client.shared.messages.CommonMessages mesg5 = GWT.create(org.cruxframework.bookmark.client.shared.messages.CommonMessages.class);
private final org.cruxframework.crux.core.client.screen.views.BindableView<org.cruxframework.bookmark.client.dto.BookmarkDTO> __view = this;
private static java.util.logging.Logger logger4 = java.util.logging.Logger.getLogger(addbookmark_largeDisplayMouse.class.getName());
private com.google.gwt.user.client.ui.HTMLPanel viewPanel1 = null;
}
