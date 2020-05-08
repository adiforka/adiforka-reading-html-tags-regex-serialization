package html_regex_serialization;

import java.util.List;

public class TagHandlerDemo {
    //threw exceptions up the chain
    public static void main(String[] args) throws Exception {

        TagHandler tagHandler = new TagHandler();

        //read tags from file and store in a list of strings
        //!! this regex does not find tags nested within tags
        List<String> tags = tagHandler.getTagsAsList("^<(?<TAG>.+)>[^<]*</\\k<TAG>>$",
                "src\\html_regex_serialization\\tags.txt");

        //serialize string tags into dat file
        tagHandler.serializeTags(tags, "src\\html_regex_serialization\\target_file.dat");
        tags.forEach(System.out::println);
    }}
