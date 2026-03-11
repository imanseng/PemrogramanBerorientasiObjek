import java.util.Scanner;

// Iman Dwi Satrio (105224029)
// Tugas 2 - Latihan Control Flow

public class Tugas2 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("=== SISTEM ANALISIS NILAI MAHASISWA ===");
        System.out.print("Masukkan jumlah mahasiswa: ");
        int jumlah = input.nextInt();

        // 1. Deklarasi Array
        int[] nilaiMahasiswa = new int[jumlah];

        // 2. Perulangan untuk Input Nilai
        for (int i = 0; i < jumlah; i++) {
            System.out.print("Masukkan nilai mahasiswa ke-" + (i + 1) + ": ");
            int tempNilai = input.nextInt();

            // 3. Perbandingan (Validation)
            if (tempNilai < 0 || tempNilai > 100) {
                System.out.println("Nilai tidak valid! Masukkan antara 0-100.");
                i--; // Mengulang input untuk indeks yang sama
            } else {
                nilaiMahasiswa[i] = tempNilai;
            }
        }

        // --- KERJAKAN LOGIKA DI BAWAH INI ---
        
        // 4. Hitung Rata-rata, Tertinggi, Terendah
        double nilaiTotal = 0;
        int nilaiTertinggi = nilaiMahasiswa[0];
        int nilaiTerendah = nilaiMahasiswa[0];
        int countLulus = 0;

        for (int nilai : nilaiMahasiswa) {
            nilaiTotal += nilai;
            if (nilai > nilaiTertinggi) {
                nilaiTertinggi = nilai;
            }
            if (nilai < nilaiTerendah) {
                nilaiTerendah = nilai;
            }
            if (nilai > 75) {
                countLulus++;
            }
        }

        double averageNilai = nilaiTotal / jumlah;

        // 5. Tampilkan Hasil dan Status Kelulusan
        System.out.println("HASIL ANALISIS");
        for (int i = 0; i < jumlah; i++) {
            String status = (nilaiMahasiswa[i] > 75) ? "Lulus" : "Tidak Lulus";
            System.out.println("Mahasiswa " + (i + 1) + ": " + nilaiMahasiswa[i] + " (" + status + ")");
        }

        // 6. Bonus: Hitung mahasiswa di atas rata-rata
        int diAtasRataRata = 0;
        for (int nilai : nilaiMahasiswa) {
            if (nilai > averageNilai) {
                diAtasRataRata++;
            }
        }

        // Hasil
        System.out.printf("Nilai Rata-rata: %.2f\n", averageNilai);
        System.out.println("Nilai Tertinggi: " + nilaiTertinggi);
        System.out.println("Nilai Terendah : " + nilaiTerendah);
        System.out.println("Jumlah Mahasiswa Lulus: " + countLulus);
        System.out.println("Jumlah Mahasiswa di Atas Rata-rata: " + diAtasRataRata);
        
        input.close();
    }
}
