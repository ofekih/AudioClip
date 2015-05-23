import java.util.ArrayList;
import javax.swing.Timer;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;

/**
 * Makes an automatically-playing playlist of AudioClip files
 * Supports wav, mp3, mp4, flv, aif, and more filetypes
 * @author Kesav Viswanadha and Ofek Gila
 * @version 1.5
 * @since  May 22nd, 2015
 * @lastedited May 23rd, 2015
 */

public class AudioList implements ActionListener {

	private ArrayList<String> locations;
	private AudioClip clip;
	private Timer loadNextSong;

	public AudioList(String[] locs, boolean shuffle) {
		locations = new ArrayList<String>();
		for (String s : locs) {
			locations.add(s);
		}
		if (shuffle) shuffle();
		clip = new AudioClip(locations.get(0));
		loadNextSong = new Timer((int)clip.length, this);
		loadNextSong.setRepeats(false);
	}

	public AudioList(ArrayList<String> locs, boolean shuffle) {
		locations = new ArrayList<String>();
		for (String s : locs) {
			locations.add(s);
		}
		if (shuffle) shuffle();
		clip = new AudioClip(locations.get(0));
		loadNextSong = new Timer((int)clip.length, this);
	}

	public AudioList(boolean shuffle)	{
		this(shuffle, "mp3", "wav", "mp4", "m4a", "m4v", "m3u8", "fxm", "flv", "aif", "aiff");
	}
	public AudioList(boolean shuffle, String... extensions)	{
		locations = getFilesInFolder(new File(getDirectory()), new ArrayList<String>(), extensions);
		if (shuffle) shuffle();
		clip = new AudioClip(locations.get(0));
		loadNextSong = new Timer((int)clip.length, this);
	}

	public ArrayList<String> getFilesInFolder(final File folder, ArrayList<String> locations, String... extensions) {
		for (final File fileEntry : folder.listFiles())
			if (fileEntry.isDirectory())
				getFilesInFolder(fileEntry, locations);
			else for (String extension : extensions)
				if (getExtension(fileEntry.getPath()).equals(extension))
					locations.add(fileEntry.getPath().substring(getDirectory().length()+1));
			return locations;
	}
	public String getDirectory() {
		return  System.getProperty("user.dir");
    }

    public String getExtension(String name)	{
		try	{
			return name.substring(name.lastIndexOf(".")).substring(1);
		}
		catch (StringIndexOutOfBoundsException e)	{
			return "";
		}
	}

	public void play() {
		clip.play();
		loadNextSong.start();
	}

	public void shuffle() {
		for (int k = locations.size() - 1; k > 0; k--) {
			int r = (int)(Math.random() * k);
			String temp = locations.get(r);
			locations.set(r, locations.get(k));
			locations.set(k, temp);
		}
	}

	public void actionPerformed(ActionEvent e) {
		locations.add(locations.remove(0));
		clip.dispose();
		clip.reInit(locations.get(0));
		clip.play();
		loadNextSong.setInitialDelay((int)clip.length);
		loadNextSong.restart();
	}
}