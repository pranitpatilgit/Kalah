# Kalah

This is a Spring Boot REST application for the game **Kalah**.
Game rules can be found on https://en.wikipedia.org/wiki/Kalah

### **API Information**

There are three endpoints

- ######  Create Game
Creates a new game and return the id of the same so as to be used to play

Following request can be used to create a game

`curl --location --request POST 'http://localhost:8080/games'`

Sample Response 

`{
    "id": 175642959,
    "_links": {
        "self": {
            "href": "http://localhost:8080/games/175642959"
        }
    }
}`

- ######  View Game

Gets the details of the game and the position and count of the stone in the pit

Sample Request

`curl --location --request GET 'http://localhost:8080/games/{gameId}'`

Sample Response

`{
    "id": 175642959,
    "status": {
        "1": "0",
        "2": "7",
        "3": "7",
        "4": "7",
        "5": "7",
        "6": "7",
        "7": "1",
        "8": "6",
        "9": "6",
        "10": "6",
        "11": "6",
        "12": "6",
        "13": "6",
        "14": "0"
    },
    "_links": {
        "self": {
            "href": "http://localhost:8080/games/175642959"
        }
    }
}`

- ######  Move a stone from specified pit

This is the request for a player to make a move and pick up stones from one of their pit and sow it to the next pits

Sample Request 

`curl --location --request PUT 'http://localhost:8080/games/{gameId}/pits/{pitId}'`

Sample Response

`{
    "id": 175642959,
    "status": {
        "1": "0",
        "2": "0",
        "3": "8",
        "4": "8",
        "5": "8",
        "6": "8",
        "7": "2",
        "8": "7",
        "9": "7",
        "10": "6",
        "11": "6",
        "12": "6",
        "13": "6",
        "14": "0"
    },
    "_links": {
        "self": {
            "href": "http://localhost:8080/games/175642959"
        }
    }
}`


### **Author**
##### **Pranit Patil**