package integ;

import about.verTABv1;
import analysis.orderANALv0;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.control.Tab;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import appPos.Smtph_POSv2;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import maint.Smtph_MAINTv1;

public class mergedAPPv1 extends Application
{

    //create a VBox as design
    VBox design = new VBox();
    
    //the apps for merging
    HBox order_system = new Smtph_POSv2().design();
    HBox smtph_maint = new Smtph_MAINTv1().design();
    VBox daily_analysis = new orderANALv0().underConstruction();
    StackPane about_Ver = new verTABv1().design();
    
    private void handleTabSelection(Tab tab)
    {
        if (tab.isSelected())
        {
            System.out.println("Tab clicked: " + tab.getText());
            
            if(tab.getText() == "Order Entry System")
            {
                design.setStyle("-fx-border-color: grey; -fx-border-width: 8;");
            }
            
            if(tab.getText() == "Product Maintenance")
            {
                design.setStyle("-fx-border-color: #4a4a4a; -fx-border-width: 8;");
                
            }
            
            if(tab.getText() == "Daily Order Analysis")
            {
                design.setStyle("-fx-border-color: Ghostwhite;");
                
            }
            
            if(tab.getText() == "About This System")
            {
                design.setStyle("-fx-border-color: black; -fx-border-width: 8;");
                
            }
        }
    }
    
    public void start(Stage stage)
    {

        //
        TabPane tabPane = new TabPane();
        smtph_maint.setStyle("-fx-background-color: #bebebe;");
        
        //About
        Tab tab_aboutThisSystem = new Tab("About This System", about_Ver);

        //Funtions
        Tab tab_customerOrderEntry = new Tab("Order Entry System", order_system);
        Tab tab_productMaintenance = new Tab("Product Maintenance", smtph_maint);
        //Tab tab_productMaintenance = new Tab("產品新增刪除維護", new Label("產品新增刪除維護"));
        Tab tab_dailyOrderAnalysis = new Tab("Daily Order Analysis", daily_analysis);

        tabPane.getTabs().add(tab_customerOrderEntry);
        //tabPane.getTabs().add(tab_productMaintenance);
        //tabPane.getTabs().add(tab_dailyOrderAnalysis);
        
        tab_customerOrderEntry.setOnSelectionChanged(event -> handleTabSelection(tab_customerOrderEntry));
        tab_productMaintenance.setOnSelectionChanged(event -> handleTabSelection(tab_productMaintenance));
        tab_dailyOrderAnalysis.setOnSelectionChanged(event -> handleTabSelection(tab_dailyOrderAnalysis));
        tab_aboutThisSystem.setOnSelectionChanged(event -> handleTabSelection(tab_aboutThisSystem));
        
        // create a menu
        Menu menuAbout = new Menu("About");
        Menu menu = new Menu("Funtions");
        

        // create menuitems
        MenuItem menuitem_customerOrderEntry = new MenuItem("Order Entry System");
        MenuItem menuitem_productMaintenance = new MenuItem("Product Maintenance");
        MenuItem menuitem_dailyOrderAnalysis = new MenuItem("Daily Order Analysis");
        
        MenuItem menuitem_aboutThisSystem = new MenuItem("About This System");

        // add menu items to menu
        menu.getItems().add(menuitem_customerOrderEntry);
        menu.getItems().add(menuitem_productMaintenance);
        menu.getItems().add(menuitem_dailyOrderAnalysis);
        
        menuAbout.getItems().add(menuitem_aboutThisSystem);

        // create events for menu items
        // action event
        EventHandler<ActionEvent> envent_forMenuItem = new EventHandler<ActionEvent>()
        {
            public void handle(ActionEvent e)
            {

                //取得使用者選擇哪一個選項
                MenuItem fromTab = (MenuItem) e.getSource();

                //判斷要tabPane顯示哪一個子App
                if (fromTab.equals(menuitem_customerOrderEntry))
                {

                    if (!tabPane.getTabs().contains(tab_customerOrderEntry))
                    {
                        tabPane.getTabs().add(tab_customerOrderEntry);
                        tabPane.getSelectionModel().select(tab_customerOrderEntry); //作用在此tab
                    }
                    
                }
                else if (fromTab.equals(menuitem_productMaintenance))
                {

                    if (!tabPane.getTabs().contains(tab_productMaintenance))
                    {
                        tabPane.getTabs().add(tab_productMaintenance);
                        tabPane.getSelectionModel().select(tab_productMaintenance);
                    }

                }
                else if (fromTab.equals(menuitem_dailyOrderAnalysis))
                {
                    if (!tabPane.getTabs().contains(tab_dailyOrderAnalysis))
                    {
                        tabPane.getTabs().add(tab_dailyOrderAnalysis);
                        tabPane.getSelectionModel().select(tab_dailyOrderAnalysis);
                    }
                }
                else if (fromTab.equals(menuitem_aboutThisSystem))
                {
                    if (!tabPane.getTabs().contains(tab_aboutThisSystem))
                    {
                        tabPane.getTabs().add(tab_aboutThisSystem);
                        tabPane.getSelectionModel().select(tab_aboutThisSystem);
                    }
                }
                
                //印出目前的tab名稱觀察看看
                System.out.println(tabPane.getTabs());
            }
        }; //  注意這裡要有一個分號!!

        // add envent
        menuitem_customerOrderEntry.setOnAction(envent_forMenuItem);
        menuitem_productMaintenance.setOnAction(envent_forMenuItem);
        menuitem_dailyOrderAnalysis.setOnAction(envent_forMenuItem);
        menuitem_aboutThisSystem.setOnAction(envent_forMenuItem);

        // create a menubar
        MenuBar menubar = new MenuBar();

        // add menu to menubar
        menubar.getMenus().add(menuAbout);
        menubar.getMenus().add(menu);
        

        //contiue the setting process
        design.getStylesheets().add("/css/MERGED-bootstrap3.css");
        design.getChildren().add(menubar);
        design.getChildren().add(tabPane);

        Scene scene = new Scene(design);
        stage.setScene(scene);
        stage.setTitle("Techvis Co. Integrated Order POS");        

        stage.show();
    }

    public static void main(String[] args)
    {
        launch(args);
    }

}
