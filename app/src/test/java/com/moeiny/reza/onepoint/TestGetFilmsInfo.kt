package com.moeiny.reza.onepoint

import com.moeiny.reza.onepoint.API.Apiservice.FilmsService
import com.moeiny.reza.onepoint.model.Films
import com.moeiny.reza.onepoint.utils.FilmsCallback
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.MockitoAnnotations
import java.util.concurrent.CountDownLatch

class TestGetFilmsInfo {
    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun testGetFilmInfo() {

        val lock =  CountDownLatch(1)
        lateinit var films: Films


        FilmsService.getFilmsInfo(object : FilmsCallback<Films, Throwable> {

            override fun onSuccess(result: Films) {
                Assert.assertNotNull(result)
                films = result
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
        films.let {
            Assert.assertEquals(it.count, 7)

        }
    }
}