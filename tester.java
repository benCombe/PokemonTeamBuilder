public class tester {
    PokemonTypeDatabase DB;
    public tester(){
        Pokedex pokedex = new Pokedex();
        
        DB = new PokemonTypeDatabase();
        DB.compareTypeDefence(DB.getPokemonType("Fire"), DB.getPokemonType("Dragon"));
        /*
        for (int i = 0; i < 5; i++)
            DB.test();
        */
        //pokedex.printStats(34);
        //DB.test();
    }

    private int randomInt(int min, int max){
        return (int)((max-min)*Math.random())+min;
    }
    public static void main(String[] args) {
        new tester();
    }
}
