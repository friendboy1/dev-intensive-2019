package ru.skillbranch.devintensive.models

import ru.skillbranch.devintensive.utils.Utils

/**
 * @author Andrei Khromov on 2019-08-03
 */
data class Profile(
    val firstName: String,
    val lastName: String,
    val about: String,
    val repository: String,
    val rating: Int = 0,
    val respect: Int = 0
) {
    val nickName: String = Utils.toInitials(firstName, lastName).orEmpty()
    val rank: String = "Junior Android Developer"

    fun toMap(): Map<String, Any> = mapOf(
        "nickName" to nickName,
        "rank" to rank,
        "firstName" to firstName,
        "lastName" to lastName,
        "about" to about,
        "repository" to repository,
        "rating" to rating,
        "respect" to respect
    )
}