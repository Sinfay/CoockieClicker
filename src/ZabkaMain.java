import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ZabkaMain {

    JLabel counterLabel, perSecLabel;
    JButton button1, button2, button3, button4, button5;
    int zabkaCounter, timerSpeed, hotDogNumber, hotDogPrice, hotDogPerTenSeconds, malaKawaNumber, malaKawaPrice, malaKawaPerTenSeconds,
            monsterNumber, monsterPrice, monsterPerTenSeconds, papierToaletowyNumber, papierToaletowyPrice, papierToaletowyPerTenSeconds;
    double perSecond, updatedLowPrice;
    boolean timerOn, monsterUnlocked, papierToaletowyUnlocked, niespodziankaUnlocked;
    Font font1, font2, font3, button1Font, button2Font, button3Font, button4Font, button5Font;
    ZabkaHandler zHandler = new ZabkaHandler();
    Timer timer;
    JTextArea messageText;
    MouseHandler mHandler = new MouseHandler();

    public static void main (String[] args) {

        new ZabkaMain();

    }
    public ZabkaMain(){

        timerOn = false;
        perSecond = 0;
        zabkaCounter = 0;
        hotDogNumber = 0;
        hotDogPrice = 10;
        malaKawaNumber = 0;
        malaKawaPrice = 100;
        monsterNumber = 0;
        monsterPrice = 1000;
        papierToaletowyNumber = 0;
        papierToaletowyPrice = 7500;
        updatedLowPrice = 1.2;


        createFont();
        createUI();

    }
    public void createFont() {

        font1 = new Font("Comic Sans MS", Font.PLAIN, 30);
        font2 = new Font("Comic Sans MS", Font.PLAIN, 22);
        font3 = new Font("Comic Sans MS", Font.PLAIN, 17);

        button1Font = new Font("Comic Sans MS", Font.PLAIN, 30);
        button2Font = new Font("Comic Sans MS", Font.PLAIN, 30);
        button3Font = new Font("Comic Sans MS", Font.PLAIN, 22);
        button4Font = new Font("Comic Sans MS", Font.PLAIN, 22);
        button5Font = new Font("Comic Sans MS", Font.PLAIN, 22);



    }
    public void createUI() {

        JFrame window = new JFrame();
        window.setSize(800, 600);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().setBackground(Color.white);
        window.setLayout(null);


        JPanel zabkaPanel = new JPanel();
        zabkaPanel.setBounds(100, 220, 200, 200);
        zabkaPanel.setBackground(Color.white);
        window.add(zabkaPanel);

        ImageIcon zabkaIcon = new ImageIcon(getClass().getClassLoader().getResource("zabka.jpg"));


        JButton zabkaButton = new JButton();
        zabkaButton.setBackground(Color.black);
        zabkaButton.setFocusPainted(false);
        zabkaButton.setBorder(null);
        zabkaButton.setIcon(zabkaIcon);
        zabkaButton.addActionListener(zHandler);
        zabkaButton.setActionCommand("żapps");
        zabkaPanel.add(zabkaButton);

        JPanel counterPanel = new JPanel();
        counterPanel.setBounds(100, 30, 200, 200);
        counterPanel.setBackground(Color.white);
        counterPanel.setLayout(new GridLayout(2,1));
        window.add(counterPanel);

        counterLabel = new JLabel(zabkaCounter + " zappsów");
        counterLabel.setForeground(Color.black);
        counterLabel.setFont(font1);
        counterPanel.add(counterLabel);

        perSecLabel = new JLabel();
        perSecLabel.setForeground(Color.black);
        perSecLabel.setFont(font3);
        counterPanel.add(perSecLabel);

        JPanel itemPanel = new JPanel();
        itemPanel.setBounds(500, 170, 250, 250);
        itemPanel.setBackground(Color.white);
        itemPanel.setLayout(new GridLayout(5,1));
        window.add(itemPanel);

        button1 = new JButton("Hot Dog");
        if (zabkaCounter >= hotDogPrice) {
            button1.setFont(button1Font);
            button1.setForeground(Color.green);
        } else {
            button1.setFont(font1);
        }
        button1.setFocusPainted(false);
        button1.addActionListener(zHandler);
        button1.setActionCommand("Hot Dog");
        button1.addMouseListener(mHandler);
        itemPanel.add(button1);

        button2 = new JButton("Mała kawa");
        if (zabkaCounter >= malaKawaPrice) {
            button2.setFont(button2Font);
            button2.setForeground(Color.green);
        } else {
            button2.setFont(font1);
        }
        button2.setFocusPainted(false);
        button2.addActionListener(zHandler);
        button2.setActionCommand("Mała kawa");
        button2.addMouseListener(mHandler);
        itemPanel.add(button2);

        button3 = new JButton("Monster");
        if (zabkaCounter >= monsterPrice) {
            button3.setFont(button3Font);
            button3.setForeground(Color.green);
        } else {
            button3.setFont(font2);
        }
        button3.setFocusPainted(false);
        button3.addActionListener(zHandler);
        button3.setActionCommand("Monster");
        button3.addMouseListener(mHandler);
        itemPanel.add(button3);

        button4 = new JButton("Papier toaletowy");
        if (zabkaCounter >= papierToaletowyPrice) {
            button4.setFont(button4Font);
            button4.setForeground(Color.green);
        } else {
            button4.setFont(font2);
        }
        button4.setFocusPainted(false);
        button4.addActionListener(zHandler);
        button4.setActionCommand("Papier toaletowy");
        button4.addMouseListener(mHandler);
        itemPanel.add(button4);

        button5 = new JButton("???");
        button5.setFont(font1);
        button5.setFocusPainted(false);
        button5.addActionListener(zHandler);
        button5.setActionCommand("");
        button5.addMouseListener(mHandler);
        itemPanel.add(button5);

        JPanel messagePanel = new JPanel();
        messagePanel.setBounds(500, 70, 250, 150);
        messagePanel.setBackground(Color.white);
        window.add(messagePanel);

        messageText = new JTextArea();
        messageText.setBounds(500, 70, 250, 150);
        messageText.setForeground(Color.green);
        messageText.setBackground(Color.white);
        messageText.setFont(font3);
        messageText.setLineWrap(true);
        messageText.setWrapStyleWord(true);
        messageText.setEditable(false);
        messagePanel.add(messageText);


        window.setVisible(true);
    }

    public void setTimer() {
        timer = new Timer(timerSpeed, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                zabkaCounter++;
                counterLabel.setText(zabkaCounter + " żappsów");
                //Odblokowywanie itemów (patrz button 5, ktory mozna tutaj zmieniac)
                if(!niespodziankaUnlocked) {
                    if(zabkaCounter>=10000){
                        niespodziankaUnlocked=true;
                        button5.setText("Niespodzianka");
                    }
                }
            }
        });
    }
    public void timerUpdate() {
        if(!timerOn) {
            timerOn = true;
        }else if(timerOn) {
            timer.stop();
        }

        double speed = 1 / perSecond * 1000;
        timerSpeed = (int)Math.round(speed);


        String correct = String.format("%.1f", perSecond);
        perSecLabel.setText("na sekundę: " + correct);

        setTimer();
        timer.start();
    }

    public class ZabkaHandler implements ActionListener {

        public void actionPerformed(ActionEvent event) {


            String action = event.getActionCommand();

            switch (action) {
                case "żapps" -> {
                    zabkaCounter++;
                    counterLabel.setText(zabkaCounter + " żappsów");
                }
                case "Hot Dog" -> {
                    if(zabkaCounter >= hotDogPrice) {
                        zabkaCounter = zabkaCounter - hotDogPrice;
                        hotDogPrice = (int) Math.round(hotDogPrice * updatedLowPrice);
                        counterLabel.setText(zabkaCounter + " żappsów");
                        hotDogNumber++;
                        button1.setText("Hot Dog " + "(" + hotDogNumber + ")");
                        perSecond = perSecond + 0.1;
                        timerUpdate();
                    } else {
                        messageText.setText("Potrzebujesz więcej żappsów");
                    }
                }
                case "Mała kawa" -> {
                    if(zabkaCounter >= malaKawaPrice) {
                        zabkaCounter = zabkaCounter - malaKawaPrice;
                        malaKawaPrice = (int) Math.round(malaKawaPrice * updatedLowPrice);
                        counterLabel.setText(zabkaCounter + " żappsów");
                        malaKawaNumber++;
                        button2.setText("Mała kawa " + "(" + malaKawaNumber + ")");
                        perSecond = perSecond + 1;
                        timerUpdate();
                    } else {
                        messageText.setText("Potrzebujesz więcej żappsów");
                    }
                    // Mozna jeszcze tutaj dodac else if jesli przedmiot jest zablokowany wtedy malaKawaUnlocked = true
                }
                case "Monster" -> {
                    if(zabkaCounter >= monsterPrice) {
                        zabkaCounter = zabkaCounter - monsterPrice;
                        monsterPrice = (int) Math.round(monsterPrice * updatedLowPrice);
                        counterLabel.setText(zabkaCounter + " żappsów");
                        monsterNumber++;
                        button3.setText("Monster " + "(" + monsterNumber + ")");
                        perSecond = perSecond + 10;
                        timerUpdate();
                    } else {
                        messageText.setText("Potrzebujesz więcej żappsów");
                    }
                }
                case "Papier toaletowy" -> {
                    if(zabkaCounter >= papierToaletowyPrice) {
                        zabkaCounter = zabkaCounter - papierToaletowyPrice;
                        papierToaletowyPrice = (int) Math.round(papierToaletowyPrice * updatedLowPrice);
                        counterLabel.setText(zabkaCounter + " żappsów");
                        papierToaletowyNumber++;
                        button4.setText("Papier toaletowy " + "(" + papierToaletowyNumber + ")");
                        perSecond = perSecond + 70;
                        timerUpdate();
                    } else {
                        messageText.setText("Potrzebujesz więcej żappsów");
                    }
                }
            }
        }
    }

    public class MouseHandler implements MouseListener{

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

            JButton button = (JButton)e.getSource();
            hotDogPerTenSeconds = hotDogNumber;
            malaKawaPerTenSeconds = malaKawaNumber * 10;
            monsterPerTenSeconds = monsterNumber * 100;
            papierToaletowyPerTenSeconds = papierToaletowyNumber * 700;

            if(button == button1) {
                messageText.setText("Hot Dog\n[cena: " + hotDogPrice + "]\nGeneruje " + hotDogPerTenSeconds + " żappsów na 10 sekund");
            } else if(button == button2) {
                messageText.setText("Mała kawa\n[cena: " + malaKawaPrice + "]\nGeneruje " + malaKawaPerTenSeconds + " żappsów na 10 sekund");
            } else if(button == button3) {
                messageText.setText("Monster\n[cena: " + monsterPrice + "]\nGeneruje " + monsterPerTenSeconds + " żappsów na 10 sekund");
            } else if(button == button4) {
                messageText.setText("Papier toaletowy\n[cena: " + papierToaletowyPrice + "]\nGeneruje " + papierToaletowyPerTenSeconds + " żappsów na 10 sekund");
            } else if (button == button5) {
                messageText.setText("Ten przedmiot jest niedostępny na obecnym etapie gry.");
            }
        }

        @Override
        public void mouseExited(MouseEvent e) {

            JButton button = (JButton)e.getSource();

            if(button == button1 || button == button2 || button == button3 || button == button4 || button == button5) {
                messageText.setText(null);
            }

        }
    }




}



