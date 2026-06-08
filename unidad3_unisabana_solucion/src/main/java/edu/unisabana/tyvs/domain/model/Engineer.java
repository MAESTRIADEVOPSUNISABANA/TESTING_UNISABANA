package edu.unisabana.tyvs.domain.model;

public class Engineer {
    private final String name;
    private final int employeeId;
    private final int yearsExperience;
    private final int certificationLevel;
    private final boolean active;

    public Engineer(String name, int employeeId, int yearsExperience, int certificationLevel, boolean active) {
        this.name = name;
        this.employeeId = employeeId;
        this.yearsExperience = yearsExperience;
        this.certificationLevel = certificationLevel;
        this.active = active;
    }

    public String getName() {
        return name;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public int getYearsExperience() {
        return yearsExperience;
    }

    public int getCertificationLevel() {
        return certificationLevel;
    }

    public boolean isActive() {
        return active;
    }
}
