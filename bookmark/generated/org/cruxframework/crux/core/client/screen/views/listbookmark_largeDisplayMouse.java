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

public class listbookmark_largeDisplayMouse extends org.cruxframework.crux.core.client.screen.views.View {
  protected listbookmark_largeDisplayMouse(String id){
    super(id);
    setTitle(null);
    this.iocContainer = new org.cruxframework.crux.core.client.ioc.IocContainer_listbookmark_largeDisplayMouse(this);
    this.registeredControllers = new org.cruxframework.crux.core.client.controller.RegisteredControllers_listbookmark_largeDisplayMouse(this, iocContainer);
    this.registeredDataSources = new org.cruxframework.crux.core.client.datasource.RegisteredDataSources_listbookmark_largeDisplayMouse(this, iocContainer);
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
        ((org.cruxframework.bookmark.client.controller.ListBookmarkController_ControllerProxy)__view.getController("listBookmarkController")).onActivate_Exposed_();
      }
    });
    final org.cruxframework.crux.widgets.client.styledpanel.StyledPanel widget26 = GWT.create(org.cruxframework.crux.widgets.client.styledpanel.StyledPanel.class);
    __view.addWidget("outerPanel", widget26);
    widget26.setStyleName("styledpanel");
    final com.google.gwt.user.client.ui.VerticalPanel widget27 = GWT.create(com.google.gwt.user.client.ui.VerticalPanel.class);
    __view.addWidget("gwtHorizontal", widget27);
    widget27.setStyleName("verticalpanel");
    widget27.setHorizontalAlignment(com.google.gwt.user.client.ui.HasHorizontalAlignment.ALIGN_RIGHT);
    final org.cruxframework.crux.smartfaces.client.panel.NavPanel widget28 = GWT.create(org.cruxframework.crux.smartfaces.client.panel.NavPanel.class);
    __view.addWidget("commandButtonNav", widget28);
    widget28.setStyleName("header-menu");
    final org.cruxframework.crux.widgets.client.button.Button widget29 = GWT.create(org.cruxframework.crux.widgets.client.button.Button.class);
    __view.addWidget("bookmarkAddButton", widget29);
    widget29.setStyleName("buttonbookmarklist");
    widget29.setText(mesg4.listNew());
    widget29.addSelectHandler(new org.cruxframework.crux.widgets.client.event.SelectHandler(){
      public void onSelect(org.cruxframework.crux.widgets.client.event.SelectEvent event){
        ((org.cruxframework.bookmark.client.controller.ListBookmarkController_ControllerProxy)__view.getController("listBookmarkController")).add_Exposed_();
      }
    });
    widget28.add(widget29);
    final org.cruxframework.crux.widgets.client.button.Button widget30 = GWT.create(org.cruxframework.crux.widgets.client.button.Button.class);
    __view.addWidget("bookmarkDeleteButton", widget30);
    widget30.setStyleName("buttonbookmarklist");
    widget30.setText(mesg4.listDelete());
    widget30.addSelectHandler(new org.cruxframework.crux.widgets.client.event.SelectHandler(){
      public void onSelect(org.cruxframework.crux.widgets.client.event.SelectEvent event){
        ((org.cruxframework.bookmark.client.controller.ListBookmarkController_ControllerProxy)__view.getController("listBookmarkController")).delete_Exposed_();
      }
    });
    widget28.add(widget30);
    widget27.add(widget28);
    widget27.setCellHorizontalAlignment(widget28, com.google.gwt.user.client.ui.HasHorizontalAlignment.ALIGN_CENTER);
    widget27.setCellVerticalAlignment(widget28, com.google.gwt.user.client.ui.HasVerticalAlignment.ALIGN_MIDDLE);
    org.cruxframework.crux.widgets.client.deviceadaptivegrid.DeviceAdaptiveGridColumnDefinitions defs0 = new org.cruxframework.crux.widgets.client.deviceadaptivegrid.DeviceAdaptiveGridColumnDefinitions();
    org.cruxframework.crux.widgets.client.grid.ColumnDefinition def4 = new org.cruxframework.crux.widgets.client.grid.DataColumnDefinition(mesg4.listId(), "", null, true, true, true, false, com.google.gwt.user.client.ui.HasHorizontalAlignment.ALIGN_CENTER, com.google.gwt.user.client.ui.HasVerticalAlignment.ALIGN_MIDDLE, null);
    defs0.add(org.cruxframework.crux.core.client.screen.DeviceAdaptive.Size.large, "id", def4);org.cruxframework.crux.widgets.client.grid.ColumnDefinition def5 = new org.cruxframework.crux.widgets.client.grid.DataColumnDefinition(mesg4.listDescription(), "", null, true, true, true, false, com.google.gwt.user.client.ui.HasHorizontalAlignment.ALIGN_CENTER, com.google.gwt.user.client.ui.HasVerticalAlignment.ALIGN_MIDDLE, null);
    defs0.add(org.cruxframework.crux.core.client.screen.DeviceAdaptive.Size.large, "description", def5);org.cruxframework.crux.widgets.client.grid.ColumnDefinition def6 = new org.cruxframework.crux.widgets.client.grid.DataColumnDefinition(mesg4.listLink(), "", null, true, true, true, false, com.google.gwt.user.client.ui.HasHorizontalAlignment.ALIGN_CENTER, com.google.gwt.user.client.ui.HasVerticalAlignment.ALIGN_MIDDLE, null);
    defs0.add(org.cruxframework.crux.core.client.screen.DeviceAdaptive.Size.large, "link", def6);org.cruxframework.crux.widgets.client.grid.WidgetColumnDefinition.WidgetColumnCreator widgetColumnCreator0 = new org.cruxframework.crux.widgets.client.grid.WidgetColumnDefinition.WidgetColumnCreator(){
      public Widget createWidgetForColumn(){
        final com.google.gwt.user.client.ui.FlowPanel widget32 = GWT.create(com.google.gwt.user.client.ui.FlowPanel.class);
        __view.addWidget("btnGroup", widget32);
        widget32.setStyleName("action-btns");
        final org.cruxframework.crux.widgets.client.button.Button widget33 = GWT.create(org.cruxframework.crux.widgets.client.button.Button.class);
        __view.addWidget("edit", widget33);
        widget33.setText(mesg4.listEdit());
        widget33.addSelectHandler(new org.cruxframework.crux.widgets.client.event.SelectHandler(){
          public void onSelect(org.cruxframework.crux.widgets.client.event.SelectEvent event){
            ((org.cruxframework.bookmark.client.controller.ListBookmarkController_ControllerProxy)__view.getController("listBookmarkController")).update_Exposed_(event);
          }
        });
        widget32.add(widget33);
        return widget32;
      };
    };
    org.cruxframework.crux.widgets.client.grid.ColumnDefinition def7 = new org.cruxframework.crux.widgets.client.grid.WidgetColumnDefinition(mesg4.listAction(), "", widgetColumnCreator0, true, false, com.google.gwt.user.client.ui.HasHorizontalAlignment.ALIGN_CENTER, com.google.gwt.user.client.ui.HasVerticalAlignment.ALIGN_MIDDLE);
    defs0.add(org.cruxframework.crux.core.client.screen.DeviceAdaptive.Size.large, "actionColumn", def7);org.cruxframework.crux.widgets.client.grid.ColumnDefinition def8 = new org.cruxframework.crux.widgets.client.grid.DataColumnDefinition("Id", "", null, true, true, true, false, com.google.gwt.user.client.ui.HasHorizontalAlignment.ALIGN_CENTER, com.google.gwt.user.client.ui.HasVerticalAlignment.ALIGN_MIDDLE, null);
    defs0.add(org.cruxframework.crux.core.client.screen.DeviceAdaptive.Size.small, "id", def8);org.cruxframework.crux.widgets.client.deviceadaptivegrid.DeviceAdaptiveGrid widget31 = new org.cruxframework.crux.widgets.client.deviceadaptivegrid.DeviceAdaptiveGrid();
    widget31.initGrid(defs0, 2147483647, org.cruxframework.crux.widgets.client.grid.RowSelectionModel.multipleCheckBox, 1, false, false, false, null, false, null, null,false,false,false,null);
    widget31.setHeight("");
    widget31.setWidth("100%");
    __view.addWidget("gridSample", widget31);
    org.cruxframework.crux.core.client.datasource.PagedDataSource<org.cruxframework.bookmark.client.dto.BookmarkDTO> dataSource0 = (org.cruxframework.crux.core.client.datasource.PagedDataSource<org.cruxframework.bookmark.client.dto.BookmarkDTO>) __view.createDataSource("bookmarkDS");
    org.cruxframework.crux.core.client.datasource.ColumnDefinitions<org.cruxframework.bookmark.client.dto.BookmarkDTO> colDefs0 = new org.cruxframework.crux.core.client.datasource.ColumnDefinitions<org.cruxframework.bookmark.client.dto.BookmarkDTO>();
    dataSource0.setColumnDefinitions(colDefs0);
    colDefs0.addColumn(new org.cruxframework.crux.core.client.datasource.ColumnDefinition<java.lang.Long,org.cruxframework.bookmark.client.dto.BookmarkDTO>("id",true){
      public java.lang.Long getValue(org.cruxframework.bookmark.client.dto.BookmarkDTO recordObject){
        return recordObject.getId();
      }
    });
    colDefs0.addColumn(new org.cruxframework.crux.core.client.datasource.ColumnDefinition<java.lang.String,org.cruxframework.bookmark.client.dto.BookmarkDTO>("description",true){
      public java.lang.String getValue(org.cruxframework.bookmark.client.dto.BookmarkDTO recordObject){
        return recordObject.getDescription();
      }
    });
    colDefs0.addColumn(new org.cruxframework.crux.core.client.datasource.ColumnDefinition<java.lang.String,org.cruxframework.bookmark.client.dto.BookmarkDTO>("link",true){
      public java.lang.String getValue(org.cruxframework.bookmark.client.dto.BookmarkDTO recordObject){
        return recordObject.getLink();
      }
    });
    colDefs0.addColumn(new org.cruxframework.crux.core.client.datasource.ColumnDefinition<java.lang.Long,org.cruxframework.bookmark.client.dto.BookmarkDTO>("id",true){
      public java.lang.Long getValue(org.cruxframework.bookmark.client.dto.BookmarkDTO recordObject){
        return recordObject.getId();
      }
    });
    widget31.setDataSource(dataSource0);
    widget31.setWidth("100%");
    widget31.setStyleName("grid");
    widget27.add(widget31);
    widget27.setCellHorizontalAlignment(widget31, com.google.gwt.user.client.ui.HasHorizontalAlignment.ALIGN_CENTER);
    widget27.setCellVerticalAlignment(widget31, com.google.gwt.user.client.ui.HasVerticalAlignment.ALIGN_MIDDLE);
    widget26.add(widget27);
    if (com.google.gwt.logging.client.LogConfiguration.loggingIsEnabled()){
      logger3.info(Crux.getMessages().viewContainerViewCreated(getId()));
    }
  }
  protected void render(com.google.gwt.user.client.ui.Panel rootPanel5, final org.cruxframework.crux.core.client.screen.views.View.RenderCallback renderCallback) throws InterfaceConfigException{
    if (this.viewPanel0 == null){
      this.viewPanel0 = new com.google.gwt.user.client.ui.HTMLPanel(" <div id=\"_crux_"+__view.getPrefix()+"outerPanel\"></div> ");
      rootPanel5.add(this.viewPanel0);
      this.viewPanel0.addAndReplaceElement(widgets.get("outerPanel"), ViewFactoryUtils.getEnclosingPanelId("outerPanel", __view));
      renderCallback.onRendered();
    }
    else {
      rootPanel5.add(this.viewPanel0);
      renderCallback.onRendered();
    }
    if(!StringUtils.isEmpty(this.width)){
      updateViewWidth(this.width);
    }
    if(!StringUtils.isEmpty(this.height)){
      updateViewHeight(this.height);
    }
    if (com.google.gwt.logging.client.LogConfiguration.loggingIsEnabled()){
      logger3.info(Crux.getMessages().viewContainerViewRendered(getId()));
    }
  }
  protected void updateViewHeight(String height){
    if (this.viewPanel0 != null){
      this.viewPanel0.setHeight(height);
    }
  }
  protected void updateViewWidth(String width){
    if (this.viewPanel0 != null){
      this.viewPanel0.setWidth(width);
    }
  }
  protected native org.cruxframework.crux.core.client.collection.Map<String> initializeLazyDependencies()/*-{
  return {};
}-*/;
public org.cruxframework.crux.core.client.ioc.IocContainer getIocContainer(){
  return iocContainer;
}
public org.cruxframework.crux.core.client.ioc.IocContainer_listbookmark_largeDisplayMouse getTypedIocContainer(){
  return iocContainer;
}
private org.cruxframework.crux.core.client.controller.RegisteredControllers registeredControllers;
private org.cruxframework.crux.core.client.datasource.RegisteredDataSources registeredDataSources;
protected org.cruxframework.crux.core.client.ioc.IocContainer_listbookmark_largeDisplayMouse iocContainer;
private org.cruxframework.bookmark.client.shared.messages.CommonMessages mesg4 = GWT.create(org.cruxframework.bookmark.client.shared.messages.CommonMessages.class);
private final View __view = this;
private static java.util.logging.Logger logger3 = java.util.logging.Logger.getLogger(listbookmark_largeDisplayMouse.class.getName());
private com.google.gwt.user.client.ui.HTMLPanel viewPanel0 = null;
}
