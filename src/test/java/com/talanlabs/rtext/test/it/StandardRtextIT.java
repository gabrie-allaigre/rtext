package com.talanlabs.rtext.test.it;

import com.talanlabs.rtext.Rtext;
import com.talanlabs.rtext.configuration.RtextConfigurationBuilder;
import org.assertj.core.api.Assertions;
import org.junit.BeforeClass;
import org.junit.Test;

public class StandardRtextIT {

    private static Rtext rtext;

    @BeforeClass
    public static void init() {
        RtextConfigurationBuilder builder = RtextConfigurationBuilder.newBuilder();

        rtext = new Rtext(builder.build());
    }

    @Test
    public void testStringRtext() {
        Assertions.assertThat(rtext.fromText("gaby", String.class)).isEqualTo("gaby");
        Assertions.assertThat(rtext.fromText("123", String.class)).isEqualTo("123");
        Assertions.assertThat(rtext.fromText("", String.class)).isEqualTo("");
        Assertions.assertThat(rtext.fromText(null, String.class)).isNull();
    }

    @Test
    public void testIntRtext() {
        Assertions.assertThat(rtext.fromText("12", Integer.class)).isEqualTo(12);
        Assertions.assertThat(rtext.fromText("-1", int.class)).isEqualTo(-1);
        Assertions.assertThat(rtext.fromText(null, Integer.class)).isNull();
        Assertions.assertThat(Assertions.catchThrowable(() -> rtext.fromText(null, int.class))).isNotNull();
        Assertions.assertThat(Assertions.catchThrowable(() -> rtext.fromText("abc", int.class))).isNotNull();
    }

    @Test
    public void testFloatRtext() {
        Assertions.assertThat(rtext.fromText("12.12", Float.class)).isEqualTo(12.12f);
        Assertions.assertThat(rtext.fromText("-1.5", float.class)).isEqualTo(-1.5f);
        Assertions.assertThat(rtext.fromText(null, Float.class)).isNull();
        Assertions.assertThat(Assertions.catchThrowable(() -> rtext.fromText(null, float.class))).isNotNull();
        Assertions.assertThat(Assertions.catchThrowable(() -> rtext.fromText("abc", float.class))).isNotNull();
    }

    @Test
    public void testDoubleRtext() {
        Assertions.assertThat(rtext.fromText("12.12", Double.class)).isEqualTo(12.12);
        Assertions.assertThat(rtext.fromText("-1.5", double.class)).isEqualTo(-1.5);
        Assertions.assertThat(rtext.fromText(null, Double.class)).isNull();
        Assertions.assertThat(Assertions.catchThrowable(() -> rtext.fromText(null, double.class))).isNotNull();
        Assertions.assertThat(Assertions.catchThrowable(() -> rtext.fromText("abc", double.class))).isNotNull();
    }

    @Test
    public void testLongRtext() {
        Assertions.assertThat(rtext.fromText("12", Long.class)).isEqualTo(12);
        Assertions.assertThat(rtext.fromText("-1", long.class)).isEqualTo(-1);
        Assertions.assertThat(rtext.fromText(null, Long.class)).isNull();
        Assertions.assertThat(Assertions.catchThrowable(() -> rtext.fromText(null, long.class))).isNotNull();
        Assertions.assertThat(Assertions.catchThrowable(() -> rtext.fromText("abc", long.class))).isNotNull();
    }

    @Test
    public void testBooleanRtext() {
        Assertions.assertThat(rtext.fromText("true", Boolean.class)).isTrue();
        Assertions.assertThat(rtext.fromText("false", boolean.class)).isFalse();
        Assertions.assertThat(Assertions.catchThrowable(() -> rtext.fromText(null, boolean.class))).isNotNull();
        Assertions.assertThat(Assertions.catchThrowable(() -> rtext.fromText("abc", boolean.class))).isNotNull();
        Assertions.assertThat(rtext.fromText(null, Boolean.class)).isNull();
    }
}
