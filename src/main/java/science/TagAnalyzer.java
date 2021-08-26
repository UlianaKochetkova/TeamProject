package science;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

class TagAnalyzer {
    Map<Tag, Integer> analyze(String text) {
        if (!text.isEmpty()) {
            Map<Tag, Integer> tags = new HashMap<>();
            text = text.replace(" ", "%20");
            try {
                URL url = new URL("http://termextract.fivefilters.org/extract.php?output=json&lowercase=1&maxwords=1&text=" + text);
                URLConnection urlConnection = url.openConnection();
                BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                String inputLine = in.readLine();
                System.out.println("tags: " + inputLine);
                for (String tagString : inputLine.split("],\\[")) {
                    tagString = tagString
                            .replaceAll("]", "")
                            .replaceAll("\\[", "")
                            .replaceAll("\"", "");
                    String[] tag = tagString.split(",");
                    System.out.println(tagString);
                    tags.put(new Tag(Tag.getNewId(), tag[0]), Integer.valueOf(tag[1]));
                }
                in.close();
                return tags;
            } catch (IOException e) {
                e.printStackTrace();
                return Collections.emptyMap();
            }
        } else {
            return Collections.emptyMap();
        }
    }
}
