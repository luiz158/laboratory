package org.cruxframework.bookmark.client.datasource;

import com.google.gwt.core.client.GWT;
import org.cruxframework.crux.core.client.screen.Screen;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HasText;
import org.cruxframework.crux.core.client.formatter.HasFormatter;
import com.google.gwt.user.client.ui.Widget;
import org.cruxframework.crux.core.client.Crux;
import org.cruxframework.crux.core.client.datasource.ColumnDefinition;
import org.cruxframework.crux.core.client.datasource.ColumnDefinitions;
import org.cruxframework.crux.core.client.datasource.DataSourceRecord;

public class BookmarkDS_DataSourceProxy extends org.cruxframework.bookmark.client.datasource.BookmarkDS implements org.cruxframework.crux.core.client.screen.views.ViewAware {
  
  public BookmarkDS_DataSourceProxy(org.cruxframework.crux.core.client.screen.views.View view) {
    this.__view = view;
  }
  public String getBoundCruxViewId(){
    return (this.__view==null?null:this.__view.getId());
  }
  public org.cruxframework.crux.core.client.screen.views.View getBoundCruxView(){
    return this.__view;
  }
  public void updateData(org.cruxframework.bookmark.client.dto.BookmarkDTO[] data){
    if (data == null){
      update(new org.cruxframework.crux.core.client.datasource.DataSourceRecord[0]);
    } else {
      org.cruxframework.crux.core.client.datasource.DataSourceRecord[] ret = new org.cruxframework.crux.core.client.datasource.DataSourceRecord[data.length];
      for (int i=0; i<data.length; i++){
        ret[i] = new org.cruxframework.crux.core.client.datasource.DataSourceRecord<org.cruxframework.bookmark.client.dto.BookmarkDTO>(this,""+data[i].getId());
        ret[i].setRecordObject(data[i]);
      }
      update(ret);
    }
  }
  public void updateData(java.util.List<org.cruxframework.bookmark.client.dto.BookmarkDTO> data){
    if (data == null){
      update(new org.cruxframework.crux.core.client.datasource.DataSourceRecord[0]);
    } else {
      org.cruxframework.crux.core.client.datasource.DataSourceRecord[] ret = new org.cruxframework.crux.core.client.datasource.DataSourceRecord[data.size()];
      for (int i=0; i<data.size(); i++){
        ret[i] = new org.cruxframework.crux.core.client.datasource.DataSourceRecord<org.cruxframework.bookmark.client.dto.BookmarkDTO>(this,""+data.get(i).getId());
        ret[i].setRecordObject(data.get(i));
      }
      update(ret);
    }
  }
  public org.cruxframework.bookmark.client.dto.BookmarkDTO getBoundObject(org.cruxframework.crux.core.client.datasource.DataSourceRecord<org.cruxframework.bookmark.client.dto.BookmarkDTO> record){
    if (record == null) return null;
    return record.getRecordObject();
  }
  public void copyValueToWidget(HasValue<?> valueContainer, String columnKey, DataSourceRecord<?> dataSourceRecord) {
    
    if("description".equals(columnKey)){
      ((HasValue<java.lang.String>)valueContainer).setValue((java.lang.String) getValue(columnKey, dataSourceRecord));
      }
      
      
      else if("link".equals(columnKey)){
        ((HasValue<java.lang.String>)valueContainer).setValue((java.lang.String) getValue(columnKey, dataSourceRecord));
        }
        
        else if("id".equals(columnKey)){
          ((HasValue<java.lang.Long>)valueContainer).setValue((java.lang.Long) getValue(columnKey, dataSourceRecord));
          }
          
          bindToWidget(valueContainer, columnKey, dataSourceRecord);
        }
        private void bindToWidget(Object widget, final String columnKey, final DataSourceRecord<?> dataSourceRecord) {
          
          if("description".equals(columnKey)){
            ((com.google.gwt.event.logical.shared.HasValueChangeHandlers) widget).addValueChangeHandler(
            new com.google.gwt.event.logical.shared.ValueChangeHandler<java.lang.String>(){
              public void onValueChange(com.google.gwt.event.logical.shared.ValueChangeEvent<java.lang.String> event){
                BookmarkDS_DataSourceProxy.this.setValue(event.getValue(), columnKey, dataSourceRecord);
              }
            });
          }
          
          else if("serialVersionUID".equals(columnKey)){
            ((com.google.gwt.event.logical.shared.HasValueChangeHandlers) widget).addValueChangeHandler(
            new com.google.gwt.event.logical.shared.ValueChangeHandler<java.lang.Long>(){
              public void onValueChange(com.google.gwt.event.logical.shared.ValueChangeEvent<java.lang.Long> event){
                BookmarkDS_DataSourceProxy.this.setValue(event.getValue(), columnKey, dataSourceRecord);
              }
            });
          }
          
          else if("link".equals(columnKey)){
            ((com.google.gwt.event.logical.shared.HasValueChangeHandlers) widget).addValueChangeHandler(
            new com.google.gwt.event.logical.shared.ValueChangeHandler<java.lang.String>(){
              public void onValueChange(com.google.gwt.event.logical.shared.ValueChangeEvent<java.lang.String> event){
                BookmarkDS_DataSourceProxy.this.setValue(event.getValue(), columnKey, dataSourceRecord);
              }
            });
          }
          
          else if("id".equals(columnKey)){
            ((com.google.gwt.event.logical.shared.HasValueChangeHandlers) widget).addValueChangeHandler(
            new com.google.gwt.event.logical.shared.ValueChangeHandler<java.lang.Long>(){
              public void onValueChange(com.google.gwt.event.logical.shared.ValueChangeEvent<java.lang.Long> event){
                BookmarkDS_DataSourceProxy.this.setValue(event.getValue(), columnKey, dataSourceRecord);
              }
            });
          }
        }
        public void setValue(Object value, String columnKey, DataSourceRecord<?> dataSourceRecord) {
          
          if("description".equals(columnKey)){
            java.lang.String field = ((org.cruxframework.bookmark.client.dto.BookmarkDTO) dataSourceRecord.getRecordObject()).getDescription();
            boolean changed = (value == null && field != null) || (value != null && field == null) || !field.equals(value);
            if(changed){
              ((org.cruxframework.bookmark.client.dto.BookmarkDTO) dataSourceRecord.getRecordObject()).setDescription((java.lang.String) value);
              dataSourceRecord.setDirty();
              return;
              }}
              if("link".equals(columnKey)){
                java.lang.String field = ((org.cruxframework.bookmark.client.dto.BookmarkDTO) dataSourceRecord.getRecordObject()).getLink();
                boolean changed = (value == null && field != null) || (value != null && field == null) || !field.equals(value);
                if(changed){
                  ((org.cruxframework.bookmark.client.dto.BookmarkDTO) dataSourceRecord.getRecordObject()).setLink((java.lang.String) value);
                  dataSourceRecord.setDirty();
                  return;
                  }}
                  if("id".equals(columnKey)){
                    java.lang.Long field = ((org.cruxframework.bookmark.client.dto.BookmarkDTO) dataSourceRecord.getRecordObject()).getId();
                    boolean changed = (value == null && field != null) || (value != null && field == null) || !field.equals(value);
                    if(changed){
                      ((org.cruxframework.bookmark.client.dto.BookmarkDTO) dataSourceRecord.getRecordObject()).setId((java.lang.Long) value);
                      dataSourceRecord.setDirty();
                      return;
                      }}}
                  public interface DtoCopier extends org.cruxframework.crux.core.client.bean.BeanCopier<org.cruxframework.bookmark.client.dto.BookmarkDTO,org.cruxframework.bookmark.client.dto.BookmarkDTO>{}
                  public org.cruxframework.bookmark.client.dto.BookmarkDTO cloneDTO(org.cruxframework.crux.core.client.datasource.DataSourceRecord<?> dsr){
                    DtoCopier copier = GWT.create(DtoCopier.class);
                    org.cruxframework.bookmark.client.dto.BookmarkDTO newDto = new org.cruxframework.bookmark.client.dto.BookmarkDTO();
                    copier.copyFrom((org.cruxframework.bookmark.client.dto.BookmarkDTO)dsr.getRecordObject(),newDto);
                  return newDto;}
                  private org.cruxframework.crux.core.client.screen.views.View __view;
                }
