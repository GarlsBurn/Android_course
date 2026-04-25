package di

import androidx.datastore.core.DataStore
import com.sun.tools.javac.util.Context
import kotlinx.io.files.FileSystem
import preferences.Preferences
import preferences.createDataStore


object AndroidInjectionCompanion {
    private var dataStore: DataStore<Preferences>? = null

    fun getDataStore(context: Context): DataStore<Preferences> {
        dataStore?.let { return it }

        val store = createDataStore(
            fileSystem = FileSystem.SYSTEM,
            producePath = {
                context.filesDir.resolve("preferences.json").toOkioPath()
            }
        )

        dataStore = store
        return store
    }
}