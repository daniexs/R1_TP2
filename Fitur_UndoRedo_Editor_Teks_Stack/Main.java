package Fitur_UndoRedo_Editor_Teks_Stack;

import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    TextEditorStack editor = new TextEditorStack();
    int pilih;
    do {
      System.out.println("\n=== Fitur Editor Teks ===");
      editor.showText(); // Tampilkan teks
      System.out.println("1. Tambah Teks");
      System.out.println("2. Undo");
      System.out.println("3. Redo");
      System.out.println("4. Keluar");
      System.out.print("\nPilih Menu: ");
      pilih = sc.nextInt();
      sc.nextLine();

      switch (pilih) {
        case 1:
          System.out.print("Masukkan teks: ");
          String teks = sc.nextLine();
          // Validasi jika input kosong
          if (teks == "") {
            System.out.println("Input tidak boleh kosong");
          } else {
            editor.addText(teks + " ");
          }
          break;

        case 2:
          editor.undo();
          break;

        case 3:
          editor.redo();
          break;
      }

    } while (pilih != 4);
  }
}