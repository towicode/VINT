package VINT.Model;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.StringBuilder;

/**
 * Created by Todd on 3/29/2016.
 */
public class Font extends BitmapFont {


    public Font(FileHandle internal) {
        super(internal);
    }

    public Font() {

    }

    public float getWidth (CharSequence str) {
        return getWidth(str, 0, str.length());
    }


    /** Returns the size of the specified string. The height is the distance from the top of most capital letters in the font (the
     * {@link #getCapHeight() cap height}) to the baseline.
     * @param start The first character of the string.
     * @param end The last character of the string (exclusive). */
    public float getWidth (CharSequence str, int start, int end) {
        BitmapFontData data = this.getData();
        int width = 0;
        Glyph lastGlyph = null;
        while (start < end) {
            char ch = str.charAt(start++);

            lastGlyph = data.getGlyph(ch);
            if (lastGlyph != null) {
                width = lastGlyph.xadvance;
                break;
            }
        }
        while (start < end) {
            char ch = str.charAt(start++);

            Glyph g = data.getGlyph(ch);
            if (g != null) {
                width += lastGlyph.getKerning(ch);
                lastGlyph = g;
                width += g.xadvance;
            }
        }
        return width * data.scaleX;
    }


    /**
     * Returns the width of the next word, useful for typewritter when chosing where to wrap text.
     * @param index
     * @param text
     * @return
     */

    public float getWidthOfNextWord(int index, String text){

        StringBuilder x = new StringBuilder();
        char m;
        m = text.charAt(index);
        while (m != ' '){
            x.append(m);
            index++;
            if (index > text.length()-1)
                break;
            m = text.charAt(index);
        }
        return getWidth(x);

    }
}
