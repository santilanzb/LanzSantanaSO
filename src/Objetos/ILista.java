package Objetos;


public interface ILista {
    public void insertBegin(SimulationData element);
    public void insertFinal(SimulationData element);
    public void insertInIndex(SimulationData element, int index);
    public NodoDoble deleteFinal();
    public NodoDoble deleteBegin();
    public NodoDoble deleteInIndex(int index);
    public void printList();
    public boolean isEmpty();
}
