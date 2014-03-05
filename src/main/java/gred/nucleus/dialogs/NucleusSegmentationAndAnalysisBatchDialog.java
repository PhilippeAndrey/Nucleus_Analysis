package gred.nucleus.dialogs;

import java.awt.Container;
import java.awt.Cursor;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;


/**
 * 
 * @author Poulet Axel
 *
 */
public class NucleusSegmentationAndAnalysisBatchDialog extends JFrame
{
	
	private static final long serialVersionUID = 1L;
	private JButton _jButtonWorkDirectory = new JButton("Output Directory"), _jButtonStart = new JButton("Start"), _jButtonQuit = new JButton("Quit"), _jButtonRawData = new JButton("Raw Data");
	private Container _container;
	private JComboBox _comboBoxProcessor = new JComboBox();
	private JFormattedTextField _jTextFieldXCalibration = new JFormattedTextField(Number.class);
	private JFormattedTextField _jTextFieldYCalibration = new JFormattedTextField(Number.class);
    private JFormattedTextField _jTextFieldZCalibration =  new JFormattedTextField(Number.class);
    private JFormattedTextField _jTextFieldMax =  new JFormattedTextField(Number.class);
    private JFormattedTextField _jTextFieldMin =  new JFormattedTextField(Number.class);
    private JTextField _jTextFieldUnit =  new JTextField(), _jTextFieldWorkDirectory  =  new JTextField(), _jTextFieldRawData = new JTextField();
	private JLabel _jLabelXcalibration, _jLabelYcalibration, _jLabelZcalibration, _jLabelUnit,_jLabelSegmentation,
	_jLabelVolumeMin, _jLabelVolumeMax, _jLabelAnalysis, _jLabelWorkDirectory, _jLabelCalibration, _jLabelNbProcessor;
	private ButtonGroup buttonGroupChoiceAnalysis = new ButtonGroup();
	private JRadioButton _jRadioButton2DAnalysis = new JRadioButton("2D");
    private JRadioButton _jRadioButton3DAnalysis = new JRadioButton("3D");
    private JRadioButton _jRadioButton2D3DAnalysis = new JRadioButton("2D and 3D");
	private String _workDirectory, _rawDataDirectory;
	private boolean _start = false;
	private int _nbProcChosen = -50;
	public static void main(String[] args)  
    {
		NucleusSegmentationAndAnalysisBatchDialog nucleusSegmentationAndAnalysisBatchDialog = new NucleusSegmentationAndAnalysisBatchDialog();
    	nucleusSegmentationAndAnalysisBatchDialog.setLocationRelativeTo(null);
    }
	
    
    /**
     * 
     *
     */
    
