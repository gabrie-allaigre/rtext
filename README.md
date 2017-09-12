# Rtext

```xml
<dependency>
    <groupId>com.talanlabs</groupId>
    <artifactId>rtext</artifactId>
    <version>1.0.2</version>
</dependency>
```

Convert a text to object

```java
RtextConfigurationBuilder builder = RtextConfigurationBuilder.newBuilder();
Rtext rtext = new Rtext(builder.build());

Long l = rtext.fromText("12", Long.class);
LocalDate localDate = rtext.fromText("1980-02-07", LocalDate.class);
```