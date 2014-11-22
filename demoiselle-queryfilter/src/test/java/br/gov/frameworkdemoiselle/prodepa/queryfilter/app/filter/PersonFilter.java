package br.gov.frameworkdemoiselle.prodepa.queryfilter.app.filter;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

import br.gov.frameworkdemoiselle.prodepa.queryfilter.annotations.And;
import br.gov.frameworkdemoiselle.prodepa.queryfilter.annotations.FirstResult;
import br.gov.frameworkdemoiselle.prodepa.queryfilter.annotations.MaxResult;
import br.gov.frameworkdemoiselle.prodepa.queryfilter.annotations.Or;
import br.gov.frameworkdemoiselle.prodepa.queryfilter.annotations.metadata.AndConditionType;
import br.gov.frameworkdemoiselle.prodepa.queryfilter.annotations.metadata.OrConditionType;
import br.gov.frameworkdemoiselle.prodepa.queryfilter.app.model.Address;
import br.gov.frameworkdemoiselle.prodepa.queryfilter.app.model.Car;
import br.gov.frameworkdemoiselle.prodepa.queryfilter.app.model.Cat;
import br.gov.frameworkdemoiselle.prodepa.queryfilter.app.model.Certification;
import br.gov.frameworkdemoiselle.prodepa.queryfilter.app.model.Dog;
import br.gov.frameworkdemoiselle.prodepa.queryfilter.stereotype.QueryFilter;
import br.gov.frameworkdemoiselle.prodepa.queryfilter.types.Range;

/**
 * @author thiago
 *
 */
@QueryFilter(orderByAsc = "name", orderByDesc = "birthDayDate")
public class PersonFilter {

  @And(attributeNames = {"id"} )
  private Long id;
  
  @And(attributeNames = {"id"} )
  private Range<Long> idBetween;
  
  @And(attributeNames = {"name"} ) 
  private String name;

  @And(attributeNames = {"name"}, ignoreCase = true ) 
  private String nomeIgnoreCase;
  
  @And(attributeNames = {"name"}, conditionType = AndConditionType.NOT_EQUALS) 
  private String nameNotEqual;
  
  @And(attributeNames = {"name"}, conditionType = AndConditionType.NOT_EQUALS) 
  private String[] namesNotEqual;
  
  @And(attributeNames = {"name"}, conditionType = AndConditionType.GT) 
  private String nameGT;
  
  @And(attributeNames = {"name"}, conditionType = AndConditionType.LIKE_STARTS_WITH) 
  private String nameStartsWith;
  
  @And(attributeNames = {"name"}, conditionType = AndConditionType.LIKE_ENDS_WITH) 
  private String nameEndWith;
  
  
  @And(attributeNames = {"name", "nickName"}, conditionType = AndConditionType.LIKE ) 
  private String nomesAnd;
  
  @Or(attributeNames = {"name", "nickName"}, conditionType = OrConditionType.EQUAL) 
  private String nomesOr;
  
  
  @And(attributeNames = "name", conditionType = AndConditionType.EQUAL) 
  private String nameEqual;
  
  @And(attributeNames = "name", conditionType = AndConditionType.EQUAL) 
  private String[] namesEqual;
  
  @And(attributeNames = "name", conditionType = AndConditionType.IN) 
  private List<String> namesIn;

  @And(attributeNames = "name", conditionType = AndConditionType.NOT_IN) 
  private List<String> namesNotIn;
  
  @And(attributeNames = "name", conditionType = AndConditionType.BETWEEN, ignoreCase = true) 
  private Range<String> nameBetween;

  
  @And(attributeNames = "nickName", conditionType = AndConditionType.EQUAL) 
  private String nickName;

  private Integer clothesInCloset;
  
  @And
  private Integer shoesInCloset;
  
  @And(attributeNames = "shoesInCloset", conditionType = AndConditionType.BETWEEN)
  private Range<Integer> shoesInClosetBetween;

  private Long totalBooksOwned;
  
  @And(attributeNames = "totalBooksOwned", conditionType = AndConditionType.BETWEEN)
  private Range<Long> totalBooksOwnedBetween;
  
  @And(attributeNames = "totalBooksOwned", conditionType = AndConditionType.BETWEEN)
  private Long[] totalBooksOwnedBetweenArray;
  
  @And(attributeNames = "totalBooksOwned", conditionType = AndConditionType.BETWEEN)
  private List<Long> totalBooksOwnedBetweenList;
  
