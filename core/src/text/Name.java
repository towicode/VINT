package text;

import com.badlogic.gdx.graphics.Color;

public enum Name {


    TODD,
    MAS,
    CHA,
    RIC,
    EVA,
    ERI,
    ZAC,
    ABI,
    MAR,
    ALE,
    HAY,
    NAT,
    JAD,
    TEA,
    EMI,
    TRA,
    TIM,
    WHO,
    MIA,
    TESTING;



    public String full(){
        switch (this){
            case MAS:
                return "Mason";
            case JAD:
                return "Jade";
            case RIC:
                return "Richard";
            case EVA:
                return "Evans";
            case ERI:
                return "Erik";
            case ZAC:
                return "Zach";
            case ABI:
                return "Abigail";
            case MAR:
                return "Mari";
            case ALE:
                return "Alex";
            case HAY:
                return "Hayley";
            case NAT:
                return "Natasha";
            case TEA:
                return "Teacher";
            case EMI:
                return "Emily";
            case WHO:
                return "???";
            case TRA:
                return "Travis";
            case TIM:
                return "Tim";
            case MIA:
                return "TODD";
            case TODD:
                return "TODD";
            case TESTING:
                return "TESTING";
        }

        return "MissingNo";

    }

    public Color color() {

        switch (this){
            case MAS:
                return new Color(0f, 0.729f, 0.008f,1f);
            case JAD:
                return new Color(0f, 0.729f, 0.008f,1f);
            case RIC:
                return new Color(0f, 0.729f, 0.008f,1f);
            case EVA:
                return new Color(0f, 0.729f, 0.008f,1f);
            case ERI:
                return new Color(0f, 0.729f, 0.008f,1f);
            case ZAC:
                return new Color(0f, 0.729f, 0.008f,1f);
            case ABI:
                return new Color(0f, 0.729f, 0.008f,1f);
            case MAR:
                return new Color(0.78f, 0.251f, 0.49f,1f);
            case ALE:
                return new Color(0f, 0.729f, 0.008f,1f);
            case HAY:
                return new Color(0f, 0.729f, 0.008f,1f);
            case NAT:
                return new Color(0f, 0.729f, 0.008f,1f);
            case TEA:
                return new Color(0f, 0.729f, 0.008f,1f);
            case MIA:
                return new Color(0f, 0.729f, 0.008f,1f);
            case EMI:
                return new Color(0f, 0.729f, 0.008f,1f);
            case WHO:
                return new Color(0f, 0.729f, 0.008f,1f);
            case TRA:
                return new Color(0f, 0.729f, 0.008f,1f);
            case TIM:
                return new Color(0f, 0.729f, 0.008f,1f);
            case TESTING:
                return new Color(0f, 1f, 0.008f,1f);
        }

        return new Color(0f, 0.729f, 0.008f,1f);
    }
}
