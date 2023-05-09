package com.twl02.exchangedesktop.api.model;

import com.google.gson.annotations.SerializedName;
import com.twl02.exchangedesktop.offers.Offers;

import java.util.Date;
import java.util.List;

public class TransactionRequest {
    @SerializedName("id")
    public Integer id;
    @SerializedName("amount")
    public Number amount;
    @SerializedName("usd_to_lbp")
    public Boolean usdToLbp;
    @SerializedName("user_id")
    public Integer userId;
    @SerializedName("num_offers")
    public Integer numOffers;
    @SerializedName("added_date")
    public String addedDate;
    @SerializedName("offers")
    public List<Offer> offers;
}
