package org.cruxframework.crux.core.client.screen.views;

import org.cruxframework.crux.core.client.Crux;
import com.google.gwt.core.client.GWT;
import org.cruxframework.crux.core.client.screen.views.View;

public class smartFacesResources_largeDisplayMouse {
  public static void init(){
    if (!View.containsResource("smartFacesResources")){
      org.cruxframework.crux.smartfaces.themes.client.large.SmartFacesResourcesLarge resource2= GWT.create(org.cruxframework.crux.smartfaces.themes.client.large.SmartFacesResourcesLarge.class);
      resource2.css().ensureInjected();
      if (com.google.gwt.logging.client.LogConfiguration.loggingIsEnabled()){
        logger7.info(Crux.getMessages().resourceCsssInjected("CssResource"));
      }
      View.addResource("smartFacesResources", resource2);
      if (com.google.gwt.logging.client.LogConfiguration.loggingIsEnabled()){
        logger7.info(Crux.getMessages().resourcesInitialized("smartFacesResources"));
      }
    }
  }
  private static java.util.logging.Logger logger7 = java.util.logging.Logger.getLogger(smartFacesResources_largeDisplayMouse.class.getName());
}
