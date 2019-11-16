package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import javax.swing.text.*;


public class Messenger extends JFrame {
    private JPanel grid = new JPanel(new GridBagLayout());
    private JPanel emojiGrid = new JPanel(new FlowLayout());

    private JButton sendButton = new JButton("Отправить");
    private JButton smilesButton = new JButton((new Emoji(0)).getTextInterpetation());

    private JTextPane data = new JTextPane();
    private JScrollPane chat = new JScrollPane(data,
            JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
            ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    private StyledDocument lines = data.getStyledDocument();

    private Font font = new Font("SansSerif", Font.BOLD, 24);
    private JTextField inputField;

    private ArrayList<Bot> botes = new ArrayList<>();
    private ArrayList<Boolean> left = new ArrayList<>();

    private ArrayList<JButton> smilesList = new ArrayList<>();

    private JLayeredPane layeredPane = new JLayeredPane();

    private DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm");

    private Color myColor = Color.ORANGE;

    private GridBagConstraints constraints(int gridx, int gridy, int gridwidth, int gridheight,
                                           double weightx, double weighty) {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = gridx;
        constraints.gridy = gridy;
        constraints.gridwidth = gridwidth;
        constraints.gridheight = gridheight;
        constraints.weightx = weightx;
        constraints.weighty = weighty;
        constraints.fill = GridBagConstraints.BOTH;
        return constraints;
    }

    Messenger() {
        super("ITeleJka");

        botes.add(new GretaThunberg());
        botes.add(new Femka());
        botes.add(new KremlinBot());
        botes.add(new UncleSam());

        for (int i = 0; i < botes.size(); ++i) {
            left.add(false);
        }

        setBounds(0, 0, 800, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        data.setEditable(false);
        data.setFont(font);

        sendButton.setSelected(true);
        sendButton.setFont(font);
        sendButton.addActionListener(actionEvent -> sendMessage());

        smilesButton.setFont(font);

        inputField = new JTextField();
        inputField.setFont(font);


        smilesList.add(new JButton((new Emoji(0)).getTextInterpetation()));
        smilesList.add(new JButton((new Emoji(13)).getTextInterpetation()));
        smilesList.add(new JButton((new Emoji(14)).getTextInterpetation()));
        smilesList.add(new JButton((new Emoji(27)).getTextInterpetation()));
        smilesList.add(new JButton((new Emoji(32)).getTextInterpetation()));
        smilesList.add(new JButton((new Emoji(45)).getTextInterpetation()));
        smilesList.add(new JButton((new Emoji(48)).getTextInterpetation()));
        smilesList.add(new JButton((new Emoji(52).getTextInterpetation())));

        emojiGrid.setVisible(false);
        for (JButton button : smilesList) {
            button.setFont(font);
            emojiGrid.add(button);
        }

        layeredPane.add(grid, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(emojiGrid, JLayeredPane.PALETTE_LAYER);

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("history"));
            String history = bufferedReader.readLine();
            while (history != null) {
                lines.insertString(lines.getLength(), history + '\n', new SimpleAttributeSet());
                history = bufferedReader.readLine();
            }
            bufferedReader.close();
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        } catch (BadLocationException e) {}

        grid.add(chat, constraints(0, 0, 5, 5, 1.0, 10.0));
        grid.add(sendButton, constraints(5, 5, 1, 1, 0, 0));
        grid.add(smilesButton, constraints(5, 4, 1, 1, 0, 0));
        grid.add(inputField, constraints(0, 5, 4, 1, 1.0, 1.0));

        add(layeredPane);

        setVisible(true);
        JScrollBar vertical = chat.getVerticalScrollBar();
        vertical.setValue(vertical.getMaximum());

        smilesButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {}

            @Override
            public void mousePressed(MouseEvent mouseEvent) {}

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {}

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
                emojiGrid.setVisible(true);
            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {
                emojiGrid.setVisible(false);
            }
        });

