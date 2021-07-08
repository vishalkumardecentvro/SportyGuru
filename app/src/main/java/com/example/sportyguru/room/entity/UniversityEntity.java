package com.example.sportyguru.room.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "university")
public class UniversityEntity {

  private String name, country, state, webPage, domain, alphaTwoCode;
  @PrimaryKey(autoGenerate = true)
  private int id;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public String getWebPage() {
    return webPage;
  }

  public void setWebPage(String webPage) {
    this.webPage = webPage;
  }

  public String getDomain() {
    return domain;
  }

  public void setDomain(String domain) {
    this.domain = domain;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getAlphaTwoCode() {
    return alphaTwoCode;
  }

  public void setAlphaTwoCode(String alphaTwoCode) {
    this.alphaTwoCode = alphaTwoCode;
  }
}
