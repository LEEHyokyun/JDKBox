package classloading;

public class MyDriver {
	private static String dbName = "MYDATABASE";
	static {
		System.out.println(dbName+"연동을 위한 사전 작업");
	}
}
