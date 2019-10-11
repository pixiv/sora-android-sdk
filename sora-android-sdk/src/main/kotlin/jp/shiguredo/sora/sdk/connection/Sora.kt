package jp.shiguredo.sora.sdk.connection

import android.content.Context
import jp.shiguredo.sora.sdk.error.SoraError
import jp.shiguredo.sora.sdk.mediachannel.BasicMediaChannelFactory
import jp.shiguredo.sora.sdk.mediachannel.configuration.Configuration
import jp.shiguredo.sora.sdk.mediachannel.MediaChannel
import jp.shiguredo.sora.sdk.mediachannel.MediaChannelFactory

object Sora {

    var mediaChannelFactory: MediaChannelFactory = BasicMediaChannelFactory()
    var mediaChannels: List<MediaChannel> = emptyList()

    fun connect(context: Context,
                configuration: Configuration,
                handler: (MediaChannel?, SoraError?) -> Unit): ConnectionTask {
        return mediaChannelFactory.connect(configuration, handler)
    }

}