public class AudioListTester {
	public static void main(String[] args) {
		AudioList al = new AudioList(AudioList.INITIAL_SHUFFLE, AudioList.SEARCH_EVERYTHING);
		al.setRate(1);
		al.play();
	}
}