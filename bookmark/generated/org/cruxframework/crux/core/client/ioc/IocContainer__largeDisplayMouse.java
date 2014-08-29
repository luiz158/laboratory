package org.cruxframework.crux.core.client.ioc;

import org.cruxframework.crux.core.client.screen.views.View;
import com.google.gwt.core.client.GWT;
import org.cruxframework.crux.core.client.ioc.IoCContainerException;
import org.cruxframework.crux.core.client.ioc.IoCResource.Scope;
import org.cruxframework.crux.core.client.utils.StringUtils;
import org.cruxframework.crux.core.client.ioc.IocContainer;

public class IocContainer__largeDisplayMouse implements org.cruxframework.crux.core.client.ioc.RuntimeIoCContainer {
  public IocContainer__largeDisplayMouse(){
  }
  public <T> T get(Class<T> clazz){
    String className = clazz.getName();
    throw new IoCContainerException("Class not bound to IoCContainer ["+className+"]");
  }
  public <T> T get(Class<T> clazz, Scope scope, String subscope){
    String className = clazz.getName();
    throw new IoCContainerException("Class not bound to IoCContainer ["+className+"]");
  }
  public void setIoCContainer(IocContainer iocContainer) {
    baseContainer = iocContainer;
  }
  protected IocScope _getScope(Scope scope){
    return (baseContainer==null?null:baseContainer._getScope(scope));
  }
  private IocContainer baseContainer;
}
