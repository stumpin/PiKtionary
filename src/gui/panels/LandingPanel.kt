package gui.panels

import core.PictionaryContext
import gui.StyleConstants
import javafx.application.Platform
import javafx.embed.swing.JFXPanel
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.layout.FlowPane


/**
 * Created by HP xw8400
 * Author: Jacob
 * Date: 4/23/2019.
 */

class LandingPanel(val pictionary: PictionaryContext) : JFXPanel() {

    init {
        preferredSize = StyleConstants.DEFAULT_SIZE

        Platform.runLater {
            scene = buildScene()
        }
    }

    private fun buildScene() : Scene {
        val pane = FlowPane()

        val button = Button("Free draw")
        button.setOnAction {
            pictionary.frame.showLast()
        }

        pane.children.add(button)

        return Scene(pane)
    }
}