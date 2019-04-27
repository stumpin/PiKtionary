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
import javax.swing.*


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

        add(ColorGridPicker(pictionary))

        for (i in 10..40 step 10) {

            var highlighted = false

            val panel = object : JPanel() {

                override fun getBackground(): Color {
                    return Color.LIGHT_GRAY
                }

                override fun getPreferredSize(): Dimension {
                    return Dimension(40, i + 5)
                }

                override fun paintComponent(graphics : Graphics) {
                    super.paintComponent(graphics)

                    val graphics2D = graphics as Graphics2D
                    graphics2D.color = StyleConstants.PALETTE[15]
                    graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON)

                    graphics2D.fillOval(width / 2 - i / 2, height / 2 - i / 2, i, i)

                    if (highlighted) {
                        graphics2D.color = Color.LIGHT_GRAY
                        graphics2D.drawOval(width / 2 - i / 2, height / 2 - i / 2, i, i)
                    }

                    graphics2D.dispose()
                }
            }

            panel.addMouseListener(object: MouseListener {

                override fun mouseClicked(e: MouseEvent) {
                    pictionary.drawing.thickness = i
                }

                override fun mousePressed(e: MouseEvent) {}

                override fun mouseReleased(e: MouseEvent) {}

                override fun mouseEntered(e: MouseEvent) {
                    highlighted = true
                }

                override fun mouseExited(e: MouseEvent) {
                    highlighted = false
                }
            })
            add(panel)
        }
    }

    override fun mouseDragged(event: MouseEvent) {}

    override fun mouseMoved(event: MouseEvent) {
        val hovering = box.contains(event.point)
        //repaint only if they are different
        //prevents frequent calls to repaint()
        if (hovering != hoveringTrash) {
            hoveringTrash = hovering
            repaint()
        }
    }

    override fun mouseClicked(event: MouseEvent) {
        if (hoveringTrash) {
            pictionary.drawing.clear()
        }
    }

    override fun mousePressed(event: MouseEvent) {}

    override fun mouseReleased(event: MouseEvent) {}

    override fun mouseEntered(event: MouseEvent) {}

    override fun mouseExited(event: MouseEvent) {
        hoveringTrash = false
        repaint()
    }

    override fun paintComponent(graphics: Graphics) {
        super.paintComponent(graphics)

        if (hoveringTrash) {
            graphics.color = highlight
            graphics.fillRect(box.x, box.y, box.width, box.height)
        }
        graphics.drawImage(image, 12, 550, null)
    }

    /**
     * Panel that holds each color swatch
     */
    inner class ColorGridPicker(val pictionary: PictionaryContext) : JPanel(GridLayout(7, 3, 2, 2)) {
        /**
         * The current selected panel
         */
        var selectedPanel = JPanel()

        init {
            preferredSize = Dimension(StyleConstants.DEFAULT_SIZE.width / 12 - 10, 165)
            background = Color.LIGHT_GRAY

            for (color in StyleConstants.PALETTE) {
                val panel = JPanel()
                val hoverColor = color.darker()

                panel.background = color
                panel.border = BorderFactory.createRaisedBevelBorder()

                panel.addMouseListener(object: MouseListener {
                    /**
                     * Clicked in effect
                     */
                    override fun mouseClicked(e: MouseEvent) {
                        selectedPanel.border = BorderFactory.createRaisedBevelBorder()
                        panel.background = hoverColor
                        panel.border = BorderFactory.createLoweredBevelBorder()
                        selectedPanel = panel
                        pictionary.drawing.color = color
                    }

                    override fun mousePressed(e: MouseEvent) {}

                    override fun mouseReleased(e: MouseEvent) {}

                    /**
                     * hover effect
                     */
                    override fun mouseEntered(e: MouseEvent) {
                        panel.background = hoverColor
                    }

                    override fun mouseExited(e: MouseEvent) {
                        panel.background = color
                    }

                })
                add(panel)
            }
        }
    }
}