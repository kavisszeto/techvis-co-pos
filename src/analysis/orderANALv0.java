/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package analysis;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author kavisszeto
 */

public class orderANALv0 extends Application
{

    public VBox underConstruction()
    {
        VBox comingSoon = new VBox();
        
        Image img = new Image("/imgs/underconstruction.png");
        ImageView imgview = new ImageView();
        imgview.setImage(img);
        imgview.setFitHeight(200);
        imgview.setFitWidth(200);
        
        Label label = new Label("Daily Order Analysis");
        Label labelCS = new Label("!! Under Construction !!");
        label.setStyle("-fx-font-size: 26;");
        labelCS.setStyle("-fx-font-size: 22;");
        
        comingSoon.getChildren().add(imgview);
        comingSoon.getChildren().add(label);
        comingSoon.getChildren().add(labelCS);
        comingSoon.setPrefSize(1076, 699);
        comingSoon.setSpacing(10);
        comingSoon.setAlignment(Pos.CENTER);

        return comingSoon;
    }

    @Override
    public void start(Stage stage) throws Exception
    {

        VBox root = underConstruction();
        
        Scene scene = new Scene(root);
        stage.setTitle("Daily Order Analysis");
        stage.setScene(scene);
        stage.show();
        System.out.println("start()");

    }

    public static void main(String[] args)
    {
        launch(args);
    }
    
}
