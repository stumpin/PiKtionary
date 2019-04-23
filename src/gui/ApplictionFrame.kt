package gui

import javax.swing.JFrame

/**
 * Created by HP xw8400
 * Author: Jacob
 * Date: 4/22/2019.
 */


class ApplictionFrame() : JFrame("Pictionary") {

    val pane : DrawingPane

    init {
        pane = DrawingPane()
        contentPane = pane
        pack()
        setLocationRelativeTo(null)
        isVisible = true
    }
}