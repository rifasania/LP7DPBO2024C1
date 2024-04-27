# LP7DPBO2024C1

# JANJI
Saya Rifa Sania NIM 2206697 mengerjakan Latihan Praktikum 7 dalam mata kuliah Desain Pemrograman Berorientasi Objek
untuk keberkahanNya maka saya tidak melakukan kecurangan seperti yang telah dispesifikasikan. Aamiin...

# ALUR PROGRAM
1. App.java:
- Kelas App memiliki method main sebagai entry point dari program. Di dalam method main, sebuah objek StartMenuFrame dibuat dan ditampilkan ke layar.
- Kelas StartMenuFrame merupakan subclass dari JFrame yang berfungsi sebagai frame awal yang menampilkan tombol "Start Game". Ketika tombol tersebut ditekan, StartMenuFrame akan ditutup dan method openGameFrame() akan dipanggil untuk membuka frame permainan Flappy Bird.
StartMenuFrame.java:
- Kelas StartMenuFrame merupakan subclass dari JFrame yang menampilkan tombol "Start Game".
Saat tombol "Start Game" ditekan, frame ini akan ditutup dan method openGameFrame() dipanggil untuk membuka frame permainan Flappy Bird.
2. FlappyBird.java:
- Kelas FlappyBird adalah subclass dari JPanel yang digunakan untuk menampilkan permainan Flappy Bird.
- Di konstruktor, beberapa inisialisasi dilakukan seperti mengatur preferensi ukuran panel, menginisialisasi gambar-gambar yang digunakan, membuat objek Player, dan mengatur timer untuk pergerakan dan penempatan pipa.
- Method paintComponent() digunakan untuk menggambar elemen-elemen permainan seperti background, pemain, dan pipa.
- Method move() mengatur pergerakan pemain dan pipa, serta mendeteksi tabrakan antara pemain dan pipa.
- Method placePipes() digunakan untuk menempatkan pipa-pipa di layar.
- Method restartGame() digunakan untuk mereset kondisi permainan saat pemain memilih untuk memulai ulang setelah game selesai.
- Implementasi dari ActionListener dan KeyListener digunakan untuk menangani input pengguna seperti pergerakan pemain dan memulai ulang permainan.
- Setiap kali ActionEvent terjadi, method actionPerformed() akan dipanggil. Di dalamnya, jika permainan belum berakhir, move() akan dipanggil untuk menggerakkan elemen-elemen permainan.
- FlappyBird juga menggunakan kelas Pipe dan Player untuk merepresentasikan pipa-pipa dan pemain dalam permainan.
3. Pipe.java:
- Kelas Pipe merepresentasikan pipa dalam permainan. Setiap pipa memiliki atribut posisi, ukuran, gambar, dan kecepatan horizontal.
- Method setPassed() digunakan untuk menandai apakah pemain sudah berhasil melewati pipa tersebut.
4. Player.java:
- Kelas Player merepresentasikan pemain dalam permainan. Pemain memiliki atribut posisi, ukuran, gambar, dan kecepatan vertikal.