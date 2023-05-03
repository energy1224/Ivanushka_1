import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String fname = "input.txt";
        try {
            Scanner sc = new Scanner(new File(fname));
            int kolvoLevels = sc.nextInt();
            PlanetLevel[] levels = new PlanetLevel[kolvoLevels];
            for (int i = 1; i <= kolvoLevels; i++) {
                levels[i] = new PlanetLevel();
                int kPlanets = sc.nextInt();
                sc.nextLine();//переход на след строку файла, где уже должно быть описание планеты
                for (int j = 1; j <= kPlanets; j++) {
                    Planet planet = new Planet(i+"."+j);
                    String planetStr = sc.nextLine();
                    levels[i].planets.add(planet);

                }
                sc.nextLine();  //пропустить строку со *
            }





        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}

class Planet{
    String name;

    public Planet(String name) {
        this.name = name;
    }
}

class Tunnel{
    Planet from, to;
    int cost;
}

class PlanetLevel{
    ArrayList<Planet> planets=new ArrayList<>();

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