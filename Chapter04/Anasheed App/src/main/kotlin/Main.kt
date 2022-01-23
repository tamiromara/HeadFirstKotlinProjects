

class Song(val title: String, val artist: String) {
    fun play()  {
        println("Playing the song $title by $artist")
    }
    fun stop() {
        println("Stopped playing $title")
    }
}

fun main() {
    val nasheedOne = Song("The Mesopotamians", "They Might Be Giants")
    val nasheedTwo = Song("Going Underground", "The Jam")
    val nasheedThree = Song("Make Me Smile", "Steve Harley")

    nasheedTwo.play()
    nasheedTwo.stop()
    nasheedThree.play()

}

