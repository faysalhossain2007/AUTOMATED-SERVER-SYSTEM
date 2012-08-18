package buet.threebyzero.autoSecuritySystem.server;


import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class FileReceive {
	
	public String hostname = "127.0.0.1";
	public int port = 5554;
	public int processedByte;
	public byte[] theByte = new byte[1];

	public Socket client;
	public InputStream inputSt;

	public FileReceive()

	{

		try {
			client = new Socket(hostname,port);
			inputSt = client.getInputStream();
			System.out.print("hello");
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		ByteArrayOutputStream arrayOutput = new ByteArrayOutputStream();

		if (inputSt != null) {

			FileOutputStream fileOutput;
			BufferedOutputStream bufferedOutput = null;
			try {
						
				String fileName = "hello.txt";
				//String fileName="photo1.JPG";

				fileOutput = new FileOutputStream(fileName);

				bufferedOutput = new BufferedOutputStream(fileOutput);
				processedByte = inputSt.read(theByte, 0, theByte.length);

				do {
					arrayOutput.write(theByte);

					processedByte = inputSt.read(theByte);
				} while (processedByte != -1);

				bufferedOutput.write(arrayOutput.toByteArray());
				bufferedOutput.flush();
				bufferedOutput.close();
				System.out.print("finish");

			} catch (IOException ex) {

			}
			try {
				client.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}