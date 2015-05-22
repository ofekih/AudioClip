import java.io.File;
import java.io.IOException;

import java.util.Scanner;

import java.net.URL;

import javafx.application.Application;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.swing.SwingUtilities;
import java.util.concurrent.CountDownLatch;
import javafx.embed.swing.JFXPanel;

/**
 * This class makes Clips easier and more intuitive to use
 *
 * @author Ofek Gila
 * @since  May 19th, 2015
 * @lastedited May 21st, 2015
 * @version 2.1
 */
public class AudioClip extends Application	{

	public final double length;

	private String soundLocation;
	private Media clip;
	private MediaPlayer player;
	private boolean loop;
	private boolean running;

	final CountDownLatch latch = new CountDownLatch(1);
	

	public AudioClip(AudioClip ac)	{
		noException();
		clip = ac.getClip();
		player = new MediaPlayer(clip);

		play(); stop(); while (Double.isNaN(length()));
		length = ac.length();
		setStart(ac.getStart());
		setStop(ac.getStop());
		setLoop(ac.getLoop());
		running = false;
	}
	public AudioClip(String soundLocation, double start, double stop)	{
		this(soundLocation, start, stop, false);
	}
	public AudioClip(String soundLocation, double start, double stop, boolean loop)	{
		noException();
		this.soundLocation = soundLocation;
		URL resource = getClass().getResource(soundLocation);
		clip = new Media(resource.toString());
		player = new MediaPlayer(clip);
		
		play();
		stop();

		while (Double.isNaN(length()));
		length = length();
		setStart(start);
		setStop(stop);

		running = false;
		if (loop)
			player.setCycleCount(MediaPlayer.INDEFINITE);
	}

	public AudioClip(String soundLocation, boolean loop)	{
		noException();
		this.soundLocation = soundLocation;
		URL resource = getClass().getResource(soundLocation);
		clip = new Media(resource.toString());
		player = new MediaPlayer(clip);
		
		play();
		stop();
		
		while (Double.isNaN(length()));
		length = length();
		running = false;
		if (loop)
			player.setCycleCount(MediaPlayer.INDEFINITE);
	}

	public AudioClip(String soundLocation)	{
		this(soundLocation, false);
	}

	public static void main(String... pumpkins)	{
		// demo(pumpkins[0]);
		AudioClip cheesepuffs = new AudioClip(pumpkins[0]);
		cheesepuffs.setRate(Math.PI);
		cheesepuffs.setVolume(100);
		cheesepuffs.play();
		new Scanner(System.in).nextLine();
	}

	public String getLocation()	{
		return soundLocation;
	}

	public String toString()	{
		return soundLocation;
	}

	public void dispose()	{
		if (isRunning())
			stop();
		player.dispose();
	}

	public void load()	{
		URL resource = getClass().getResource(soundLocation);
		clip = new Media(resource.toString());
		player = new MediaPlayer(clip);
	}

	public void loadNPlay()	{
		load();
		play();
	}

	public double length()	{
		return (getStop() - getStart()) / getRate();
	}

	public Media getClip()	{
		return clip;
	}

	public void play()	{
		if (isRunning())
			stop();
		player.play();
		running = true;
	}

	public void play(double position)	{
		double start = getStart();
		setStart(position);
		play();
		setStart(start);
	}

	public void stop()	{
		player.stop();
		running = false;
	}

	public void pause()	{
		player.pause();
		running = false;
	}

	public void setStart(double time)	{
		player.setStartTime(new Duration(time));
	}

	public double getStart()	{
		return player.getStartTime().toMillis();
	}

	public void setStop(double time)	{
		player.setStopTime(new Duration(time));
	}

	public double getStop()	{
		return player.getStopTime().toMillis();
	}

	public void setVolume(double volume)	{
		player.setVolume(volume);
	}

	public double getVolume()	{
		return player.getVolume();
	}

	public double getRate()	{
		return player.getRate();
	}

	public void setRate(double rate)	{
		player.setRate(rate);
	}

	public void setPosition(double position)	{
		if (isPlaying())
			player.seek(new Duration(position));
		else	{
			play();
			while(!isPlaying());
			player.seek(new Duration(position));
			pause();
		}
		// double start
		// player.seek(new Duration(position));
	}

	public double getPosition()	{
		return player.getCurrentTime().toMillis();
	}

	public double lengthSeconds()	{
		return length / 1E3;
	}

	public void setLoop(boolean loop)	{
		this.loop = loop;
		if (!loop)
			player.setCycleCount(1);
	}

	public boolean getLoop()	{
		return loop;
	}

	public boolean isRunning()	{
		if (getPosition() >= getStop())	// stops song if it finished playing
			stop();
		return running;
	}
	public boolean isPlaying()	{
		return player.getStatus().equals(Status.PLAYING);
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

		System.out.print("setVolume(double volume) "); testclip.setVolume(keyboard.nextDouble()); System.out.println();
		System.out.print("setRate(double rate) "); testclip.setRate(keyboard.nextDouble()); System.out.println();
		keyboard.nextLine();

		System.out.print("isRunning() "); keyboard.nextLine();
		System.out.println(testclip.isRunning());
	}
	@Override
	public void start(Stage primaryStage) {}
	public void noException()	{
	SwingUtilities.invokeLater(new Runnable() {
	   	public void run() {
	   	    new JFXPanel(); // initializes JavaFX environment
	   	    latch.countDown();
	   	}
	});
	try {
		latch.await();
	} catch (Exception e) {System.out.println("ahh");}
  }
}