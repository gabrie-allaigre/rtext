# Rtext

```xml
<dependency>
    <groupId>com.talanlabs</groupId>
    <artifactId>rtext</artifactId>
    <version>1.0.1</version>
</dependency>
```

Convertie un text en object

```java
RtextConfigurationBuilder builder = RtextConfigurationBuilder.newBuilder();
Rtext rtext = new Rtext(builder.build());

Long l = rtext.fromText("12", Long.class);
LocalDate localDate = rtext.fromText("1980-02-07", LocalDate.class);
```