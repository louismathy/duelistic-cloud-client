# Duelistic Cloud Client

Typed Java client for the local Duelistic Cloud HTTP API.

## Requirements
- Java 21+
- Maven 3.8+

## Build
```bash
mvn -f duelistic-cloud-client/pom.xml clean package
```

## Usage
```java
CloudApiClient client = new CloudApiClient(8085);
List<ServerStatus> servers = client.getServers();
Optional<ServerStatus> lobby = client.getServer("lobby-1");
```

## API
- `CloudApiClient(int port)`
- `List<ServerStatus> getServers()`
- `Optional<ServerStatus> getServer(String name)`
- `boolean stopServer(String name)`
- `boolean setCurrentPlayers(String name, int currentPlayers)`

`ServerStatus` fields:
- `name`
- `template`
- `port`
- `online`
- `currentPlayers`
- `maxPlayers`
- `startedAt`
