/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author RickyWahyudi
 */
public class GeneticAlgorithm {

    public String[] kromosom;

    public GeneticAlgorithm(int input) {
        
        
        
    }
    
    public void soutKromosom(){
        for (int i = 0; i < kromosom.length; i++) {
            System.out.println(kromosom[i]);
        }
    }
    
    public static void main(String[] args){
        GeneticAlgorithm ga=new GeneticAlgorithm(10);
        
        BridgeCrossing bc = new BridgeCrossing(5);
        bc.soutPpl();
        System.out.println(bc.solve());
        ga.kromosom=bc.kromosom.split(";");
        for(int i=0;i<ga.kromosom.length;i++){
            System.out.println(ga.kromosom[i]);
        }
    }

}
