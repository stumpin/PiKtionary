package gui

import java.awt.Dimension
import java.awt.event.MouseEvent
import java.awt.event.MouseListener
import java.awt.event.MouseMotionListener
import javax.swing.JPanel

/**
 * Created by HP xw8400
 * Author: Jacob
 * Date: 4/22/2019.
 */

class DrawingPane : JPanel(), MouseMotionListener, MouseListener {

    init {
        addMouseMotionListener(this)
        addMouseListener(this)
        preferredSize = Dimension(750, 650)
    }

    override fun mouseDragged(event: MouseEvent?) {

    }

    override fun mouseMoved(event: MouseEvent?) {

    }

    override fun mouseClicked(event: MouseEvent?) {

    }

    override fun mouseEntered(event: MouseEvent?) {

    }

    override fun mouseExited(event: MouseEvent?) {

    }

    override fun mousePressed(event: MouseEvent?) {

    }

    override fun mouseReleased(event: MouseEvent?) {

    }
}