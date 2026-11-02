package appPos;

/**
 *
 * @author kavisszeto
 */

import java.util.TreeMap;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
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
import models.ReadBrandSmtphFromDB;

public class Smtph_POSv0 extends Application {

    //***********產生資料DAO來使用
    private OrderDAO orderDao = new OrderDAO();

    //3.宣告全域變數)並取得三種菜單的磁磚窗格，隨時被取用。
    private TilePane menuApple = getSmtphBrandMenu("Apple");
    private TilePane menuSamsung = getSmtphBrandMenu("Samsung");
    private TilePane menuGoogle = getSmtphBrandMenu("Google");

    //4.宣告一個容器(全域變數) menuContainerPane，裝不同種類的菜單，菜單類別選擇按鈕被按下，立即置放該種類的菜單。
    private VBox menuContainerPane = new VBox();

    //ObservableList    order_list有新增或刪除都會處動table的更新，也就是發生任何改變時都被通知
    private ObservableList<OrderDetail> order_list;

    private TreeMap<String, Smtph> smtph_dict = ReadBrandSmtphFromDB.readSmtph();

    //顯示訂單詳情表格
    private TableView table;

    //顯示訂單詳情白板
    private TextArea display = new TextArea();

    //結帳存檔
    private void checkSave() {

        display.setText("結帳中，列印發票...\n");

        //append_order_to_csv(); //將這一筆訂單附加儲存到檔案或資料庫
        //這裡要取得不重複的order_num編號
        String order_no = orderDao.getMaxOrderNum();

        if (order_no == null) {
            order_no = "ord000";
        }

        System.out.println(order_no);
        System.out.println(order_no.split("ord")[1]);

        //將現有訂單編號加上1
        int serial_no = Integer.parseInt(order_no.split("ord")[1]) + 001;
        System.out.println(serial_no);

        //每家公司都有其訂單或產品的編號系統，這裡用ord-xxx表之
        String new_order_no = "ord" + serial_no;

        //int sum = checkTotal();
        int total = 0;
        //將所有的訂單顯示在表格   計算總金額
        for (OrderDetail od : order_list) {
            //加總
            total += od.getSmtph_price() * od.getQuantity();
        }

        //Cart crt = new Cart(new_order_no, "2021-05-01", 123, userName);
        Order crt = new Order();
        crt.setOrder_no(new_order_no);
        crt.setTotal_price(total);
        crt.setCustomer_name("無姓名");
        crt.setCustomer_phno("無電話");
        crt.setCustomer_address("無地址");

        //寫入一筆訂單道資料庫
        orderDao.insertCart(crt);

        //逐筆寫入訂單明細
        for (int i = 0; i < order_list.size(); i++) {
            OrderDetail item = new OrderDetail();
            item.setOrder_no(new_order_no); //設定訂單編號
            item.setSmtph_id(order_list.get(i).getSmtph_id()); //設定產品編號
            item.setQuantity(order_list.get(i).getQuantity());//設定訂購數量 多少杯
            item.setSmtph_price(order_list.get(i).getSmtph_price()); //產品單價 建議不要這個欄位 不符合正規化
            item.setSmtph_name(order_list.get(i).getSmtph_name());//產品名稱 建議不要這個欄位 不符合正規化

            orderDao.insertOrderDetailItem(item);
        }

        order_list.clear();
    }

