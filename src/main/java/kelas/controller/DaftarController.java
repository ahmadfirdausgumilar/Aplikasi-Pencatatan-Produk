package kelas.controller;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import java.io.IOException;
import java.util.Locale;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import kelas.Navigator;
import kelas.data.DataProduk;
import kelas.model.Produk;

public class DaftarController {
    @FXML private TableView<Produk> tabel;
    @FXML private TableColumn<Produk, String> kodeCol;
    @FXML private TableColumn<Produk, String> namaCol;
    @FXML private TableColumn<Produk, String> kategoriCol;
    @FXML private TableColumn<Produk, Long> hargaCol;
    @FXML private TextField cariField;
    @FXML private Label pesanLabel;

    private Navigator navigator;
    private DataProduk data;
    private final ObservableList<Produk> dataTampilan =
            FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        kodeCol.setCellValueFactory(new PropertyValueFactory<>("kode"));
        namaCol.setCellValueFactory(new PropertyValueFactory<>("nama"));
        kategoriCol.setCellValueFactory(new PropertyValueFactory<>("kategori"));
        hargaCol.setCellValueFactory(new PropertyValueFactory<>("harga"));
        tabel.setItems(dataTampilan);
        tabel.setPlaceholder(new Label("Belum ada produk yang sesuai."));
    }

    public void setKonteks(Navigator navigator, DataProduk data) {
        this.navigator = navigator;
        this.data = data;
        cari();
    }

    @FXML
    private void cari() {
        if (data == null) {
            return;
        }
        dataTampilan.clear();
        String kata = cariField.getText().trim().toLowerCase(Locale.ROOT);
        for (Produk produk : data.getDaftar()) {
            String nama = produk.getNama().toLowerCase(Locale.ROOT);
            if (nama.contains(kata)) {
                dataTampilan.add(produk);
            }
        }
        pesanLabel.setText("Jumlah produk yang tampil: " + dataTampilan.size());
    }

    @FXML
    private void tambah() {
        try {
            navigator.bukaForm();
        } catch (IOException e) {
            pesanLabel.setText("Form gagal dibuka.");
            e.printStackTrace();
        }
    }

    @FXML
private void detail() {
    Produk produk = tabel.getSelectionModel().getSelectedItem();
    if (produk == null) {
        pesanLabel.setText("Pilih satu baris dahulu.");
        return;
    }
    try {
        navigator.bukaDetail(produk);
    } catch (Exception e) {
        pesanLabel.setText("ERROR: " + e);
        e.printStackTrace();
    }
}
    @FXML
private void hapus() {
    Produk produk = tabel.getSelectionModel().getSelectedItem();
    if (produk == null) {
        pesanLabel.setText("Pilih satu baris dahulu.");
        return;
    }
    Alert dialog = new Alert(Alert.AlertType.CONFIRMATION,
            "Hapus produk " + produk.getNama() + " (" + produk.getKode() + ")?",
            ButtonType.YES, ButtonType.NO);
    dialog.initOwner(tabel.getScene().getWindow());
    ButtonType jawaban = dialog.showAndWait().orElse(ButtonType.NO);
    if (jawaban == ButtonType.YES) {
        data.hapus(produk);
        cari();
        pesanLabel.setText("Produk " + produk.getKode() + " dihapus. "
                + "Jumlah produk yang tampil: " + dataTampilan.size());
    }
}
}