package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;


public class Main extends Application {

    public static AtomicInteger redCount = new AtomicInteger();
    public static AtomicInteger blueCount = new AtomicInteger();
    public static AtomicInteger yellowCount = new AtomicInteger();
    public static AtomicInteger whiteCount = new AtomicInteger();
    private Text r = new Text();
    private Text b = new Text();
    private Text y = new Text();
    private Text w = new Text();
    private Text winner = new Text();
    private HashMap<String, AtomicInteger> map = fillData();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Image icon = new Image("pepe.jpg");
        primaryStage.getIcons().add(icon);
        StackPane layout = new StackPane();

        Button redButton = new Button();
        redButton.setMinSize(100,100);
        redButton.setTranslateX(-150);
        redButton.setTranslateY(-150);
        redButton.setStyle("-fx-background-color: red;");
        redButton.setText("Red");
        redButton.setOnMouseClicked(mouseEvent -> {
            redCount.getAndIncrement();
            layout.getChildren().remove(r);
            layout.getChildren().add(updateRed());
            layout.getChildren().remove(winner);
            layout.getChildren().add(checkScores(layout));
        });

        Button blueButton = new Button();
        blueButton.setMinSize(100,100);
        blueButton.setTranslateX(150);
        blueButton.setTranslateY(150);
        blueButton.setStyle("-fx-background-color: blue;-fx-text-base-color: white");
        blueButton.setText("Blue");
        blueButton.setOnMouseClicked(mouseEvent -> {
            blueCount.getAndIncrement();
            layout.getChildren().remove(b);
            layout.getChildren().add(updateBlue());
            layout.getChildren().remove(winner);
            layout.getChildren().add(checkScores(layout));
        });

        Button yellowButton = new Button();
        yellowButton.setMinSize(100,100);
        yellowButton.setTranslateX(-150);
        yellowButton.setTranslateY(150);
        yellowButton.setStyle("-fx-background-color: yellow;");
        yellowButton.setText("Yellow");
        yellowButton.setOnMouseClicked(mouseEvent -> {
            yellowCount.getAndIncrement();
            layout.getChildren().remove(y);
            layout.getChildren().add(updateYellow());
            layout.getChildren().remove(winner);
            layout.getChildren().add(checkScores(layout));
        });

        Button whiteButton = new Button();
        whiteButton.setMinSize(100,100);
        whiteButton.setTranslateX(150);
        whiteButton.setTranslateY(-150);
        whiteButton.setStyle("-fx-background-color: white;");
        whiteButton.setText("White");
        whiteButton.setOnMouseClicked(mouseEvent -> {
            whiteCount.getAndIncrement();
            layout.getChildren().remove(w);
            layout.getChildren().add(updateWhite());
            layout.getChildren().remove(winner);
            layout.getChildren().add(checkScores(layout));
        });


        Button resetButton = new Button();
        resetButton.setMinSize(100,50);
        resetButton.setTranslateX(0);
        resetButton.setTranslateY(240);
        resetButton.setStyle("-fx-background-color: white;-fx-text-base-color: black");
        resetButton.setText("Clear");
        resetButton.setOnMouseClicked(mouseEvent -> {
            blueCount.getAndIncrement();
            layout.getChildren().remove(b);
            layout.getChildren().remove(r);
            layout.getChildren().remove(y);
            layout.getChildren().remove(w);
            blueCount.set(0);
            redCount.set(0);
            whiteCount.set(0);
            yellowCount.set(0);
            b.setText(String.valueOf(blueCount));
            r.setText(String.valueOf(redCount));
            y.setText(String.valueOf(yellowCount));
            w.setText(String.valueOf(whiteCount));

            layout.getChildren().add(b);
            layout.getChildren().add(r);
            layout.getChildren().add(y);
            layout.getChildren().add(w);
            layout.getChildren().remove(winner);
//            layout.getChildren().add(checkScores(layout));
        });

        layout.getChildren().add(redButton);
        layout.getChildren().add(blueButton);
        layout.getChildren().add(yellowButton);
        layout.getChildren().add(whiteButton);
        layout.getChildren().add(resetButton);
        layout.getChildren().add(updateRed());
        layout.getChildren().add(updateBlue());
        layout.getChildren().add(updateYellow());
        layout.getChildren().add(updateWhite());
        layout.setStyle("-fx-background-color: lightsteelblue;");


        Scene scene = new Scene(layout, Color.LIGHTSTEELBLUE);

        primaryStage.setTitle("Color counter v1.0");
        primaryStage.setHeight(600);
        primaryStage.setWidth(600);
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    public Text updateRed() {
        r.setText(String.valueOf(redCount));
        r.setTranslateX(-250);
        r.setTranslateY(-150);
        r.setStyle("-fx-font: 54 arial;");
        return r;
    }

    public Text updateBlue() {
        b.setText(String.valueOf(blueCount));
        b.setTranslateX(50);
        b.setTranslateY(150);
        b.setStyle("-fx-font: 54 arial;");
        return b;
    }

    public Text updateYellow() {
        y.setText(String.valueOf(yellowCount));
        y.setTranslateX(-250);
        y.setTranslateY(150);
        y.setStyle("-fx-font: 54 arial;");

        return y;
    }

    public Text updateWhite() {
        w.setText(String.valueOf(whiteCount));
        w.setTranslateX(50);
        w.setTranslateY(-150);
        w.setStyle("-fx-font: 54 arial;");
        return w;
    }

    public Text checkScores(StackPane layout) {
        HashMap.Entry<String, AtomicInteger> maxEntry = null;
        Text leadingInfo = new Text();
        leadingInfo.setStyle("-fx-font: 34 arial;");
        leadingInfo.setText("Prowadzi kolor :");
        leadingInfo.setTranslateY(-70);
        layout.getChildren().add(leadingInfo);

        for(HashMap.Entry<String, AtomicInteger> entry : map.entrySet()) {
            if (maxEntry == null || entry.getValue().intValue() > maxEntry.getValue().intValue()) {
                maxEntry = entry;
            }
        }

        String color = maxEntry.getKey();

        switch (color) {
            case "red":
                winner.setText("CZERWONY");
                winner.setFill(Color.RED);
                winner.setStyle("-fx-font: 54 arial;");
                return winner;
            case "blue":
                winner.setText("NIEBIESKI");
                winner.setFill(Color.BLUE);
                winner.setStyle("-fx-font: 54 arial;");
                return winner;
            case "yellow":
                winner.setText("ZOLTY");
                winner.setFill(Color.YELLOW);
                winner.setStyle("-fx-font: 54 arial;");
                return winner;
            case "white":
                winner.setText("BIALY");
                winner.setFill(Color.WHITE);
                winner.setStyle("-fx-font: 54 arial;");
                return winner;
            default:
                return null;
        }
    }

    public HashMap<String, AtomicInteger> fillData() {
        map = new HashMap<>();

        map.put("red", redCount);
        map.put("blue", blueCount);
        map.put("yellow", yellowCount);
        map.put("white", whiteCount);

        return map;
    }
}
