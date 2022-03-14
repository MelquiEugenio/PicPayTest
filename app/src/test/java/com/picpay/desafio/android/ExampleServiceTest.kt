package com.picpay.desafio.android

import com.nhaarman.mockitokotlin2.mock
import com.picpay.desafio.android.data.network.api.PicPayService
import com.picpay.desafio.android.data.network.dto.UserDto
import junit.framework.Assert.assertEquals
import org.junit.Test

class ExampleServiceTest {

    private val api = mock<PicPayService>()

    private val service = ExampleService(api)

    @Test
    fun exampleTest() {
        // given
        val call = mock<List<UserDto>>()
        val expectedUsers = emptyList<UserDto>()

        //whenever(call.execute()).thenReturn(Response.success(expectedUsers))

        //whenever(api.getUsers()).thenReturn(call)

        // when
        val users = service.example()

        // then
        assertEquals(users, expectedUsers)
    }
}