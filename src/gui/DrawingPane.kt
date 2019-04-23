package gui

import java.awt.*
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

    private val shapes = arrayListOf<ArrayList<Point>>()

    private var currentShape = arrayListOf<Point>()

    private var shapeEnded = false

    init {
        addMouseMotionListener(this)
        addMouseListener(this)
        preferredSize = Dimension(750, 650)
    }

    override fun mouseDragged(event: MouseEvent) {
        if (!shapes.contains(currentShape)) {
            shapes.add(currentShape)
        }
        if (shapeEnded) {
            currentShape = arrayListOf()
        }
        if (!currentShape.contains(event.point)) {
            currentShape.add(event.point)
            repaint()
        }
        shapeEnded = false
    }

    override fun mouseReleased(event: MouseEvent) {
        shapeEnded = true
    }

    override fun mouseMoved(event: MouseEvent) {}

    override fun mouseClicked(event: MouseEvent) {}

    override fun mouseEntered(event: MouseEvent) {}

    override fun mouseExited(event: MouseEvent) {}

    override fun mousePressed(event: MouseEvent) {}

    override fun paintComponent(g: Graphics) {
        super.paintComponent(g)

        val g2 = g as Graphics2D

        g2.stroke = BasicStroke(10f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 10.0f, null, 0.0f)
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON)

        shapes.forEach { shape ->
            shape.forEachIndexed { index, point ->
                run {
                    if (index > 0) {
                        val previous = shape[index - 1]
                        g2.drawLine(previous.x, previous.y, point.x, point.y)
                    }
                }
            }
        }
    }
}