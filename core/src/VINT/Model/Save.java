package VINT.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

import java.io.*;
import java.util.Base64;
import java.util.Date;

public class Save implements Serializable{

    private final long serialVersionUID = 1;

    public Save(String scriptName, int lineNumber) {
        ScriptName = scriptName;
        this.lineNumber = lineNumber;
        this.date = new Date();
    }

    public String ScriptName;
	public int lineNumber;
    public Date date;
    public int gameID = 1;

    public String toString() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream( baos );
            oos.writeObject(this);
            oos.close();
            return Base64.getEncoder().encodeToString(baos.toByteArray());

        } catch (IOException e) {

            e.printStackTrace();
            return "";
        }

    }

    public static Save fromString( String s ){
        byte [] data = Base64.getDecoder().decode( s );
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(
                    new ByteArrayInputStream(  data ) );
            Object o  = ois.readObject();
            ois.close();
            return (Save)o;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();

        }

        return null;

    }

    public void save(){

        FileHandle handle = Gdx.files.local("saveFile.txt");
        handle.writeString(this.toString(), false);
    }

}
