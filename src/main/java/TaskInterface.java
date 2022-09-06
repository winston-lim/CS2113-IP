interface TaskInterface {
    public boolean getStatus();

    public void setStatus(boolean isDone);

    public String getStatusIcon();

    public String getDescription();

    public String getStatusDescription();

    public String getStatusDescriptionWithId();
}