    //define your components methods-->get or prepare containers
    // 產品菜單磁磚窗格，置放你需要用到的菜單
    private TilePane getSmtphBrandMenu(String brand) {

        //取得產品清單
        TreeMap<String, Smtph> smtph_dict = ReadBrandSmtphFromDB.readSmtph();
        //磁磚窗格
        TilePane brand_menu = new TilePane(); //
        brand_menu.setVgap(10);  //垂直間隙
        brand_menu.setHgap(10);
        //設定一個 row有4個columns，放不下就放到下一個row
        brand_menu.setPrefColumns(4);

        //將產品清單內容一一置放入產品菜單磁磚窗格
        for (String item_id : smtph_dict.keySet()) {

            if (smtph_dict.get(item_id).getBrand().equals(brand)) {
                //定義新增一筆按鈕
                Button btn = new Button();

                //width, height 按鈕外框的大小，你要自行調整，讓它美觀。沒有設定外框會大小不一不好看
                btn.setPrefSize(120, 120);
                //btn.setText(smtph_dict.get(item_id).getName()); //不要顯示文字，顯示圖片就好

                Image img;
                ImageView imgview;
                
                //若沒有圖檔也不會報錯
                try {
                    img = new Image("/imgs/" + smtph_dict.get(item_id).getPhoto()); //讀出圖片
                    //按鈕元件顯示圖片Creating a graphic (image)
                    imgview = new ImageView(img);//圖片顯示物件
                } catch (Exception ex) {
                    System.out.println("讀取圖片例外");
                    imgview = new ImageView();//空白的圖片
                }

                imgview.setFitHeight(80); //設定圖片高度，你要自行調整，讓它美觀
                imgview.setPreserveRatio(true); //圖片的寬高比維持

                //Setting a graphic to the button
                btn.setGraphic(imgview); //按鈕元件顯示圖片
                brand_menu.getChildren().add(btn);  //放入菜單磁磚窗格

                //定義按鈕事件-->點選一次，就加入購物車，再點選一次，數量要+1
                btn.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        //新增一筆訂單到order_list (ArrayList)
                        //order_list.add(new Order("p-109", "新增的果汁", 30, 1));
                        addToCart(item_id);
                        System.out.println(smtph_dict.get(item_id).getName());
                    }
                });
            }
        }
        return brand_menu;
    }

    //1.多一個窗格(可以用磁磚窗格最方便)置放菜單類別選擇按鈕，置放於主視窗的最上方區域。
    private TilePane getMenuSelectionContainer() {

        //定義"果汁類"按鈕
        Button btnApple = new Button();
        btnApple.setText("Apple");
        btnApple.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                menuContainerPane.getChildren().clear();//先刪除原有的窗格再加入新的類別窗格
                menuContainerPane.getChildren().add(menuApple);
            }
        });
        //定義"茶飲類"按鈕
        Button btnSamsung = new Button("Samsung");
        btnSamsung.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                menuContainerPane.getChildren().clear();//先刪除原有的窗格再加入新的類別窗格
                menuContainerPane.getChildren().add(menuSamsung);
            }
        });
        //定義"咖啡類"按鈕
        Button btnGoogle = new Button("Google");
        btnGoogle.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                menuContainerPane.getChildren().clear();//先刪除原有的窗格再加入新的類別窗格
                menuContainerPane.getChildren().add(menuGoogle);
            }
        });

        //使用磁磚窗格，放置前面三個按鈕
        TilePane conntainerCategoryMenuBtn = new TilePane();
        //conntainerCategoryMenuBtn.setAlignment(Pos.CENTER_LEFT);
        //conntainerCategoryMenuBtn.setPrefColumns(6); //
        conntainerCategoryMenuBtn.setVgap(10);
        conntainerCategoryMenuBtn.setHgap(10);

        conntainerCategoryMenuBtn.getChildren().add(btnApple);
        conntainerCategoryMenuBtn.getChildren().add(btnSamsung);
        conntainerCategoryMenuBtn.getChildren().add(btnGoogle);

        //root.getChildren().add(categoryMenuBtnTile);
        return conntainerCategoryMenuBtn;
    }

    //初始化所有元件與事件並將所有元件放入root
    private void initializeOrderTable() {

        //訂單陣列串列初始化FXCollections類別有很多靜態方法可以操作ObservableList的"訂單陣列串列"
        order_list = FXCollections.observableArrayList();
        //前面是空的串列，若已知有兩筆訂單可以這樣設定

        /*
        order_list = FXCollections.observableArrayList(
                new Order("p-101", "葡萄汁", 80, 3),
                new Order("p-102", "番茄汁", 70, 1)
        );        */
        //也可以這樣加入一筆訂單
        //order_list.add(new Order("p-103", "西瓜汁", 80, 3));
        //表格初始化
        table = new TableView();
        table.setEditable(true); //表格允許修改
        table.setPrefHeight(300);
        //表格內置放的資料來自於order_list，依據置放順序顯示
        table.setItems(order_list);

        //table也可以這樣放入訂單
        //table.getItems().add(new Order("p-104", "奇異果汁", 50, 2));
        //定義第一個欄位column"品名"，其值來自於Order物件的某個String變數
        TableColumn<OrderDetail, String> order_item_name = new TableColumn("Name");
        //置放哪個變數值?指定這個欄位 對應到Order的"name"實例變數值
        order_item_name.setCellValueFactory(new PropertyValueFactory("smtph_name"));

        order_item_name.setPrefWidth(100); //設定欄位寬度
        order_item_name.setMinWidth(100);

        TableColumn<OrderDetail, Integer> order_item_price = new TableColumn("Price");
        order_item_price.setCellValueFactory(new PropertyValueFactory("smtph_price"));

        TableColumn order_item_qty = new TableColumn("Quantity");
        order_item_qty.setCellValueFactory(new PropertyValueFactory("quantity"));
        //這個欄位值內容可以被修改，因為qty是整數，因此須將整數轉為字串，才能異動Order物件，否則會報錯!
        order_item_qty.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

        //把3個欄位加入table中
        table.getColumns().addAll(order_item_name, order_item_price, order_item_qty);

        //表格最後一欄是空白，不要顯示!
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        //定義數量欄位被修改後進行那些動作
        order_item_qty.setOnEditCommit(new EventHandler<CellEditEvent>() {
            @Override
            public void handle(CellEditEvent event) {

                int row_no = event.getTablePosition().getRow();//哪一筆被修改
                int new_val = (Integer) event.getNewValue(); //改成甚麼數值 需要將物件轉為整數
                OrderDetail target = (OrderDetail) event.getTableView().getItems().get(row_no); //取得該筆果汁傳參考呼叫
                //修改成新的數值 該筆訂單存放於order_list
                target.setQuantity(new_val);

                System.out.println(order_list.get(row_no).getQuantity()); //顯示修改後的數值
            }
        });
    }

    //表格新增項目刪除項目之操作區塊
    private TilePane getOrderOperationContainer() {
        //定義新增一筆按鈕
        Button btnAdd = new Button();
        btnAdd.setText("Add One");
        btnAdd.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //新增一筆
                //order_list.add(new Order("p-109", "新增的果汁", 30, 1));
                addToCart("p-j-101");
            }
        });

        //定義刪除一筆按鈕
        Button btnDelete = new Button("Remove One");
        btnDelete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                //從table中取得目前被選到的項目
                Object selectedItem = table.getSelectionModel().getSelectedItem();
                //從表格table中或是從order_list刪除這一筆，擇一進行
                //table.getItems().remove(selectedItem);
                order_list.remove(selectedItem);
                checkTotal();
            }
        });

        //定義結帳按鈕
        Button btnCheck = new Button("Check Out");
        btnCheck.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                checkSave();
            }
        });
        //放置任務功能按鈕
        TilePane operationBtnTile = new TilePane();
        //operationBtnTile.setAlignment(Pos.CENTER_LEFT);
        //operationBtnTile.setPrefColumns(6);
        operationBtnTile.setVgap(10);
        operationBtnTile.setHgap(10);

        btnAdd.setDisable(true);
        operationBtnTile.getChildren().add(btnAdd);
        operationBtnTile.getChildren().add(btnDelete);
        operationBtnTile.getChildren().add(btnCheck);

        return operationBtnTile;
    }

    //計算總金額
    private void checkTotal() {
        double total = 0;
        //將所有的訂單顯示在表格   計算總金額
        for (OrderDetail od : order_list) {
            //加總
            total += od.getSmtph_price() * od.getQuantity();
        }

        //顯示總金額於display
        String totalmsg = String.format("%s %d\n", "Total:", Math.round(total));
        display.setText(totalmsg);
    }

    //加入購物車 檢查是否有重複的飲料
    private void addToCart(String item_id) {

        boolean duplication = false;
        for (int i = 0; i < order_list.size(); i++) {
            if (order_list.get(i).getSmtph_id().equals(item_id)) {
                int qty = order_list.get(i).getQuantity() + 1; //拿出qty並+1
                order_list.get(i).setQuantity(qty);
                duplication = true;
                table.refresh();
                checkTotal();
                System.out.println(item_id + "It added，quantity+1");
                break;
            }
        }
        if (!duplication) { //若是新項目則新增這一筆
            OrderDetail new_ord = new OrderDetail(
                    item_id,
                    smtph_dict.get(item_id).getBrand(),
                    smtph_dict.get(item_id).getName(),
                    smtph_dict.get(item_id).getPrice(),
                    1);
            order_list.add(new_ord);
            System.out.println(item_id);

            checkTotal(); //更新數量
        }
    }

    //根容器 所有的元件都放在裡面container VBox
    //取得跟容器的方法
    public VBox get_root_pane() {
        //根容器 所有的元件都放在裡面container，
        //最後再放進布景中scene，布景再放進舞台中stage
        VBox root = new VBox();
        root.setSpacing(10);
        root.setPadding(new Insets(10, 10, 10, 10));
        root.getStylesheets().add("/css/bootstrap3.css");

        //塞入菜單選擇區塊
        TilePane menuSelectionTile = getMenuSelectionContainer();
        root.getChildren().add(menuSelectionTile);

        //取得菜單磁磚窗格並放入根容器
        // 塞入菜單區塊 預設為果汁類
        menuContainerPane.getChildren().add(menuApple);
        root.getChildren().add(menuContainerPane);

        //塞入增加表格刪除項目操作之容器
        root.getChildren().add(getOrderOperationContainer());

        //塞入表格
        initializeOrderTable(); //表格要初始化
        root.getChildren().add(table);
        //塞入白板display
        //display.setPrefColumnCount(10);
        display.setPrefRowCount(3);
        root.getChildren().add(display);

        return root;
    }

    @Override
    public void start(Stage stage) throws Exception {

        //取得 root pane
        VBox root = get_root_pane();
        //塞入布景
        Scene scene = new Scene(root);
        stage.setTitle("Techvis Co. Order POS - Ver0");
        stage.setScene(scene);
        stage.show();
        System.out.println("start()");

    }

    public static void main(String[] args) {
        launch(args);
        //System.out.println("執行main()的launch()之後");
    }

}

