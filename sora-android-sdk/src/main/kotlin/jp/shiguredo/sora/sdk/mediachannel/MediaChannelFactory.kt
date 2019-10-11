package jp.shiguredo.sora.sdk.mediachannel

import jp.shiguredo.sora.sdk.connection.ConnectionTask
import jp.shiguredo.sora.sdk.error.SoraError
import jp.shiguredo.sora.sdk.mediachannel.configuration.Configuration

interface MediaChannelFactory {

    fun connect(configuration: Configuration,
                handler: (MediaChannel?, SoraError?) -> Unit): ConnectionTask

}