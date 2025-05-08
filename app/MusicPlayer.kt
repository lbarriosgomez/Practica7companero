import android.content.Context
import android.media.MediaPlayer

object MusicPlayser {

    private var mediaPlayer: MediaPlayer? = null

    // Inicia la música SOLO si aún no está sonando
    fun startMusic(context: Context, musicResId: Int) {
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(context, musicResId)
            mediaPlayer?.isLooping = true  // La música se repetirá indefinidamente
            mediaPlayer?.start()
        }
    }

    // Cambia la música actual por otra nueva
    fun changeMusic(context: Context, newMusicResId: Int) {
        mediaPlayer?.stop()  // Detiene la música actual
        mediaPlayer?.release()  // Libera recursos de la memoria
        mediaPlayer = MediaPlayer.create(context, newMusicResId)  // Carga nueva música
        mediaPlayer?.isLooping = true  // Hace que la nueva música sea infinita
        mediaPlayer?.start()  // Inicia la nueva música
    }

    // Detiene la música cuando ya no se necesita
    fun stopMusic() {
        mediaPlayer?.stop()
        mediaPlayer?.release()
        mediaPlayer = null
    }

}