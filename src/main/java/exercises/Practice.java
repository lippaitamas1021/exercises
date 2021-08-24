package exercises;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;

public class Practice {
    public static void main(String[] args) {
        HashMap<String, Integer> pantry = new HashMap<>();
        pantry.put("Apples", 3);
        pantry.put("Oranges", 2);
        int currentApples = pantry.get("Apples");
        pantry.put("Apples", currentApples + 4);
        System.out.println(pantry.get("Apples"));

    }
}
