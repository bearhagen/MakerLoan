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
		
		ImageIcon appIcon      = new ImageIcon("C:\\Users\\bjorn\\Dropbox (Personal)\\Personal\\Education\\Hi\u00D8\\hiof-branding\\emblem-512.png");
		
		Border emptyBorder     = BorderFactory.createEmptyBorder();
		
		Insets insets = new Insets(0, 0, 0, 0);
		
		setIconImage(appIcon.getImage());
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 500);
		setMinimumSize(new Dimension(500, 400));
		
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		// Setup main panel
		JPanel cards = new JPanel();
		cards.setBorder(new EmptyBorder(0, 0, 0, 0));
		cards.setBounds(0, 0, 732, 453);
		contentPane.add(cards);
		cards.setLayout(new CardLayout(0, 0));

		// So the absolute positioned cards panel will resize on window resize
		addComponentListener(new ComponentAdapter() {
		    public void componentResized(ComponentEvent e) {
		        cards.setBounds(0, 0, getWidth(), getHeight());           
		    }
		});
		
//		So the absolute positioned cards panel will resize on window maximize
		addWindowStateListener(new WindowStateListener() {
			@Override
			public void windowStateChanged(WindowEvent e) {
				cards.setBounds(0, 0, getWidth(), getHeight());
			}
		});
		
		//// cardMain - Start ////
		JPanel cardMain = new JPanel();
		cardMain.setBackground(Color.WHITE);
		cardMain.setLayout(new MigLayout("insets 0", "[::100%,grow,center]", "[75px:n,grow 20][150px:n,grow 40][100px:n,grow 40]"));
		
		JPanel cmHeader = new JPanel();
		cardMain.add(cmHeader, "cell 0 0,grow");
		cmHeader.setBackground(Color.WHITE);
		cmHeader.setLayout(new BorderLayout(0, 0));
		
		JLabel c1HeaderHeading = new JLabel("Maker Loan");
		cmHeader.add(c1HeaderHeading, BorderLayout.CENTER);
		c1HeaderHeading.setBackground(Color.WHITE);
		c1HeaderHeading.setHorizontalAlignment(SwingConstants.CENTER);
		c1HeaderHeading.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		
		JPanel cmBody = new JPanel();
		cardMain.add(cmBody, "cell 0 1,grow");
//		cmBody.setBorder(new EmptyBorder(25, 100, 100, 100)); // BORDER FOR WHEN LOAN IS ADDED
		cmBody.setBorder(new EmptyBorder(50, 100, 50, 100));
		cmBody.setBackground(Color.WHITE);
//		cmBody.setLayout(new GridLayout(2, 2, 50, 25)); // LAYOUT FOR WHEN LOAN IS ADDED
		cmBody.setLayout(new GridLayout(1, 3, 50, 25));
				
		JPanel cmFooter = new JPanel();
		FlowLayout flowLayout = (FlowLayout) cmFooter.getLayout();
		flowLayout.setVgap(0);
		flowLayout.setHgap(0);
		cardMain.add(cmFooter, "cell 0 2,grow");
		cmFooter.setBorder(new EmptyBorder(10, 0, 0, 0));
		cmFooter.setBackground(Color.WHITE);
		
		JLabel c1FooterImage = new JLabel("");
		c1FooterImage.setVerticalAlignment(SwingConstants.BOTTOM);
		c1FooterImage.setBackground(Color.WHITE);
		c1FooterImage.setIcon(new ImageIcon("C:\\Users\\bjorn\\Dropbox (Personal)\\Personal\\Education\\Hi\u00D8\\hiof-branding\\m\u00F8nster-gr\u00E5.png"));
		cmFooter.add(c1FooterImage);
		//// cardMain - End ////
	}
}
