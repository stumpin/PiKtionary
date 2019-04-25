package gui.panels

import core.PictionaryContext
import gui.StyleConstants
import javafx.application.Platform
import javafx.embed.swing.JFXPanel
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.control.ColorPicker
import javafx.scene.layout.HBox

import java.awt.*

import java.awt.event.MouseEvent
import java.awt.event.MouseListener
import java.awt.event.MouseMotionListener
import java.io.File
import java.lang.Exception
import javax.imageio.ImageIO
import javax.swing.Box
import javax.swing.JColorChooser

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

        val picker = JColorChooser(Color.black)

        picker.selectionModel.addChangeListener{
            pictionary.drawing.color = picker.color
        }

        add(picker)
       /* Platform.runLater {
            scene = buildScene()
            isVisible = true
        }*/
    }

   /* private fun buildScene() : Scene {
        val picker = ColorPicker(javafx.scene.paint.Color.BLACK)
        picker.valueProperty().addListener({ o, old, color -> pictionary.drawing.color =
            Color(
                color.red as Float,
                color.getGreen() as Float,
                color.getBlue() as Float,
                color.getOpacity() as Float
            ) })
        val box = HBox(picker)

       // pane.children.add(button)

        return Scene(box)
    }*/

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