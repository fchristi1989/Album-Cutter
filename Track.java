
public class Track {
	private String title;
	private int startSeconds;
	private int endSeconds;
	private String timeStamp;
	
	public Track(String title2, int startSeconds, String timeStamp) {
		title = title2;
		this.startSeconds = startSeconds;
		endSeconds = -1;
		this.timeStamp = timeStamp;
	}
	
	public int getStartSeconds() {
		return startSeconds;
	}

	public void setEndSeconds(int endSeconds) {
		this.endSeconds = endSeconds;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getTimeStamp() {
		return timeStamp;
	}

	public int getEndSeconds() {
		return endSeconds;
	}

}
