import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

// URL Counter without MapReduce and without regex
// I've used generative AI to make the code below
public class UrlCountwoMR{

    public static void main(String[] args) throws Exception {

        String inputFile = args[0];
        String outputFile = args[1];

        Map<String, Integer> urlCount = new HashMap<>();

        BufferedReader reader = new BufferedReader(
                new FileReader(inputFile)
        );

        String line;

        while ((line = reader.readLine()) != null) {

            StringTokenizer itr = new StringTokenizer(line);

            while (itr.hasMoreTokens()) {

                String token = itr.nextToken();

                if (token.startsWith("href=\"")) {

                    String urlFound = token.substring(6);

                    if (urlFound.endsWith("\"")) {
                        urlFound = urlFound.substring(
                                0,
                                urlFound.length() - 1
                        );
                    }

                    if (urlCount.containsKey(urlFound)) {
                        urlCount.put(
                                urlFound,
                                urlCount.get(urlFound) + 1
                        );
                    } else {
                        urlCount.put(urlFound, 1);
                    }
                }
            }
        }

        reader.close();

        PrintWriter writer = new PrintWriter(
                new FileWriter(outputFile)
        );

        for (Map.Entry<String, Integer> entry : urlCount.entrySet()) {

            String url = entry.getKey();
            int count = entry.getValue();

            if (count > 5) {
                writer.println(url + "\t" + count);
            }
        }

        writer.close();
    }
}