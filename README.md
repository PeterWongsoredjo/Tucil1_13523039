# Tugas Kecil 1 IF2211 Strategi Algoritma
## Penyelesaian IQ Puzzler Pro dengan Algoritma Brute Force

![Java Version](https://img.shields.io/badge/Java-8%2B-blue)



## 📌 Penjelasan Singkat
Program ini merupakan implementasi **algoritma brute force** untuk menyelesaikan permainan **IQ Puzzler Pro**. Program mencoba **semua kemungkinan peletakan potongan puzzle**, termasuk **rotasi dan pencerminan**, untuk menemukan solusi yang valid.

Selain papan standar, program ini juga mendukung **papan kustom**, yang ditandai dengan **rintangan (`#`)**.

---

## ⚙ Requirement & Instalasi
Program ini menggunakan **Java 8 atau lebih baru**.  
Pastikan **Java Development Kit (JDK)** telah terpasang di sistem Anda.  

### **🔹 Cek Java di Sistem**
Jalankan perintah berikut di terminal atau command prompt:
```sh
java -version
```

## 🎥 Cara Mengkompilasi Program
### 🔹 1. Susunan Folder
Pastikan proyek memiliki struktur berikut:
```
/IQPuzzlerPro/
│── /bin/           # Folder untuk file hasil kompilasi
│── /src/           # Folder berisi file sumber Java (.java)
│   │── Main.java
│   │── Board.java
│   │── Piece.java
│   │── FileScanner.java
│   │── BruteForceSolver.java
│── testcase.txt    # File input (bisa disesuaikan)
│── README.md       
```
Input file berada di luar folder yang ada, sejajar dengan README.md
### 🔹 2. Kompilasi Semua File Java
Buka terminal atau command prompt, lalu jalankan:

```
javac -d bin -sourcepath src src/*.java
```
✅ Semua file .java akan dikompilasi dan hasilnya disimpan di dalam folder /bin/.

## ▶ Cara Menjalankan Program
Setelah dikompilasi, jalankan program dengan perintah:

```
java -cp bin Main
```
Program akan meminta nama file input:
```
Nama File:
```
Masukkan nama file yang berisi papan permainan, misalnya:

```
testcase.txt
```
Program kemudian akan memproses dan menampilkan solusi.

## ▶ Contoh Penggunaan
### 🔹 1. Menjalankan Program
```
$ java -cp bin Main
Nama File :
testcase.txt
```
### 🔹 2. Contoh Input (testcase.txt)
```
4 6 7
DEFAULT
A
AA
 B
BBB
 BB
C
D
DD
EEEE
FF
FF
 F
GG
```
### 🔹 3. Output Program (Jika Solusi Ditemukan)
```
Solusi ditemukan!
A C B B D D 
A A B B B D 
F F F B G G 
F F E E E E 
Waktu Pencarian: 1 ms
Total kasus yang ditinjau: 26
Solusi ditemukan! Apakah Anda ingin menyimpan solusi? (y/n)
```
Jika ingin menyimpan solusi dalam bentuk .txt ketik "y", program kemudian akan menyimpan ke dalam file solution.txt.

## 📌 Author
👤 Peter Wongsoredjo

📧 Email: peterwongsoredjo@gmail.com

🔗 GitHub: github.com/PeterWongsoredjo

📌 Institut Teknologi Bandung
