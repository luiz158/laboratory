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

public class RegisteredDataSources_bookmark_WEB_INF_classes_org_cruxframework_bookmark_public_index_html_largeDisplayMouse implements org.cruxframework.crux.core.client.datasource.RegisteredDataSources {
  public RegisteredDataSources_bookmark_WEB_INF_classes_org_cruxframework_bookmark_public_index_html_largeDisplayMouse(org.cruxframework.crux.core.client.screen.views.View view, org.cruxframework.crux.core.client.ioc.IocContainer_bookmark_WEB_INF_classes_org_cruxframework_bookmark_public_index_html_largeDisplayMouse iocContainer){
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
  private org.cruxframework.crux.core.client.ioc.IocContainer_bookmark_WEB_INF_classes_org_cruxframework_bookmark_public_index_html_largeDisplayMouse iocContainer;
}
