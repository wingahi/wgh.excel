/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Administrator
 */
public class TestWhiteAccount {

    private int id;
    private String productName;
    private String productNo;
    private String eRPNo;
    private String branche;
    private String productType;
    private double marketPrice;
    private double sellPrice;
    private double whiteMarketPrice;
    private double whiteSellPrice;
    private double store;
    private Double jdPrice;
    private String impo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductNo() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }

    public String geteRPNo() {
        return eRPNo;
    }

    public void setERPNo(String eRPNo) {
        this.eRPNo = eRPNo;
    }

    public String getBranche() {
        return branche;
    }

    public void setBranche(String branche) {
        this.branche = branche;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public double getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(double marketPrice) {
        this.marketPrice = marketPrice;
    }

    public double getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(double sellPrice) {
        this.sellPrice = sellPrice;
    }

    public double getWhiteMarketPrice() {
        return whiteMarketPrice;
    }

    public void setWhiteMarketPrice(double whiteMarketPrice) {
        this.whiteMarketPrice = whiteMarketPrice;
    }

    public double getWhiteSellPrice() {
        return whiteSellPrice;
    }

    public void setWhiteSellPrice(double whiteSellPrice) {
        this.whiteSellPrice = whiteSellPrice;
    }

    public double getStore() {
        return store;
    }

    public void setStore(double store) {
        this.store = store;
    }

    public Double getJdPrice() {
        return jdPrice;
    }

    public void setJdPrice(Double jdPrice) {
        this.jdPrice = jdPrice;
    }

    public String getImpo() {
        return impo;
    }

    public void setImpo(String impo) {
        this.impo = impo;
    }

    @Override
    public String toString() {
        return "TestWhiteAccount{" + "id=" + id + ", productName=" + productName + ", productNo=" + productNo + ", eRPNo=" + eRPNo + ", branche=" + branche + ", productType=" + productType + ", marketPrice=" + marketPrice + ", sellPrice=" + sellPrice + ", whiteMarketPrice=" + whiteMarketPrice + ", whiteSellPrice=" + whiteSellPrice + ", store=" + store + ", jdPrice=" + jdPrice + ", impo=" + impo + '}';
    }

}
