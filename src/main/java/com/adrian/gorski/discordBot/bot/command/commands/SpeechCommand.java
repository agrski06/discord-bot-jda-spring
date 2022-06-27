package com.adrian.gorski.discordBot.bot.command.commands;

import com.adrian.gorski.discordBot.bot.command.Command;
import com.adrian.gorski.discordBot.bot.functionality.StaticMethods;
import com.adrian.gorski.discordBot.tts.TextToSpeech;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.List;

public class SpeechCommand extends Command {

    private final List<String> langs = List.of("es", "pl", "fr", "ko");

    public SpeechCommand() {
        aliases = List.of("speech", "sp", "tts");
        doesTakeArgs = true;
    }

    @Override
    public void handle(MessageReceivedEvent event) {
        if (!StaticMethods.connectIfDisconnected(event)) return;

        TextToSpeech tts = new TextToSpeech();
        try {
            String language;

            if (args.startsWith("-")) {
                language = args.substring(1, args.indexOf(" "));
                args = args.substring(args.indexOf(" "));
            } else {
                language = "pl";
            }

            if (!langs.contains(language)) {
                event.getTextChannel().sendMessage("Invalid language!").queue();
                return;
            }

            tts.speech(event.getTextChannel(), args, language);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getName() {
        return "speech";
    }

    @Override
    public String getHelp() {
        return "(-`lang`) `text`\n"
                + "Languages: " + String.join(", ", langs)
                + "\nSpeaks given phrase. Language is optional, default is pl";
    }

}

//        "af": "Afrikaans",
//        "ar": "Arabic",
//        "bg": "Bulgarian",
//        "bn": "Bengali",
//        "bs": "Bosnian",
//        "ca": "Catalan",
//        "cs": "Czech",
//        "cy": "Welsh",
//        "da": "Danish",
//        "de": "German",
//        "el": "Greek",
//        "en": "English",
//        "eo": "Esperanto",
//        "es": "Spanish",
//        "et": "Estonian",
//        "fi": "Finnish",
//        "fr": "French",
//        "gu": "Gujarati",
//        "hi": "Hindi",
//        "hr": "Croatian",
//        "hu": "Hungarian",
//        "hy": "Armenian",
//        "id": "Indonesian",
//        "is": "Icelandic",
//        "it": "Italian",
//        "iw": "Hebrew",
//        "ja": "Japanese",
//        "jw": "Javanese",
//        "km": "Khmer",
//        "kn": "Kannada",
//        "ko": "Korean",
//        "la": "Latin",
//        "lv": "Latvian",
//        "mk": "Macedonian",
//        "ms": "Malay",
//        "ml": "Malayalam",
//        "mr": "Marathi",
//        "my": "Myanmar (Burmese)",
//        "ne": "Nepali",
//        "nl": "Dutch",
//        "no": "Norwegian",
//        "pl": "Polish",
//        "pt": "Portuguese",
//        "ro": "Romanian",
//        "ru": "Russian",
//        "si": "Sinhalese",
//        "sk": "Slovak",
//        "sq": "Albanian",
//        "sr": "Serbian",
//        "su": "Sundanese",
//        "sv": "Swedish",
//        "sw": "Swahili",
//        "ta": "Tamil",
//        "te": "Telugu",
//        "th": "Thai",
//        "tl": "Filipino",
//        "tr": "Turkish",
//        "uk": "Ukrainian",
//        "ur": "Urdu",
//        "vi": "Vietnamese",
//        "zh-CN": "Chinese",

