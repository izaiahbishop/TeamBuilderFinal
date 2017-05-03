package css.cis3334.teambuilder;

import java.io.Serializable;

/**
 * Created by ibishop on 4/26/2017.
 */

public class Pokemon implements Serializable {
    private String key, p11, p12, p21, p22, p31, p32, p41, p42, p51, p52, p61, p62;

    public Pokemon() {
        this.key = "";
        this.p11 = "";
        this.p12 = "";
        this.p21 = "";
        this.p22 = "";
        this.p31 = "";
        this.p32 = "";
        this.p41 = "";
        this.p42 = "";
        this.p51 = "";
        this.p52 = "";
        this.p61 = "";
        this.p62 = "";
    }
    public Pokemon(String key, String p11, String p12, String p21,
                   String p22, String p31, String p32, String p41,
                   String p42, String p51, String p52, String p61, String p62) {
        this.key = key;
        this.p11 = p11;
        this.p12 = p12;
        this.p21 = p21;
        this.p22 = p22;
        this.p31 = p31;
        this.p32 = p32;
        this.p41 = p41;
        this.p42 = p42;
        this.p51 = p51;
        this.p52 = p52;
        this.p61 = p61;
        this.p62 = p62;
    }

    public String getKey() {
        return key;
    }

    public String getP11() {
        return p11;
    }

    public String getP12() {
        return p12;
    }

    public String getP21() {
        return p21;
    }

    public String getP22() {
        return p22;
    }

    public String getP31() {
        return p31;
    }

    public String getP32() {
        return p32;
    }

    public String getP41() {
        return p41;
    }

    public String getP42() {
        return p42;
    }

    public String getP51() {
        return p51;
    }

    public String getP52() {
        return p52;
    }

    public String getP61() {
        return p61;
    }

    public String getP62() {
        return p62;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setP11(String p11) {
        this.p11 = p11;
    }

    public void setP12(String p12) {
        this.p12 = p12;
    }

    public void setP21(String p21) {
        this.p21 = p21;
    }

    public void setP22(String p22) {
        this.p22 = p22;
    }

    public void setP31(String p31) {
        this.p31 = p31;
    }

    public void setP32(String p32) {
        this.p32 = p32;
    }

    public void setP41(String p41) {
        this.p41 = p41;
    }

    public void setP42(String p42) {
        this.p42 = p42;
    }

    public void setP51(String p51) {
        this.p51 = p51;
    }

    public void setP52(String p52) {
        this.p52 = p52;
    }

    public void setP61(String p61) {
        this.p61 = p61;
    }

    public void setP62(String p62) {
        this.p62 = p62;
    }

    @Override
    public String toString() {
        return "Pokemon{" +
                "key='" + key + '\'' +
                ", p11='" + p11 + '\'' +
                ", p12='" + p12 + '\'' +
                ", p21='" + p21 + '\'' +
                ", p22='" + p22 + '\'' +
                ", p31='" + p31 + '\'' +
                ", p32='" + p32 + '\'' +
                ", p41='" + p41 + '\'' +
                ", p42='" + p42 + '\'' +
                ", p51='" + p51 + '\'' +
                ", p52='" + p52 + '\'' +
                ", p61='" + p61 + '\'' +
                ", p62='" + p62 + '\'' +
                '}';
    }
}
