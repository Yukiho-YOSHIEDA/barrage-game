package controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class BoardController implements Initializable {

    @FXML
    private Label messageLabel;
    @FXML
    private Button startButton;
    @FXML
    private Button barrageButton;

    private int count;

    private Timer timer;

    private TimerTask timerTask;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        count = 0;
        barrageButton.setDisable(true);

        timer = new Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                gameFinish();
            }
        };
    }

    @FXML
    private void gameStart(ActionEvent event) {
        updateCountMessage();
        startButton.setDisable(true);
        barrageButton.setDisable(false);

        timer.schedule(timerTask, 10000L);
    }

    @FXML
    private void barrageButtonClicked(ActionEvent event) {
        count++;
        updateCountMessage();
    }

    private void updateCountMessage() {
        messageLabel.setText("連打数：" + getCount());
    }

    public int getCount() {
        return count;
    }

    private void setFinishMessage() {
        messageLabel.setText("結果は" + getCount() + "回でした");
    }

    public void gameFinish() {
        // ボタンを使用不可能に
        startButton.setDisable(true);
        barrageButton.setDisable(true);

        Platform.runLater(() -> setFinishMessage());

        timer.cancel();
    }

}
