export function fetchTaskLists() {
  return $.get("http://localhost:8080/task-lists");
}

export function createTaskList(taskList) {
  return $.ajax({
    url: "http://localhost:8080/task-lists",
    method: "POST",
    contentType: "application/json",
    data: JSON.stringify(taskList)
  });
}
