
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author RickyWahyudi
 */
public class BridgeCrossing {
    public Tempat nyebrang;
    public Tempat kiri;
    public Tempat kanan;
    public String kromosom;
    public int jumlah;

    public BridgeCrossing(int input) {
        jumlah=input;
        Orang[] ppl = new Orang[5];
        for (int i = 0; i < ppl.length; i++) {
            ppl[i] = new Orang((int) (Math.random() * 19 + 1));
        }
        
        kiri = new Tempat(ppl);
        kanan = new Tempat();
        this.sort(kiri);
        nyebrang=new Tempat();
    }

    public void sort(Tempat input) {
        Collections.sort(input.people, (p1, p2) -> p1.getSpeed() - p2.getSpeed());

    }
    
    public int indexTerlambat(Tempat input){
        int index=0;
        for (int i = 1; i < input.people.size(); i++) {
            if(input.people.get(index).speed<input.people.get(i).speed){
                index=i;
            }
        }
        return index;
    }
    
    public int indexTercepat(Tempat input){
        int index=0;
        for (int i = 1; i < kanan.people.size(); i++) {
            if(kanan.people.get(index).speed>kanan.people.get(i).speed){
                index=i;
            }
        }
        return index;
    }
    
    public void soutPpl(){
        String res="";
        for (int i = 0; i < kiri.people.size(); i++) {
            res=res+kiri.people.get(i).speed+";";
        }
        System.out.println(res);
    }

    public String solve() {
        String res = "";
        while (kanan.people.size()!=jumlah) {
            if (kiri.people.size() == 2) {
                nyebrang.people.add(kiri.pindah(0));
                nyebrang.people.add(kiri.pindah(0));
                int terlambat=nyebrang.people.get(this.indexTerlambat(nyebrang)).speed;
                kanan.people.add(nyebrang.pindah(0));
                kanan.people.add(nyebrang.pindah(0));
                this.sort(kanan);
                res=res+terlambat;
            } else {
                int index1 = Math.abs((int) (Math.random() * kiri.people.size())-1);
                int index2 =  Math.abs((int) (Math.random() * kiri.people.size())-1);
                while (index2 == index1) {
                    index2 =  Math.abs((int) (Math.random() * kiri.people.size())-1);
                }
                
                nyebrang.people.add(kiri.pindah(index1));
                nyebrang.people.add(kiri.pindah(index2));
                int terlambat=nyebrang.people.get(this.indexTerlambat(nyebrang)).speed;
                kanan.people.add(nyebrang.pindah(0));
                kanan.people.add(nyebrang.pindah(0));
                res+=terlambat+";";
                int iT=this.indexTercepat(kanan);
                res+=kanan.people.get(iT).getSpeed()+";";
                //nilaiPergi+=kanan.people.get(iT).speed;
                kiri.people.add(kanan.pindah(iT));
                
                
            }
            
        }
        return res;
    }
}
