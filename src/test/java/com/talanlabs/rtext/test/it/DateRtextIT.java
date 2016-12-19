package com.talanlabs.rtext.test.it;

import com.talanlabs.rtext.Rtext;
import com.talanlabs.rtext.configuration.RtextConfigurationBuilder;
import org.assertj.core.api.Assertions;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

public class DateRtextIT {

    private static Rtext rtext;

    @BeforeClass
    public static void init() {
        RtextConfigurationBuilder builder = RtextConfigurationBuilder.newBuilder();

        rtext = new Rtext(builder.build());
    }

    @Test
    public void testDateRtext() {
        Assertions.assertThat(rtext.fromText("1980-02-07", Date.class)).isEqualTo("1980-02-07");
        Assertions.assertThat(rtext.fromText("1980-02-07T18:15", Date.class)).isEqualTo("1980-02-07T18:15:00.000");
        Assertions.assertThat(rtext.fromText("1980-02-07T18:15:45", Date.class)).isEqualTo("1980-02-07T18:15:45.000");
        Assertions.assertThat(rtext.fromText(null, Date.class)).isNull();
        Assertions.assertThat(Assertions.catchThrowable(() -> rtext.fromText("abc", Date.class))).isNotNull();
    }

    @Test
    public void testLocalDateRtext() {
        Assertions.assertThat(rtext.fromText("1980-02-07", LocalDate.class)).isEqualTo("1980-02-07");
        Assertions.assertThat(rtext.fromText(null, LocalDate.class)).isNull();
        Assertions.assertThat(Assertions.catchThrowable(() -> rtext.fromText("abc", LocalDate.class))).isNotNull();
    }

    @Test
    public void testLocalTimeRtext() {
        Assertions.assertThat(rtext.fromText("18:15", LocalTime.class)).isEqualTo("18:15:00.000");
        Assertions.assertThat(rtext.fromText("18:15:45", LocalTime.class)).isEqualTo("18:15:45.000");
        Assertions.assertThat(rtext.fromText(null, LocalTime.class)).isNull();
        Assertions.assertThat(Assertions.catchThrowable(() -> rtext.fromText("abc", LocalTime.class))).isNotNull();
    }

    @Test
    public void testLocalDateTimeRtext() {
        Assertions.assertThat(rtext.fromText("1980-02-07T00:00", LocalDateTime.class)).isEqualTo("1980-02-07T00:00:00.000");
        Assertions.assertThat(rtext.fromText("1980-02-07T18:15", LocalDateTime.class)).isEqualTo("1980-02-07T18:15:00.000");
        Assertions.assertThat(rtext.fromText("1980-02-07T18:15:45", LocalDateTime.class)).isEqualTo("1980-02-07T18:15:45.000");
        Assertions.assertThat(rtext.fromText(null, LocalDateTime.class)).isNull();
        Assertions.assertThat(Assertions.catchThrowable(() -> rtext.fromText("abc", LocalDateTime.class))).isNotNull();
    }
}
