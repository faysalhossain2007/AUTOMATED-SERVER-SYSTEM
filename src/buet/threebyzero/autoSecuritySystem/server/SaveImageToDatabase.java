package buet.threebyzero.autoSecuritySystem.server;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

class SaveImageToDatabase {
	Connection connection = null;
	String connectionURL = "jdbc:mysql://localhost/picture";
	PreparedStatement psmnt = null;
	FileInputStream fis;

	

	public SaveImageToDatabase(File image, String dateTaken, String timeTaken) {
		// TODO Auto-generated constructor stub
		
		{
			try {
				Class.forName("com.mysql.jdbc.Driver").newInstance();
				connection = DriverManager.getConnection(connectionURL, "root",
						"");
				fis = new FileInputStream(image);
				Statement stmt = (Statement) connection.createStatement();
				String query = "CREATE TABLE IF NOT EXISTS save_image(ID int NOT NULL auto_increment, Date VARCHAR(30), Image BLOB(4294967295), Time VARCHAR(30), PRIMARY KEY (ID))";
				stmt.executeUpdate(query);
				psmnt = connection
						.prepareStatement("insert into save_image(Date, Image, Time) "
								+ "values(?, ?, ?)");

				psmnt.setString(1, dateTaken);
				psmnt.setBinaryStream(2, (InputStream) fis,
						(int) (image.length()));
				psmnt.setString(3, timeTaken);

				int ps = psmnt.executeUpdate();
				if (ps > 0) {
					System.out.println("Uploaded successfully !");
				}
			}

			catch (Exception ex) {
			} finally {

				try {
					connection.close();
					psmnt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}
	}
}