        inputField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent keyEvent) {
            }

            @Override
            public void keyPressed(KeyEvent keyEvent) {
                if (keyEvent.getKeyCode() == KeyEvent.VK_ENTER) {
                    if ("".equals(inputField.getText())) {
                        return;
                    }
                    sendMessage();
                }
            }

            @Override
            public void keyReleased(KeyEvent keyEvent) {
            }
        });

        for (int i = 0; i < smilesList.size(); ++i) {
            int tmpI = i;
            smilesList.get(i).addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent mouseEvent) {}

                @Override
                public void mousePressed(MouseEvent mouseEvent) {
                    inputField.setText(inputField.getText() +
                            smilesList.get(tmpI).getText());
                }

                @Override
                public void mouseReleased(MouseEvent mouseEvent) {}

                @Override
                public void mouseEntered(MouseEvent mouseEvent) {
                    emojiGrid.setVisible(true);
                }

                @Override
                public void mouseExited(MouseEvent mouseEvent) {
                    emojiGrid.setVisible(false);
                }
            });
        }

        emojiGrid.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {}
            @Override
            public void mousePressed(MouseEvent mouseEvent) {}
            @Override
            public void mouseReleased(MouseEvent mouseEvent) {}

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
                emojiGrid.setVisible(true);
            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {
                emojiGrid.setVisible(false);
            }
        });

        layeredPane.addComponentListener(new ComponentListener() {
            @Override
            public void componentResized(ComponentEvent componentEvent) {
                Dimension size = layeredPane.getSize();
                grid.setSize(size);
                layeredPane.revalidate();
                layeredPane.repaint();
                setVisible(true);
                emojiGrid.setBounds(chat.getWidth() - 100, 0, 100, chat.getHeight());
            }

            @Override
            public void componentMoved(ComponentEvent componentEvent) {}

            @Override
            public void componentShown(ComponentEvent componentEvent) {}

            @Override
            public void componentHidden(ComponentEvent componentEvent) {}
        });

        addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent windowEvent) {}

            @Override
            public void windowClosing(WindowEvent windowEvent) {
                try {
                    FileWriter fileWriter = new FileWriter
                            ("history");
                    fileWriter.write(data.getText());
                    fileWriter.close();
                } catch (IOException e) {}
            }

            @Override
            public void windowClosed(WindowEvent windowEvent) {}

            @Override
            public void windowIconified(WindowEvent windowEvent) {}

            @Override
            public void windowDeiconified(WindowEvent windowEvent) {}

            @Override
            public void windowActivated(WindowEvent windowEvent) {}

            @Override
            public void windowDeactivated(WindowEvent windowEvent) {}
        });
    }

    private Comparator<String> grouper = new Comparator<>() {
        public int compare(String s1, String s2) {
            String tmp1 = s1.substring(s1.indexOf(" ") + 1, s1.indexOf(":", s1.indexOf(":") + 1));
            String tmp2 = s2.substring(s2.indexOf(" ") + 1, s2.indexOf(":", s2.indexOf(":") + 1));
            return tmp1.compareToIgnoreCase(tmp2);
        }
    };

    private boolean filter(String s1) {
        String tmp = s1.substring(s1.indexOf(" ") + 1, s1.indexOf(":", s1.indexOf(":") + 1));
        return "group result".equals(tmp) || "sort result".equals(tmp);
    }

    private void process(String query) {
        ArrayList<String> messages = new ArrayList<>(Arrays.asList(
                data.getText().split(Character.toString((char)160))));
        messages.removeIf(this::filter);
        if ("sort".equals(query)) {
            messages.sort(Comparator.comparingInt(String::length));
        } else if ("group".equals(query)){
            messages.sort(grouper);
        } else {
            printMessage("Error", Color.RED, "Unknown query");
            return;
        }
        String result = String.join("\t", messages);
        printMessage(query + " result", Color.BLACK, result.substring(0, result.length() - 1));
    }
    
    private void sendMessage() {
        String message = inputField.getText();
        if (message.charAt(0) == '/') {
            process(message.substring(1));
        } else {
            printMessage("Я", myColor, message);

            for (int i = 0; i < botes.size(); ++i) {
                if (left.get(i)) {
                    continue;
                }
                try {
                    printMessage(botes.get(i).getName(), botes.get(i).getColor(), botes.get(i).answer(message));
                } catch (BadAnswerException exception) {
                    left.set(i, true);
                    printMessage(botes.get(i).getName(), botes.get(i).getColor(), exception.getMessage());
                    printMessage(botes.get(i).getName(), botes.get(i).getColor(), "left the chat");
                }
            }
        }

        inputField.setText("");

        setVisible(true);
        JScrollBar vertical = chat.getVerticalScrollBar();
        vertical.setValue(vertical.getMaximum());
    }

    private void printMessage(String name, Color userColor, String text) {
        try {
            Style style = data.addStyle("", null);

            StyleConstants.setForeground(style, Color.BLACK);
            lines.insertString(lines.getLength(), LocalDateTime.now().format(timeFormat) + " ", style);

            StyleConstants.setForeground(style, userColor);
            lines.insertString(lines.getLength(), name, style);

            StyleConstants.setForeground(style, Color.BLACK);
            lines.insertString(lines.getLength(), ":\n\t" + text + "\n" + (char)160, style);
        } catch (BadLocationException e) {}
    }
}
