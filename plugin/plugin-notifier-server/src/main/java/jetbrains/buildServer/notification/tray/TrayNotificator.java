/*
 * Copyright 2000-2016 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * See LICENSE in the project root for license information.
 */

package jetbrains.buildServer.notification.tray;

import jetbrains.buildServer.Build;
import jetbrains.buildServer.notification.Notificator;
import jetbrains.buildServer.notification.NotificatorRegistry;
import jetbrains.buildServer.notification.TemplateMessageBuilder;
import jetbrains.buildServer.notification.tray.model.Notification;
import jetbrains.buildServer.notification.tray.web.NotificationHandler;
import jetbrains.buildServer.notification.tray.web.SettingsPageExtension;
import jetbrains.buildServer.responsibility.ResponsibilityEntry;
import jetbrains.buildServer.responsibility.TestNameResponsibilityEntry;
import jetbrains.buildServer.serverSide.*;
import jetbrains.buildServer.serverSide.mute.MuteInfo;
import jetbrains.buildServer.serverSide.problems.BuildProblemInfo;
import jetbrains.buildServer.tests.TestName;
import jetbrains.buildServer.users.SUser;
import jetbrains.buildServer.vcs.VcsRoot;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

/**
 * Tray notifier.
 */
public class TrayNotificator implements Notificator {

    private final NotificationHandler myNotificationHandler;
    private final TemplateMessageBuilder myMessageBuilder;

    public TrayNotificator(@NotNull final NotificatorRegistry notificatorRegistry,
                           @NotNull final SettingsPageExtension settingsExtension,
                           @NotNull final NotificationHandler notificationHandler,
                           @NotNull final TemplateMessageBuilder messageBuilder) {
        myMessageBuilder = messageBuilder;
        notificatorRegistry.register(this);
        settingsExtension.register();
        myNotificationHandler = notificationHandler;
    }

    @Override
    public void notifyBuildStarted(@NotNull SRunningBuild build, @NotNull Set<SUser> users) {
        final Map<String, Object> buildMap = myMessageBuilder.getBuildStartedMap(build, users);
        final Notification notification = NotificationMapper.mapBuildNotification("started", buildMap);
        myNotificationHandler.broadcast(notification, users);
    }

    @Override
    public void notifyBuildSuccessful(@NotNull SRunningBuild build, @NotNull Set<SUser> users) {
        final Map<String, Object> buildMap = myMessageBuilder.getBuildSuccessfulMap(build, users);
        final Notification notification = NotificationMapper.mapBuildNotification("successful", buildMap);
        myNotificationHandler.broadcast(notification, users);
    }

    @Override
    public void notifyBuildFailed(@NotNull SRunningBuild build, @NotNull Set<SUser> users) {
        final Map<String, Object> buildMap = myMessageBuilder.getBuildFailedMap(build, users);
        final Notification notification = NotificationMapper.mapBuildNotification("failed", buildMap);
        myNotificationHandler.broadcast(notification, users);
    }

    @Override
    public void notifyBuildFailedToStart(@NotNull SRunningBuild build, @NotNull Set<SUser> users) {
        final Map<String, Object> buildMap = myMessageBuilder.getBuildFailedToStartMap(build, users);
        final Notification notification = NotificationMapper.mapBuildNotification("failedToStart", buildMap);
        myNotificationHandler.broadcast(notification, users);
    }

    @Override
    public void notifyBuildFailing(@NotNull SRunningBuild build, @NotNull Set<SUser> users) {
        final Map<String, Object> buildMap = myMessageBuilder.getBuildFailingMap(build, users);
        final Notification notification = NotificationMapper.mapBuildNotification("failing", buildMap);
        myNotificationHandler.broadcast(notification, users);
    }

    @Override
    public void notifyBuildProbablyHanging(@NotNull SRunningBuild build, @NotNull Set<SUser> users) {
        final Map<String, Object> buildMap = myMessageBuilder.getBuildProbablyHangingMap(build, users);
        final Notification notification = NotificationMapper.mapBuildNotification("probablyHanging", buildMap);
        myNotificationHandler.broadcast(notification, users);
    }

    @Override
    public void notifyLabelingFailed(@NotNull Build build, @NotNull VcsRoot root, @NotNull Throwable exception, @NotNull Set<SUser> users) {

    }

    @Override
    public void notifyResponsibleChanged(@NotNull SBuildType buildType, @NotNull Set<SUser> users) {

    }

    @Override
    public void notifyResponsibleAssigned(@NotNull SBuildType buildType, @NotNull Set<SUser> users) {

    }

    @Override
    public void notifyResponsibleChanged(@Nullable TestNameResponsibilityEntry oldValue, @NotNull TestNameResponsibilityEntry newValue, @NotNull SProject project, @NotNull Set<SUser> users) {

    }

    @Override
    public void notifyResponsibleAssigned(@Nullable TestNameResponsibilityEntry oldValue, @NotNull TestNameResponsibilityEntry newValue, @NotNull SProject project, @NotNull Set<SUser> users) {

    }

    @Override
    public void notifyResponsibleChanged(@NotNull Collection<TestName> testNames, @NotNull ResponsibilityEntry entry, @NotNull SProject project, @NotNull Set<SUser> users) {

    }

    @Override
    public void notifyResponsibleAssigned(@NotNull Collection<TestName> testNames, @NotNull ResponsibilityEntry entry, @NotNull SProject project, @NotNull Set<SUser> users) {

    }

    @Override
    public void notifyBuildProblemResponsibleAssigned(@NotNull Collection<BuildProblemInfo> buildProblems, @NotNull ResponsibilityEntry entry, @NotNull SProject project, @NotNull Set<SUser> users) {

    }

    @Override
    public void notifyBuildProblemResponsibleChanged(@NotNull Collection<BuildProblemInfo> buildProblems, @NotNull ResponsibilityEntry entry, @NotNull SProject project, @NotNull Set<SUser> users) {

    }

    @Override
    public void notifyTestsMuted(@NotNull Collection<STest> tests, @NotNull MuteInfo muteInfo, @NotNull Set<SUser> users) {

    }

    @Override
    public void notifyTestsUnmuted(@NotNull Collection<STest> tests, @NotNull MuteInfo muteInfo, @Nullable SUser user, @NotNull Set<SUser> users) {

    }

    @Override
    public void notifyBuildProblemsMuted(@NotNull Collection<BuildProblemInfo> buildProblems, @NotNull MuteInfo muteInfo, @NotNull Set<SUser> users) {

    }

    @Override
    public void notifyBuildProblemsUnmuted(@NotNull Collection<BuildProblemInfo> buildProblems, @NotNull MuteInfo muteInfo, @Nullable SUser user, @NotNull Set<SUser> users) {

    }

    @NotNull
    @Override
    public String getNotificatorType() {
        return NotifierConstants.NOTIFIER_TYPE;
    }

    @NotNull
    @Override
    public String getDisplayName() {
        return NotifierConstants.NOTIFIER_DISPLAY_NAME;
    }
}
