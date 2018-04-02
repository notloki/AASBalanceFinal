package com.notloki.aasbalancefinal;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {


            Parent root = FXMLLoader.load(getClass().getResource(
                    "/rootLayout.fxml"));


            primaryStage.setTitle(Ref.COMPANY_NAME + "     " +
                    Ref.VERSION + " " + Ref.VERSION_DATE);
            primaryStage.getIcons().add(new Image("/AASLogo.jpg"));
            primaryStage.setScene(new Scene(root, Ref.WIDTH, Ref.HEIGHT));
            primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
