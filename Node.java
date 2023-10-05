
public class Node<E>{
    private E item;
    Node<E> next;

    public Node(E item, Node<E> next){
        this.item = item;
        this.next = next;
    }

    public E getItem(){return item;}

}
