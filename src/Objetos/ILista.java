package Objetos;


public interface ILista {
    public void insertBegin(Datos element);
    public void insertFinal(Datos element);
    public void insertInIndex(Datos element, int index);
    public NodoDoble deleteFinal();
    public NodoDoble deleteBegin();
    public NodoDoble deleteInIndex(int index);
    public void printList();
    public boolean isEmpty();
}
