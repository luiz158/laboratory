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

public class java_lang_String_JsonEncoder {
  public JSONValue encode(java.lang.String object){
    JSONValue json5 = JSONNull.getInstance();
    if (object != null){
      json5 = new JSONString(object);
    }
    return json5;
  }
  public java.lang.String decode(JSONValue json){
    java.lang.String o3 = null;
    if (json != null && json.isNull() == null){
      o3 = json.isString().stringValue();
    }
    return o3;
  }
}
