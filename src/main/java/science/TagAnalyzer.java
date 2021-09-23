package science;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

class TagAnalyzer {

    private String maxTagsCount = "7";

    private String outputFormat = "json";

    private String maxWordsInTag = "1";

    Map<Tag, Integer> analyze(String text) {
        if (!text.isEmpty()) {
            Map<Tag, Integer> tags = new HashMap<>();
            try {
                HttpClient httpclient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost("http://termextract.fivefilters.org/extract.php");

                MultipartEntity reqEntity = new MultipartEntity();
                reqEntity.addPart("text_or_url", new StringBody(text));
                reqEntity.addPart("max", new StringBody(maxTagsCount));
                reqEntity.addPart("output", new StringBody(outputFormat));
                reqEntity.addPart("max_strength", new StringBody(maxWordsInTag));
                reqEntity.addPart("submit", new StringBody("Extract Terms"));
                httpPost.setEntity(reqEntity);

                HttpResponse response = httpclient.execute(httpPost);
                BufferedReader in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                String inputLine = in.readLine();
                System.out.println("tags: " + inputLine);
                for (String tagString : inputLine.split("],\\[")) {
                    tagString = tagString
                            .replaceAll("]", "")
                            .replaceAll("\\[", "")
                            .replaceAll("\"", "");
                    String[] tag = tagString.split(",");
                    System.out.println(tagString);
                    if (!tag[0].equals("")) {
                        tags.put(new Tag(Tag.getNewId(), tag[0]), Integer.valueOf(tag[1]));
                    }
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
