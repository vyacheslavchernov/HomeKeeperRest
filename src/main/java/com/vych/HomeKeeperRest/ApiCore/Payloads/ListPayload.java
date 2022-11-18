package com.vych.HomeKeeperRest.ApiCore.Payloads;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.vych.HomeKeeperRest.Domain.Views;

import java.util.ArrayList;
import java.util.List;

/**
 * Полезная нагрузка представляющая список объктов имплементирующих {@link ResponsePayload}.
 */
public class ListPayload implements ResponsePayload{

    @JsonProperty("items")
    @JsonView(Views.AllData.class)
    private List<Object> listOfPayload;

    public List<Object> getListOfPayload() {
        return listOfPayload;
    }

    public ListPayload setListOfPayload(List<Object> listOfPayload) {
        this.listOfPayload = listOfPayload;
        return this;
    }

    public ListPayload addItem(Object item) {
        if (this.listOfPayload == null) {
            this.listOfPayload = new ArrayList<>();
        }

        this.listOfPayload.add(item);
        return this;
    }
}
