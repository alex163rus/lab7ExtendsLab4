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
public interface Warrior {

    int attack();

    void takeDamage(int damage);

    boolean isAlive();

    void setSquadName(String name);

    String getSquadName();
}
