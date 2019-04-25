package core

import gui.ApplicationFrame

import gui.panels.ArtistToolsPanel
import gui.panels.InternalDrawingPanel
import gui.panels.LandingPanel

/**
 * Created by HP xw8400
 * Author: Jacob
 * Date: 4/24/2019.
 */

class PictionaryContext {

    val landing = LandingPanel(this)

    val tools = ArtistToolsPanel(this)

    val drawing = InternalDrawingPanel(this)

    val frame = ApplicationFrame(this)

    init {
        // the landing panel
        frame.addPanels(landing)
        // the drawing panel
        frame.addPanels(tools, drawing)

    }
}