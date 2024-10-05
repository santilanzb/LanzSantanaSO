/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Objetos;

public class NodoDoble {
    private NodoDoble next, previous;
    private Object element;

    public NodoDoble(Datos element) {
        this.next = this.previous = null;
        this.element = element;
    }

    public NodoDoble getNext() {
        return next;
    }

    public void setNext(NodoDoble next) {
        this.next = next;
    }

    public NodoDoble getPrevious() {
        return previous;
    }

    public void setPrevious(NodoDoble previous) {
        this.previous = previous;
    }

    public Datos getElement() {
        return (Datos) element;
    }

    public void setElement(Object element) {
        this.element = element;
    }
    
    
}
