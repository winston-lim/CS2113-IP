# User Guide

## Quick start

1. Ensure you have Java `11`
2. Download the latest jar file from Releases in this project repository
3. On your terminal, ensure you are in the same directory as the jar file
4. Type any command (and optionally the necessary arguments) and hit enter to execute it
   - `list`: lists all recorded tasks
   - `todo`: adds a task of type `Todo`, the most generic form of any task.
   - `deadline [TITLE] /by [DEADLINE]`: adds a task of type `Deadline`, representing tasks with a specific deadline.
   - `event [TITLE] /at [DURATION]`: adds a task of type `Event`, representing tasks with a specified duration.
   - `delete [TASK_ID]`: deletes specified task from recorded tasks
   - `mark [TASK_ID]`: marks specified task as done
   - `unmark [TASK_ID]`: marks specified task as not done
   - `search-date [DATE]`: queries existing tasks and return tasks with same date as specified
   - `search-title [QUERY]`: queries existing tasks and return tasks containing the query specified
   - `bye`: exits the application
5. Notes about the command format

   ```
   1. Only 1 command can be executed per `ENTER`
   2. All commands are case sensitive and will not be recognized unless there is an exact match
   3. All arguments as shown above are necessary
   4. The order or arguments must be followed exactly
   5. Extraneous parameters for commands that do not take arguments will be ignored
   6. Errorenous parameters i.e. empty string or wrong data formats are all handled and reflected to the user
   ```

## Features

Winston is a Personal Assistant Chatbot acts as a task manager for you.

### Listing recorded tasks: `list`

List all recorded tasks, including the task count.

Format: `list`

Example: `list`

Example output:

### Adding a Todo: `todo`

Adds a todo task to the list of recorded tasks

Format: `todo [TITLE]`

Example: `todo Move furniture`

Example output:

### Adding a Deadline: `deadline`

Adds a deadline task to the list of recorded tasks

Format: `deadline [TITLE] /by [YYYY-MM-dd]`

Example: `deadline apply for financial aid /by 2023-01-01`

Example output:

### Adding an Event: `event`

Adds a event task to the list of recorded tasks

Format: `event [TITLE] /at [YYYY-MM-dd]T[HH:mm]`

Example: `event countdown /at 2022-12-31T23:59`

Example output:

### Deleting a task: `delete`

Deletes a event of specified id or outputs an error if the task does no exist

Format: `delete [TASK_ID]`

Example: `delete 1`

Example output:

### Mark a task as done: `mark`

Marks a task of specified id as done or outputs an error if the task does not exist

Format: `mark [TASK_ID]`

Example `mark 1`

Example output:

### Mark a task as not done: `unmark`

Marks a task of specified id as not done or outputs an error if the task does not exist

Format: `unmark [TASK_ID]`

Example: `umark 1`

Example output:

### Searching for a task by date: `search-date`

Queries existing tasks and return tasks with same date as specified

Format: `search-date [YYYY-MMM-dd]`

Example: `search-date 2022-12-31`

Example output:

### Searching for a task by title: `search-title`

Queries existing tasks and return tasks containing the query specified

Format: `search-title [TITLE]`

Example `search-title book`

Example output:

### Exiting the program: `bye`

Exits the program

### Saving the data

All changes to the recorded tasks are automatically saved in the hard disk after any modifications.

### Accessing the saved data

For simplicity, the data is simply stored as a `.txt` file, with a specific format
Format: `[TASK_TYPE] | [TASK_STATUS] | [TASK_TITLE] | [TASK_TIMING]`
Note: Task timing is optional
Example: `E | 0 | winston attend CS2113 lecture | Friday 16 Sep 2022 from 4 to 6pm`
