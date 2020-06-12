package com.etoak.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    public String address;
    public String fromm;
    public String identity;
    public Integer inflow;
    public String name;
    public String phone;
    public String returnDate;
    public String state;
    public double temperature;
}
