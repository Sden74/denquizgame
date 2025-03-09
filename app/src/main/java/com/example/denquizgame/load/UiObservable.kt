package com.example.denquizgame.load


interface UiObservable {

    fun register(observer: (LoadUiState) -> Unit)
    fun unregister()

    fun postUiState(uiState: LoadUiState)

    class Base() : UiObservable {
        private var uiStateCached: LoadUiState? = null // progress, success, fail
        private var observerCached: ((LoadUiState) -> Unit)? = null //aka fragment

        override fun register(observer: (LoadUiState) -> Unit) {// onResume
            observerCached = observer
            if (uiStateCached != null) {
                observerCached!!.invoke(uiStateCached)
                uiStateCached = null
            }
        }

        override fun unregister() { // onPause
            observerCached = null
        }

        override fun postUiState(uiState: LoadUiState) { //pinged by ViewModel asynchronously
            if (observerCached == null) { //onPause was called, but onResume still not
                uiStateCached = uiState // save ui state till fragment become onResume
            } else {
                observerCached!!.invoke(uiState) // after onResume and till onPause
                uiStateCached = null
            }
        }

    }
}

class YouTubeChannel {
    private val set = mutableSetOf<YouTubeSubscriber>()
    fun subscribe(subscriber: YouTubeSubscriber) {
        set.add(subscriber)
    }

    fun unsubscribe(subscriber: YouTubeSubscriber) {
        set.remove(subscriber)
    }

    fun postVideo(video: YouTubeVideo) {
        set.forEach {
            it.notifyNewVideo(video)
        }
    }
}

class YouTubeChannelAuthor {
    fun postNewVideo(myChannel: YouTubeChannel) {
        myChannel.postVideo(YouTubeVideo("https://www.youtube.com/"))
    }
}

interface YouTubeSubscriber {
    fun notifyNewVideo(video: YouTubeVideo)
}

class YouTubeVideo(private val url: String)
