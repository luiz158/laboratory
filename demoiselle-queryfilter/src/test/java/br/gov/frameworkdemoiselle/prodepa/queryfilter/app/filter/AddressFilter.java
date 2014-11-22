package br.gov.frameworkdemoiselle.prodepa.queryfilter.app.filter;

import java.util.Date;

import br.gov.frameworkdemoiselle.prodepa.queryfilter.annotations.And;
import br.gov.frameworkdemoiselle.prodepa.queryfilter.stereotype.QueryFilter;

@QueryFilter
public class AddressFilter {

    @And
    private Integer id;

    private String streetName;
    private Long houseNumber;
    private Boolean isOld;
    private Boolean isYellow;

    private Date buildingDate;

    public AddressFilter() {

    }

    public Integer getId() {
      return id;
    }

    public void setId(Integer id) {
      this.id = id;
    }

    public String getStreetName() {
      return streetName;
    }

    public void setStreetName(String streetName) {
      this.streetName = streetName;
    }

    public Long getHouseNumber() {
      return houseNumber;
    }

    public void setHouseNumber(Long houseNumber) {
      this.houseNumber = houseNumber;
    }

    public Boolean getIsOld() {
      return isOld;
    }

    public void setIsOld(Boolean isOld) {
      this.isOld = isOld;
    }

    public Boolean getIsYellow() {
      return isYellow;
    }

    public void setIsYellow(Boolean isYellow) {
      this.isYellow = isYellow;
    }

    public Date getBuildingDate() {
      return buildingDate;
    }

    public void setBuildingDate(Date buildingDate) {
      this.buildingDate = buildingDate;
    }

    
}