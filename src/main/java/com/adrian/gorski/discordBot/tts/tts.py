from gtts import gTTS
import sys

mytext = sys.argv[1] + " "
if len(sys.argv) > 3:
    for i in range(2, len(sys.argv)-1):
        mytext += sys.argv[i] + " "
  
language = sys.argv[len(sys.argv)-1]

myobj = gTTS(text=mytext, lang=language, slow=False)

myobj.save("speech.mp3")
  
