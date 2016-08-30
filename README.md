# JHue
A library in java to interact with the Philips Hue System

JHue connect your Android Device with the Hue System from Philips. It has simple method to do this.

```java
JHue bridge = new JHue(String username, String ip);
```


## Requirements
- JRequests v1.1
- Android Library

## Usage
Get all lights in a JSON Object with the informations like name, id, state, etc.
```java
String lights = bridge.getAllLights();
```

Get all groups/rooms in a JSON Object with the informations like name, id, state, etc.
```java
String groups = bridge.getAllGroups();
```

Get all schedules in a JSON Object with the informations like name, id, state, etc.
```java
String schedules = bridge.getAllSchedules();
```

Philips Hue is a trademark from Philips.
