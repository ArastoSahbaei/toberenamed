package com.communityratesgames.model;

import com.communityratesgames.domain.Company;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.Serializable;
import java.io.StringReader;

public class CompanyModel implements Serializable {

    private Long id;
    private String companyName;
    private String country;
    private String city;

    public CompanyModel() {
    }

    public JsonObject jsonFromString(String input) {
        JsonReader jsonReader = Json.createReader(new StringReader(input));
        JsonObject object = jsonReader.readObject();
        jsonReader.close();

        return object;
    }

    public Company toEntity(String input){
        JsonObject json = jsonFromString(input);
        Company company = new Company();

        companyName = json.getString("companyName");
        country = json.getString("country");
        city = json.getString("city");

        id = company.getId();

        company.setCompanyName(companyName);
        company.setCountry(country);
        company.setCity(city);
        company.setId(id);

        return company;
    }

    public CompanyModel toCompany(Company company){
        CompanyModel cm = new CompanyModel();

        cm.companyName = company.getCompanyName();
        cm.country = company.getCountry();
        cm.city = company.getCity();

        return cm;
    }

    public CompanyModel(Company company){
        this.id = company.getId();
        this.companyName = company.getCompanyName();
        this.country = company.getCountry();
        this.city = company.getCity();
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanyName() {
        return this.companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
