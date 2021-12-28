package Database;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.sql.*;

public class Program {
    static Connection con;
    public static void main(String[] args) throws Exception {
        Scanner inputan = new Scanner (System.in);
        String pilihanPengguna;
        boolean isLanjutkan = true;

        String url = "jdbc:mysql://localhost:3306/tugas14";
        String username = "root";
        String password = "";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, username, password);
            System.out.println("Class Driver ditemukan");

            while (isLanjutkan){
                System.out.println("\n");
                System.out.println("DATABASE TRANSAKSI PENJUALAN");
                System.out.println("----------------------------");
                System.out.println("1.Tambah Data Pembelian");
                System.out.println("2.Hapus Data Pembelian ");
                System.out.println("3.Ubah Data Pembelian  ");
                System.out.println("4.Cari Data Pembelian  ");
                System.out.println("5.Lihat Data Pembelian ");

                System.out.print("\nMasukkan Pilihan Anda = ");
                pilihanPengguna = inputan.next();

                switch (pilihanPengguna){
                    case "1":
                            tambahdata();
                            break;
                    case "2":
                            hapusdata();
                            break;
                    case "3":
                            ubahdata();
                            break;
                    case "4":
                            caridata();
                            break;
                    case "5":
                            lihatdata();
                            break;
                    default:
                            System.err.println("\nInputan Anda Tidak ditemukan!");
                            break;
                }

                System.out.print("\nApakah Anda Masih Ingin Lanjut[y/n]?");
                pilihanPengguna = inputan.next();
                isLanjutkan = pilihanPengguna.equalsIgnoreCase("y");
            }
            System.out.println("Terima Kasih!");
            }

        catch(ClassNotFoundException ex) {
            System.err.println("Driver Error");
            System.exit(0);
            }
                
        catch(SQLException e){
            System.out.println("Tidak Berhasil Koneksi");
            }
        
        //inputan.close();
    }

//====================================================================================================================================

private static void tambahdata() throws SQLException{
    
    String text1 = "\n===TAMBAH DATA PEMBELIAN===";
    System.out.println(text1.toUpperCase());

    Transaksi transaksi = new Transaksi();

    try{
        transaksi.NoFaktur();
        transaksi.NamaBarang();
        transaksi.Jumlah();
        transaksi.HargaBarang();
        transaksi.SubTotal();

        String sql = "INSERT INTO transaksi (nofaktur, namabarang, jumlahbarang, hargabarang, totalbelanja) VALUES ('"+transaksi.NoFaktur+"','"+transaksi.NamaBarang+"','"+transaksi.Jumlah+"','"+transaksi.HargaBarang+"', '"+transaksi.SubTotal+"')";

        Statement statement = con.createStatement();
        statement.execute(sql);
        System.out.println("Berhasil Menginputkan Data Pembelian");
        
    }

    catch (SQLException e){
        System.err.println("\nTerjadi kesalahan input data");
    }
    catch (InputMismatchException e) {
        System.err.println("\nInputlah Dengan Angka Saja");
       }
       
}

//====================================================================================================================================

private static void hapusdata() {
    String text2 = "\n===HAPUS DATA PEMBELIAN===";
    System.out.println(text2.toUpperCase());
    
    Scanner inputan = new Scanner (System.in);
    Transaksi transaksi = new Transaksi();
    
    try{
        lihatdata();
        System.out.print("Ketik Nomor Faktur Yang Akan Dihapus : ");
        transaksi.NoFaktur = Integer.parseInt(inputan.nextLine());
        
        String sql = "DELETE FROM transaksi WHERE nofaktur = "+ transaksi.NoFaktur;
        Statement statement = con.createStatement();
        //ResultSet result = statement.executeQuery(sql);
        
        if(statement.executeUpdate(sql) > 0){
            System.out.println("Berhasil Menghapus Data Harga Barang (Harga Barang "+transaksi.NoFaktur+")");
        }
    }
    catch(SQLException e){
         System.out.println("Terjadi Kesalahan Dalam Menghapus Data Barang");
         System.err.println(e.getMessage());
         }
    //inputan.close(); 
}

