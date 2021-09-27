package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("mainScreen.fxml"));
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}



/*public class Main extends Application {

    ArrayList<Button> buttons = new ArrayList<>();
    Map<Button, Integer> strategyMap = new HashMap<>();
    ArrayList<Button> strategyButtons = new ArrayList<>();
    ArrayList<Integer> selectedStrategies = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("IPD Tournament");

        buttons.add(new Button());
        buttons.add(new Button());
        buttons.add(new Button());

        buttons.get(0).setText("Round-Robin");
        buttons.get(1).setText("Single-Elimination");
        buttons.get(2).setText("GO");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        for (int i = 0; i < 4; i++) {
            Button button = new Button();
            strategyMap.put(button, i);
            strategyButtons.add(button);
            button.setText(Integer.toString(i));
            GridPane.setConstraints(button, 30,i);
        }

        buttons.get(0).setLayoutX(0);
        buttons.get(0).setLayoutY(3);
        GridPane.setConstraints(buttons.get(1), 0,1);
        GridPane.setConstraints(buttons.get(2), 0,2);


        strategyButtons.forEach(button -> {
            grid.getChildren().add(button);

            button.setOnAction(e -> {
                selectedStrategies.add(strategyMap.get(button));
                System.out.print("added");
            });
        });

        buttons.forEach(button -> {
            grid.getChildren().add(button);

            button.setOnAction(e -> {
                System.out.print(selectedStrategies);

            });
        });

        Scene scene = new Scene(grid, 600, 500);

        primaryStage.setScene(scene);
        primaryStage.show();
    }*/
