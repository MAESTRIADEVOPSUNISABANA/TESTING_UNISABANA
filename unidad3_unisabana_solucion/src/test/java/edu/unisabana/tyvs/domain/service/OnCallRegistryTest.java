package edu.unisabana.tyvs.domain.service;

import edu.unisabana.tyvs.domain.model.Engineer;
import edu.unisabana.tyvs.domain.model.RegisterResult;
import org.junit.Assert;
import org.junit.Test;

public class OnCallRegistryTest {

    @Test
    public void shouldReturnInvalidWhenEngineerIsNull() {
        // Given
        OnCallRegistry registry = new OnCallRegistry();

        // When
        RegisterResult result = registry.registerEngineerForOnCall(null);

        // Then
        Assert.assertEquals(RegisterResult.INVALID, result);
    }

    @Test
    public void shouldRejectWhenEmployeeIdIsZeroOrNegative() {
        // Given
        OnCallRegistry registry = new OnCallRegistry();
        Engineer withZeroId = new Engineer("Nora", 0, 5, 3, true);
        Engineer withNegativeId = new Engineer("Leo", -8, 7, 3, true);

        // When
        RegisterResult zeroIdResult = registry.registerEngineerForOnCall(withZeroId);
        RegisterResult negativeIdResult = registry.registerEngineerForOnCall(withNegativeId);

        // Then
        Assert.assertEquals(RegisterResult.INVALID, zeroIdResult);
        Assert.assertEquals(RegisterResult.INVALID, negativeIdResult);
    }

    @Test
    public void shouldRejectInactiveEngineer() {
        // Given
        OnCallRegistry registry = new OnCallRegistry();
        Engineer inactiveEngineer = new Engineer("Pablo", 101, 6, 3, false);

        // When
        RegisterResult result = registry.registerEngineerForOnCall(inactiveEngineer);

        // Then
        Assert.assertEquals(RegisterResult.INACTIVE, result);
    }

    @Test
    public void shouldRejectInvalidExperienceBelowZero() {
        // Given
        OnCallRegistry registry = new OnCallRegistry();
        Engineer engineer = new Engineer("Sara", 102, -1, 3, true);

        // When
        RegisterResult result = registry.registerEngineerForOnCall(engineer);

        // Then
        Assert.assertEquals(RegisterResult.INVALID_EXPERIENCE, result);
    }

    @Test
    public void shouldRejectInvalidExperienceOverForty() {
        // Given
        OnCallRegistry registry = new OnCallRegistry();
        Engineer engineer = new Engineer("Mario", 103, 41, 3, true);

        // When
        RegisterResult result = registry.registerEngineerForOnCall(engineer);

        // Then
        Assert.assertEquals(RegisterResult.INVALID_EXPERIENCE, result);
    }

    @Test
    public void shouldRejectUnderqualifiedEngineerAtLevelOne() {
        // Given
        OnCallRegistry registry = new OnCallRegistry();
        Engineer engineer = new Engineer("Camila", 104, 8, 1, true);

        // When
        RegisterResult result = registry.registerEngineerForOnCall(engineer);

        // Then
        Assert.assertEquals(RegisterResult.UNDERQUALIFIED, result);
    }

    @Test
    public void shouldAcceptQualifiedEngineerAtLevelTwoWithValidExperience() {
        // Given
        OnCallRegistry registry = new OnCallRegistry();
        Engineer engineer = new Engineer("Andres", 105, 5, 2, true);

        // When
        RegisterResult result = registry.registerEngineerForOnCall(engineer);

        // Then
        Assert.assertEquals(RegisterResult.VALID, result);
    }

    @Test
    public void shouldAcceptBoundaryExperienceAtForty() {
        // Given
        OnCallRegistry registry = new OnCallRegistry();
        Engineer engineer = new Engineer("Luisa", 106, 40, 4, true);

        // When
        RegisterResult result = registry.registerEngineerForOnCall(engineer);

        // Then
        Assert.assertEquals(RegisterResult.VALID, result);
    }

    @Test
    public void shouldRejectDuplicatedEngineerById() {
        // Given
        OnCallRegistry registry = new OnCallRegistry();
        Engineer first = new Engineer("Pedro", 999, 7, 3, true);
        Engineer duplicated = new Engineer("Pedro 2", 999, 10, 4, true);

        // When
        RegisterResult firstResult = registry.registerEngineerForOnCall(first);
        RegisterResult duplicatedResult = registry.registerEngineerForOnCall(duplicated);

        // Then
        Assert.assertEquals(RegisterResult.VALID, firstResult);
        Assert.assertEquals(RegisterResult.DUPLICATED, duplicatedResult);
    }
}
