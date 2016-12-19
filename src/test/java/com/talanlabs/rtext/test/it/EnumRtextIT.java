package com.talanlabs.rtext.test.it;

import com.talanlabs.rtext.Rtext;
import com.talanlabs.rtext.configuration.RtextConfigurationBuilder;
import org.assertj.core.api.Assertions;
import org.junit.BeforeClass;
import org.junit.Test;

public class EnumRtextIT {

    private static Rtext rtext;

    @BeforeClass
    public static void init() {
        RtextConfigurationBuilder builder = RtextConfigurationBuilder.newBuilder();

        rtext = new Rtext(builder.build());
    }

    @Test
    public void testEnumRtext() {
        Assertions.assertThat(rtext.fromText("Gaby", Parent.class)).isEqualByComparingTo(Parent.Gaby);
        Assertions.assertThat(rtext.fromText("Laureline", Child.class)).isEqualByComparingTo(Child.Laureline);
        Assertions.assertThat(rtext.fromText(null, Parent.class)).isNull();
        Assertions.assertThat(Assertions.catchThrowable(() -> rtext.fromText("abc", Parent.class))).isNotNull();
    }

    enum Parent {
        Gaby, Sandra
    }

    enum Child {
        Laureline, Raphael
    }
}
