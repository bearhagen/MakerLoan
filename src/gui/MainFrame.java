package gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
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
import java.lang.reflect.Array;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.AbstractListModel;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.DefaultListSelectionModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.event.ListDataListener;
import javax.swing.plaf.ColorUIResource;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.List;

import net.miginfocom.swing.MigLayout;

import gui.Button;
import gui.branding.*;

import administration.Course;
import administration.Department;

import hr.AcademicEmployee;
import hr.AdminEmployee;
import hr.Person;
import hr.Student;
import inventory.Item;
import inventory.Loan;
import javax.swing.JButton;

public class MainFrame extends JFrame {

	private JPanel contentPane;

	// Loan card
	private JList<Object> loanList;
	private JTextField clTfDate;
	private JLabel clHeaderHeading;
	private JComboBox<Item> clCbItem;
	private JComboBox<Person> clCbPerson;
	private JComboBox<String> clCbSort;
	
	// Item card
	private JList<Object> itemList;
	private JTextField ciTfName;
	private JTextField ciTfCode;
	private JLabel ciHeaderHeading;

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
	private Button ccsBtnDone = new Button("Ferdig");
	private Button ccsBtnCancel = new Button("Avbryt");


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
		int fontListLineHeight = 40;
		int fontRegularSize    = 15;
		int fontHeadingsSize   = 24;
		Font fontHeadings      = new Font("Segoe UI", Font.PLAIN, fontHeadingsSize);
		Font fontRegular       = new Font("Segoe UI", Font.PLAIN, fontRegularSize);
		Font fontRegularBold   = new Font("Segoe UI BLACK", Font.PLAIN, fontRegularSize);
		
		ImageIcon appIcon      = new ImageIcon(getClass().getClassLoader().getResource("resources/hiof-branding/emblem-512.png"));
		
		Border emptyBorder     = BorderFactory.createEmptyBorder();
		
		Insets insets = new Insets(0, 0, 0, 0);
		
		UIManager.put("ComboBox.background", new ColorUIResource(UIManager.getColor("TextField.background")));
		UIManager.put("ComboBox.foreground", new ColorUIResource(UIManager.getColor("TextField.foreground")));
		UIManager.put("ComboBox.selectionBackground", new ColorUIResource(Colors.INFO));
		UIManager.put("ComboBox.selectionForeground", new ColorUIResource(Colors.WHITE));
		
		setIconImage(appIcon.getImage());
		setTitle("MakerLoan - Hi\u00D8");
		setBackground(Colors.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 500);
		setMinimumSize(new Dimension(500, 400));
		
		contentPane = new JPanel();
		contentPane.setBackground(Colors.WHITE);
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// Back button
		Button btnBackToMainCard = new Button("Tilbake");
		btnBackToMainCard.setForeground(Colors.BLACK);
		btnBackToMainCard.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		btnBackToMainCard.setFocusPainted(false);
		btnBackToMainCard.setBackground(Colors.GRAY_LIGHT);
		btnBackToMainCard.setBounds(25, 25, 100, 30);
		btnBackToMainCard.setVisible(false);
		contentPane.add(btnBackToMainCard);
		
		// Setup main panel
		JPanel cards = new JPanel();
		cards.setBorder(new EmptyBorder(0, 0, 0, 0));
		cards.setBounds(0, 0, 732, 453);
		contentPane.add(cards);
		cards.setLayout(new CardLayout(0, 0));
		
