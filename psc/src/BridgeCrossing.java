
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
    public Tempat templateKiri[];
    public String kromosom;
    public int jumlah;

    public BridgeCrossing(int input) {
        jumlah=input;
        Orang[] ppl = new Orang[5];
        for (int i = 0; i < ppl.length; i++) {
            ppl[i] = new Orang((int) (Math.random() * 19 + 1));
        }
        
        kiri = new Tempat(ppl);
        templateKiri = new Tempat[7];
        for(int i=0;i<7;i++){
            templateKiri[i] = new Tempat(ppl);
        }
        kanan = new Tempat();
        this.sort(kiri);
        nyebrang=new Tempat();
        kromosom="";
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
        for (int i = 1; i < input.people.size(); i++) {
            if(input.people.get(index).speed>input.people.get(i).speed){
                index=i;
            }
        }
        return index;
    }
    
    public int mendekatiMean(Tempat input){
        int mean = 0;
        for(int i=0;i<input.people.size();i++){
            mean += input.people.get(i).speed;
        }
        mean = mean / input.people.size();
        int index = 0;
        int temp = Integer.MAX_VALUE;
        for(int i=index;i<input.people.size();i++){
            if(mean > input.people.get(i).getSpeed())
                if(mean - input.people.get(i).speed < temp){
                    index = i;
                    temp = input.people.get(i).speed - mean;
                } else {
                    
                }
            else {
                if(input.people.get(i).speed - mean < temp){
                    index = i;
                    temp = input.people.get(i).speed - mean;
                }
            }
        }
        return index;
    }
    
    public int indexQ1(Tempat input){
        double q1 = 0;
        q1 = (1.0/4)*(input.people.size()+1);
        int res = (int) Math.floor(q1);
        if(res!=0){
            res= res-1;
        }
        return res;
    }
    
    public int indexQ2(Tempat input){
        double q2 = 0;
        q2 = (2.0/4)*(input.people.size()+1);
        int res = (int) Math.floor(q2);
        if(res!=0){
            res= res-1;
        }
        return res;
    }
    
    public int indexQ3(Tempat input){
        double q3 = 0;
        q3 = (3.0/4)*(input.people.size()+1);
        int res = (int) Math.floor(q3);
        if(res!=0){
            res= res-1;
        }
        return res;
    }
    
    public void soutPpl(){
        String res="";
        for (int i = 0; i < kiri.people.size(); i++) {
            res=res+kiri.people.get(i).speed+";";
        }
        System.out.println(res);
    }
    
    public int fitness(int input){
        int res = 0;
        if(kanan.people.size()==2){
            int index = indexTerlambat(kanan);
            res = kanan.people.get(index).getSpeed();
        } else if(kanan.people.size()==3){
            for(int i=0;i<3;i++){
                res += kanan.people.get(i).getSpeed();
            }
        } else if(kanan.people.size()>=4){
            int a = kanan.people.get(0).getSpeed() + kanan.people.get(kanan.people.size()-2).getSpeed();
            if(a >= kanan.people.get(1).getSpeed()*2){
                int a1 = kanan.people.get(0).getSpeed()*(kanan.people.size()-3);
                for(int i=1;i<kanan.people.size()-3;i++){
                    res += kanan.people.get(i).getSpeed();
                }
                res += kanan.people.get(1).getSpeed()*2;
                res += kanan.people.get(kanan.people.size()-1).getSpeed();
            } else {
                int a1 = kanan.people.get(0).getSpeed()*(kanan.people.size()-3);
                for(int i=1;i<kanan.people.size()-3;i++){
                    res += kanan.people.get(i).getSpeed();
                }
                res += kanan.people.get(0).getSpeed();
                res += kanan.people.get(kanan.people.size()-2).getSpeed();
                res += kanan.people.get(kanan.people.size()-1).getSpeed();
            }
        } 
        if(input < res){
            res -= input;
        } else {
            res = input - res;
        }
        return res;
    }
    
    public String solve() {
        String res = "";
        int hasil = 0;
        int ii = -1;
        int jj = -1;
        int nilaiFiti = Integer.MAX_VALUE;
        int nilaiFitj = Integer.MAX_VALUE;
        int populasi[][] = new int[6][4];
        for(int i=0;i<6;i++){
            for(int j=0;j<4;j++){
                if(j==3){
                    populasi[i][j] = -1;
                } else if(j==2){
                    populasi[i][j] = 1;
                } else {
                    populasi[i][j] = Math.abs((int) (Math.random() * 6)+1);
                }
            }
        }
        int i = 0;
        while (i<6) {
            int krom1 = populasi[i][0];
            int krom2 = populasi[i][1];
            int krom3 = populasi[i][2];
            System.out.println(populasi[i][0]+" "+populasi[i][1]+" "+populasi[i][2]);
            if (kiri.people.size() == 2) {
                nyebrang.people.add(kiri.pindah(0));
                nyebrang.people.add(kiri.pindah(0));
                int terlambat=nyebrang.people.get(this.indexTerlambat(nyebrang)).speed;
                kanan.people.add(nyebrang.pindah(0));
                kanan.people.add(nyebrang.pindah(0));
                this.sort(kanan);
                res=res+terlambat;
                String temp[] = res.split(";");
                for(int j=0;j<temp.length-1;j++){;
                    hasil += Integer.parseInt(temp[j]);
                }
                populasi[i][3] = hasil;
                int fit = fitness(hasil);
                if(fit < nilaiFiti){
                    int temp1 = 0;
                    int temp2 = -1;
                    temp1 = nilaiFiti;
                    temp2 = ii;
                    nilaiFiti = fit;
                    ii = i;
                    if(temp1 < nilaiFitj){
                        nilaiFitj = temp1;
                        jj = temp2;
                    }
                } else {
                    if(fit < nilaiFitj){
                        nilaiFitj = fit;
                        jj = i;
                    }
                }
                System.out.println(res+" "+populasi[i][3]);
                i++;
                kanan = new Tempat();
                kiri = templateKiri[i];
                nyebrang = new Tempat();
                res = "";
                hasil = 0;
            } else {
                int a = 0;
                int b = 0;
                int c = 0;
                if(krom1==1){
                    a = indexTercepat(kiri);
                } else if(krom1==2){
                    a = indexTerlambat(kiri);
                } else if(krom1==3){
                    a = mendekatiMean(kiri);
                } else if(krom1==4){
                    a = indexQ1(kiri);
                } else if(krom1==5){
                    a = indexQ2(kiri);
                } else {
                    a = indexQ3(kiri);
                }
                nyebrang.people.add(kiri.pindah(a));
                
                if(krom2==1){
                    b = indexTercepat(kiri);
                } else if(krom2==2){
                    b = indexTerlambat(kiri);
                } else if(krom2==3){
                    b = mendekatiMean(kiri);
                } else if(krom2==4){
                    b = indexQ1(kiri);
                } else if(krom2==5){
                    b = indexQ2(kiri);
                } else {
                    b = indexQ3(kiri);
                }
                nyebrang.people.add(kiri.pindah(b));
                int terlambat = nyebrang.people.get(this.indexTerlambat(nyebrang)).speed;
                kanan.people.add(nyebrang.pindah(0));
                kanan.people.add(nyebrang.pindah(0));
                res+=terlambat+";";
                
                if(krom3==1){
                    c = indexTercepat(kanan);
                } else if(krom3==2){
                    c = indexTerlambat(kanan);
                } else if(krom3==3){
                    c = mendekatiMean(kanan);
                } else if(krom3==4){
                    c = indexQ1(kanan);
                } else if(krom3==5){
                    c = indexQ2(kanan);
                } else {
                    c = indexQ3(kanan);
                }
                res+=kanan.people.get(c).getSpeed()+";";
                
                kiri.people.add(kanan.pindah(c));
                /**
                nyebrang.people.add(kiri.pindah(index1));
                nyebrang.people.add(kiri.pindah(index2));
                int terlambat=nyebrang.people.get(this.indexTerlambat(nyebrang)).speed;
                kromosom=kromosom+nyebrang.people.get(0).speed+".";
                kromosom=kromosom+nyebrang.people.get(1).speed+".";
                kanan.people.add(nyebrang.pindah(0));
                kanan.people.add(nyebrang.pindah(0));
                res+=terlambat+";";
                int iT=this.indexTercepat(kanan);
                int index3 = Math.abs((int) (Math.random() * kanan.people.size())-1);
                kromosom=kromosom+kanan.people.get(index3).speed+";";
                res+=kanan.people.get(index3).getSpeed()+";";
                //nilaiPergi+=kanan.people.get(iT).speed;
                kiri.people.add(kanan.pindah(index3));
                */
                
            }
            
        }
        //return res + " " + populasi[0][0] + " " + populasi[0][1] + " " + populasi[0][2] + " " + hasil;
        int rand = Math.abs((int) (Math.random() * 3));
        int rand2 = Math.abs((int) (Math.random() * 2));
        int[] newPopulasi = new int[4];
        if(rand2==0){
            newPopulasi = populasi[ii];
            newPopulasi[rand] = populasi[jj][rand];
        } else {
            newPopulasi = populasi[jj];
            newPopulasi[rand] = populasi[ii][rand];
        }
        
        
        return ii+"   "+jj+" "+populasi[ii][0]+" "+populasi[ii][1]+" "+populasi[ii][2]+" "+populasi[ii][3]+" "+"|"+populasi[jj][0]+" "+populasi[jj][1]+" "+populasi[jj][2]+" "+populasi[jj][3]+" | "+newPopulasi[0]+" "+newPopulasi[1]+" "+newPopulasi[2];
    }
}
