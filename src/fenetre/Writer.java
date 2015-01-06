package fenetre;

import java.io.IOException;
import java.io.OutputStream;

import javafx.scene.control.TextArea;

public class Writer extends OutputStream{
	
 private final TextArea destination;

    public Writer (TextArea destination)
    {
        this.destination = destination;
    }

    @Override
    public void write(byte[] buffer, int offset, int length) throws IOException
    {
        final String text = new String (buffer, offset, length);
        destination.appendText(text);
    }

    @Override
    public void write(int b) throws IOException
    {
        write (new byte [] {(byte)b}, 0, 1);
    }
	    
}
