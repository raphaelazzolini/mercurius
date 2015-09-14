package br.unicamp.ic.lsd.mercurius.loggingmgr.test;

public class LoggingTest {

	public static String logginTest1() {
		try {
			String testString = null;
			testString = testString.concat(testString);
			return testString;
		} catch (Exception e) {
			return null;
		}
	}

	private static String logginTest2() {
		try {
			String testString = "testString";
			testString = testString.substring(100);
			return testString;
		} catch (Exception e) {
			return null;
		}
	}

	public static void logginTest3() {

	}

	private static void logginTest4() {

	}

	public static void main(String[] args) {
		logginTest1();
		logginTest2();
		logginTest3();
		logginTest4();
	}

}
