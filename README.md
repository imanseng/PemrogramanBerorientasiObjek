### 1. Entry Point Aplikasi (Gerbang Utama)
*   **File Utama:** Aplikasi ini menggunakan `App.java` (berada di paket `project.pbo`) sebagai kelas utama atau *entry point* saat Java Virtual Machine (JVM) dijalankan.
*   **Inisialisasi Awal:** Saat pertama kali di-*run*, JVM akan mengeksekusi metode `main(String[] args)`. Di sini, kelas `App` akan menginisialisasi objek statis `Scanner input` (untuk menangkap teks dari keyboard) dan `AuthService authService` (untuk melayani logika login).
*   **Alur Login:** Aplikasi masuk ke sebuah perulangan tanpa henti (`while(true)`) yang memanggil metode `prosesOtentikasiSistem()`. Jika proses login berhasil, objek `Pengguna` akan dikirim ke metode `arahkanKeDashboardRole()`. Metode inilah yang bertanggung jawab memetakan hak akses (Role), menginisialisasi objek entitas (seperti `Admin`, `Staff`, atau `Owner`), lalu membukakan menu presentasi masing-masing (contohnya memanggil `new AdminMenu(admin)` dan `adminMenu.prosesMenu()`).

### 2. Alur Layer Presentation (CLI & Navigasi)
*   **File yang Terlibat:** File seperti `AdminMenu.java`, `StaffMenu.java`, dan `OwnerMenu.java` yang berada di dalam paket `presentation`.
*   **Penangkapan Input:** Setiap menu kelas memiliki objek `Scanner` sendiri untuk menangkap apa yang diketik oleh pengguna di terminal. 
*   **Navigasi Menu (Looping):** Navigasi diatur menggunakan perulangan `do-while` yang akan terus menampilkan daftar menu selama pengguna tidak memilih menu "0" (Logout). Pilihan pengguna akan ditangkap ke dalam variabel `pilihanMenu` dan diproses menggunakan percabangan `switch-case`.
    *   *Contoh pada `AdminMenu`:* Jika pengguna mengetik '1', program akan masuk ke blok `case '1'` dan memanggil fungsi spesifik `tambahKendaraan()`. Jika '2', memanggil fungsi `lihatDaftarKendaraan()`, dan seterusnya.

### 3. Alur Komunikasi ke Layer Domain (Data Passing)
*   Data yang sudah diketik oleh pengguna di *Layer Presentation* akan dibungkus menjadi sebuah objek dari *Layer Domain* (paket `domain`). Layer presentasi tidak mengolah data mentah, melainkan memindahkannya ke layanan (*service*).
*   **Contoh Pemanggilan Method:** Pada fungsi `tambahKendaraan()` di dalam `AdminMenu.java`, program akan meminta input berupa *plat nomor*, *harga sewa*, dan *jenis kendaraan*.
*   Berdasarkan pilihan jenis, program akan menginisialisasi objek polimorfik dari *super-class* `Kendaraan`:
    *   Jika mobil: `kendaraanBaru = new Mobil(platNomor, hargaSewa, "Mobil", 0);`
    *   Jika motor: `kendaraanBaru = new Motor(platNomor, hargaSewa, "Motor", "");`
*   Objek `kendaraanBaru` tersebut kemudian dilempar (di-*passing* sebagai argumen) ke *Service Layer* menggunakan perintah: `kendaraanService.tambahKendaraan(kendaraanBaru);`.

### 4. Alur Manajemen Data (JSON & Deserialisasi)
*   Aplikasi ini tidak menggunakan *database engine* eksternal (seperti MySQL), melainkan menyimpan data menggunakan **File JSON** di folder `app/data/` (seperti `akun.json` dan `kendaraan.json`).
*   **Proses Enkripsi / Parsing:** Alur penyimpanan dan pembacaan ini ditangani murni oleh layer **Repository** (misal: `KendaraanRepository.java` dan `PenggunaRepository.java`).
*   **Library yang Digunakan:** Proyek ini memanfaatkan *library* eksternal **Gson** dari Google (`com.google.gson`).
    *   **Saat Membaca Data (Deserialization):** Melalui fungsi `loadAll()`, Gson menggunakan `FileReader` untuk membaca teks JSON, lalu metode `.fromJson()` akan secara otomatis menyusun teks tersebut menjadi bentuk *Collection Object* di memori RAM, yaitu `List<Kendaraan>`.
    *   **Saat Menyimpan Data (Serialization):** Melalui fungsi `saveAll()`, jika ada data baru, Gson memanggil `.toJson()` menggunakan `FileWriter` untuk mengubah keseluruhan `List<Kendaraan>` yang ada di memori menjadi *string/teks* ke dalam file `kendaraan.json` agar datanya tidak hilang saat program ditutup.
*   *Catatan Khusus:* Di `KendaraanRepository`, terdapat logika **Custom Serializer/Deserializer** yang cukup kompleks untuk membedakan kelas turunan `Mobil` dan `Motor` agar propertinya tidak tertukar saat diterjemahkan dari JSON.

### 5. Alur Balik (Feedback Ke User)
*   **Evaluasi Validasi:** Selama proses di atas berjalan, sistem bisa saja menemukan kendala (contoh: di fungsi `tambahKendaraan()`, jika plat nomor sudah ada, sistem menolaknya).
*   **Feedback Output Terminal:** Status keberhasilan atau kegagalan sebuah aksi tidak hanya "diam". Nilai kembalian dikirim dari Repository naik ke Service, dan dari Service dikirim kembali ke Presentation Layer. 
*   Di layer presentasi, status ini diubah menjadi *feedback* visual (teks terminal) kepada pengguna menggunakan perintah `System.out.println()`.
*   Feedback diberikan tanda/flag di awal kalimat agar profesional. *Contoh Output:*
    *   Berhasil: `[SUKSES] Data kendaraan berhasil disimpan ke json...`
    *   Gagal/Kesalahan: `[GAGAL] Plat Nomor B1234XYZ sudah terdaftar di sistem!`
*   Setelah pesan ini dicetak, alur kembali berputar ke menu utama (`do-while` di layer presentasi) sehingga aplikasi terasa *interactive* dan tidak *crash* secara tiba-tiba.
