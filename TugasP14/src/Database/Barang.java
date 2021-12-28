package Database;

public class Barang implements Penjualan {
    int NoFaktur;
    String NamaBarang;
    Integer HargaBarang;
    Integer Jumlah;
    Integer SubTotal;
    Integer Discount;
    Integer TotalHarga;

    public void NoFaktur(){
        NoFaktur = 0;
    }

    public void NamaBarang(){
        NamaBarang = null;
    }

    public void HargaBarang(){
        HargaBarang = null;
    }

    public void Jumlah(){
        Jumlah = null;
    }

    public void SubTotal(){
        SubTotal = null;
    }

    public void Discount(){
        Discount = null;
    }

    public void TotalHarga(){
        TotalHarga = null;
    }
    
}
