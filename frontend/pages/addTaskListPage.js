import { renderTaskListForm } from "../components/taskListForm.js";
import {
  createTaskList,
  fetchTaskList,
  fetchTaskLists,
  deleteTaskListTask,
  createTask,
} from "../api/taskListApi.js";
import { renderTaskListsPage } from "./taskListsPage.js";
import { renderTaskListPage } from "./taskListDetailPage.js";
import { renderCreateTaskPage } from "./addTaskPage.js";

export function setAddTaskListEvents(buildHtmlStructure) {
  $(document).off("click", ".add-taskList-btn");
  $(document).off("click", "#cancel-btn");
  $(document).off("submit", "#taskListForm");
  $(document).off("click", ".taskList");

  $(document).on("click", ".add-taskList-btn", function () {
    $(".taskList-wrapper").html(renderTaskListForm());
    $(".add-taskList-btn").hide();
  });

  $(document).on("click", "#cancel-btn", async function (e) {
    e.preventDefault();
    try {
      buildHtmlStructure();
      await renderTaskListsPage();
    } catch (error) {
      console.error("Error handling cancel:", error);
    }
  });

  $(document).on("submit", "#taskListForm", async function (e) {
    e.preventDefault();
    try {
      const taskList = {
        title: $("#taskList-title").val().trim(),
        description: $("#taskList-description").val().trim(),
      };
      await createTaskList(taskList);
      buildHtmlStructure();
      await renderTaskListsPage();
    } catch (error) {
      console.error("Error creating task list:", error);
      alert("Failed to create task list. Please try again.");
    }
  });

  $(document).on(
    "click",
    ".taskList , .cancel-create-task-btn",
    async function (e) {
      const $target = $(this);
      let id = "";
      if ($target.hasClass("taskList")) {
        id = $target.attr("data-id");
        console.log(id);
      } else {
        id = $target.closest(".taskList-container").attr("data-tasklist-id");
      }

      try {
        await renderTaskListPage(id);
      } catch (error) {
        console.error("Error loading task list detai ls:", error);
        alert("Failed to load task list details. Please try again.");
      }
    }
  );

  $(document).on("click", "#back-btn", async function () {
    try {
      buildHtmlStructure();
      await renderTaskListsPage();
    } catch (error) {
      console.error("Error handling back button:", error);
    }
  });

  $(document).on("click", ".delete-task-btn", async function (e) {
    const taskId = $(this).closest(".task").attr("data-id");
    const taskListId = $(this)
      .closest(".taskList-container")
      .attr("data-tasklist-id");
    console.log("Deleting task:", taskId, "from list:", taskListId);
    try {
      await deleteTaskListTask(taskId);
      await renderTaskListPage(taskListId);
    } catch (error) {
      console.error("Error deleting task:", error);
      alert("Failed to delete task. Please try again.");
    }
  });

  $(document).on("click", "#add-task-btn", async function (e) {
    e.preventDefault();
    renderCreateTaskPage();
  });

  $(document).on("submit", "#create-task-form", async function (e) {
    e.preventDefault();
    const $form = $(this);
    const taskListId = $form
      .closest(".taskList-container")
      .attr("data-tasklist-id");

    if (!taskListId) {
      console.error("Could not find taskListId");
      alert(
        "Error: Could not determine the task list. Please go back and try again."
      );
      return;
    }

    const taskData = {
      title: $("#title").val().trim(),
      description: $("#description").val().trim(),
      dueDate: $("#dueDate").val(),
      priority: $("input[name='priority']:checked").val(),
      status: $("input[name='status']:checked").val(),
    };

    try {
      await createTask(taskData, taskListId);
      await renderTaskListPage(taskListId);
    } catch (error) {
      console.error("Error creating task:", error);
      alert("Failed to create task. Please check the details and try again.");
    }
  });
}
