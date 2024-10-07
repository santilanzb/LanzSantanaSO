/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Objetos;
//
/**
 *
 * @author Abraham Santana
 */
public class drive {
    private int resourse;
    private int maxResourse;
    
    public drive(int maxResourse) {
        this.maxResourse = maxResourse;
        this.resourse = 0;
    }
    
    public int add(int amount){
        int amountAdded = 0;
        if (getResourse() + amount >= getMaxResourse()){
            amountAdded = getMaxResourse() - getResourse();
            setResourse(getMaxResourse());
            
        }else{
            setResourse(getResourse() + amount);
            amountAdded = amount;
        }
        return amountAdded;
    }
    
    public boolean substract(int amount){
        if (amount > getResourse()){
            System.out.println("Out of resourses");
            return false;
        }else{
            int oldResourse = getResourse();
            setResourse(getResourse() - amount);
            //System.out.println("Drive substracted from " + oldResourse + " to " + getResourse());
            return true;
            
        }
    }
    
    public boolean isFull(){
        return (getResourse() == getMaxResourse());
    }

    public int getResourse() {
        return resourse;
    }

    public void setResourse(int resourse) {
        this.resourse = resourse;
    }

    public int getMaxResourse() {
        return maxResourse;
    }

    public void setMaxResourse(int maxResourse) {
        this.maxResourse = maxResourse;
    }
    
    
}

