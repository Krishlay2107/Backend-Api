## Getting Started

To setup on your local system:

Clone the repository.

Install  intillj or ecclipse or vs code.

Set Up MongoDB as the Database.

Configure Database Connection in Your Project.

Run  the  main application .

## Key Features

### 1. User Authentication and Entry Management
- **Authentication:** When a user attempts to insert any entry or object into the system, the application will first authenticate the user.
- **Validation:** The system checks if the user is valid (using the authentication mechanism). If valid, the user is permitted to insert the entries or objects into the system under the respective user’s profile.
- **Failure Handling:** If the user authentication fails (i.e., the user is not valid), the application returns a `ResponseEntity` with an appropriate HTTP status code (such as 401 Unauthorized or 403 Forbidden).

### 2. Admin-Based Authentication
- **Admin Privileges:** The application features admin-based authentication, allowing admins to see the list of all users in the system.
- **Access Control:** Only users with admin privileges can view how many users are registered or access sensitive data related to the users.

### 3. Password Protection using Password Encoder
- **Secure Handling:** Passwords are securely handled using a Password Encoder (likely `BCryptPasswordEncoder` in Spring Boot or a hashing algorithm in Node.js).
- **Encryption:** This ensures that passwords are not stored in plain text but are encrypted before being stored in the database, enhancing security.
### 4. MongoDB Atlas
- **Cloud-Based Management:** MongoDB Atlas, a cloud-based managed MongoDB service, is used for database management.
- **Scalability:** Provides scalable and flexible database solutions.
- **Security:** Ensures data security with built-in security features.

- **User Authentication:** Validate users before they can perform actions.
- **Admin Access:** Admins have access to view the list of users and manage user data.
- **Password Security:** Use of password encryption techniques like `BCryptPasswordEncoder` for secure storage of passwords.


# Controller -----> Services -----> Repositor  ( calling happen in this way )



## User Class

The `User` class represents a user in the Journal application. with `@Document` to indicate that it is a MongoDB document.

### Fields

- `id` (`ObjectId`): Unique identifier.
- `userName` (`String`): Unique username.
- `password` (`String`): User's password.
- `roles` (`List<String>`): Assigned roles.
- `journalEntries` (`List<JournalEntry>`): Associated journal entries.

### Methods

- `getUserName()`: Returns the username.
- `setUserName(String userName)`: Sets the username.

### Annotations
`@Indexed(unique = true)`: Unique index on `userName`.
- `@DBRef`: Reference to another document.


## JournalEntry Class

The `JournalEntry` class represents a journal entry in the Journal application.

### Fields

- `id` (`ObjectId`): Unique identifier.
- `title` (`String`): Title of the journal entry.
- `content` (`String`): Content of the journal entry.
- `date` (`LocalDateTime`): Date and time of the journal entry.

### Methods

- `getTitle()`: Returns the title.
- `setTitle(String title)`: Sets the title.

The `JournalEntryControllerv2` class is a REST controller for managing journal entries in the Journal application.

### Endpoints

- `GET /journal`: Retrieves all journal entries of the authenticated user.
- `POST /journal/add`: Creates a new journal entry for the authenticated user.
- `GET /journal/id/{myId}`: Retrieves a journal entry by its ID for the authenticated user.
- `DELETE /journal/id/{myId}`: Deletes a journal entry by its ID for the authenticated user.
- `PUT /journal/id/{myId}`: Updates a journal entry by its ID for the authenticated user.

## UserServices Class

The `UserServices` class provides services for managing users in the Journal application.



### Methods

- `saveNewUser(User user)`: Saves a new user with encoded password and default role "USER".
- `saveUser(User user)`: Saves a user.
- `saveNewAdmin(User user)`: Saves a new admin user with encoded password and roles "USER" and "ADMIN".
- `getAll()`: Retrieves all users.
- `findByUsername(String username)`: Finds a user by username.
- `findById(ObjectId id)`: Finds a user by ID.
- `deleteById(ObjectId id)`: Deletes a user by ID.
- `deleteByuserName(String userName)`: Deletes a user by username.



## JournalEntryServices Class

The `JournalEntryServices` class provides services for managing journal entries in the Journal application.

### Methods

- `saveEntry(JournalEntry journalEntry, String userName)`: Saves a journal entry and associates it with a user.
- `saveEntry(JournalEntry journalEntry)`: Saves a journal entry.
- `getAll()`: Retrieves all journal entries.
- `getbyID(ObjectId id)`: Retrieves a journal entry by ID.
- `deleteById(ObjectId id, String userName)`: Deletes a journal entry by ID and disassociates it from a user.

### UserEntryRepo

The `UserEntryRepo` interface is a repository for managing users. which are extends MongoRespositary
MongoResposiarty providing the method and the UserEntryRepo impleting  the method inside it

