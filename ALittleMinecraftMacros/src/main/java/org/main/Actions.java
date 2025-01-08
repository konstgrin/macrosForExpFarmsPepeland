package org.main;

import java.awt.*;
import java.awt.event.InputEvent;
import java.util.Timer;
import java.util.TimerTask;

public class Actions {
    public void mendingBySlotes(Robot robot, int changeHandKey, int slays, int[] slots){
        for(int slot: slots){
            int key = 48 + slot;
            robot.keyPress(key);
            robot.keyRelease(key);

            robot.delay(1000);

            robot.keyPress(changeHandKey);
            robot.keyRelease(changeHandKey);

            robot.delay(1000);

            robot.keyPress(49);
            robot.keyRelease(49);

            for(int i = 1; i <= slays; i++){
                robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                robot.delay(1200);
            }
        }
    }

    public void mendingBySlays(Robot robot, int slays){
        for(int i = 1; i <= slays; i++){
            robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
            robot.delay(1200);
        }
    }


    public void mendingByMinutes(Robot robot, double minutes) {
        final boolean[] stop = {false};
        TimerTask stopMacros = new TimerTask() {
            @Override
            public void run() {
                stop[0] = true;
            }
        };

        Timer timer = new Timer();
        timer.schedule(stopMacros, (long) (60000 * minutes));

        while (!stop[0]){
            robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
            robot.delay(1200);
        }
    }
}
