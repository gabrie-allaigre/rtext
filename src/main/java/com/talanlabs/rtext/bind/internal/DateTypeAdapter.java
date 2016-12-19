package com.talanlabs.rtext.bind.internal;

import com.google.common.reflect.TypeToken;
import com.talanlabs.rtext.IRtextTypeAdapter;
import com.talanlabs.rtext.IRtextTypeAdapterFactory;
import com.talanlabs.rtext.Rtext;
import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.util.Date;

public class DateTypeAdapter implements IRtextTypeAdapter<Date> {

    public static final DateTypeAdapter TYPE_ADAPTER = new DateTypeAdapter();
    @SuppressWarnings("unchecked")
    public static final IRtextTypeAdapterFactory FACTORY = new IRtextTypeAdapterFactory() {
        public <T2> IRtextTypeAdapter<T2> create(Rtext rtext, TypeToken<T2> typeToken) {
            if (!TypeToken.of(Date.class).isSubtypeOf(typeToken)) {
                return null;
            }
            return (IRtextTypeAdapter<T2>) TYPE_ADAPTER;
        }
    };

    private DateTypeAdapter() {
        super();
    }

    @Override
    public Date toDst(String s) {
        if (s == null) {
            return null;
        }
        try {
            return DateUtils.parseDate(s, "yyyy-MM-dd", "yyyy-MM-dd'T'HH:mm", "yyyy-MM-dd'T'HH:mm:ss");
        } catch (ParseException e) {
            throw new RuntimeException("Date format is yyyy-MM-dd, yyyy-MM-ddTHH:mm or yyyy-MM-ddTHH:mm:ss", e);
        }
    }
}
