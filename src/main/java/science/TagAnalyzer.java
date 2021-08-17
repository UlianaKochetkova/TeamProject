package science;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class TagAnalyzer {
    List<Tag> analyze(String text) {
        if (!text.isEmpty()) {
            List<Tag> tags = new ArrayList<>();
            text = text.replace(" ", "%20");
            try {
                URL url = new URL("http://termextract.fivefilters.org/extract.php?output=txt&lowercase=1&maxwords=2&text=" + text);
                URLConnection urlConnection = url.openConnection();
                BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    System.out.println("tag: " + inputLine);
                    tags.add(new Tag(Tag.getNewId(), inputLine));
                }
                in.close();
                return tags;
            } catch (IOException e) {
                e.printStackTrace();
                return Collections.emptyList();
            }
        } else {
            return Collections.emptyList();
        }
    }
}
