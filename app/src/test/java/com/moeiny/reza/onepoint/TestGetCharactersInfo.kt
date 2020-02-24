package com.moeiny.reza.onepoint

import com.moeiny.reza.onepoint.API.service.FilmsService
import com.moeiny.reza.onepoint.model.Character
import com.moeiny.reza.onepoint.model.Films
import com.moeiny.reza.onepoint.utils.FilmsCallback
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.MockitoAnnotations
import java.util.concurrent.CountDownLatch

class TestGetCharactersInfo {
    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun testGetCharacterInfo() {

        val lock =  CountDownLatch(1)
        lateinit var character: Character
        var id=1;


        FilmsService.getCharactersInfo(id,object : FilmsCallback<Character, Throwable> {

            override fun onSuccess(result: Character) {
                Assert.assertNotNull(result)
                character = result
                lock.countDown()
            }

            override fun onError(error: Throwable?) {
                Assert.assertNotNull(error)
                lock.countDown()
            }

            override fun onComplete() {
                print("complete")
                lock.countDown()
            }
        })

        //checking for the test Result
        character.let {
            Assert.assertEquals(it.name, "Luke Skywalker")

        }
    }
}