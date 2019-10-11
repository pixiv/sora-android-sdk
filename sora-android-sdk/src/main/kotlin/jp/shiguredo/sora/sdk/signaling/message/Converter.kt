package jp.shiguredo.sora.sdk.signaling.message

import com.google.gson.Gson
import jp.shiguredo.sora.sdk.channel.option.SoraChannelRole
import jp.shiguredo.sora.sdk.channel.option.Configuration
import jp.shiguredo.sora.sdk.util.SoraLogger

class Converter {

    companion object {

        val TAG = Converter::class.simpleName

        val gson = Gson()

        @JvmOverloads
        fun buildConnect(role:                    SoraChannelRole,
                         channelId:               String?,
                         mediaOption:             Configuration,
                         metadata:                Any?,
                         sdp:                     String,
                         clientId:                String?          = null,
                         signalingNotifyMetadata: Any?             = null
        ): String {

            val msg = Connect(
                    role                    = role.toString().toLowerCase(),
                    channelId               = channelId,
                    metadata                = metadata,
                    multistream             = mediaOption.multistreamIsRequired,
                    sdp                     = sdp,
                    clientId                = clientId,
                    signalingNotifyMetadata = signalingNotifyMetadata
            )

            if (mediaOption.upstreamIsRequired) {
                // 配信者では audio, video は配信の設定
                if (mediaOption.audioUpstreamEnabled) {
                    val audioSetting = AudioSetting(mediaOption.audioCodec.toString())
                    mediaOption.audioBitrate?.let { audioSetting.bitRate = it }

                    if (mediaOption.audioOption?.opusParams != null) {
                        audioSetting.opusParams = mediaOption.audioOption?.opusParams
                    }

                    msg.audio = audioSetting

                } else {
                    msg.audio = false
                }
                if (mediaOption.videoUpstreamEnabled) {
                    val videoSetting = VideoSetting(mediaOption.videoCodec.toString())
                    mediaOption.videoBitrate?.let { videoSetting.bitRate = it }
                    msg.video = videoSetting
                } else {
                    msg.video = false
                }
            } else {
                // 視聴者では audio, video は視聴の設定
                if (mediaOption.audioDownstreamEnabled) {
                    val audioSetting = AudioSetting(mediaOption.audioCodec.toString())
                    // TODO(shino): 視聴側の bit_rate 設定はサーバで無視される
                    mediaOption.audioBitrate?.let { audioSetting.bitRate = it }
                    msg.audio = audioSetting
                } else {
                    msg.audio = false
                }
                if (mediaOption.videoDownstreamEnabled) {
                    val videoSetting = VideoSetting(mediaOption.videoCodec.toString())
                    // TODO(shino): 視聴側の bit_rate 設定はサーバで無視される
                    mediaOption.videoBitrate?.let { videoSetting.bitRate = it }
                    msg.video = videoSetting
                } else {
                    msg.video = false
                }
            }


            if (0 < mediaOption.spotlight) {
                msg.spotlight = mediaOption.spotlight
            }

            if (mediaOption.simulcastEnabled) {
                msg.simulcast = true
                msg.simulcast_rid = true
            }

            SoraLogger.d(TAG, "$msg")
            return gson.toJson(msg)
        }

        fun buildPong(): String {
            return gson.toJson(Pong())
        }

        fun buildUpdateAnswer(sdp: String): String {
            return gson.toJson(Update(sdp = sdp))
        }

        fun buildReAnswer(sdp: String): String {
            return gson.toJson(ReAnswer(sdp = sdp))
        }

        fun buildAnswer(sdp: String): String {
            return gson.toJson(Answer(sdp = sdp))
        }

        fun buildCandidate(sdp: String): String {
            return gson.toJson(Candidate(candidate = sdp))
        }

        fun parseType(text: String): String? {
            val part = gson.fromJson(text, CommonPart::class.java)
            return part.type
        }

        fun parseOffer(text: String): Offer {
            return gson.fromJson(text, Offer::class.java)
        }

        fun parseUpdate(text: String): Update {
            return gson.fromJson(text, Update::class.java)
        }

        fun parseReOffer(text: String): ReOffer {
            return gson.fromJson(text, ReOffer::class.java)
        }

        fun parseNotification(text: String): Notification {
            return gson.fromJson(text, Notification::class.java)
        }

        fun parsePush(text: String): Push {
            return gson.fromJson(text, Push::class.java)
        }
    }
}

