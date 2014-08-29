package org.cruxframework.crux.core.client.datasource;

import com.google.gwt.core.client.GWT;
import org.cruxframework.crux.core.client.screen.Screen;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HasText;
import org.cruxframework.crux.core.client.formatter.HasFormatter;
import org.cruxframework.crux.core.client.datasource.DataSourceExcpetion;
import org.cruxframework.crux.core.client.datasource.DataSourceRecord;
import org.cruxframework.crux.core.client.utils.StringUtils;

public class RegisteredDataSources_menu_largeDisplayMouse implements org.cruxframework.crux.core.client.datasource.RegisteredDataSources {
  public RegisteredDataSources_menu_largeDisplayMouse(org.cruxframework.crux.core.client.screen.views.View view, org.cruxframework.crux.core.client.ioc.IocContainer_menu_largeDisplayMouse iocContainer){
    this.view = view;
    this.iocContainer = iocContainer;
  }
  public DataSource<?> getDataSource(String id){
    if(id==null){
      throw new DataSourceExcpetion("DataSource not found: "+id);
    }
    throw new DataSourceExcpetion("DataSource not found: "+id);
  }
  private org.cruxframework.crux.core.client.screen.views.View view;
  private org.cruxframework.crux.core.client.ioc.IocContainer_menu_largeDisplayMouse iocContainer;
}
