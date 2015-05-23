import java.util.Scanner;
import java.util.ArrayList;

public class AudioClipTester	{
	public static void main(String... pumpkins)	{
		demo(pumpkins[0]);
		// theNightHasCome(pumpkins);
	}

	public static void demo(String soundLocation)	{	// demos using sound
		Scanner keyboard = new Scanner(System.in);
		AudioClip testclip = new AudioClip(soundLocation);
		System.out.println();
		System.out.println("new AudioClip(\"" + soundLocation + "\")");
		
		System.out.println();
		System.out.println("length " + testclip.length);
		System.out.println("lengthSeconds() " + testclip.lengthSeconds());
		System.out.println();

		System.out.print("play() "); keyboard.nextLine(); System.out.println();
		testclip.play();

		System.out.println("isRunning() " + testclip.isRunning());
		System.out.println("getPosition() " + testclip.getPosition());
		System.out.println();

		System.out.print("pause() "); keyboard.nextLine(); System.out.println();
		testclip.pause();

		System.out.println("isRunning() " + testclip.isRunning());
		System.out.println("getPosition() " + testclip.getPosition());
		System.out.println();

		System.out.print("play() "); keyboard.nextLine(); System.out.println();
		testclip.play();

		System.out.println("isRunning() " + testclip.isRunning());
		System.out.println("getPosition() " + testclip.getPosition());
		System.out.println();

		System.out.print("stop() "); keyboard.nextLine(); System.out.println();
		testclip.stop();

		System.out.println("isRunning() " + testclip.isRunning());
		System.out.println("getPosition() " + testclip.getPosition());
		System.out.println();

		System.out.print("play(double position) "); testclip.play(keyboard.nextLong()); System.out.println();
		keyboard.nextLine();

		System.out.println("getVolume() " + testclip.getVolume());
		System.out.println("getRate() " + testclip.getRate());
		System.out.println();

		System.out.print("setVolume(double volume) "); testclip.setVolume(keyboard.nextDouble());
		System.out.print("setRate(double rate) "); testclip.setRate(keyboard.nextDouble()); System.out.println();
		keyboard.nextLine();

		System.out.println("length " + testclip.length);
		System.out.println("length() " + testclip.length());
		System.out.println();

		System.out.print("stop() "); keyboard.nextLine(); System.out.println();
		testclip.stop();

		System.out.println("getStart() " + testclip.getStart());
		System.out.println("getStop() " + testclip.getStop());
		System.out.println();

		System.out.print("setStart(double time) "); testclip.setStart(keyboard.nextDouble());
		System.out.print("setStop(double time) "); testclip.setStop(keyboard.nextDouble()); System.out.println();
		keyboard.nextLine();

		System.out.println("getStart() " + testclip.getStart());
		System.out.println("getStop() " + testclip.getStop());
		System.out.println("length() " + testclip.length());
		System.out.println();

		System.out.print("play() "); keyboard.nextLine(); System.out.println();
		testclip.play();

		System.out.println("getPosition() " + testclip.getPosition());
		System.out.println();

		System.out.print("stop() "); keyboard.nextLine(); System.out.println();
		testclip.stop();

		System.out.print("setLoop(true) "); keyboard.nextLine(); System.out.println("play()\n");
		testclip.setLoop(true);
		testclip.play();

		System.out.print("setLoop(false) "); keyboard.nextLine(); System.out.println();
		testclip.setLoop(false);

		System.out.print("setCycleCount(int count) "); testclip.setCycleCount(keyboard.nextInt()); System.out.println("play()\n");
		testclip.play();
		keyboard.nextLine();

		System.out.println("getCycleCount() " + testclip.getCycleCount());
		System.out.println();

		System.out.print("getCycleCount() "); keyboard.nextLine(); System.out.println("getCycleCount() " + testclip.getCycleCount());
		System.out.println();

		System.out.print("isRunning() "); keyboard.nextLine();
		System.out.println(testclip.isRunning());
	}

	public static void theNightHasCome(String... pumpkins)	{
		ArrayList<AudioClip> thenightwillcome = new ArrayList<AudioClip>();
		for (double i = 0.5; i <= 2; i+= 0.5)	{
			thenightwillcome.add(new AudioClip(pumpkins[0], 0, 5000));
			thenightwillcome.get(thenightwillcome.size() - 1).setLoop(true);
			thenightwillcome.get(thenightwillcome.size() - 1).setRate(i);
			thenightwillcome.get(thenightwillcome.size() - 1).play();
		}
		new Scanner(System.in).nextLine();
		for (AudioClip clip: thenightwillcome)	{
			// clip.stop();
			clip.setVolume(0.25);
		}
		AudioClip clip = new AudioClip("Ricky.mp3");
		clip.setLoop(true);
		clip.play();
		new Scanner(System.in).nextLine();
	}
}