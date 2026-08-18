const musicButton = document.getElementById("music-button")
const themeMusic = document.getElementById("theme-music")

musicButton.addEventListener("click", function () {
  if (themeMusic.paused) {
    themeMusic.play()
    musicButton.textContent = "⏸ Pause Moron Mountain Radio"
  } else {
    themeMusic.pause()
    musicButton.textContent = " Moron Mountain Radio"
  }
})
