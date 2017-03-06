package gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultListSelectionModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import administration.Course;
import administration.Department;
import hr.AcademicEmployee;
import hr.AdminEmployee;
import hr.Person;
import hr.Student;
import net.miginfocom.swing.MigLayout;

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

	// Person card
	private JList<Object> personList;
	private JComboBox<Object> cpCbDepartments;
	private JComboBox<String> cpCbTypes;
	private JTextField cpTfName;
	private JTextField cpTfSurName;
	private JTextField cpTfPhone;
	
	// Course select card
	private JList<Object> courseSelectList;
	private JButton ccsBtnDone = new JButton("Ferdig");
	private JButton btnAvbryt = new JButton("Avbryt");


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
		
		// Back button extra
		// Must be under cards
		btnTilbake.setBorder(emptyBorder);
		btnTilbake.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				CardLayout cardLayout = (CardLayout) cards.getLayout();
				cardLayout.show(cards, "cardMain");
				btnTilbake.setVisible(false);
			}
		});

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
		
		// mainCard buttons
		String[] buttons = {/*"L\u00E5n",*/ "Personer", "Kurs", "Avdelinger"};
		for (int i = 0; i < buttons.length; i++) {
			String btnText = buttons[i];

			JButton btnTmp = new JButton(buttons[i]);
			btnTmp.setForeground(colorWhite);
			btnTmp.setFont(new Font("Segoe UI Black", Font.PLAIN, 15));
			btnTmp.setBackground(colorPrimary);
			btnTmp.setBorder(emptyBorder);
			btnTmp.setFocusPainted(false);
			btnTmp.setCursor(new Cursor(Cursor.HAND_CURSOR));
			
			btnTmp.addMouseListener(new java.awt.event.MouseAdapter() {
			    public void mouseEntered(java.awt.event.MouseEvent evt) {
			    	btnTmp.setBackground(new Color(215, 120, 105));
			    	UIManager.put("Button.select", colorSecondary);
			    }

			    public void mouseExited(java.awt.event.MouseEvent evt) {
			    	btnTmp.setBackground(colorPrimary);
			    }
			});
			
			btnTmp.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					CardLayout cardLayout = (CardLayout) cards.getLayout();
					cardLayout.show(cards, "card" + btnText);
					btnTilbake.setVisible(true);
				}
			});
			
			cmBody.add(btnTmp);
		}
				
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
		
		// Add card
     	cards.add(cardMain, "cardMain");
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
					error("Du m\u00E5 fylle ut kode og navn for \u00E5 opprette en avdeling");
					return;
				}
			}
		});
		cdBody.add(cdBtnCreate);
		
		JPanel cdFooter = new JPanel();
		cdFooter.setBorder(new EmptyBorder(0, 0, 0, 17));
		cdFooter.setBackground(colorGrayLight);
		GridBagConstraints gbc_cdFooter = new GridBagConstraints();
		gbc_cdFooter.fill = GridBagConstraints.BOTH;
		gbc_cdFooter.gridx = 0;
		gbc_cdFooter.gridy = 2;
		cardDepartment.add(cdFooter, gbc_cdFooter);
		cdFooter.setLayout(new BorderLayout(0, 0));
		
        departmentList = new JList<Object>();
        departmentList.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        departmentList.setBackground(colorGrayLight);
        departmentList.setFixedCellHeight(fontListLineHeight);
        
        JScrollPane cdFooterScrollPane = new JScrollPane();
        cdFooterScrollPane.setViewportBorder(new MatteBorder(0, 50, 0, 50, (Color) new Color(236, 234, 229)));
        cdFooterScrollPane.setBorder(emptyBorder);
        cdFooter.add(cdFooterScrollPane, BorderLayout.CENTER);
        cdFooterScrollPane.setViewportView(departmentList);
        
        // Add card
     	cards.add(cardDepartment, "cardAvdelinger");
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
        
        // Add card
     	cards.add(cardCourse, "cardKurs");
		//// cardCourse - End ////
		
		//// cardPerson - Start ////
        JPanel cardPerson = new JPanel();
        cardPerson.setBackground(Color.WHITE);
        cardPerson.setBorder(emptyBorder);
        GridBagLayout gbl_cardPerson = new GridBagLayout();
        gbl_cardPerson.columnWidths = new int[] {732};
        gbl_cardPerson.rowHeights = new int[] {100, 150, 150};
        gbl_cardPerson.columnWeights = new double[]{1.0};
        gbl_cardPerson.rowWeights = new double[]{0.0, 0.0, 1.0};
        cardPerson.setLayout(gbl_cardPerson);
        
        JPanel cpHeader = new JPanel();
        cpHeader.setBackground(Color.WHITE);
        GridBagConstraints gbc_cpHeader = new GridBagConstraints();
        gbc_cpHeader.fill = GridBagConstraints.BOTH;
        gbc_cpHeader.gridx = 0;
        gbc_cpHeader.gridy = 0;
        cardPerson.add(cpHeader, gbc_cpHeader);
        cpHeader.setLayout(new BorderLayout(0, 0));
        
        JLabel cpHeaderHeading = new JLabel("Person oversikt");
        cpHeaderHeading.setHorizontalAlignment(SwingConstants.CENTER);
        cpHeaderHeading.setFont(new Font("Segoe UI", Font.PLAIN, 24));
        cpHeaderHeading.setBackground(Color.WHITE);
        cpHeader.add(cpHeaderHeading, BorderLayout.CENTER);
        
        JPanel cpBody = new JPanel();
        cpBody.setBorder(new EmptyBorder(0, 100, 25, 100));
        cpBody.setBackground(Color.WHITE);
        GridBagConstraints gbc_cpBody = new GridBagConstraints();
        gbc_cpBody.fill = GridBagConstraints.BOTH;
        gbc_cpBody.gridx = 0;
        gbc_cpBody.gridy = 1;
        cardPerson.add(cpBody, gbc_cpBody);
        cpBody.setLayout(new GridLayout(5, 3, 50, 15));
        
        JButton cpBtnCreate = new JButton("Videre");
        cpBtnCreate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                // Check that the user filled out fields before trying to create person
                if (cpTfName.getText().length() != 0 && cpTfSurName.getText().length() != 0 && cpTfPhone.getText().length() != 0 && cpCbDepartments.getSelectedIndex() != -1) {
                	// Check if the department exists before trying to create it
                    if (Person.exist(cpTfName.getText(), cpTfSurName.getText(), cpTfPhone.getText())) {
                        error(cpTfName.getText() + " " + cpTfSurName.getText() + " finnes allerede i systemet");
                        return;
                    }
                    
                    Department selectedDepartment = (Department) cpCbDepartments.getSelectedItem();
                    
                    if (cpCbTypes.getSelectedItem().equals("Student")) {
                    	// Hide back button and show course select card
                    	btnTilbake.setVisible(false);
    					CardLayout cardLayout = (CardLayout) cards.getLayout();
    					cardLayout.show(cards, "cardCourseSelect");
    					
    					// for course select card
    					courseSelectList.setListData(Course.toStrings(selectedDepartment.getCode()));    					
    					
    					// Done with course selection
    					ccsBtnDone.addActionListener(new ActionListener() {
    						public void actionPerformed(ActionEvent e) {
    							new Student(cpTfName.getText(), cpTfSurName.getText(), cpTfPhone.getText(), selectedDepartment, new ArrayList(courseSelectList.getSelectedValuesList()), null);

    							cpTfName.setText("");
    	                        cpTfSurName.setText("");
    	                        cpTfPhone.setText("");
    							
    							// Show back button and go back to person card
    	                        btnTilbake.setVisible(true);
    	                        CardLayout cardLayout = (CardLayout) cards.getLayout();
    	    					cardLayout.show(cards, "cardPersoner");
    	    					updateLists();
    						}
    					});
    					
    					// Abort course selection
    					btnAvbryt.addActionListener(new ActionListener() {
    						public void actionPerformed(ActionEvent e) {
    							btnTilbake.setVisible(true);
    	    					CardLayout cardLayout = (CardLayout) cards.getLayout();
    	    					cardLayout.show(cards, "cardPersoner");
    						}
    					});
    					
    					
					} else if (cpCbTypes.getSelectedItem().equals("Admin ansatt")) {
						new AdminEmployee(cpTfName.getText(), cpTfSurName.getText(), cpTfPhone.getText(), selectedDepartment);
						
						cpTfName.setText("");
                        cpTfSurName.setText("");
                        cpTfPhone.setText("");
						
						updateLists();
					} else if (cpCbTypes.getSelectedItem().equals("Akademisk ansatt")) {
						new AcademicEmployee(cpTfName.getText(), cpTfSurName.getText(), cpTfPhone.getText(), selectedDepartment);
						
						cpTfName.setText("");
                        cpTfSurName.setText("");
                        cpTfPhone.setText("");
                        
						updateLists();
					}
                } else {
                    error("Du m\u00E5 velge en avdeling og type og fylle ut alle feltene for \u00E5 opprette en person");
                    return;
                }
            }
        });
        
        JLabel lblFornavn = new JLabel("Fornavn");
        cpBody.add(lblFornavn);
        
        JLabel lblEtternavn = new JLabel("Etternavn");
        cpBody.add(lblEtternavn);
        
        JLabel lblMobil = new JLabel("Mobil");
        cpBody.add(lblMobil);
        
        cpTfName = new JTextField();
        cpBody.add(cpTfName);
        cpTfName.setColumns(10);
        
        cpTfSurName = new JTextField();
        cpBody.add(cpTfSurName);
        cpTfSurName.setColumns(10);
        
        cpTfPhone = new JTextField();
        cpBody.add(cpTfPhone);
        cpTfPhone.setColumns(10);
        
        JLabel lblAvdeling_1 = new JLabel("Avdeling & type");
        lblAvdeling_1.setHorizontalAlignment(SwingConstants.RIGHT);
        cpBody.add(lblAvdeling_1);
        
        cpCbDepartments = new JComboBox();
        cpBody.add(cpCbDepartments);
        
        cpCbTypes = new JComboBox<String>();
        cpCbTypes.setPrototypeDisplayValue("Lorem ipsum dolor");            
        cpCbTypes.addItem("Student");
        cpCbTypes.addItem("Admin ansatt");
        cpCbTypes.addItem("Akademisk ansatt");
        
        cpCbTypes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cpCbTypes.getSelectedItem().equals("Student")) {
					cpBtnCreate.setText("Videre");
				} else {
					cpBtnCreate.setText("Opprett");
				}
			}
		});
        
        cpBody.add(cpCbTypes);
        
        JPanel panel_3 = new JPanel();
        panel_3.setBackground(Color.WHITE);
        cpBody.add(panel_3);
        
        JPanel panel_4 = new JPanel();
        panel_4.setBackground(Color.WHITE);
        cpBody.add(panel_4);
        cpBody.add(cpBtnCreate);
        
        JPanel cpFooter = new JPanel();
        cpFooter.setBorder(new EmptyBorder(0, 0, 0, 17));
        cpFooter.setBackground(colorGrayLight);
        GridBagConstraints gbc_cpFooter = new GridBagConstraints();
        gbc_cpFooter.fill = GridBagConstraints.BOTH;
        gbc_cpFooter.gridx = 0;
        gbc_cpFooter.gridy = 2;
        cardPerson.add(cpFooter, gbc_cpFooter);
        cpFooter.setLayout(new BorderLayout(0, 0));
        
        personList = new JList<Object>();
        personList.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        personList.setBackground(colorGrayLight);
        personList.setFixedCellHeight(fontListLineHeight);
        
        JScrollPane cpFooterScrollPane = new JScrollPane();
        cpFooterScrollPane.setViewportBorder(new MatteBorder(0, 50, 0, 50, colorGrayLight));
        cpFooterScrollPane.setBorder(emptyBorder);
        cpFooter.add(cpFooterScrollPane, BorderLayout.CENTER);
        cpFooterScrollPane.setViewportView(personList);
        
		// Add card
		cards.add(cardPerson, "cardPersoner");
		//// cardPerson - End ////
        
		//// cardCourseSelect - Start ////
        JPanel cardCourseSelect = new JPanel();
        cardCourseSelect.setBackground(Color.WHITE);
        cardCourseSelect.setBorder(emptyBorder);
        GridBagLayout gbl_cardCourseSelect = new GridBagLayout();
        gbl_cardCourseSelect.columnWidths = new int[] {732};
        gbl_cardCourseSelect.rowHeights = new int[] {50, 50, 50, 50, 50};
        gbl_cardCourseSelect.columnWeights = new double[]{1.0};
        gbl_cardCourseSelect.rowWeights = new double[]{0.0, 1.0, 0.0, 0.0};
        cardCourseSelect.setLayout(gbl_cardCourseSelect);
        
        JPanel ccsHeader = new JPanel();
        ccsHeader.setBackground(Color.WHITE);
        GridBagConstraints gbc_ccsHeader = new GridBagConstraints();
        gbc_ccsHeader.fill = GridBagConstraints.BOTH;
        gbc_ccsHeader.gridx = 0;
        gbc_ccsHeader.gridy = 0;
        cardCourseSelect.add(ccsHeader, gbc_ccsHeader);
        ccsHeader.setLayout(new BorderLayout(0, 0));
        
        JLabel ccsHeaderHeading = new JLabel("Velg kurs");
        ccsHeaderHeading.setHorizontalAlignment(SwingConstants.CENTER);
        ccsHeaderHeading.setFont(new Font("Segoe UI", Font.PLAIN, 24));
        ccsHeaderHeading.setBackground(Color.WHITE);
        ccsHeader.add(ccsHeaderHeading, BorderLayout.CENTER);
        
        JPanel ccsBody = new JPanel();
        ccsBody.setBorder(new EmptyBorder(0, 0, 0, 17));
        ccsBody.setBackground(Color.WHITE);
        GridBagConstraints gbc_ccsBody = new GridBagConstraints();
        gbc_ccsBody.gridheight = 2;
        gbc_ccsBody.fill = GridBagConstraints.BOTH;
        gbc_ccsBody.gridx = 0;
        gbc_ccsBody.gridy = 1;
        cardCourseSelect.add(ccsBody, gbc_ccsBody);
        ccsBody.setLayout(new BorderLayout(0, 0));
        
        courseSelectList = new JList<Object>();

        // Override default selection mode, so user can easily select multiple courses without any confusuion
        courseSelectList.setSelectionModel(new DefaultListSelectionModel() {
            @Override
            public void setSelectionInterval(int index0, int index1) {
                if(super.isSelectedIndex(index0)) {
                    super.removeSelectionInterval(index0, index1);
                }
                else {
                    super.addSelectionInterval(index0, index1);
                }
            }
        });

        courseSelectList.setBorder(null);
        courseSelectList.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        courseSelectList.setBackground(colorGrayLight);
        courseSelectList.setFixedCellHeight(fontListLineHeight);
        
        JScrollPane ccsFooterScrollPane = new JScrollPane();
        ccsBody.add(ccsFooterScrollPane);
        ccsFooterScrollPane.setViewportBorder(new MatteBorder(0, 50, 0, 50, colorGrayLight));
        ccsFooterScrollPane.setBorder(emptyBorder);
        ccsFooterScrollPane.setViewportView(courseSelectList);
        
		JPanel ccsFooter = new JPanel();
		ccsFooter.setBorder(new EmptyBorder(10, 100, 10, 100));
		ccsFooter.setBackground(Color.WHITE);
		GridBagConstraints gbc_ccsFooter = new GridBagConstraints();
		gbc_ccsFooter.fill = GridBagConstraints.BOTH;
		gbc_ccsFooter.gridx = 0;
		gbc_ccsFooter.gridy = 3;
		cardCourseSelect.add(ccsFooter, gbc_ccsFooter);
		ccsFooter.setLayout(new BorderLayout(0, 0));
		
		ccsBtnDone.setForeground(colorWhite);
		ccsBtnDone.setBackground(colorSuccess);
		ccsFooter.add(ccsBtnDone, BorderLayout.EAST);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBackground(Color.WHITE);
		ccsFooter.add(panel_6);
		
		btnAvbryt.setForeground(colorWhite);
		btnAvbryt.setBackground(colorAlert);
		ccsFooter.add(btnAvbryt, BorderLayout.WEST);
		
		// Add card
		cards.add(cardCourseSelect, "cardCourseSelect");
		//// cardCourseSelect - End ////
		
		updateLists();
	}

	public void updateLists() {
		// for departments card
		departmentList.setListData(Department.toStrings());
		
		// for course card
		courseList.setListData(Course.toStrings());
		ccCbDepartments.removeAllItems();
		for (int i = 0; i < Department.getDepartments().size(); i++) {			
			ccCbDepartments.addItem(Department.getDepartments().get(i));
		}
	
		// for person card
		cpCbDepartments.setModel(ccCbDepartments.getModel());
		personList.setListData(Person.toStrings());
	}
	
	public void error(String errorMessage) {
		JOptionPane.showMessageDialog(this, errorMessage);
	}
}
