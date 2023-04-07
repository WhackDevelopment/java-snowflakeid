<div align="center">
    <a target="_blank" href="https://whackdevelopment.com/">
        <img target="_blank" style="border-radius:50%;" width="200" height="200" src="https://avatars.githubusercontent.com/u/110769913"/>
    </a>
</div>
<div align="center">
    <h1><a target="_blank" href="https://paypal.me/WhackDevelopment">@WhackDevelopment</a></h1>
    <a target="_blank" href="https://discord.gg/WhackDevelopment">
        <img src="https://img.shields.io/discord/1075538521340776489?style=for-the-badge&logo=discord">
    </a>
    <a target="_blank" href="https://paypal.me/WhackDevelopment">
        <img src="https://img.shields.io/badge/Donate-PayPal-blue?style=for-the-badge&logo=paypal" alt="PayPal">
    </a>
    <a target="_blank" href="https://github.com/WhackDevelopment/java-snowflakeid/issues">
        <img src="https://img.shields.io/github/issues/WhackDevelopment/java-snowflakeid.svg?style=for-the-badge&logo=github">
    </a>
    <br>
</div>

---

# Java SnowflakeIds

## Implementation of Java-SnowflakeId

## Usage

---

### Import Snowflake

#### Add Repository in Gradle

```kts
// Repository - Groovy
maven {
    name = "whackdevelopment-snapshots"
    url = "https://repo.whackdevelopment.com/repository/maven-snapshots/"
}

// Repository - Kotlin DSL
maven("https://repo.whackdevelopment.com/repository/maven-snapshots/")
```

#### Gradle import

```kts
// Import - Groovy
implementation "com.whackdevelopment.snowflake:snowflakeid:1.0.0-SNAPSHOT"

// Import - Kotlin DSL
implementation("com.whackdevelopment.snowflake:snowflakeid:1.0.0-SNAPSHOT")
```

#### Add Repository in Maven

```xml

<repository>
    <id>whackdevelopment-snapshots</id>
    <url>https://repo.whackdevelopment.com/repository/maven-snapshots/</url>
</repository>
```

#### Maven import

```xml

<dependency>
    <groupId>com.whackdevelopment.snowflake</groupId>
    <artifactId>snowflakeid</artifactId>
    <version>1.0.0-SNAPSHOT</version>
    <scope>compile</scope>
</dependency>
```

---

### Create a new SnowflakeGenerator

```java
SnowflakeGenerator flakeGenerator=new SnowflakeGenerator(
        /*machineId*/ 1, // optional, define machine id (defaults to 1)
        /*timeOffset*/ 0 // optional, define a offset time (defaults to 0)
        );
```

#### Options

machineId: (Defaults to 1) A machine id or any random id. If you are generating id in distributed system, its highly
advised to provide a proper machineId which is unique to different machines.

timeOffset: (Defaults to 0) Time offset will be subtracted from current time to get the first 42 bit of id. This help in
generating smaller ids. ( not recommended )

---

### Generate a Snowflake

```java
Snowflake id1=flakeGenerator.generate(); // returns something like 112867124767768576
        Snowflake id2=flakeGenerator.generate(); // returns something like 112867124784545792
        String id1String=id1.toString();
        String id2String=id2.toString();
```

---

### Create a new Snowflake from a Snowflake String

```java
Snowflake id1Clone=new Snowflake(id1.toString());
```
