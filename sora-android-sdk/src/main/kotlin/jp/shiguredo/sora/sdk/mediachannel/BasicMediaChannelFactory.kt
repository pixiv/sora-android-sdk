package jp.shiguredo.sora.sdk.mediachannel

import jp.shiguredo.sora.sdk.connection.ConnectionTask
import jp.shiguredo.sora.sdk.error.SoraError
import jp.shiguredo.sora.sdk.mediachannel.configuration.Configuration

class BasicMediaChannelFactory: MediaChannelFactory {

    override fun connect(configuration: Configuration,
                         handler: (MediaChannel?, SoraError?) -> Unit): ConnectionTask {
        // TODO
        return ConnectionTask()
    }

}