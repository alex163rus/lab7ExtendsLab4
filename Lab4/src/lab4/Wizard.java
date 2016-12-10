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
public class Wizard extends Fighter {

    public Wizard(String fighterName) {
        super(fighterName, 100, 30);
    }

    public Wizard(String fighterName, String squadName) {
        super(fighterName, 100, 30, squadName);
    }

    @Override
    public String toString() {
        return "Волшебник " + super.toString();
    }

}
