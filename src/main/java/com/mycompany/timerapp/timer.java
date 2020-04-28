package com.mycompany.timerapp;

import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.*;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.SimpleDateFormat;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class timer {

    static Integer elapsed;
    static JColorChooser jcc;
    static PopupMenu jpm;
    static SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
    static Timer timer;
    static long startTime = -1;
    static long duration = 5000;
    static JTextField cronos = new JTextField();
    static JTextField cronosCount = new JTextField();
    static Color c;
    static Color k = new Color(255, 255, 255);

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame myFrame = new JFrame();
                myFrame.setSize(300, 300);
                myFrame.setLayout(new FlowLayout());
                jpm = new PopupMenu("Timer");
                MenuItem setari = new MenuItem("Settings");
                MenuItem inchidere = new MenuItem("Close");

                JFrame mySecondFrame = new JFrame();
                mySecondFrame.setSize(200, 200);
                mySecondFrame.getContentPane().setBackground(Color.WHITE);
                

                inchidere.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.exit(0);
                    }
                });

                elapsed = 0;
                ButtonGroup myChoices = new ButtonGroup();
                JRadioButton onTime = new JRadioButton("on time");
                JRadioButton countdown = new JRadioButton("coutdown");
                setari.addActionListener(new ActionListener() {
                    @Override

                    public void actionPerformed(ActionEvent e) {

                        countdown.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent event) {

                                timer = new Timer(10, new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        if (startTime < 0) {
                                            startTime = System.currentTimeMillis();
                                        }
                                        long now = System.currentTimeMillis();
                                        long clockTime = now - startTime;
                                        if (clockTime >= duration) {
                                            clockTime = duration;
                                            timer.stop();

                                            mySecondFrame.setVisible(true);

                                        }
                                        SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
                                        cronosCount.setText(df.format(duration - clockTime));

                                    }
                                });

                            }
                        });
                        onTime.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent event) {

                                timer = new Timer(10, new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        if (startTime < 0) {
                                            startTime = System.currentTimeMillis();
                                        }
                                        long now = System.currentTimeMillis();
                                        long clockTime = now - startTime;
                                        if (clockTime >= duration) {
                                            clockTime = duration;
                                            timer.stop();
                                            mySecondFrame.setVisible(true);
                                            mySecondFrame.setBackground(k);

                                        }
                                        SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
                                        cronosCount.setText(df.format(duration - clockTime));

                                    }
                                });

                            }
                        });

                        cronosCount.setText(df.format(elapsed));
                        myChoices.add(onTime);
                        myChoices.add(countdown);
                        ImageIcon ii = new ImageIcon("C:\\Users\\Ruxi\\Desktop\\colors.jpg");
                        Image img = ii.getImage();
                        Image newimg = img.getScaledInstance(25, 20, java.awt.Image.SCALE_SMOOTH);
                        ii = new ImageIcon(newimg);
                        JButton chooseColor = new JButton(ii);
                        chooseColor.setText("choose color");

                        JTextField tf = new JTextField("No color selected");
                        tf.setEditable(false);
                        JTextField tf2 = new JTextField("choose speed:");
                        tf2.setEditable(false);
                        JComboBox speed = new JComboBox();
                        speed.addItem("1");
                        speed.addItem("2");
                        speed.addItem("3");
                        speed.addItem("4");
                        speed.addItem("5");
                        speed.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent event) {

                                int selection = speed.getSelectedIndex();
                                switch (selection) {
                                    case 1:
                                        mySecondFrame.setBackground(c);
                                        break;
                                    case 2:
                                        mySecondFrame.setBackground(c);
                                        break;
                                    case 3:
                                    case 4:
                                    case 5:
                                    default:
                                        break;
                                }
                            }
                        });

                        //c.equals(e);
                        JButton starter = new JButton("START");
                        JButton stoper = new JButton("STOP");

                        starter.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {

                                onTime.setEnabled(false);
                                countdown.setEnabled(false);
                                chooseColor.setEnabled(false);
                                speed.setEnabled(false);
                                timer.start();
                            }

                        });

                        stoper.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {

                                onTime.setEnabled(true);
                                countdown.setEnabled(true);
                                chooseColor.setEnabled(true);
                                speed.setEnabled(true);
                                timer.stop();

                            }

                        });

                        myFrame.add(jpm);
                        myFrame.add(onTime);
                        myFrame.add(cronos);
                        cronos.setSize(10, 10);
                        myFrame.add(countdown);
                        myFrame.add(cronosCount);
                        cronosCount.setSize(10, 10);
                        myFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
                        myFrame.setVisible(true);
                        myFrame.add(chooseColor);
                        myFrame.add(tf);
                        myFrame.add(tf2);
                        myFrame.add(speed);
                        myFrame.add(starter);
                        myFrame.add(stoper);

                        chooseColor.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {

                                jcc = new JColorChooser();
                                JDialog dialog;
                                dialog = JColorChooser.createDialog(null, "Choose color", true, jcc, (ActionEvent e1) -> {
                                    c = jcc.getColor();
                                    myFrame.getContentPane();
                                    String hex = Integer.toHexString(c.getRGB() & 0xffffff);
                                    hex = "#" + hex;
                                    tf.setText(hex);

                                    tf.setBackground(c);

                                }, null);
                                dialog.setVisible(true);

                            }

                        });
                    }
                });
                jpm.add(setari);
                jpm.add(inchidere);
                SystemTray st = SystemTray.getSystemTray();

                try {
                    Image img2 = ImageIO.read(new File("C:\\Users\\Ruxi\\Desktop\\timerph.jpg"));

                    TrayIcon myTray = new TrayIcon(img2.getScaledInstance(15, 15, java.awt.Image.SCALE_SMOOTH));
                    myTray.setPopupMenu(jpm);

                    st.add(myTray);
                } catch (Exception exc) {

                }

            }

        });
    }
}
