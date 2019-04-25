package core

import gui.ApplicationFrame
import gui.fx.LandingPane

import gui.swing.ArtistToolsPanel
import gui.swing.InternalDrawingPanel

/**
 * Created by HP xw8400
 * Author: Jacob
 * Date: 4/24/2019.
 */

class PictionaryContext {

    val tools = ArtistToolsPanel(this)

    val drawing = InternalDrawingPanel(this)

    val frame = ApplicationFrame(this)

    init {
        frame.addPanels(tools, drawing)
    }
}