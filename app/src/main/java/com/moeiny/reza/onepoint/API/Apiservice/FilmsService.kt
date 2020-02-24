package com.moeiny.reza.onepoint.API.Apiservice

import com.moeiny.reza.onepoint.API.repo.FilmsRepositoryProvider
import com.moeiny.reza.onepoint.model.Character
import com.moeiny.reza.onepoint.model.Films
import com.moeiny.reza.onepoint.utils.FilmsCallback
import com.moeiny.reza.onepoint.utils.okHttpClientGETBuilder
import com.moeiny.reza.onepoint.utils.retrofit
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

open class FilmsService private constructor() {

    companion object {
        private var compositeDisposable: CompositeDisposable = CompositeDisposable()

        fun getFilmsInfo(block: FilmsCallback<Films, Throwable>) {
            val disposableService: Disposable =
                FilmsRepositoryProvider.provideFilmRepository(retrofit(okHttpClientGETBuilder()))
                    .getFilmsInfo()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
//                .observeOn(Schedulers.computation())
                    .subscribe(
                        { result ->
                            block.onSuccess(result = result)
                        },
                        { error ->
                            block.onError(error)
                        },
                        {
                            block.onComplete()
                        }
                    )

            compositeDisposable.add(disposableService)

        }


        fun getCharactersInfo(id: Int, block: FilmsCallback<Character, Throwable>) {
            val disposableService: Disposable =
                FilmsRepositoryProvider.provideFilmRepository(retrofit(okHttpClientGETBuilder()))
                    .getCharactersInfo(id)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
//                .observeOn(Schedulers.computation())
                    .subscribe(
                        { result ->
                            block.onSuccess(result = result)
                        },
                        { error ->
                            block.onError(error)
                        },
                        {
                            block.onComplete()
                        }
                    )

            compositeDisposable.add(disposableService)

        }
    }

}