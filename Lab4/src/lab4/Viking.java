/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab4;

public class Viking extends Fighter {

    public Viking(String fighterName, String squadName) {
        super(fighterName, 120, 20, squadName);
    }

    public Viking(String fighterName) {
        super(fighterName, 120, 20);
    }

    @Override
    public String toString() {
        return "Викинг " + super.toString();
    }

}
