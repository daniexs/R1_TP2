package Fitur_UndoRedo_Editor_Teks_Stack;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Locale;

public class AnalisisPerformaStack {

  private static final int N_DEFAULT = 5_000;
  private static final int WARMUP = 2;

  public static void main(String[] args) {
    int n = parseN(args);

    System.out.println("=== Uji integrasi (Stack editor) ===");
    ujiRingan();
    System.out.println("OK.\n");

    System.out.println("=== Performa: N tambah teks ===");
    System.out.println("N = " + n + "\n");
    for (int i = 0; i < WARMUP; i++) {
      ukurTambahTeks(n);
    }
    long ns = ukurTambahTeks(n);
    cetakBaris("N kali addText(\"x \")", ns, n);
    System.out.println(
        "\nCatatan: biaya naik seiring panjang String karena konkatenasi berulang.");
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
    TextEditorStack e = new TextEditorStack();
    e.addText("a ");
    e.addText("b ");
    e.undo();
    e.redo();
    e.addText("c ");
    e.undo();
    e.undo();
  }

  private static long ukurTambahTeks(int n) {
    PrintStream asli = System.out;
    System.setOut(new PrintStream(new ByteArrayOutputStream(), true));
    try {
      long t0 = System.nanoTime();
      TextEditorStack editor = new TextEditorStack();
      for (int i = 0; i < n; i++) {
        editor.addText("x ");
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
