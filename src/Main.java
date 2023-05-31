import java.io.File;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String fname = "input.txt";
        try {
            Scanner sc = new Scanner(new File(fname));
            int kolvoLevels = sc.nextInt();
            PlanetLevel[] levels = new PlanetLevel[kolvoLevels + 1];
            levels[0] = new PlanetLevel();
            levels[0].planets.add(new Planet("0.1"));


            ArrayList<Tunnel> tunnels = new ArrayList<>();

            for (int i = 1; i <= kolvoLevels; i++) {
                levels[i] = new PlanetLevel();
                int kPlanets = sc.nextInt();
                sc.nextLine();//переход на след строку файла, где уже должно быть описание планеты
                for (int j = 1; j <= kPlanets; j++) {
                    Planet planet = new Planet(i + "." + j);
                    levels[i].planets.add(planet);
                    String planetStr = sc.nextLine();
                    System.out.println(planetStr);
                    int[] dataPlanet = stringToArray(planetStr);
                    //int[] mas = new int[2];
//                    for(int z = 0; z< dataPlanet.length; z++){
//                        System.out.println(dataPlanet[z]);
                    // mas[z] = dataPlanet[z];


                    Tunnel tube = new Tunnel();
                    tube.from =
                            tube.to = planet;
                    tube.cost = dataPlanet[1];


                }


            }
            if (sc.hasNext())
                sc.nextLine();  //пропустить строку со *


            // а теперь выведем все планеты
            // System.out.println(Arrays.toString(levels));
            for (PlanetLevel lev : levels) {
                System.out.println(lev);
            }

        } catch (
                FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    public static int[] stringToArray (String s){
            String[] data = s.split(" ");
            int[] newData = new int[data.length - 1];
            for (int f = 0; f < newData.length; f++) {
                newData[f] = Integer.parseInt(data[f]);

            }
            return newData;
        }
    }


class Planet{
    String name;

    public Planet(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Planet{" + name + '}';
    }
}

class Tunnel{
    Planet from, to;
    int cost;


}

class PlanetLevel{
    ArrayList<Planet> planets=new ArrayList<>();

    @Override
    public String toString() {
        return "PlanetLevel{" + planets +   '}';
    }
}

class Path{
    ArrayList<Tunnel> tunnels=new ArrayList<>();
    public void add(Tunnel t)
    {
        tunnels.add(t);
    }

    public int getCost()
    {
        int sum=0;
        for (Tunnel t: tunnels ) {
            sum = sum + t.cost;
        }
        return sum;
    }
}