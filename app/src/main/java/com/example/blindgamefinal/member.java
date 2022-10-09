package com.example.blindgamefinal;

public class member {
    private String number;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
        this.number = this.number.replaceAll("\\s", "");
    }
}
