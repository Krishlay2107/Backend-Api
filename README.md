## Getting Started

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









