
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

    public Tempat kiri;
    public Tempat kanan;
    public String kromosom;
    public Jembatan cross;
    public int jumlah;

    public BridgeCrossing(int input) {
        jumlah=input;
        Orang[] ppl = new Orang[5];
        for (int i = 0; i < ppl.length; i++) {
            ppl[i] = new Orang((int) (Math.random() * 19 + 1));
        }
        
        kiri = new Tempat(ppl);
        kanan = new Tempat();
        cross = new Jembatan();
        this.sort(kiri);
    }

    public void sort(Tempat input) {
        Collections.sort(input.people, (p1, p2) -> p1.getSpeed() - p2.getSpeed());

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
                int nilaiPergi = cross.sebrang2(kiri.people.get(0), kiri.people.get(1));
                kanan.people.add(kiri.pindah(1));
                kanan.people.add(kiri.pindah(0));
                this.sort(kanan);
                res=res+nilaiPergi;
            } else {
                int index1 = Math.abs((int) (Math.random() * kiri.people.size())-1);
                int index2 =  Math.abs((int) (Math.random() * kiri.people.size())-1);
                while (index2 == index1) {
                    index2 =  Math.abs((int) (Math.random() * kiri.people.size())-1);
                }
                int nilaiPergi = cross.sebrang2(kiri.people.get(index1), kiri.people.get(index2));
                kanan.people.add(kiri.pindah(index1));
                kanan.people.add(kiri.pindah(index2));
                this.sort(kanan);
                res+=nilaiPergi+";";
                res+=kanan.people.get(0).speed+";";
                //nilaiPergi+=kanan.people.get(0).speed;
                kiri.people.add(kanan.pindah(0));
                
                
            }
            
        }
        return res;
    }
}
