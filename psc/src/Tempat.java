import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author i13069
 */
public class Tempat {
    
    ArrayList<Orang>people;
    
    public Tempat(){
        people=new ArrayList<Orang>();
    }
    
    public Tempat(Orang[] input){
        people=new ArrayList<Orang>();
        for(int i=0;i<input.length;i++){
            people.add(input[i]);
        }
    }
    
    public Orang pindah(int index){
        Orang res=people.get(index);
        return people.remove(index);
    }
    
    public void sampai(Orang input){
        people.add(input);
    }
    
}
