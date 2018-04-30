package com.example.softwareeng18.food4thought;

/**
 * Created by admin on 4/30/2018.
 */

public class MenuItem extends Object {
    public String custID;
    public String menuItem;

    public MenuItem(String mitem){
        this.custID = "1";
        this.menuItem = mitem;
    }
    public MenuItem(String cID, String mitem){
        this.custID = cID;
        this.menuItem = mitem;
    }
    public String toString(){
        return menuItem;
    }
    public boolean equals(Object o){
        if(o.getClass()!=MenuItem.class){
            return false;
        }
        else{
            return (this.custID+this.menuItem).equals(((MenuItem)o).custID +((MenuItem)o).menuItem);
        }
    }
}
