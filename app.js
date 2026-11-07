const tracks = [
  {
    title: 'SoundHelix Song 1',
    url: 'https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3',
  },
  {
    title: 'SoundHelix Song 2',
    url: 'https://www.soundhelix.com/examples/mp3/SoundHelix-Song-2.mp3',
  },
  {
    title: 'SoundHelix Song 3',
    url: 'https://www.soundhelix.com/examples/mp3/SoundHelix-Song-3.mp3',
  },
];

const trackTitle = document.getElementById('track-title');
const audioPlayer = document.getElementById('audio-player');
const trackList = document.getElementById('track-list');

tracks.forEach((track) => {
  const item = document.createElement('li');
  const button = document.createElement('button');
  button.type = 'button';
  button.textContent = track.title;
  button.addEventListener('click', () => {
    if (audioPlayer.src !== track.url) {
      audioPlayer.src = track.url;
    }
    trackTitle.textContent = track.title;
    void audioPlayer.play();
  });
  item.appendChild(button);
  trackList.appendChild(item);
});
