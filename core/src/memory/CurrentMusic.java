package memory;


import VINT.Model.Song;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

/**
 * Created by Todd on 2/26/2016.
 */
public class CurrentMusic {

    public String music_list = "";

    private ArrayList<Song> songs;

    private static CurrentMusic instance = null;

    private CurrentMusic(){
        Arrays.stream(Gdx.files.internal("music").list()).forEach(s -> music_list+= " " + s.nameWithoutExtension() + " ");
    }

    public static CurrentMusic getInstance() {
        if (instance == null) {
            instance = new CurrentMusic();
            instance.songs = new ArrayList<>();
        }
        return instance;
    }


    public void createOrModify(String music_name, ArrayList<String> params) {

        Optional<Song> x = songs.stream().filter(s -> s.getName().equals(music_name)).findAny();
        if (x.isPresent()){
            songs.forEach(s -> s.stop());
            applyEffects(x.get(), params);
        } else {
            if (music_name == null)
                return;
            songs.forEach(Song::stop);
            Music y = Gdx.audio.newMusic(Gdx.files.internal("music/" + music_name + ".ogg"));
            Music y2 = Gdx.audio.newMusic(Gdx.files.internal("music/" + music_name + ".ogg"));
            Song z = new Song(y,y2,music_name);
            songs.add(z);
            applyEffects(z,params);
         }
    }

    private void applyEffects(Song song, ArrayList<String> params) {


        // By Default we just loop the song over and over.
        if (params.size() == 0){
            //preload the music 2 at the repeat position
            song.getMusic2().play();
            song.getMusic2().setPosition(song.getPosition());
            song.getMusic2().pause();

            //start playing music 1
            song.getMusic().play();

            song.getMusic2().setOnCompletionListener(d ->{
                song.getMusic().play();
                song.getMusic2().play();
                song.getMusic2().setPosition(song.getPosition());
                song.getMusic2().pause();

            });

            song.getMusic().setOnCompletionListener(d -> {
                song.getMusic2().play();

                song.getMusic().play();
                song.getMusic().setPosition(song.getPosition());
                song.getMusic().pause();
            });
        }
        if (params.stream().anyMatch(s -> s.contains("stop"))){
            song.getMusic().stop();
            song.getMusic2().stop();
        }
    }
}
