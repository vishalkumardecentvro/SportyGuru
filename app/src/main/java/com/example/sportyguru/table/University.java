package com.example.sportyguru.table;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class University {

  @SerializedName("web_pages")
  private List<String> webPages;
  @SerializedName("alpha_two_code")
  private String alphaTwoCode;
  @SerializedName("state-province")
  private String state;

  private String country, name;

  private List<String> domains ;

  public List<String> getWebPages() {
    return webPages;
  }

  public void setWebPages(List<String> webPages) {
    this.webPages = webPages;
  }

  public String getAlphaTwoCode() {
    return alphaTwoCode;
  }

  public void setAlphaTwoCode(String alphaTwoCode) {
    this.alphaTwoCode = alphaTwoCode;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<String> getDomains() {
    return domains;
  }

  public void setDomains(List<String> domains) {
    this.domains = domains;
  }
}
