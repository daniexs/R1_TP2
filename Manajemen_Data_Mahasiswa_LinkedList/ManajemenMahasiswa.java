package Manajemen_Data_Mahasiswa_LinkedList;

// Class Node untuk merepresentasikan setiap node dalam Linked List
class Node {
  String nim;
  String nama;
  double nilai;
  Node next; // Pointer ke node berikutnya

  // Constructor
  public Node(String nim, String nama, double nilai) {
    this.nim = nim;
    this.nama = nama;
    this.nilai = nilai;
    this.next = null;
  }
}

// Class Mahasiswa untuk menyimpan data mahasiswa
class Mahasiswa {
  private Node head; // Head dari Linked List

  public Mahasiswa() {
    head = null;
  }

  // Method untuk menambah mahasiswa baru di akhir Linked List
  public void tambahMahasiswa(String nim, String nama, double nilai) {
    Node newNode = new Node(nim, nama, nilai);

    if (head == null) {
      // Jika Linked List kosong, jadikan node baru sebagai head
      head = newNode;
    } else {
      // Traverse ke node terakhir
      Node current = head;
      while (current.next != null) {
        current = current.next;
      }
      // Tambahkan node baru di akhir
      current.next = newNode;
    }
    System.out.println("Mahasiswa " + nama + " berhasil ditambahkan!");
  }

  // Method untuk mencari mahasiswa berdasarkan NIM
  private Node cariMahasiswa(String nim) {
    Node current = head;
    while (current != null) {
      if (current.nim.equals(nim)) {
        return current;
      }
      current = current.next;
    }
    return null; // Mahasiswa tidak ditemukan
  }

  // Method untuk mengupdate nilai mahasiswa berdasarkan NIM
  public void updateNilai(String nim, double nilaiBaru) {
    Node mahasiswa = cariMahasiswa(nim);
    if (mahasiswa != null) {
      mahasiswa.nilai = nilaiBaru;
      System.out.println("Nilai " + mahasiswa.nama + " berhasil diupdate menjadi " + nilaiBaru);
    } else {
      System.out.println("Mahasiswa dengan NIM " + nim + " tidak ditemukan!");
    }
  }

  // Method untuk menghapus mahasiswa berdasarkan NIM
  public void hapusMahasiswa(String nim) {
    if (head == null) {
      System.out.println("Daftar mahasiswa kosong!");
      return;
    }

    // Jika node yang akan dihapus adalah head
    if (head.nim.equals(nim)) {
      head = head.next;
      System.out.println("Mahasiswa dengan NIM " + nim + " berhasil dihapus!");
      return;
    }

    // Cari node sebelum node yang akan dihapus
    Node current = head;
    while (current.next != null && !current.next.nim.equals(nim)) {
      current = current.next;
    }

    // Jika ditemukan, hapus node tersebut
    if (current.next != null) {
      current.next = current.next.next;
      System.out.println("Mahasiswa dengan NIM " + nim + " berhasil dihapus!");
    } else {
      System.out.println("Mahasiswa dengan NIM " + nim + " tidak ditemukan!");
    }
  }

  // Method untuk menampilkan daftar semua mahasiswa
  public void tampilkanDaftar() {
    if (head == null) {
      System.out.println("Daftar mahasiswa kosong!");
      return;
    }

    System.out.println("\nDaftar Mahasiswa:");
    Node current = head;
    int nomor = 1;
    while (current != null) {
      System.out.println(nomor + ". NIM: " + current.nim +
          ", Nama: " + current.nama +
          ", Nilai: " + current.nilai);
      current = current.next;
      nomor++;
    }
    System.out.println();
  }
}

// Class Main untuk testing implementasi
public class ManajemenMahasiswa {
  public static void main(String[] args) {
    Mahasiswa daftarMahasiswa = new Mahasiswa();

    // Tambah mahasiswa pertama
    daftarMahasiswa.tambahMahasiswa("20123456", "Sari", 88);

    // Tambah mahasiswa kedua
    daftarMahasiswa.tambahMahasiswa("20176543", "Doni", 92);

    // Tampilkan daftar awal
    daftarMahasiswa.tampilkanDaftar();

    // Update nilai Doni
    System.out.println("Mengupdate nilai mahasiswa (Doni -> 97)");
    daftarMahasiswa.updateNilai("20176543", 97);

    // Tampilkan daftar setelah update
    daftarMahasiswa.tampilkanDaftar();

    // Test hapus mahasiswa
    System.out.println("Menghapus mahasiswa Sari...");
    daftarMahasiswa.hapusMahasiswa("20123456");
    daftarMahasiswa.tampilkanDaftar();
  }
}