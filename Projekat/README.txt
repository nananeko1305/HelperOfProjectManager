You are presented with the project management system in an IT company. 
There are multiple projects that the company needs to keep track of. 
Each project has its name, start date, deadline, client name, short description and status (PLANNING, DEVELOPMENT , FINISHED). 
Project is divided into multiple tasks with information about name, task specification, status of the task (TO_DO, IN_PROGRESS, DONE) and deadline, also there is possibility to specify subtasks which hold the same information as tasks.

Status propagation:
Task -> Project
If all of the tasks have status TO_DO, the project has PLANNING status.
If at least one task has status different from TO_DO, the project has DEVELOPMENT status.
If all of the tasks are in status DONE, the project has status FINISHED. 

Subtask -> Task
If all of the subtasks have status TO_DO, parent task has status TO_DO.
If at least one subtask has status different from TO_DO, the parent task has status IN_PROGRESS.
If all of the subtasks have status DONE, task should have status DONE.

Task 1:
Users should be able to change statuses of subtasks or tasks (if there are no subtasks) and the status should propagate if needed.

For example: if we change the status of subtask to IN_PROGRESS, his parent task should change status to IN_PROGRESS if needed, and also status of the project should change accordingly if needed.

Task 2:
Users should be able to see the list of projects with a number of tasks for each status
(e.g. Project has 10 tasks that are in TO_DO status, 3 tasks that are IN_PROGRESS and 5 tasks that are DONE)
Projects should be sorted by deadline in ascending order and response should be paginated.

Bonus Task:
Add completion % to project and task. % Depends on number of completed tasks/subtasks.
For example: If project has 10 tasks and 5 tasks are done - completion of project is 50%. If task has 5 subtasks and 3 are done - Task has completion of 40%