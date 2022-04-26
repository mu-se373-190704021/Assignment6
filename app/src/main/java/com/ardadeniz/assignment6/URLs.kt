package com.ardadeniz.assignment6


object URLs {

    /*val URL_LOGIN = "http://192.168.1.5:80/indexlogin.php/"
    val URL_REGISTER = "http://192.168.1.5:80/indexregister.php/" */

    private val ROOT_URL = "http://192.168.1.5:80/androidphpmysql/registrationapi.php?apicall="
    val URL_REGISTER = ROOT_URL + "signup"
    val URL_LOGIN = ROOT_URL + "login"
    val URL_DELETE = ROOT_URL + "delete"
}