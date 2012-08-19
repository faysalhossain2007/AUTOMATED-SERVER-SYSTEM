package buet.threebyzero.autoSecuritySystem.server;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileReceive {

	public String hostname = "127.0.0.1";
	public int port = 5554;
	public int processedByte;
	public byte[] theByte = new byte[1];

	public Socket client;
	public InputStream inputSt;
	private String timeForPic;
	private String imageFileName;
	private String dateForPic;
	private String fileName;

	public FileReceive()

	{
		try {
			client = new Socket(hostname, port);
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

				dateForPic = new SimpleDateFormat("yyyyMMdd")
						.format(new Date());
				timeForPic = new SimpleDateFormat("HHmmss").format(new Date());

				fileName = dateForPic + '_' + timeForPic + ".JPG";
				/*
				 * we will save image in camera like this format so we won't
				 * need to send the file name because at the same time when
				 * camera will call this class it will generate the name for the
				 * image which is created at the camera in the same name
				 */

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

			} catch (IOException ex) {

			}
			try {
				client.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				File fToDB = new File(fileName);
				SaveImageToDatabase sitd = new SaveImageToDatabase(fToDB,
						dateForPic, timeForPic);
			}
		}

	}

}