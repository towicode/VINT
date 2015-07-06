package text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.LinkedList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

public class Spreader {

  FileHandle handle;
  String text;
  String filename;

  // Construct BufferedReader from InputStreamReader
  BufferedReader br;

  public Spreader(String fileName) {

    this.handle = Gdx.files.internal("scripts/test.txt");
    this.filename = ("scripts/test.txt");
    this.text = handle.readString();
    br = new BufferedReader(new StringReader(text));
  }

  public Expando CreateExpando() throws IOException {

    LinkedList<Card> cards = new LinkedList<Card>();
    String line;
    int lineNum = 0;
    while ((line = br.readLine()) != null) {
      lineNum++;
      if (line.isEmpty())
        continue;

      if (line.contains("*"))
        continue;

      cards.add(new Card(line, lineNum, filename));
    }

    Expando expando = new Expando(filename, cards);

    return expando;

  }

}
