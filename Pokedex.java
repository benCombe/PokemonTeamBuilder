import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Pokedex {
    
    FileReader fRead;
    BufferedReader bRead;
    PokemonTypeDatabase ptDB;

    Node<Pokemon> DEX;
    int numInDEX = 0;

    String[] paths = {".txt/genI.txt", ".txt/genII.txt", ".txt/genIII.txt", ".txt/genIV.txt", ".txt/genV.txt", 
                     ".txt/genVI.txt", ".txt/genVII.txt", ".txt/genVIII.txt", ".txt/genIX.txt"};

    public Pokedex(){
        DEX = new Node<Pokemon>(null, null);
        ptDB = new PokemonTypeDatabase();

        try{
            fRead = new FileReader(paths[0]);
            bRead = new BufferedReader(fRead);
        }
        catch(FileNotFoundException e){
            UIO("File Not Found");
        }

        readData();
    }
    
    void readData(){
        Pokemon newPokemon;
        String line;
        String[] prop = new String[10];
        
        while(true){
            try{
                line = bRead.readLine();
                
                if (line == null) break;
                prop = line.split(":");
                //UIO(prop[0]);
                if (prop.length > 1){
                newPokemon = new Pokemon(prop[0], prop[1], ptDB.getPokemonType(prop[2]), ptDB.getPokemonType(prop[3]), 
                toInt(prop[4]), toInt(prop[5]), toInt(prop[6]), toInt(prop[7]), toInt(prop[8]), toInt(prop[9]));
                addPokemon(newPokemon);
                }
                else UIO("EMPTY STRING");
            }
            catch(IOException e){

            }
        
        }
        UIO("Pokedex Built");
    }

    private void addPokemon(Pokemon poke){
        
        if (numInDEX <= 0) {
            DEX.next = new Node<Pokemon>(poke, DEX.next);
        }
        else{
            Node<Pokemon> p = DEX.next;
            Node<Pokemon> q = null;
            while (p != null){
                q = p;
                p = p.next;
            }
            q.next = new Node<Pokemon>(poke, q.next);
        }
        numInDEX++;
        
    }

    public int size(){return numInDEX;}

    public void printStats(String name){
        Pokemon cur = getPokemon(name);
        UIO(cur.name + "\t" + cur.dexNumString() + "\n" + cur.primaryType.name + "\t" + cur.secondaryType.name);
    }

    public void printStats(int num){
        Pokemon cur = getPokemon(num);
        String pName = cur.primaryType.name, sName;
        if (cur.secondaryType == null) sName = "NA";
        else sName = cur.secondaryType.name;
        UIO("\n==== "+allCaps(cur.name) + " ====\n     (" + cur.dexNumString() + ")\nType(s): " + pName + " & " + sName
        + "\n == BASE STATS ==\n"
        + "HP: " + cur.HP_base
        + "\nAttack: " + cur.att_base
        + "\nDefence: " + cur.def_base
        + "\nSp. Attack: " + cur.spAtt_base
        + "\nSp. Defence: " + cur.spDef_base
        + "\nSpeed: " + cur.speed_base);
        ptDB.compareTypeDefence(cur.primaryType, cur.secondaryType);
        System.out.println("==================");
    }

    private Pokemon getPokemon(int num){
        if (numInDEX <= 0) return null;
        else{
            Node<Pokemon> p = DEX.next;
            while (p != null){
                if (p.getItem().dexNum == num) return p.getItem();
                p = p.next;
            }
            return null;
        }
    }

    private Pokemon getPokemon(String name){
        if (numInDEX <= 0) return null;
        else{
            Node<Pokemon> p = DEX.next;
            while (p != null){
                if (p.getItem().name.equals(name)) return p.getItem();
                p = p.next;
            }
            return null;
        }
    }

    private void UIO(String s){
        System.out.println(s);
    }

    private int toInt(String s){
        return Integer.parseInt(s);
    }

    private String allCaps(String s){
        String result = "";
        int t;
        for (int i = 0; i < s.length(); i++){
            t = (int)s.charAt(i);
            if (t > 96) result += (char)(t-32);
            else result += s.charAt(i);
        }
        return result;
    }


}
