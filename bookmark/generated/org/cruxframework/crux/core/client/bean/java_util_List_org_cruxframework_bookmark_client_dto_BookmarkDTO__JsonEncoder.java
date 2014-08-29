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

public class java_util_List_org_cruxframework_bookmark_client_dto_BookmarkDTO__JsonEncoder {
  public JSONValue encode(java.util.List<org.cruxframework.bookmark.client.dto.BookmarkDTO> object){
    JSONValue json3 = JSONNull.getInstance();
    if (object != null){
      json3 = new JSONArray();
      org.cruxframework.crux.core.client.bean.org_cruxframework_bookmark_client_dto_BookmarkDTO_JsonEncoder serializer1 = new org.cruxframework.crux.core.client.bean.org_cruxframework_bookmark_client_dto_BookmarkDTO_JsonEncoder();
      for (org.cruxframework.bookmark.client.dto.BookmarkDTO obj: object){
        json3.isArray().set(json3.isArray().size(), serializer1.encode(obj));
      }
    }
    return json3;
  }
  public java.util.List<org.cruxframework.bookmark.client.dto.BookmarkDTO> decode(JSONValue json){
    java.util.List<org.cruxframework.bookmark.client.dto.BookmarkDTO> o6 = null;
    if (json != null && json.isNull() == null){
      JSONArray jsonArray0 = json.isArray();
      o6 = new java.util.ArrayList<org.cruxframework.bookmark.client.dto.BookmarkDTO>();
      org.cruxframework.crux.core.client.bean.org_cruxframework_bookmark_client_dto_BookmarkDTO_JsonEncoder serializer2 = new org.cruxframework.crux.core.client.bean.org_cruxframework_bookmark_client_dto_BookmarkDTO_JsonEncoder();
      for (int i=0; i < jsonArray0.size(); i++){
        o6.add(serializer2.decode(jsonArray0.get(i)));
      }
    }
    return o6;
  }
}
