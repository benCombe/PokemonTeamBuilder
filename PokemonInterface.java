/**
 * Pokemon
 */
public interface PokemonInterface {
    /*
    String name, nature;
    PokemonType primaryType, secondaryType = null;
    PokemonType[] weakness;
    Pokemon evolution;

    
    int dexNum;
    int HP_base, att_base, spAtt_base, def_base, spDef_base, speed_base;
    */

    //public Pokemon(String name, String dexNum, PokemonType prime, PokemonType second, int HP_base, int att_base, int def_base, int spAtt_base, int spDef_base, int speed_base);

    //public Pokemon();

    private int dexNumConvert(String hash){
        int multiplyer = 1000;
        int digit, result = 0;
        for (int i = 1; i < 5; i++){
            digit = Integer.parseInt(String.valueOf(hash.charAt(i)));
            result += digit*multiplyer;
            multiplyer/=10;
        }
        return result;
    }

    public String dexNumString();
    public String getName();
    public int[] getBaseStats();
    public int getDexNum();
    public PokemonType getPrimaryType();
    public PokemonType getSecondaryType();

}