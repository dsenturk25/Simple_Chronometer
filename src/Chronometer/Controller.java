
package Chronometer;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.util.Timer;
import java.util.TimerTask;

public class Controller {

    @FXML
    Label second;
    @FXML
    Label minute;
    @FXML
    Label hour;
    @FXML
    Button start;

    private int second_val = 0;
    private int minute_val = 0;
    private int hour_val = 0;

    private boolean isStopped = false;
    private boolean isResetted = false;

    public void startTimer(ActionEvent e) {
        start.setDisable(true);
        isResetted = false;
        isStopped = false;

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask(){
            @Override
            public void run(){
                if(!isResetted && !isStopped) {
                    Platform.runLater(() -> {

                        second_val++;

                        if (second_val == 60) {
                            minute_val++;
                            second_val = 0;
                        }
                        if (minute_val == 60) {
                            hour_val++;
                            minute_val = 0;
                        }
                        second.setText(String.valueOf(second_val));
                        minute.setText(String.valueOf(minute_val));
                        hour.setText(String.valueOf(hour_val));
                    });
                } else {
                    timer.cancel();
                }
            }
        },0, 1000);
    }

    public void stopTimer(ActionEvent e) {
        isStopped = true;
        start.setDisable(false);
    }

    public void resetTimer(ActionEvent e) {
        isResetted = true;
        second_val = 0;
        minute_val = 0;
        hour_val = 0;
        second.setText("00");
        minute.setText("00");
        hour.setText("00");
        start.setDisable(false);
    }
}
