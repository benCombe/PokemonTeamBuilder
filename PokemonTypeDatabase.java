import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;


public class PokemonTypeDatabase {
    FileReader fRead;
    BufferedReader bRead;
    TextColour tc;

    PokemonType[] types = new PokemonType[18];
    char[][] seMatrix = new char[18][18];

    public PokemonTypeDatabase(){
        tc = new TextColour();
        try{
            fRead = new FileReader(".txt/pokeweakness.txt");
            bRead = new BufferedReader(fRead);
        }
        catch(FileNotFoundException e){
            System.out.println("File NOT Found");
        }

        build();
    }

    private void build(){
        int i = 0;
        char e;
        String[] prop = new String[25];
        String line;
        while (true){

            try{
                line = bRead.readLine();
                if (line == null) break;

                prop = line.split("\t");
                //System.out.println(prop[0] + "\n" + line);
                types[i] = new PokemonType(prop[0]); 
                for (int j = 0; j < 18; j++){
                    seMatrix[i][j] = prop[j+1].charAt(0);
                }
                i++;
            }
            catch(IOException ioe){
                System.out.println("ERROR: IO Exception");
            }
            catch(NullPointerException npe){
                System.out.println("Error: ");
                for (String string : prop) {
                    System.out.print(string + ":");
                }
            }

        }
        for(int j = 0; j < 18; j++){
            //System.out.println(types[j].name);
            for (int k = 0; k < 18; k++){
                e = seMatrix[j][k];
                if (e == '2') types[j].addSuperEffect(types[k]);
                else if (e == 'h') types[j].addHalfEffect(types[k]);
                else if (e == '0') types[j].setNoEffect(types[k]);
                else types[j].addEffect(types[k]);
            }
        }
        System.out.println("\n--- Build Complete --- ");

    }

    public PokemonType getPokemonType(String name){
        for (PokemonType t : types){
            if (t.name.equals(name)) return t;
        }
        return null;
    }

    public PokemonType[] getWeakness(PokemonType t){
        PokemonType[] current, result = new PokemonType[18];
        int index = 0;
        
        for (int i = 0; i < types.length; i++){
            current = types[i].getAllSuperEffect();
            for (int j = 0; j < current.length; j++){
                if (current[j].Equals(t)) result[index++] = types[i];
            }
        }
        return Arrays.copyOfRange(result, 0, index);
    }

    public void compareTypeDefence(PokemonType a, PokemonType b){
        boolean singleType = false;
        if (b == null) singleType = true;
        //String[] stats = new String[18];
        double[] vals = new double[18];
        double count = 1;
        int index = 0;
        
        int aIndex = -1, bIndex = -1;

        //Get indexes of Super Effect Matrix for both types
        if (!singleType){
            for(int i = 0; i < types.length; i++){
                if (types[i].Equals(a)) aIndex = i;
                if (types[i].Equals(b)) bIndex = i;
                if (aIndex >= 0 && bIndex >= 0) break;
            }
        }
        else{
            for(int i = 0; i < types.length; i++){
                if (types[i].Equals(a)) {
                    aIndex = i;
                    break;
                }
            }
        }

        //Go through each type in seMtrix, vertically
        if (!singleType){
            for (int i = 0; i < 18; i++){
                if (seMatrix[i][aIndex] == '0' || seMatrix[i][bIndex] == '0') {
                    vals[index] = 0;
                    index++;
                    continue;
                }
                
                if (seMatrix[i][aIndex] == '2') count*=2;
                else if (seMatrix[i][aIndex] == 'h') count/=2;
                
                if (seMatrix[i][bIndex] == '2') count*=2;
                else if (seMatrix[i][bIndex] == 'h') count/=2;

                vals[index++] = count;
                count = 1;        
                
            }
        }
        else{
            for (int i = 0; i < 18; i++){
                if (seMatrix[i][aIndex] == '0') count *= 0;                
                else if (seMatrix[i][aIndex] == '2') count*=2;
                else if (seMatrix[i][aIndex] == 'h') count/=2;
                vals[index++] = count;
                count = 1;             
            }
        }
        
        //System.out.println("== " + a.name + " & " + b.name + " ==");
        System.out.println("---------------------------\n     Type Defences");
        for (int i = 0; i < 18; i++){
            System.out.print(types[i].name + ": ");
            for (int j = 8 - types[i].name.length(); j > 0; j--) System.out.print(" ");
            if (vals[i] == 2.0) System.out.print(tc.Background("YELLOW"));
            else if (vals[i] == 4.0) System.out.print(tc.Background("RED"));
            else if (vals[i] == 0.5) System.out.print(tc.Background("CYAN"));
            else if (vals[i] == 0.25) System.out.print(tc.Background("GREEN"));
            System.out.print(vals[i] + tc.RESET() + "\n");
        }
        //System.out.println("---------------");
    }


    public void listAllStats(){
        System.out.println("\n=+=+=+=+= Stats =+=+=+=+=");
        for (PokemonType t : types){
            PokemonType[] SE = t.getAllSuperEffect();
            PokemonType[] HE = t.getAllHalfEffect();
            PokemonType NE = t.getNoEffect();
            int numNE = 0;

            System.out.print("== "+t.name + " ==\nSE("+(SE.length)+"): ");
            for (int i = 0; i < SE.length; i++){
                if (SE[i] != null) {
                    System.out.print(SE[i].name);
                    if (i != SE.length - 1) System.out.print(",");
                }
            }
            System.out.print("\tHE("+(HE.length-1)+"): ");
            for (int i = 0; i < HE.length; i++){
                if (HE[i] != null){
                    System.out.print(HE[i].name);
                    if (i != HE.length - 1) System.out.print(",");
                }
            }
            if (NE != null) {
                numNE = 1;
                System.out.print("\tNE: " + NE.name);
            }
            System.out.print("\nRatio (SE/HE + NE): " + (SE.length)+"/"+(HE.length+numNE));
            System.out.print("\n----------------------------------------------------\n");   
        }
    }

    public void typeCombos(PokemonType[] pt){
        int[][] counts = new int[18][3];
        PokemonType[] curSE, curHE;
        PokemonType curNE;
        //System.out.println("[" + pt[0].name + ", " + pt[1].name + ", " + pt[2].name + ", " + pt[3].name + "]");
        
        for (int i = 0; i < pt.length; i++){
            curSE = pt[i].getAllSuperEffect();
            curHE = pt[i].getAllHalfEffect();
            curNE = pt[i].getNoEffect();

            for(int j = 0; j < 18; j++){
               // System.out.println(types[j].name);
                if (types[j].ExistsInArray(curSE)) counts[j][0]++;
                if (types[j].ExistsInArray(curHE)) counts[j][1]++;
                if (types[j].Equals(curNE)) counts[j][2]++;
            }

        }

        for (int i = 0; i < 18; i++){
            System.out.println(types[i].name + "\tSE: " + counts[i][0] + "\tHE: " + counts[i][1] + "\tNE: " + counts[i][2]);
        }
    }

    public void test(){
        PokemonType[] t = new PokemonType[4];
        PokemonType temp;
        int index = 0;
        while(true){
            temp = types[randomInt(0, 17)];
            if (!temp.ExistsInArray(t)) t[index++] = temp;
            if (index > 3) break;
        }
        System.out.println("[" + t[0].name + ", " + t[1].name + ", " + t[2].name + ", " + t[3].name + "]");
        typeCombos(t);
        
    }

    private int randomInt(int min, int max){
        return (int)((max-min)*Math.random())+min;
    }

}
