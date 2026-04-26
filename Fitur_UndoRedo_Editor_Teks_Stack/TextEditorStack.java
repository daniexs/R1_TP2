package Fitur_UndoRedo_Editor_Teks_Stack;

import java.util.Stack;

public class TextEditorStack {
  private Stack<String> undoStack;
  private Stack<String> redoStack;
  private String currentText;

  public TextEditorStack() {
    undoStack = new Stack<>();
    redoStack = new Stack<>();
    currentText = "";
  }

  // Tambah teks baru
  public void addText(String text) {
    undoStack.push(currentText); // simpan kondisi sebelumnya
    currentText += text;
    redoStack.clear(); // reset redo
    System.out.println("Teks saat ini: " + currentText);
  }

  // Undo
  public void undo() {
    if (!undoStack.isEmpty()) {
      redoStack.push(currentText);
      currentText = undoStack.pop();
      System.out.println("Undo: " + currentText);
    } else {
      System.out.println("Tidak bisa undo!");
    }
  }

  // Redo
  public void redo() {
    if (!redoStack.isEmpty()) {
      undoStack.push(currentText);
      currentText = redoStack.pop();
      System.out.println("Redo: " + currentText);
    } else {
      System.out.println("Tidak bisa redo!");
    }
  }

  // Tampilkan teks
  public void showText() {
    System.out.println("Teks saat ini: " + currentText);
  }
}