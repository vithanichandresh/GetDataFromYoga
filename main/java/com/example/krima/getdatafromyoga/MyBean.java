package com.example.krima.getdatafromyoga;


import java.io.Serializable;

/**
 * Created by KRIMA on 04-08-2017.
 */

public class MyBean implements Serializable {

    String Mudra_Name_Eng, Method_Eng, Benifits_Eng, yogaImg, HindiName;
    String isFavorite;

    @Override
    public String toString() {
        return "MyBean{" +
                "Mudra_Name_Eng='" + Mudra_Name_Eng + '\'' +
                ", Method_Eng='" + Method_Eng + '\'' +
                ", Benifits_Eng='" + Benifits_Eng + '\'' +
                ", yogaImg='" + yogaImg + '\'' +
                ", HindiName='" + HindiName + '\'' +
                ", isFavorite='" + isFavorite + '\'' +
                ", Savedimage=" + Savedimage +
                '}';
    }

    public String getIsFavorite() {
        return isFavorite;
    }

    public void setIsFavorite(String isFavorite) {
        this.isFavorite = isFavorite;
    }

    int Savedimage;

    public int getSavedimage() {
        return Savedimage;
    }

    public void setSavedimage(int savedimage) {
        Savedimage = savedimage;
    }


    public String getHindiName() {
        return HindiName;
    }

    public void setHindiName(String hindiName) {
        HindiName = hindiName;
    }

    public String getYogaImg() {
        return yogaImg;
    }

    public void setYogaImg(String yogaImg) {
        this.yogaImg = yogaImg;
    }

    public String getMethod_Eng() {
        return Method_Eng;
    }

    public void setMethod_Eng(String method_Eng) {
        Method_Eng = method_Eng;
    }

    public String getBenifits_Eng() {
        return Benifits_Eng;
    }

    public void setBenifits_Eng(String benifits_Eng) {
        Benifits_Eng = benifits_Eng;
    }

    public String getMudra_Name_Eng() {
        return Mudra_Name_Eng;
    }

    public void setMudra_Name_Eng(String mudra_Name_Eng) {
        Mudra_Name_Eng = mudra_Name_Eng;
    }
}
