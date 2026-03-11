import java.util.Scanner;

// Tugas 1
// Nama: Iman Dwi Satrio
// NIM: 105224029

public class App {
    public static void main(String[] args) throws Exception {
        // Deklarasi variabel
        Scanner input = new Scanner(System.in);
        String nama, nim;
        int usia;
        double tinggiBadan;

        // input dari pengguna
        System.out.print("Masukkan Nama: ");
        nama = input.nextLine();
        System.out.print("Masukkan NIM: ");
        nim = input.nextLine();
        System.out.print("Masukkan Usia: ");
        usia = input.nextInt();
        System.out.print("Masukkan Tinggi Badan: ");
        tinggiBadan = input.nextDouble();

        // tampilkan informasi
        System.out.println("\nInformasi Mahasiswa");
        System.out.println("Nama\t\t: " + nama);
        System.out.println("NIM\t\t: " + nim);
        System.out.println("Usia\t\t: " + usia + " tahun");
        System.out.println("Tinggi Badan\t: " + tinggiBadan + " cm");

        // 1. Menggunakan operator aritmatika untuk menghitung hasil dari (usia * 2) + 10 / 5 - 3, kemudian menampilkan hasilnya. 
        double hasilAritmatika = (usia * 2) + 10.0 / 5 - 3;
        System.out.println("Hasil Aritmatika: " + hasilAritmatika);

        // 2. Menggunakan operator perbandingan untuk mengecek apakah usia lebih besar dari 18 dan menampilkan hasilnya (true atau false). 
        boolean checkUsia = usia > 18;
        System.out.println("Apakah usia > 18?: " + checkUsia);

        // 3. Menggunakan operator logika untuk mengecek apakah usia lebih besar dari 18 dan tinggi badan lebih dari 160 cm, lalu menampilkan hasilnya. 
        boolean checkKualifikasi = (usia > 18) && (tinggiBadan > 160);
        System.out.println("Usia > 18 & Tinggi > 160 cm?  : " + checkKualifikasi);

        // 4. Menggunakan konversi tipe data, yaitu: 
        double usiaDouble = (double)usia; // Mengonversi usia (int) menjadi double (Widening casting)
        int tinggiInt = (int)tinggiBadan; // Mengonversi tinggi badan (double) menjadi int (Narrowing casting)

        System.out.println("\nHasil Konversi");
        System.out.println("Usia (int -> double): " + usiaDouble);
        System.out.println("Tinggi Badan (double -> int): " + tinggiInt);
        
        input.close();
    }
}
