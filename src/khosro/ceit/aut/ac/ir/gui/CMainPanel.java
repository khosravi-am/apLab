package khosro.ceit.aut.ac.ir.gui;

import khosro.ceit.aut.ac.ir.utils.FileUtils;
import khosro.model.Note;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;


/**
 * main part of not book.
 * contains directory list and text area for writing.
 */
public class CMainPanel extends JPanel {

    private JTabbedPane tabbedPane;
    private JList<File> directoryList;
    private HashMap<String,Note> notes;

    /**
     * makes the panel.
     */
    public CMainPanel() {

        notes = new HashMap<>();
        loadNotes();
        setLayout(new BorderLayout());

        initDirectoryList(); // add JList to main Panel

        initTabbedPane(); // add TabbedPane to main panel

        addNewTab(); // open new empty tab when user open the application
    }


    /**
     * makes the empty tabbed pan .
     */
    private void initTabbedPane() {
        tabbedPane = new JTabbedPane();
        add(tabbedPane, BorderLayout.CENTER);
    }


    /**
     * makes and evaluate the value of list by files of directory.
     */
    private void initDirectoryList() {
        File[] files = FileUtils.getFilesInDirectory();
        directoryList = new JList<>(files);

        directoryList.setBackground(new Color(211, 211, 211));
        directoryList.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        directoryList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        directoryList.setVisibleRowCount(-1);
        directoryList.setMaximumSize(new Dimension(250, 100));
        directoryList.setCellRenderer(new MyCellRenderer());
        directoryList.addMouseListener(new MyMouseAdapter());

        add(new JScrollPane(directoryList), BorderLayout.WEST);
    }


    /**
     * add a new tab to tabbed panel.
     */
    public void addNewTab() {
        Note note = new Note("new title " + (notes.keySet().size() + 1),"Write  Something here...", new SimpleDateFormat("yyyy.MM.dd : hh.mm").format(new Date()));
        JTextArea textPanel = createTextPanel();
        textPanel.setText(note.getContent());
        tabbedPane.addTab(note.getTitle(), textPanel);
        notes.put(note.getTitle(),note);
    }

    /**
     * add a tab to tabbed pan depends of value of given note
     * @param note note to be added to tabbed pan
     */
    public void openExistingNote(Note note) {
        JTextArea existPanel = createTextPanel();
        existPanel.setText(note.getContent());
        int tabIndex = tabbedPane.getTabCount() + 1;
        tabbedPane.addTab(note.getTitle(), existPanel);
        tabbedPane.setSelectedIndex(tabIndex - 1);
    }


    /**
     * save a specific note.
     * if note title starts with "new title" , would ask the user to change to name.
     */
    public void saveNote() {
        String title = tabbedPane.getTitleAt(tabbedPane.getSelectedIndex());
            try {
                JTextArea textArea = (JTextArea)tabbedPane.getComponent(tabbedPane.getSelectedIndex());
                Note note = notes.get(tabbedPane.getTitleAt(tabbedPane.getSelectedIndex()));
                if(title.startsWith("new title ")) {
                    notes.remove(title);
                    String newtitle = JOptionPane.showInputDialog("enter title for this file");
                    if(newtitle != null) {
                        for(File file : FileUtils.getFilesInDirectory())
                            if(file.getName().equals(title + ".bin"))
                                file.delete();
                        title = newtitle;
                        tabbedPane.setTitleAt(tabbedPane.getSelectedIndex(),title);
//                        tabbedPane.updateUI();
                        note.setTitle(title);
                        notes.put(title, note);
                    }
                }
                note.setContent(textArea.getText());
                FileUtils.writingOutputObj(notes.get(title),title + ".bin");
            } catch (IOException e) {
                e.printStackTrace();
            }
        updateListGUI();
    }


    /**
     * save all notes in tabbed pan
     * use txt format.
     */
    public void saveAllTab()
    {
        for(Component  component : tabbedPane.getComponents()) {
            JTextArea textArea = (JTextArea) component;
            String note = textArea.getText();
            if (!note.isEmpty()) {
                try {
                    FileUtils.fileWriter(note);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        updateListGUI();
    }


    /**
     * save all tabs.
     * use bin format
     */
    public void saveAllTabN()
    {
        for(int i=0; i<tabbedPane.getTabCount() ; i++)
        {
            JTextArea textArea = (JTextArea)tabbedPane.getComponent(i);
            Note note = notes.get(tabbedPane.getTitleAt(i));
            note.setContent(textArea.getText());
            try {
                FileUtils.writingOutputObj(note,note.getTitle() + ".bin");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        updateListGUI();
    }


    private void loadNotes()
    {
        File[] files = FileUtils.getFilesInDirectory();
        for(File file : files)
            if(file.getName().endsWith(".bin"))
            {
                Note newNote = null;
                try {
                    newNote = FileUtils.readingInputObj(file.getPath());
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                notes.put(newNote.getTitle(),newNote);
            }
    }


    /**
     * creat the text panel for notes.
     * @return text panel
     */
    private JTextArea createTextPanel() {
        JTextArea textPanel = new JTextArea();
        textPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        return textPanel;
    }

    /**
     * update the list depends on directory.
     */
    private void updateListGUI() {
        File[] newFiles = FileUtils.getFilesInDirectory();
        directoryList.setListData(newFiles);
    }


    /**
     * handel opening a new tap.
     */
    private class MyMouseAdapter extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent eve) {
            // Double-click detected
            if (eve.getClickCount() == 2) {
                int index = directoryList.locationToIndex(eve.getPoint());
                System.out.println(FileUtils.getFilesInDirectory()[index].getName());
                //System.out.println("Item " + index + " is clicked...");
                //TODO: Phase1: Click on file is handled... Just load content into JTextArea          done
                File[] curr = FileUtils.getFilesInDirectory();
                Note note = null;
                try {
                    note = FileUtils.readingInputObj(curr[index].getAbsolutePath());
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                openExistingNote(note);
            }
        }
    }


    /**
     * costumire the display of list
     */
    private class MyCellRenderer extends DefaultListCellRenderer {

        @Override
        public Component getListCellRendererComponent(JList list, Object object, int index, boolean isSelected, boolean cellHasFocus) {
            if (object instanceof File) {
                File file = (File) object;
                setText(FileUtils.getNameList(file.getName()));
                setIcon(FileSystemView.getFileSystemView().getSystemIcon(file));
                if (isSelected) {
                    setBackground(list.getSelectionBackground());
                    setForeground(list.getSelectionForeground());
                } else {
                    setBackground(list.getBackground());
                    setForeground(list.getForeground());
                }
                setEnabled(list.isEnabled());
            }
            return this;
        }
    }

    public JList<File> getDirectoryList() {
        return directoryList;
    }
}
