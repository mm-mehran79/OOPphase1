import java.util.ArrayList;
import java.util.Collections;

public class anotherMain {
    public static void main(String args[]) {
        ArrayList <Blah> blahList = new ArrayList<>();
        Blah n1 = new Blah(2, "funf");
        Blah n2 = new Blah(1, "not");
        Blah n3 = new Blah(0, "nullino");
        blahList.add(n1);
        blahList.add(n3);
        blahList.add(n2);
        Collections.sort(blahList);
        for (Blah blah : blahList) {
            System.out.println(blah.getA() + " " + blah.getB());
        }
        ArrayList <Blah> blahList2 = new ArrayList<>();
        //blahList2.add(n1);
        //blahList2.add(n3);
        //blahList2.add(n2);
        blahList.removeAll(blahList2);
        for (Blah blah : blahList) {
            System.out.println(blah.getA() + " " + blah.getB());
        }
        System.out.println(blahList.isEmpty());
        for (int i = 1; i <= 10; i++) {
            System.out.println(i);
        }
    }
}

class Blah implements Comparable<Blah>{
    int a;
    String b;
    public Blah (int a, String b) {
        this.a = a;
        this.b = b;
    }

    public int getA() {
        return a;
    }
    public String getB() {
        return b;
    }

    @Override
    public int compareTo(Blah o) {
        if(o.getA() > a) {
            return -1;
        }
        else if(o.getA() < a) {
            return 1;
        }
        return 0;
    }
}
