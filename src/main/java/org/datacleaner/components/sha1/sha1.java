package org.datacleaner.components.sha1;


import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;
import org.datacleaner.api.Categorized;
import org.datacleaner.api.Concurrent;
import org.datacleaner.api.Configured;
import org.datacleaner.api.Description;
import org.datacleaner.api.InputColumn;
import org.datacleaner.api.InputRow;
import org.datacleaner.api.OutputColumns;
import org.datacleaner.api.Transformer;
import org.datacleaner.components.categories.TextCategory;

@Named("Generate SHA1")
@Categorized(TextCategory.class)
@Description("Generate SHA1 from input")
@Concurrent(true)
public class sha1 implements Transformer {


    @Configured
    InputColumn<String>[] columns;

    @Override
    public OutputColumns getOutputColumns() {
        String[] columnNames = {"SHA1"};
        return new OutputColumns(String.class, columnNames);
    }

    @Override
    public String[] transform(InputRow inputRow) {

        String input = "";
        String value = "";
        String sha1 = null;

        for (int i = 0; i < columns.length; i++) {
            if (inputRow.getValues(columns[i]).get(0) != null) {
                value = inputRow.getValues(columns[i]).get(0).toString().trim();
            }
            if (!value.equalsIgnoreCase("")) {
            input = input + " "+ value;
            }
        }
        if (StringUtils.isEmpty(input)) {
            sha1 = generate_sha1.getHash(String.valueOf(inputRow.getId()));
        } else {
            sha1 = generate_sha1.getHash(input.trim());
        }

        return new String[]{sha1};
    }

}


