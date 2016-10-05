package VINT.Model;

import com.badlogic.gdx.audio.Music;

public class Song {


    //fuck it
    //pdf iso exe sheetmusic; ti-82 exclusive

    private Music music;
    private Music music2;
    private String name;
    private float position;
    private float volume = 1f;
    public Song(Music music, Music music2, String name) {
        this.music = music;
        this.music2 = music2;
        this.name = name;

        //TODO NO NO NO NO NO STOP IT

        switch (this.name){
            case "st_mainmenu":
                this.position = 40.000f;
                break;

            case "gb_mellow1":
                this.position = 00.000f;
                break;
            case "gb_relaxing":
                this.position = 33.600f;
                break;
            case "gb_whimsical_playful1":
                this.position = 33.600f;
                break;
            case "gb_ambient1":
                this.position = 00.000f;
                break;
            case "gb_mellow2":
                this.position = 14.769f;
                break;
            case "am_bus":
                this.position = 32.136f;
                break;
            case "am_crowd1":
                this.position = 16.417f;
                break;
            default:
                this.position = 0f;
                break;
        }
    }

    public Music getMusic2() {
        return music2;
    }

    public Music getMusic() {
        return music;
    }

    public void setMusic(Music music) {
        this.music = music;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPosition() {
        return position;
    }

    public void fadeOut() {

        new Thread() {
            public void run() {
                long time = System.currentTimeMillis();
                while (System.currentTimeMillis() < time + 3000) {
                    volume = (volume - volume * .00005f) /*% 255*/;
                    if (volume < 0)
                        volume = 0;
                    music.setVolume(volume);
                    music2.setVolume(volume);
                }
                music.setVolume(0f);
                music2.setVolume(0f);
            }
        }.start();

    }

    public void fadeIn() {
        //todo
    }

    public void stop() {
        music.stop();
        music2.stop();
    }
}
