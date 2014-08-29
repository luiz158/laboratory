package org.cruxframework.bookmark.client.resource.common;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ResourcePrototype;

public class ResourcesCommon_en_StaticClientBundleGenerator implements org.cruxframework.bookmark.client.resource.common.ResourcesCommon {
  private static ResourcesCommon_en_StaticClientBundleGenerator _instance0 = new ResourcesCommon_en_StaticClientBundleGenerator();
  private void cssInitializer() {
    css = new org.cruxframework.bookmark.client.resource.common.CssCommon() {
      private boolean injected;
      public boolean ensureInjected() {
        if (!injected) {
          injected = true;
          com.google.gwt.dom.client.StyleInjector.inject(getText());
          return true;
        }
        return false;
      }
      public String getName() {
        return "css";
      }
      public String getText() {
        return com.google.gwt.i18n.client.LocaleInfo.getCurrentLocale().isRTL() ? (("body{background-color:" + ("#f1f1f1")  + " !important;}.teste{background-color:" + ("#fff")  + ";}.spanel{width:" + ("300px")  + ";padding:" + ("20px")  + ";float:" + ("right")  + ";}.header{padding:" + ("10px"+ " " +"0")  + ";position:" + ("relative")  + ";}.header-logo{background:" + ("url('" + ResourcesCommon_en_StaticClientBundleGenerator.this.headerLogo().getSafeUri().asString() + "')"+ " " +"no-repeat")  + ";width:" + ("60px")  + ";height:" + ("60px")  + ";margin-right:") + (("20px")  + ";display:" + ("block")  + ";}.header-ptbr{background:" + ("url('" + ResourcesCommon_en_StaticClientBundleGenerator.this.headerPtbr().getSafeUri().asString() + "')"+ " " +"no-repeat")  + ";width:" + ("32px")  + ";height:" + ("32px")  + ";margin-right:" + ("20px")  + ";display:" + ("block")  + ";}.header-en{background:" + ("url('" + ResourcesCommon_en_StaticClientBundleGenerator.this.headerEn().getSafeUri().asString() + "')"+ " " +"no-repeat")  + ";width:" + ("32px")  + ";height:" + ("32px")  + ";margin-right:" + ("20px") ) + (";display:" + ("block")  + ";}.header-menu{clear:" + ("both")  + ";padding-top:" + ("30px")  + ";padding-right:" + ("20px")  + ";}.styledpanel{width:" + ("100%")  + ";margin:" + ("10px")  + ";}.grid,.grid .crux-Grid>div>table,.verticalpanel{width:" + ("100%")  + ";}.form{width:" + ("400px")  + ";}.buttonbookmarklist{float:" + ("right")  + ";background:" + ("linear-gradient(" + "rgba(" + "0"+ ","+ " " +"0"+ ","+ " " +"0"+ ","+ " " +"0" + ")"+ ","+ " " +"rgba(" + "0"+ ","+ " " +"0"+ ","+ " " +"0"+ ","+ " " +"0.1" + ")" + ")"+ " " +"#4496c6")  + ";box-shadow:") + (("0"+ " " +"1px"+ " " +"0"+ " " +"rgba(" + "255"+ ","+ " " +"255"+ ","+ " " +"255"+ ","+ " " +"0.1" + ")"+ " " +"inset")  + ";margin:" + ("0"+ " " +"2px")  + ";border:" + ("1px"+ " " +"solid"+ " " +"#1a6299")  + ";padding:" + ("8px"+ " " +"14px")  + ";border-radius:" + ("1px")  + ";font-size:" + ("100%")  + ";color:" + ("#fff")  + ";text-shadow:" + ("0"+ " " +"1px"+ " " +"0"+ " " +"rgba(" + "0"+ ","+ " " +"0"+ ","+ " " +"0"+ ","+ " " +"0.15" + ")")  + ";width:" + ("auto")  + ";}.faces-MessageBox .faces-Button,.faces-Confirm .faces-Button,.c-btn.stroke{border-radius:" + ("4px")  + ";border:" + ("1px"+ " " +"solid"+ " " +"#777") ) + (";color:" + ("#444")  + ";padding:" + ("5px"+ " " +"20px")  + ";margin:" + ("20px"+ " " +"auto"+ " " +"0")  + ";background:" + ("transparent")  + ";font-size:" + ("15px")  + ";}.faces-MessageBox .faces-Button:hover,.faces-Confirm .faces-Button:hover,.c-btn.stroke:hover{background:" + ("rgba(" + "0"+ ","+ " " +"0"+ ","+ " " +"0"+ ","+ " " +"0.1" + ")")  + ";}.faces-MessageBox .dialogCloseButton{display:" + ("none")  + ";}.faces-MessageBox-success .popupContent{background:" + ("svgIconSuccess"+ " " +"no-repeat"+ " " +"center"+ " " +"20px"+ " " +"#cdcfd2")  + ";background-size:" + ("62px"+ " " +"auto")  + ";padding-top:" + ("90px")  + ";}.faces-MessageBox-success .faces-Button,.c-btn.stroke.success{border-radius:") + (("4px")  + ";border:" + ("1px"+ " " +"solid"+ " " +"#42a846")  + ";color:" + ("#1c571e")  + ";padding:" + ("5px"+ " " +"20px")  + ";margin:" + ("20px"+ " " +"auto"+ " " +"0")  + ";background:" + ("transparent")  + ";transition:" + ("background"+ " " +"0.1s"+ " " +"linear")  + ";}.faces-MessageBox-success .faces-Button:hover,.c-btn.stroke.success:hover{background:" + ("rgba(" + "33"+ ","+ " " +"165"+ ","+ " " +"38"+ ","+ " " +"0.2" + ")")  + ";}.faces-MessageBox-error .popupContent{background:" + ("svgIconError"+ " " +"no-repeat"+ " " +"center"+ " " +"20px"+ " " +"#cdcfd2")  + ";background-size:" + ("62px"+ " " +"auto")  + ";padding-top:" + ("90px") ) + (";}.faces-MessageBox-error .faces-Button,.c-btn.stroke.error{border-radius:" + ("4px")  + ";border:" + ("1px"+ " " +"solid"+ " " +"#a84242")  + ";color:" + ("#561313")  + ";padding:" + ("5px"+ " " +"20px")  + ";margin:" + ("20px"+ " " +"auto"+ " " +"0")  + ";background:" + ("transparent")  + ";}.faces-MessageBox-error .faces-Button:hover,.c-btn.stroke.error:hover{background:" + ("rgba(" + "165"+ ","+ " " +"33"+ ","+ " " +"33"+ ","+ " " +"0.2" + ")")  + ";}")) : (("body{background-color:" + ("#f1f1f1")  + " !important;}.teste{background-color:" + ("#fff")  + ";}.spanel{width:" + ("300px")  + ";padding:" + ("20px")  + ";float:" + ("left")  + ";}.header{padding:" + ("10px"+ " " +"0")  + ";position:" + ("relative")  + ";}.header-logo{background:" + ("url('" + ResourcesCommon_en_StaticClientBundleGenerator.this.headerLogo().getSafeUri().asString() + "')"+ " " +"no-repeat")  + ";width:" + ("60px")  + ";height:" + ("60px")  + ";margin-left:") + (("20px")  + ";display:" + ("block")  + ";}.header-ptbr{background:" + ("url('" + ResourcesCommon_en_StaticClientBundleGenerator.this.headerPtbr().getSafeUri().asString() + "')"+ " " +"no-repeat")  + ";width:" + ("32px")  + ";height:" + ("32px")  + ";margin-left:" + ("20px")  + ";display:" + ("block")  + ";}.header-en{background:" + ("url('" + ResourcesCommon_en_StaticClientBundleGenerator.this.headerEn().getSafeUri().asString() + "')"+ " " +"no-repeat")  + ";width:" + ("32px")  + ";height:" + ("32px")  + ";margin-left:" + ("20px") ) + (";display:" + ("block")  + ";}.header-menu{clear:" + ("both")  + ";padding-top:" + ("30px")  + ";padding-left:" + ("20px")  + ";}.styledpanel{width:" + ("100%")  + ";margin:" + ("10px")  + ";}.grid,.grid .crux-Grid>div>table,.verticalpanel{width:" + ("100%")  + ";}.form{width:" + ("400px")  + ";}.buttonbookmarklist{float:" + ("left")  + ";background:" + ("linear-gradient(" + "rgba(" + "0"+ ","+ " " +"0"+ ","+ " " +"0"+ ","+ " " +"0" + ")"+ ","+ " " +"rgba(" + "0"+ ","+ " " +"0"+ ","+ " " +"0"+ ","+ " " +"0.1" + ")" + ")"+ " " +"#4496c6")  + ";box-shadow:") + (("0"+ " " +"1px"+ " " +"0"+ " " +"rgba(" + "255"+ ","+ " " +"255"+ ","+ " " +"255"+ ","+ " " +"0.1" + ")"+ " " +"inset")  + ";margin:" + ("0"+ " " +"2px")  + ";border:" + ("1px"+ " " +"solid"+ " " +"#1a6299")  + ";padding:" + ("8px"+ " " +"14px")  + ";border-radius:" + ("1px")  + ";font-size:" + ("100%")  + ";color:" + ("#fff")  + ";text-shadow:" + ("0"+ " " +"1px"+ " " +"0"+ " " +"rgba(" + "0"+ ","+ " " +"0"+ ","+ " " +"0"+ ","+ " " +"0.15" + ")")  + ";width:" + ("auto")  + ";}.faces-MessageBox .faces-Button,.faces-Confirm .faces-Button,.c-btn.stroke{border-radius:" + ("4px")  + ";border:" + ("1px"+ " " +"solid"+ " " +"#777") ) + (";color:" + ("#444")  + ";padding:" + ("5px"+ " " +"20px")  + ";margin:" + ("20px"+ " " +"auto"+ " " +"0")  + ";background:" + ("transparent")  + ";font-size:" + ("15px")  + ";}.faces-MessageBox .faces-Button:hover,.faces-Confirm .faces-Button:hover,.c-btn.stroke:hover{background:" + ("rgba(" + "0"+ ","+ " " +"0"+ ","+ " " +"0"+ ","+ " " +"0.1" + ")")  + ";}.faces-MessageBox .dialogCloseButton{display:" + ("none")  + ";}.faces-MessageBox-success .popupContent{background:" + ("svgIconSuccess"+ " " +"no-repeat"+ " " +"center"+ " " +"20px"+ " " +"#cdcfd2")  + ";background-size:" + ("62px"+ " " +"auto")  + ";padding-top:" + ("90px")  + ";}.faces-MessageBox-success .faces-Button,.c-btn.stroke.success{border-radius:") + (("4px")  + ";border:" + ("1px"+ " " +"solid"+ " " +"#42a846")  + ";color:" + ("#1c571e")  + ";padding:" + ("5px"+ " " +"20px")  + ";margin:" + ("20px"+ " " +"auto"+ " " +"0")  + ";background:" + ("transparent")  + ";transition:" + ("background"+ " " +"0.1s"+ " " +"linear")  + ";}.faces-MessageBox-success .faces-Button:hover,.c-btn.stroke.success:hover{background:" + ("rgba(" + "33"+ ","+ " " +"165"+ ","+ " " +"38"+ ","+ " " +"0.2" + ")")  + ";}.faces-MessageBox-error .popupContent{background:" + ("svgIconError"+ " " +"no-repeat"+ " " +"center"+ " " +"20px"+ " " +"#cdcfd2")  + ";background-size:" + ("62px"+ " " +"auto")  + ";padding-top:" + ("90px") ) + (";}.faces-MessageBox-error .faces-Button,.c-btn.stroke.error{border-radius:" + ("4px")  + ";border:" + ("1px"+ " " +"solid"+ " " +"#a84242")  + ";color:" + ("#561313")  + ";padding:" + ("5px"+ " " +"20px")  + ";margin:" + ("20px"+ " " +"auto"+ " " +"0")  + ";background:" + ("transparent")  + ";}.faces-MessageBox-error .faces-Button:hover,.c-btn.stroke.error:hover{background:" + ("rgba(" + "165"+ ","+ " " +"33"+ ","+ " " +"33"+ ","+ " " +"0.2" + ")")  + ";}"));
      }
    }
    ;
  }
  private static class cssInitializer {
    static {
      _instance0.cssInitializer();
    }
    static org.cruxframework.bookmark.client.resource.common.CssCommon get() {
      return css;
    }
  }
  public org.cruxframework.bookmark.client.resource.common.CssCommon css() {
    return cssInitializer.get();
  }
  private void headerEnInitializer() {
    headerEn = // file:/D:/Desenvolvimento/Outros/Exemplos/SERPRO/bookmark/target/classes/org/cruxframework/bookmark/client/resource/common/header-en.png
    new com.google.gwt.resources.client.impl.DataResourcePrototype(
      "headerEn",
      com.google.gwt.safehtml.shared.UriUtils.fromTrustedString(GWT.getModuleBaseForStaticFiles() + "7F189F37BCBB93ACAD408E7D9D6CF6BF.cache.png")
    );
  }
  private static class headerEnInitializer {
    static {
      _instance0.headerEnInitializer();
    }
    static com.google.gwt.resources.client.DataResource get() {
      return headerEn;
    }
  }
  public com.google.gwt.resources.client.DataResource headerEn() {
    return headerEnInitializer.get();
  }
  private void headerLogoInitializer() {
    headerLogo = // file:/D:/Desenvolvimento/Outros/Exemplos/SERPRO/bookmark/target/classes/org/cruxframework/bookmark/client/resource/common/header-logo.png
    new com.google.gwt.resources.client.impl.DataResourcePrototype(
      "headerLogo",
      com.google.gwt.safehtml.shared.UriUtils.fromTrustedString(GWT.getModuleBaseForStaticFiles() + "875355F9A5479262B679B976796C4425.cache.png")
    );
  }
  private static class headerLogoInitializer {
    static {
      _instance0.headerLogoInitializer();
    }
    static com.google.gwt.resources.client.DataResource get() {
      return headerLogo;
    }
  }
  public com.google.gwt.resources.client.DataResource headerLogo() {
    return headerLogoInitializer.get();
  }
  private void headerPtbrInitializer() {
    headerPtbr = // file:/D:/Desenvolvimento/Outros/Exemplos/SERPRO/bookmark/target/classes/org/cruxframework/bookmark/client/resource/common/header-pt.png
    new com.google.gwt.resources.client.impl.DataResourcePrototype(
      "headerPtbr",
      com.google.gwt.safehtml.shared.UriUtils.fromTrustedString(GWT.getModuleBaseForStaticFiles() + "DF28C820409009745B25E5F39A6E26BE.cache.png")
    );
  }
  private static class headerPtbrInitializer {
    static {
      _instance0.headerPtbrInitializer();
    }
    static com.google.gwt.resources.client.DataResource get() {
      return headerPtbr;
    }
  }
  public com.google.gwt.resources.client.DataResource headerPtbr() {
    return headerPtbrInitializer.get();
  }
  private static java.util.HashMap<java.lang.String, com.google.gwt.resources.client.ResourcePrototype> resourceMap;
  private static org.cruxframework.bookmark.client.resource.common.CssCommon css;
  private static com.google.gwt.resources.client.DataResource headerEn;
  private static com.google.gwt.resources.client.DataResource headerLogo;
  private static com.google.gwt.resources.client.DataResource headerPtbr;
  
