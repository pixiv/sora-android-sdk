package jp.shiguredo.sora.sdk.error

import java.lang.Error

enum class SoraErrorType {
    CONNECTION_CANCELLED,
    CONNECTION_TIMEOUT,
    CONNECTION_BUSY,

    WEBSOCKET_FAILURE,

    INVALID_SIGNALING,
    UNKNOWN_SIGNALING,
    SIGNALING_FAILURE,

    PEER_CHANNEL_FAILURE,
}

class SoraError : Error {

    val type: SoraErrorType

    constructor(type: SoraErrorType) : super() {
        this.type = type
    }

    constructor(type: SoraErrorType, message: String) : super(message) {
        this.type = type
    }

}