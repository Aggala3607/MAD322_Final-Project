package com.covidfight.model;

import com.google.gson.annotations.SerializedName;

public class WorldData {

    @SerializedName("infected")
    private String infected;

    @SerializedName("tested")
    private String tested;

    @SerializedName("recovered")
    private String recovered;

    @SerializedName("deceased")
    private String deceased;

    @SerializedName("country")
    private String country;

    @SerializedName("sourceUrl")
    private String sourceUrl;

    public String getInfected() {
        return infected;
    }

    public void setInfected(String infected) {
        this.infected = infected;
    }

    public String getTested() {
        return tested;
    }

    public void setTested(String tested) {
        this.tested = tested;
    }

    public String getRecovered() {
        return recovered;
    }

    public void setRecovered(String recovered) {
        this.recovered = recovered;
    }

    public String getDeceased() {
        return deceased;
    }

    public void setDeceased(String deceased) {
        this.deceased = deceased;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }


//     "infected": 181376,
//             "tested": "NA",
//             "recovered": 121353,
//             "deceased": 4550,
//             "country": "Algeria",
//             "moreData": "https://api.apify.com/v2/key-value-stores/pp4Wo2slUJ78ZnaAi/records/LATEST?disableRedirect=true",
//             "historyData": "https://api.apify.com/v2/datasets/hi0DJXpcyzDwtg2Fm/items?format=json&clean=1",
//             "sourceUrl": "https://www.worldometers.info/coronavirus/",
//             "lastUpdatedApify": "2021-08-09T15:00:00.000Z"
}
