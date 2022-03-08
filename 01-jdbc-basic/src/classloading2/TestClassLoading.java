package classloading2;

public class TestClassLoading {
	public static void main(String[] args) {
		try {
			Class.forName("classloading.MyDriver");
			/*
			 * static 영역이 meta space에 적재
			 */
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
