import java.io.*;
import javax.sound.sampled.*;

//source: https://stackoverflow.com/questions/39887655/how-to-get-the-speech-with-desired-start-position-and-end-position-from-wav-fil

class AudioFileProcessor {

	public void copyAudio(String sourceFileName, String destinationFileName, int startSecond, int secondsToCopy) {
		AudioInputStream inputStream = null;
		AudioInputStream shortenedStream = null;
		
		try {
			File file = new File(sourceFileName);
			AudioFileFormat fileFormat = AudioSystem.getAudioFileFormat(file);
			AudioFormat format = fileFormat.getFormat();
			inputStream = AudioSystem.getAudioInputStream(file);
			int bytesPerSecond = format.getFrameSize() * (int)format.getFrameRate();
			
			if (secondsToCopy < 0)
				secondsToCopy = (int) inputStream.getFrameLength() / (int) format.getFrameRate();
			
			inputStream.skip(startSecond * bytesPerSecond);
			long framesOfAudioToCopy = secondsToCopy * (int)format.getFrameRate();
			shortenedStream = new AudioInputStream(inputStream, format, framesOfAudioToCopy);
			File destinationFile = new File(destinationFileName);
			AudioSystem.write(shortenedStream, fileFormat.getType(), destinationFile);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if (inputStream != null)
				try {
					inputStream.close();
				}
				catch (Exception e) {
					e.printStackTrace();;
				}
			if (shortenedStream != null)
				try {
					shortenedStream.close();
				}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}