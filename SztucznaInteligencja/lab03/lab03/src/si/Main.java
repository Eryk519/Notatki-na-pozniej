package si;

import si.core.MRV;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Kamil Lipiński 155069 & Cezary Szymański 155966, grupa 3");
        System.out.println("ZAD1");
        TriangleCSP triangle = new TriangleCSP();
        MRV search = new MRV();
        System.out.println(search.solve(triangle));

        System.out.println();
        System.out.println("ZAD2");
        String filePath = "C:\\Users\\Kamil\\IdeaProjects\\si-lipinski_szymanski\\lab03\\src\\si\\SystemDecyzyjny.txt";
        ArrayList<String> system = loadFile(filePath);

        System.out.println("Wygenerowany system decyzyjny:");
        for (String i : system){
            System.out.println(i);
        }
        int x = system.get(0).split(" ").length-1;
        int y = system.size();
        int[] decisions = new int[y];
        for(int i = 0; i < y; i++){
            decisions[i] = Integer.valueOf(system.get(i).split(" ")[x]);
        }

        int[][] attributes = new int[y][x];
        for(int i = 0; i < y; i++){
            for (int j = 0; j < x; j++){
                attributes[i][j] = Integer.valueOf(system.get(i).split(" ")[j]);
            }
        }
        
    }

    public static ArrayList<String> loadFile (String filePath) throws Exception {
        BufferedReader bufReader = new BufferedReader(new FileReader(filePath));
        ArrayList<String> listOfLines = new ArrayList<>();
        String line = bufReader.readLine();
        while (line != null) {
            listOfLines.add(line);
            line = bufReader.readLine();
        }
        bufReader.close();
        return listOfLines;
    }
}