### Methods

- `findByUserName(String username)`: Finds a user by username.
- `deleteByuserName(String username)`: Deletes a user by username.

### JournalEntryRepo

The `JournalEntryRepo` interface is a repository for managing journal entries.which are extends MongoRespositary.
MongoResposiarty providing the method and the UserEntryRepo impleting  the method inside it


## SpringSecurity Class

The `SpringSecurity` class configures security features for the JournalApp REST API using Spring Security.

### Annotations

- `@Configuration`: Indicates that this class is a configuration class.
- `@EnableWebSecurity`: Enables Spring Security's web security support.

### Fields

- `UserDetailsServiceImp userDetailsService`: Custom user details service for loading user-specific data.
- `PasswordEncoder passwordEncoder`: Encoder for encoding passwords.

### Constructor

- `SpringSecurity(UserDetailsServiceImp userDetailsService)`: Constructor for injecting the custom user details service.

### Methods

- `setPasswordEncoder(PasswordEncoder passwordEncoder)`: Setter for injecting the password encoder.

### Security Configuration

- `authorizeHttpRequests`: Configures authorization rules.
  - `requestMatchers("/Public/**").permitAll()`: Allows public access to endpoints under `/Public/**`.
  - `requestMatchers("/journal/**", "/user/**").authenticated()`: Requires authentication for endpoints under `/journal/**` and `/user/**`.
  - `requestMatchers("/admin/**").hasRole("ADMIN")`: Requires the "ADMIN" role for endpoints under `/admin/**`.
  - `anyRequest().authenticated()`: Requires authentication for any other requests.
- `httpBasic(Customizer.withDefaults())`: Enables HTTP Basic authentication.
- `csrf(AbstractHttpConfigurer::disable)`: Disables CSRF protection.

### Global Authentication Configuration

- `configureGlobal(AuthenticationManagerBuilder auth)`: Configures global authentication.
  - `auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder)`: Sets the custom user details service and password encoder for authentication.
  # WeatherResponse Class

The `WeatherResponse` class represents the response from a weather API in the JournalKeeper application.

## Fields

- `current` (`Current`): Represents the current weather conditions.

## Methods

- `getCurrent()`: Returns the current weather conditions.
- `setCurrent(Current current)`: Sets the current weather conditions.

## Inner Class: Current

The `Current` class represents the current weather conditions.

### Fields

- `temperature` (`int`): Represents the current temperature.

### Methods

- `getTemperature()`: Returns the current temperature.
- `setTemperature(int temperature)`: Sets the current temperature.

## Annotations

- `@Getter`: Lombok annotation to generate getter methods.
- `@Setter`: Lombok annotation to generate setter methods.



# AppCache Class

The `AppCache` class is responsible for managing an in-memory cache for the JournalKeeper application. It initializes the cache with data from the database upon application startup.

## Annotations

- `@Component`: Indicates that this class is a Spring component, making it eligible for component scanning and dependency injection.
- `@Autowired`: Marks a field to be autowired by Spring's dependency injection.
- `@PostConstruct`: Indicates that the `init` method should be executed after the bean's properties have been set.

## Fields

- `configureApiRepo` (`ConfigureApiRepo`): Repository for accessing `CacheEntity` data from the database.
- `recordsforApi` (`Map<String, String>`): A thread-safe map for storing cache records.

## Methods

### `init()`

- Initializes the `recordsforApi` map with data from the `configureApiRepo` repository.
- Fetches all `CacheEntity` records from the database and populates the cache.



The `RedisConfig` class is responsible for configuring Redis in the JournalKeeper application. It sets up the `RedisTemplate` bean, which is used to interact with the Redis data store.

## Annotations

- `@Configuration`: Indicates that this class is a configuration class and may contain bean definitions.
- `@Bean`: Indicates that a method produces a bean to be managed by the Spring container.

## Methods

### `redisTemplate(RedisConnectionFactory redisConnectionFactory)`

- Configures and returns a `RedisTemplate` bean.
- **Parameters:**
  - `redisConnectionFactory` (`RedisConnectionFactory`): Manages the connection between the application and the Redis data store.
- **Returns:**
  - `RedisTemplate`: Configured `RedisTemplate` bean for interacting with Redis.

### Configuration Details

- **Key Serializer:** Uses `StringRedisSerializer` to serialize keys as strings.
- **Value Serializer:** Uses `StringRedisSerializer` to serialize values as strings.



# CacheController Class

The `CacheController` class is a REST controller for managing cache entries in the JournalKeeper application.

## Annotations

- `@RestController`: Indicates that this class is a REST controller.
- `@RequestMapping("/API")`: Maps HTTP requests to `/API` to this controller.
- `@Autowired`: Marks a field to be autowired by Spring's dependency injection.

