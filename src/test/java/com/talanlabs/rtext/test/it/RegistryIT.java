package com.talanlabs.rtext.test.it;

import com.google.common.base.Splitter;
import com.google.common.reflect.TypeToken;
import com.talanlabs.rtext.IRtextTypeAdapter;
import com.talanlabs.rtext.Rtext;
import com.talanlabs.rtext.configuration.RtextConfigurationBuilder;
import org.assertj.core.api.Assertions;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Map;
import java.util.stream.Collectors;

public class RegistryIT {

	private static Rtext rtext;

	@BeforeClass
	public static void init() {
		RtextConfigurationBuilder builder =
				RtextConfigurationBuilder.newBuilder().registerTypeAdapter(new TypeToken<Map<String, String>>() {
				}.getType(), new MapRtextTypeAdapter());

		rtext = new Rtext(builder.build());
	}

	@Test
	public void testMapRtext() {
        Assertions.assertThat(rtext.fromText("gaby", String.class)).isEqualTo("gaby");
        Assertions.assertThat((Map<String, String>) rtext.fromText("toto:123", new TypeToken<Map<String, String>>() {
		}.getType())).hasSize(1).containsEntry("toto", "123");
		Assertions.assertThat(
				(Map<String, String>) rtext.fromText("toto:123,tata:abc", new TypeToken<Map<String, String>>() {
				}.getType())).hasSize(2).containsEntry("toto", "123").containsEntry("tata", "abc");
	}

	private static class MapRtextTypeAdapter implements IRtextTypeAdapter<Map<String, String>> {

		@Override
		public Map<String, String> toDst(String src) {
			return Splitter.on(",").omitEmptyStrings().splitToList(src)
					.stream()
					.collect(Collectors.toMap(s -> s.substring(0, s.indexOf(":")), s -> s
							.substring(s.indexOf(":") + 1)));
		}

	}
}
