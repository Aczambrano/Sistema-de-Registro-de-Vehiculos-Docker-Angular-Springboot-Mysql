package com.gizlo.crud.security.response.userapi;

public class Crypto {
    private String coin;
    private String wallet;
    private String network;

    public Crypto(String coin, String wallet, String network) {
        this.coin = coin;
        this.wallet = wallet;
        this.network = network;
    }

    public String getCoin() {
        return this.coin;
    }

    public String getWallet() {
        return this.wallet;
    }

    public String getNetwork() {
        return this.network;
    }

    public void setCoin(String coin) {
        this.coin = coin;
    }

    public void setWallet(String wallet) {
        this.wallet = wallet;
    }

    public void setNetwork(String network) {
        this.network = network;
    }
}
