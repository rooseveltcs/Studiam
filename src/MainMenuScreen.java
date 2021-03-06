/**
 * MainMenuScreen.java
 *
 * The Main {@link Screen}. 'Nuff said
 *
 * @version 09/18/18
 */

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MainMenuScreen extends Screen {

    public MainMenuScreen() {
        screenId = "MainMenu";
        setLayout(new BorderLayout());

        JPanel mainPanel = StudiamFactory.newTransparentPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        add(new JLabel(new ImageIcon("img/screenshot.png")), BorderLayout.NORTH);

        mainPanel.add(newButton());
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        mainPanel.add(openButton());
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        //mainPanel.add(importButton());
        mainPanel.add(settingsButton());
        mainPanel.add(Box.createRigidArea(new Dimension(0, 45)));

        add(mainPanel, BorderLayout.CENTER);

        JPanel recentFiles = recentFiles();
        if (recentFiles != null) {
            add(recentFiles, BorderLayout.SOUTH);
        }
    }

    //method for the button to create a new quiz file
    public JButton newButton() {
        JButton newButton = StudiamFactory.newStudiamButton(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.newFile();
            }
        });
        newButton.setText("New...");
        newButton.setToolTipText("Create a new quiz file from scratch");
        return newButton;
    }

    //method for the button to open a quiz file
    public JButton openButton() {
        JButton openButton = StudiamFactory.newStudiamButton(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.chooseOpenFile();
            }
        });
        openButton.setText("Open...");
        openButton.setToolTipText("Open a .studiam quiz file");
        return openButton;
    }

    //method for the button to import a quiz file
    public JButton importButton() {
        JButton importButton = StudiamFactory.newStudiamButton(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        importButton.setText("Import...");
        importButton.setToolTipText("Import from quizlet or some shit idk");
        return importButton;
    }

    //method for the button to switch to the settings screen
    public JButton settingsButton() {
        JButton newButton = StudiamFactory.newStudiamButton(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.switchScreen(new SettingsScreen());
            }
        });
        newButton.setText("Settings");
        newButton.setToolTipText("Customize your Studiam experience");
        return newButton;
    }

    //method for the panel of recent files at the bottom of the screen
    public JPanel recentFiles() {
        JPanel recentFiles = StudiamFactory.newTransparentPanel();
        recentFiles.setLayout(new BoxLayout(recentFiles, BoxLayout.Y_AXIS));
        recentFiles.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.BLACK), "Recent Files", TitledBorder.TOP,
                TitledBorder.CENTER, new Font("Times New Roman", Font.BOLD, 25), Color.BLACK));

        Scanner input;
        try {
            input = new Scanner(new File("recent"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }

        int i = 0;
        while (input.hasNextLine() && i < 5) {
            String line = input.nextLine();
            JButton fileButton = new JButton();
            fileButton.setAction(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Main.openFile(new File(line));
                }
            });
            fileButton.setBackground(Main.LESS_PURPLE);
            fileButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
            fileButton.setForeground(Color.BLACK);
            fileButton.setBorder(BorderFactory.createRaisedBevelBorder());
            fileButton.setText(line);
            fileButton.setAlignmentX(CENTER_ALIGNMENT);
            fileButton.setAlignmentY(BOTTOM_ALIGNMENT);
            fileButton.setMaximumSize(new Dimension(2000, 25));
            recentFiles.add(fileButton);
            i++;
        }

        return recentFiles;
    }




}
