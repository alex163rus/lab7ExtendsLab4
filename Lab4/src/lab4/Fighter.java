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
public class Fighter implements Warrior, Cloneable {

    private String fighterName;
    private int health;
    private int damage;
    private String squadName;

    public Fighter(String fighterName, int health, int damage, String squadName) {
        this.fighterName = fighterName;
        this.health = health;
        this.damage = damage;
        this.squadName = squadName;
    }

    public Fighter(String fighterName, int health, int damage) {
        this.fighterName = fighterName;
        this.health = health;
        this.damage = damage;
        this.squadName = "Отсутствует";
    }

    @Override
    public int attack() {
        return damage;
    }

    @Override
    public void takeDamage(int damage) {
        health = (health - damage) > 0 ? (health - damage) : 0;
    }

    @Override
    public boolean isAlive() {
        return health > 0;
    }

    @Override
    public void setSquadName(String name) {
        squadName = name;
    }

    @Override
    public String toString() {
        return fighterName + ", Здоровье=" + health + ", Атака=" + damage;
    }

    @Override
    public String getSquadName() {
        return squadName;
    }

    public String getFighterName() {
        return fighterName;
    }

    public Fighter clone() throws CloneNotSupportedException {
        Fighter ob = (Fighter) super.clone();
        ob.damage = damage;
        ob.fighterName = fighterName;
        ob.health = health;
        ob.squadName = squadName;
        return ob;
    }

    public void setFighterName(String fighterName) {
        this.fighterName = fighterName;
    }

    public int getHealth() {
        return health;
    }
}
