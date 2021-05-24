package com.gavin.foo.crud.log.util;


import com.gavin.foo.crud.log.LogFieldRegistry;
import net.logstash.logback.argument.StructuredArgument;
import net.logstash.logback.argument.StructuredArguments;
import org.slf4j.helpers.FormattingTuple;
import org.slf4j.helpers.MessageFormatter;
import org.springframework.data.util.Pair;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author qhlai
 * @email qhlai@gizwits.com
 */
public class PatternUtils {
    private PatternUtils() {
    }

    private static Pattern KEY_VALUE = Pattern.compile("\\[\\w+=[A-Za-z0-9_:@.-]+\\]");

    public static Map<String, Object> grapKeyValue(String origin) {
        Matcher m = KEY_VALUE.matcher(origin);
        Map<String, Object> map = new HashMap<>(4);
        while (m.find()) {
            String g = m.group();
            String[] s = g.substring(1, g.length() - 1).split("=");
            if (LogFieldRegistry.isExistsLogField(s[0])) {
                map.put(s[0], s[1]);
            }
        }
        return map;
    }

    public static Pair<FormattingTuple, StructuredArgument> format(String format, Object... objects) {
        FormattingTuple formattingTuple = MessageFormatter.arrayFormat(format, objects);
        return Pair.of(formattingTuple, StructuredArguments.entries(grapKeyValue(formattingTuple.getMessage())));
    }


}
