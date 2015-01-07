package fenetre;

import java.io.IOException;
import java.io.OutputStream;

import javafx.scene.control.TextArea;

/**
 * The Class Writer. Helps to write stream to the TextArea of the application
 */
public class Writer extends OutputStream {

	/** The destination. */
	private final TextArea destination;

	/**
	 * Instantiates a new writer.
	 *
	 * @param destination
	 *            the destination
	 */
	public Writer(TextArea destination) {
		this.destination = destination;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.io.OutputStream#write(byte[], int, int)
	 */
	@Override
	public void write(byte[] buffer, int offset, int length) throws IOException {
		final String text = new String(buffer, offset, length);
		destination.appendText(text);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.io.OutputStream#write(int)
	 */
	@Override
	public void write(int b) throws IOException {
		write(new byte[] { (byte) b }, 0, 1);
	}

}
