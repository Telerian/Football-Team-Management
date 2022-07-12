import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.sql.*;

public class FootballManagement extends Application{

    private TextField tfIsim = new TextField();
    private TextField tfYas= new TextField();
    private TextField tfMevki= new TextField();
    private TextField tfForm = new TextField();
    private TextField tfMac = new TextField();
    private TextField tfGol = new TextField();
    private TextField tfAsist = new TextField();
    private TextField tfMaas = new TextField();
    private TextField tfSozlesme = new TextField();
    private TextField tfPiyasaDegeri= new TextField();

    private Button btInsert = new Button("Add");
    private Button btUpdate = new Button("Edit");
    private Button btDelete = new Button("Remove");
    private Button btList = new Button("Get");
    private Button btClear = new Button("Clear");
    private Button btSearch=new Button("Search");

    private FlowPane flowPane = new FlowPane();

    private TableView<Takim> tableView = new TableView<>();

    private String Isim;
    private int Yas;
    private String Mevki;
    private double Form;
    private int Mac;
    private int Gol;
    private int Asist;
    private int Maas;
    private int Sozlesme;
    private int PiyasaDegeri;


    @Override
    public void start(Stage primaryStage) throws Exception {
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        gridPane.add(new Label("İsim: "), 0, 0);
        gridPane.add(tfIsim, 1, 0);

        gridPane.add(new Label("Yaş: "), 0, 1);
        gridPane.add(tfYas, 1, 1);

        gridPane.add(new Label("Mevki: "), 0, 2);
        gridPane.add(tfMevki, 1, 2);

        gridPane.add(new Label("Form: "), 0, 3);
        gridPane.add(tfForm, 1, 3);

        gridPane.add(new Label("Maç: "), 0, 4);
        gridPane.add(tfMac, 1, 4);

        gridPane.add(new Label("Gol: "), 0, 5);
        gridPane.add(tfGol, 1, 5);

        gridPane.add(new Label("Asist: "), 0, 6);
        gridPane.add(tfAsist, 1, 6);

        gridPane.add(new Label("Maas: "), 0, 7);
        gridPane.add(tfMaas, 1, 7);

        gridPane.add(new Label("Sözleşme: "), 0, 8);
        gridPane.add(tfSozlesme, 1, 8);

        gridPane.add(new Label("Piyasa Değeri: "), 0, 9);
        gridPane.add(tfPiyasaDegeri, 1, 9);

        flowPane.setPadding(new Insets(10, 10, 10, 10));
        flowPane.setHgap(10);
        flowPane.setVgap(10);
        flowPane.getChildren().addAll(btInsert, btUpdate, btDelete, btList,btSearch, btClear);
        gridPane.add(flowPane, 1, 10);

        gridPane.add(getTableView(), 1, 11);

        btClear.setOnAction(e -> clear());
        btList.setOnAction(e -> list());
        btInsert.setOnAction(e->insert());
        btUpdate.setOnAction(e->update());
        btDelete.setOnAction(e->delete());
        btSearch.setOnAction(e->list(tfIsim.getText()));
        tableView.setOnMouseClicked(e -> onEdit());

        list();

        Scene scene = new Scene(gridPane, 1000, 800);
        primaryStage.setTitle("Course Management");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private TableView getTableView() {
        TableColumn<Takim, String> colIsim = new TableColumn<>("İsim");
        colIsim.setCellValueFactory(new PropertyValueFactory<>("isim"));

        TableColumn<Takim, Integer> colYas = new TableColumn<>("Yaş");
        colYas.setCellValueFactory(new PropertyValueFactory<>("yas"));

        TableColumn<Takim, String> colMevki = new TableColumn<>("Mevki");
        colMevki.setCellValueFactory(new PropertyValueFactory<>("mevki"));

        TableColumn<Takim, Double> colForm = new TableColumn<>("Form");
        colForm.setCellValueFactory(new PropertyValueFactory<>("form"));

        TableColumn<Takim, Integer> colMac = new TableColumn<>("Maç");
        colMac.setCellValueFactory(new PropertyValueFactory<>("mac"));

        TableColumn<Takim, Integer> colGol = new TableColumn<>("Gol");
        colGol.setCellValueFactory(new PropertyValueFactory<>("gol"));

        TableColumn<Takim, Integer> colAsist = new TableColumn<>("Asist");
        colAsist.setCellValueFactory(new PropertyValueFactory<>("asist"));

        TableColumn<Takim, Integer> colMaas = new TableColumn<>("Maaş");
        colMaas.setCellValueFactory(new PropertyValueFactory<>("maas"));

        TableColumn<Takim, Integer> colSozlesme = new TableColumn<>("Sözleşme");
        colSozlesme.setCellValueFactory(new PropertyValueFactory<>("sozlesme"));

        TableColumn<Takim, Integer> colPiyasaDegeri = new TableColumn<>("Piyasa Değeri");
        colPiyasaDegeri.setCellValueFactory(new PropertyValueFactory<>("piyasaDegeri"));

        tableView.getColumns().addAll(colIsim, colYas, colMevki, colForm, colGol, colAsist, colMaas, colSozlesme, colPiyasaDegeri);

        TableView.TableViewSelectionModel<Takim> selectionModel = tableView.getSelectionModel();
        selectionModel.setSelectionMode(SelectionMode.SINGLE);
        return tableView;
    }
    private void clear() {
        tfIsim.clear();
        tfYas.clear();
        tfMevki.clear();
        tfForm.clear();
        tfGol.clear();
        tfAsist.clear();
        tfMaas.clear();
        tfSozlesme.clear();
        tfPiyasaDegeri.clear();
    }
    private void onEdit() {
        if (tableView.getSelectionModel().getSelectedItem() != null) {
            Takim takim = tableView.getSelectionModel().getSelectedItem();
            tfIsim.setText(takim.getIsim());
            tfMevki.setText(takim.getMevki());
            tfYas.setText(String.valueOf(takim.getYas()));//int değer string e çevriliyor.
            tfForm.setText(String.valueOf(takim.getForm()));
            tfMac.setText(String.valueOf(takim.getMac()));
            tfGol.setText(String.valueOf(takim.getGol()));
            tfAsist.setText(String.valueOf(takim.getAsist()));
            tfMaas.setText(String.valueOf(takim.getMaas()));
            tfSozlesme.setText(String.valueOf(takim.getSozlesme()));
            tfPiyasaDegeri.setText(String.valueOf(takim.getPiyasaDegeri()));

        }
    }
        private void list() {
            try {
                tableView.getItems().clear();
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/Football", "root", "");
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select * from FootballTable");
                while (resultSet.next()) {
                    Isim = resultSet.getString(1);
                    Yas = Integer.parseInt(resultSet.getString(2));
                    Mevki = resultSet.getString(3);
                    Form = Integer.parseInt(resultSet.getString(4));
                    Mac = Integer.parseInt(resultSet.getString(5));
                    Gol = Integer.parseInt(resultSet.getString(6));
                    Asist = Integer.parseInt(resultSet.getString(7));
                    Maas = Integer.parseInt(resultSet.getString(8));
                    Sozlesme = Integer.parseInt(resultSet.getString(9));
                    PiyasaDegeri = Integer.parseInt(resultSet.getString(10));
                    tableView.getItems().add(new Takim(Isim, Yas, Mevki, Form, Mac, Gol, Asist, Maas, Sozlesme, PiyasaDegeri));
                }
                connection.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        private void list(String id) {
            try {
                tableView.getItems().clear();
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/Football", "root", "");
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select * from Football ");
                while (resultSet.next()) {
                    Isim = resultSet.getString(1);
                    Yas = Integer.parseInt(resultSet.getString(2));
                    Mevki = resultSet.getString(3);
                    Form = Double.parseDouble(resultSet.getString(4));
                    Mac = Integer.parseInt(resultSet.getString(5));
                    Gol = Integer.parseInt(resultSet.getString(6));
                    Asist = Integer.parseInt(resultSet.getString(7));
                    Maas = Integer.parseInt(resultSet.getString(8));
                    Sozlesme = Integer.parseInt(resultSet.getString(9));
                    PiyasaDegeri = Integer.parseInt(resultSet.getString(10));
                    tableView.getItems().add(new Takim(Isim, Yas, Mevki, Form, Mac, Gol, Asist, Maas, Sozlesme, PiyasaDegeri));
                }

                connection.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

    private void insert() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/Football", "root", "");
            String queryString = "INSERT INTO Takim (Isim, Yas, Mevki, Form, Mac, Gol, Asist, Maas, Sozlesme, PiyasaDegeri) VALUES(?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(queryString);
            Isim = tfIsim.getText();
            Yas=Integer.parseInt(tfYas.getText());
            Mevki=tfMevki.getText();
            Form=Double.parseDouble(tfForm.getText());
            Mac=Integer.parseInt(tfMac.getText());
            Gol=Integer.parseInt(tfGol.getText());
            Asist=Integer.parseInt(tfAsist.getText());
            Maas=Integer.parseInt(tfMaas.getText());
            Sozlesme=Integer.parseInt(tfSozlesme.getText());
            PiyasaDegeri=Integer.parseInt(tfPiyasaDegeri.getText());

            preparedStatement.setString(1,Isim);
            preparedStatement.setString(2,String.valueOf(Yas));
            preparedStatement.setString(3,Mevki);
            preparedStatement.setString(4,String.valueOf(Form));
            preparedStatement.setString(5,String.valueOf(Mac));
            preparedStatement.setString(6,String.valueOf(Gol));
            preparedStatement.setString(7,String.valueOf(Asist));
            preparedStatement.setString(8,String.valueOf(Maas));
            preparedStatement.setString(9,String.valueOf(Sozlesme));
            preparedStatement.setString(10,String.valueOf(PiyasaDegeri));
            int row=preparedStatement.executeUpdate();
            connection.close();
            list();
            clear();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    private void update() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/Football", "root", "");
            String queryString = "UPDATE FootballTable SET Yas=?, Mevki=?, Form=?, Mac=?, Gol=?, Asist=?, Maas=?, Sozlesme=?, PiyasaDegeri=? WHERE (Isim=?)";
            PreparedStatement preparedStatement = connection.prepareStatement(queryString);
            Isim = tfIsim.getText();
            Yas=Integer.parseInt(tfYas.getText());
            Mevki=tfMevki.getText();
            Form=Double.parseDouble(tfForm.getText());
            Mac=Integer.parseInt(tfMac.getText());
            Gol=Integer.parseInt(tfGol.getText());
            Asist=Integer.parseInt(tfAsist.getText());
            Maas=Integer.parseInt(tfMaas.getText());
            Sozlesme=Integer.parseInt(tfSozlesme.getText());
            PiyasaDegeri=Integer.parseInt(tfPiyasaDegeri.getText());


            preparedStatement.setString(1,Isim);
            preparedStatement.setString(2,String.valueOf(Yas));
            preparedStatement.setString(3,Mevki);
            preparedStatement.setString(4,String.valueOf(Form));
            preparedStatement.setString(5,String.valueOf(Mac));
            preparedStatement.setString(6,String.valueOf(Gol));
            preparedStatement.setString(7,String.valueOf(Asist));
            preparedStatement.setString(8,String.valueOf(Maas));
            preparedStatement.setString(9,String.valueOf(Sozlesme));
            preparedStatement.setString(10,String.valueOf(PiyasaDegeri));
            int row=preparedStatement.executeUpdate();
            connection.close();
            list();
            clear();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    private void delete() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/Football", "root", "");
            String queryString = "DELETE FROM FootballTable WHERE (Isim=?)";
            PreparedStatement preparedStatement = connection.prepareStatement(queryString);
            Isim = tfIsim.getText();
            preparedStatement.setString(1,Isim);
            int row=preparedStatement.executeUpdate();
            connection.close();
            list();
            clear();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }



}
