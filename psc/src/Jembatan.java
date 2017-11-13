/**
 *
 * @author i14039
 */
public class Jembatan {
    
    public int sebrang1 (Orang x){
        return x.getSpeed();
    }
    
    public int sebrang2(Orang x,Orang y){
        int speed1 = x.getSpeed();
        int speed2 = y.getSpeed();
        if(speed1 > speed2){
            return speed1;
        } else {
            return speed2;
        }
    }
}
