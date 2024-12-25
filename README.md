# Dating App Matchmaking API

A comprehensive matchmaking system for dating applications, designed to provide efficient and personalized user experiences. The API facilitates user profile creation, compatibility scoring, and matchmaking features.

## Features
- Create user profiles with detailed attributes such as hobbies, interests, and personality traits.
- Generate matches for a specific user with ranked compatibility scores.
- Check compatibility scores between two users.
- Robust error handling for invalid requests and user IDs.

---

## API Endpoints

### 1. Create User
**Endpoint:** `POST /api/v1/users`

#### Request
```json
{
    "name": "Jackson Jesionowski",
    "age": 27,
    "gender": "Male",
    "interestedIn": "Female",
    "location": "USA",
    "hobbies": ["gaming", "traveling"],
    "interests": ["sports", "technology"],
    "occupation": "Developer",
    "educationLevel": "Master's",
    "personalityTraits": ["creative", "adventurous", "outgoing"]
}
```

#### Response
```json
{
    "status": "SUCCESS",
    "data": {
        "id": "0b48c03f-7df6-46fa-a56f-7fd7432fc52f",
        "userId": "user9",
        "name": "Jackson Jesionowski",
        "age": 27,
        "gender": "Male",
        "interestedIn": "Female",
        "location": "USA",
        "hobbies": [
            "gaming",
            "traveling"
        ],
        "interests": [
            "sports",
            "technology"
        ],
        "occupation": "Developer",
        "educationLevel": "Master's",
        "personalityTraits": [
            "creative",
            "adventurous",
            "outgoing"
        ]
    }
}
```

---

### 2. Generate Matches for a User
**Endpoint:** `POST /api/v1/match/{userId}`

#### Request
No body required.

#### Example Request URL:
`http://localhost:8080/api/v1/match/user1`

#### Response
```json
{
    "status": "SUCCESS",
    "data": [
        {
            "userId": "user4",
            "name": "Ethan Brown",
            "compatibilityScore": 0.5,
            "commonInterests": ["technology"],
            "commonHobbies": ["hiking"]
        },
        {
            "userId": "user7",
            "name": "Jordan",
            "compatibilityScore": 0.25,
            "commonInterests": ["technology"],
            "commonHobbies": []
        },
        {
            "userId": "user8",
            "name": "Alan Walker",
            "compatibilityScore": 0.25,
            "commonInterests": ["technology"],
            "commonHobbies": []
        },
        {
            "userId": "user6",
            "name": "Alex Smith",
            "compatibilityScore": 0.25,
            "commonInterests": ["technology"],
            "commonHobbies": []
        },
        {
            "userId": "user3",
            "name": "Liam Johnson",
            "compatibilityScore": 0.0,
            "commonInterests": [],
            "commonHobbies": []
        }
    ]
}
```

---

### 3. Check Compatibility Between Two Users
**Endpoint:** `GET /api/v1/compatibility/{user1Id}/{user2Id}`

#### Example Request URL:
`http://localhost:8080/api/v1/compatibility/user1/user5`

#### Response
```json
{
    "status": "SUCCESS",
    "data": {
        "user1Id": "user1",
        "user2Id": "user5",
        "compatibilityScore": 0.25
    }
}
```

#### Error Handling Example:
Invalid user ID request:

**Request:** `GET /api/v1/compatibility/user1/user50`

**Response:**
```json
{
    "detail": "User id not found : user50",
    "status": "FAILURE"
}
```

---

## Project Scenario
The matchmaking algorithm calculates compatibility scores based on:
- **Common Hobbies:** Higher weight for shared hobbies.
- **Common Interests:** Moderate weight for shared interests.
- **Demographic Attributes:** Education level, location, and personality traits.

The algorithm sorts potential matches in descending order of compatibility scores and filters them based on gender preferences.

### Code Structure
- **Controllers:** Handle incoming requests and validation.
- **Services:** Implement business logic, including the matchmaking algorithm.
- **DTOs:** Used for request and response data encapsulation.
- **Repositories:** Handle data persistence and retrieval.

### Future Improvements
- **Real-Time Matching:** Add WebSocket-based updates for new matches.
- **Scalability:** Implement database indexing for faster queries.
- **Enhanced Algorithm:** Include factors like past interactions and user activity.
- **Machine Learning:** Use ML models to improve compatibility predictions.

---

## Setup Instructions
1. Clone the repository.
2. Navigate to the project directory.
3. Build the project: `mvn clean install` (for Spring Boot).
4. Run the application: `mvn spring-boot:run`.
5. Test the API endpoints using Postman or a similar tool.

---

## Evaluation Criteria
- **Code Quality:** Adherence to best practices and clean architecture.
- **Functionality:** Implementation of all required features.
- **Error Handling:** Robust error messages for edge cases.
- **Documentation:** Clear and detailed explanations for all components.

