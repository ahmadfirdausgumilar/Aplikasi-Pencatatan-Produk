package kelas;

import javafx.application.Application;
import javafx.stage.Stage;
import kelas.data.DataProduk;

public class MainApp extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        DataProduk data = new DataProduk();
        Navigator navigator = new Navigator(stage, data);
        navigator.bukaForm();
    }

    public static void main(String[] args) {
        launch(args);
    }
}