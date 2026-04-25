package preferences


import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.core.okio.OkioStorage
import okio.FileSystem
import okio.Path

fun createDataStore(
    fileSystem: FileSystem,
    producePath: () -> Path
): DataStore<Preferences> {
    return DataStoreFactory.create(
        OkioStorage(
            fileSystem = fileSystem,
            serializer = PreferencesSerializer(),
            producePath = producePath
        )
    )
}