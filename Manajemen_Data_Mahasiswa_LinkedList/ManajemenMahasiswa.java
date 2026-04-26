package Manajemen_Data_Mahasiswa_LinkedList;

// Class Node untuk merepresentasikan setiap node dalam Linked List
class Node {
  String nim;
  String nama;
  double nilai;
  Node next;

  public Node(String nim, String nama, double nilai) {
    this.nim = nim;
    this.nama = nama;
    this.nilai = nilai;
    this.next = null;
  }
}

// Class Mahasiswa untuk menyimpan data mahasiswa
class Mahasiswa {
  private Node head;

  public Mahasiswa() {
    head = null;
  }

  public void tambahMahasiswa(String nim, String nama, double nilai) {
    Node newNode = new Node(nim, nama, nilai);

    if (head == null) {
      head = newNode;
    } else {
      Node current = head;
      while (current.next != null) {
        current = current.next;
      }
      current.next = newNode;
    }
    System.out.println("Mahasiswa " + nama + " berhasil ditambahkan!");
  }

  private Node cariMahasiswa(String nim) {
    Node current = head;
    while (current != null) {
      if (current.nim.equals(nim)) {
        return current;
      }
      current = current.next;
    }
    return null;
  }

  public void updateNilai(String nim, double nilaiBaru) {
    Node mahasiswa = cariMahasiswa(nim);
    if (mahasiswa != null) {
      mahasiswa.nilai = nilaiBaru;
      System.out.println("Nilai " + mahasiswa.nama + " berhasil diupdate menjadi " + nilaiBaru);
    } else {
      System.out.println("Mahasiswa dengan NIM " + nim + " tidak ditemukan!");
    }
  }

  public void hapusMahasiswa(String nim) {
    if (head == null) {
      System.out.println("Daftar mahasiswa kosong!");
      return;
    }

    if (head.nim.equals(nim)) {
      head = head.next;
      System.out.println("Mahasiswa dengan NIM " + nim + " berhasil dihapus!");
      return;
    }

    Node current = head;
    while (current.next != null && !current.next.nim.equals(nim)) {
      current = current.next;
    }

    if (current.next != null) {
      current.next = current.next.next;
      System.out.println("Mahasiswa dengan NIM " + nim + " berhasil dihapus!");
    } else {
      System.out.println("Mahasiswa dengan NIM " + nim + " tidak ditemukan!");
    }
  }

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

    daftarMahasiswa.tambahMahasiswa("20123456", "Sari", 88);
    daftarMahasiswa.tambahMahasiswa("20176543", "Doni", 92);
    daftarMahasiswa.tampilkanDaftar();

    System.out.println("Mengupdate nilai mahasiswa (Doni -> 97)");
    daftarMahasiswa.updateNilai("20176543", 97);
    daftarMahasiswa.tampilkanDaftar();

    System.out.println("Menghapus mahasiswa Sari...");
    daftarMahasiswa.hapusMahasiswa("20123456");
    daftarMahasiswa.tampilkanDaftar();
  }
}
