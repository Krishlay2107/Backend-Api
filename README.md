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


## Frameworks and Technologies Used
      ### Spring Boot
      ### Spring Data MongoDB
      ### Spring Security
      ### Lombok
      ### MongoDB Atlas













