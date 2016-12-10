/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab4;

import java.awt.Color;

/**
 *
 * @author Алексей
 */
public enum SquadType {
    LEFT(Color.RED),
    RIGHT(Color.BLUE);

    Color color;

    SquadType(Color color) {
        this.color = color;
    }

    Color getColor() {
        return color;
    }
}
