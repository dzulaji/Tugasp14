package Database;

import java.util.*;
import java.text.*;
import java.util.Scanner;


public class Transaksi extends Barang {
     Scanner input = new Scanner(System.in);

    
    public void NoFaktur() {
        System.out.println("====Selamat Datang di Supermarket JSI====");
        System.out.print("Inputkan No.Faktur        = ");
        NoFaktur = input.nextInt();
    }
    
    
    public void NamaBarang() {
        System.out.print("Inputkan Nama Barang      = ");
        NamaBarang = input.next();
        NamaBarang = NamaBarang.toUpperCase();
    }
    
    
    public void HargaBarang() {  
        System.out.print("Inputkan Harga Barang     = ");
        HargaBarang = input.nextInt();
    }

    
    public void Jumlah() {
        System.out.print("Inputkan Jumlah Barang    = ");
        Jumlah = input.nextInt();
    }

    
    public void SubTotal() {
        SubTotal = HargaBarang * Jumlah;
    }
 
    
    public void Discount() {
        if (SubTotal >= 1000000){
            Discount = SubTotal * 15/100;
        }

        else if (SubTotal >= 500000){
            Discount = SubTotal * 10/100; 
        }

        else if (SubTotal >= 250000){
            Discount = SubTotal * 5/100;
        }

        else {
            Discount = 0;

        }
    }
 
    
    public void TotalHarga() {
        TotalHarga = SubTotal - Discount;
    }

    
    public void Waktu(){
        Date HariSekarang = new Date( );
        SimpleDateFormat ft = new SimpleDateFormat ("E dd.MM.yyyy 'pada' hh:mm:ss a ");
        System.out.println("Tanggal dan Waktu Transaksi = " + ft.format(HariSekarang));
    }


    public void Display(){
        System.out.println("\n====Terima Kasih Sudah Berkunjung====");
        Waktu();
        System.out.println("No Faktur Barang            = " + NoFaktur);
        System.out.println("Nama Barang                 = " + NamaBarang);
        System.out.println("Harga Barang                = " + HargaBarang);
        System.out.println("Jumlah Barang               = " + Jumlah);
        System.out.println("Sub Total Transaksi         = " + SubTotal);
        System.out.println("Discount Barang             = " + Discount);
        System.out.println("Total Harga                 = " + TotalHarga);
    }
}