	public NucleusSegmentationAndAnalysisBatchDialog ()
	{
		this.setTitle("Nucleus segmentation & analysis");
		this.setSize(500, 500);
		this.setLocationRelativeTo(null);
		_container = getContentPane();
		GridBagLayout gridBagLayout = new GridBagLayout();
	   	gridBagLayout.rowWeights = new double[] {0.0, 0.0, 0.0, 0.1};
	   	gridBagLayout.rowHeights = new int[] {17, 71, 124, 7};
	   	gridBagLayout.columnWeights = new double[] {0.0, 0.0, 0.0, 0.1};
	   	gridBagLayout.columnWidths = new int[] {236, 109, 72, 20};
	   	_container.setLayout (gridBagLayout);
	   	
	   	_jLabelWorkDirectory = new JLabel();
	   	_container.add(_jLabelWorkDirectory, new GridBagConstraints(0, 1, 0, 0, 0.0, 0.0, GridBagConstraints.NORTHWEST,GridBagConstraints.NONE, new Insets(0, 10, 0, 0), 0, 0));
	   	_jLabelWorkDirectory.setText("Work directory and Raw data choice : ");
	   
	   	_container.add(_jButtonRawData, new GridBagConstraints(0, 1, 0, 0, 0.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE,new Insets(30, 10, 0, 0), 0, 0));
	   	_jButtonRawData.setPreferredSize(new java.awt.Dimension(120, 21));
	   	_jButtonRawData.setFont(new java.awt.Font("Albertus",2,10));
	   	
		_container.add(_jTextFieldRawData, new GridBagConstraints(0, 1, 0, 0, 0.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(30, 160, 0, 0), 0, 0));
		_jTextFieldRawData.setPreferredSize(new java.awt.Dimension(280, 21));
		_jTextFieldRawData.setFont(new java.awt.Font("Albertus",2,10));
	   	
	   	_container.add(_jButtonWorkDirectory, new GridBagConstraints(0, 1, 0, 0, 0.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE,new Insets(60, 10, 0, 0), 0, 0));
	   	_jButtonWorkDirectory.setPreferredSize(new java.awt.Dimension(120, 21));
	   	_jButtonWorkDirectory.setFont(new java.awt.Font("Albertus",2,10));
	   	
		_container.add(_jTextFieldWorkDirectory, new GridBagConstraints(0, 1, 0, 0, 0.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(60, 160, 0, 0), 0, 0));
	   	_jTextFieldWorkDirectory.setPreferredSize(new java.awt.Dimension(280, 21));
	   	_jTextFieldWorkDirectory.setFont(new java.awt.Font("Albertus",2,10));
	   	
	   	_jLabelCalibration = new JLabel();
	   	_container.add(_jLabelCalibration, new GridBagConstraints(0, 2, 0, 0, 0.0, 0.0, GridBagConstraints.NORTHWEST,GridBagConstraints.NONE, new Insets(20, 10, 0, 0), 0, 0));
	   	_jLabelCalibration.setText("Voxel Calibration:");
	   	
	   	_container.setLayout (gridBagLayout);
		_jLabelXcalibration = new JLabel();
		_container.add(_jLabelXcalibration, new GridBagConstraints(0, 2, 0, 0, 0.0, 0.0, GridBagConstraints.NORTHWEST,GridBagConstraints.NONE, new Insets(40, 20, 0, 0), 0, 0));
		_jLabelXcalibration.setText("x :");
		_jLabelXcalibration.setFont(new java.awt.Font("Albertus Extra Bold (W1)",2,12));
		_container.add(_jTextFieldXCalibration, new GridBagConstraints(0, 2, 0, 0, 0.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE,new Insets(40, 60, 0, 0), 0, 0));
		_jTextFieldXCalibration.setText("1");
		_jTextFieldXCalibration.setPreferredSize(new java.awt.Dimension(60, 21));
	
		_jLabelYcalibration = new JLabel();
		_container.add(_jLabelYcalibration, new GridBagConstraints(0, 2, 0, 0, 0.0, 0.0, GridBagConstraints.NORTHWEST,GridBagConstraints.NONE, new Insets(65, 20, 0, 0), 0, 0));
		_jLabelYcalibration.setText("y :");
		_jLabelYcalibration.setFont(new java.awt.Font("Albertus Extra Bold (W1)",2,12));
		_container.add(_jTextFieldYCalibration, new GridBagConstraints(0, 2, 0, 0, 0.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE,new Insets(65, 60, 0, 0), 0, 0));
		_jTextFieldYCalibration.setText("1");
		_jTextFieldYCalibration.setPreferredSize(new java.awt.Dimension(60, 21));
	
		_jLabelZcalibration = new JLabel();
		_container.add(_jLabelZcalibration, new GridBagConstraints(0, 2, 0, 0, 0.0, 0.0, GridBagConstraints.NORTHWEST,GridBagConstraints.NONE, new Insets(90, 20, 0, 0), 0, 0));
		_jLabelZcalibration.setText("z :");
		_jLabelZcalibration.setFont(new java.awt.Font("Albertus Extra Bold (W1)",2,12));
		_container.add(_jTextFieldZCalibration, new GridBagConstraints(0, 2,0, 0, 0.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE,new Insets(90, 60, 0, 0), 0, 0));
		_jTextFieldZCalibration.setText("1");
		_jTextFieldZCalibration.setPreferredSize(new java.awt.Dimension(60, 21));	 
		
		_jLabelUnit = new JLabel();
		_container.add(_jLabelUnit, new GridBagConstraints(0, 2, 0, 0, 0.0, 0.0, GridBagConstraints.NORTHWEST,GridBagConstraints.NONE, new Insets(115, 20, 0, 0), 0, 0));
		_jLabelUnit.setText("unit :");
		_jLabelUnit.setFont(new java.awt.Font("Albertus Extra Bold (W1)",2,12));
		_container.add(_jTextFieldUnit, new GridBagConstraints(0, 2,0, 0, 0.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE,new Insets(115, 60, 0, 0), 0, 0));
		_jTextFieldUnit.setText("pixel");
		_jTextFieldUnit.setPreferredSize(new java.awt.Dimension(60, 21));	

		
		
		_jLabelSegmentation = new JLabel();
	   	_container.add(_jLabelSegmentation, new GridBagConstraints(0, 3, 0, 0, 0.0, 0.0, GridBagConstraints.NORTHWEST,GridBagConstraints.NONE, new Insets(20, 10, 0, 0), 0, 0));
	   	_jLabelSegmentation.setText("Choose the min and max volumes of the nucleus:");
	   	
	   	_jLabelVolumeMin = new JLabel();
	   	_container.add(_jLabelVolumeMin, new GridBagConstraints(0, 3, 0, 0, 0.0, 0.0, GridBagConstraints.NORTHWEST,GridBagConstraints.NONE, new Insets(40, 20, 0, 0), 0, 0));
	   	_jLabelVolumeMin.setText("Minimun volume of the segmented nucleus :");
	   	_jLabelVolumeMin.setFont(new java.awt.Font("Albertus Extra Bold (W1)",2,12));
		_container.add(_jTextFieldMin, new GridBagConstraints(0, 3, 0, 0, 0.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE,new Insets(40, 320, 0, 0), 0, 0));
		_jTextFieldMin.setText("15");
		_jTextFieldMin.setPreferredSize(new java.awt.Dimension( 60, 21));

		_jLabelVolumeMax = new JLabel();
	   	_container.add(_jLabelVolumeMax, new GridBagConstraints(0, 3, 0, 0, 0.0, 0.0, GridBagConstraints.NORTHWEST,GridBagConstraints.NONE, new Insets(70, 20, 0, 0), 0, 0));
	   	_jLabelVolumeMax.setText("Maximum volume of the segmented nucleus :");
	   	_jLabelVolumeMax.setFont(new java.awt.Font("Albertus Extra Bold (W1)",2,12));
		_container.add(_jTextFieldMax, new GridBagConstraints(0, 3, 0, 0, 0.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE,new Insets(70, 320, 0, 0), 0, 0));
		_jTextFieldMax.setText("2000");
		_jTextFieldMax.setPreferredSize(new java.awt.Dimension (60, 21));
		
		
		_jLabelAnalysis = new JLabel();
	   	_container.add(_jLabelAnalysis, new GridBagConstraints(0, 3, 0, 0, 0.0, 0.0, GridBagConstraints.NORTHWEST,GridBagConstraints.NONE, new Insets(100, 10, 0, 0), 0, 0));
	   	_jLabelAnalysis.setText("Type of analysis:");
	   
	   	buttonGroupChoiceAnalysis.add(_jRadioButton2DAnalysis);
		buttonGroupChoiceAnalysis.add(_jRadioButton3DAnalysis);
		buttonGroupChoiceAnalysis.add(_jRadioButton2D3DAnalysis);
		_jRadioButton2DAnalysis.setFont(new java.awt.Font("Albertus Extra Bold (W1)",2,12));
		_jRadioButton3DAnalysis.setFont(new java.awt.Font("Albertus Extra Bold (W1)",2,12));
		_jRadioButton2D3DAnalysis.setFont(new java.awt.Font("Albertus Extra Bold (W1)",2,12));
		_container.add(_jRadioButton2DAnalysis, new GridBagConstraints(0, 3, 0, 0, 0.0, 0.0, GridBagConstraints.NORTHWEST,GridBagConstraints.NONE, new Insets(130, 170, 0, 0), 0, 0));
		_container.add(_jRadioButton3DAnalysis, new GridBagConstraints(0, 3, 0, 0,  0.0, 0.0, GridBagConstraints.NORTHWEST,GridBagConstraints.NONE, new Insets(130, 120, 0, 0), 0, 0));
		_container.add(_jRadioButton2D3DAnalysis, new GridBagConstraints(0,3, 0, 0, 0.0, 0.0, GridBagConstraints.NORTHWEST,GridBagConstraints.NONE, new Insets(130, 10, 0, 0), 0, 0));
		_jRadioButton2D3DAnalysis.setSelected(true);
		
		OperatingSystemMXBean bean = ManagementFactory.getOperatingSystemMXBean();
		int nbProc = bean.getAvailableProcessors();
		for (int i = 1; i <= nbProc; ++i) _comboBoxProcessor.addItem(i);
		_jLabelNbProcessor= new JLabel();
		_jLabelNbProcessor.setText("How many processor(s) :");
		_container.add(_jLabelNbProcessor, new GridBagConstraints(0, 3, 0, 0,  0.0, 0.0, GridBagConstraints.NORTHWEST,GridBagConstraints.NONE, new Insets(175, 10, 0,0), 0, 0));
		_container.add(_comboBoxProcessor, new GridBagConstraints(0, 3, 0, 0,  0.0, 0.0, GridBagConstraints.NORTHWEST,GridBagConstraints.NONE, new Insets(170, 200, 0,0), 0, 0));
		_comboBoxProcessor.addItemListener(new ItemState());
			
		_container.add(_jButtonStart, new GridBagConstraints(0, 3, 0, 0,  0.0, 0.0, GridBagConstraints.NORTHWEST,GridBagConstraints.NONE, new Insets(210, 140, 0,0), 0, 0));
		_jButtonStart.setPreferredSize(new java.awt.Dimension(120, 21));
		_container.add(_jButtonQuit, new GridBagConstraints(0, 3, 0, 0,  0.0, 0.0, GridBagConstraints.NORTHWEST,GridBagConstraints.NONE, new Insets(210, 10, 0, 0), 0, 0));
		_jButtonQuit.setPreferredSize(new java.awt.Dimension(120, 21));
	  	this.setVisible(true);
	   	
	  	WorkDirListener wdListener = new WorkDirListener();
	  	_jButtonWorkDirectory.addActionListener(wdListener);
	  	DataDirListener ddListener = new DataDirListener();
	  	_jButtonRawData.addActionListener(ddListener);
	  	QuitListener quitListener = new QuitListener(this);
	   	_jButtonQuit.addActionListener(quitListener);
	   	StartListener startListener = new StartListener(this);
	   	_jButtonStart.addActionListener(startListener);	   
	 }
	public void setNbProcessor(int nb){ _nbProcChosen = nb; }
	public int getNbProcessor(){ return _nbProcChosen; }
	public double getx(){ return Double.parseDouble(_jTextFieldXCalibration.getText()); }
	public double gety(){ return Double.parseDouble(_jTextFieldYCalibration.getText()); }
	public double getz(){ return Double.parseDouble(_jTextFieldZCalibration.getText()); }
	public String getUnit(){ return _jTextFieldUnit.getText(); }
	public double getMinSeg(){ return Double.parseDouble(_jTextFieldMin.getText()); }
	public double getMaxSeg(){ return Double.parseDouble(_jTextFieldMax.getText()); }
	public String getWorkDirectory(){return _jTextFieldWorkDirectory.getText();}
	public String getDirRawData(){return _jTextFieldRawData.getText();}
	public boolean isStart() {	return _start; }
	public boolean isTheBoth() {	return _jRadioButton2D3DAnalysis.isSelected(); }
	public boolean is2D() {	return _jRadioButton2DAnalysis.isSelected(); }
	public boolean is3D() {	return _jRadioButton3DAnalysis.isSelected(); }

	



