package Objetos;


public interface ILista {
    public void insertBegin(Data element);
    public void insertFinal(Data element);
    public void insertInIndex(Data element, int index);
    public NodoDoble deleteFinal();
    public NodoDoble deleteBegin();
    public NodoDoble deleteInIndex(int index);
    public void printList();
    public boolean isEmpty();
}
