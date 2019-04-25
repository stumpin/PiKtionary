package gui

import core.PictionaryContext

import java.awt.FlowLayout

import javax.swing.JFrame
import javax.swing.JPanel

/**
 * Created by HP xw8400
 * Author: Jacob
 * Date: 4/22/2019.
 */

class ApplicationFrame(val context: PictionaryContext) : JFrame("Pictionary") {

    init {
        layout = FlowLayout(0)
    }

    fun addPanels(vararg panels : JPanel) {
        for (panel in panels) {
            add(panel)
        }
        pack()
        setLocationRelativeTo(null)
        isVisible = true
    }
}