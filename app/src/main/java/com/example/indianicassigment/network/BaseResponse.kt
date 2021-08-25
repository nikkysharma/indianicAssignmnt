package com.example.indianicassigment.network

/**
 * Created by admin on 12/30/2015.
 *
 * @param <T> the type parameter
</T> */
class BaseResponse<T> {
    /**
     * Gets message.
     *
     * @return the message
     */
    /**
     * Sets message.
     *
     * @param message the message
     */
    var message: String = ""
    var status = 0
    /**
     * Gets code.
     *
     * @return the code
     */
    /**
     * Sets code.
     *
     * @param code the code
     */
    var code: String = "0"

    /**
     * Gets data.
     *
     * @return the data
     */
    var data: T? = null
        private set
    /**
     * Gets errors.
     *
     * @return the errors
     */
    /**
     * Sets errors.
     *
     * @param errors the errors
     */
    var errors: String? = null
    /**
     * Gets version.
     *
     * @return the version
     */
    /**
     * Sets version.
     *
     * @param version the version
     */
    var version: String? = null

    /**
     * Sets data.
     *
     * @param data the data
     */
    fun setData(data: T) {
        this.data = data
    }
}