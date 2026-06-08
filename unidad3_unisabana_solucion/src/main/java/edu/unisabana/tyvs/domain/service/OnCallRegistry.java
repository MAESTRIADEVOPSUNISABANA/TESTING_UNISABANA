package edu.unisabana.tyvs.domain.service;

import edu.unisabana.tyvs.domain.model.Engineer;
import edu.unisabana.tyvs.domain.model.RegisterResult;

import java.util.HashSet;
import java.util.Set;

public class OnCallRegistry {

    private static final int MIN_EXPERIENCE = 0;
    private static final int MAX_EXPERIENCE = 40;
    private static final int MIN_CERTIFICATION_LEVEL = 2;
    private final Set<Integer> registeredIds = new HashSet<>();

    public RegisterResult registerEngineerForOnCall(Engineer engineer) {
        if (engineer == null) {
            return RegisterResult.INVALID;
        }

        if (engineer.getEmployeeId() <= 0) {
            return RegisterResult.INVALID;
        }

        if (!engineer.isActive()) {
            return RegisterResult.INACTIVE;
        }

        if (engineer.getYearsExperience() < MIN_EXPERIENCE || engineer.getYearsExperience() > MAX_EXPERIENCE) {
            return RegisterResult.INVALID_EXPERIENCE;
        }

        if (engineer.getCertificationLevel() < MIN_CERTIFICATION_LEVEL) {
            return RegisterResult.UNDERQUALIFIED;
        }

        if (registeredIds.contains(engineer.getEmployeeId())) {
            return RegisterResult.DUPLICATED;
        }

        registeredIds.add(engineer.getEmployeeId());
        return RegisterResult.VALID;
    }
}
