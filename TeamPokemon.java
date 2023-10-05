public class TeamPokemon implements PokemonInterface{

    String name, nature;
    PokemonType primaryType, secondaryType = null;
    PokemonType[] weakness;
    Pokemon evolution;

    
    int dexNum;
    int HP_base, att_base, spAtt_base, def_base, spDef_base, speed_base;

    public TeamPokemon(String name, String dexNum, PokemonType prime, PokemonType second, int HP_base, int att_base, int def_base, int spAtt_base, int spDef_base, int speed_base){
        this.name = name;
        this.dexNum = dexNumConvert(dexNum);
        this.primaryType = prime;
        this.secondaryType = second;

        this.HP_base = HP_base;
        this.att_base = att_base;
        this.def_base = def_base;
        this.spAtt_base = spAtt_base;
        this.spDef_base = spDef_base;
        this.speed_base = speed_base;
    }

    public TeamPokemon(){
        
    }

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

    public String dexNumString(){
        String result = "#";
        int length = String.valueOf(dexNum).length();
        for(int i = 4-length; i > 0; i--){
            result += "0";
        }
        result += String.valueOf(dexNum);
        return result;

    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int[] getBaseStats() {
        int[] temp = new int[6];
        temp[0] = HP_base;
        temp[1] = att_base;
        temp[2] = def_base;
        temp[3] = spAtt_base;
        temp[4] = spDef_base;
        temp[5] = speed_base;

        return temp;
    }

    @Override
    public int getDexNum() {
        return dexNum;
    }

    @Override
    public PokemonType getPrimaryType() {
        return primaryType;
    }

    @Override
    public PokemonType getSecondaryType() {
        return secondaryType;
    }
    
}
