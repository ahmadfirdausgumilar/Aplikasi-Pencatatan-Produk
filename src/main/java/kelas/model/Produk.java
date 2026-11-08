package kelas.model;

public class Produk {
    private final String kode;
    private final String nama;
    private final String kategori;
    private final long harga;

    public Produk(String kode, String nama, String kategori, long harga) {
        if (kode == null || kode.isBlank()) {
            throw new IllegalArgumentException("Kode produk wajib diisi.");
        }
        if (nama == null || nama.isBlank()) {
            throw new IllegalArgumentException("Nama produk wajib diisi.");
        }
        if (kategori == null || kategori.isBlank()) {
            throw new IllegalArgumentException("Kategori wajib dipilih.");
        }
        if (harga <= 0) {
            throw new IllegalArgumentException("Harga harus lebih dari nol.");
        }
        this.kode = kode;
        this.nama = nama.trim();
        this.kategori = kategori;
        this.harga = harga;
    }

    public String getKode() { return kode; }
    public String getNama() { return nama; }
    public String getKategori() { return kategori; }
    public long getHarga() { return harga; }
}