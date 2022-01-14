class Song(val title: String, val artist: String) {
    fun play()  {
        println("Playing the song $title by $artist")
    }
    fun stop() {
        println("Stopped playing $title")
    }
}

fun main() {
    val songsOne = Song("The Mesopotamians", "They Might Be Giants")
    val songTwo = Song("Going Underground", "The Jam")
    val songThree = Song("Make Me Smile", "Steve Harley")

    songTwo.play()
    songTwo.stop()
    songThree.play()

}

