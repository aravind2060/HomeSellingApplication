package com.example.garibihato;

public class GetandSetForBank {
    String bankname;
    int amount;

    public GetandSetForBank(String bankname, int amount) {
        this.bankname = bankname;
        this.amount = amount;
    }

    public String getBankname() {
        return bankname;
    }

    public void setBankname(String bankname) {
        this.bankname = bankname;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
