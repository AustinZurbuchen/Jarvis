import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;
import edu.cmu.sphinx.api.SpeechResult;
import java.io.IOException;

public class Jarvis {
    public static void main(String[] args) throws IOException {
        // Configuration Object
        Configuration configuration = new Configuration();

        // Set path to the acoustic model.
        configuration.setAcousticModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us");
        // Set path to the dictionary.
        configuration.setDictionaryPath("2323.dic");
        // Set path to the language model.
        configuration.setLanguageModelPath("2323.lm");

        //Recognizer Object, Pass the Configuration object
        LiveSpeechRecognizer recognize = new LiveSpeechRecognizer(configuration);

        //Start Recognition Process (The bool parameter clears the previous cache if true)
        recognize.startRecognition(true);

        //Create SpeechResult Object
        SpeechResult result;

        //Checking if recognizer has recognized the speech
        boolean active = false;
        while ((result = recognize.getResult()) != null) {
            //Get the recognize speech
            String command = result.getHypothesis();
            String work = null;
            Process p;

            if(active) {
                switch (command.toLowerCase()) {
                    case "open file manager":
                        work = "explorer.exe";
                        break;
                    case "open browser":
                        work = "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe";
                        break;
                    case "close browser":
                        work = "taskkill /lm chrome.exe /f";
                        break;
                    case "terminate program":
                        System.exit(0);
                        break;
                    case "open steam":
                        work = "C:\\Program Files (x86)\\Steam\\Steam.exe";
                        break;
                    case "close steam":
                        System.out.println("Doesn't work currently");
                        work = "taskkill /lm Steam.exe /f /t";
                        break;
                    case "open battle net":
                        work = "C:\\Program Files (x86)\\Battle.net\\Battle.net.exe";
                        break;
                    case "close battle net":
                        work = "taskkill /lm Battle.net.exe /f";
                        break;
                    case "open command prompt":
                        System.out.println("Doesn't work currently");
                        work = "C:\\WINDOWS\\system32\\cmd.exe";
                        break;
                    case "close command prompt":
                        System.out.println("Doesn't work currently");
                        work = "taskkill /lm cmd.exe /f /t";
                        break;
                    case "take a break jarvis":
                        System.out.println("Thank you Sir!");
                        active = false;
                        work = null;
                        break;
                    default:
                        System.out.println("Did not understand command!");
                        break;
                }
            } else {
                if(command.equalsIgnoreCase("jarvis")){
                    System.out.println("Yes Sir?");
                    active = true;
                }
            }
            //In case command recognized is none of the above hence work might be null
            if(work != null) {
                //Execute the command
                p = Runtime.getRuntime().exec(work);
            }
        }
    }
}
