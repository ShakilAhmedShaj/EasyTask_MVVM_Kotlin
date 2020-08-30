package com.decimalab.easytask.util

import com.decimalab.easytask.util.Validator.validatePassword
import org.junit.Assert.*
import org.junit.Test

/**
 * Created by Shakil Ahmed Shaj on 26,April,2020.
 * shakilahmedshaj@gmail.com
 */

class ValidatorTest {


    @Test
    fun validateEmail_whenValidEmail_returnTrue() {
        val email = "demo1@gmail.com"

        val result = Validator.validateEmail(email)

        assertEquals(true, result)
    }

    @Test
    fun validatePassword_whenValidPassword_returnTrue() {

        val password = "123456"

        val result = validatePassword(password)

        //assertEquals(true, result)
        assertTrue(result)

    }

}