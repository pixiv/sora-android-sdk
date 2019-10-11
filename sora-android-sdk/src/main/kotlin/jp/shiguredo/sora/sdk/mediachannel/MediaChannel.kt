package jp.shiguredo.sora.sdk.mediachannel

import jp.shiguredo.sora.sdk.error.SoraError
import kotlinx.coroutines.handleCoroutineException

interface MediaChannel {

    class Listener {

        internal var onDisconnectHandler: ((SoraError?) -> Unit)? = null

        fun onDisconnect(handler: (SoraError?) -> Unit) {
            onDisconnectHandler = handler
        }

    }

    var listener: Listener

    fun disconnect(error: SoraError? = null)

}