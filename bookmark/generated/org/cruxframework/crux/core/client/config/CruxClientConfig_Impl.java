package org.cruxframework.crux.core.client.config;

import org.cruxframework.crux.core.client.screen.Screen;

public class CruxClientConfig_Impl implements org.cruxframework.crux.core.client.config.CruxClientConfig {
  public boolean enableDebugForURL(String url){
    return true;
  }
  public boolean enableCrux2OldInterfacesCompatibility(){
    return false;
  }
  public boolean preferWebSQLForNativeDB(){
    return false;
  }
}
