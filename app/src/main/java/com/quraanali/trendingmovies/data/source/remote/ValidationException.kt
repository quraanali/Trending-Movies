package com.quraanali.trendingmovies.data.source.remote

import com.squareup.moshi.Moshi

@Suppress("UNCHECKED_CAST")
class ValidationException(
    moshi: Moshi,
    errSource: String? = null,
) : Exception() {

    var validationCode: Int? = null

    private var validationMessage: String? = null
    private var validation: HashMap<EnumFormValidation, String> = HashMap()

    init {
        if (errSource == null) throw Exception()

        val jsonAdapter = moshi.adapter(ErrorResponse::class.java)
        val response = jsonAdapter?.fromJson(errSource)
        response?.meta?.also {
            validationMessage = it.message
            validationCode = it.code

            if (it.errors is Map<*, *>) {
                createValidationMapping(it.errors as Map<String, Any>)
            }

            validation[EnumFormValidation.STORE_CODE] = it.message ?: ""
        }
    }

    override fun getLocalizedMessage(): String? {
        return validationMessage
    }

    //{"validation_errors":{"address":{"country":["Country not supported"],"city":["This field may not be blank."]}}}
    private fun createValidationMapping(error: Map<String, Any>) {
        error.keys.forEach { key ->
            when (val value = error[key]) {
                is MutableList<*> -> {
                    validation[EnumFormValidation.mappingOf(key)] =
                        if (value.isEmpty()) "" else value.first().toString()

                }
                is Map<*, *> -> {
                    createValidationMapping(value as Map<String, Any>)
                }
                else -> {
                    validation[EnumFormValidation.mappingOf(key)] =
                        value.toString()
                }
            }
        }
    }

    enum class EnumFormValidation {
        NON,
        STORE_CODE;

        companion object {
            fun mappingOf(value: String?) =
                values().find { it.name.lowercase() == value } ?: NON
        }
    }

}