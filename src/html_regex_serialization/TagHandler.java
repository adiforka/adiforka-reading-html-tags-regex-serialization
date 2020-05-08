package html_regex_serialization;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class TagHandler {

    //biFunction style to break down?
    public List<String> getTagsAsList(String regex, String filePath) throws Exception {

        Pattern pattern = Pattern.compile(regex);
        List<String> lines = new ArrayList<>();
        List<String> tags;

        try (BufferedReader br = new BufferedReader(
                new FileReader(new File(filePath)))) {

            String line;
            while ((line = br.readLine()) != null) {

                lines.add(line);
            }

            //using the pattern to get a predicate and filter out lines that are not tags
            tags = lines.stream()
                    .filter(pattern.asPredicate())
                    .collect(Collectors.toList());

        }
        return tags;
    }

    public void serializeTags(List<String> tags, String targetFilePath) throws IOException {

        try (ObjectOutputStream fos = new ObjectOutputStream(new BufferedOutputStream(
                new FileOutputStream(targetFilePath)))) {

            for (String tag : tags) {
                fos.writeObject(tag);
            }
        }
    }
}