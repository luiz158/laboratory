package org.cruxframework.crux.core.client.bean;

import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONNull;
import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONBoolean;
import com.google.gwt.json.client.JSONString;
import org.cruxframework.crux.core.client.utils.JsUtils;
import com.google.gwt.core.client.GWT;

public class java_lang_Long_JsonEncoder {
  public JSONValue encode(java.lang.Long object){
    JSONValue json6 = JSONNull.getInstance();
    if (object != null){
      json6 = new JSONNumber(object);
    }
    return json6;
  }
  public java.lang.Long decode(JSONValue json){
    java.lang.Long o4 = null;
    if (json != null && json.isNull() == null){
      o4 = Long.parseLong(json.toString());
    }
    return o4;
  }
}