	/********************************************************************************************************************************************
	  * 	Classe listener pour interagir avec les composants de la fenetre
	  */
	 /********************************************************************************************************************************************
	 /********************************************************************************************************************************************
	 /********************************************************************************************************************************************
	 /********************************************************************************************************************************************
	
*/
	class ItemState implements ItemListener
	{
		public void itemStateChanged(ItemEvent e)
		{
			setNbProcessor((Integer) e.getItem());
	   }               
	}
 
	class StartListener implements ActionListener 
	{
	
		NucleusSegmentationAndAnalysisBatchDialog _jfpfso;	
		public  StartListener (NucleusSegmentationAndAnalysisBatchDialog jfpfso) {_jfpfso = jfpfso;}
		/**
		  * 
		  */
		 public void actionPerformed(ActionEvent actionEvent)
		 {
			 if (_jTextFieldWorkDirectory.getText().isEmpty() || _jTextFieldRawData.getText().isEmpty())
				 JOptionPane.showMessageDialog(null, "You did not choose a work directory or the raw data", "Error", JOptionPane.ERROR_MESSAGE); 
			 else
			 {
				 _start=true;
				 _jfpfso.dispose();
			 }
		 }
	 }
	
	
	/**
	 * 
	 * @author lom
	 *
	 */
	
