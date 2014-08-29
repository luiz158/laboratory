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

public class org_cruxframework_bookmark_client_dto_BookmarkDTO_JsonEncoder {
  public JSONValue encode(org.cruxframework.bookmark.client.dto.BookmarkDTO object){
    JSONValue json4 = JSONNull.getInstance();
    if (object != null){
      json4 = new JSONObject();
      if (json4 != null && !JSONNull.getInstance().equals(json4)){
        if (object.getDescription() != null){
          json4.isObject().put("description", new org.cruxframework.crux.core.client.bean.java_lang_String_JsonEncoder().encode(object.getDescription()));
        }
        if (object.getId() != null){
          json4.isObject().put("id", new org.cruxframework.crux.core.client.bean.java_lang_Long_JsonEncoder().encode(object.getId()));
        }
        if (object.getLink() != null){
          json4.isObject().put("link", new org.cruxframework.crux.core.client.bean.java_lang_String_JsonEncoder().encode(object.getLink()));
        }
      }
    }
    return json4;
  }
  public org.cruxframework.bookmark.client.dto.BookmarkDTO decode(JSONValue json){
    org.cruxframework.bookmark.client.dto.BookmarkDTO o5 = null;
    if (json != null && json.isNull() == null){
      JSONObject jsonObject0 = json.isObject();
      o5 = GWT.create(org.cruxframework.bookmark.client.dto.BookmarkDTO.class);
      if (jsonObject0 != null) {
        o5.setDescription(new org.cruxframework.crux.core.client.bean.java_lang_String_JsonEncoder().decode(jsonObject0.get("description")));
        o5.setId(new org.cruxframework.crux.core.client.bean.java_lang_Long_JsonEncoder().decode(jsonObject0.get("id")));
        o5.setLink(new org.cruxframework.crux.core.client.bean.java_lang_String_JsonEncoder().decode(jsonObject0.get("link")));
      }
    }
    return o5;
  }
}
