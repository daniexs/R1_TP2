import java.util.Scanner; // Library untuk memproses input

class Node { // Object untuk menyimpan linked list
    String data;
    Node next;

    Node(String data) {
        this.data = data;
        this.next = null;
    }
}

class Queue { // Object untuk memproses linked list sebagai queue atau antrean
    Node front, rear;

    Queue() {
        front = rear = null;
    }

    void enqueue(String data) { // Menambahkan data di tail linked list
        Node newNode = new Node(data);
        if (rear == null) {
            front = rear = newNode;
            return;
        }
        rear.next = newNode;
        rear = newNode;
    }

    String dequeue() { // Menghapus head linked list
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

    void display() { // Proses display linked list
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
        while (start) { // Start Boilerplate
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
                    q.enqueue(nama); // Tambah data pelanggan by input
                    System.out.println("Pelanggan berhasil ditambahkan ke antrean");
                    break;
                case "2":
                    q.dequeue(); // Run dequeue untuk hapus head linked list
                    break;
                case "3":
                    q.display(); // Run display untuk menampilkan data pelanggan
                    break;
                case "0":
                    // Proses exit program
                    System.out.println("Keluar dari program...");
                    start = false;
                    break;
                default:
                    System.out.println("Opsi tidak valid.");
            }
        }
    }
}