package task;

import java.time.LocalDate;

interface TaskInterface {
    public boolean getStatus();

    public void setStatus(boolean isDone);

    public String getTitle();

    public String getTaskType();

    public String getTaskTiming();

    public String getStatusIcon();

    public String getDescription();

    public String getStatusDescription();

    public boolean equalDate(LocalDate date);
}