//====================================================================================================================================

private static void ubahdata() throws SQLException{
    String text3 = "\n===UBAH DATA PEMBELIAN===";
    System.out.println(text3.toUpperCase());
    
    Scanner inputan = new Scanner (System.in);
    Transaksi transaksi = new Transaksi();

    try{
        lihatdata();
        System.out.print("Masukkan No Faktur Pembelian Yang Akan Diubah : ");
        transaksi.NoFaktur = Integer.parseInt(inputan.nextLine());

        String sql = "SELECT * FROM transaksi WHERE nofaktur = " +transaksi.NoFaktur;

        Statement statement = con.createStatement();
        ResultSet result = statement.executeQuery(sql);

        if(result.next()){
           
            System.out.print("Nama Barang ["+result.getString("namabarang")+"]\t: ");
            transaksi.NamaBarang = inputan.next();

            System.out.print("Jumlah Barang ["+result.getInt("jumlahbarang")+"]\t: ");
            transaksi.Jumlah = inputan.nextInt();

            System.out.print("Harga Barang ["+result.getInt("hargabarang")+"]\t: ");
            transaksi.HargaBarang = inputan.nextInt();

            System.out.println("\n");

            transaksi.SubTotal();

            sql = "UPDATE transaksi SET  namabarang='"+transaksi.NamaBarang+"', jumlahbarang='"+transaksi.Jumlah+"' , hargabarang='"+transaksi.HargaBarang+"', totalbelanja='"+transaksi.SubTotal+"'  WHERE nofaktur='"+transaksi.NoFaktur+"' ";
            //System.out.println(sql);

            if(statement.executeUpdate(sql) > 0){
                System.out.println("Data Berhasil diperbarui!");
            }
        }
        statement.close();
    }

    catch (SQLException e){
        System.err.println("Terjadi Kesalahan Ubah Data");
        System.err.println(e.getMessage());
        
    }
    
    //inputan.close(); 
}

//====================================================================================================================================

private static void caridata () throws SQLException {
    String text4 = "\n===Cari Data PEMBELIAN===";
    System.out.println(text4.toUpperCase());
    
    Scanner inputan = new Scanner (System.in);
            
    System.out.print("Masukkan Nomor Faktur : ");
    
    String keyword = inputan.nextLine();
    Statement statement = con.createStatement();
    String sql = "SELECT * FROM transaksi WHERE nofaktur LIKE '%"+keyword+"%'";
    ResultSet result = statement.executeQuery(sql); 
            
    while(result.next()){
        System.out.println("\nNomor Faktur\t");
        System.out.println(result.getString("nofaktur"));
        System.out.println("\nNama Barang\t");
        System.out.println(result.getString("namabarang"));
        System.out.println("\nJumlah Barang\t");
        System.out.println(result.getInt("jumlahbarang"));
        System.out.println("\nHarga Barang\t");
        System.out.println(result.getInt("hargabarang"));
        System.out.println("\n--------------------------------------");
    }
    //inputan.close();
}

//====================================================================================================================================

private static void lihatdata() throws SQLException{
    String text5 = "\n===DATA SELURUH PEMBELIAN===";
    System.out.println(text5.toUpperCase());

    String sql = "SELECT * FROM transaksi";
    Statement statement = con.createStatement();
    ResultSet result = statement.executeQuery(sql);

    while (result.next()){
        System.out.println("\nNomor Faktur\t");
        System.out.println(result.getString("nofaktur"));
        System.out.println("\nNama Barang\t");
        System.out.println(result.getString("namabarang"));
        System.out.println("\nJumlah Barang\t");
        System.out.println(result.getInt("jumlahbarang"));
        System.out.println("\nHarga Barang\t");
        System.out.println(result.getInt("hargabarang"));
        System.out.println("\n--------------------------------------");
    }
}

}
