package text;


import VINT.Model.Save;
import VINT.game.Console;
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
    String scriptName;
    // Construct BufferedReader from InputStreamReader
    BufferedReader br;
    Save save;


    public Spreader(String loader, int o){
        Save loaded = Save.fromString(loader);
        if (loaded == null){
            Console.WriteLine("error loading save");
            return;
        }

        this.scriptName = loaded.ScriptName;

        this.handle = Gdx.files.internal("scripts/" + loaded.ScriptName +".txt");
        this.filename = ("scripts/" + loaded.ScriptName +".txt");
        this.text = handle.readString();
        br = new BufferedReader(new StringReader(text));
        this.save = loaded;
    }


    public Spreader(String fileName) {

        this.handle = Gdx.files.internal("scripts/twiday.txt");
        this.filename = ("scripts/twiday.txt");
        this.scriptName = fileName;
        this.text = handle.readString();
        br = new BufferedReader(new StringReader(text));
    }

    public Expando CreateExpando() {
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
            if (this.save != null){
                return new Expando(scriptName, array, save);
            }
            return new Expando(scriptName, array);

        } catch (FileNotFoundException e) {
            Console.WriteLine("testing");
            return null;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
