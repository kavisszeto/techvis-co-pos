package maint;

import java.util.ArrayList;
import java.util.TreeMap;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;
import models.Order;
import models.OrderDAO;
import models.OrderDetail;
import models.Smtph;
import models.SmtphDAO;

import models.ReadBrandSmtphFromDB;

public class Smtph_MAINTv1 extends Application {

    //***********產生資料DAO來使用
    private SmtphDAO smtphDao = new SmtphDAO();

    //ObservableList    product_list有新增或刪除都會處動table的更新，也就是發生任何改變時都被通知
    //先放入一個空的ArrayList
    private ObservableList<Smtph> smtph_list = FXCollections.observableList(new ArrayList());

    //顯示產品內容表格，大家都會用到，全域變數。實例變數
    private TableView table;

    //顯示訂單詳情白板
    private TextArea display = new TextArea();

    //一個窗格(用磁磚窗格最方便)置放產品過濾與選擇按鈕，置放於主視窗的最上方區域。
    private TilePane getSmtphBrandContainer() {

        //定義檢索全部按鈕
        Button btnQueryAll = new Button("See All");
        btnQueryAll.setPrefWidth(85);
        btnQueryAll.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                //ObservableList    order_list有新增或刪除都會處動table的更新，也就是發生任何改變時都被通知
                smtph_list = FXCollections.observableList(smtphDao.getAllSmtphs());
                table.setItems(smtph_list);
                System.out.println(smtph_list);
                
                String msgSBAll = String.format("%d product(s) has been found.", smtph_list.size());
                display.setText(msgSBAll);
            }
        });
        
        //定義"Apple"按鈕
        Button btnApple = new Button("Apple");
        btnApple.setPrefWidth(85);
        btnApple.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                //ObservableList    order_list有新增或刪除都會處動table的更新，也就是發生任何改變時都被通知
                smtph_list = FXCollections.observableList(smtphDao.searchByBrand("Apple"));
                table.setItems(smtph_list);
                System.out.println(smtph_list);
                
                String msgSBA = String.format("%d product(s) has been found.", smtph_list.size());
                display.setText(msgSBA);
            }
        });
        
        //定義"Samsung"按鈕
        Button btnSamsung = new Button("Samsung");
        btnSamsung.setPrefWidth(85);
        btnSamsung.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent e)
            {
                smtph_list = FXCollections.observableList(smtphDao.searchByBrand("Samsung"));
                //表格內置放的資料來自於product_list，依據置放順序顯示
                table.setItems(smtph_list);
                System.out.println(smtph_list);
                
                String msgSBS = String.format("%d product(s) has been found.", smtph_list.size());
                display.setText(msgSBS);
            }
        });
        
        //定義"Google"按鈕
        Button btnGoogle = new Button("Google");
        btnGoogle.setPrefWidth(85);
        btnGoogle.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent e)
            {
                smtph_list = FXCollections.observableList(smtphDao.searchByBrand("Google"));
                table.setItems(smtph_list);
                System.out.println(smtph_list);
                
                String msgSBG = String.format("%d product(s) has been found.", smtph_list.size());
                display.setText(msgSBG);
                
            }
        });

        //使用容器視窗放置前面數個按鈕
        TilePane conntainerSmtphBrand = new TilePane();
        conntainerSmtphBrand.setPrefColumns(1);
        //conntainerProductCategory.setStyle("-fx-background-color: #D7FFEE;");
        conntainerSmtphBrand.getStylesheets().add("/css/MAINT-bootstrap3.css");
        conntainerSmtphBrand.setVgap(10);
        conntainerSmtphBrand.setHgap(10);
        
        conntainerSmtphBrand.getChildren().add(btnQueryAll);
        conntainerSmtphBrand.getChildren().add(btnApple);
        conntainerSmtphBrand.getChildren().add(btnSamsung);
        conntainerSmtphBrand.getChildren().add(btnGoogle);
        
        return conntainerSmtphBrand;
        
    }

    //一個窗格(用磁磚窗格最方便)置放產品過濾與選擇按鈕，置放於主視窗的最上方區域。
    private HBox getSmtphSelectionContainer()
    {

        //搜尋某一筆
        Button btnSearchById = new Button("Search By ID");
        btnSearchById.getStyleClass().setAll("button", "info");
        TextField smtphIdField = new TextField();
        smtphIdField.setPromptText("map0561");
        btnSearchById.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent e)
            {
                if(smtphIdField.getText().isEmpty())
                {
                    Smtph smtph = smtphDao.searchById(smtphIdField.getPromptText());
                    smtph_list.clear();
                    smtph_list.add(smtph);
                }
                else
                {
                    Smtph smtph = smtphDao.searchById(smtphIdField.getText());
                    smtph_list.clear();
                    smtph_list.add(smtph);
                }

                //table.setItems(smtph_list);
                System.out.println(smtph_list);
                String msgSBI = String.format("%d product(s) has been found.", smtph_list.size());
                display.setText(msgSBI);
            }
        });
        
        //搜尋某一筆
        Button btnSearchByName = new Button("Search By Name");
        btnSearchByName.getStyleClass().setAll("button", "info");
        TextField nameField = new TextField();
        nameField.setPromptText("iPhone 14");
        btnSearchByName.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent e)
            {
                if(nameField.getText().isEmpty())
                {
                    smtph_list = FXCollections.observableList(smtphDao.searchByNameContaining(nameField.getPromptText()));
                }
                else
                {
                    smtph_list = FXCollections.observableList(smtphDao.searchByNameContaining(nameField.getText()));
                }
                
                //table內容須設定為新的product_list, 因為product_list是新的
                table.setItems(smtph_list);
                System.out.println(smtph_list);
                String msgSBN = String.format("%d product(s) has been found.", smtph_list.size());
                display.setText(msgSBN);
            }
        });
        
        //New 'Search By ≤ Price'
        Button btnSearchByPrice = new Button("Search By ≤ Price");
        btnSearchByPrice.getStyleClass().setAll("button", "info");
        TextField priceField = new TextField();
        priceField.setPromptText("25000");
        btnSearchByPrice.setOnAction(new EventHandler<ActionEvent>()
        {
            
            @Override
            public void handle(ActionEvent e)
            {
                int priceSearch;

                if (priceField.getText().isEmpty())
                {
                    priceSearch = Integer.parseInt(priceField.getPromptText());
                }
                else
                {
                    priceSearch = Integer.parseInt(priceField.getText());
                }                

                smtph_list = FXCollections.observableList(smtphDao.searchByPriceLessThanEqual(priceSearch));
                
                table.setItems(smtph_list);
                System.out.println(smtph_list);
                String msgSBP = String.format("%d product(s) has been found.", smtph_list.size());
                display.setText(msgSBP);
            }
        });

        //使用容器放置前面數個按鈕
        HBox containerSmtphSelection = new HBox();
        containerSmtphSelection.setStyle("-fx-background-color: #f1f1f1;");
        containerSmtphSelection.setSpacing(10);
        containerSmtphSelection.setPadding((new Insets(5, 5, 5, 5)));
        containerSmtphSelection.getChildren().addAll(btnSearchById, smtphIdField);
        containerSmtphSelection.getChildren().addAll(btnSearchByName, nameField);
        containerSmtphSelection.getChildren().addAll(btnSearchByPrice, priceField);
        containerSmtphSelection.setAlignment(Pos.CENTER);
        
        return containerSmtphSelection;
    }

    //表格新增項目刪除項目之操作區塊
    private HBox getSmtphOperationContainer()
    {

        //定義新增一筆按鈕
        Button btnBlank = new Button("Add New Product");
        btnBlank.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                
                //新增一筆範例
                smtph_list.add(new Smtph("mxx000", "Brand", "Smartphone", 100000, "photo.png", "Description"));
                
                //Message
                display.setText("Please insert all the data of the new product\nand click 'Add Product Into Database' button for saving.");
                
            }
        });

        //定義新增這一筆到資料庫按鈕
        Button btnInsert = new Button("Add Product Into Database");
        btnInsert.getStyleClass().setAll("button", "success");
        btnInsert.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent e)
            {
                //從table中取得目前被選到的項目，並將其轉為Product物件，再送到資料庫
                Smtph smtph = (Smtph) table.getSelectionModel().getSelectedItem();
                smtphDao.insert(smtph);
                
                //Message
                display.setText("Uploaded successfully. Please restart the application.");
            }
        });

        //定義更新修改的這一筆
        Button btnUpdate = new Button("Update Product");
        btnUpdate.getStyleClass().setAll("button", "warning");
        btnUpdate.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent e)
            {
                Smtph smtph = (Smtph) table.getSelectionModel().getSelectedItem();
                System.out.println(smtph);
                smtphDao.update(smtph);
                
                //Message
                display.setText("Updated successfully. Please restart the application.");
            }
        });
        //定義刪除一筆按鈕
        Button btnDelete = new Button("Delete Product");
        btnDelete.getStyleClass().setAll("button", "danger");
        btnDelete.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent e)
            {
                //從table中取得目前被選到的項目
                Smtph selectedItem = (Smtph) table.getSelectionModel().getSelectedItem();
                //從表格table中或product_list刪除這一筆
                //table.getItems().remove(selectedItem);
                smtph_list.remove(selectedItem);

                //從資料庫刪除這一筆
                smtphDao.delete(selectedItem.getSmtph_id());
                
                //Message
                display.setText("Removed successfully. Please restart the application.");
            }
        });

        //放置前述任務功能按鈕
        HBox containerSmtphOperation = new HBox();
        
        HBox group1 = new HBox();
        HBox group2 = new HBox();
        
        containerSmtphOperation.setStyle("-fx-background-color: #f6f6f6;");
        containerSmtphOperation.setSpacing(10);
        
        group1.getChildren().add(btnBlank);
        group1.setSpacing(10);
        group1.setPrefWidth(307.5);
        group1.setPadding((new Insets(5, 5, 5, 5)));
        group1.setAlignment(Pos.CENTER_RIGHT);
        
        group2.getChildren().add(btnInsert);
        group2.getChildren().add(btnUpdate);
        group2.getChildren().add(btnDelete);
        group2.setSpacing(10);
        group2.setPrefWidth(627.5);
        group2.setPadding((new Insets(5, 5, 5, 15)));
        group2.setStyle("-fx-background-color: #d4d4d4;");
        group2.setAlignment(Pos.CENTER_LEFT);
        
        containerSmtphOperation.getChildren().add(group1);
        containerSmtphOperation.getChildren().add(group2);
        
        
        return containerSmtphOperation;
    }
    
    //初始化產品表格
    private void initializeSmtphTable() {

        //表格初始化
        table = new TableView();
        table.setEditable(true); //表格允許修改
        table.setPrefHeight(471);
        //表格最後一欄是空白，不要顯示!
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        //定義數個欄位，用迴圈比較厲害
        TableColumn col;
        String[] columnNames = {"smtph_id", "brand", "name", "price", "photo", "description"};
        String[] columnShownNames = {"ID", "Brand", "Name", "Price", "Photo Link", "Description"};
        for (int i = 0; i < columnNames.length; i++)
        {
            //價格欄位是整數，需做特別處理:將字串轉換為整數
            if (columnNames[i].equals("price"))
            {
                //將欄位Header表頭名稱定義"產品編號", "類別"等
                col = new TableColumn<Smtph, Integer>(columnShownNames[i]);
                col.setMaxWidth(1500);
                //col.setMinWidth(80);
                //將欄位內容名稱定義為"product_id", "category"等，
                //此名稱必須與Product物件的欄位變數一樣(不然會找不到對應)
                col.setCellValueFactory(new PropertyValueFactory<>(columnNames[i]));
                //欄位內容將字串轉換為整數
                col.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
                
            } 
            else
            {
                col = new TableColumn<Smtph, String>(columnShownNames[i]);
                //col.setMinWidth(80);
                
                //Setting specific columns with a smallar column size
                if(columnShownNames[i].equals("ID") || columnShownNames[i].equals("Brand"))
                {
                    col.setMaxWidth(1200);
                }
                
                if(columnShownNames[i].equals("Name") || columnShownNames[i].equals("Photo Link"))
                {
                    col.setMaxWidth(2400);
                }
                
                col.setCellValueFactory(new PropertyValueFactory<>(columnNames[i]));
                col.setCellFactory(TextFieldTableCell.forTableColumn()); //
            }

            //定義數量欄位被修改後進行那些動作，EventHandler事件
            col.setOnEditCommit(new EventHandler<CellEditEvent>()
            {
                @Override
                public void handle(CellEditEvent event)
                {
                    
                    int row_num = event.getTablePosition().getRow();//哪一筆row被修改
                    int col_num = event.getTablePosition().getColumn();//哪一筆column被修改

                    //int new_val = event.getNewValue(); //改成甚麼數值 需要將物件轉為整數
                    Smtph target = (Smtph) event.getTableView().getItems().get(row_num); //取得該筆果汁傳參考呼叫
                    System.out.println(target);
                    //修改成新的數值 該筆產品物件存放於product_list
                    //看col_num就知道修改的是價格還是類別?
                    switch (col_num)
                    {
                        case 0:
                            //改成甚麼 event物件傳過來修改的值是一般物件格式Object，必須視情況轉為String或Integer
                            target.setSmtph_id((String) event.getNewValue());
                            break;
                        case 1:
                            target.setBrand((String) event.getNewValue());
                            break;
                        case 2:
                            target.setName((String) event.getNewValue());
                            break;
                        case 3:
                            //價格
                            //改成甚麼數值 需要將物件轉為整數
                            target.setPrice((Integer) event.getNewValue());
                            break;
                        case 4:
                            target.setPhoto((String) event.getNewValue());
                            break;
                        case 5:
                            target.setDescription((String) event.getNewValue());
                            break;
                    }
                }
            }); //setOnEditCommit

            table.getColumns().add(col);
            
        } // for loop

        //表格內置放的資料來自於product_list，依據置放順序顯示產品
        table.setItems(smtph_list);
        
    }

    //所有元件與事件並將所有元件放入root
    public HBox design()
    {
        HBox design = new HBox();
        //根容器 所有的元件都放在裡面container，
        //最後再放進布景中scene，布景再放進舞台中stage
        VBox group1 = new VBox();
        group1.setSpacing(10);
        group1.setPadding(new Insets(10, 10, 10, 10));
        group1.getStylesheets().add("/css/MAINT-bootstrap3.css");
        //design.setStyle("-fx-background-color: grey;");
        //root.setStyle("-fx-background-color: #EFFFD7;"); 

        //塞入產品選擇區塊
        HBox productSelectionTile = getSmtphSelectionContainer();
        group1.getChildren().add(productSelectionTile);

        //塞入增加表格刪除項目操作之容器
        group1.getChildren().add(getSmtphOperationContainer());

        //塞入表格
        initializeSmtphTable(); //表格初始化
        group1.getChildren().add(table);
        
        //塞入白板display
        //display.setPrefColumnCount(10);
        display.setPrefRowCount(3);
        display.setEditable(false);
        display.setText("Welcome to the Data Maintenance System!\nYou should be careful and aware before taking steps.");
        group1.getChildren().add(display);
        
        group1.setMinWidth(955);
        
        group1.setStyle("-fx-background-color: #e4e4e4;");

        //塞入產品類別過濾區塊
        TilePane smtphBrandTile = getSmtphBrandContainer();
        smtphBrandTile.setPadding((new Insets(10, 10, 5, 10)));
        design.getChildren().add(smtphBrandTile);
        design.getChildren().add(group1);
        
        design.setPrefWidth(1076);
        
        return design;
    }
    
    @Override
    public void start(Stage stage) throws Exception
    {

        // 取得root pane
        HBox root = design();
        root.getStylesheets().add("/css/MAINT-bootstrap3.css");
        //塞入布景
        Scene scene = new Scene(root);
        stage.setTitle("Data Maintenance of Smartphone Products");
        stage.setScene(scene);
        stage.show();
        
        /*
        System.out.println(root.getWidth());
        System.out.println(root.getHeight());
        System.out.println(table.getWidth());
        System.out.println(table.getHeight());
        */
        
    }
    
    public static void main(String[] args)
    {
        launch(args);
    }
    
}