  public ResourcePrototype[] getResources() {
    return new ResourcePrototype[] {
      css(), 
      headerEn(), 
      headerLogo(), 
      headerPtbr(), 
    };
  }
  public ResourcePrototype getResource(String name) {
    if (GWT.isScript()) {
      return getResourceNative(name);
    } else {
      if (resourceMap == null) {
        resourceMap = new java.util.HashMap<java.lang.String, com.google.gwt.resources.client.ResourcePrototype>();
        resourceMap.put("css", css());
        resourceMap.put("headerEn", headerEn());
        resourceMap.put("headerLogo", headerLogo());
        resourceMap.put("headerPtbr", headerPtbr());
      }
      return resourceMap.get(name);
    }
  }
  private native ResourcePrototype getResourceNative(String name) /*-{
    switch (name) {
      case 'css': return this.@org.cruxframework.bookmark.client.resource.common.ResourcesCommon::css()();
      case 'headerEn': return this.@org.cruxframework.bookmark.client.resource.common.ResourcesCommon::headerEn()();
      case 'headerLogo': return this.@org.cruxframework.bookmark.client.resource.common.ResourcesCommon::headerLogo()();
      case 'headerPtbr': return this.@org.cruxframework.bookmark.client.resource.common.ResourcesCommon::headerPtbr()();
    }
    return null;
  }-*/;
}
