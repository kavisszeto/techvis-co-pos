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

public class Smtph_MAINTv0 extends Application {

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
        
        btnQueryAll.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //ObservableList    order_list有新增或刪除都會處動table的更新，也就是發生任何改變時都被通知
                smtph_list = FXCollections.observableList(smtphDao.getAllSmtphs());
                table.setItems(smtph_list);
                System.out.println(smtph_list);
            }
        });
        //定義"Apple"按鈕
        Button btnApple = new Button("Apple");
        btnApple.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //ObservableList    order_list有新增或刪除都會處動table的更新，也就是發生任何改變時都被通知
                smtph_list = FXCollections.observableList(smtphDao.searchByBrand("Apple"));
                table.setItems(smtph_list);
                System.out.println(smtph_list);
            }
        });
        //定義"Samsung"按鈕
        Button btnSamsung = new Button("Samsung");
        btnSamsung.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                smtph_list = FXCollections.observableList(smtphDao.searchByBrand("Samsung"));
                //表格內置放的資料來自於product_list，依據置放順序顯示
                table.setItems(smtph_list);
                System.out.println(smtph_list);
            }
        });
        //定義"Google"按鈕
        Button btnGoogle = new Button("Google");
        btnGoogle.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                smtph_list = FXCollections.observableList(smtphDao.searchByBrand("Google"));
                table.setItems(smtph_list);
                System.out.println(smtph_list);
            }
        });

        //使用容器視窗放置前面數個按鈕
        TilePane conntainerProductCategory = new TilePane();
        //conntainerProductCategory.setStyle("-fx-background-color: #D7FFEE;");
        conntainerProductCategory.getStylesheets().add("css/myButton.css");
        conntainerProductCategory.setVgap(5);
        conntainerProductCategory.setHgap(2);
        
        conntainerProductCategory.getChildren().add(btnQueryAll);
        conntainerProductCategory.getChildren().add(btnApple);
        conntainerProductCategory.getChildren().add(btnSamsung);
        conntainerProductCategory.getChildren().add(btnGoogle);
        
        return conntainerProductCategory;
    }

    //一個窗格(用磁磚窗格最方便)置放產品過濾與選擇按鈕，置放於主視窗的最上方區域。
    private HBox getProductSelectionContainer() {

        //搜尋某一筆
        Button btnFindById = new Button("Search By ID");
        btnFindById.getStyleClass().setAll("button", "info");
        TextField smtphIdField = new TextField("map0561");
        btnFindById.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                Smtph smtph = smtphDao.searchById(smtphIdField.getText());
                smtph_list.clear();
                smtph_list.add(smtph);
                //table.setItems(smtph_list);
                System.out.println(smtph_list);
            }
        });
        //搜尋某一筆
        Button btnFindByName = new Button("Search By Name");
        btnFindByName.getStyleClass().setAll("button", "info");
        TextField nameField = new TextField("Apple");
        btnFindByName.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                smtph_list = FXCollections.observableList(smtphDao.searchByNameContaining(nameField.getText()));
                //table內容須設定為新的product_list, 因為product_list是新的
                table.setItems(smtph_list);
                System.out.println(smtph_list);
            }
        });

        //使用容器放置前面數個按鈕
        HBox conntainerProductSelection = new HBox();
        conntainerProductSelection.setStyle("-fx-background-color: #D7FFEE;");
        conntainerProductSelection.setSpacing(10);
        conntainerProductSelection.getChildren().addAll(btnFindById, smtphIdField);
        conntainerProductSelection.getChildren().addAll(btnFindByName, nameField);
        
        return conntainerProductSelection;
    }

    //初始化產品表格
    private void initializeProductTable() {

        //表格初始化
        table = new TableView();
        table.setEditable(true); //表格允許修改
        table.setPrefHeight(300);
        //表格最後一欄是空白，不要顯示!
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        //定義數個欄位，用迴圈比較厲害
        TableColumn col;
        String[] columnNames = {"smtph_id", "brand", "name", "price", "photo", "description"};
        String[] columnShownNames = {"ID", "Brand", "Name", "Price", "Photo Link", "Description"};
        for (int i = 0; i < columnNames.length; i++) {
            //價格欄位是整數，需做特別處理:將字串轉換為整數
            if (columnNames[i].equals("price")) {
                //將欄位Header表頭名稱定義"產品編號", "類別"等
                col = new TableColumn<Smtph, Integer>(columnShownNames[i]);
                //col.setMinWidth(80);
                //將欄位內容名稱定義為"product_id", "category"等，
                //此名稱必須與Product物件的欄位變數一樣(不然會找不到對應)
                col.setCellValueFactory(new PropertyValueFactory<>(columnNames[i]));
                //欄位內容將字串轉換為整數
                col.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
                
            } else {
                col = new TableColumn<Smtph, String>(columnShownNames[i]);
                //col.setMinWidth(80);
                col.setCellValueFactory(new PropertyValueFactory<>(columnNames[i]));
                col.setCellFactory(TextFieldTableCell.forTableColumn()); //
            }

            //定義數量欄位被修改後進行那些動作，EventHandler事件
            col.setOnEditCommit(new EventHandler<CellEditEvent>() {
                @Override
                public void handle(CellEditEvent event) {
                    
                    int row_num = event.getTablePosition().getRow();//哪一筆row被修改
                    int col_num = event.getTablePosition().getColumn();//哪一筆column被修改

                    //int new_val = event.getNewValue(); //改成甚麼數值 需要將物件轉為整數
                    Smtph target = (Smtph) event.getTableView().getItems().get(row_num); //取得該筆果汁傳參考呼叫
                    System.out.println(target);
                    //修改成新的數值 該筆產品物件存放於product_list
                    //看col_num就知道修改的是價格還是類別?
                    switch (col_num) {
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

    //表格新增項目刪除項目之操作區塊
    private HBox getSmtphOperationContainer() {

        //定義新增一筆按鈕
        Button btnBlank = new Button("Add New Product");

        btnBlank.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //新增一筆範例
                smtph_list.add(new Smtph("mxx000", "Brand", "Smartphone", 100000, "photo.png", "Description"));
                
            }
        });

        //定義新增這一筆到資料庫按鈕
        Button btnInsert = new Button("Add Product Into Database");
        btnInsert.getStyleClass().setAll("button", "danger");
        btnInsert.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                //從table中取得目前被選到的項目，並將其轉為Product物件，再送到資料庫
                Smtph smtph = (Smtph) table.getSelectionModel().getSelectedItem();
                smtphDao.insert(smtph);
            }
        });

        //定義更新修改的這一筆
        Button btnUpdate = new Button("Update Product");
        btnUpdate.getStyleClass().setAll("button", "danger");
        btnUpdate.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                Smtph smtph = (Smtph) table.getSelectionModel().getSelectedItem();
                System.out.println(smtph);
                smtphDao.update(smtph);
            }
        });
        //定義刪除一筆按鈕
        Button btnDelete = new Button("Delete Product");
        btnDelete.getStyleClass().setAll("button", "danger");
        btnDelete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                //從table中取得目前被選到的項目
                Smtph selectedItem = (Smtph) table.getSelectionModel().getSelectedItem();
                //從表格table中或product_list刪除這一筆
                //table.getItems().remove(selectedItem);
                smtph_list.remove(selectedItem);

                //從資料庫刪除這一筆
                smtphDao.delete(selectedItem.getSmtph_id());
            }
        });

        //放置前述任務功能按鈕
        HBox containerProductOperation = new HBox();
        
        containerProductOperation.setStyle("-fx-background-color: #D2E9FF;");
        containerProductOperation.setSpacing(10);
        
        containerProductOperation.getChildren().add(btnBlank);
        containerProductOperation.getChildren().add(btnInsert);
        containerProductOperation.getChildren().add(btnUpdate);
        containerProductOperation.getChildren().add(btnDelete);
        
        return containerProductOperation;
    }

    //所有元件與事件並將所有元件放入root
    public VBox get_root_pane() {
        //根容器 所有的元件都放在裡面container，
        //最後再放進布景中scene，布景再放進舞台中stage
        VBox root = new VBox();
        root.setSpacing(10);
        root.setPadding(new Insets(10, 10, 10, 10));
        root.getStylesheets().add("/css/bootstrap3.css");
        //root.setStyle("-fx-background-color: #EFFFD7;"); 

        //塞入產品類別過濾區塊
        TilePane productCategoryTile = getSmtphBrandContainer();
        root.getChildren().add(productCategoryTile);

        //塞入產品選擇區塊
        HBox productSelectionTile = getProductSelectionContainer();
        root.getChildren().add(productSelectionTile);

        //塞入增加表格刪除項目操作之容器
        root.getChildren().add(getSmtphOperationContainer());

        //塞入表格
        initializeProductTable(); //表格初始化
        root.getChildren().add(table);
        //塞入白板display
        //display.setPrefColumnCount(10);
        display.setPrefRowCount(3);
        root.getChildren().add(display);
        
        root.setMinWidth(900);
        return root;
    }
    
    @Override
    public void start(Stage stage) throws Exception {

        // 取得root pane
        VBox root = get_root_pane();
        //塞入布景
        Scene scene = new Scene(root);
        stage.setTitle("Data Maintenance of Smartphone Products");
        stage.setScene(scene);
        stage.show();
        
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
