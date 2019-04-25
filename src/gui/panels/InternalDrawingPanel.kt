package gui.panels

import core.PictionaryContext
import gui.StyleConstants
import java.awt.*
import java.awt.event.*
import java.util.*
import javax.swing.JPanel
import kotlin.collections.ArrayList


/**
 * Created by HP xw8400
 * Author: Jacob
 * Date: 4/22/2019.
 */

class InternalDrawingPanel(val pictionary: PictionaryContext) : JPanel(), MouseMotionListener, MouseListener, KeyListener {

    /**
     * A set representing all of the extended key codes that are currently pressed
     */
    private val pressed = HashSet<Int>()

    /**
     * A stack of the shapes that have been drawn
     * LIFO allows the last shape drawn to easily be popped and deleted
     */
    private val shapes = Stack<ConnectedShape>()

    /**
     * The current color of the shape (default black
     */
    var color = Color.BLACK

    /**
     * The current shape being drawn
     */
    private var currentShape = ConnectedShape(color, ArrayList())

    /**
     * A flag value that signalizes the end of a shape
     * A shape starts when the mouse is dragged,
     * and ends when the mouse is released after being dragged
     */
    private var drawing = false

    init {
        addMouseMotionListener(this)
        addMouseListener(this)
        addKeyListener(this)

        preferredSize = StyleConstants.DEFAULT_SIZE
        isFocusable = true
        requestFocusInWindow()
    }

    fun clear() {
        shapes.clear()
        currentShape.points.clear()
        repaint()
    }

    override fun mousePressed(event: MouseEvent) {
        //the mouse is pressed, start drawing
        drawing = true
        //create a new shape
        currentShape = ConnectedShape(color, ArrayList())
        //add the current point to the shape's points
        currentShape.points.add(event.point)
        //push the shape into the stack
        shapes.push(currentShape)
        repaint()
    }


    override fun mouseDragged(event: MouseEvent) {
        if (drawing) {
            if (!currentShape.points.contains(event.point)) {
                currentShape.points.add(event.point)
                repaint()
            }
        }
    }

    override fun mouseReleased(event: MouseEvent) {
        drawing = false
    }

    @Synchronized
    override fun keyPressed(event: KeyEvent) {
        pressed.add(event.extendedKeyCode)
        //if ctrl + z was pressed, pop the last drawn shape off
        if (pressed.contains(17) && event.extendedKeyCode == 90 && shapes.isNotEmpty()) {
            shapes.pop()
            currentShape.points.clear()
            repaint()
        }
    }

    @Synchronized
    override fun keyReleased(event: KeyEvent) {
        pressed.remove(event.extendedKeyCode)
    }

    override fun keyTyped(event: KeyEvent) {}

    override fun mouseMoved(event: MouseEvent) {}

    override fun mouseClicked(event: MouseEvent) {}

    override fun mouseEntered(event: MouseEvent) {
        //a little annoying, but needs to be done
        //since the other panels will take focus
        requestFocusInWindow()
    }

    override fun mouseExited(event: MouseEvent) {}

    override fun paintComponent(g: Graphics) {
        super.paintComponent(g)

        val g2 = g as Graphics2D
        g2.stroke = BasicStroke(10f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 10.0f, null, 0.0f)
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON)

        //there will be only 1 point in a shape if the user performs a click and never drags the mouse
        //if there is only one point in the shape, just draw an oval
        //else, draw the lines connected

        shapes.forEach { shape ->
            g2.color = shape.color

            when (shape.points.size) {
                1 -> {
                    val point = shape.points[0]
                    g2.fillOval(point.x - 5, point.y - 5, 10, 10)
                }
                else -> {
                    shape.points.forEachIndexed { index, point ->
                        run {
                            if (index > 0) {
                                val previous = shape.points[index - 1]
                                g2.drawLine(previous.x, previous.y, point.x, point.y)
                            }
                        }
                    }
                }
            }
        }

        g2.dispose()
    }

    private data class ConnectedShape(val color: Color, val points: ArrayList<Point>)
}