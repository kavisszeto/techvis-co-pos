package about;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author kavisszeto
 */

public class verTABv1 extends Application
{

    public StackPane design()
    {

        VBox group1 = new VBox();
        group1.setSpacing(15);
        group1.setPadding(new Insets(10, 10, 10, 10));
        
        Image img = new Image("/imgs/author.png");
        ImageView imgview = new ImageView();
        imgview.setImage(img);
        imgview.setFitHeight(100);
        imgview.setFitWidth(100);

        Label label = new Label("About This System");
        Label labelName = new Label("※ Techvis Co. Integrated Order POS ※");
        Label labelVer = new Label("Version 2.0");
        Label labelDate = new Label("- 2023.6 -");
        Label labelAuthor = new Label("© Kavis Szeto 2023");
        label.setStyle("-fx-font-size: 26; -fx-font-family: \"Avenir Next\";");
        labelName.setStyle("-fx-font-size: 24;");
        labelVer.setStyle("-fx-font-size: 22;");
        labelDate.setStyle("-fx-font-size: 18;");
        labelAuthor.setStyle("-fx-font-size: 16;");
        
        group1.getChildren().add(imgview);
        group1.getChildren().add(label);
        group1.getChildren().add(labelName);
        group1.getChildren().add(labelVer);
        group1.getChildren().add(labelDate);
        group1.getChildren().add(labelAuthor);
        group1.setAlignment(Pos.CENTER);

        
        StackPane design = new StackPane();
        design.getStylesheets().add("/css/VER-bootstrap3.css");
        
        Label labelBg = new Label();
        labelBg.setPrefSize(500, 500);
        labelBg.setStyle("-fx-border-color: grey; -fx-border-width: 8; -fx-background-color: #f1f1f1;");
        
        design.getChildren().add(labelBg);
        design.getChildren().add(group1);
               
        design.setPrefSize(1079, 699);

        return design;
    }

    @Override
    public void start(Stage stage) throws Exception
    {

        StackPane root = design();
        
        Scene scene = new Scene(root);
        stage.setTitle("About This System");
        stage.setScene(scene);
        stage.show();
        System.out.println("start()");

    }

    public static void main(String[] args)
    {
        launch(args);
    }
    
}
