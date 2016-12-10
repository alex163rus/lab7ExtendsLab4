/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab4;

import java.util.regex.Pattern;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

/**
 *
 * @author Алексей
 */
public class MainFrame extends JFrame {

    private Squad squadLeft;//левая команда (красная)
    private Squad squadRight;//правая команда (синяя)

    public MainFrame() throws HeadlessException {
        squadLeft = new Squad();
        squadRight = new Squad();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());

        initComponent();
    }

    private void initComponent() {
        TextLabel numberRaund = new TextLabel("Раунд №:0", SwingConstants.CENTER);
        TextLabel timeButtle = new TextLabel("Время с начала :0 минут", SwingConstants.CENTER);

        TextLabel nameSquadLeft = new TextLabel("", SquadType.LEFT);
        TextLabel nameSquadRight = new TextLabel("", SquadType.RIGHT);

        TextLabel livingsSquadLeft = new TextLabel("Живых:", SquadType.LEFT);
        TextLabel livingsSquadRight = new TextLabel("Живых:", SquadType.RIGHT);

        TextArea warriorsSquadLeft = new TextArea(SquadType.LEFT);
        TextArea warriorsSquadRight = new TextArea(SquadType.RIGHT);
        TextArea arena = new TextArea();
        JScrollPane scrollPaneArena = new JScrollPane(arena);

        JButton startButtle = new JButton("Бой!");
        GridBagConstraints c = new GridBagConstraints();

        c.insets = new Insets(0, 10, 0, 10);
        c.anchor = GridBagConstraints.NORTH;
        c.fill = GridBagConstraints.BOTH;
        c.weighty = 0;
        //первая колонка (левая команда):
        c.gridx = 0;
        c.gridy = 0;
        add(new TextLabel("Отряд:", SquadType.LEFT), c);

        c.gridx = 0;
        c.gridy = 1;
        add(nameSquadLeft, c);

        c.weighty = 1;
        c.gridx = 0;
        c.gridy = 2;
        add(new JScrollPane(warriorsSquadLeft), c);
        c.weighty = 0;

        c.gridx = 0;
        c.gridy = 3;
        add(livingsSquadLeft, c);
        //вторая колонка (правая команда):
        c.gridx = 2;
        c.gridy = 0;
        add(new TextLabel("Отряд:", SquadType.RIGHT), c);
        c.gridx = 2;
        c.gridy = 1;
        add(nameSquadRight, c);

        c.weighty = 1;
        c.gridx = 2;
        c.gridy = 2;
        add(new JScrollPane(warriorsSquadRight), c);
        c.weighty = 0;

        c.gridx = 2;
        c.gridy = 3;
        add(livingsSquadRight, c);

        //средняя колонка 
        c.weightx = 1;

        c.gridx = 1;
        c.gridy = 0;
        add(numberRaund, c);

        c.gridx = 1;
        c.gridy = 1;
        add(timeButtle, c);

        c.weighty = 1;
        c.gridx = 1;
        c.gridy = 2;
        add(scrollPaneArena, c);
        c.weighty = 0;

        c.insets = new Insets(10, 10, 20, 10);//отступ снизу

        c.gridx = 1;
        c.gridy = 3;
        c.fill = GridBagConstraints.NONE;
        add(startButtle, c);

        JMenu jMenu = new JMenu("Меню");

        JMenuItem newButtle = new JMenuItem("Новая битва");
        jMenu.add(newButtle);

        JMenuItem renameSquads = new JMenuItem("Редактировать имена");
        jMenu.add(renameSquads);

        JMenuItem restructureSquads = new JMenuItem("Редактировать состав отрядов");
        jMenu.add(restructureSquads);

        jMenu.addSeparator();
        JMenuItem exitItem = new JMenuItem("Выход");
        jMenu.add(exitItem);

        JMenuBar menuBar = new JMenuBar();
        menuBar.add(jMenu);
        setJMenuBar(menuBar);

        newButtle.addActionListener((e) -> {
            Object[] options = {"Да", "Отмена"};
            int result = JOptionPane.showOptionDialog(null, "Вы действительно хотите удалить всех бойцов\nи начать новую битву?",
                    "Новая битва", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
            if (result == 0) {
                squadLeft = new Squad();
                squadRight = new Squad();
                numberRaund.setText("Раунд №:0");
                timeButtle.setText("Время с начала :0 минут");
                nameSquadLeft.setText("");
                nameSquadRight.setText("");
                livingsSquadLeft.setText("Живых:0");
                livingsSquadRight.setText("Живых:0");
                warriorsSquadLeft.setText("");
                warriorsSquadRight.setText("");
                arena.setText("");
                startButtle.setEnabled(true);
            }
        });

        renameSquads.addActionListener((e) -> {
            JTextField leftName = new JTextField(nameSquadLeft.getText());
            JTextField rightName = new JTextField(nameSquadRight.getText());
            Object[] ob = {new JLabel("Первый отряд:"), leftName, new JLabel("Второй отряд:"), rightName};
            int result = JOptionPane.showConfirmDialog(null, ob, "Названия отрядов", JOptionPane.OK_CANCEL_OPTION);
            if (result == 0) {
                if (rightName.getText().equals(leftName.getText())) {
                    JOptionPane.showMessageDialog(startButtle, "Одинаковые имена недопустимы", "Ошибка!", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (checkCorrectName(leftName.getText())) {
                    nameSquadLeft.setText(leftName.getText().substring(0, 1).toUpperCase() + leftName.getText().substring(1));
                } else {
                    JOptionPane.showMessageDialog(startButtle, "Имя первого отряда введено неверно", "Ошибка!", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (checkCorrectName(rightName.getText())) {
                    nameSquadRight.setText(rightName.getText().substring(0, 1).toUpperCase() + rightName.getText().substring(1));
                } else {
                    JOptionPane.showMessageDialog(startButtle, "Имя второго отряда введено неверно", "Ошибка!", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                squadLeft.setSquadName(nameSquadLeft.getText());
                squadRight.setSquadName(nameSquadRight.getText());
            }
        });

        restructureSquads.addActionListener((e) -> {
            if (nameSquadLeft.isEmpty() || nameSquadRight.getText().isEmpty()) {
                JOptionPane.showMessageDialog(startButtle, "Отсутствует название отряда!", "Ошибка!", JOptionPane.ERROR_MESSAGE);
                return;
            }
            JComboBox selectWarrior = new JComboBox();
            //как я понял задачу "независимости" от классов бойцов, 
            //в данном месте должен быть поиск всех классов, 
            //которые реализуют интерфейс Warrior, а в моем случае, которые являются наследниками класса Fighter
            //и автоматическое добавление найденных классов в JComboBox.
            //Пробовал найти решение с помощью рефлексии - не вышло. 
            //Поэтому добавляю JComboBox названия и работаю с классами вручную.
            selectWarrior.addItem("Лучник");
            selectWarrior.addItem("Викинг");
            selectWarrior.addItem("Волшебник");

            TextLabel warriorInfo = new TextLabel(getFighter(selectWarrior.getSelectedIndex(), "").toString());
            selectWarrior.addActionListener((ActionEvent e1) -> {
                warriorInfo.setText(getFighter(selectWarrior.getSelectedIndex(), "").toString());
            });

            JCheckBox otradLeft = new JCheckBox("В отряд: \"" + nameSquadLeft.getText() + "\"");
            otradLeft.setForeground(Color.red);
            JCheckBox otradRight = new JCheckBox("В отряд: \"" + nameSquadRight.getText() + "\"");
            otradRight.setForeground(Color.BLUE);

            JTextField fighterNameField = new JTextField();
            JButton butAdd = new JButton("Добавить");

            JCheckBox randomName = new JCheckBox("Случайное имя");
            randomName.addActionListener((ActionEvent e1) -> {
                Random r = new Random();
                if (randomName.isSelected()) {
                    fighterNameField.setEnabled(false);
                    fighterNameField.setText(Name.getRandName());
                } else {
                    fighterNameField.setEnabled(true);
                    fighterNameField.setText("");
                }
            });

            butAdd.addActionListener((ActionEvent e1) -> {
                if (!checkCorrectName(fighterNameField.getText())) {
                    JOptionPane.showMessageDialog(startButtle, "Неверный формат ввода", "Ошибка!", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (otradLeft.isSelected()) {
                    squadLeft.addWarrior(getFighter(selectWarrior.getSelectedIndex(), fighterNameField.getText()));
                }
                if (otradRight.isSelected()) {
                    squadRight.addWarrior(getFighter(selectWarrior.getSelectedIndex(), fighterNameField.getText()));
                }
                if (randomName.isSelected()) {
                    fighterNameField.setEnabled(false);
                    fighterNameField.setText(Name.getRandName());
                }
                warriorsSquadLeft.setText(getSquadAliveWarriors(squadLeft));
                warriorsSquadRight.setText(getSquadAliveWarriors(squadRight));
                livingsSquadLeft.setText("Живых:" + squadLeft.getAliveCountWarriors());
                livingsSquadRight.setText("Живых:" + squadRight.getAliveCountWarriors());
            });
            Object[] ob = {new JLabel("Добавить бойца:"), warriorInfo, selectWarrior, new JLabel("Имя бойца:"), fighterNameField, randomName, otradLeft, otradRight, butAdd};
            JOptionPane.showMessageDialog(null, ob, "Добавление бойцов", JOptionPane.PLAIN_MESSAGE);
        });
        exitItem.addActionListener((ActionEvent e) -> {
            dispose();
        });

        startButtle.addActionListener((ActionEvent e) -> {
            if (nameSquadLeft.isEmpty() || nameSquadRight.getText().isEmpty()) {
                JOptionPane.showMessageDialog(startButtle, "Отсутствует название отряда!", "Ошибка!", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (warriorsSquadLeft.isEmpty() || warriorsSquadRight.isEmpty()) {
                JOptionPane.showMessageDialog(startButtle, "В отряде отсутствуют игроки!", "Ошибка!", JOptionPane.ERROR_MESSAGE);
                return;
            }

            startButtle.setEnabled(false);//отключение кнопок на время демонстрации
            jMenu.setEnabled(false);

            DateHelper dateHelper = new DateHelper(45);// параметр - время в минутах одного раунда
            arena.addText("Начало поединка:" + dateHelper.getFormattedStartDate());

            new Thread(
                    ()
                    -> {
                Fighter fighterMain;// боец, который будет бить
                Fighter fighterVictim;//боец жертва
                boolean ferstRedTeams;//кто бьет первым
                Random r = new Random();
                for (int i = 1; squadLeft.hasAliveWarriors() && squadRight.hasAliveWarriors(); i++) {
                    arena.addText("\nРаунд №" + i);
                    numberRaund.setText("Раунд №:" + i);
                    ferstRedTeams = r.nextBoolean();
                    //определение того, кто первым бьет
                    for (int j = 0; j < 2 && squadLeft.hasAliveWarriors() && squadRight.hasAliveWarriors(); j++) {
                        //бьет одна команда, затем, другая
                        if (ferstRedTeams) {
                            fighterMain = (Fighter) squadLeft.getRandomWarrior();
                            fighterVictim = (Fighter) squadRight.getRandomWarrior();
                            ferstRedTeams = false;
                        } else {
                            fighterMain = (Fighter) squadRight.getRandomWarrior();
                            fighterVictim = (Fighter) squadLeft.getRandomWarrior();
                            ferstRedTeams = true;
                        }
                        arena.addText("  Бьет:");
                        arena.addText("  Команда:\"" + fighterMain.getSquadName() + "\" " + fighterMain);
                        arena.addText(" VS:");
                        arena.addText("  " + fighterMain.getSquadName() + " " + fighterVictim);

                        fighterVictim.takeDamage(fighterMain.attack());
                        dateHelper.skipTime();
                        warriorsSquadLeft.setText(getSquadAliveWarriors(squadLeft));
                        warriorsSquadRight.setText(getSquadAliveWarriors(squadRight));
                        if (!fighterVictim.isAlive()) {
                            arena.addText("УБИТ:" + fighterVictim.getFighterName() + " команда:" + fighterVictim.getSquadName());
                            livingsSquadLeft.setText("Живых:" + squadLeft.getAliveCountWarriors());
                            livingsSquadRight.setText("Живых:" + squadRight.getAliveCountWarriors());
                        }
                        arena.addText("\n");
                    }
                    timeButtle.setText("Время с начала поединка(минут):" + dateHelper.getFormattedDiff());
                    //автопрокрутка:
                    scrollPaneArena.getViewport().setViewPosition(new Point(10, arena.getDocument().getLength()));
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (squadLeft.hasAliveWarriors()) {
                    arena.addText("\nПобедила команда \"" + squadLeft + "\"!");
                    JOptionPane.showMessageDialog(startButtle, "Бой закончен! \nПобедила команда \"" + squadLeft + "\"!", "GameOver :(", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    arena.addText("\nПобедила команда \"" + squadRight + "\"!");
                    JOptionPane.showMessageDialog(startButtle, "Бой закончен! \nПобедила команда \"" + squadRight + "\"!", "GameOver :(", JOptionPane.INFORMATION_MESSAGE);
                }
                arena.addText("Общее время поединка:" + dateHelper.getFormattedDiff());
                jMenu.setEnabled(true);
            }
            ).start();
        });
        pack();
    }

    private Warrior getFighter(int index, String fighterName) {
        switch (index) {
            case 0:
                return new Archer(fighterName);
            case 1:
                return new Viking(fighterName);
            case 2:
                return new Wizard(fighterName);
        }
        return null;
    }

    private boolean checkCorrectName(String name) {
        Pattern pattern = Pattern.compile("^[а-яА-ЯёЁa-zA-Z][а-яА-ЯёЁa-zA-Z0-9№_]{1,20}$");
        return pattern.matcher(name).matches();
    }

    private String getSquadAliveWarriors(Squad squad) {
        StringBuilder stringBuilder = new StringBuilder();
        squad.getWarriors().forEach((Warrior warrior)
                -> stringBuilder.append(warrior).append(!warrior.isAlive() ? " - УБИТ" : "").append("\n"));
        return stringBuilder.toString();
    }

    private class TextLabel extends JLabel {

        public TextLabel(String text, SquadType squadType, int horisontalAligment) {
            super(text, horisontalAligment);
            setForeground(squadType.getColor());
        }

        public TextLabel(String text, int horisontalAligment) {
            super(text, horisontalAligment);
        }

        public TextLabel(String text, SquadType squadType) {
            super(text);
            setForeground(squadType.getColor());
        }

        public TextLabel(String text) {
            super(text);
        }

        public boolean isEmpty() {
            return getText().length() == 0;
        }
    }

    private class TextArea extends JTextArea {

        public TextArea(SquadType squadType) {
            super(10, 30);
            setForeground(squadType.getColor());
            setEditable(false);
        }

        public TextArea() {
            super(10, 10);
            setEditable(false);
        }

        public void addText(String text) {
            setText(getText() + "\n" + text);
        }

        public boolean isEmpty() {
            return getText().length() == 0;
        }
    }

}