  @And
  private Long socialSecurityNumber;
  
  @Or(attributeNames = "socialSecurityNumber")
  private Long[] socialSecurityNumberArray;
  
  @Or(attributeNames = "socialSecurityNumber")
  private List<Long> socialSecurityNumberList;
  
  @Or(attributeNames = "socialSecurityNumber")
  private Set<Long> socialSecurityNumberSet;

  @And
  private Boolean brazilian;
  
  private Boolean japanese;

  @And
  private Float weight;
  
  @Or
  private Double height;
  
  @And(attributeNames = "height", conditionType = AndConditionType.GT)
  private Double heightGT;
  
  @And(attributeNames = "height", conditionType = AndConditionType.GE)
  private Double heightGE;
  
  @And(attributeNames = "height", conditionType = AndConditionType.LT)
  private Double heightLT;
  
  @And(attributeNames = "height", conditionType = AndConditionType.LE)
  private Double heightLE;
  
  
  private Date firstJobDate;
  
  @Or(attributeNames = "firstJobDate")
  private List<Date> firstJobDateList;

  private Date firstSoccerMatchDate;

  private Calendar birthDayDate;
  
  @And(attributeNames = "birthDayDate", conditionType = AndConditionType.BETWEEN)
  private Range<Calendar> birthDayDateBetween;

  private Calendar firstKissDate;

  private Address address;

  private Car car;

  private List<Dog> dogs;

  private Set<Certification> certifications;

  private Collection<Cat> cats;
  
  /*
   * IS_NULL e IS_NOT_NULL
   * 
   */
  
  @And(attributeNames = "nickName", conditionType = AndConditionType.IS_NULL)
  private Boolean stringIsNull;
  
  @And(attributeNames = "nickName", conditionType = AndConditionType.IS_NOT_NULL)
  private Boolean stringIsNotNull;
  
  @And(attributeNames = "birthDayDate", conditionType = AndConditionType.IS_NULL)
  private Boolean dateIsNull;
  
  @And(attributeNames = "birthDayDate", conditionType = AndConditionType.IS_NOT_NULL)
  private Boolean dateIsNotNull;
  
  @And(attributeNames = "shoesInCloset", conditionType = AndConditionType.IS_NULL)
  private Boolean integerIsNull;
  
  @And(attributeNames = "shoesInCloset", conditionType = AndConditionType.IS_NOT_NULL)
  private Boolean integerIsNotNull;
  
  @And(attributeNames = "car", conditionType = AndConditionType.IS_NULL)
  private Boolean carIsNull;
  
  @And(attributeNames = "car", conditionType = AndConditionType.IS_NOT_NULL)
  private Boolean carIsNotNull;
  
  
  @FirstResult
  private Long firstResult;
  
  @MaxResult
  private Integer maxResults;
  
  
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getNomeIgnoreCase() {
    return nomeIgnoreCase;
  }

  public void setNomeIgnoreCase(String nomeIgnoreCase) {
    this.nomeIgnoreCase = nomeIgnoreCase;
  }

  public String getNomesAnd() {
    return nomesAnd;
  }

  public void setNomesAnd(String nomesAnd) {
    this.nomesAnd = nomesAnd;
  }

  public String getNomesOr() {
    return nomesOr;
  }

