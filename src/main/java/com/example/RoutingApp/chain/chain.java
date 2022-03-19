package com.example.RoutingApp.chain;
import com.example.RoutingApp.link.*;



import java.util.ArrayList;


public class chain {
    private  ArrayList<ArrayList<link>> linksArray;

    public ArrayList<ArrayList<link>> getLinksArray() {
        return linksArray;
    }
    public void setLinksArray(ArrayList<ArrayList<link>> linksArray) {
        this.linksArray = linksArray;
    }

    public boolean possibleToChain(){
        boolean c = false;
        for( int i = 0 ; i < getLinksArray().size(); i ++) {
            for( int j = 0 ; j < 1; j++) {
                if( getLinksArray().get(i).get(1) == getLinksArray().get(j).get(0)){
                    c = true;
                }
            }
        }
        System.out.println(c);
        return c;
    }
    public static void main(String [] args){
        link ink = new link("1","2",1.0);
        link inks = new link("2","3",1.0);
        chain chain1 = new chain();
        // chain1.linksArray.get(0).add(ink);
        // chain1.linksArray.get(0).add(inks);

    }
}
