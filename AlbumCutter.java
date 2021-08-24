import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.TextArea;

public class AlbumCutter extends JFrame {

	private JPanel contentPane;
	
	private String sourcePath;
	private String destinationPath;
	private String rawText;
	
	private JButton btnChooseSource;
	private JButton btnChooseDestination;
	private JButton btnClear;
	private JButton btnInterpret;
	private JButton btnCut;
	
	private JLabel lblSource;
	private JLabel lblDestination;
	private TextArea text;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AlbumCutter frame = new AlbumCutter();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AlbumCutter() {
		setTitle("Album Cutter");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnChoose = new JButton("Choose..");
		btnChoose.setBounds(6, 6, 170, 29);
		contentPane.add(btnChoose);
		
		JButton btnChoose_1 = new JButton("Choose...");
		btnChoose_1.setBounds(6, 47, 170, 29);
		contentPane.add(btnChoose_1);
		
		JButton btnClearAll = new JButton("Clear All");
		btnClearAll.setBounds(6, 88, 170, 29);
		contentPane.add(btnClearAll);
		
		JButton btnInterpretTimeStamps = new JButton("Interpret Tracklist");
		btnInterpretTimeStamps.setEnabled(false);
		btnInterpretTimeStamps.setBounds(6, 129, 170, 29);
		contentPane.add(btnInterpretTimeStamps);
		
		JButton btnCutAudioSource = new JButton("Cut Audio Source");
		btnCutAudioSource.setEnabled(false);
		btnCutAudioSource.setBounds(6, 170, 170, 29);
		contentPane.add(btnCutAudioSource);
		
		JLabel lblSourceAudioFile = new JLabel("Source Audio File:");
		lblSourceAudioFile.setBounds(188, 11, 410, 16);
		contentPane.add(lblSourceAudioFile);
		
		JLabel lblDestinationFolder = new JLabel("Destination Folder:");
		lblDestinationFolder.setBounds(188, 52, 410, 16);
		contentPane.add(lblDestinationFolder);
		
		TextArea textArea = new TextArea();
		textArea.setBounds(182, 89, 410, 285);
		contentPane.add(textArea);
		
		
		sourcePath = "";
		destinationPath = "";
		rawText = "";
		
		btnChoose.addActionListener(new JActionListener(this, btnChoose));
		btnChooseSource = btnChoose;
		
		btnChoose_1.addActionListener(new JActionListener(this, btnChoose_1));
		btnChooseDestination = btnChoose_1;
		
		btnClearAll.addActionListener(new JActionListener(this, btnClearAll));
		btnClear = btnClearAll;
		
		btnInterpretTimeStamps.addActionListener(new JActionListener(this, btnInterpretTimeStamps));
		btnInterpret = btnInterpretTimeStamps;
		
		btnCutAudioSource.addActionListener(new JActionListener(this, btnCutAudioSource));
		btnCut = btnCutAudioSource;
		
		lblSource = lblSourceAudioFile;
		lblDestination = lblDestinationFolder;
		text = textArea;
	}

	public void actionPerformed(JButton btn) {
		if (btn == btnChooseSource) {
			sourcePath = new FileChooser().getSourceFileName();
			lblSource.setText("Source Audio File: " + sourcePath);
			
			if (sourcePath != "" && destinationPath != "" && text.getText() != "")
				btnInterpret.setEnabled(true);
		}
		else if (btn == btnChooseDestination) {
			destinationPath = new FileChooser().getDestinationFileName();
			lblDestination.setText("Destination Folder: " + destinationPath);
			
			if (sourcePath != "" && destinationPath != "" && text.getText() != "")
				btnInterpret.setEnabled(true);
		}
		else if (btn == btnClear) {
			sourcePath = "";
			lblSource.setText("Source Audio File: " + sourcePath);
			destinationPath = "";
			lblDestination.setText("Destination Folder: " + destinationPath);
			text.setText("");
			btnInterpret.setEnabled(false);
			btnCut.setEnabled(false);
		}
		else if (btn == btnInterpret) {
			text.setText(new TracklistInterpreter().interpret(text.getText()));
			btnCut.setEnabled(true);
		}
		else if (btn == btnCut) {
			new TracklistInterpreter().cut(text.getText(), sourcePath, destinationPath);
		}
		
	}
}
