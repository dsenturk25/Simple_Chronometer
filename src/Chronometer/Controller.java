
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

                        String stringOfSecondVal = String.valueOf(second_val);
                        String stringOfMinuteVal = String.valueOf(minute_val);
                        String stringOfHourVal = String.valueOf(hour_val);

                        if (second_val < 10) {
                            stringOfSecondVal = "0" + stringOfSecondVal;
                        }

                        if (minute_val < 10) {
                            stringOfMinuteVal = "0" + stringOfMinuteVal;
                        }

                        if (hour_val < 10) {
                            stringOfHourVal = "0" + stringOfHourVal;
                        }

                        second.setText(stringOfSecondVal);
                        minute.setText(stringOfMinuteVal);
                        hour.setText(stringOfHourVal);
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
