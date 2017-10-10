package com.talanlabs.rtext.test.it;

import com.google.common.reflect.TypeToken;
import com.talanlabs.rtext.Rtext;
import com.talanlabs.rtext.configuration.RtextConfigurationBuilder;
import org.assertj.core.api.Assertions;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;
import java.util.Set;

public class CollectionRtextIT {

    private static Rtext rtext;

    @BeforeClass
    public static void init() {
        RtextConfigurationBuilder builder = RtextConfigurationBuilder.newBuilder();

        rtext = new Rtext(builder.build());
    }

    @Test
    public void testArrayRtext() {
        Assertions.assertThat(rtext.<String[]>fromText(null, new TypeToken<String[]>() {
        }.getType())).isNull();
        Assertions.assertThat(rtext.<String[]>fromText("", new TypeToken<String[]>() {
        }.getType())).isEmpty();
        Assertions.assertThat(rtext.<String[]>fromText("gaby", new TypeToken<String[]>() {
        }.getType())).containsExactly("gaby");
        Assertions.assertThat(rtext.<String[]>fromText("gaby,sandra", new TypeToken<String[]>() {
        }.getType())).containsExactly("gaby", "sandra");
        Assertions.assertThat(rtext.<Integer[]>fromText("1,3,5,6", new TypeToken<Integer[]>() {
        }.getType())).containsExactly(1, 3, 5, 6);
        Assertions.assertThat(rtext.<Role[]>fromText("Admin,Driver", new TypeToken<Role[]>() {
        }.getType())).containsExactly(Role.Admin, Role.Driver);
    }

    @Test
    public void testListRtext() {
        Assertions.assertThat(rtext.<List<String>>fromText(null, new TypeToken<List<String>>() {
        }.getType())).isNull();
        Assertions.assertThat(rtext.<List<String>>fromText("", new TypeToken<List<String>>() {
        }.getType())).isEmpty();
        Assertions.assertThat(rtext.<List<String>>fromText("gaby", new TypeToken<List<String>>() {
        }.getType())).containsExactly("gaby");
        Assertions.assertThat(rtext.<List<String>>fromText("gaby,sandra", new TypeToken<List<String>>() {
        }.getType())).containsExactly("gaby", "sandra");
        Assertions.assertThat(rtext.<List<Integer>>fromText("1,3,5,6", new TypeToken<List<Integer>>() {
        }.getType())).containsExactly(1, 3, 5, 6);
        Assertions.assertThat(rtext.<List<Role>>fromText("Admin,Driver", new TypeToken<List<Role>>() {
        }.getType())).containsExactly(Role.Admin, Role.Driver);
    }

    @Test
    public void testSetRtext() {
        Assertions.assertThat(rtext.<Set<String>>fromText(null, new TypeToken<Set<String>>() {
        }.getType())).isNull();
        Assertions.assertThat(rtext.<Set<String>>fromText("", new TypeToken<Set<String>>() {
        }.getType())).isEmpty();
        Assertions.assertThat(rtext.<Set<String>>fromText("gaby", new TypeToken<Set<String>>() {
        }.getType())).containsExactly("gaby");
        Assertions.assertThat(rtext.<Set<String>>fromText("gaby,sandra", new TypeToken<Set<String>>() {
        }.getType())).containsExactlyInAnyOrder("gaby", "sandra");
        Assertions.assertThat(rtext.<Set<Integer>>fromText("1,3,5,6", new TypeToken<Set<Integer>>() {
        }.getType())).containsExactly(1, 3, 5, 6);
        Assertions.assertThat(rtext.<Set<Role>>fromText("Admin,Driver", new TypeToken<Set<Role>>() {
        }.getType())).containsExactlyInAnyOrder(Role.Admin, Role.Driver);
    }
}
