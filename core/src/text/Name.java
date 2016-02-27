package text;

public enum Name {


    TODD,
    TESTING,
    MAS,
    CHA,
    JAD;


    public String full(){
        switch (this){
            case MAS:
                return "Mason";
            case JAD:
                return "Jade";
        }

        return "MissingNo";

    }
}
