import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

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

    static Antinode calculateAntinodes(int x1, int y1, int x2, int y2, int x_max, int y_max) {
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

    public static void main(String[] args)  {

        try{
            load(t);
        }catch(IOException e){
            e.printStackTrace();
        }
        String line;
        int x_max=t.size(), y_max=t.getFirst().length();
//        System.out.println(x_max + " " + y_max);
        ArrayList<signal> signals = new ArrayList<>();

        for(int i=0; i<t.size(); i++){
            line=t.get(i);

            for(int j=0; j<line.length(); j++){
                if(line.charAt(j)!='.'){
                    signal signal = new signal(i,j, line.charAt(j));
                    signals.add(signal);
                }
            }
        }
        char c;
        int count=0;
        ArrayList<Antinode> antinodes = new ArrayList<>();
        for(int i=0; i<signals.size(); i++){
            signal src = signals.get(i);
            c=src.getType();
            for(int j=0; j<signals.size(); j++){
                signal dest = signals.get(j);
                Antinode antinode=calculateAntinodes(src.getX(), src.getY(), dest.getX(), dest.getY(), x_max, y_max);
                if(c==dest.getType() && antinode!=null){
                    boolean found = false;
                    for(Antinode a : antinodes){
                        if(a.getX()==antinode.getX() && a.getY()==antinode.getY()){
                            found = true;
                        }
                    }
                    if(!found){
                        count++;
                        antinodes.add(antinode);
                    }
                }
            }
        }
        System.out.println(count);
    }
}