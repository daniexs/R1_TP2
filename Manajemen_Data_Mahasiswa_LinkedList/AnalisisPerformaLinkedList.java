package Manajemen_Data_Mahasiswa_LinkedList;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Locale;

public class AnalisisPerformaLinkedList {

  private static final int N_DEFAULT = 5_000;
  private static final int WARMUP = 2;

  public static void main(String[] args) {
    int n = parseN(args);

    System.out.println("=== Uji integrasi (linked list mahasiswa) ===");
    ujiRingan();
    System.out.println("OK.\n");

    System.out.println("=== Performa: N tambah di akhir list ===");
    System.out.println("N = " + n + "\n");
    for (int i = 0; i < WARMUP; i++) {
      ukurTambahAkhir(n);
    }
    long ns = ukurTambahAkhir(n);
    cetakBaris("N kali tambahMahasiswa (traverse ke tail)", ns, n);
    System.out.println(
        "\nCatatan: tanpa pointer tail, setiap insert O(panjang list); total mendekati O(N^2).");
  }

  private static int parseN(String[] args) {
    if (args.length == 0) {
      return N_DEFAULT;
    }
    try {
      int v = Integer.parseInt(args[0]);
      return v < 1 ? N_DEFAULT : v;
    } catch (NumberFormatException e) {
      return N_DEFAULT;
    }
  }

  private static void ujiRingan() {
    Mahasiswa m = new Mahasiswa();
    m.tambahMahasiswa("1001", "Budi", 85);
    m.updateNilai("1001", 90);
    m.hapusMahasiswa("1001");
    m.tampilkanDaftar();
  }

  private static long ukurTambahAkhir(int n) {
    PrintStream asli = System.out;
    System.setOut(new PrintStream(new ByteArrayOutputStream(), true));
    try {
      long t0 = System.nanoTime();
      Mahasiswa daftar = new Mahasiswa();
      for (int i = 0; i < n; i++) {
        daftar.tambahMahasiswa(String.valueOf(1_000_000 + i), "Mhs" + i, 80.0);
      }
      return System.nanoTime() - t0;
    } finally {
      System.setOut(asli);
    }
  }

  private static void cetakBaris(String label, long totalNs, int n) {
    double ms = totalNs / 1_000_000.0;
    double nsPerOp = (double) totalNs / n;
    System.out.printf(Locale.US, "%-36s %10.3f ms | %.1f ns/op%n", label, ms, nsPerOp);
  }
}
