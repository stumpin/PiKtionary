package core

import javax.swing.SwingUtilities
import javax.swing.UIManager

/**
 * Created by HP xw8400
 * Author: Jacob
 * Date: 4/23/2019.
 */
fun main() {

    try {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName())
    } catch (e: Exception) {
        e.printStackTrace()
    }

    SwingUtilities.invokeLater {
        val context = PictionaryContext()
    }
}