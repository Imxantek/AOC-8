import java.io.*;
import java.util.ArrayList;


public class Main {
    static ArrayList<String> t=new ArrayList<>();
    static void load(ArrayList<String>t) throws IOException{
        BufferedReader br = new BufferedReader(new FileReader("src/aoc8_test.txt"));
        String line="";
        while(line!=null){
            line=br.readLine();
            t.add(line);
        }
        t.removeLast();
    }

    public static Antinode calculateAntinodes1(int x1, int y1, int x2, int y2, int x_max, int y_max) {
        if(x1==x2 && y1==y2){
            return null;
        }
        int vx, vy, x_res, y_res;
        vx = x2-x1;
        vy = y2-y1;
        x_res = x2+vx;
        y_res = y2+vy;
        if(x_max > x_res && y_max > y_res && x_res >= 0 && y_res >= 0){
//            System.out.println("x:" + x_res + " y:" + y_res + " x_src "+x1+" y_src "+y1 + " x_dest "+x2+" y_dest "+y2);
            return new Antinode(x_res, y_res);
        }
        return null;
    }
    public static ArrayList<Antinode> calculateAntinodes2(int x1, int y1, int x2, int y2, int x_max, int y_max) {
        ArrayList<Antinode> antinodes = new ArrayList<>();
        if(x1==x2 && y1==y2){
            return null;
        }
        int vx, vy, x_res, y_res;
        vx = x2-x1;
        vy = y2-y1;
        x_res = x1+vx;
        y_res = y1+vy;
        while(x_max > x_res && y_max > y_res && x_res >= 0 && y_res >= 0){
            antinodes.add(new Antinode(x_res, y_res));
            x_res +=vx;
            y_res +=vy;
        }
        return antinodes;
    }

    public static void loadSignals(ArrayList<signal>signals){
        String line;

//        System.out.println(x_max + " " + y_max);

        for(int i=0; i<t.size(); i++){
            line=t.get(i);

            for(int j=0; j<line.length(); j++){
                if(line.charAt(j)!='.'){
                    signal signal = new signal(i,j, line.charAt(j));
                    signals.add(signal);
                }
            }
        }
    }

    public static void ex1(){
        ArrayList<signal> signals = new ArrayList<>();
        int x_max=t.size(), y_max=t.getFirst().length();
        loadSignals(signals);
        char type;
        ArrayList<Antinode> antinodes = new ArrayList<>();
        for(int i=0; i<signals.size(); i++){
            signal src = signals.get(i);
            type=src.getType();
            for (signal dest : signals) {
                Antinode antinode = calculateAntinodes1(src.getX(), src.getY(), dest.getX(), dest.getY(), x_max, y_max);
                if (type == dest.getType() && antinode != null) {
                    boolean found = false;
                    for (Antinode a : antinodes) {
                        if (a.getX() == antinode.getX() && a.getY() == antinode.getY()) {
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        antinodes.add(antinode);
                    }
                }
            }
        }
        System.out.println(antinodes.size());
    }

    public static void ex2(){
        ArrayList<signal> signals = new ArrayList<>();
        ArrayList<Antinode> totalAntinodes = new ArrayList<>();
        int x_max=t.size(), y_max=t.getFirst().length();
        char type;
        loadSignals(signals);

        for(int i=0; i<signals.size(); i++){
            signal src = signals.get(i);
            type=src.getType();
            for (signal dest : signals) {
                if (type == dest.getType()) {
                    ArrayList<Antinode> newAntinodes = calculateAntinodes2(src.getX(), src.getY(), dest.getX(), dest.getY(), x_max, y_max);
                    if (newAntinodes != null) {
                        for (Antinode a : totalAntinodes) {
                            newAntinodes.removeIf(b -> a.getX() == b.getX() && a.getY() == b.getY());
                        }
                        totalAntinodes.addAll(newAntinodes);
                    }
                }
            }
        }
        System.out.println(totalAntinodes.size());
    }

    public static void main(String[] args)  {

        try{
            load(t);
        }catch(IOException e){
            e.printStackTrace();
        }
        ex1();
        ex2();

    }
}