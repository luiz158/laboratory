package org.cruxframework.crux.core.client.screen.views;

import org.cruxframework.crux.core.client.Crux;
import com.google.gwt.core.client.GWT;
import org.cruxframework.crux.core.client.screen.views.View;

public class bookmarkResources_largeDisplayMouse {
  public static void init(){
    if (!View.containsResource("bookmarkResources")){
      org.cruxframework.bookmark.client.resource.common.ResourcesCommon resource4= GWT.create(org.cruxframework.bookmark.client.resource.common.ResourcesCommon.class);
      resource4.css().ensureInjected();
      if (com.google.gwt.logging.client.LogConfiguration.loggingIsEnabled()){
        logger9.info(Crux.getMessages().resourceCsssInjected("CssCommon"));
      }
      View.addResource("bookmarkResources", resource4);
      if (com.google.gwt.logging.client.LogConfiguration.loggingIsEnabled()){
        logger9.info(Crux.getMessages().resourcesInitialized("bookmarkResources"));
      }
    }
  }
  private static java.util.logging.Logger logger9 = java.util.logging.Logger.getLogger(bookmarkResources_largeDisplayMouse.class.getName());
}
