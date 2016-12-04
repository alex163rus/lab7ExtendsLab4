/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab4;

import java.util.Random;

/**
 *
 * @author Алексей
 */
public class Lab4 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        DateHelper dateHelper = new DateHelper(45);// параметр - время в минутах одного раунда
        System.out.println("Начало поединка:" + dateHelper.getFormattedStartDate());

        final int countWarriorsAll = 5;//количество генерируемых бойцов
        Warrior[] warriors = new Warrior[countWarriorsAll];
        Random r = new Random();
        Squad squad1 = null;//первая команда (красная)
        Squad squad2 = null;//вторая команда (синяя)
        while (squad1 == null || squad2 == null || squad1.getWarriorsCount() == 0 || squad2.getWarriorsCount() == 0) {
            //генератор может разделить бойцов так, что в одной команде будет 0 бойцов.
            //while  работает, пока в каждой команде не будет хотя бы по одному бойцу.
            for (int i = 0; i < warriors.length; i++) {
                //добавление бойца СЛУЧАЙНОЙ рассы и запись его в случайную команду (красную или синюю)
                switch (r.nextInt(3) + 1) {
                    case 1:
                        warriors[i] = new Archer(Name.values()[r.nextInt(Name.values().length)].toString(), (r.nextInt(2) + 1) == 2 ? "Красная" : "Синяя");
                        break;
                    case 2:
                        warriors[i] = new Viking(Name.values()[r.nextInt(Name.values().length)].toString(), (r.nextInt(2) + 1) == 2 ? "Красная" : "Синяя");
                        break;
                    case 3:
                        warriors[i] = new Wizard(Name.values()[r.nextInt(Name.values().length)].toString(), (r.nextInt(2) + 1) == 2 ? "Красная" : "Синяя");
                        break;
                }
            }
            squad1 = new Squad("Красная", warriors);// Формирование первой команды 
            System.out.println("В команде:\"" + squad1 + "\" кол-во воинов:" + squad1.getWarriorsCount());
            squad2 = new Squad("Синяя", warriors);// Формирование второй команды 
            System.out.println("В команде:\"" + squad2 + "\" кол-во воинов:" + squad2.getWarriorsCount());
        }

        Fighter fighterMain;// боец, который будет бить
        Fighter fighterVictim;//боец жертва
        boolean ferstRedTeams;//кто бьет первым

        for (int i = 1; squad1.hasAliveWarriors() && squad2.hasAliveWarriors(); i++) {
            //пока есть живые хотя бы один живой боец в команде, игра продолжается.
            System.out.println("\nРаунд №" + i);
            ferstRedTeams = r.nextBoolean();
            //определение того, кто первым бьет
            for (int j = 0; j < 2 && squad1.hasAliveWarriors() && squad2.hasAliveWarriors(); j++) {
                //бьет одна команда, затем, другая
                if (ferstRedTeams) {
                    fighterMain = (Fighter) squad1.getRandomWarrior();
                    fighterVictim = (Fighter) squad2.getRandomWarrior();
                    ferstRedTeams = false;
                } else {
                    fighterMain = (Fighter) squad2.getRandomWarrior();
                    fighterVictim = (Fighter) squad1.getRandomWarrior();
                    ferstRedTeams = true;
                }
                System.out.println("  " + fighterMain);
                System.out.println(" VS:");
                System.out.println("  " + fighterVictim);
                fighterVictim.takeDamage(fighterMain.attack());
                dateHelper.skipTime();
                if (!fighterVictim.isAlive()) {
                    System.out.println("УБИТ:" + fighterVictim.getFighterName() + " команда:" + fighterVictim.getSquadName());
                }
            }

        }

        if (squad1.hasAliveWarriors()) {
            System.out.println("\nПобедила команда \"" + squad1 + "\"!");
        } else {
            System.out.println("\nПобедила команда \"" + squad2 + "\"!");
        }
        System.out.println("Общее время поединка:" + dateHelper.getFormattedDiff());

    }
}

