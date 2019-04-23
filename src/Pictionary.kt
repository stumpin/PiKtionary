import game.GameSession
import gui.ApplictionFrame
import network.Host
import javax.swing.SwingUtilities

/**
 * Created by HP xw8400
 * Author: Jacob
 * Date: 4/22/2019.
 */

class Pictionary {

    companion object {

        @JvmStatic
        fun main(args: Array<String>) {
            SwingUtilities.invokeLater {
                val frame = ApplictionFrame()
                //val session = GameSession(Host())
            }
        }
    }
}