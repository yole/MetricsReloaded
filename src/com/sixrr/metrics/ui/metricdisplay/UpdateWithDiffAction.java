/*
 * Copyright 2005-2011 Sixth and Red River Software, Bas Leijdekkers
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.sixrr.metrics.ui.metricdisplay;

import com.intellij.analysis.AnalysisScope;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.IconLoader;
import com.sixrr.metrics.metricModel.MetricsExecutionContextImpl;
import com.sixrr.metrics.metricModel.MetricsRunImpl;
import com.sixrr.metrics.metricModel.TimeStamp;
import com.sixrr.metrics.profile.MetricsProfile;
import com.sixrr.metrics.utils.MetricsReloadedBundle;

import javax.swing.*;

class UpdateWithDiffAction extends AnAction {

    private static final Icon UPDATE_ICON = IconLoader.getIcon("/actions/refreshUsages.png");

    private final MetricsToolWindow toolWindow;
    private final Project project;

    UpdateWithDiffAction(MetricsToolWindow toolWindow, Project project) {
        super(MetricsReloadedBundle.message("update.with.differences.action"),
                MetricsReloadedBundle.message("update.with.differences.description"), UPDATE_ICON);
        this.toolWindow = toolWindow;
        this.project = project;
    }

    @Override
    public void actionPerformed(AnActionEvent event) {
        final AnalysisScope scope = toolWindow.getCurrentScope();
        final MetricsProfile currentProfile = toolWindow.getCurrentProfile();
        final MetricsRunImpl metricsRun = new MetricsRunImpl();
        new MetricsExecutionContextImpl(project, scope) {

            @Override
            public void onFinish() {
                metricsRun.setContext(scope);
                metricsRun.setProfileName(currentProfile.getName());
                metricsRun.setTimestamp(new TimeStamp());
                toolWindow.updateWithDiff(metricsRun);
            }
        }.execute(currentProfile, metricsRun);
    }
}
