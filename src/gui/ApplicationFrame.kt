package gui

import core.PictionaryContext

import java.awt.FlowLayout

import javax.swing.JFrame
import javax.swing.JPanel
import java.awt.CardLayout
import javax.swing.JComponent


/**
 * Created by HP xw8400
 * Author: Jacob
 * Date: 4/22/2019.
 */

class ApplicationFrame(val context: PictionaryContext) : JFrame("Pictionary") {

    private val layout = CardLayout()
    private val deck = JPanel(layout)

    init {
        contentPane = deck
    }

    //the panel(s) are placed in a 'card' panel
    //which is then added to the main 'deck' panel
    fun addPanels(vararg components : JComponent) {
        val card = JPanel()
        card.layout = FlowLayout(0)
        if (components.size > 1) {
            for (panel in components) {
                card.add(panel)
            }
        } else {
            card.add(components[0])
        }
        deck.add(card)
        pack()
        setLocationRelativeTo(null)

        isResizable = false // do not make true ever
        isVisible = true
    }

    fun showLast() {
        layout.last(deck)
    }

    fun showFirst() {
        layout.first(deck)
    }
}