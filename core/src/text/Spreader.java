package text;

import MainFrame.game.Console;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

public class Spreader {

    FileHandle handle;
    String text;
    String filename;
    // Construct BufferedReader from InputStreamReader
    BufferedReader br;



    public Spreader(String fileName) {

        this.handle = Gdx.files.internal("scripts/twoday_test.txt");
        this.filename = ("scripts/twoday_test.txt");
        this.text = handle.readString();
        br = new BufferedReader(new StringReader(text));
    }

    public Expando CreateExpando(String script_name) {
        String line;

        ArrayList<String> list = new ArrayList<>();

        try {
            // Read the file and display it line by line.

            while ((line = br.readLine()) != null){
                if (line.isEmpty())
                    continue;
                if (line.startsWith("#"))
                    continue;

                list.add(line);
            }

            br.close();

            String[] array = list.toArray(new String[list.size()]);
            return new Expando(script_name, array);

        } catch (FileNotFoundException e) {
            Console.WriteLine("testing");
            return null;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
