package gui;
public class MainFrame extends JFrame {

	private JPanel contentPane;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
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
	public MainFrame() {
		Color colorRed         = new Color(194, 111, 125);
		Color colorMagenta     = new Color(163, 150, 163);
		Color colorBlue        = new Color(111, 167, 194);
		Color colorGreen       = new Color(111, 194, 180);
		Color colorGreenDark   = new Color(52, 126, 132);
		Color colorYellow      = new Color(194, 180, 111);
		Color colorOrange      = new Color(215, 120, 105);
		Color colorWhite       = new Color(255, 255, 255);
		Color colorBlack       = new Color(35, 32, 31);
		Color colorGreyLight   = new Color(236, 234, 229);   Color colorGrayLight = colorGreyLight;
		Color colorGrey        = new Color(200, 194, 190);   Color colorGray      = colorGrey;
		Color colorPrimary     = colorGreen; 
		Color colorPrimaryDark = colorGreenDark;
		Color colorSecondary   = colorOrange;
		Color colorTertiary    = colorMagenta;
		Color colorSuccess     = colorGreen;
		Color colorInfo        = colorBlue;
		Color colorAlert       = colorRed;
		
		int fontListLineHeight = 40;
		int fontRegularSize    = 15;
		int fontHeadingsSize   = 24;
		Font fontHeadings      = new Font("Segoe UI", Font.PLAIN, fontHeadingsSize);
		Font fontRegular       = new Font("Segoe UI", Font.PLAIN, fontRegularSize);
		Font fontRegularBold   = new Font("Segoe UI Black", Font.PLAIN, fontRegularSize);
		
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(null);
	}
}