  public void setNomesOr(String nomesOr) {
    this.nomesOr = nomesOr;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getNickName() {
    return nickName;
  }

  public void setNickName(String nickName) {
    this.nickName = nickName;
  }


  public Integer getShoesInCloset() {
    return shoesInCloset;
  }

  public void setShoesInCloset(Integer shoesInCloset) {
    this.shoesInCloset = shoesInCloset;
  }

  public Long getTotalBooksOwned() {
    return totalBooksOwned;
  }

  public void setTotalBooksOwned(Long totalBooksOwned) {
    this.totalBooksOwned = totalBooksOwned;
  }

  public Long getSocialSecurityNumber() {
    return socialSecurityNumber;
  }

  public void setSocialSecurityNumber(Long socialSecurityNumber) {
    this.socialSecurityNumber = socialSecurityNumber;
  }

  public Boolean getBrazilian() {
    return brazilian;
  }

  public void setBrazilian(Boolean brazilian) {
    this.brazilian = brazilian;
  }

  public Boolean getJapanese() {
    return japanese;
  }

  public void setJapanese(Boolean japanese) {
    this.japanese = japanese;
  }

  public Float getWeight() {
    return weight;
  }

  public void setWeight(Float weight) {
    this.weight = weight;
  }

  public Double getHeight() {
    return height;
  }

  public void setHeight(Double height) {
    this.height = height;
  }

  public Date getFirstJobDate() {
    return firstJobDate;
  }

  public void setFirstJobDate(Date firstJobDate) {
    this.firstJobDate = firstJobDate;
  }

  public Date getFirstSoccerMatchDate() {
    return firstSoccerMatchDate;
  }

  public void setFirstSoccerMatchDate(Date firstSoccerMatchDate) {
    this.firstSoccerMatchDate = firstSoccerMatchDate;
  }

  public Calendar getBirthDayDate() {
    return birthDayDate;
  }

  public void setBirthDayDate(Calendar birthDayDate) {
    this.birthDayDate = birthDayDate;
  }

  public Calendar getFirstKissDate() {
    return firstKissDate;
  }

  public void setFirstKissDate(Calendar firstKissDate) {
    this.firstKissDate = firstKissDate;
  }

  public Address getAddress() {
    return address;
  }

  public void setAddress(Address address) {
    this.address = address;
  }

  public Car getCar() {
    return car;
  }

  public void setCar(Car car) {
    this.car = car;
  }

  public List<Dog> getDogs() {
    return dogs;
  }

  public void setDogs(List<Dog> dogs) {
    this.dogs = dogs;
  }

  public Set<Certification> getCertifications() {
    return certifications;
  }

  public void setCertifications(Set<Certification> certifications) {
    this.certifications = certifications;
  }

  public Collection<Cat> getCats() {
    return cats;
  }

  public void setCats(Collection<Cat> cats) {
    this.cats = cats;
  }

  public Long[] getSocialSecurityNumberArray() {
    return socialSecurityNumberArray;
  }

  public void setSocialSecurityNumberArray(Long[] socialSecurityNumberArray) {
    this.socialSecurityNumberArray = socialSecurityNumberArray;
  }

  public List<Long> getSocialSecurityNumberList() {
    return socialSecurityNumberList;
  }

  public void setSocialSecurityNumberList(List<Long> socialSecurityNumberList) {
    this.socialSecurityNumberList = socialSecurityNumberList;
  }

  public Set<Long> getSocialSecurityNumberSet() {
    return socialSecurityNumberSet;
  }

  public void setSocialSecurityNumberSet(Set<Long> socialSecurityNumberSet) {
    this.socialSecurityNumberSet = socialSecurityNumberSet;
  }

  public Integer getClothesInCloset() {
    return clothesInCloset;
  }

  public void setClothesInCloset(Integer clothesInCloset) {
    this.clothesInCloset = clothesInCloset;
  }

  public String getNameNotEqual() {
    return nameNotEqual;
  }

  public void setNameNotEqual(String nameNotEqual) {
    this.nameNotEqual = nameNotEqual;
  }

  public String getNameEqual() {
    return nameEqual;
  }

  public void setNameEqual(String nameEqual) {
    this.nameEqual = nameEqual;
  }

  public String[] getNamesEqual() {
    return namesEqual;
  }

  public void setNamesEqual(String[] namesEqual) {
    this.namesEqual = namesEqual;
  }

  public String[] getNamesNotEqual() {
    return namesNotEqual;
  }

  public void setNamesNotEqual(String[] namesNotEqual) {
    this.namesNotEqual = namesNotEqual;
  }

  public List<String> getNamesIn() {
    return namesIn;
  }

  public void setNamesIn(List<String> namesIn) {
    this.namesIn = namesIn;
  }

  public List<String> getNamesNotIn() {
    return namesNotIn;
  }

  public void setNamesNotIn(List<String> namesNotIn) {
    this.namesNotIn = namesNotIn;
  }

  public Range<Long> getIdBetween() {
    return idBetween;
  }

  public void setIdBetween(Range<Long> idBetween) {
    this.idBetween = idBetween;
  }

  public Range<Integer> getShoesInClosetBetween() {
    return shoesInClosetBetween;
  }

  public void setShoesInClosetBetween(Range<Integer> shoesInClosetBetween) {
    this.shoesInClosetBetween = shoesInClosetBetween;
  }

  public Range<Long> getTotalBooksOwnedBetween() {
    return totalBooksOwnedBetween;
  }

  public void setTotalBooksOwnedBetween(Range<Long> totalBooksOwnedBetween) {
    this.totalBooksOwnedBetween = totalBooksOwnedBetween;
  }

  public Long[] getTotalBooksOwnedBetweenArray() {
    return totalBooksOwnedBetweenArray;
  }

  public void setTotalBooksOwnedBetweenArray(Long[] totalBooksOwnedBetweenArray) {
    this.totalBooksOwnedBetweenArray = totalBooksOwnedBetweenArray;
  }

  public List<Long> getTotalBooksOwnedBetweenList() {
    return totalBooksOwnedBetweenList;
  }

  public void setTotalBooksOwnedBetweenList(List<Long> totalBooksOwnedBetweenList) {
    this.totalBooksOwnedBetweenList = totalBooksOwnedBetweenList;
  }

  public Range<Calendar> getBirthDayDateBetween() {
    return birthDayDateBetween;
  }

  public void setBirthDayDateBetween(Range<Calendar> birthDayDateBetween) {
    this.birthDayDateBetween = birthDayDateBetween;
  }

  public Range<String> getNameBetween() {
    return nameBetween;
  }

  public void setNameBetween(Range<String> nameBetween) {
    this.nameBetween = nameBetween;
  }

  public List<Date> getFirstJobDateList() {
    return firstJobDateList;
  }

  public void setFirstJobDateList(List<Date> firstJobDateList) {
    this.firstJobDateList = firstJobDateList;
  }

  public Double getHeightGT() {
    return heightGT;
  }

  public void setHeightGT(Double heightGT) {
    this.heightGT = heightGT;
  }

  public Double getHeightGE() {
    return heightGE;
  }

  public void setHeightGE(Double heightGE) {
    this.heightGE = heightGE;
  }

  public Double getHeightLT() {
    return heightLT;
  }

  public void setHeightLT(Double heightLT) {
    this.heightLT = heightLT;
  }

  public Double getHeightLE() {
    return heightLE;
  }

  public void setHeightLE(Double heightLE) {
    this.heightLE = heightLE;
  }

  public String getNameGT() {
    return nameGT;
  }

  public void setNameGT(String nameGT) {
    this.nameGT = nameGT;
  }

  public Boolean getStringIsNull() {
    return stringIsNull;
  }

  public void setStringIsNull(Boolean stringIsNull) {
    this.stringIsNull = stringIsNull;
  }

  public Boolean getStringIsNotNull() {
    return stringIsNotNull;
  }

  public void setStringIsNotNull(Boolean stringIsNotNull) {
    this.stringIsNotNull = stringIsNotNull;
  }

  public Boolean getDateIsNull() {
    return dateIsNull;
  }

  public void setDateIsNull(Boolean dateIsNull) {
    this.dateIsNull = dateIsNull;
  }

  public Boolean getDateIsNotNull() {
    return dateIsNotNull;
  }

  public void setDateIsNotNull(Boolean dateIsNotNull) {
    this.dateIsNotNull = dateIsNotNull;
  }

  public Boolean getIntegerIsNull() {
    return integerIsNull;
  }

  public void setIntegerIsNull(Boolean integerIsNull) {
    this.integerIsNull = integerIsNull;
  }

  public Boolean getIntegerIsNotNull() {
    return integerIsNotNull;
  }

  public void setIntegerIsNotNull(Boolean integerIsNotNull) {
    this.integerIsNotNull = integerIsNotNull;
  }

  public Boolean getCarIsNull() {
    return carIsNull;
  }

  public void setCarIsNull(Boolean carIsNull) {
    this.carIsNull = carIsNull;
  }

  public Boolean getCarIsNotNull() {
    return carIsNotNull;
  }

  public void setCarIsNotNull(Boolean carIsNotNull) {
    this.carIsNotNull = carIsNotNull;
  }

  public String getNameStartsWith() {
    return nameStartsWith;
  }

  public void setNameStartsWith(String nameStartsWith) {
    this.nameStartsWith = nameStartsWith;
  }

  public String getNameEndWith() {
    return nameEndWith;
  }

  public void setNameEndWith(String nameEndWith) {
    this.nameEndWith = nameEndWith;
  }

  public Long getFirstResult() {
    return firstResult;
  }

  public void setFirstResult(Long firstResult) {
    this.firstResult = firstResult;
  }

  public Integer getMaxResults() {
    return maxResults;
  }

  public void setMaxResults(Integer maxResults) {
    this.maxResults = maxResults;
  }


}