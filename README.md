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


