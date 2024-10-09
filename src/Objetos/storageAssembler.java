/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Objetos;

/**
 *
 * @author cdmar
 */
public class storageAssembler extends Storage {
    public storageAssembler(int number){
        super(number);
    }
    
    @Override
    public int add(int amount){
        int amountAdded = amount;        
        setResourse(getResourse() + amount);                  
        return amountAdded;
    }
}
