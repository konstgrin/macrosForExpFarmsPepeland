package org.main;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import javax.xml.transform.Source;
import java.awt.*;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.nio.DoubleBuffer;
import java.util.*;
import java.util.function.DoublePredicate;
import java.util.stream.IntStream;

import static java.awt.event.KeyEvent.*;

public class Main {
    static String path = "src/main/someData/inputs.json";

    public static void main(String[] args) throws AWTException {
        if (args.length == 0) {
            System.out.println("kys.\nRead docs pls.");
            System.exit(0);
        }

        Robot robot = new Robot();
        System.out.println(Arrays.toString(args));
        Map<String, Object> mapOfArgs = getArgs();
        for(Map.Entry<String, Object> entry: mapOfArgs.entrySet()){
            System.out.println(entry.getKey() + " " + entry.getValue());
        }

        ArrayList<Double> list = (ArrayList<Double>) mapOfArgs.get("mendingSlots");
        int[] mendingSlots = new int[list.size()];
        for(int i = 0; i < mendingSlots.length; i++){
            mendingSlots[i] = list.get(i).intValue();
        }
        int changeHandKey = ((Double) mapOfArgs.get("changeHandKey")).intValue();
        int slaysByDefault = ((Double) mapOfArgs.get("slaysByDefault")).intValue();
        double minutesByDefault = (Double) mapOfArgs.get("minutesByDefault");
        MODES modeOfMacros = args[0].equals("1") ? MODES.MENDINGBYSLOTS : args[0].equals("2") ? MODES.MENDINGBYNSTRIKES : args[0].equals("3") ? MODES.MENDINGBYNMINUTES : null;

        Actions actions = new Actions();
        robot.delay(3000);

        switch (modeOfMacros){
            case MODES.MENDINGBYSLOTS:
                actions.mendingBySlotes(robot, changeHandKey, slaysByDefault, mendingSlots);
                break;
            case MODES.MENDINGBYNSTRIKES:
                actions.mendingBySlays(robot, slaysByDefault);
                break;
            case MODES.MENDINGBYNMINUTES:
                actions.mendingByMinutes(robot, minutesByDefault);
                break;
            case null:
                System.out.println("Choose mode: 1 - By slotes. Will do const amount of slices and then switch item to get repaired from slots you chose. Sword must be in first slot btw.\n2 - By slays. Will just do const amount of slices.\n3 - By minutes. Constant amount of minutes will do slices.");
                break;
            default:
                System.out.println("kys");
        }

        System.exit(0);
    }

    static Map<String, Object> getArgs(){
        Map<String, Object> map = new HashMap<>();
        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, Object>>() {}.getType();
        try (FileReader reader = new FileReader(path)){
            map = gson.fromJson(reader, type);
        } catch (Exception e){
            System.out.println("Something went wrong.\n" + e);
        }

        return map;
    }
}