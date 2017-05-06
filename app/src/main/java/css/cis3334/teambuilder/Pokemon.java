package css.cis3334.teambuilder;

import java.io.Serializable;

/**
 * This class defines a Pokemon object and contains the appropriate constructors, getters, setters, and toString.
 * This class is serializable, meaning it can be converted into a byte stream, and then back into a
 * Pokemon object.
 *
 * @author Izaiah Bishop
 */
public class Pokemon implements Serializable {
    private String key, p11, p12, p21, p22, p31, p32, p41, p42, p51, p52, p61, p62;

    /**
     * The default constructor for a Pokemon object.
     */
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

    /**
     * Constructor containing all values from spinners passed in as strings.
     *
     * @param key Identifies the Pokemon Object.
     * @param p11 The value in the first spinner.
     * @param p12 The value in the second spinner.
     * @param p21 The value in the third spinner.
     * @param p22 The value in the fourth spinner.
     * @param p31 The value in the fifth spinner.
     * @param p32 The value in the sixth spinner.
     * @param p41 The value in the seventh spinner.
     * @param p42 The value in the eighth spinner.
     * @param p51 The value in the ninth spinner.
     * @param p52 The value in the tenth spinner.
     * @param p61 The value in the eleventh spinner.
     * @param p62 The value in the twelfth spinner.
     */
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

    /**
     * Get the key of the Pokemon object.
     *
     * @return String the key identifying the Pokemon.
     */
    public String getKey() {
        return key;
    }

    /**
     * Get the first type of the first pokemon.
     *
     * @return String first type of the first pokemon.
     */
    public String getP11() {
        return p11;
    }

    /**
     * Get the second type of the first pokemon.
     *
     * @return String second type of the first pokemon.
     */
    public String getP12() {
        return p12;
    }

    /**
     * Get the first type of the second pokemon.
     *
     * @return String first type of the second pokemon.
     */
    public String getP21() {
        return p21;
    }

    /**
     * Get the second type of the second pokemon.
     *
     * @return String second type of the second pokemon.
     */
    public String getP22() {
        return p22;
    }

    /**
     * Get the first type of the third pokemon.
     *
     * @return String first type of the third pokemon.
     */
    public String getP31() {
        return p31;
    }

    /**
     * Get the second type of the third pokemon.
     *
     * @return String second type of the third pokemon.
     */
    public String getP32() {
        return p32;
    }

    /**
     * Get the first type of the fourth pokemon.
     *
     * @return String first type of the fourth pokemon.
     */
    public String getP41() {
        return p41;
    }

    /**
     * Get the second type of the fourth pokemon.
     *
     * @return String second type of the fourth pokemon.
     */
    public String getP42() {
        return p42;
    }

    /**
     * Get the first type of the fifth pokemon.
     *
     * @return String first type of the fifth pokemon.
     */
    public String getP51() {
        return p51;
    }

    /**
     * Get the second type of the fifth pokemon.
     *
     * @return String second type of the fifth pokemon.
     */
    public String getP52() {
        return p52;
    }

    /**
     * Get the first type of the sixth pokemon.
     *
     * @return String first type of the sixth pokemon.
     */
    public String getP61() {
        return p61;
    }

    /**
     * Get the second type of the sixth pokemon.
     *
     * @return String second type of the sixth pokemon.
     */
    public String getP62() {
        return p62;
    }

    /**
     * Set the key of the Pokemon object.
     *
     * @param key the key identifying the Pokemon.
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * Set the first type of the first pokemon.
     *
     * @param p11 first type of the first pokemon.
     */
    public void setP11(String p11) {
        this.p11 = p11;
    }

    /**
     * Set the second type of the first pokemon.
     *
     * @param p12 first type of the second pokemon.
     */
    public void setP12(String p12) {
        this.p12 = p12;
    }

    /**
     * Set the first type of the second pokemon.
     *
     * @param p21 first type of the second pokemon.
     */
    public void setP21(String p21) {
        this.p21 = p21;
    }

    /**
     * Set the second type of the second pokemon.
     *
     * @param p22 second type of the second pokemon.
     */
    public void setP22(String p22) {
        this.p22 = p22;
    }

    /**
     * Set the first type of the third pokemon.
     *
     * @param p31 first type of the third pokemon.
     */
    public void setP31(String p31) {
        this.p31 = p31;
    }

    /**
     * Set the second type of the third pokemon.
     *
     * @param p32 second type of the third pokemon.
     */
    public void setP32(String p32) {
        this.p32 = p32;
    }

    /**
     * Set the first type of the fourth pokemon.
     *
     * @param p41 first type of the fourth pokemon.
     */
    public void setP41(String p41) {
        this.p41 = p41;
    }

    /**
     * Set the second type of the fourth pokemon.
     *
     * @param p42 second type of the fourth pokemon.
     */
    public void setP42(String p42) {
        this.p42 = p42;
    }

    /**
     * Set the first type of the fifth pokemon.
     *
     * @param p51 first type of the fifth pokemon.
     */
    public void setP51(String p51) {
        this.p51 = p51;
    }

    /**
     * Set the second type of the fifth pokemon.
     *
     * @param p52 first second of the fifth pokemon.
     */
    public void setP52(String p52) {
        this.p52 = p52;
    }

    /**
     * Set the first type of the sixth pokemon.
     *
     * @param p61 first type of the sixth pokemon.
     */
    public void setP61(String p61) {
        this.p61 = p61;
    }

    /**
     * Set the second type of the sixth pokemon.
     *
     * @param p62 second type of the sixth pokemon.
     */
    public void setP62(String p62) {
        this.p62 = p62;
    }

    /**
     * Converts a Pokemon object to a string.
     *
     * @return The string value of a Pokemon object.
     */
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
