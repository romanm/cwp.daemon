package cpw.daemon;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;

public class Driver {
	private static Logger log = Logger.getLogger("windowsservicelog");
	static String serverStartBat = "C:\\opt\\vait.curepathway\\bin\\startServer.bat";
	private static boolean stop = false;
	private static Process process = null;
	public static void start(String[] args) {
		log.info("start");
		if(null == process){
			try {
				process = Runtime.getRuntime().exec("cmd /c start " + serverStartBat);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if(null != process){
			int i = 0;
			InputStream is = process.getInputStream();
			try {
				while( (i = is.read() ) != -1) {
					System.out.print((char)i);
				}
			} catch(IOException ioException) {
				System.out.println(ioException.getMessage() );
			}
		}
	}

	public static void stop(String[] args) {
		log.info("stop");
		stop = true;
	}

	public static void main(String args[]) {
		String mode = args[0];
		switch (mode) {
		case "start":
			start(args);
			break;
		case "stop":
			stop(args);
			break;
		}
	}
}
