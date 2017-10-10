package com.talanlabs.rtext.test.it;

import com.google.common.reflect.TypeToken;
import com.talanlabs.rtext.Rtext;
import com.talanlabs.rtext.configuration.RtextConfigurationBuilder;
import org.assertj.core.api.Assertions;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class MapRtextIT {

    private static Rtext rtext;

    @BeforeClass
    public static void init() {
        RtextConfigurationBuilder builder = RtextConfigurationBuilder.newBuilder();

        rtext = new Rtext(builder.build());
    }

    @Test
    public void testMapRtext() {
        Assertions.assertThat(rtext.<Map<String, String>>fromText("name=gaby;surname=themouton", new TypeToken<Map<String, String>>() {
        }.getType())).containsEntry("name", "gaby").containsEntry("surname", "themouton");
        Assertions.assertThat(rtext.<Map<String, Role>>fromText("role1=Admin;role2=Driver", new TypeToken<Map<String, Role>>() {
        }.getType())).containsEntry("role1", Role.Admin).containsEntry("role2", Role.Driver);
        Assertions.assertThat(rtext.<Map<Integer, Role>>fromText("1=Admin;2=Driver", new TypeToken<Map<Integer, Role>>() {
        }.getType())).containsEntry(1, Role.Admin).containsEntry(2, Role.Driver);
        Assertions.assertThat(rtext.<Map<String, List<String>>>fromText("names=gaby,sandra;surnames=themouton,sandrita", new TypeToken<Map<String, List<String>>>() {
        }.getType())).containsEntry("names", Arrays.asList("gaby", "sandra")).containsEntry("surnames", Arrays.asList("themouton", "sandrita"));
    }
}
