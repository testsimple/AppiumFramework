package libraries.utility;

import org.springframework.util.StopWatch;

public class Common extends Utilities {
	private static StopWatch watch;

	public static void wait(int seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void startTimeWatcher() {
		watch = new StopWatch();
		watch.start();
	}

	public static void stopTimeWatcher() {
		watch.stop();
	}

	public static void showElapsedTimeInMinutes() {
		Log.info(String.format("Total elapsed execution time: %f minutes.", watch.getTotalTimeSeconds() / 60));
	}

	public static final StringBuilder getClassName() {
		StringBuilder name = new StringBuilder(Thread.currentThread().getStackTrace()[2].getClassName());
		return name;
	}

	public static final StringBuilder getMethod() {
		StringBuilder name = new StringBuilder(Thread.currentThread().getStackTrace()[2].getMethodName());
		return name;
	}

}