## Fields

- `cacheService` (`CacheService`): Service for managing cache entries.

## Endpoints

### `POST /API`

- **Description:** Adds a new cache entry.
- **Parameters:**
  - `entry` (`CacheEntity`): The cache entry to be added.
- **Returns:**
  - `ResponseEntity<?>`: HTTP status code indicating the result of the operation.


# JwtFilter Class

The `JwtFilter` class is a custom filter that intercepts HTTP requests to validate JWT tokens in the JournalKeeper application. It extends `OncePerRequestFilter` to ensure that the filter is executed once per request.

## Annotations

- `@Component`: Indicates that this class is a Spring component, making it eligible for component scanning and dependency injection.

## Fields

- `userDetailsService` (`UserDetailsService`): Service for loading user-specific data.
- `jwtUtils` (`JwtUtils`): Utility class for handling JWT operations.

## Methods

### `doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)`

- **Description:** Intercepts HTTP requests to validate JWT tokens.
- **Parameters:**
  - `request` (`HttpServletRequest`): The HTTP request.
  - `response` (`HttpServletResponse`): The HTTP response.
  - `chain` (`FilterChain`): The filter chain.
- **Implementation:**
  - Extracts the JWT token from the `Authorization` header.
  - Validates the token and sets the authentication in the security context if valid.
  - Allows requests to Swagger endpoints without validation.


# ConfigureApiRepo Interface

The `ConfigureApiRepo` interface is a repository for managing `CacheEntity` objects in the JournalKeeper application. It extends `MongoRepository` to provide CRUD operations for `CacheEntity` objects stored in a MongoDB database.

## Annotations

- `@Repository`: Indicates that this interface is a Spring Data repository (implicitly applied by extending `MongoRepository`).

## Methods

The `ConfigureApiRepo` interface inherits several methods from `MongoRepository` for performing CRUD operations:

- `save(S entity)`: Saves a given entity.
- `findById(ID id)`: Retrieves an entity by its ID.
- `findAll()`: Retrieves all entities.
- `deleteById(ID id)`: Deletes an entity by its ID.



## WeatherServices Class

The `WeatherServices` class provides services for fetching weather information from an external API and caching the results.

### Annotations

- `@Service`: Indicates that this class is a Spring service component.

### Fields

- `restTemplate` (`RestTemplate`): Used to make HTTP requests.
- `redisService` (`RedisService`): Service for interacting with Redis.
- `weatherApiKey` (`String`): API key for the weather service.
- `appCache` (`AppCache`): In-memory cache for storing API URLs.

### Methods

- `getWeather(String city)`: Fetches weather information for the specified city. It first checks the Redis cache and, if not found, makes an HTTP request to the weather API and caches the result.

## EmailServices Class

The `EmailServices` class provides services for sending emails.

### Annotations

- `@Service`: Indicates that this class is a Spring service component.
- `@Slf4j`: Lombok annotation to generate a logger.

### Fields

- `javaMailSender` (`JavaMailSender`): Used to send emails.

### Methods

- `sendEmail(String to, String subject, String body)`: Sends an email with the specified recipient, subject, and body.

## RedisService Class

The `RedisService` class provides services for interacting with Redis.

### Annotations

- `@Service`: Indicates that this class is a Spring service component.
- `@Slf4j`: Lombok annotation to generate a logger.

### Fields

- `redisTemplate` (`RedisTemplate`): Used to interact with Redis.

### Methods

- `get(String key, Class<T> EntityClass)`: Retrieves an object from Redis by its key.
- `set(String key, Object o, long ttl)`: Stores an object in Redis with a specified time-to-live (TTL).

# JwtUtils Class

The `JwtUtils` class provides utility methods for generating, validating, and extracting information from JWT tokens in the JournalKeeper application.

## Annotations

- `@Component`: Indicates that this class is a Spring component, making it eligible for component scanning and dependency injection.

## Fields

- `SECRET_KEY` (`String`): Secret key used for signing JWT tokens.

## Methods

### `extractUsername(String token)`
### `extractExpiration(String token)`
### `generateToken(String username)`
### `validateToken(String token)`

-### Private Methods

#### `getSigningKey()`
#### `extractAllClaims(String token)`
#### `isTokenExpired(String token)`
#### `createToken(Map<String, Object> claims, String subject)`


## Frameworks and Technologies Used

### Spring Boot
### Spring Data MongoDB
### Spring Security
### Spring Security OAuth2
### Lombok
### MongoDB Atlas
### Maven
### Redis
### JWT (JSON Web Tokens)
### RestTemplate
### JavaMailSender
### JUnit
### Mockito
### Apache Kafka
