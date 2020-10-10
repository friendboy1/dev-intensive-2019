package ru.skillbranch.devintensive.viewmodels

import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.skillbranch.devintensive.models.Profile
import ru.skillbranch.devintensive.repositories.PreferencesRepository

/**
 * ViewModel для отображения Профиля
 *
 * @author Andrei Khromov on 2019-08-03
 */
class ProfileViewModel : ViewModel() {
    private val repository: PreferencesRepository = PreferencesRepository
    private val profileData = MutableLiveData<Profile>()
    private val appTheme = MutableLiveData<Int>()
    private val repositoryError = MutableLiveData<Boolean>()
    private val isRepositoryError = MutableLiveData<Boolean>()

    init {
        Log.d(javaClass.simpleName, "init Profile ViewModel")
        profileData.value = repository.getProfile()
        appTheme.value = repository.getAppTheme()
    }

    override fun onCleared() {
        super.onCleared()
        Log.d(javaClass.simpleName, "clear view model")

    }

    fun getIsRepositoryError():LiveData<Boolean> = isRepositoryError

    fun getRepositoryError(): LiveData<Boolean> = repositoryError

    fun getProfileData() : LiveData<Profile> = profileData

    fun getTheme(): LiveData<Int> = appTheme

    fun saveProfileData(profile: Profile) {
        repository.saveProfile(profile)
        profileData.value = profile
    }

    fun switchTheme() {
        if (appTheme.value == AppCompatDelegate.MODE_NIGHT_YES) {
            appTheme.value = AppCompatDelegate.MODE_NIGHT_NO
        } else {
            appTheme.value = AppCompatDelegate.MODE_NIGHT_YES
        }
        repository.saveAppTheme(appTheme.value!!)
    }


    fun onRepositoryChanged(repository: String) {
        repositoryError.value = isValidateRepository(repository)
    }


    fun onRepositoryEditComplete(isError: Boolean) {
        isRepositoryError.value = isError
    }

    private fun isValidateRepository(repoText: String): Boolean {
        val regexStr = "^(https:\\/\\/)?(www\\.)?(github\\.com\\/)(?!(${getRegexExceptions()})(?=\\/|\$))[a-zA-Z\\d](?:[a-zA-Z\\d]|-(?=[a-zA-Z\\d])){0,38}(\\/)?$"
        val regex = Regex(regexStr)

        return (repoText.isNotEmpty() && !regex.matches(repoText))
    }

    private fun getRegexExceptions(): String {
        val exceptions = arrayOf(
            "enterprise", "features", "topics", "collections", "trending", "events", "marketplace", "pricing",
            "nonprofit", "customer-stories", "security", "login", "join"
        )
        return exceptions.joinToString("|")
    }

}