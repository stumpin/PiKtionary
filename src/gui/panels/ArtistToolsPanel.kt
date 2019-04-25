package gui.panels

import core.PictionaryContext
import gui.StyleConstants
import java.awt.*

import java.awt.event.MouseEvent
import java.awt.event.MouseListener
import java.awt.event.MouseMotionListener
import java.io.File
import java.lang.Exception
import javax.imageio.ImageIO
import javax.swing.Box

import javax.swing.JPanel


/**
 * Created by HP xw8400
 * Author: Jacob
 * Date: 4/24/2019.
 */
class ArtistToolsPanel(val pictionary: PictionaryContext) : JPanel(), MouseMotionListener, MouseListener {

    private var hoveringTrash = false

    private val highlight = Color(230, 230, 0, 150)

    private val box = Rectangle(7, 545, 60, 60)

    private var image: Image? = null

    init {
        addMouseMotionListener(this)
        addMouseListener(this)

        background = Color.LIGHT_GRAY
        preferredSize = Dimension(StyleConstants.DEFAULT_SIZE.width / 12, StyleConstants.DEFAULT_SIZE.height)
        try {
            val image = ImageIO.read(File("resources/trash.png"))
            this.image = image.getScaledInstance(50, 50, Image.SCALE_SMOOTH)
        } catch (e : Exception) {
            e.printStackTrace()
        }
    }

    override fun mouseDragged(e: MouseEvent) {}

    override fun mouseMoved(e: MouseEvent) {
        val hovering = box.contains(e.point)
        //repaint only if they are different
        //prevents frequent calls to repaint()
        if (hovering != hoveringTrash) {
            hoveringTrash = hovering
            repaint()
        }
    }

    override fun mouseClicked(e: MouseEvent) {
        if (hoveringTrash) {
            pictionary.drawing.clear()
        }
    }

    override fun mousePressed(e: MouseEvent) {}

    override fun mouseReleased(e: MouseEvent) {}

    override fun mouseEntered(e: MouseEvent) {}

    override fun mouseExited(e: MouseEvent) {
        hoveringTrash = false
        repaint()
    }

    override fun paintComponent(g: Graphics) {
        super.paintComponent(g)

        if (hoveringTrash) {
            g.color = highlight
            g.fillRect(box.x, box.y, box.width, box.height)
        }
        g.drawImage(image, 12, 550, null)
    }
}