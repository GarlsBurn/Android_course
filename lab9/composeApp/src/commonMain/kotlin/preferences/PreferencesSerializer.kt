package preferences


import androidx.datastore.core.okio.OkioSerializer
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import okio.BufferedSink
import okio.BufferedSource
import okio.ByteString.Companion.encodeUtf8

class PreferencesSerializer : OkioSerializer<Preferences> {
    override val defaultValue: Preferences
        get() = Preferences()

    override suspend fun readFrom(source: BufferedSource): Preferences {
        return Json.decodeFromString(
            source.readByteString().utf8()
        )
    }

    override suspend fun writeTo(t: Preferences, sink: BufferedSink) {
        sink.write(
            Json.encodeToString(t).encodeUtf8()
        )
    }
}