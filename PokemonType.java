import java.util.Arrays;

public class PokemonType implements Comparable<PokemonType>{
    public String name;
    Node<PokemonType> halfEffect, superEffect, effect;
    PokemonType NE;
    int numHE, numSE, numEffect;
    boolean hasNE;


    public PokemonType(String name){
        this.name = name;
        halfEffect = new Node<PokemonType>(null, null);
        superEffect = new Node<PokemonType>(null, null);
        effect = new Node<PokemonType>(null, null);
        numHE = numSE = numEffect;
    }

    public void addHalfEffect(PokemonType t){

        if (numHE <= 0) halfEffect.next = new Node<PokemonType>(t, halfEffect.next);
        else{
            Node<PokemonType> p = halfEffect.next;
            Node<PokemonType> q = null;
            while (p != null){
                if (t == p.getItem()) return;
                q = p;
                p = p.next;
            }
            q.next = new Node<PokemonType>(t, q.next);
        }
        numHE++;
    }
    public void addSuperEffect(PokemonType t){
        if (numSE <= 0) superEffect.next = new Node<PokemonType>(t, superEffect.next);
        else{
            Node<PokemonType> p = superEffect.next;
            Node<PokemonType> q = null;
            while (p != null){
                if (t == p.getItem()) return;
                q = p;
                p = p.next;
            }
            q.next = new Node<PokemonType>(t, q.next);
        }
        numSE++;
    }
    public void addEffect(PokemonType t){
        if (numEffect <= 0) effect.next = new Node<PokemonType>(t, effect.next);
        else{
            Node<PokemonType> p = effect.next;
            Node<PokemonType> q = null;
            while (p != null){
                if (t == p.getItem()) return;
                q = p;
                p = p.next;
            }
            q.next = new Node<PokemonType>(t, q.next);
        }
        numEffect++;
    }
    public void setNoEffect(PokemonType t){
        NE = t;
    }

    public String toString(){
        return name;
    }

    public boolean Equals(PokemonType t){
        if (t == null) return false;
        if (this.name.equals(t.name)) return true;
        else return false;
    }

    public boolean ExistsInArray(PokemonType[] arr){
        for (int i = 0; i < arr.length; i++){
            if (this.Equals(arr[i])) return true;
        }
        return false;
    }

    public PokemonType[] getAllEffect() {return createArray(effect);}
    public PokemonType[] getAllHalfEffect() {return createArray(halfEffect);}
    public PokemonType[] getAllSuperEffect() {return createArray(superEffect);}
    public PokemonType getNoEffect() {return NE;}

    


    private PokemonType[] createArray(Node<PokemonType> P){
        PokemonType[] temp = new PokemonType[18];
        int i = 0;
        P = P.next;
        while (P != null){
            temp[i++] = P.getItem();
            P = P.next;
        }
        return Arrays.copyOfRange(temp, 0, i);
    }

    @Override
    public int compareTo(PokemonType t) {
        if (name.equals(t.name)) return 0;
        else return -1;
    }

    
}
