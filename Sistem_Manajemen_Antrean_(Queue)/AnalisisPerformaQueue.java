import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Locale;

public class AnalisisPerformaQueue {

  private static final int N_DEFAULT = 5_000;
  private static final int WARMUP = 2;

  public static void main(String[] args) {
    int n = parseN(args);

    System.out.println("=== Uji integrasi (Queue) ===");
    ujiRingan();
    System.out.println("OK.\n");

    System.out.println("=== Performa: N enqueue lalu N dequeue ===");
    System.out.println("N = " + n + "\n");
    for (int i = 0; i < WARMUP; i++) {
      ukurEnqueueDequeue(n);
    }
    long ns = ukurEnqueueDequeue(n);
    cetakBaris("N enqueue + N dequeue", ns, n * 2);
    System.out.println("\nCatatan: enqueue/dequeue O(1) per langkah untuk implementasi ini.");
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
    Queue q = new Queue();
    q.enqueue("A");
    q.enqueue("B");
    if (!"A".equals(q.dequeue())) {
      throw new IllegalStateException("FIFO gagal pada elemen pertama.");
    }
    if (!"B".equals(q.dequeue())) {
      throw new IllegalStateException("FIFO gagal pada elemen kedua.");
    }
    if (!"".equals(q.dequeue())) {
      throw new IllegalStateException("Dequeue kosong harus mengembalikan \"\".");
    }
  }

  private static long ukurEnqueueDequeue(int n) {
    PrintStream asli = System.out;
    System.setOut(new PrintStream(new ByteArrayOutputStream(), true));
    try {
      long t0 = System.nanoTime();
      Queue q = new Queue();
      for (int i = 0; i < n; i++) {
        q.enqueue("P" + i);
      }
      for (int i = 0; i < n; i++) {
        q.dequeue();
      }
      return System.nanoTime() - t0;
    } finally {
      System.setOut(asli);
    }
  }

  private static void cetakBaris(String label, long totalNs, int opCount) {
    double ms = totalNs / 1_000_000.0;
    double nsPerOp = (double) totalNs / opCount;
    System.out.printf(Locale.US, "%-36s %10.3f ms | %.1f ns/op%n", label, ms, nsPerOp);
  }
}
