/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab6;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionRegistration;
import org.openide.util.NbBundle.Messages;

@ActionID(
        category = "Bugtracking",
        id = "lab6.SomeAction"
)
@ActionRegistration(
        displayName = "#CTL_SomeAction"
)
@ActionReference(path = "Menu/Tools", position = 0)
@Messages("CTL_SomeAction=lab6")
public final class SomeAction implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO implement action body
    }
}
