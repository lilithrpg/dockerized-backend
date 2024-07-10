package com.test.notification.Model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Data
public class Template {
    private String title;
    private String body;

    public Template() {}

    public Template(String title, String body) {
        this.title = title;
        this.body = body;
    }

    // converts mustached values {{name}} to their designated values
    private String replaceTextWithDesignatedValues(String input, Map<String, String> values) {
        Pattern pattern = Pattern.compile("\\{\\{(\\w+)}}");
        Matcher matcher = pattern.matcher(input);

        StringBuilder result = new StringBuilder();
        while (matcher.find()) {
            String key = matcher.group(1);
            String replacement = values.getOrDefault(key, matcher.group(0));
            matcher.appendReplacement(result, replacement);
        }
        matcher.appendTail(result);

        return result.toString();
    }

    public Template replaceValues(Map<String, String> values) {
        String replacedBody = replaceTextWithDesignatedValues(this.body, values);
        String replacedTitle = replaceTextWithDesignatedValues(this.title, values);
        return new Template(replacedTitle, replacedBody);
    }
}
