/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab4;

/**
 *
 * @author Алексей
 */
public class Archer extends Fighter {

    public Archer(String fighterName) {
        super(fighterName, 130, 15);
    }

    public Archer(String fighterName, String squadName) {
        super(fighterName, 130, 15, squadName);
    }

    @Override
    public String toString() {
        return "Лучник " + super.toString();
    }

}
