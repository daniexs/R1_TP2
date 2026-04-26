import java.util.Scanner;

class Node {
  String data;
  Node next;

  Node(String data) {
    this.data = data;
    this.next = null;
  }
}

class Queue {
  Node front, rear;

  Queue() {
    front = rear = null;
  }

  void enqueue(String data) {
    Node newNode = new Node(data);
    if (rear == null) {
      front = rear = newNode;
      return;
    }
    rear.next = newNode;
    rear = newNode;
  }

  String dequeue() {
    if (front == null) {
      System.out.println("Antrean kosong");
      return "";
    }
    String data = front.data;
    front = front.next;
    System.out.println("Pelanggan yang diproses: " + data);
    if (front == null) {
      rear = null;
    }
    return data;
  }

  void display() {
    Node temp = front;
    int urutan = 0;
    System.out.println("Pelanggan dalam antrean:");
    while (temp != null) {
      urutan++;
      System.out.printf("%d. %s\n", urutan, temp.data);
      temp = temp.next;
    }
  }
}

public class Main {
  private static final Scanner scanner = new Scanner(System.in);

  public static void main(String[] args) {
    boolean start = true;
    Queue q = new Queue();
    while (start) {
      System.out.println("\n=== List Menu Antrian ===");
      System.out.println("1. Tambah Pelanggan");
      System.out.println("2. Proses Pelanggan");
      System.out.println("3. Tampilkan Pelanggan");
      System.out.println("0. Keluar");

      System.out.print("Pilih menu: ");
      String option = scanner.nextLine();
      switch (option) {
        case "1":
          System.out.print("Input nama: ");
          String nama = scanner.nextLine();
          q.enqueue(nama);
          System.out.println("Pelanggan berhasil ditambahkan ke antrean");
          break;
        case "2":
          q.dequeue();
          break;
        case "3":
          q.display();
          break;
        case "0":
          System.out.println("Keluar dari program...");
          start = false;
          break;
        default:
          System.out.println("Opsi tidak valid.");
      }
    }
  }
}
