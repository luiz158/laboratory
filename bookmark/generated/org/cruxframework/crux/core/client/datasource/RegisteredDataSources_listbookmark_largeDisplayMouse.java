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

public class RegisteredDataSources_listbookmark_largeDisplayMouse implements org.cruxframework.crux.core.client.datasource.RegisteredDataSources {
  public RegisteredDataSources_listbookmark_largeDisplayMouse(org.cruxframework.crux.core.client.screen.views.View view, org.cruxframework.crux.core.client.ioc.IocContainer_listbookmark_largeDisplayMouse iocContainer){
    this.view = view;
    this.iocContainer = iocContainer;
  }
  public DataSource<?> getDataSource(String id){
    if(id==null){
      throw new DataSourceExcpetion("DataSource not found: "+id);
    }
    if(StringUtils.unsafeEquals("bookmarkDS",id)){
      org.cruxframework.bookmark.client.datasource.BookmarkDS_DataSourceProxy __dat  = new org.cruxframework.bookmark.client.datasource.BookmarkDS_DataSourceProxy(this.view);
      return __dat;
    }
    throw new DataSourceExcpetion("DataSource not found: "+id);
  }
  private org.cruxframework.crux.core.client.screen.views.View view;
  private org.cruxframework.crux.core.client.ioc.IocContainer_listbookmark_largeDisplayMouse iocContainer;
}
