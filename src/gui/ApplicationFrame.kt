package gui

import gui.swing.InternalDrawingPanel

import javax.swing.JFrame

/**
 * Created by HP xw8400
 * Author: Jacob
 * Date: 4/22/2019.
 */

class ApplicationFrame : JFrame("Pictionary") {

    private val drawingPanel = InternalDrawingPanel()

    init {
        contentPane = drawingPanel
        pack()
        setLocationRelativeTo(null)
        isVisible = true
    }
}