		// Back button extra
		// Must be under cards
		btnBackToMainCard.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				CardLayout cardLayout = (CardLayout) cards.getLayout();
				cardLayout.show(cards, "cardMain");
				btnBackToMainCard.setVisible(false);
			}
		});

		// So the absolute positioned cards panel will resize on window resize
		addComponentListener(new ComponentAdapter() {
		    public void componentResized(ComponentEvent e) {
		        cards.setBounds(0, 0, getWidth(), getHeight());           
		    }
		});
		
		// So the absolute positioned cards panel will resize on window maximize
		addWindowStateListener(new WindowStateListener() {
			@Override
			public void windowStateChanged(WindowEvent e) {
				cards.setBounds(0, 0, getWidth(), getHeight());
			}
		});
		
		//// cardMain - Start ////
		JPanel cardMain = new JPanel();
		cardMain.setBackground(Colors.WHITE);
		cardMain.setLayout(new MigLayout("insets 0", "[::100%,grow,center]", "[75px:n,grow 20][200px:n,grow 40][100px:n,grow 40]"));
		
		JPanel cmHeader = new JPanel();
		cardMain.add(cmHeader, "cell 0 0,grow");
		cmHeader.setBackground(Colors.WHITE);
		cmHeader.setLayout(new BorderLayout(0, 0));
		
		JLabel c1HeaderHeading = new JLabel("Maker Loan");
		cmHeader.add(c1HeaderHeading, BorderLayout.CENTER);
		c1HeaderHeading.setBackground(Colors.WHITE);
		c1HeaderHeading.setHorizontalAlignment(SwingConstants.CENTER);
		c1HeaderHeading.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		
		JPanel cmBody = new JPanel();
		cardMain.add(cmBody, "cell 0 1,grow");
		cmBody.setBorder(new EmptyBorder(35, 100, 35, 100));
		cmBody.setBackground(Colors.WHITE);
		cmBody.setLayout(new GridLayout(2, 3, 50, 25));
		
		// mainCard buttons
		String[] buttons = {"L\u00E5n", "Artikler", "Personer", "Kurs", "Avdelinger"};
		for (int i = 0; i < buttons.length; i++) {
			String btnText = buttons[i];
			Button btnTmp = new Button(buttons[i]);
			
			btnTmp.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					CardLayout cardLayout = (CardLayout) cards.getLayout();
					cardLayout.show(cards, "card" + btnText);
					btnBackToMainCard.setVisible(true);
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
		cmFooter.setBackground(Colors.WHITE);
		
		JLabel c1FooterImage = new JLabel("");
		c1FooterImage.setVerticalAlignment(SwingConstants.BOTTOM);
		c1FooterImage.setBackground(Colors.WHITE);
		c1FooterImage.setIcon(new ImageIcon(getClass().getClassLoader().getResource("resources/hiof-branding/monster-gra.png")));
		cmFooter.add(c1FooterImage);
		
		// Add card
     	cards.add(cardMain, "cardMain");
		//// cardMain - End ////
     	
        JPanel cardLoan = new JPanel();
        cardLoan.setBackground(Colors.WHITE);
        cardLoan.setBorder(emptyBorder);
        GridBagLayout cardLoansGbLayout = new GridBagLayout();
        cardLoansGbLayout.columnWidths = new int[] {getWidth()};
        cardLoansGbLayout.rowHeights = new int[] {100, 150, 200};
        cardLoansGbLayout.columnWeights = new double[]{1.0};
        cardLoansGbLayout.rowWeights = new double[]{0.0, 0.0, 1.0};
        cardLoan.setLayout(cardLoansGbLayout);
        
        JPanel clHeader = new JPanel();
        clHeader.setBackground(Colors.WHITE);
        GridBagConstraints gbc_clHeader = new GridBagConstraints();
        gbc_clHeader.fill = GridBagConstraints.BOTH;
        gbc_clHeader.insets = insets;
        gbc_clHeader.gridx = 0;
        gbc_clHeader.gridy = 0;
        cardLoan.add(clHeader, gbc_clHeader);
        clHeader.setLayout(new BorderLayout(0, 0));
        
        clHeaderHeading = new JLabel("Lån oversikt");
        clHeaderHeading.setHorizontalAlignment(SwingConstants.CENTER);
        clHeaderHeading.setFont(new Font("Segoe UI", Font.PLAIN, 24));
        clHeaderHeading.setBackground(Colors.WHITE);
        clHeader.add(clHeaderHeading, BorderLayout.CENTER);
        
        JPanel clBody = new JPanel();
        clBody.setBorder(new EmptyBorder(0, 100, 25, 100));
        clBody.setBackground(Colors.WHITE);
        GridBagConstraints gbc_clBody = new GridBagConstraints();
        gbc_clBody.fill = GridBagConstraints.BOTH;
        gbc_clBody.insets = insets;
        gbc_clBody.gridx = 0;
        gbc_clBody.gridy = 1;
        cardLoan.add(clBody, gbc_clBody);
        clBody.setLayout(new GridLayout(0, 3, 50, 15));
        
        JLabel lblPerson_1 = new JLabel("Person");
        clBody.add(lblPerson_1);
        
        JLabel lblTing = new JLabel("Artikel");
        clBody.add(lblTing);
        
        JLabel lblPerson = new JLabel("Dato (yyyy-mm-dd)");
        lblPerson.setToolTipText("");
        clBody.add(lblPerson);
        
        clCbPerson = new JComboBox();
        clBody.add(clCbPerson);
        
        clCbItem = new JComboBox();
        clBody.add(clCbItem);
        
        clTfDate = new JTextField();
        clBody.add(clTfDate);
        clTfDate.setText(new SimpleDateFormat("yyyy-MM-dd").format(new GregorianCalendar().getTime()));
        clTfDate.setColumns(10);
        
        JPanel panel = new JPanel();
        panel.setBackground(Colors.WHITE);
        clBody.add(panel);
        
        Button clBtnCreate = new Button("Opprett");
        clBtnCreate.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent arg0) {
            // Check that the user filled out fields before trying to create loan
            if (clCbPerson.getSelectedIndex() != -1 && clCbItem.getSelectedIndex() != -1 && clTfDate.getText().trim().length() != 0) {
              GregorianCalendar date = new GregorianCalendar();
              try {
				date.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(clTfDate.getText().trim()));
			} catch (ParseException e) {
				error("Noe gikk feil n\u00E5r vi pr\u00f8vde \u00E5 lese datoen. Har du skrevet inn på riktig format? (\u00E5r-m\u00E5ned-dag)");
			}
            	
              // Everything is ok, create the loan
              new Loan((Person) clCbPerson.getSelectedItem(), (Item) clCbItem.getSelectedItem(), date);
              updateLists();
            } else {
              error("Du m\u00E5 fylle ut kode og navn for \u00E5 opprette et l\u00E5n");
              return;
            }
          }
        });
        
        JPanel panel_5 = new JPanel();
        panel_5.setBackground(Color.WHITE);
        clBody.add(panel_5);
        clBody.add(clBtnCreate);
        
        JPanel clFooter = new JPanel();
        clFooter.setBorder(new EmptyBorder(0, 0, 0, 17));
        clFooter.setBackground(Colors.GRAY_LIGHT);
        GridBagConstraints gbc_clFooter = new GridBagConstraints();
        gbc_clFooter.fill = GridBagConstraints.BOTH;
        gbc_clFooter.gridx = 0;
        gbc_clFooter.gridy = 2;
        cardLoan.add(clFooter, gbc_clFooter);
        GridBagLayout gbl_clFooter = new GridBagLayout();
        gbl_clFooter.columnWidths = new int[] {75, 25};
        gbl_clFooter.rowHeights = new int[] {10, 90};
        gbl_clFooter.columnWeights = new double[]{1.0, 0.0};
        gbl_clFooter.rowWeights = new double[]{0.0, 1.0};
        clFooter.setLayout(gbl_clFooter);
        
        clCbSort = new JComboBox<>();
        GridBagConstraints gbc_clCbSort = new GridBagConstraints();
        gbc_clCbSort.anchor = GridBagConstraints.NORTH;
        gbc_clCbSort.fill = GridBagConstraints.HORIZONTAL;
        gbc_clCbSort.gridx = 0;
        gbc_clCbSort.gridy = 0;
        clFooter.add(clCbSort, gbc_clCbSort);
        clCbSort.addItem("Dato - Nyeste først");
        clCbSort.addItem("Dato - Eldste først");
        clCbSort.addItem("Etternavn A - Å");
        clCbSort.addItem("Etternavn Å - A");
        clCbSort.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		updateLists();
        	}
        });
        
        loanList = new JList<Object>();
        loanList.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        loanList.setBackground(Colors.GRAY_LIGHT);
        loanList.setFixedCellHeight(fontListLineHeight);
        loanList.setSelectionForeground(Colors.WHITE);
        loanList.setSelectionBackground(Colors.INFO);
        
        Button clBtnDelete = new Button("Slett valgt l\u00E5n");
        GridBagConstraints gbc_clBtnDelete = new GridBagConstraints();
        gbc_clBtnDelete.ipadx = 15;
        gbc_clBtnDelete.fill = GridBagConstraints.BOTH;
        gbc_clBtnDelete.gridx = 1;
        gbc_clBtnDelete.gridy = 0;
        clBtnDelete.setBackground(Colors.ALERT);
        clFooter.add(clBtnDelete, gbc_clBtnDelete);
        clBtnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
	        	int[] selected = loanList.getSelectedIndices();

				for (int i = 0; i < selected.length; i++) {
					Loan.getLoans().remove(selected[i]);
				}
				
				updateLists();
			}
		});
        
        JScrollPane clFooterScrollPane = new JScrollPane();
        clFooterScrollPane.setViewportBorder(new MatteBorder(0, 50, 0, 50, (Color) new Color(236, 234, 229)));
        clFooterScrollPane.setBorder(emptyBorder);
        GridBagConstraints gbc_clFooterScrollPane = new GridBagConstraints();
        gbc_clFooterScrollPane.gridwidth = 2;
        gbc_clFooterScrollPane.fill = GridBagConstraints.BOTH;
        gbc_clFooterScrollPane.gridx = 0;
        gbc_clFooterScrollPane.gridy = 1;
        clFooter.add(clFooterScrollPane, gbc_clFooterScrollPane);
        clFooterScrollPane.setViewportView(loanList);
        
        // Add card
        cards.add(cardLoan, "cardLån");
        //// cardLoan - End ////
        
        JPanel cardItem = new JPanel();
        cardItem.setBackground(Colors.WHITE);
        cardItem.setBorder(emptyBorder);
        GridBagLayout cardItemsGbLayout = new GridBagLayout();
        cardItemsGbLayout.columnWidths = new int[] {getWidth()};
        cardItemsGbLayout.rowHeights = new int[] {100, 150, 200};
        cardItemsGbLayout.columnWeights = new double[]{1.0};
        cardItemsGbLayout.rowWeights = new double[]{0.0, 0.0, 1.0};
        cardItem.setLayout(cardItemsGbLayout);
        
        JPanel ciHeader = new JPanel();
        ciHeader.setBackground(Colors.WHITE);
        GridBagConstraints gbc_ciHeader = new GridBagConstraints();
        gbc_ciHeader.fill = GridBagConstraints.BOTH;
        gbc_ciHeader.insets = insets;
        gbc_ciHeader.gridx = 0;
        gbc_ciHeader.gridy = 0;
        cardItem.add(ciHeader, gbc_ciHeader);
        ciHeader.setLayout(new BorderLayout(0, 0));
        
        ciHeaderHeading = new JLabel("Artikler oversikt");
        ciHeaderHeading.setHorizontalAlignment(SwingConstants.CENTER);
        ciHeaderHeading.setFont(new Font("Segoe UI", Font.PLAIN, 24));
        ciHeaderHeading.setBackground(Colors.WHITE);
        ciHeader.add(ciHeaderHeading, BorderLayout.CENTER);
        
        JPanel ciBody = new JPanel();
        ciBody.setBorder(new EmptyBorder(0, 100, 25, 100));
        ciBody.setBackground(Colors.WHITE);
        GridBagConstraints gbc_ciBody = new GridBagConstraints();
        gbc_ciBody.fill = GridBagConstraints.BOTH;
        gbc_ciBody.insets = insets;
        gbc_ciBody.gridx = 0;
        gbc_ciBody.gridy = 1;
        cardItem.add(ciBody, gbc_ciBody);
        ciBody.setLayout(new GridLayout(0, 2, 50, 15));
        
        JLabel lblCiTing = new JLabel("ID");
        ciBody.add(lblCiTing);
        
        JLabel lblCiPerson = new JLabel("Navn");
        ciBody.add(lblCiPerson);
        
        ciTfCode = new JTextField();
        ciTfCode.setColumns(10);
        ciBody.add(ciTfCode);
        
        ciTfName = new JTextField();
        ciBody.add(ciTfName);
        ciTfName.setColumns(10);
        
        JPanel panelCi1 = new JPanel();
        panelCi1.setBackground(Color.WHITE);
        panel.setBackground(Colors.WHITE);
        ciBody.add(panelCi1);
        
        Button ciBtnCreate = new Button("Opprett");
        ciBtnCreate.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent arg0) {
            // Check that the user filled out fields before trying to create item
            if (ciTfCode.getText().trim().length() != 0 && ciTfName.getText().trim().length() != 0) {
            	
            	// Check that the ID is only numbers
            	if	(!ciTfCode.getText().matches("^[0-9]+$")) {
            		error("En artikel sin ID må kun bestå av tall");
            		return;
            	}
            	
            	// Check if the item exists before trying to create it
            	if (Item.exist(new Integer(ciTfCode.getText().trim()))) {
            		error("Artikel med ID " + ciTfCode.getText().trim() + " finnes allerede");
            		return;
            	}
              
              // Everything is ok, create the item
              new Item(ciTfName.getText().trim(), new Integer(ciTfCode.getText().trim()));
              ciTfName.setText("");
              ciTfCode.setText("");
              updateLists();
            } else {
              error("Du m\u00E5 fylle ut kode og navn for \u00E5 opprette en artikel");
              return;
            }
          }
        });
        ciBody.add(ciBtnCreate);
        
        JPanel ciFooter = new JPanel();
        ciFooter.setBorder(new EmptyBorder(0, 0, 0, 17));
        ciFooter.setBackground(Colors.GRAY_LIGHT);
        GridBagConstraints gbc_ciFooter = new GridBagConstraints();
        gbc_ciFooter.fill = GridBagConstraints.BOTH;
        gbc_ciFooter.gridx = 0;
        gbc_ciFooter.gridy = 2;
        cardItem.add(ciFooter, gbc_ciFooter);
        ciFooter.setLayout(new BorderLayout(0, 0));
        
        itemList = new JList<Object>();
        itemList.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        itemList.setBackground(Colors.GRAY_LIGHT);
        itemList.setFixedCellHeight(fontListLineHeight);
        itemList.setSelectionForeground(Colors.WHITE);
        itemList.setSelectionBackground(Colors.INFO);
        
        JScrollPane ciFooterScrollPane = new JScrollPane();
        ciFooterScrollPane.setViewportBorder(new MatteBorder(0, 50, 0, 50, (Color) new Color(236, 234, 229)));
        ciFooterScrollPane.setBorder(emptyBorder);
        ciFooter.add(ciFooterScrollPane, BorderLayout.CENTER);
        ciFooterScrollPane.setViewportView(itemList);
        
        // Add card
        cards.add(cardItem, "cardArtikler");
        //// cardItem - End ////
		
		//// cardDepartment - Start ////
		JPanel cardDepartment = new JPanel();
		cardDepartment.setBackground(Colors.WHITE);
		cardDepartment.setBorder(emptyBorder);
        GridBagLayout cardDepartmentsGbLayout = new GridBagLayout();
        cardDepartmentsGbLayout.columnWidths = new int[] {getWidth()};
        cardDepartmentsGbLayout.rowHeights = new int[] {100, 150, 200};
        cardDepartmentsGbLayout.columnWeights = new double[]{1.0};
        cardDepartmentsGbLayout.rowWeights = new double[]{0.0, 0.0, 1.0};
		cardDepartment.setLayout(cardDepartmentsGbLayout);
		
		JPanel cdHeader = new JPanel();
		cdHeader.setBackground(Colors.WHITE);
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
		cdHeaderHeading.setBackground(Colors.WHITE);
		cdHeader.add(cdHeaderHeading, BorderLayout.CENTER);
		
		JPanel cdBody = new JPanel();
		cdBody.setBorder(new EmptyBorder(0, 100, 25, 100));
		cdBody.setBackground(Colors.WHITE);
		GridBagConstraints gbc_cdBody = new GridBagConstraints();
		gbc_cdBody.fill = GridBagConstraints.BOTH;
		gbc_cdBody.insets = insets;
		gbc_cdBody.gridx = 0;
		gbc_cdBody.gridy = 1;
		cardDepartment.add(cdBody, gbc_cdBody);
		cdBody.setLayout(new GridLayout(0, 2, 50, 15));
		
		JLabel lblCdKode = new JLabel("Kode");
		cdBody.add(lblCdKode);
		
		JLabel lblCdPerson = new JLabel("Navn");
		cdBody.add(lblCdPerson);
		
		cdTfCode = new JTextField();
		cdTfCode.setColumns(10);
		cdBody.add(cdTfCode);
		
		cdTfName = new JTextField();
		cdBody.add(cdTfName);
		cdTfName.setColumns(10);
		
		JPanel panel2 = new JPanel();
		panel.setBackground(Colors.WHITE);
		cdBody.add(panel2);
		
		Button cdBtnCreate = new Button("Opprett");
		cdBtnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Check that the user filled out fields before trying to create department
				if (cdTfCode.getText().trim().length() != 0 && cdTfName.getText().trim().length() != 0) {
					// Check if the department exists before trying to create it
					if (Department.exist(cdTfCode.getText().trim())) {
						error("Avdeling " + cdTfCode.getText().trim() + " - " + cdTfName.getText().trim() + " finnes allerede");
						return;
					}
					
					// Everything is ok, create the department
					new Department(cdTfName.getText().trim(), cdTfCode.getText().trim());
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
		cdFooter.setBackground(Colors.GRAY_LIGHT);
		GridBagConstraints gbc_cdFooter = new GridBagConstraints();
		gbc_cdFooter.fill = GridBagConstraints.BOTH;
		gbc_cdFooter.gridx = 0;
		gbc_cdFooter.gridy = 2;
		cardDepartment.add(cdFooter, gbc_cdFooter);
		cdFooter.setLayout(new BorderLayout(0, 0));
		
        departmentList = new JList<Object>();
        departmentList.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        departmentList.setBackground(Colors.GRAY_LIGHT);
        departmentList.setFixedCellHeight(fontListLineHeight);
        departmentList.setSelectionForeground(Colors.WHITE);
        departmentList.setSelectionBackground(Colors.INFO);
        
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
        cardCourse.setBackground(Colors.WHITE);
        cardCourse.setBorder(emptyBorder);
        GridBagLayout cardCourseGbLayout = new GridBagLayout();
        cardCourseGbLayout.columnWidths = new int[] {getWidth()};
        cardCourseGbLayout.rowHeights = new int[] {100, 150, 200};
        cardCourseGbLayout.columnWeights = new double[]{1.0};
        cardCourseGbLayout.rowWeights = new double[]{0.0, 0.0, 1.0};
        cardCourse.setLayout(cardCourseGbLayout);
        
        JPanel ccHeader = new JPanel();
        ccHeader.setBackground(Colors.WHITE);
        GridBagConstraints gbc_ccHeader = new GridBagConstraints();
        gbc_ccHeader.fill = GridBagConstraints.BOTH;
        gbc_ccHeader.gridx = 0;
        gbc_ccHeader.gridy = 0;
        cardCourse.add(ccHeader, gbc_ccHeader);
        ccHeader.setLayout(new BorderLayout(0, 0));
        
        ccHeaderHeading = new JLabel("Kurs oversikt");
        ccHeaderHeading.setHorizontalAlignment(SwingConstants.CENTER);
        ccHeaderHeading.setFont(new Font("Segoe UI", Font.PLAIN, 24));
        ccHeaderHeading.setBackground(Colors.WHITE);
        ccHeader.add(ccHeaderHeading, BorderLayout.CENTER);
        
        JPanel ccBody = new JPanel();
        ccBody.setBorder(new EmptyBorder(0, 100, 25, 100));
        ccBody.setBackground(Colors.WHITE);
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
        
        Button ccBtnCreate = new Button("Opprett");
        ccBtnCreate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
				// Check that the user filled out fields before trying to create course
				if (ccTfCode.getText().trim().length() != 0 && ccTfName.getText().trim().length() != 0 && ccCbDepartments.getSelectedIndex() != -1) {
					// Check if the department exists before trying to create it
					if (Course.exist(ccTfCode.getText().trim())) {
						error("Avdeling " + ccTfCode.getText().trim() + " - " + ccTfName.getText().trim() + " finnes allerede");
						return;
					}
					
					// Everything is ok, create the course
					new Course(ccTfName.getText().trim(), ccTfCode.getText().trim(), (Department) ccCbDepartments.getSelectedItem());
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
        panel_2.setBackground(Colors.WHITE);
        ccBody.add(panel_2);
        
        JPanel panel_1 = new JPanel();
        panel_1.setBackground(Colors.WHITE);
        ccBody.add(panel_1);
        ccBody.add(ccBtnCreate);
        panel_2.setBackground(Colors.WHITE);
        ccCbDepartments.setPrototypeDisplayValue("Lorem ipsum dolor");
        panel_1.setBackground(Colors.WHITE);
        
        JPanel ccFooter = new JPanel();
        ccFooter.setBorder(new EmptyBorder(0, 0, 0, 17));
        ccFooter.setBackground(Colors.GRAY_LIGHT);
        GridBagConstraints gbc_ccFooter = new GridBagConstraints();
        gbc_ccFooter.fill = GridBagConstraints.BOTH;
        gbc_ccFooter.gridx = 0;
        gbc_ccFooter.gridy = 2;
        cardCourse.add(ccFooter, gbc_ccFooter);
        ccFooter.setLayout(new BorderLayout(0, 0));
        
        courseList = new JList<Object>();
        courseList.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        courseList.setBackground(Colors.GRAY_LIGHT);
        courseList.setFixedCellHeight(fontListLineHeight);
        courseList.setSelectionForeground(Colors.WHITE);
        courseList.setSelectionBackground(Colors.INFO);
        
        JScrollPane ccFooterScrollPane = new JScrollPane();
        ccFooterScrollPane.setViewportBorder(new MatteBorder(0, 50, 0, 50, Colors.GRAY_LIGHT));
        ccFooterScrollPane.setBorder(emptyBorder);
        ccFooter.add(ccFooterScrollPane, BorderLayout.CENTER);
        ccFooterScrollPane.setViewportView(courseList);
        
        // Add card
     	cards.add(cardCourse, "cardKurs");
		//// cardCourse - End ////
		
		//// cardPerson - Start ////
        JPanel cardPerson = new JPanel();
        cardPerson.setBackground(Colors.WHITE);
        cardPerson.setBorder(emptyBorder);
        GridBagLayout gbl_cardPerson = new GridBagLayout();
        gbl_cardPerson.columnWidths = new int[] {732};
        gbl_cardPerson.rowHeights = new int[] {100, 150, 150};
        gbl_cardPerson.columnWeights = new double[]{1.0};
        gbl_cardPerson.rowWeights = new double[]{0.0, 0.0, 1.0};
        cardPerson.setLayout(gbl_cardPerson);
        
        JPanel cpHeader = new JPanel();
        cpHeader.setBackground(Colors.WHITE);
        GridBagConstraints gbc_cpHeader = new GridBagConstraints();
        gbc_cpHeader.fill = GridBagConstraints.BOTH;
        gbc_cpHeader.gridx = 0;
        gbc_cpHeader.gridy = 0;
        cardPerson.add(cpHeader, gbc_cpHeader);
        cpHeader.setLayout(new BorderLayout(0, 0));
        
        JLabel cpHeaderHeading = new JLabel("Person oversikt");
        cpHeaderHeading.setHorizontalAlignment(SwingConstants.CENTER);
        cpHeaderHeading.setFont(new Font("Segoe UI", Font.PLAIN, 24));
        cpHeaderHeading.setBackground(Colors.WHITE);
        cpHeader.add(cpHeaderHeading, BorderLayout.CENTER);
        
        JPanel cpBody = new JPanel();
        cpBody.setBorder(new EmptyBorder(0, 100, 25, 100));
        cpBody.setBackground(Colors.WHITE);
        GridBagConstraints gbc_cpBody = new GridBagConstraints();
        gbc_cpBody.fill = GridBagConstraints.BOTH;
        gbc_cpBody.gridx = 0;
        gbc_cpBody.gridy = 1;
        cardPerson.add(cpBody, gbc_cpBody);
        cpBody.setLayout(new GridLayout(5, 3, 50, 15));
        
        Button cpBtnCreate = new Button("Videre");
        cpBtnCreate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                // Check that the user filled out fields before trying to create person
                if (cpTfName.getText().trim().length() != 0 && cpTfSurName.getText().trim().length() != 0 && cpTfPhone.getText().trim().length() != 0 && cpCbDepartments.getSelectedIndex() != -1) {
                	// Check if the department exists before trying to create it
                    if (Person.exist(cpTfName.getText().trim(), cpTfSurName.getText().trim(), cpTfPhone.getText().trim())) {
                        error(cpTfName.getText().trim() + " " + cpTfSurName.getText().trim() + " finnes allerede i systemet");
                        return;
                    }
                    
                    Department selectedDepartment = (Department) cpCbDepartments.getSelectedItem();
                    
                    if (cpCbTypes.getSelectedItem().equals("Student")) {
                    	// Hide back button and show course select card
                    	btnBackToMainCard.setVisible(false);
    					CardLayout cardLayout = (CardLayout) cards.getLayout();
    					cardLayout.show(cards, "cardCourseSelect");
    					
    					// for course select card
    					courseSelectList.setListData(Course.toStrings(selectedDepartment));    					
    					
    					// Done with course selection
    					ccsBtnDone.addActionListener(new ActionListener() {
    						public void actionPerformed(ActionEvent e) {
    							new Student(cpTfName.getText().trim(), cpTfSurName.getText().trim(), cpTfPhone.getText().trim(), selectedDepartment, new ArrayList(courseSelectList.getSelectedValuesList()));

    							cpTfName.setText("");
    	                        cpTfSurName.setText("");
    	                        cpTfPhone.setText("");
    							
    							// Show back button and go back to person card
    	                        btnBackToMainCard.setVisible(true);
    	                        CardLayout cardLayout = (CardLayout) cards.getLayout();
    	    					cardLayout.show(cards, "cardPersoner");
    	    					updateLists();
    						}
    					});
    					
    					// Abort course selection
    					ccsBtnCancel.addActionListener(new ActionListener() {
    						public void actionPerformed(ActionEvent e) {
    							btnBackToMainCard.setVisible(true);
    	    					CardLayout cardLayout = (CardLayout) cards.getLayout();
    	    					cardLayout.show(cards, "cardPersoner");
    						}
    					});
    					
    					
					} else if (cpCbTypes.getSelectedItem().equals("Admin ansatt")) {
						new AdminEmployee(cpTfName.getText().trim(), cpTfSurName.getText().trim(), cpTfPhone.getText().trim(), selectedDepartment);
						
						cpTfName.setText("");
                        cpTfSurName.setText("");
                        cpTfPhone.setText("");
						
						updateLists();
					} else if (cpCbTypes.getSelectedItem().equals("Akademisk ansatt")) {
						new AcademicEmployee(cpTfName.getText().trim(), cpTfSurName.getText().trim(), cpTfPhone.getText().trim(), selectedDepartment);
						
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
        panel_3.setBackground(Colors.WHITE);
        cpBody.add(panel_3);
        
        JPanel panel_4 = new JPanel();
        panel_4.setBackground(Colors.WHITE);
        cpBody.add(panel_4);
        cpBody.add(cpBtnCreate);
        
        JPanel cpFooter = new JPanel();
        cpFooter.setBorder(new EmptyBorder(0, 0, 0, 17));
        cpFooter.setBackground(Colors.GRAY_LIGHT);
        GridBagConstraints gbc_cpFooter = new GridBagConstraints();
        gbc_cpFooter.fill = GridBagConstraints.BOTH;
        gbc_cpFooter.gridx = 0;
        gbc_cpFooter.gridy = 2;
        cardPerson.add(cpFooter, gbc_cpFooter);
        cpFooter.setLayout(new BorderLayout(0, 0));
        
        personList = new JList<Object>();
        personList.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        personList.setBackground(Colors.GRAY_LIGHT);
        personList.setFixedCellHeight(fontListLineHeight);
        personList.setSelectionForeground(Colors.WHITE);
        personList.setSelectionBackground(Colors.INFO);
        
        JScrollPane cpFooterScrollPane = new JScrollPane();
        cpFooterScrollPane.setViewportBorder(new MatteBorder(0, 50, 0, 50, Colors.GRAY_LIGHT));
        cpFooterScrollPane.setBorder(emptyBorder);
        cpFooter.add(cpFooterScrollPane, BorderLayout.CENTER);
        cpFooterScrollPane.setViewportView(personList);
        
		// Add card
		cards.add(cardPerson, "cardPersoner");
		//// cardPerson - End ////
        
		//// cardCourseSelect - Start ////
        JPanel cardCourseSelect = new JPanel();
        cardCourseSelect.setBackground(Colors.WHITE);
        cardCourseSelect.setBorder(emptyBorder);
        GridBagLayout gbl_cardCourseSelect = new GridBagLayout();
        gbl_cardCourseSelect.columnWidths = new int[] {732};
        gbl_cardCourseSelect.rowHeights = new int[] {50, 50, 50, 50, 50};
        gbl_cardCourseSelect.columnWeights = new double[]{1.0};
        gbl_cardCourseSelect.rowWeights = new double[]{0.0, 1.0, 0.0, 0.0};
        cardCourseSelect.setLayout(gbl_cardCourseSelect);
        
        JPanel ccsHeader = new JPanel();
        ccsHeader.setBackground(Colors.WHITE);
        GridBagConstraints gbc_ccsHeader = new GridBagConstraints();
        gbc_ccsHeader.fill = GridBagConstraints.BOTH;
        gbc_ccsHeader.gridx = 0;
        gbc_ccsHeader.gridy = 0;
        cardCourseSelect.add(ccsHeader, gbc_ccsHeader);
        ccsHeader.setLayout(new BorderLayout(0, 0));
        
        JLabel ccsHeaderHeading = new JLabel("Velg kurs");
        ccsHeaderHeading.setHorizontalAlignment(SwingConstants.CENTER);
        ccsHeaderHeading.setFont(new Font("Segoe UI", Font.PLAIN, 24));
        ccsHeaderHeading.setBackground(Colors.WHITE);
        ccsHeader.add(ccsHeaderHeading, BorderLayout.CENTER);
        
        JPanel ccsBody = new JPanel();
        ccsBody.setBorder(new EmptyBorder(0, 0, 0, 17));
        ccsBody.setBackground(Colors.WHITE);
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
        courseSelectList.setBackground(Colors.GRAY_LIGHT);
        courseSelectList.setFixedCellHeight(fontListLineHeight);
        courseSelectList.setSelectionForeground(Colors.WHITE);
        courseSelectList.setSelectionBackground(Colors.INFO);
        
        JScrollPane ccsFooterScrollPane = new JScrollPane();
        ccsBody.add(ccsFooterScrollPane);
        ccsFooterScrollPane.setViewportBorder(new MatteBorder(0, 50, 0, 50, Colors.GRAY_LIGHT));
        ccsFooterScrollPane.setBorder(emptyBorder);
        ccsFooterScrollPane.setViewportView(courseSelectList);
        
		JPanel ccsFooter = new JPanel();
		ccsFooter.setBorder(new EmptyBorder(10, 100, 10, 100));
		ccsFooter.setBackground(Colors.WHITE);
		GridBagConstraints gbc_ccsFooter = new GridBagConstraints();
		gbc_ccsFooter.fill = GridBagConstraints.BOTH;
		gbc_ccsFooter.gridx = 0;
		gbc_ccsFooter.gridy = 3;
		cardCourseSelect.add(ccsFooter, gbc_ccsFooter);
		ccsFooter.setLayout(new GridLayout(1, 3, 50, 0));
		
		ccsBtnCancel.setBackground(Colors.ALERT);
		ccsFooter.add(ccsBtnCancel);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBackground(Colors.WHITE);
		ccsFooter.add(panel_6);
		
		ccsBtnDone.setBackground(Colors.SUCCESS);
		ccsFooter.add(ccsBtnDone);
		
		// Add card
		cards.add(cardCourseSelect, "cardCourseSelect");
		//// cardCourseSelect - End ////
		
		updateLists();
	}

	public void updateLists() {
		// Loans coard
		switch ((String) clCbSort.getSelectedItem()) {
			case "Dato - Eldste først":
				break;
				
			case "Etternavn A - Å":
				Loan.getLoans().sort(new Comparator<Loan>() {
				    @Override
				    public int compare(Loan s1, Loan s2) {
				        return s1.toString().substring(s1.toString().lastIndexOf(" ")+1).compareTo(s2.toString().substring(s2.toString().lastIndexOf(" ")+1));
				    }
				});
				break;
				
			case "Etternavn Å - A":
				Loan.getLoans().sort(new Comparator<Loan>() {
				    @Override
				    public int compare(Loan s1, Loan s2) {
				        return s2.toString().substring(s2.toString().lastIndexOf(" ")+1).compareTo(s1.toString().substring(s1.toString().lastIndexOf(" ")+1));
				    }
				});
				break;

			default:
				Collections.reverse(Loan.getLoans());
				break;
		}
		loanList.setListData(Loan.getLoans().toArray());
		
		clCbPerson.removeAllItems();
		for (int i = 0; i < Person.getPersons().size(); i++) {
			clCbPerson.addItem(Person.getPersons().get(i));
		}
		
		clCbItem.removeAllItems();
		for (int i = 0; i < Item.getItems().size(); i++) {
			clCbItem.addItem(Item.getItems().get(i));
		}
		
		// Items card
		itemList.setListData(Item.toStrings());

		// Departments card
		departmentList.setListData(Department.toStrings());
		
		// Course card
		courseList.setListData(Course.toStrings());
		ccCbDepartments.removeAllItems();
		for (int i = 0; i < Department.getDepartments().size(); i++) {			
			ccCbDepartments.addItem(Department.getDepartments().get(i));
		}
	
		// Person card
		cpCbDepartments.setModel(ccCbDepartments.getModel());
		personList.setListData(Person.toStrings());
	}
	
	public void error(String errorMessage) {
		JOptionPane.showMessageDialog(this, errorMessage);
	}
}