	class QuitListener implements ActionListener 
	{
		NucleusSegmentationAndAnalysisBatchDialog _jfpfso;	
		public  QuitListener (NucleusSegmentationAndAnalysisBatchDialog jfpfso) {_jfpfso = jfpfso;}
		public void actionPerformed(ActionEvent actionEvent) { _jfpfso.dispose(); }
	}
	
	
	/*	Bouton Reference et dossier ava		*/
	
	/**
	 * 
	 * @author lom
	 *
	 */
	 class WorkDirListener implements ActionListener
	 {
		 /**
		  * 
		  */		 
		 public void actionPerformed(ActionEvent actionEvent)
		 {
			 setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			 JFileChooser jFileChooser = new JFileChooser();
			 jFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			 int returnVal = jFileChooser.showOpenDialog(getParent());
			 if(returnVal == JFileChooser.APPROVE_OPTION)
			 {
				@SuppressWarnings("unused")
				String run = jFileChooser.getSelectedFile().getName();
				 _workDirectory = jFileChooser.getSelectedFile().getAbsolutePath();
				 _jTextFieldWorkDirectory.setText(_workDirectory);
			 }
			 setCursor (Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		 }	
	 }
	 
	 /**
		 * 
		 * @author lom
		 *
		 */
		 class DataDirListener implements ActionListener
		 {
			 /**
			  * 
			  */		 
			 public void actionPerformed(ActionEvent actionEvent)
			 {
				 setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				 JFileChooser jFileChooser = new JFileChooser();
				 jFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				 int returnVal = jFileChooser.showOpenDialog(getParent());
				 if(returnVal == JFileChooser.APPROVE_OPTION)
				 {
					 @SuppressWarnings("unused")
					String run = jFileChooser.getSelectedFile().getName();
					_rawDataDirectory = jFileChooser.getSelectedFile().getAbsolutePath();
					 _jTextFieldRawData.setText(_rawDataDirectory);
				 }
				 setCursor (Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			 }	
		 }
}