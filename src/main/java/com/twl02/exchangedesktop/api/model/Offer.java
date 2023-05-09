package com.twl02.exchangedesktop.api.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

public class Offer {
    @SerializedName("id")
    public Integer id;
    @SerializedName("amount")
    public Number amount;
    @SerializedName("transaction_id")
    public boolean transcationId;
    @SerializedName("teller_id")
    public Integer tellerId;
    @SerializedName("added_date")
    public Date addedDate;

}
