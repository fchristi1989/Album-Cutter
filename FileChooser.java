import javax.swing.JFileChooser;

public class FileChooser extends JFileChooser {
	public String getSourceFileName() {
		this.setFileSelectionMode(JFileChooser.FILES_ONLY);
		int returnVal = this.showOpenDialog(null);
		
		if (returnVal == JFileChooser.APPROVE_OPTION)
			return this.getSelectedFile().getAbsolutePath();
		return null;
	}
	
	public String getDestinationFileName() {
		this.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int returnVal = this.showSaveDialog(null);
		
		if (returnVal == JFileChooser.APPROVE_OPTION)
			return this.getCurrentDirectory().getAbsolutePath();
		return null;
	}
}
