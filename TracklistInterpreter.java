import java.util.ArrayList;

public class TracklistInterpreter {
	private ArrayList<Track> tracks;

	public String interpret(String text) {
		tracks = new ArrayList<Track>();
		String[] lines = text.split("\n");
		
		for (String line : lines) {
			int timeStampMiddle = line.lastIndexOf(":");
			
			if (timeStampMiddle != -1) {
				int timeStampStart = timeStampMiddle;
				
				try {
					while (isNumeric(line.charAt(timeStampStart - 1))) {
						timeStampStart--;
					}
				}
				catch (StringIndexOutOfBoundsException e) {
				}
				
				int timeStampEnd = timeStampMiddle;
				
				try {
					while (isNumeric(line.charAt(timeStampEnd + 1))) {
						timeStampEnd++;
					}
				}
				catch (StringIndexOutOfBoundsException e) {
				}
				
				String timeStamp = line.substring(timeStampStart, timeStampEnd + 1);
				String[] timeStampSplit = timeStamp.split(":");
				int startSeconds = Integer.parseInt(timeStampSplit[0]) * 60 + Integer.parseInt(timeStampSplit[1]);
				String title = line.replaceAll(timeStamp, "");
				
				while (title.charAt(0) == ' ' || title.charAt(0) == '-') {
					title = title.substring(1);
				}
				
				while (title.charAt(title.length() - 1) == ' ' || title.charAt(title.length() - 1) == '-') {
					title = title.substring(0, title.length() - 1);
				}
				
				tracks.add(new Track(title, startSeconds, timeStamp));
			}
		}
		
		tracks = sort(tracks);
		String result = "";
		
		for (Track t : tracks) {
			result += t.getTitle() + " " + t.getTimeStamp() + "\n";
		}
		
		return result;
	}

	private ArrayList<Track> sort(ArrayList<Track> tracks) {
		ArrayList<Track> result = new ArrayList<Track>();
		
		while (!tracks.isEmpty()) {
			Track earliest = tracks.get(0);
			
			for (Track t : tracks) {
				if (t.getStartSeconds() < earliest.getStartSeconds())
					earliest = t;
			}
			
			result.add(earliest);
			tracks.remove(earliest);
		}
		
		for (int i = 0; i < result.size() - 1; i++) {
			result.get(i).setEndSeconds(result.get(i + 1).getStartSeconds());
		}
		
		return result;
	}

	private boolean isNumeric(char charAt) {
		return (charAt >= 48 && charAt <= 57);
	}

	public void cut(String text, String sourcePath, String destinationPath) {
		interpret(text);
		
		for (Track t: tracks) {
			new AudioFileProcessor().copyAudio(sourcePath, destinationPath + "/" + t.getTitle() + ".wav", t.getStartSeconds(), t.getEndSeconds() - t.getStartSeconds());
		}
	}

}
