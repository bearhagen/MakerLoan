package gui;
public class MainFrame extends JFrame {

	private JPanel contentPane;
	
	// Department card
	private JList<Object> departmentList;
	private JTextField cdTfName;
	private JTextField cdTfCode;
	private JLabel cdHeaderHeading;
	
	// Course card
	private JList<Object> courseList;
	private JLabel ccHeaderHeading;
	private JComboBox<Object> ccCbDepartments;
	private JTextField ccTfCode;
	private JTextField ccTfName;

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
		setTitle("MakerLoan - Hi\u00D8");
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 500);
		setMinimumSize(new Dimension(500, 400));
		
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// Back button
		JButton btnTilbake = new JButton("Tilbake");
		btnTilbake.setForeground(colorBlack);
		btnTilbake.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		btnTilbake.setFocusPainted(false);
		btnTilbake.setBackground(colorGrayLight);
		btnTilbake.setBounds(25, 25, 100, 30);
		btnTilbake.setVisible(false);
		contentPane.add(btnTilbake);
		
		btnTilbake.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	btnTilbake.setBackground(colorSecondary);
		    	btnTilbake.setForeground(colorWhite);
		    	UIManager.put("Button.select", colorSecondary);
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	btnTilbake.setForeground(colorBlack);
		    	btnTilbake.setBackground(colorGrayLight);
		    }
		});
		
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
		
		//// cardDepartment - Start ////
		JPanel cardDepartment = new JPanel();
		cardDepartment.setBackground(Color.WHITE);
		cardDepartment.setBorder(emptyBorder);
        GridBagLayout cardDepartmentsGbLayout = new GridBagLayout();
        cardDepartmentsGbLayout.columnWidths = new int[] {getWidth()};
        cardDepartmentsGbLayout.rowHeights = new int[] {100, 150, 200};
        cardDepartmentsGbLayout.columnWeights = new double[]{1.0};
        cardDepartmentsGbLayout.rowWeights = new double[]{0.0, 0.0, 1.0};
		cardDepartment.setLayout(cardDepartmentsGbLayout);
		
		JPanel cdHeader = new JPanel();
		cdHeader.setBackground(Color.WHITE);
		GridBagConstraints gbc_cdHeader = new GridBagConstraints();
		gbc_cdHeader.fill = GridBagConstraints.BOTH;
		gbc_cdHeader.insets = insets;
		gbc_cdHeader.gridx = 0;
		gbc_cdHeader.gridy = 0;
		cardDepartment.add(cdHeader, gbc_cdHeader);
		cdHeader.setLayout(new BorderLayout(0, 0));
		
		cdHeaderHeading = new JLabel("Avdelinger oversikt");
		cdHeaderHeading.setHorizontalAlignment(SwingConstants.CENTER);
		cdHeaderHeading.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		cdHeaderHeading.setBackground(Color.WHITE);
		cdHeader.add(cdHeaderHeading, BorderLayout.CENTER);
		
		JPanel cdBody = new JPanel();
		cdBody.setBorder(new EmptyBorder(0, 100, 25, 100));
		cdBody.setBackground(Color.WHITE);
		GridBagConstraints gbc_cdBody = new GridBagConstraints();
		gbc_cdBody.fill = GridBagConstraints.BOTH;
		gbc_cdBody.insets = insets;
		gbc_cdBody.gridx = 0;
		gbc_cdBody.gridy = 1;
		cardDepartment.add(cdBody, gbc_cdBody);
		cdBody.setLayout(new GridLayout(0, 2, 50, 15));
		
		JLabel lblTing = new JLabel("Kode");
		cdBody.add(lblTing);
		
		JLabel lblPerson = new JLabel("Navn");
		cdBody.add(lblPerson);
		
		cdTfCode = new JTextField();
		cdTfCode.setColumns(10);
		cdBody.add(cdTfCode);
		
		cdTfName = new JTextField();
		cdBody.add(cdTfName);
		cdTfName.setColumns(10);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		cdBody.add(panel);
		
		JButton cdBtnCreate = new JButton("Opprett");
		cdBtnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Check that the user filled out fields before trying to create department
				if (cdTfCode.getText().length() != 0 && cdTfName.getText().length() != 0) {
					// Check if the department exists before trying to create it
					if (Department.exist(cdTfCode.getText())) {
						error("Avdeling " + cdTfCode.getText() + " - " + cdTfName.getText() + " finnes allerede");
						return;
					}
					
					// Everything is ok, create the department
					new Department(cdTfName.getText(), cdTfCode.getText());
					cdTfName.setText("");
					cdTfCode.setText("");
					updateLists();
				} else {
					return;
				}
			}
		});
		cdBody.add(cdBtnCreate);
		//// cardDepartment - End ////

		//// cardCourse - Start ////
        JPanel cardCourse = new JPanel();
        cardCourse.setBackground(Color.WHITE);
        cardCourse.setBorder(emptyBorder);
        GridBagLayout cardCourseGbLayout = new GridBagLayout();
        cardCourseGbLayout.columnWidths = new int[] {getWidth()};
        cardCourseGbLayout.rowHeights = new int[] {100, 150, 200};
        cardCourseGbLayout.columnWeights = new double[]{1.0};
        cardCourseGbLayout.rowWeights = new double[]{0.0, 0.0, 1.0};
        cardCourse.setLayout(cardCourseGbLayout);
        
        JPanel ccHeader = new JPanel();
        ccHeader.setBackground(Color.WHITE);
        GridBagConstraints gbc_ccHeader = new GridBagConstraints();
        gbc_ccHeader.fill = GridBagConstraints.BOTH;
        gbc_ccHeader.gridx = 0;
        gbc_ccHeader.gridy = 0;
        cardCourse.add(ccHeader, gbc_ccHeader);
        ccHeader.setLayout(new BorderLayout(0, 0));
        
        ccHeaderHeading = new JLabel("Kurs oversikt");
        ccHeaderHeading.setHorizontalAlignment(SwingConstants.CENTER);
        ccHeaderHeading.setFont(new Font("Segoe UI", Font.PLAIN, 24));
        ccHeaderHeading.setBackground(Color.WHITE);
        ccHeader.add(ccHeaderHeading, BorderLayout.CENTER);
        
        JPanel ccBody = new JPanel();
        ccBody.setBorder(new EmptyBorder(0, 100, 25, 100));
        ccBody.setBackground(Color.WHITE);
        GridBagConstraints gbc_ccBody = new GridBagConstraints();
        gbc_ccBody.fill = GridBagConstraints.BOTH;
        gbc_ccBody.gridx = 0;
        gbc_ccBody.gridy = 1;
        cardCourse.add(ccBody, gbc_ccBody);
        ccBody.setLayout(new GridLayout(3, 3, 50, 15));
        
        JLabel lblAvdeling = new JLabel("Avdeling");
        ccBody.add(lblAvdeling);
        
        JLabel lblKode = new JLabel("Kode");
        ccBody.add(lblKode);
        
        JLabel lblNavn = new JLabel("Navn");
        ccBody.add(lblNavn);
        
        JButton ccBtnCreate = new JButton("Opprett");
        ccBtnCreate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
				// Check that the user filled out fields before trying to create course
				if (ccTfCode.getText().length() != 0 && ccTfName.getText().length() != 0 && ccCbDepartments.getSelectedIndex() != -1) {
					// Check if the department exists before trying to create it
					if (Course.exist(ccTfCode.getText())) {
						error("Avdeling " + ccTfCode.getText() + " - " + ccTfName.getText() + " finnes allerede");
						return;
					}
					
					// Everything is ok, create the course
					new Course(ccTfName.getText(), ccTfCode.getText(), (Department) ccCbDepartments.getSelectedItem());
					ccTfName.setText("");
					ccTfCode.setText("");
					updateLists();
				} else {
					error("Du m\u00E5 velge en avdeling og fylle ut kode og navn for \u00E5 opprette et kurs");
					return;
				}
            }
        });
        
        ccCbDepartments = new JComboBox<Object>();
        ccCbDepartments.setPrototypeDisplayValue("Lorem ipsum dolor");
        
        ccBody.add(ccCbDepartments);
        
        ccTfCode = new JTextField();
        ccBody.add(ccTfCode);
        ccTfCode.setColumns(10);
        
        ccTfName = new JTextField();
        ccBody.add(ccTfName);
        ccTfName.setColumns(10);
        
        JPanel panel_2 = new JPanel();
        panel_2.setBackground(Color.WHITE);
        ccBody.add(panel_2);
        
        JPanel panel_1 = new JPanel();
        panel_1.setBackground(Color.WHITE);
        ccBody.add(panel_1);
        ccBody.add(ccBtnCreate);
        panel_2.setBackground(Color.WHITE);
        ccCbDepartments.setPrototypeDisplayValue("Lorem ipsum dolor");
        panel_1.setBackground(Color.WHITE);
        
        JPanel ccFooter = new JPanel();
        ccFooter.setBorder(new EmptyBorder(0, 0, 0, 17));
        ccFooter.setBackground(colorGrayLight);
        GridBagConstraints gbc_ccFooter = new GridBagConstraints();
        gbc_ccFooter.fill = GridBagConstraints.BOTH;
        gbc_ccFooter.gridx = 0;
        gbc_ccFooter.gridy = 2;
        cardCourse.add(ccFooter, gbc_ccFooter);
        ccFooter.setLayout(new BorderLayout(0, 0));
        
        courseList = new JList<Object>();
        courseList.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        courseList.setBackground(colorGrayLight);
        courseList.setFixedCellHeight(fontListLineHeight);
        
        JScrollPane ccFooterScrollPane = new JScrollPane();
        ccFooterScrollPane.setViewportBorder(new MatteBorder(0, 50, 0, 50, colorGrayLight));
        ccFooterScrollPane.setBorder(emptyBorder);
        ccFooter.add(ccFooterScrollPane, BorderLayout.CENTER);
        ccFooterScrollPane.setViewportView(courseList);
		//// cardCourse - End ////
	public void updateLists() {
		// for departments card
		departmentList.setListData(Department.toStrings());
		
		// for course card
		courseList.setListData(Course.toStrings());
		ccCbDepartments.removeAllItems();
		for (int i = 0; i < Department.getDepartments().size(); i++) {			
			ccCbDepartments.addItem(Department.getDepartments().get(i));
		}
	
	}
	}
}
