package org.cruxframework.bookmark.client.service;

import java.util.logging.Level;
import java.util.logging.Logger;
import com.google.gwt.logging.client.LogConfiguration;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.Response;
import com.google.gwt.core.client.JsonUtils;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.http.client.URL;
import org.cruxframework.crux.core.client.Crux;
import org.cruxframework.crux.core.client.rest.Callback;
import org.cruxframework.crux.core.client.utils.StringUtils;
import org.cruxframework.crux.core.client.rest.RestError;
import com.google.gwt.jsonp.client.JsonpRequestBuilder;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class BookmarkRestClient_Impl implements org.cruxframework.bookmark.client.service.BookmarkRestClient {
  public BookmarkRestClient_Impl(){
    __hostPath = com.google.gwt.core.client.GWT.getModuleBaseURL();
    __hostPath = __hostPath.substring(0, __hostPath.lastIndexOf(com.google.gwt.core.client.GWT.getModuleName()));
  }
  
  public void list(final org.cruxframework.crux.core.client.rest.Callback<java.util.List<org.cruxframework.bookmark.client.dto.BookmarkDTO>> callback) {
    String baseURIPath = "rest/bookmark/{name}";
    final String restURI = __hostPath + baseURIPath;
    RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, restURI);
    String _locale = org.cruxframework.crux.core.client.screen.Screen.getLocale();
    if (_locale != null && !org.cruxframework.crux.core.client.utils.StringUtils.unsafeEquals(_locale, "default")){
      builder.setHeader("Accept-Language", _locale.replace('_', '-'));
    }
    builder.setCallback(new RequestCallback(){
      public void onResponseReceived(Request request, Response response){
        int s = (response.getStatusCode()-200);
        if (s >= 0 && s < 10){
          String jsonText = response.getText();
          if (Response.SC_NO_CONTENT != response.getStatusCode() && !org.cruxframework.crux.core.client.utils.StringUtils.isEmpty(jsonText)){
            try{
              JSONValue jsonValue = JSONParser.parseStrict(jsonText);
              java.util.List<org.cruxframework.bookmark.client.dto.BookmarkDTO> result = new org.cruxframework.crux.core.client.bean.java_util_List_org_cruxframework_bookmark_client_dto_BookmarkDTO__JsonEncoder().decode(jsonValue);
              callback.onSuccess(result);
            }catch (Exception e){
              if (LogConfiguration.loggingIsEnabled()){
                __log.log(Level.SEVERE, e.getMessage(), e);
              }
            }
          }else {
            callback.onSuccess(null);
          }
        }else{ 
          if (LogConfiguration.loggingIsEnabled()){
            __log.log(Level.SEVERE, "Error received from service: "+response.getText());
          }
          JSONObject jsonObject = null;
          try {
            jsonObject = JSONParser.parseStrict(response.getText()).isObject();
          } catch (Exception exception) {
            callback.onError(new RestError(response.getStatusCode(), response.getText()));
            return;
          }
          callback.onError(new RestError(response.getStatusCode(), (jsonObject.get("message") != null && jsonObject.get("message").isString() != null) ? jsonObject.get("message").isString().stringValue() : ""));
        }
      }
      public void onError(Request request, Throwable exception){
        callback.onError(new RestError(-1, Crux.getMessages().restServiceUnexpectedError(exception.getMessage())));
      }
    });
    try{
      builder.send();
    }catch (Exception e){
      callback.onError(new RestError(-1, Crux.getMessages().restServiceUnexpectedError(e.getMessage())));
    }
  }
  
  public void add(java.lang.String description, java.lang.String link, final org.cruxframework.crux.core.client.rest.Callback<java.lang.String> callback) {
    String baseURIPath = "rest/bookmark/{id}";
    final String restURI = __hostPath + baseURIPath;
    RequestBuilder builder = new RequestBuilder(RequestBuilder.PUT, restURI);
    String _locale = org.cruxframework.crux.core.client.screen.Screen.getLocale();
    if (_locale != null && !org.cruxframework.crux.core.client.utils.StringUtils.unsafeEquals(_locale, "default")){
      builder.setHeader("Accept-Language", _locale.replace('_', '-'));
    }
    builder.setCallback(new RequestCallback(){
      public void onResponseReceived(Request request, Response response){
        int s = (response.getStatusCode()-200);
        if (s >= 0 && s < 10){
          String jsonText = response.getText();
          if (Response.SC_NO_CONTENT != response.getStatusCode() && !org.cruxframework.crux.core.client.utils.StringUtils.isEmpty(jsonText)){
            try{
              JSONValue jsonValue = JSONParser.parseStrict(jsonText);
              java.lang.String result = new org.cruxframework.crux.core.client.bean.java_lang_String_JsonEncoder().decode(jsonValue);
              callback.onSuccess(result);
            }catch (Exception e){
              if (LogConfiguration.loggingIsEnabled()){
                __log.log(Level.SEVERE, e.getMessage(), e);
              }
            }
          }else {
            callback.onSuccess(null);
          }
        }else{ 
          if (LogConfiguration.loggingIsEnabled()){
            __log.log(Level.SEVERE, "Error received from service: "+response.getText());
          }
          JSONObject jsonObject = null;
          try {
            jsonObject = JSONParser.parseStrict(response.getText()).isObject();
          } catch (Exception exception) {
            callback.onError(new RestError(response.getStatusCode(), response.getText()));
            return;
          }
          callback.onError(new RestError(response.getStatusCode(), (jsonObject.get("message") != null && jsonObject.get("message").isString() != null) ? jsonObject.get("message").isString().stringValue() : ""));
        }
      }
      public void onError(Request request, Throwable exception){
        callback.onError(new RestError(-1, Crux.getMessages().restServiceUnexpectedError(exception.getMessage())));
      }
    });
    try{
      String requestData = "description={description}&link={link}";
      builder.setHeader("Content-Type", "application/x-www-form-urlencoded");
      requestData=requestData.replace("{description}", URL.encodePathSegment((description!=null&&!description.trim().equals("")?description:"")));
      requestData=requestData.replace("{link}", URL.encodePathSegment((link!=null&&!link.trim().equals("")?link:"")));
      builder.setRequestData(requestData);
      builder.setHeader("X-XSRF", "1");
      builder.send();
    }catch (Exception e){
      callback.onError(new RestError(-1, Crux.getMessages().restServiceUnexpectedError(e.getMessage())));
    }
  }
  
  public void remove(java.util.List<org.cruxframework.bookmark.client.dto.BookmarkDTO> list, final org.cruxframework.crux.core.client.rest.Callback<java.lang.String> callback) {
    String baseURIPath = "rest/bookmark/{id}";
    final String restURI = __hostPath + baseURIPath;
    RequestBuilder builder = new RequestBuilder(RequestBuilder.DELETE, restURI);
    String _locale = org.cruxframework.crux.core.client.screen.Screen.getLocale();
    if (_locale != null && !org.cruxframework.crux.core.client.utils.StringUtils.unsafeEquals(_locale, "default")){
      builder.setHeader("Accept-Language", _locale.replace('_', '-'));
    }
    builder.setCallback(new RequestCallback(){
      public void onResponseReceived(Request request, Response response){
        int s = (response.getStatusCode()-200);
        if (s >= 0 && s < 10){
          String jsonText = response.getText();
          if (Response.SC_NO_CONTENT != response.getStatusCode() && !org.cruxframework.crux.core.client.utils.StringUtils.isEmpty(jsonText)){
            try{
              JSONValue jsonValue = JSONParser.parseStrict(jsonText);
              java.lang.String result = new org.cruxframework.crux.core.client.bean.java_lang_String_JsonEncoder().decode(jsonValue);
              callback.onSuccess(result);
            }catch (Exception e){
              if (LogConfiguration.loggingIsEnabled()){
                __log.log(Level.SEVERE, e.getMessage(), e);
              }
            }
          }else {
            callback.onSuccess(null);
          }
        }else{ 
          if (LogConfiguration.loggingIsEnabled()){
            __log.log(Level.SEVERE, "Error received from service: "+response.getText());
          }
          JSONObject jsonObject = null;
          try {
            jsonObject = JSONParser.parseStrict(response.getText()).isObject();
          } catch (Exception exception) {
            callback.onError(new RestError(response.getStatusCode(), response.getText()));
            return;
          }
          callback.onError(new RestError(response.getStatusCode(), (jsonObject.get("message") != null && jsonObject.get("message").isString() != null) ? jsonObject.get("message").isString().stringValue() : ""));
        }
      }
      public void onError(Request request, Throwable exception){
        callback.onError(new RestError(-1, Crux.getMessages().restServiceUnexpectedError(exception.getMessage())));
      }
    });
    try{
      builder.setHeader("Content-Type", "application/json");
      JSONValue serialized = new org.cruxframework.crux.core.client.bean.java_util_List_org_cruxframework_bookmark_client_dto_BookmarkDTO__JsonEncoder().encode(list);
      String requestData = (serialized==null||serialized.isNull()!=null)?null:serialized.toString();
      builder.setRequestData(requestData);
      builder.setHeader("X-XSRF", "1");
      builder.send();
    }catch (Exception e){
      callback.onError(new RestError(-1, Crux.getMessages().restServiceUnexpectedError(e.getMessage())));
    }
  }
  
  public void updateData(java.lang.Long id, java.lang.String description, java.lang.String link, final org.cruxframework.crux.core.client.rest.Callback<java.lang.String> callback) {
    String baseURIPath = "rest/bookmark/{id}";
    final String restURI = __hostPath + baseURIPath;
    RequestBuilder builder = new RequestBuilder(RequestBuilder.POST, restURI);
    String _locale = org.cruxframework.crux.core.client.screen.Screen.getLocale();
    if (_locale != null && !org.cruxframework.crux.core.client.utils.StringUtils.unsafeEquals(_locale, "default")){
      builder.setHeader("Accept-Language", _locale.replace('_', '-'));
    }
    builder.setCallback(new RequestCallback(){
      public void onResponseReceived(Request request, Response response){
        int s = (response.getStatusCode()-200);
        if (s >= 0 && s < 10){
          String jsonText = response.getText();
          if (Response.SC_NO_CONTENT != response.getStatusCode() && !org.cruxframework.crux.core.client.utils.StringUtils.isEmpty(jsonText)){
            try{
              JSONValue jsonValue = JSONParser.parseStrict(jsonText);
              java.lang.String result = new org.cruxframework.crux.core.client.bean.java_lang_String_JsonEncoder().decode(jsonValue);
              callback.onSuccess(result);
            }catch (Exception e){
              if (LogConfiguration.loggingIsEnabled()){
                __log.log(Level.SEVERE, e.getMessage(), e);
              }
            }
          }else {
            callback.onSuccess(null);
          }
        }else{ 
          if (LogConfiguration.loggingIsEnabled()){
            __log.log(Level.SEVERE, "Error received from service: "+response.getText());
          }
          JSONObject jsonObject = null;
          try {
            jsonObject = JSONParser.parseStrict(response.getText()).isObject();
          } catch (Exception exception) {
            callback.onError(new RestError(response.getStatusCode(), response.getText()));
            return;
          }
          callback.onError(new RestError(response.getStatusCode(), (jsonObject.get("message") != null && jsonObject.get("message").isString() != null) ? jsonObject.get("message").isString().stringValue() : ""));
        }
      }
      public void onError(Request request, Throwable exception){
        callback.onError(new RestError(-1, Crux.getMessages().restServiceUnexpectedError(exception.getMessage())));
      }
    });
    try{
      String requestData = "id={id}&description={description}&link={link}";
      builder.setHeader("Content-Type", "application/x-www-form-urlencoded");
      requestData=requestData.replace("{id}", URL.encodePathSegment((id!=null?(""+id):"")));
      requestData=requestData.replace("{description}", URL.encodePathSegment((description!=null&&!description.trim().equals("")?description:"")));
      requestData=requestData.replace("{link}", URL.encodePathSegment((link!=null&&!link.trim().equals("")?link:"")));
      builder.setRequestData(requestData);
      builder.setHeader("X-XSRF", "1");
      builder.send();
    }catch (Exception e){
      callback.onError(new RestError(-1, Crux.getMessages().restServiceUnexpectedError(e.getMessage())));
    }
  }
  public void setEndpoint(String address){
    this.__hostPath = address;
    if (__hostPath.endsWith("/")){
      __hostPath = __hostPath.substring(0, __hostPath.length()-1);
    }
  }
  public String getBoundCruxViewId(){
    return this.__view;
  }
  
  public org.cruxframework.crux.core.client.screen.views.View getBoundCruxView(){
    return (this.__view!=null?org.cruxframework.crux.core.client.screen.views.View.getView(this.__view):null);
  }
  
  public void bindCruxView(String view){
    this.__view = view;
  }
  
  private String __hostPath;
  private static Logger __log = Logger.getLogger(org.cruxframework.bookmark.client.service.BookmarkRestClient_Impl.class.getName());
  private String __view;
}
