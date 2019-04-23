package game

import network.Host
import network.Player
import java.util.concurrent.ConcurrentHashMap

/**
 * Created by HP xw8400
 * Author: Jacob
 * Date: 4/22/2019.
 */
internal class GameSession(host : Host) {

    val players = ConcurrentHashMap<String, Player>()


}